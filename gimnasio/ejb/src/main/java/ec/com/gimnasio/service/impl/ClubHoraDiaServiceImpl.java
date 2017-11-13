package ec.com.gimnasio.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.com.gimnasio.dao.ClubHoraDiaDAO;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;
import ec.com.gimnasio.model.ClubHorDia;
import ec.com.gimnasio.service.ClubHoraDiaService;

@Stateless
public class ClubHoraDiaServiceImpl implements ClubHoraDiaService {
	
	@Inject
	private ClubHoraDiaDAO clubHoraDiaDAO;
	
	@Override
	public ClubHorDia buscarPorId(Long id){
		return clubHoraDiaDAO.findById(id);
	}
	
	@Override
	public List<ClubHorDia> obtenerActivas(){
		return clubHoraDiaDAO.obtenerActivas();
	}
	
	@Override
	public ClubHorDia findByDiaHora(long codDia,long codHora){
		return clubHoraDiaDAO.findByDiaHora(codDia,codHora);
	}
	
	@Override
	public void crear(ClubHorDia aplicacion){
		try {
			clubHoraDiaDAO.persist(aplicacion);
		} catch (ClubPersistException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public ClubHorDia actualizar(ClubHorDia aplicacion){
		ClubHorDia app=new ClubHorDia();
			try {
				app = clubHoraDiaDAO.update(aplicacion);	
			} catch (ClubUpdateException e) {
				e.printStackTrace();
			}
			return app;
    }
	
	@Override
	public ClubHorDia findByCodigo(long codigo){
		return clubHoraDiaDAO.findByCodigo(codigo);
	}
}
