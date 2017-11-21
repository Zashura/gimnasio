package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubInsDisSedClubDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubInsDisSedClub;
import ec.com.gimnasio.service.ClubInsDisSedClubService;

@Stateless
public class ClubInsDisSedClubServiceImpl implements ClubInsDisSedClubService {
	
	@Inject
	private ClubInsDisSedClubDAO clubInsDisSedClubDAO;
	
	@Override
	public ClubInsDisSedClub buscarPorId(Long id){
		return clubInsDisSedClubDAO.findById(id);
	}
	
	@Override
	public List<ClubInsDisSedClub> findPersonaInstitucion(long codPersona,long codInstitucion){
		return clubInsDisSedClubDAO.findPersonaInstitucion(codPersona,codInstitucion);
	}
	
	@Override
	public void crear(ClubInsDisSedClub aplicacion){
		try {
			clubInsDisSedClubDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubInsDisSedClub actualizar(ClubInsDisSedClub aplicacion){
		ClubInsDisSedClub app=new ClubInsDisSedClub();
			try {
				app = clubInsDisSedClubDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubInsDisSedClub findByCodigo(long codigo){
		return clubInsDisSedClubDAO.findByCodigo(codigo);
	}
	
	@Override
	public ClubInsDisSedClub findByInscripcionHorarioDisciplina(long codInscripcion,long codHorario,long codDisciplina){
		return clubInsDisSedClubDAO.findByInscripcionHorarioDisciplina(codInscripcion,codHorario,codDisciplina);
	}
}
