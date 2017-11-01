package ec.com.gimnasio.resources;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

import ec.com.control.acceso.model.Recurso;

@FacesComponent("ec.com.gimnasio.resources.MenuComponent")
public class MenuComponent extends UIComponentBase {
	
	private List<Recurso> value;
	
	@Override
    public String getFamily() {        
        return "ec.com.gimnasio.resources.MenuComponent";
    }
 
	@Override
    public String getRendererType() {
        return "ec.com.gimnasio.resources.MenuRenderer";
    }

	public List<Recurso> getValue() {
	    final ValueExpression valueExpression = getValueExpression("value");
	    if (valueExpression != null) {
	        @SuppressWarnings("unchecked")
			final List<Recurso> elValue = (List<Recurso>) valueExpression.getValue(
	            getFacesContext().getELContext());
	        return elValue;
	    }
	    return value;
	}

	public void setValue(List<Recurso> value) {
		this.value = value;
	}
    
}
