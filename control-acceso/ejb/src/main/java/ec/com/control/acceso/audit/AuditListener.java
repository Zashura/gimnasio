package ec.com.control.acceso.audit;

import java.util.Map;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class AuditListener implements RevisionListener {
	
	@SuppressWarnings("unchecked")
	@Override
	public void newRevision(Object revisionEntity) {
		RevEntity revEntity = (RevEntity) revisionEntity;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null && context.getAuthentication() != null
				&& context.getAuthentication().getName()!=null) {
			revEntity.setUsuario(context.getAuthentication().getName());
		} else {
			revEntity.setUsuario("WS");
		}
		
		// agregamos la ip y el hostName a la auditoria
		try {
			Map<String, Object> info = (Map<String, Object>)context.getAuthentication().getDetails();  
			String ipAddress = (String) info.get("ipAddressUserInSession");  
			String hostName = (String) info.get("hostNameUserInSession");  
			if(ipAddress == null || ipAddress.isEmpty()){
				WebAuthenticationDetails details = (WebAuthenticationDetails) context
						.getAuthentication().getDetails();
				ipAddress = details.getRemoteAddress();
			}
			
			//seteamos los valores
			revEntity.setIp(ipAddress);
			revEntity.setHostName(hostName);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
