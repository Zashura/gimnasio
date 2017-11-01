package ec.com.control.acceso.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.control.acceso.model.Aplicacion;
import ec.com.control.acceso.service.AplicacionService;

@RequestScoped
public class AplicacionListProducer {
	
	@Inject
	private AplicacionService aplicacionService;
	
	private List<Aplicacion> aplicaciones;
	
	@Produces
    @Named
    public List<Aplicacion> getAplicaciones() {
        return aplicaciones;
    }

    public void onAplicacionListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Aplicacion persona) {
    	obtenerTodasAplicaciones();
    }

    @PostConstruct
    public void obtenerTodasAplicaciones() {
    	aplicaciones = aplicacionService.obtenerActivas();
    }

}
