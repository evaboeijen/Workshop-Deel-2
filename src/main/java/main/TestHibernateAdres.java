package main;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Adres;
import service.AdresDaoService;
import service.KlantDaoService;

public class TestHibernateAdres {
	private static final Logger logger =  LoggerFactory.getLogger(TestHibernateAdres.class);
	static AdresDaoService AdresService = new AdresDaoService();
	
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
	
	public static void toonAdresMenu(){
		Scanner input = new Scanner(System.in);		     
		
		System.out.println("\t-------------------");
		System.out.println("\t Test Adres Domain  ");
		System.out.println("\t-------------------");
		System.out.println("\n\t1. Persist Adres");
		System.out.println("\t2. Update Adres");
		System.out.println("\t3. FindById Adres");
		System.out.println("\t4. Delete Adres");
		System.out.println("\t5. FindAll Adresses");
		System.out.println("\t6. DeleteAll Adresses");
		System.out.print("\nVoer optie in en druk op Enter:");
		
		try{
			int keuze = input.nextInt();
			long id;
			
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
				id = input.nextInt();
				
				Adres bestaandAdres = CreateAdres();	
				//bestaandAdres.setId(id);
				
				logger.info("klant is: " + bestaandAdres);
				AdresService.update(bestaandAdres); 
				
				toonAdresMenu();				
				break;

			case 3:// FindById
				System.out.println("\n*** FindById Adres- Start ***");			
				//bestaanAdres = new Adres();				
				System.out.print("Voer het ID in van het adres dat je wil zoeken: ");
				id = input.nextLong();
				//bestaandAdres.setId(id2);
				System.out.println(AdresService.findById(id));				
				
				toonAdresMenu();				
				break;

			case 4:// Delete
				System.out.println("\n*** Delete Adres - Start ***");
				bestaandAdres= new Adres();	
				System.out.print("Voer het ID in van het adres die je wil deleten: ");				
				id = input.nextLong();				
				AdresService.delete(id);
				
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