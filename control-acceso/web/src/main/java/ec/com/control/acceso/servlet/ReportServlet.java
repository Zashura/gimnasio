package ec.com.control.acceso.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 8795000477609113780L;

	@Resource(mappedName = "java:jboss/datasources/control-acceso-ds")
	private DataSource dataSource;
	
	public static final String REPORT_PREFIX = "/report/";
	
	public static final String REPORT_SUFFIX = ".jasper";
	
	public static final String REPORT_DEFINITION_SUFFIX = ".jrxml";
	
	@Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		
		
		try {
			String codigoInscripcion = request.getParameter("codigo");
			String reporte = request.getParameter("reporte");
			JasperReport report = compileReport(reporte);
			Map<String, Object> args = new HashMap<String, Object>();
			
			
			args.put("URL_LOGO", "");
			args.put("NOMBRES_ALUMNO", "");
			args.put("CEDULA_ALUMNO", "");
			args.put("TIPO_REGISTRO", "");
			args.put("NOMBRES_REPRESENTANTE", "");
			args.put("CEDULA_REPRESENTANTE", "");
			args.put("TIPO_REPRESENTANTE", "");
			args.put("DOMICILIO_REPRESENTANTE", "");
			args.put("CONTACTO_REPRESENTANTE", "");
			args.put("NOMBRE_OPERADOR", "");
			args.put("AUTO_REPRESENTADO", "");
			
			byte[] result = JasperRunManager.runReportToPdf(report, args, dataSource.getConnection());
			response.setContentType( "application/pdf" );
		    response.setContentLength(result.length);
			response.getOutputStream().write(result);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private JasperReport compileReport(String reportName) throws JRException {
		JasperReport report = null;
		if (null != getClass().getClassLoader().getResource(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX)) {
			report = JasperCompileManager.compileReport(getClass().getClassLoader().getResourceAsStream(REPORT_PREFIX + reportName + REPORT_DEFINITION_SUFFIX));
		}
		return report;
	}
	
}
