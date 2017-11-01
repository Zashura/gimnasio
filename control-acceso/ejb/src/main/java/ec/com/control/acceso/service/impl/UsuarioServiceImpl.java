package ec.com.control.acceso.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import ec.com.control.acceso.dao.AsignacionUsuarioSedeDAO;
import ec.com.control.acceso.dao.ClaveUsuarioDAO;
import ec.com.control.acceso.dao.UsuarioDAO;
import ec.com.control.acceso.exception.ClaveNoValidaException;
import ec.com.control.acceso.exception.EducacionPersistException;
import ec.com.control.acceso.exception.EducacionUpdateException;
import ec.com.control.acceso.exception.UsuarioException;
import ec.com.control.acceso.model.AsignacionUsuarioSede;
import ec.com.control.acceso.model.ClaveUsuario;
import ec.com.control.acceso.model.Sede;
import ec.com.control.acceso.model.Usuario;
import ec.com.control.acceso.resources.Constantes;
import ec.com.control.acceso.resources.EncryptUtils;
import ec.com.control.acceso.service.UsuarioService;
import ec.com.control.acceso.service.remote.UsuarioServiceRemote;

@Stateless
public class UsuarioServiceImpl implements UsuarioService, UsuarioServiceRemote {
	
	@Inject
    private Logger log;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ClaveUsuarioDAO claveUsuarioDAO;
	
	@Inject
	private AsignacionUsuarioSedeDAO asignacionDAO;
	
	@Override
	public Usuario buscarPorId(Long id) throws UsuarioException {
		return usuarioDAO.findById(id);
	}

	public void crear(Usuario usuario) throws UsuarioException {
        try {
        	log.info("---> Creando nuevo usuario: " + usuario.getNombres() + " " + usuario.getApellidos());
        	usuario.setEstado(Constantes.REGISTRO_ACTIVO);
        	usuario.setCambioClave(Constantes.SI);
        	usuario.setActualizacionDatos(Constantes.SI);
        	usuarioDAO.persist(usuario);
        	cambiarClave(usuario, null, usuario.getIdentificacion());
		} catch (EducacionPersistException e) {
			throw new UsuarioException(e);
		} catch (ClaveNoValidaException e) {
			throw new UsuarioException(e);
		}
    }
	
	@Override
	public Usuario actualizar(Usuario usuario) throws UsuarioException {
        try {
        	log.info("---> Actualizando usuario: " + usuario.getNombres() + " " + usuario.getApellidos());
        	Usuario usr = usuarioDAO.update(usuario);
			return usr;
		} catch (EducacionUpdateException e) {
			throw new UsuarioException(e);
		}
    }
	
	@Override
	public Usuario desactivar(Usuario usuario) throws UsuarioException {
        try {
        	log.info("---> Desactivando usuario: " + usuario.getNombres() + " " + usuario.getApellidos());
        	usuario.setEstado(Constantes.REGISTRO_INACTIVO);
        	Usuario usr = usuarioDAO.update(usuario);
			return usr;
		} catch (EducacionUpdateException e) {
			throw new UsuarioException(e);
		}
    }
	
	@Override
	public Usuario activar(Usuario usuario) throws UsuarioException {
        try {
        	log.info("---> Activando usuario: " + usuario.getNombres() + " " + usuario.getApellidos());
        	usuario.setEstado(Constantes.REGISTRO_ACTIVO);
        	Usuario usr = usuarioDAO.update(usuario);
			return usr;
		} catch (EducacionUpdateException e) {
			throw new UsuarioException(e);
		}
    }

	@Override
	public List<Usuario> obtenerTodos(boolean soloActivos) {
		return usuarioDAO.obtenerTodos(false);
	}

	@Override
	public List<Usuario> obtenerPorNombre(String nombre, boolean soloActivos) {
		return usuarioDAO.buscarPorNombre(nombre, soloActivos);
	}
	
	@Override
	public List<Usuario> buscarUsuarioNombre(String nombre, String apellido) {
		return usuarioDAO.buscarUsuarioNombre(nombre, apellido);
	}
	
