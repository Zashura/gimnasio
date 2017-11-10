package ec.com.gimnasio.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ec.com.gimnasio.dao.ClubPersonaDAO;
import ec.com.gimnasio.model.ClubPersona;
import ec.com.gimnasio.resources.Constantes;

/**
*
*/
@Stateless
public class ClubPersonaDAOImpl extends GenericDAOImpl<ClubPersona, Long> implements ClubPersonaDAO {

	@Override
	public List<ClubPersona> obtenerActivas() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubPersona> criteria = cb.createQuery(ClubPersona.class);
        Root<ClubPersona> aplicacion = criteria.from(ClubPersona.class);
        criteria.select(aplicacion)
        	.where(cb.equal(aplicacion.get("perEstado"), Constantes.REGISTRO_ACTIVO_NUMERO))
        	.orderBy(cb.asc(aplicacion.get("perNombres")));
        List<ClubPersona> aux= getEntityManager().createQuery(criteria).getResultList();
        for (ClubPersona per : aux) {
			if(per.getClubRepresentante()!=null){
				per.getClubRepresentante().getRepCodigo();
				per.getClubRepresentante().getRepCelular();
				per.getClubRepresentante().getRepConvencional();
				per.getClubRepresentante().getRepDireccion();
				per.getClubRepresentante().getRepEstado();
				per.getClubRepresentante().getRepFechCreacion();
				per.getClubRepresentante().getRepIdentificacion();
				per.getClubRepresentante().getRepNombres();
			}
			if(per.getClubSexo()!=null){
				per.getClubSexo().getSexCodigo();
				per.getClubSexo().getSexDescripcion();
				per.getClubSexo().getSexEstado();
				per.getClubSexo().getSexNemonico();
			}
			if(per.getClubTipIde()!=null){
				per.getClubTipIde().getTiinCodigo();
				per.getClubTipIde().getTiinDescripcion();
				per.getClubTipIde().getTiinEstado();
				per.getClubTipIde().getTiinFecCreacion();
				per.getClubTipIde().getTiinNemonico();
			}
		}
        return aux;
	}

	@Override
	public List<ClubPersona> buscarPorNombre(String nombre) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ClubPersona> criteria = cb.createQuery(ClubPersona.class);
        Root<ClubPersona> aplicacion = criteria.from(ClubPersona.class);
        criteria.select(aplicacion).where(
        			cb.like(aplicacion.<String>get("perNombres"),"%")
        		)
        	.orderBy(cb.asc(aplicacion.get("perNombres")));
        return getEntityManager().createQuery(criteria).getResultList();
	}
	
	@Override
	public ClubPersona findByCodigo(long codigo){
		StringBuilder sentencia = new StringBuilder().append("select o from ClubPersona o ");
		sentencia.append("where o.perCodigo = :codigo and o.perEstado=:estado ");
		ClubPersona aplicacion = (ClubPersona) getEntityManager().createQuery(sentencia.toString()).setParameter("codigo" , codigo).setParameter("estado", Constantes.REGISTRO_ACTIVO_NUMERO).getSingleResult();
		return aplicacion;
	}
	
	
}
