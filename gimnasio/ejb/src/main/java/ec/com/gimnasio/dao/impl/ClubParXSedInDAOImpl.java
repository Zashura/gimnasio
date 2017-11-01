package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import ec.com.gimnasio.dao.ClubParXSedInDAO;
import ec.com.gimnasio.model.CluParXSedIn;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubParXSedInDAOImpl extends GenericDAOImpl<CluParXSedIn, Long> implements ClubParXSedInDAO {

	@Override
	public CluParXSedIn findByCodigo(long codigo){
		try{
			StringBuilder sentencia = new StringBuilder().append("select o from CluParXSedIn o ");
			sentencia.append("where o.pasiCodigo = :codigo and o.pasiEstado=:estado");
			return (CluParXSedIn) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CluParXSedIn> findByInstitucion(long codInstitucion){
		StringBuilder sentencia = new StringBuilder().append("select o from CluParXSedIn o,ClubSedIn si,ClubInstitucion i,ClubSede sd ");
		sentencia.append("where si.clubInstitucion.cluCodigo = i.cluCodigo and o.clubSedIn.seinCodigo=si.seinCodigo ");
		sentencia.append("and si.clubSede.sedCodigo=sd.sedCodigo ");
		sentencia.append("and i.cluCodigo = :codigo and o.pasiEstado=:estado");
		List<CluParXSedIn> aux= getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codInstitucion).setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
		for (CluParXSedIn item : aux) {
			item.getClubSedIn().getClubInstitucion().getCluCodigo();
			item.getClubSedIn().getClubInstitucion().getCluDescripcion();
			item.getClubSedIn().getClubSede().getSedCodigo();
			item.getClubSedIn().getClubSede().getSedDescripcion();
			item.getClubParroquia().getParCodigo();
			item.getClubParroquia().getParDescripcion();
			item.getClubParroquia().getClubCanton().getCanCodigo();
			item.getClubParroquia().getClubCanton().getCanDescripcion();
			item.getClubParroquia().getClubCanton().getClubProvincia().getProCodigo();
			item.getClubParroquia().getClubCanton().getClubProvincia().getProDescripcion();
		}
		return aux;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CluParXSedIn> findBySedeNombre(String nombreSede,long codInstitucion){
		StringBuilder sentencia = new StringBuilder().append("select o from CluParXSedIn o,ClubSedIn si,ClubInstitucion i,ClubSede sd ");
		sentencia.append("where si.clubInstitucion.cluCodigo = i.cluCodigo and o.clubSedIn.seinCodigo=si.seinCodigo ");
		sentencia.append("and si.clubSede.sedCodigo=sd.sedCodigo and sd.sedDescripcion like :nombre ");
		sentencia.append("and i.cluCodigo = :codigo and o.pasiEstado=:estado");
		List<CluParXSedIn> aux= getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codInstitucion).setParameter("nombre","%"+nombreSede+"%").setParameter("estado" , Constantes.REGISTRO_ACTIVO_NUMERO).getResultList();
		for (CluParXSedIn item : aux) {
			item.getClubSedIn().getClubInstitucion().getCluCodigo();
			item.getClubSedIn().getClubInstitucion().getCluDescripcion();
			item.getClubSedIn().getClubSede().getSedCodigo();
			item.getClubSedIn().getClubSede().getSedDescripcion();
			item.getClubParroquia().getParCodigo();
			item.getClubParroquia().getParDescripcion();
			item.getClubParroquia().getClubCanton().getCanCodigo();
			item.getClubParroquia().getClubCanton().getCanDescripcion();
			item.getClubParroquia().getClubCanton().getClubProvincia().getProCodigo();
			item.getClubParroquia().getClubCanton().getClubProvincia().getProDescripcion();
		}
		return aux;
	}
	
}
