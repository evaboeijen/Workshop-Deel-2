package service;

import java.util.List;
import business.*;
import dao.BestelArtikelDao;

 

public class BestelArtikelDaoService {
	
	private static BestelArtikelDao bestelArtikelDao;

	public BestelArtikelDaoService() {
		bestelArtikelDao = new BestelArtikelDao();
	}

	public void persist(BestelArtikel entity) {
		bestelArtikelDao.openCurrentSessionwithTransaction();
		bestelArtikelDao.persist(entity);
		bestelArtikelDao.closeCurrentSessionwithTransaction();
	}

	public void update(BestelArtikel entity) {
		bestelArtikelDao.openCurrentSessionwithTransaction();
		bestelArtikelDao.update(entity);
		bestelArtikelDao.closeCurrentSessionwithTransaction();
	}
	
	//public BestelArtikel findById(String id) {
	public BestelArtikel findById(Long id) {
		bestelArtikelDao.openCurrentSession();
		BestelArtikel BestelArtikel = bestelArtikelDao.findById(id);
		bestelArtikelDao.closeCurrentSession();
		return BestelArtikel;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		bestelArtikelDao.openCurrentSessionwithTransaction();
		BestelArtikel BestelArtikel = bestelArtikelDao.findById(id);
		System.out.println(BestelArtikel + "will be deleted.");
		bestelArtikelDao.delete(BestelArtikel);
		bestelArtikelDao.closeCurrentSessionwithTransaction();
	}
	
	public List<BestelArtikel> findAll() {
		bestelArtikelDao.openCurrentSession();
		List<BestelArtikel> bestellingen = bestelArtikelDao.findAll();
		bestelArtikelDao.closeCurrentSession();
		return bestellingen;
	}

	public void deleteAll() {
		bestelArtikelDao.openCurrentSessionwithTransaction();
		bestelArtikelDao.deleteAll();
		bestelArtikelDao.closeCurrentSessionwithTransaction();
	}

	public BestelArtikelDao bestelArtikelDao() {
		return bestelArtikelDao;
	}
}

