package service;

import java.util.List;
import business.*;
import dao.KlantDao;

 

public class KlantDaoService {
	
	private static KlantDao klantDao;

	public KlantDaoService() {
		klantDao = new KlantDao();
	}

	public void persist(Klant entity) {
		klantDao.openCurrentSessionwithTransaction();
		klantDao.persist(entity);
		klantDao.closeCurrentSessionwithTransaction();
	}

	public void update(Klant entity) {
		klantDao.openCurrentSessionwithTransaction();
		klantDao.update(entity);
		klantDao.closeCurrentSessionwithTransaction();
	}
	
	//public Klant findById(String id) {
	public Klant findById(Long id) {
		klantDao.openCurrentSession();
		Klant klant = klantDao.findById(id);
		klantDao.closeCurrentSession();
		return klant;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		klantDao.openCurrentSessionwithTransaction();
		Klant klant = klantDao.findById(id);
		System.out.println(klant + "will be deleted.");
		klantDao.delete(klant);
		klantDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Klant> findAll() {
		klantDao.openCurrentSession();
		List<Klant> klanten = klantDao.findAll();
		klantDao.closeCurrentSession();
		return klanten;
	}

	public void deleteAll() {
		klantDao.openCurrentSessionwithTransaction();
		klantDao.deleteAll();
		klantDao.closeCurrentSessionwithTransaction();
	}

	public KlantDao klantDao() {
		return klantDao;
	}
}

