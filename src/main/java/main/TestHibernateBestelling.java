package main;


import business.*;

import service.*;

import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestHibernateBestelling { 

	private static final Logger logger =  LoggerFactory.getLogger(TestHibernateBestelling.class);

	public static void main(String[] args){

		BestellingDaoService bestellingService = new BestellingDaoService();
		BestelArtikelDaoService bestellingHasArtikelService = new BestelArtikelDaoService();
		KlantDaoService klantService = new KlantDaoService();
		ArtikelDaoService artikelService = new ArtikelDaoService();

		System.out.println("\t-------------------------");
		System.out.println("\t Test Bestelling Domain  ");
		System.out.println("\t-------------------------");
		System.out.println("1. persist");
		System.out.println("2. update");
		System.out.println("3. findById");
		System.out.println("4. delete");
		System.out.println("5. findAll");
		System.out.println("6. deleteAll");
		System.out.print("Voer optie in en druk op Enter:");


		try (Scanner input = new Scanner(System.in);) {		     

			int keuze = input.nextInt();

			switch (keuze) {
			case 1:
				System.out.println("*** Persist - start ***");
				Bestelling nieuweBestelling = new Bestelling();
				BestelArtikel nieuweBestellingHasArtikel = new BestelArtikel();
				input.nextLine();
				System.out.print("Voer ID van klant in waarvoor je een bestelling wil plaatsen: ");
				long klant_id = input.nextInt();		

				nieuweBestelling.setBestelNummer();				
				nieuweBestelling.setBestelDatum();
				nieuweBestelling.setKlant(klantService.findById(klant_id));
				
				System.out.print("Voer ID van artikel in die je wil bestellen: ");
				long artikel_id = input.nextInt();	
												
				nieuweBestellingHasArtikel.setArtikel(artikelService.findById(artikel_id));
				
				System.out.print("Hoeveel wil je er van bestellen?: ");
				int aantal = input.nextInt();	
				
				nieuweBestellingHasArtikel.setAantal(aantal);
				
				nieuweBestellingHasArtikel.setBestelling(nieuweBestelling);
				
				nieuweBestellingHasArtikel.getId();
						
				nieuweBestelling.bestellingHasArtikelen.add(nieuweBestellingHasArtikel);
				

				System.out.println("Toe te voegen nieuwe Bestelling: " + nieuweBestelling);
				//logger.info("Bestelling is: " + nieuweBestelling);
				bestellingService.persist(nieuweBestelling);
				bestellingHasArtikelService.persist(nieuweBestellingHasArtikel);
				System.out.println("Bestelling toegevoegd: " + nieuweBestelling);
				
				
				break;

			case 2:
				
				System.out.println("*** Update - start ***");
				Bestelling bestaandeBestelling = new Bestelling();	
				Scanner input2 = new Scanner(System.in);
				System.out.print("Voer het ID in van de Bestelling die je wil aanpassen: ");				
				int id = input2.nextInt();
				input2.nextLine();


				logger.info("Bestelling is: " + bestaandeBestelling);
				//service.update(bestaandeBestelling); 
				
				
				break;

			case 3:
				System.out.println("*** findById - start ***");			
				//bestaandeBestelling = new Bestelling();				
				Scanner input3 = new Scanner(System.in);
				System.out.print("Voer het ID in van de Bestelling die je wil zoeken: ");
				Long id2 = input3.nextLong();
				//bestaandeBestelling.setId(id2);
				//System.out.println(service.findById(id2));				
				break;

			case 4:
				System.out.println("*** Delete - start ***");
				bestaandeBestelling = new Bestelling();	
				System.out.print("Voer het ID in van de Bestelling die je wil deleten: ");				
				id2 = input.nextLong();				
				//service.delete(id2);
				break;

			case 5:
				logger.info("findAll Bestellingen aangeroepen");
				//List<Bestelling> Bestellingen = service.findAll();

				System.out.println("De volgende Bestellingen staan in de Bestelling tabel :");

				/* for (Bestelling k : Bestellingen) {
					System.out.println("-" + k.toString());
				} */

				break;
				
			case 6:
				System.out.println("*** DeleteAll - start ***");
				// service.deleteAll();
				break;

			default:
				System.out.println("\n! Ongeldige optie!\n");

			} 

		}

		finally {
			// zinnige code			
		}	



	}

}
