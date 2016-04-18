package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CheckExistenceInDatabase {
	private static final Logger logger =  LoggerFactory.getLogger(CheckExistenceInDatabase.class);

	public boolean checkKlant_id(long klant_id) {

		boolean result = false;
		KlantDaoService klantService = new KlantDaoService();

		logger.info("Check klant ID methode begint");
		logger.info("Klant ID is : "+ klant_id);

		if (klantService.findById(klant_id) != null) {
			result = true;
		} 
		else {
			System.out.println();
			System.out.println("Het opgegeven klant ID bevindt zich niet in de database..."); 
		}

		return result;
	}


	public boolean checkBestelling_id(long bestelling_id) {

		boolean result = false;
		BestellingDaoService bestelService = new BestellingDaoService();

		logger.info("Check bestelling ID methode begint");
		logger.info("Bestelling ID is : "+ bestelling_id);				

		if (bestelService.findById(bestelling_id) != null) {
			result = true;
		} 
		else {
			System.out.println();
			System.out.println("Het opgegeven bestelling ID bevindt zich niet in de database..."); 
		}

		return result;
	}


	public boolean checkAdres_id(long adres_id) {

		boolean result = false;
		AdresDaoService adresService = new AdresDaoService();

		logger.info("Check adres ID methode begint");
		logger.info("Adres ID is : "+ adres_id);				

		if (adresService.findById(adres_id) != null) {
			result = true;
		} 
		else {
			System.out.println();
			System.out.println("Het opgegeven adres ID bevindt zich niet in de database..."); 
		}

		return result;
	}


	public boolean checkAccount_id(long account_id) {

		boolean result = false;
		AccountDaoService accountService = new AccountDaoService();

		logger.info("Check account ID methode begint");
		logger.info("Account ID is : "+ account_id);				

		if (accountService.findById(account_id) != null) {
			result = true;
		} 
		else {
			System.out.println();
			System.out.println("Het opgegeven account ID bevindt zich niet in de database..."); 
		}

		return result;
	}


	public boolean checkArtikel_id(long artikel_id) {

		boolean result = false;
		ArtikelDaoService artikelService = new ArtikelDaoService();

		logger.info("Check artikel ID methode begint");
		logger.info("Artikel ID is : "+ artikel_id);				

		if (artikelService.findById(artikel_id) != null) {
			result = true;
		} 
		else {
			System.out.println();
			System.out.println("Het opgegeven artikel ID bevindt zich niet in de database..."); 
		}

		return result;
	}




}
