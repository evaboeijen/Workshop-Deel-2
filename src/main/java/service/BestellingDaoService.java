package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.*;
import dao.BestellingDao;
import main.TestHibernateBestelling;

 

public class BestellingDaoService {
	
	private static final Logger logger =  LoggerFactory.getLogger(BestellingDaoService.class);
	
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
		Bestelling bestelling = bestellingDao.findById(id);
		System.out.println(bestelling + "will be deleted.");
		bestellingDao.delete(bestelling);
		bestellingDao.closeCurrentSessionwithTransaction();
	}
	
	public void delete(Bestelling bestelling) {
		bestellingDao.openCurrentSessionwithTransaction();
		
		Long id = bestelling.getId();
		bestellingDao.findById(id);
		System.out.println(bestelling + "will be deleted.");
		bestellingDao.delete(bestelling);
		bestellingDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Bestelling> findAll() {
		bestellingDao.openCurrentSession();
		List<Bestelling> bestellingen = bestellingDao.findAll();
		bestellingDao.closeCurrentSession();
		return bestellingen;
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