	public Usuario buscarUsuarioId(String identificacion){
		return usuarioDAO.buscarUsuarioId(identificacion);
	}
	
	@Override
	public List<Usuario> obtenerPorNombreYSedes(String nombre, List<Long> codigosSede, boolean soloActivos) {
		return usuarioDAO.obtenerPorNombreYSedes(nombre, codigosSede, soloActivos);
	}
	
	@Override
	public void cambiarClave(Usuario usuario, String claveAnterior, String nuevaClave) throws UsuarioException, ClaveNoValidaException {
		try {
			String claveEncriptada = EncryptUtils.applyAlgorithm(nuevaClave, EncryptUtils.MD5, EncryptUtils.UTF);
			ClaveUsuario claveActiva = claveUsuarioDAO.obtenerActiva(usuario);
			if (claveActiva == null) {
				claveActiva = new ClaveUsuario();
				claveActiva.setUsuario(usuario);
				claveActiva.setEstado(Constantes.REGISTRO_ACTIVO);
				claveActiva.setFechaInicio(new Date());
				claveActiva.setClave(claveEncriptada);
				claveUsuarioDAO.persist(claveActiva);
				usuario.setCambioClave(claveAnterior == null ? Constantes.SI : Constantes.NO);
				usuarioDAO.update(usuario);
			} else {
				if (EncryptUtils.sonIguales(claveAnterior, claveActiva.getClave(), EncryptUtils.MD5, EncryptUtils.UTF)) {
					Date fechaActual = new Date();
					claveActiva.setFechaFin(fechaActual);
					claveActiva.setEstado(Constantes.REGISTRO_INACTIVO);
					claveUsuarioDAO.update(claveActiva);
					claveActiva = new ClaveUsuario();
					claveActiva.setUsuario(usuario);
					claveActiva.setEstado(Constantes.REGISTRO_ACTIVO);
					claveActiva.setFechaInicio(new Date());
					claveActiva.setClave(claveEncriptada);
					claveUsuarioDAO.persist(claveActiva);
					usuario.setCambioClave(Constantes.NO);
					usuario.setFechaSolicitudClave(null);
					usuarioDAO.update(usuario);
				} else {
					throw new ClaveNoValidaException("La clave ingresada no es igual a la anterior");
				}
			}
		} catch (NoSuchAlgorithmException ex) {
			log.error("Error al desencriptar la clave con el algoritmo enviado", ex);
			throw new UsuarioException("No se ha podido desencriptar la clave anterior", ex);
		} catch (UnsupportedEncodingException ex) {
			log.error("Error al desencriptar la clave con la codificacion enviada", ex);
			throw new UsuarioException("No se ha podido desencriptar la clave anterior", ex);
		} catch (EducacionPersistException e) {
			log.error("Error al cambiar la clave del usuario", e);
			throw new UsuarioException("Ha existido un error en el cambio de clave del usuario", e);
		} catch (EducacionUpdateException e) {
			log.error("Error al cambiar la clave del usuario", e);
			throw new UsuarioException("Ha existido un error en el cambio de clave del usuario", e);
		}
	}
	
