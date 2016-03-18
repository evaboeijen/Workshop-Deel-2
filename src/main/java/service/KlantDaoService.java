package service;

import java.util.List;
import business.*;
import dao.KlantDaoImpl;

 

public class KlantDaoService {
	
	private static KlantDaoImpl klantDaoImpl;

	public KlantDaoService() {
		klantDaoImpl = new KlantDaoImpl();
	}

	public void persist(Klant entity) {
		klantDaoImpl.openCurrentSessionwithTransaction();
		klantDaoImpl.persist(entity);
		klantDaoImpl.closeCurrentSessionwithTransaction();
	}

	public void update(Klant entity) {
		klantDaoImpl.openCurrentSessionwithTransaction();
		klantDaoImpl.update(entity);
		klantDaoImpl.closeCurrentSessionwithTransaction();
	}
	
	//public Klant findById(String id) {
	public Klant findById(Long id) {
		klantDaoImpl.openCurrentSession();
		Klant klant = klantDaoImpl.findById(id);
		klantDaoImpl.closeCurrentSession();
		return klant;
	}
	
	//public void delete(String id) {
	public void delete(Long id) {
		klantDaoImpl.openCurrentSessionwithTransaction();
		Klant klant = klantDaoImpl.findById(id);
		System.out.println(klant + "will be deleted.");
		klantDaoImpl.delete(klant);
		klantDaoImpl.closeCurrentSessionwithTransaction();
	}
	
	public List<Klant> findAll() {
		klantDaoImpl.openCurrentSession();
		List<Klant> klanten = klantDaoImpl.findAll();
		klantDaoImpl.closeCurrentSession();
		return klanten;
	}

	public void deleteAll() {
		klantDaoImpl.openCurrentSessionwithTransaction();
		klantDaoImpl.deleteAll();
		klantDaoImpl.closeCurrentSessionwithTransaction();
	}

	public KlantDaoImpl klantDaoImpl() {
		return klantDaoImpl;
	}
}

