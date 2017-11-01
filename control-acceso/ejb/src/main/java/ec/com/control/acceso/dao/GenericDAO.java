package ec.com.control.acceso.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ec.com.control.acceso.exception.EducacionDeleteException;
import ec.com.control.acceso.exception.EducacionPersistException;
import ec.com.control.acceso.exception.EducacionUpdateException;

public interface GenericDAO<T, ID extends Serializable> {

    public T findById(ID id);

    public List<T> findAll();

    public List<T> findByNamedQuery(String queryName, Object... params);

    public List<T> findByNamedQueryAndNamedParams(String queryName, Map<String, ? extends Object> params);

    public T update(T entity) throws EducacionUpdateException;

    public void delete(T entity) throws EducacionDeleteException;

    public void delete(ID id) throws EducacionDeleteException;

    public void persist(T entity) throws EducacionPersistException;
	
}
