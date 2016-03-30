package service;

import java.util.List;
import business.*;
import dao.BestellingDao;

 

public class BestellingDaoService {
	
	private static BestellingDao bestellingDao;

	public BestellingDaoService() {
		bestellingDao = new BestellingDao();
	}

	public void persist(Bestelling entity) {
		bestellingDao.openCurrentSessionwithTransaction();
		bestellingDao.persist(entity);
		bestellingDao.closeCurrentSessionwithTransaction();
	}

	public void update(Bestelling entity) {
		bestellingDao.openCurrentSessionwithTransaction();
		bestellingDao.update(entity);
		bestellingDao.closeCurrentSessionwithTransaction();
	}
	
	//public Bestelling findById(String id) {
	public Bestelling findById(Long id) {
		bestellingDao.openCurrentSession();
		Bestelling Bestelling = bestellingDao.findById(id);
		bestellingDao.closeCurrentSession();
		return Bestelling;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		bestellingDao.openCurrentSessionwithTransaction();
		Bestelling Bestelling = bestellingDao.findById(id);
		System.out.println(Bestelling + "will be deleted.");
		bestellingDao.delete(Bestelling);
		bestellingDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Bestelling> findAll() {
		bestellingDao.openCurrentSession();
		List<Bestelling> Bestellingen = bestellingDao.findAll();
		bestellingDao.closeCurrentSession();
		return Bestellingen;
	}

	public void deleteAll() {
		bestellingDao.openCurrentSessionwithTransaction();
		bestellingDao.deleteAll();
		bestellingDao.closeCurrentSessionwithTransaction();
	}

	public BestellingDao bestellingDao() {
		return bestellingDao;
	}
}

