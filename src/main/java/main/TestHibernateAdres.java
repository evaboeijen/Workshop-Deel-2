package main;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Adres;
import business.Klant;
import business.AdresType;
import service.AdresDaoService;
import service.KlantDaoService;

public class TestHibernateAdres {
	private static final Logger logger =  LoggerFactory.getLogger(TestHibernateAdres.class);
	static AdresDaoService AdresService = new AdresDaoService();
	static KlantDaoService KlantService = new KlantDaoService();
	
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
	
	public static long AdresTypeKeuze(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Kies een adrestype ");
		System.out.println("1. Postadres");
		System.out.println("2. Factuuradres");
		System.out.println("3. Bezoekadres");
		
		long adresType = input.nextLong();
		return adresType;
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
		System.out.println("\t10. Vind alle klanten op een adres\n");		
		System.out.println("\t11. Ken een adrestype toe");
		System.out.println("\t12. Verwijder adrestype");		
		System.out.print("\nVoer optie in en druk op Enter:");
		
		try{
			int keuze = input.nextInt();
			long adres_id;
			long klant_id;
			long adresTypeKeuze;
			AdresType adresType= null;
			
			
			switch (keuze) {
			case 1:// Persist
				System.out.println("\n*** Persist Adres - Start ***");
					Adres nieuwAdres = CreateAdres();
				System.out.println("Toe te voegen nieuw adres: " + nieuwAdres);
				AdresService.persist(nieuwAdres);
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
						AdresService.update(bestaandAdres); 
				
				toonAdresMenu();				
				break;

			case 3:// FindById
				System.out.println("\n*** FindById Adres- Start ***");						
				System.out.print("Voer het ID in van het adres dat je wil zoeken: ");
					adres_id = input.nextLong();
				System.out.println(AdresService.findById(adres_id));				
				
				toonAdresMenu();				
				break;

			case 4:// Delete
				System.out.println("\n*** Delete Adres - Start ***");
					bestaandAdres= new Adres();	
				System.out.print("Voer het ID in van het adres die je wil deleten: ");				
					adres_id = input.nextLong();				
					AdresService.delete(adres_id);
				
				toonAdresMenu();
				break;

			case 5:// FindAll
				System.out.println("\n*** FindAll Adresses - Start ***");
				logger.info("findAll adressen aangeroepen");
				List<Adres> adressen = AdresService.findAll();

				System.out.println("De volgende adressen staan in de Adres tabel :");
					for (Adres k : adressen) {
						System.out.println("-" + k.toString());
					}				
				toonAdresMenu();
				break;
				
			case 6:// DeleteAll
				System.out.println("\n*** DeleteAll Adresses - Start ***");
				AdresService.deleteAll();				
				
				toonAdresMenu();
				break;
				
			case 7://Koppel adres aan klant
				System.out.println("Geef het klantnummer op: ");
				klant_id = input.nextLong();
				System.out.println("Geef het adresnummer op: ");
				adres_id = input.nextLong();
				
				toonAdresMenu();
				break;
				
			case 8:// Ontkoppel adres van klant
				//To Do
				toonAdresMenu();
				break;
				
			case 9://Vind alle adressen van een klant	
				//To Do
				toonAdresMenu();
				break;
				
			case 10://Vind alle kalnten op een adres
				//To Do
				toonAdresMenu();
				break;
				
			case 11://Ken een adrestype toe
				System.out.println("Geef het adresnummer op: ");
				adres_id=input.nextLong();
				adresType= new AdresType();
				adresTypeKeuze = AdresTypeKeuze();
				adresType.setId(adresTypeKeuze);
				Adres adres = AdresService.findById(adres_id);
				
				KlantDaoService.addToAdresMap(adres,adresType);
				toonAdresMenu();
				break;
				
			case 12://Verwijder adrestype
				System.out.println("Geef het adresnummer op: ");
				adres_id=input.nextLong();
				
				
				
				adresType= new AdresType();
				adresTypeKeuze = AdresTypeKeuze();
				adresType.setId(adresTypeKeuze);
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