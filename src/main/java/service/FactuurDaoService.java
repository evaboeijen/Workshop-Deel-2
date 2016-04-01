package service;

import java.util.List;
import business.*;
import dao.FactuurDao;

 

public class FactuurDaoService {
	
	private static FactuurDao factuurDao;

	public FactuurDaoService() {
		factuurDao = new FactuurDao();
	}

	public void persist(Factuur entity) {
		factuurDao.openCurrentSessionwithTransaction();
		factuurDao.persist(entity);
		factuurDao.closeCurrentSessionwithTransaction();
	}

	public void update(Factuur entity) {
		factuurDao.openCurrentSessionwithTransaction();
		factuurDao.update(entity);
		factuurDao.closeCurrentSessionwithTransaction();
	}
	
	//public Factuur findById(String id) {
	public Factuur findById(Long id) {
		factuurDao.openCurrentSession();
		Factuur Factuur = factuurDao.findById(id);
		factuurDao.closeCurrentSession();
		return Factuur;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		factuurDao.openCurrentSessionwithTransaction();
		Factuur Factuur = factuurDao.findById(id);
		System.out.println(Factuur + "will be deleted.");
		factuurDao.delete(Factuur);
		factuurDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Factuur> findAll() {
		factuurDao.openCurrentSession();
		List<Factuur> factuur = factuurDao.findAll();
		factuurDao.closeCurrentSession();
		return factuur;
	}

	public void deleteAll() {
		factuurDao.openCurrentSessionwithTransaction();
		factuurDao.deleteAll();
		factuurDao.closeCurrentSessionwithTransaction();
	}

	public FactuurDao factuurDao() {
		return factuurDao;
	}
}

