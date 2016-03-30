package service;

import java.util.List;
import business.*;
import dao.BestelArtikelDao;

 

public class BestelArtikelDaoService {
	
	private static BestelArtikelDao BestelArtikelDao;

	public BestelArtikelDaoService() {
		BestelArtikelDao = new BestelArtikelDao();
	}

	public void persist(BestelArtikel entity) {
		BestelArtikelDao.openCurrentSessionwithTransaction();
		BestelArtikelDao.persist(entity);
		BestelArtikelDao.closeCurrentSessionwithTransaction();
	}

	public void update(BestelArtikel entity) {
		BestelArtikelDao.openCurrentSessionwithTransaction();
		BestelArtikelDao.update(entity);
		BestelArtikelDao.closeCurrentSessionwithTransaction();
	}
	
	//public BestelArtikel findById(String id) {
	public BestelArtikel findById(Long id) {
		BestelArtikelDao.openCurrentSession();
		BestelArtikel BestelArtikel = BestelArtikelDao.findById(id);
		BestelArtikelDao.closeCurrentSession();
		return BestelArtikel;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		BestelArtikelDao.openCurrentSessionwithTransaction();
		BestelArtikel BestelArtikel = BestelArtikelDao.findById(id);
		System.out.println(BestelArtikel + "will be deleted.");
		BestelArtikelDao.delete(BestelArtikel);
		BestelArtikelDao.closeCurrentSessionwithTransaction();
	}
	
	public List<BestelArtikel> findAll() {
		BestelArtikelDao.openCurrentSession();
		List<BestelArtikel> Bestellingen = BestelArtikelDao.findAll();
		BestelArtikelDao.closeCurrentSession();
		return Bestellingen;
	}

	public void deleteAll() {
		BestelArtikelDao.openCurrentSessionwithTransaction();
		BestelArtikelDao.deleteAll();
		BestelArtikelDao.closeCurrentSessionwithTransaction();
	}

	public BestelArtikelDao BestelArtikelDao() {
		return BestelArtikelDao;
	}
}

