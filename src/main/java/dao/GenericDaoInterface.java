package dao;
import java.io.Serializable;
import java.util.*;


public interface GenericDaoInterface <T, Id extends Serializable> {
	    public void createOrUpdate(T entity);
	    public T findById(Id id);
	    public void delete(T entity);
	    public List<T> findAll();
	    public void deleteAll();
	}

