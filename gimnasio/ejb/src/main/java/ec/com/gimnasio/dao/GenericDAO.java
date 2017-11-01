package ec.com.gimnasio.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ec.com.gimnasio.exception.ClubDeleteException;
import ec.com.gimnasio.exception.ClubPersistException;
import ec.com.gimnasio.exception.ClubUpdateException;

/**
*
* @author BontaLabs.
*/
public interface GenericDAO<T, ID extends Serializable> {

    public T findById(ID id);

    public List<T> findAll();

    public List<T> findByNamedQuery(String queryName, Object... params);

    public List<T> findByNamedQueryAndNamedParams(String queryName, Map<String, ? extends Object> params);

    public T update(T entity) throws ClubUpdateException;

    public void delete(T entity) throws ClubDeleteException;

    public void delete(ID id) throws ClubDeleteException;

    public void persist(T entity) throws ClubPersistException;
	
}
