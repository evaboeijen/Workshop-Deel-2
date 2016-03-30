package service;

import java.util.List;
import business.*;
import dao.BestellingDao;

 

public class BestellingDaoService {
	
	private static BestellingDao BestellingDao;

	public BestellingDaoService() {
		BestellingDao = new BestellingDao();
	}

	public void persist(Bestelling entity) {
		BestellingDao.openCurrentSessionwithTransaction();
		BestellingDao.persist(entity);
		BestellingDao.closeCurrentSessionwithTransaction();
	}

	public void update(Bestelling entity) {
		BestellingDao.openCurrentSessionwithTransaction();
		BestellingDao.update(entity);
		BestellingDao.closeCurrentSessionwithTransaction();
	}
	
	//public Bestelling findById(String id) {
	public Bestelling findById(Long id) {
		BestellingDao.openCurrentSession();
		Bestelling Bestelling = BestellingDao.findById(id);
		BestellingDao.closeCurrentSession();
		return Bestelling;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		BestellingDao.openCurrentSessionwithTransaction();
		Bestelling Bestelling = BestellingDao.findById(id);
		System.out.println(Bestelling + "will be deleted.");
		BestellingDao.delete(Bestelling);
		BestellingDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Bestelling> findAll() {
		BestellingDao.openCurrentSession();
		List<Bestelling> Bestellingen = BestellingDao.findAll();
		BestellingDao.closeCurrentSession();
		return Bestellingen;
	}

	public void deleteAll() {
		BestellingDao.openCurrentSessionwithTransaction();
		BestellingDao.deleteAll();
		BestellingDao.closeCurrentSessionwithTransaction();
	}

	public BestellingDao BestellingDao() {
		return BestellingDao;
	}
}

