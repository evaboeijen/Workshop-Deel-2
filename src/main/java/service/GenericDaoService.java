package service;

import java.util.List;
import business.*;
import dao.GenericDao;

 

public class GenericDaoService<T> {
	
	private GenericDao<T> genericDao;

	public GenericDaoService() {
		genericDao = new GenericDao<T>();
	}

	public void saveOrUpdate(T entity) {
		genericDao.openCurrentSessionwithTransaction();
		genericDao.createOrUpdate(entity);
		genericDao.closeCurrentSessionwithTransaction();
	}

	public T findById(T entity, Long id) {
		genericDao.openCurrentSession();
		entity = genericDao.findById(id);
		genericDao.closeCurrentSession();
		return entity;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		genericDao.openCurrentSessionwithTransaction();
		T entity = (T)genericDao.findById(id);
		System.out.println(entity.getClass() + "will be deleted.");
		genericDao.delete(entity);
		genericDao.closeCurrentSessionwithTransaction();
	}
	
	public List<T> findAll() {
		genericDao.openCurrentSession();
		List<T> entities = genericDao.findAll();
		genericDao.closeCurrentSession();
		return entities;
	}

	public void deleteAll() {
		genericDao.openCurrentSessionwithTransaction();
		genericDao.deleteAll();
		genericDao.closeCurrentSessionwithTransaction();
	}

	public GenericDao<T> genericDao() {
		return genericDao;
	}
}


