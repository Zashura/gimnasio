package ec.com.control.acceso.scope;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

/**
 * 
 * 
 * @author BontaLabs Cia. Ltda.
 * @version $Revision: 1.0 $
 */
public class ViewContextExtension implements Extension {

	public void afterBeanDiscovery(@Observes AfterBeanDiscovery event,
			BeanManager manager) {
		event.addContext(new ViewContext());
	}
}
