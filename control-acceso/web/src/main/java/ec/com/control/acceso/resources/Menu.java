package ec.com.control.acceso.resources;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String nombre;
	
	private String url;
	
	private List<Menu> items;

	public Menu(String nombre, String url) {
		this.nombre = nombre;
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getItems() {
		return items;
	}

	public void setItems(List<Menu> items) {
		this.items = items;
	}
	
	public void agregarMenu(Menu menu) {
		if(items == null) {
			items = new ArrayList<Menu>();
		}
		items.add(menu);
	}
	
}
