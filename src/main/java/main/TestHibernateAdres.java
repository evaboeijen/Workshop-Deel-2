package main;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.*;
import service.AdresDaoService;
import service.KlantAdresDaoService;
import service.KlantDaoService;

public class TestHibernateAdres {
	private static final Logger logger =  LoggerFactory.getLogger(TestHibernateAdres.class);
	static AdresDaoService adresService = new AdresDaoService();
	static KlantDaoService klantService = new KlantDaoService();
	static KlantAdresDaoService klantAdresService = new KlantAdresDaoService();
	private static Scanner input;
	
	public static Adres CreateAdres(){
	input = new Scanner(System.in);
	
	Adres adres = new Adres();
	System.out.print("Voer straatnaam in: ");
	String straatnaam = input.nextLine();
	System.out.print("Voer huisnummer in: ");
	int huisnummer = input.nextInt();
	input.nextLine();
	System.out.print("Voer toevoeging in: ");
	String toevoeging = input.nextLine();
	System.out.print("Voer postcode in: ");
	String postcode = input.nextLine();
	System.out.print("Voer woonplaats in: ");
	String woonplaats = input.nextLine();
	System.out.println("");

	adres.setStraatnaam(straatnaam);						
	adres.setHuisnummer(huisnummer);
	adres.setToevoeging(toevoeging);
	adres.setPostcode(postcode);
	adres.setWoonplaats(woonplaats);
	
	return adres;
	}
	
	public static void toonAdresMenu(){
		Scanner input = new Scanner(System.in);		     
		
		System.out.println("\t-------------------");
		System.out.println("\t Test Adres Domain  ");
		System.out.println("\t-------------------\n");
		System.out.println("\t1.  Persist adres");
		System.out.println("\t2.  Update adres");
		System.out.println("\t3.  FindById adres");
		System.out.println("\t4.  Delete adres");
		System.out.println("\t5.  FindAll adresses");
		System.out.println("\t6.  DeleteAll adresses\n");		
		System.out.println("\t7.  Koppel adres aan klant");
		System.out.println("\t8.  Ontkoppel adres van klant");
		System.out.println("\t9.  Vind alle adressen van een klant");
				
		System.out.println("\t11. Ken een adrestype toe aan een adres van een klant");
		System.out.println("\t12. Verwijder adrestype");		
		System.out.print("\nVoer optie in en druk op Enter:");
		
		try{
			int keuze = input.nextInt();
			long adres_id;
			long klant_id;
			long klantAdres_id;
			Adres adres = null;
			Klant klant = null;
			KlantAdres klantAdres =  null;
			AdresType adresType = null;
			
			
			switch (keuze) {
			case 1:// Persist
				System.out.println("\n*** Persist Adres - Start ***");
					Adres nieuwAdres = CreateAdres();
				System.out.println("Toe te voegen nieuw adres: " + nieuwAdres);
				adresService.persist(nieuwAdres);
				System.out.println("Adres toegevoegd: " + nieuwAdres);
				toonAdresMenu();				
				break;

			case 2:// Update	
				System.out.println("\n*** Update Adres - Start ***");
				System.out.println("Voer het ID in van het adres die je wil aanpassen: ");				
				adres_id = input.nextInt();
					Adres bestaandAdres = CreateAdres();	
					bestaandAdres.setId(adres_id);	
						logger.info("Adres is: " + bestaandAdres);
						adresService.update(bestaandAdres); 				
				toonAdresMenu();				
				break;

			case 3:// FindById
				System.out.println("\n*** FindById Adres- Start ***");						
				System.out.print("Voer het ID in van het adres dat je wil zoeken: ");
					adres_id = input.nextLong();
				System.out.println(adresService.findById(adres_id));					
				toonAdresMenu();				
				break;

			case 4:// Delete
				System.out.println("\n*** Delete Adres - Start ***");
					bestaandAdres = new Adres();	
				System.out.print("Voer het ID in van het adres die je wil deleten: ");				
					adres_id = input.nextLong();				
					adresService.delete(adres_id);				
				toonAdresMenu();
				break;

			case 5:// FindAll
				System.out.println("\n*** FindAll Adresses - Start ***");
				logger.info("findAll adressen aangeroepen");
				List<Adres> adressen = adresService.findAll();
				System.out.println("De volgende adressen staan in de Adres tabel :");
					for (Adres k : adressen) {
						System.out.println("-" + k.toString());
					}				
				toonAdresMenu();
				break;
				
			case 6:// DeleteAll
				System.out.println("\n*** DeleteAll Adresses - Start ***");
				adresService.deleteAll();				
				toonAdresMenu();
				break;
				
			case 7://Koppel adres aan klant
				klantAdres = new KlantAdres();
				System.out.println("Geef het klantnummer op: ");
				klant_id = input.nextInt();
				klant = new Klant();
				klant.setId(klant_id); 
				System.out.println("Geef het adresnummer op: ");
				adres_id = input.nextLong();
				klantAdres.setAdresTypeKeuzeMenu();
				klantAdresService.update(klantAdres);
				toonAdresMenu();
				break;
				
			case 8:// Ontkoppel adres van klant				
				System.out.println(klantAdresService.findAll());
				System.out.println("Geef het klant - adres nummer op");
				klantAdres_id = input.nextLong();
				klantAdresService.delete(klantAdres_id);
				toonAdresMenu();
				break;
				
			case 9://Vind alle adressen van een klant	
				System.out.println("Geeft het klantnummer op: ");
				klant_id = input.nextLong();
				System.out.println(klantAdresService.findByKlant_id(klant_id));
				toonAdresMenu();
				break;
				
			case 11://Ken een adrestype toe aan een adres van een klant
				System.out.println("Geef het klantnummer op: ");
				klant_id = input.nextLong();				
				System.out.println(klantAdresService.findByKlant_id(klant_id));
				System.out.println("Geef het klantAdres_id op");
				klantAdres_id = input.nextLong();
				klantAdres =  new KlantAdres();
				klantAdres.setAdresTypeKeuzeMenu();
				klantAdresService.update(klantAdres);
				toonAdresMenu();
				break;
				
			case 12://Verwijder adrestype
				System.out.println("Geef het klantAdres_id op: ");
				klantAdres_id = input.nextLong();			
				klantAdres = new KlantAdres();
				klantAdres.setId(klantAdres_id);
				System.out.println("Geef het klantnummer op: ");
				klant_id = input.nextLong();
				klant = new Klant();
				klant.setId(klant_id);
				
				klantAdres.setAdresTypeKeuzeMenu();
				//klant.removeFromAdresMap(adres,adresType);
				klantService.update(klant);
				toonAdresMenu();
				break;
				
			default:
				System.out.println("\n! Ongeldige optie!\n");
				
			} 			
		}			
		finally {	
		}
	}
	
	public static void main(String[] args){		
		toonAdresMenu();
	}

}