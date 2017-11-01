package ec.com.control.acceso.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.control.acceso.dao.ClaveUsuarioDAO;
import ec.com.control.acceso.dao.ParametroDAO;
import ec.com.control.acceso.enumeration.EstadoCaducidadClave;
import ec.com.control.acceso.exception.EducacionUpdateException;
import ec.com.control.acceso.model.ClaveUsuario;
import ec.com.control.acceso.model.Parametro;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.model.Parametro.EnumParametro;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.resources.EncryptUtils;
import ec.com.control.acceso.service.ClaveUsuarioService;
import ec.com.control.acceso.service.remote.ClaveServiceRemote;

@Stateless
public class ClaveServiceImpl implements ClaveServiceRemote, ClaveUsuarioService {


	@Inject
	private ClaveUsuarioDAO claveUsuarioDAO;
	
	@Inject
	private ParametroDAO parametroDAO;
	
	
	@Override
	public List<ClaveUsuario> obtenerClaves(Usuario usuario) {
		// TODO Auto-generated method stub
		return claveUsuarioDAO.obtenerClaves(usuario);
	}
	
	public boolean primerIngreso(String usuarioLogueado){
		return (claveUsuarioDAO.totalClavesUsuario(usuarioLogueado) <= 1);
	}
	
	public boolean contraseniaUsada(String claveComprobar){
		String claveEncriptada = "";
		boolean soloActivos = true;
		boolean soloInactivos = false;
		try {
			claveEncriptada = EncryptUtils.applyAlgorithm(claveComprobar, EncryptUtils.MD5, EncryptUtils.UTF);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (claveUsuarioDAO.totalClavesUsuarioCoincidentes(claveEncriptada, soloActivos, soloInactivos) > 1);
	}
	
	public boolean entreUltimasContrasenias(String claveComprobar, Usuario usuario){
		String claveEncriptada = "";
		boolean existe = false;
		try {
			claveEncriptada = EncryptUtils.applyAlgorithm(claveComprobar, EncryptUtils.MD5, EncryptUtils.UTF);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Parametro parametro = parametroDAO.obtenerPorEnumeracion(EnumParametro.NUM_CLAVES);
		List<String> cincoUltimasClaves = claveUsuarioDAO.obtenerUltimasClaves(usuario.getCodigo(), ((parametro!=null && parametro.getValor() != null)?parametro.getValor():1));
		if(!cincoUltimasClaves.isEmpty()){
			for (String clave : cincoUltimasClaves) {
				if(clave.equals(claveEncriptada)){
					existe = true;
					break;
				}
			}
		}
		return existe;
	}
	
	public void inactivarClaveUsuario(Usuario usuario, String tipoEstadoFinClave) {
		try {
			ClaveUsuario claveActiva = claveUsuarioDAO.obtenerActiva(usuario);
			claveActiva.setFechaFin(new Date());
			claveActiva.setEstado(Constantes.REGISTRO_INACTIVO);
			claveActiva.setEstadoFinalizacionClave(tipoEstadoFinClave);
			claveUsuarioDAO.update(claveActiva);
		} catch (EducacionUpdateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inactivarPorCaducidadTiempoVigencia(Usuario usuario){
		if(determinarInactivarPorCaducidadTiempoVigencia(usuario)){
			ClaveUsuario claveActiva = claveUsuarioDAO.obtenerActiva(usuario);
			inactivarClaveUsuario(claveActiva, EstadoCaducidadClave.CADUCIDAD_VIGENCIA.getValor());
		}
	}
	
	private Date sumarRestarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 
		calendar.add(Calendar.DAY_OF_YEAR, dias); 
		return calendar.getTime(); 
	}
	
	public void inactivarClaveUsuario(ClaveUsuario claveActiva, String tipoEstadoFinClave) {
		try {
			claveActiva.setFechaFin(new Date());
			claveActiva.setEstado(Constantes.REGISTRO_INACTIVO);
			claveActiva.setEstadoFinalizacionClave(tipoEstadoFinClave);
			claveUsuarioDAO.update(claveActiva);
		} catch (EducacionUpdateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inactivarPorCaducidadMasiva(Usuario usuario){
		if(determinarInactivarPorCaducidadMasiva(usuario)){
			ClaveUsuario claveActiva = claveUsuarioDAO.obtenerActiva(usuario);
			inactivarClaveUsuario(claveActiva, EstadoCaducidadClave.CADUCIDAD_MASIVA.getValor());
		}
	}
	
	public boolean cambiarClaveObligatoriamente(Usuario usuario){
		inactivarPorCaducidadMasiva(usuario);
		inactivarPorCaducidadTiempoVigencia(usuario);
		long totalActivas = claveUsuarioDAO.totalClavesUsuario(usuario, true, false);
		long totalInactivas = claveUsuarioDAO.totalClavesUsuario(usuario, false, true);
		return !(totalActivas == 1 && totalInactivas >= 1);
	}
	
	public boolean determinarInactivarPorCaducidadTiempoVigencia(Usuario usuario){
		boolean inactiva = false;
		Parametro parametro = parametroDAO.obtenerPorEnumeracion(EnumParametro.CAD_DIAS);
		if(parametro != null && parametro.getValor() != null){
			ClaveUsuario claveActiva = claveUsuarioDAO.obtenerActiva(usuario);
			if(claveActiva != null){
				Date fechaActual = new Date();
				Date fechaValidada = sumarRestarDiasFecha(claveActiva.getFechaInicio(), parametro.getValor().intValue());
				if(fechaActual.compareTo(fechaValidada) > 0){
					inactiva = true;
				}
			}
		}
		return inactiva;
	}
	
	public boolean determinarInactivarPorCaducidadMasiva(Usuario usuario){
		boolean inactiva = false;
		Parametro parametro = parametroDAO.obtenerPorEnumeracion(EnumParametro.CAD_MASIVA);
		if(parametro != null && parametro.getValor() != null && parametro.getValor().intValue() == 1){
			inactiva = true;
			ClaveUsuario ultimaClaveInactiva = claveUsuarioDAO.obtenerUltimaInactiva(usuario);
			if(ultimaClaveInactiva != null && ultimaClaveInactiva.getEstadoFinalizacionClave() !=null && ultimaClaveInactiva.getEstadoFinalizacionClave().equals(EstadoCaducidadClave.CADUCIDAD_MASIVA.getValor())){
				inactiva = false;
			}
		}
		return inactiva;
	}
	
	public void actualizarEstadoClaveInactivada(Usuario usuario, int opcionEstadoFinClave){
		ClaveUsuario ultimaClaveInactiva = claveUsuarioDAO.obtenerUltimaInactiva(usuario);
		String estadoFinalizacion = null;
		switch (opcionEstadoFinClave) {
		case 1:
			estadoFinalizacion = EstadoCaducidadClave.CADUCIDAD_MASIVA.getValor();
			break;
		case 2:
			estadoFinalizacion = EstadoCaducidadClave.CADUCIDAD_VIGENCIA.getValor();
			break;
		case 3:
			estadoFinalizacion = EstadoCaducidadClave.CAMBIO_CONTRASENIA.getValor();
			break;
		}
		try {
			ultimaClaveInactiva.setEstadoFinalizacionClave(estadoFinalizacion);
			claveUsuarioDAO.update(ultimaClaveInactiva);
		} catch (EducacionUpdateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
