package dao;
import java.io.Serializable;
import java.util.*;

import business.Bestelling;


public interface BestellingDaoInterface <T, Id extends Serializable> {
	    public void persist(T entity);
	    public void update(T entity);
	    public T findById(Id id);
	    public void delete(T entity);
	    public List<T> findAll();
	    public void deleteAll();
	}



