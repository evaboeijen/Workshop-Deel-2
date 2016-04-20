package service;

import java.util.List;

import business.KlantAdres;
import dao.KlantAdresDao;

public class KlantAdresDaoService {			
			private static KlantAdresDao klantAdresDaoImpl;

			public KlantAdresDaoService() {
				klantAdresDaoImpl = new KlantAdresDao();
			}

			public void persist(KlantAdres entity) {
				klantAdresDaoImpl.openCurrentSessionwithTransaction();
				klantAdresDaoImpl.persist(entity);
				klantAdresDaoImpl.closeCurrentSessionwithTransaction();
			}

			public void update(KlantAdres entity) {
				klantAdresDaoImpl.openCurrentSessionwithTransaction();
				klantAdresDaoImpl.update(entity);
				klantAdresDaoImpl.closeCurrentSessionwithTransaction();
			}
			
			public KlantAdres findById(Long id) {
				klantAdresDaoImpl.openCurrentSession();
				KlantAdres klantAdres = klantAdresDaoImpl.findById(id);
				klantAdresDaoImpl.closeCurrentSession();
				return klantAdres;
			}
			
			public void delete(Long id) {
				klantAdresDaoImpl.openCurrentSessionwithTransaction();
				KlantAdres klantAdres = klantAdresDaoImpl.findById(id);
				System.out.println(klantAdres + "will be deleted.");
				klantAdresDaoImpl.delete(klantAdres);
				klantAdresDaoImpl.closeCurrentSessionwithTransaction();
			}
			
			public List<KlantAdres> findAll() {
				klantAdresDaoImpl.openCurrentSession();
				List<KlantAdres> klanten = klantAdresDaoImpl.findAll();
				klantAdresDaoImpl.closeCurrentSession();
				return klanten;
			}

			public void deleteAll() {
				klantAdresDaoImpl.openCurrentSessionwithTransaction();
				klantAdresDaoImpl.deleteAll();
				klantAdresDaoImpl.closeCurrentSessionwithTransaction();
			}
			
			public KlantAdresDao adresDaoImpl() {
				return klantAdresDaoImpl;
			}
}