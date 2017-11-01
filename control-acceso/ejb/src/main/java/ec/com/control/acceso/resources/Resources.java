package ec.com.control.acceso.resources;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

public class Resources {
	
    // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
    @Produces
    @PersistenceContext(unitName = "seguridades-pu")
    private EntityManager emSeguridades;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
