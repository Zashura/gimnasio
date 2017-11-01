/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.gimnasio.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteJasper {
	private static final String JASPER = ".jasper";
	private static final String TIPO_PDF = "pdf";
	private String PATH_REPORTES = "/report/";
	private ServletContext aux;

	/**
	 * Metodo que permite generar el reporte
	 * @param parametros enviados al reporte
	 * @param ds con la lista enviada al reporte
	 * @param nombre del reporte
	 */
	@SuppressWarnings("deprecation")
	public void generarReporte(Map<String, Object> parametros,
			JRBeanCollectionDataSource ds, String nombreReporte) {
		byte[] archivo = null;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance()
					.getExternalContext().getContext();
			String realPath = aux.getRealPath("/");
			parametros.put("SUBREPORT_DIR", realPath+ "jasperReportsUsuario/");
			
			JasperReport report = (JasperReport) JRLoader.loadObject(realPath
					+ PATH_REPORTES + nombreReporte + JASPER);
			JasperPrint jasperPrint = new JasperPrint();
			jasperPrint = JasperFillManager.fillReport(report, parametros, ds);
			
			archivo = JasperExportManager.exportReportToPdf(jasperPrint);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
			exporter.exportReport();
			archivo = outputStream.toByteArray();

			verReporte(archivo, nombreReporte, TIPO_PDF);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("general",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
	}
	
	/**
	 * Metodo que permite generar el reporte y devuelve el archivo
	 * @param parametros
	 * @param ds
	 * @param nombreReporte
	 * @return byte[]
	 */
	@SuppressWarnings("deprecation")
	public byte[] generarReporteArchivo(Map<String, Object> parametros,
			JRBeanCollectionDataSource ds, String nombreReporte) {
		byte[] archivo = null;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance()
					.getExternalContext().getContext();
			String realPath = aux.getRealPath("/");
			
			parametros.put("SUBREPORT_DIR", realPath+ "jasperReportsUsuario/");
			
			JasperReport report = (JasperReport) JRLoader.loadObject(realPath
					+ PATH_REPORTES + nombreReporte + JASPER);

			JasperPrint jasperPrint = new JasperPrint();
			jasperPrint = JasperFillManager.fillReport(report, parametros, ds);
			archivo = JasperExportManager.exportReportToPdf(jasperPrint);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
			exporter.exportReport();
			archivo = outputStream.toByteArray();

			verReporte(archivo, nombreReporte, TIPO_PDF);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					"general",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"No se puede Generar el archivo PDF", ""));
		}
		return archivo;
	}
	
	/**
	 * Metodo que permite visualizar el reporte utilizdo por jasper
	 * @param data
	 * @param nombreArchivo
	 * @param tipo
	 */
	public void verReporte(byte[] data, String nombreArchivo, String tipo) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		try {
			OutputStream responseStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ nombreArchivo + ".pdf\"");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentLength(data.length);
			responseStream.write(data);
			response.flushBuffer();
			responseStream.close();
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("general",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
		facesContext.responseComplete();
	}
	
	@SuppressWarnings("deprecation")
	public void generarReporteNavegador(Map<String, Object> parametros,
			JRBeanCollectionDataSource ds, String nombreReporte) {
		byte[] archivo = null;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance()
					.getExternalContext().getContext();
			String realPath = aux.getRealPath("/");
			
			parametros.put("SUBREPORT_DIR", realPath+ "jasperReportsUsuario/");
			parametros.put("IMAGE_DIR", realPath+ "img/");
			
			JasperReport report = (JasperReport) JRLoader.loadObject(realPath
					+ PATH_REPORTES + nombreReporte + JASPER);
			JasperPrint jasperPrint = new JasperPrint();
			jasperPrint = JasperFillManager.fillReport(report, parametros, ds);

			archivo = JasperExportManager.exportReportToPdf(jasperPrint);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
			exporter.exportReport();
			archivo = outputStream.toByteArray();

			verReporteNavegador(archivo);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("general",new FacesMessage(FacesMessage.SEVERITY_ERROR,"", ""));
		}
	}
	
	@SuppressWarnings("deprecation")
	public void generarReporteExcel(Map<String, Object> parametros,
			JRBeanCollectionDataSource ds, String nombreReporte) {
		 ServletOutputStream servletOutputStream = null;
		    try {
		        aux = (ServletContext) FacesContext.getCurrentInstance()
						.getExternalContext().getContext();
				String realPath = aux.getRealPath("/");
				JasperReport report = (JasperReport) JRLoader.loadObject(realPath
						+ PATH_REPORTES + nombreReporte + JASPER);
		        JasperPrint jasperPrint= JasperFillManager.fillReport(report,parametros,
		                ds);
		        HttpServletResponse httpServletResponse =
		                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		        httpServletResponse.setContentType("application/xlsx");
		        httpServletResponse.addHeader("Content-disposition", "attachment; filename=\""+nombreReporte+".xlsx\"");
		        servletOutputStream =  httpServletResponse.getOutputStream();
		        JRXlsxExporter excelExporter=new JRXlsxExporter();
		        excelExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		        excelExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream);
		        excelExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		        excelExporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE);
		        excelExporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
		        excelExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		        excelExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		        excelExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
		        excelExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		        excelExporter.exportReport();
		        servletOutputStream.flush();
		        servletOutputStream.close();
		        FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					"general",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"", ""));
		}
	}
	
	public void verReporteNavegador(byte[] archivo){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		int read = 0;
		try {
			InputStream fis = new ByteArrayInputStream(archivo);
			OutputStream os = null;
			byte[] bytes1 = new byte[1024];

			os = response.getOutputStream();

			while ((read = fis.read(bytes1)) != -1) {
				os.write(bytes1, 0, read);
			}
			os.flush();
			os.close();
			fis.close();
		} catch (Exception e) {
			
		}
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	
	@SuppressWarnings("deprecation")
	public byte[] generarReporteNavegadorAr(Map<String, Object> parametros,
			JRBeanCollectionDataSource ds, String nombreReporte) {
		byte[] archivo = null;
		try {
			aux = (ServletContext) FacesContext.getCurrentInstance()
					.getExternalContext().getContext();
			String realPath = aux.getRealPath("/");
			
			parametros.put("SUBREPORT_DIR", realPath+ "jasperReportsUsuario/");
			parametros.put("IMAGE_DIR", realPath+ "img/");
			
			JasperReport report = (JasperReport) JRLoader.loadObject(realPath
					+ PATH_REPORTES + nombreReporte + JASPER);
			JasperPrint jasperPrint = new JasperPrint();
			jasperPrint = JasperFillManager.fillReport(report, parametros, ds);

			archivo = JasperExportManager.exportReportToPdf(jasperPrint);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,outputStream);
			exporter.exportReport();
			archivo = outputStream.toByteArray();
			return archivo;
			//verReporteNavegador(archivo);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("general",new FacesMessage(FacesMessage.SEVERITY_ERROR,"", ""));
		}
		return archivo;
	}
}