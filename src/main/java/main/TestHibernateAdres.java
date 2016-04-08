package main;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Adres;
import business.Klant;
import business.Klant.Adrestype;
import business.AdresType;
import service.AdresDaoService;
import service.KlantDaoService;

public class TestHibernateAdres {
	private static final Logger logger =  LoggerFactory.getLogger(TestHibernateAdres.class);
	static AdresDaoService adresService = new AdresDaoService();
	static KlantDaoService klantService = new KlantDaoService();
	
	public static Adres CreateAdres(){
	Scanner input = new Scanner(System.in);
	
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
	
	public static void adrestypeKeuze(Klant klant){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Kies een adrestype ");
		System.out.println("1. Postadres");
		System.out.println("2. Factuuradres");
		System.out.println("3. Bezoekadres");
		
		int keuze = input.nextInt();
		
		switch (keuze) {
		case 1:
			klant.setAdrestype(klant.getAdrestype().Postadres);
			break;
		case 2:
			klant.setAdrestype(klant.getAdrestype().Factuuradres);
			break;
		case 3:
			klant.setAdrestype(klant.getAdrestype().Bezoekadres);
			break;			
		default:
			klant.setAdrestype(klant.getAdrestype().Postadres);
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
		System.out.println("\t8.  Ontkoppel adres van klant");
		System.out.println("\t9.  Vind alle adressen van een klant");
				
		System.out.println("\t11. Ken een adrestype toe aan een adres van een klant");
		System.out.println("\t12. Verwijder adrestype");		
		System.out.print("\nVoer optie in en druk op Enter:");
		
		try{
			int keuze = input.nextInt();
			long adres_id;
			long klant_id;
			Adres adres= null;
			Klant klant = null;
			Adrestype adrestype= null;
			
			
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
					bestaandAdres= new Adres();	
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
				System.out.println("Geef het klantnummer op: ");
				klant_id = input.nextLong();
				System.out.println("Geef het adresnummer op: ");
				adres_id = input.nextLong();			
				klant.addToAdresMap(adres,adrestype);
				klantService.update(klant);
				toonAdresMenu();
				break;
				
			case 8:// Ontkoppel adres van klant
				System.out.println("Geef het klantnummer op: ");
				klant_id = input.nextLong();
				klant = new Klant();
				klant.setId(klant_id);
				System.out.println("Geef het adresnummer op: ");
				adres_id = input.nextLong();
				adres = new Adres();
				adres.setId(adres_id);
				
				klant.removeFromAdresMap(adres,adrestype);
				klantService.update(klant);
				toonAdresMenu();
				break;
				
			case 9://Vind alle adressen van een klant	
				System.out.println("Geeft het klantnummer op: ");
				klant_id = input.nextLong();
				klant = new Klant();
				klant.setId(klant_id);
				System.out.println(klant.getAdresMap());
				toonAdresMenu();
				break;
				
			case 11://Ken een adrestype toe aan een adres van een klant
				System.out.println("Geef het klantnummer op: ");
				klant_id=input.nextLong();
				klant= new Klant();
				klant.setId(klant_id);
				
				System.out.println(klant = klantService.findById(klant_id));
				
				System.out.println("Geef het adresnummer op: ");
				adres_id=input.nextLong();
				adres = new Adres();
				adres.setId(adres_id);
				
				adrestypeKeuze(klant);

				klant.addToAdresMap(adres,adrestype);
				klantService.update(klant);
				toonAdresMenu();
				break;
				
			case 12://Verwijder adrestype
				System.out.println("Geef het adresnummer op: ");
				adres_id=input.nextLong();
				adres = new Adres();
				adres.setId(adres_id);				
				
				System.out.println("Geef het klantnummer op: ");
				klant_id = input.nextLong();
				klant = new Klant();
				klant.setId(klant_id);
				
				adrestypeKeuze(klant);
				klant.removeFromAdresMap(adres,adrestype);
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