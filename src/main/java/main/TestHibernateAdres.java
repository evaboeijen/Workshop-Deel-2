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
	
	public static void setAdresTypeKeuzeMenu(KlantAdres klantAdres){
		
		AdresTypeType adresTypeType = null;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Geef het adrestype op: ");
		System.out.println("1. Postadres");
		System.out.println("2. Factuuradres");
		System.out.println("3. Bezoekadres");
		
		int keuze = input.nextInt();

		switch (keuze) {
		case 1:
			klantAdres.setAdresType(klantAdres.getAdresType().Postadres);
			break;
		case 2:
			klantAdres.setAdresType(klantAdres.getAdresType().Factuuradres);
			break;
		case 3:
			klantAdres.setAdresType(klantAdres.getAdresType().Bezoekadres);
			break;			
		default:
			klantAdres.setAdresType(klantAdres.getAdresType().Postadres);
		}
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
		System.out.println("\t8.  Pas klantAdres aan");
		System.out.println("\t9.  Ontkoppel adres van klant");		
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

			case 4:// Delete						FOUTMELDING: constraint violation foreign key constraint fail
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
				
			case 6:// DeleteAll						FOUTMELDING: constraint violation foreign key constraint fail
				System.out.println("\n*** DeleteAll Adresses - Start ***");
				adresService.deleteAll();				
				toonAdresMenu();
				break;
				
			case 7://Koppel adres aan klant
				klantAdres = new KlantAdres();			
				System.out.println("Geef het klant ID op: ");
				klant_id = input.nextInt();
				klant = klantService.findById(klant_id);
				System.out.println("Geef het adres ID op: ");
				adres_id = input.nextLong();
				setAdresTypeKeuzeMenu(klantAdres);				
				bestaandAdres = adresService.findById(adres_id);
				klantAdres.setAdres(bestaandAdres);
				klantAdres.setKlant(klant);
				//logger.info("klantAdres bevat nu: " + klantAdres);
				//logger.info("klant bevat nu: " + klant);		
				klantAdresService.persist(klantAdres);				
				toonAdresMenu();
				break;
				
			case 8://Pas klantAdres aan				FOUTMELDING: constraint violation klant_id may not be null				
				System.out.println(klantAdresService.findAll());
				System.out.println("Geef het klant - adres nummer op");
				klantAdres_id = input.nextLong();
				klantAdres = new KlantAdres();
				klantAdres.setId(klantAdres_id);
				System.out.println("Geef klantnummer op: ");
				klant_id = input.nextLong();
				klant = new Klant();
				klant = klantService.findById(klant_id);
				klantAdres.setKlant(klant);
				System.out.println("Geef adresnummer op: ");
				adres_id = input.nextLong();
				adres = adresService.findById(adres_id);
				klantAdres.setAdres(adres);
				setAdresTypeKeuzeMenu(klantAdres);
				klantAdresService.update(klantAdres);
				toonAdresMenu();
				break;
				
			case 9:// Ontkoppel adres van klant				
				System.out.println(klantAdresService.findAll());
				System.out.println("Geef het klant - adres nummer op");
				klantAdres_id = input.nextLong();
				klantAdresService.delete(klantAdres_id);
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