	@Override
	public void cambiarClaveExterno(Usuario usuario, String nuevaClave) throws UsuarioException, ClaveNoValidaException {
		try {
			String claveEncriptada = EncryptUtils.applyAlgorithm(nuevaClave, EncryptUtils.MD5, EncryptUtils.UTF);
			ClaveUsuario claveActiva = claveUsuarioDAO.obtenerActiva(usuario);
			if (claveActiva == null) {
				claveActiva = new ClaveUsuario();
				claveActiva.setUsuario(usuario);
				claveActiva.setEstado(Constantes.REGISTRO_ACTIVO);
				claveActiva.setFechaInicio(new Date());
				claveActiva.setClave(claveEncriptada);
				claveUsuarioDAO.persist(claveActiva);
			} else {
				Date fechaActual = new Date();
				claveActiva.setFechaFin(fechaActual);
				claveActiva.setEstado(Constantes.REGISTRO_INACTIVO);
				claveUsuarioDAO.update(claveActiva);
				claveActiva = new ClaveUsuario();
				claveActiva.setUsuario(usuario);
				claveActiva.setEstado(Constantes.REGISTRO_ACTIVO);
				claveActiva.setFechaInicio(new Date());
				claveActiva.setClave(claveEncriptada);
				claveUsuarioDAO.persist(claveActiva);
				usuario.setCambioClave(Constantes.NO);
				usuario.setFechaSolicitudClave(null);
				usuarioDAO.update(usuario);
			}
		} catch (NoSuchAlgorithmException ex) {
			log.error("Error al desencriptar la clave con el algoritmo enviado", ex);
			throw new UsuarioException("No se ha podido desencriptar la clave anterior", ex);
		} catch (UnsupportedEncodingException ex) {
			log.error("Error al desencriptar la clave con la codificacion enviada", ex);
			throw new UsuarioException("No se ha podido desencriptar la clave anterior", ex);
		} catch (EducacionPersistException e) {
			log.error("Error al cambiar la clave del usuario", e);
			throw new UsuarioException("Ha existido un error en el cambio de clave del usuario", e);
		} catch (EducacionUpdateException e) {
			log.error("Error al cambiar la clave del usuario", e);
			throw new UsuarioException("Ha existido un error en el cambio de clave del usuario", e);
		}
	}

	@Override
	public Usuario buscarPorIdentificacion(String identificacion) {
		return usuarioDAO.buscarPorIdentificacion(identificacion);
	}
	
	@Override
	public Usuario buscarPorCorreo(String correo) {
		return usuarioDAO.buscarPorCorreo(correo);
	}
	
	@Override
	public Usuario solicitarRecordarClave(Usuario usuario) throws UsuarioException {
		try { 
			Date date = new Date();
			usuario.setTokenCambioClave(EncryptUtils.applyAlgorithm(date.getTime() + "", EncryptUtils.SHA1, EncryptUtils.ISO));
			usuario.setFechaSolicitudClave(date);
			return usuarioDAO.update(usuario);
		} catch (NoSuchAlgorithmException e) {
			throw new UsuarioException(e);
		} catch (UnsupportedEncodingException e) {
			throw new UsuarioException(e);
		} catch (EducacionUpdateException e) {
			throw new UsuarioException(e);
		}
	}
	
	@Override
	public void asignarSede(Usuario usuario, Sede sede) throws UsuarioException {
		AsignacionUsuarioSede anterior = asignacionDAO.obtenerUltimaAsignacion(usuario);
		if (anterior != null) {
			if(anterior.getSede().getCodigo().equals(sede.getCodigo())) {
				throw new UsuarioException("No se puede asignar dos veces al mismo usuario en la misma sede");
			} else {
				try {
					anterior.setFechaFinalizacion(new Date());
					asignacionDAO.update(anterior);
				} catch(EducacionUpdateException e) {
					throw new UsuarioException(e);
				}
			}
		}
		AsignacionUsuarioSede asignacion = new AsignacionUsuarioSede();
		asignacion.setFechaAsignacion(new Date());
		asignacion.setSede(sede);
		asignacion.setUsuario(usuario);
		usuario.setSede(sede);
		try {
			asignacionDAO.persist(asignacion);
			usuarioDAO.update(usuario);
		} catch (EducacionPersistException e) {
			throw new UsuarioException(e);
		} catch(EducacionUpdateException e) {
			throw new UsuarioException(e);
		}
	}

	@Override
	public List<Usuario> obtenerPorSede(Sede sede) {
		return asignacionDAO.obtenerPorSede(sede);
	}
	
	public List<Usuario> buscarUsuarioPorCorreoElectronicoVariosResultados(String correo){
		return usuarioDAO.buscarPorCorreoElectronico(correo);
	}
	
	public boolean existeCorreoElectronicoUsuario(String correo){
		boolean existe = false;
		if(!usuarioDAO.buscarPorCorreoElectronico(correo).isEmpty()){
			existe = true;
		}
		return existe;
	}
	
}
