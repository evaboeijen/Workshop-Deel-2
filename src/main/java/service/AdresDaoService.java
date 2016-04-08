package service;

import business.Adres;
import dao.AdresDao;
import java.util.List;

public class AdresDaoService {
		
		private static AdresDao adresDaoImpl;

		public AdresDaoService() {
			adresDaoImpl = new AdresDao();
		}

		public void persist(Adres entity) {
			adresDaoImpl.openCurrentSessionwithTransaction();
			adresDaoImpl.persist(entity);
			adresDaoImpl.closeCurrentSessionwithTransaction();
		}

		public void update(Adres entity) {
			adresDaoImpl.openCurrentSessionwithTransaction();
			adresDaoImpl.update(entity);
			adresDaoImpl.closeCurrentSessionwithTransaction();
		}
		
		public Adres findById(Long id) {
			adresDaoImpl.openCurrentSession();
			Adres adres = adresDaoImpl.findById(id);
			adresDaoImpl.closeCurrentSession();
			return adres;
		}
		
		public void delete(Long id) {
			adresDaoImpl.openCurrentSessionwithTransaction();
			Adres adres = adresDaoImpl.findById(id);
			System.out.println(adres + "will be deleted.");
			adresDaoImpl.delete(adres);
			adresDaoImpl.closeCurrentSessionwithTransaction();
		}
		
		public List<Adres> findAll() {
			adresDaoImpl.openCurrentSession();
			List<Adres> klanten = adresDaoImpl.findAll();
			adresDaoImpl.closeCurrentSession();
			return klanten;
		}

		public void deleteAll() {
			adresDaoImpl.openCurrentSessionwithTransaction();
			adresDaoImpl.deleteAll();
			adresDaoImpl.closeCurrentSessionwithTransaction();
		}
		
		public AdresDao adresDaoImpl() {
			return adresDaoImpl;
		}
}



