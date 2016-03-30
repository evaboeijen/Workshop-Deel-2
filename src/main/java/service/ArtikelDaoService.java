package service;

import java.util.List;
import business.*;
import dao.*;

 

public class ArtikelDaoService {
	
	private static ArtikelDao artikelDao;

	public ArtikelDaoService() {
		artikelDao = new ArtikelDao();
	}

	public void persist(Artikel entity) {
		artikelDao.openCurrentSessionwithTransaction();
		artikelDao.persist(entity);
		artikelDao.closeCurrentSessionwithTransaction();
	}

	public void update(Artikel entity) {
		artikelDao.openCurrentSessionwithTransaction();
		artikelDao.update(entity);
		artikelDao.closeCurrentSessionwithTransaction();
	}
	
	public Artikel findById(Long id) {
		artikelDao.openCurrentSession();
		Artikel artikel = artikelDao.findById(id);
		artikelDao.closeCurrentSession();
		return artikel;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		artikelDao.openCurrentSessionwithTransaction();
		Artikel artikel = artikelDao.findById(id);
		System.out.println(artikel + "will be deleted.");
		artikelDao.delete(artikel);
		artikelDao.closeCurrentSessionwithTransaction();
	}
	
	public List<Artikel> findAll() {
		artikelDao.openCurrentSession();
		List<Artikel> artikellen = artikelDao.findAll();
		artikelDao.closeCurrentSession();
		return artikellen;
	}

	public void deleteAll() {
		artikelDao.openCurrentSessionwithTransaction();
		artikelDao.deleteAll();
		artikelDao.closeCurrentSessionwithTransaction();
	}

	public ArtikelDao artikelDao() {
		return artikelDao;
	}
}


