package main;


import business.*;

import service.*;

import java.util.Date;
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
		FactuurDaoService factuurService = new FactuurDaoService();
		BetalingDaoService betalingService = new BetalingDaoService();
		CheckExistenceInDatabase checkExistenceInDatabase = new CheckExistenceInDatabase();


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

				logger.info("*** Persist - start ***");

				Bestelling nieuweBestelling = new Bestelling();
				BestelArtikel nieuweBestellingHasArtikel = new BestelArtikel();
				Factuur nieuweFactuur = new Factuur();
				Betaling nieuweBetaling = new Betaling();

				input.nextLine();
				System.out.print("Voer ID van klant in waarvoor je een bestelling wil plaatsen: ");
				long klant_id = input.nextInt();	
				
				while (checkExistenceInDatabase.checkKlant_id(klant_id)!= true) { 
            		System.out.print("\nVoer een ander klant ID in: ");
            		klant_id = input.nextLong();
            		System.out.println();
            	}    

				nieuweBestelling.setBestelNummer();				
				nieuweBestelling.setBestelDatum();
				nieuweBestelling.setKlant(klantService.findById(klant_id));

				logger.info("Klant :" + nieuweBestelling.getKlant());
				logger.info("bestelling_id: " + nieuweBestelling.getId());

				bestellingService.persist(nieuweBestelling);

				long artikel_id = 0;
				
				
				System.out.print("Voer ID van artikel in die je wil bestellen (0 = stoppen met artikelen toevoegen): ");
				artikel_id = input.nextLong();
				
				do {
									
					nieuweBestellingHasArtikel.setArtikel(artikelService.findById(artikel_id));

					System.out.print("Hoeveel wil je er van bestellen?: ");
					int aantal = input.nextInt();	

					nieuweBestellingHasArtikel.setAantal(aantal);
					nieuweBestellingHasArtikel.setBestelling(nieuweBestelling);
					nieuweBestellingHasArtikel.getId();

					logger.info("bestelartikel_id: " + nieuweBestellingHasArtikel.getId());
					logger.info("id van nieuweBestellingHasArtikel is" + nieuweBestellingHasArtikel.getId());

					nieuweBestelling.bestellingHasArtikelen.add(nieuweBestellingHasArtikel);
					bestellingHasArtikelService.persist(nieuweBestellingHasArtikel);

					logger.info("bestelartikel_id wordt toegevoegd aan het bestelling object");

					nieuweBestelling.setBestellingHasArtikelen(nieuweBestelling.bestellingHasArtikelen);

					logger.info("object nieuweBestelling bevat nu: " + nieuweBestelling);
					logger.info("bestelling object wordt geupdate met bestelartikel_id en geupdate in bestelling tabel");

					bestellingService.update(nieuweBestelling);

					logger.info("de set bestellingHasArtikelen bevat: " + nieuweBestelling.getBestellingHasArtikelen());
				
					System.out.print("Voer ID van artikel in die je wil bestellen (0 = stoppen met artikelen toevoegen): ");
					artikel_id = input.nextLong();
					
				} while (artikel_id  != 0 )  ;
				
				

				nieuweFactuur.setFactuurDatum();
				nieuweFactuur.setFactuurNummer();

				logger.info("object nieuweFactuur " + nieuweFactuur);

				nieuweFactuur.setBestelling(nieuweBestelling);

				logger.info("object nieuweFactuur na  \"nieuweFactuur.setBestelling(nieuweBestelling) \" " + nieuweFactuur);

				nieuweBestellingHasArtikel.setArtikel(artikelService.findById(artikel_id));
				nieuweBetaling.setBetaalDatum();


				System.out.print("Hoe wil je betalen? 1 = Contant, 2 = Pinbetaling, 3 = IDeal, 4 = Creditcard : ");
				int betaalwijze = input.nextInt();

				switch (betaalwijze) {
				case 1:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Contant);
					break;
				case 2:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Pinbetaling);
					break;
				case 3:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().IDeal);
					break;
				case 4:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Creditcard);
					break;				
				default:
					nieuweBetaling.setBetaalwijze(nieuweBetaling.getBetaalwijze().Pinbetaling);
				} 



				System.out.print("Voer overige betalingsgegevens in c.q. een beschrijving: ");
				input.nextLine();
				String betalingsGegevens = input.nextLine();

				nieuweBetaling.setBetalingsGegevens(betalingsGegevens);
				nieuweBetaling.setKlant(klantService.findById(klant_id));			
				nieuweBetaling.setFactuur(nieuweFactuur);

				betalingService.persist(nieuweBetaling);

				logger.info("object nieuweBetaling bevat:" + nieuweBetaling);									
				logger.info("object nieuweFactuur VOOR  \"nieuweFactuur.betalingSet.add(nieuweBetaling) \" " + nieuweFactuur);

				nieuweFactuur.betalingSet.add(nieuweBetaling);

				logger.info("object nieuweFactuur NA  \"nieuweFactuur.betalingSet.add(nieuweBetaling) \" " + nieuweFactuur);

				//factuurService.persist(nieuweFactuur);	

				logger.info("*** Persist - end ***");

				break;


			case 2:

				logger.info("*** Update - start ***");
				
				/* input.nextLine();
				System.out.print("Voer uw Klant ID in: ");
				klant_id = input.nextInt();		*/
				
				System.out.print("Voer het ID van de bestelling in die u wilt veranderen: ");
				long bestelling_id = input.nextLong();
				
				while (checkExistenceInDatabase.checkBestelling_id(bestelling_id)!= true) { 
            		System.out.print("\nVoer een ander bestelling ID in: ");
            		bestelling_id = input.nextLong();
            		System.out.println();
            	}    
				
				
				Bestelling updateBestelling = bestellingService.findById(bestelling_id);			
				updateBestelling.setBestelDatum();
				// updateBestelling.setKlant(klantService.findById(klant_id));

				logger.info("Klant:" + updateBestelling.getKlant());
				logger.info("bestelling_id: " + updateBestelling.getId());
				

				logger.info("bestaandeBestelling bevat " + updateBestelling);

				BestelArtikel updateBestelArtikel = bestellingHasArtikelService.findById(bestelling_id);

				logger.info("bestaandeBestelArtikel bevat " + updateBestelArtikel);

				bestellingService.update(updateBestelling);

				artikel_id = 0;
				
				
				System.out.print("Voer ID van artikel in die je wil toevoegen (0 = stoppen met artikelen toevoegen): ");
				artikel_id = input.nextLong();
				
				do {
					
					updateBestelArtikel.setArtikel(artikelService.findById(artikel_id));
					
					logger.info("updateBestelArtikel bevat: " + updateBestelArtikel);

					System.out.print("Hoeveel wil je er van bestellen?: ");
					int aantal = input.nextInt();	

					updateBestelArtikel.setAantal(aantal);
					updateBestelArtikel.setBestelling(updateBestelling);
				
					updateBestelling.bestellingHasArtikelen.add(updateBestelArtikel);
					bestellingHasArtikelService.persist(updateBestelArtikel);

					logger.info("bestelartikel wordt geupdate");

					updateBestelling.setBestellingHasArtikelen(updateBestelling.bestellingHasArtikelen);

					logger.info("object nieuweBestelling bevat nu: " + updateBestelling);
					logger.info("bestelling object wordt geupdate met bestelartikel_id en geupdate in bestelling tabel");

					bestellingService.update(updateBestelling);

					logger.info("de set bestellingHasArtikelen bevat: " + updateBestelling.getBestellingHasArtikelen());
				
					System.out.print("Voer ID van artikel in die je wilt toevoegen (0 = stoppen met artikelen toevoegen): ");
					artikel_id = input.nextLong();
					
				} while (artikel_id  != 0 )  ;
				
				/* Factuur updateFactuur = factuurService.findById(bestelling_id);
				

				logger.info("object bestaandeFactuur " + updateFactuur);

				updateFactuur.setBestelling(updateBestelling);
				updateFactuur.setFactuurDatum();
				long factuur_id = updateFactuur.getId();

				logger.info("object bestaandeFactuur na verandering bestelling " + updateBestelling);

				updateBestelArtikel.setArtikel(artikelService.findById(artikel_id));
				Betaling updateBetaling = betalingService.findById(factuur_id);
				
				updateBetaling.setBetaalDatum();


				System.out.print("Hoe wil je betalen? 1 = Contant, 2 = Pinbetaling, 3 = IDeal, 4 = Creditcard : ");
				betaalwijze = input.nextInt();

				switch (betaalwijze) {
				case 1:
					updateBetaling.setBetaalwijze(updateBetaling.getBetaalwijze().Contant);
					break;
				case 2:
					updateBetaling.setBetaalwijze(updateBetaling.getBetaalwijze().Pinbetaling);
					break;
				case 3:
					updateBetaling.setBetaalwijze(updateBetaling.getBetaalwijze().IDeal);
					break;
				case 4:
					updateBetaling.setBetaalwijze(updateBetaling.getBetaalwijze().Creditcard);
					break;				
				default:
					updateBetaling.setBetaalwijze(updateBetaling.getBetaalwijze().Pinbetaling);
				} 

				System.out.print("Voer overige betalingsgegevens in c.q. een beschrijving: ");
				input.nextLine();
				betalingsGegevens = input.nextLine();

				updateBetaling.setBetalingsGegevens(betalingsGegevens);
				// updateBetaling.setKlant(klantService.findById(klant_id));			
				updateBetaling.setFactuur(updateFactuur);

				betalingService.update(updateBetaling);

				logger.info("object updateBetaling bevat:" + updateBetaling);									
				logger.info("object updateFactuur VOOR  \"nieuweFactuur.betalingSet.add(nieuweBetaling) \" " + updateFactuur);

				updateFactuur.betalingSet.add(updateBetaling);

				logger.info("object updateFactuur NA  \"nieuweFactuur.betalingSet.add(nieuweBetaling) \" " + updateFactuur);

				//factuurService.persist(nieuweFactuur);	 */

				logger.info("*** Update - end ***"); 

			
				break;

			case 3:
				System.out.println("*** findById - start ***");			
				
				System.out.print("Voer het bestelling id die u zoekt: ");
				bestelling_id = input.nextLong();
				
				System.out.println(bestellingService.findById(bestelling_id));				
				break;

			case 4:
				System.out.println("*** Delete - start ***");
				
				System.out.print("Voer de bestelling id in die je wilt deleten: ");				
				bestelling_id = input.nextLong();		
				
				
				while (checkExistenceInDatabase.checkBestelling_id(bestelling_id)!= true) { 
            		System.out.print("\nVoer een ander bestelling ID in: ");
            		bestelling_id = input.nextLong();
            		System.out.println();
            	}    
							
				bestellingService.delete(bestelling_id);
				
				
				break;

			case 5:
				logger.info("findAll bestellingen aangeroepen");
				List<Bestelling> bestellingen = bestellingService.findAll();

				System.out.println("De volgende bestellingen staan in het assortiment:");

				for (Bestelling b: bestellingen) {
					System.out.println("-" + b.toString());
				}

				break;

			case 6:
				System.out.println("*** DeleteAll - start ***");
				
				System.out.println("Weet u zeker dat u al uw bestelling wilt verwijderen: ");
				String antwoord = input.next();
				
				if(antwoord.equalsIgnoreCase("ja")){
					artikelService.deleteAll();
					break;
					
				} else {
					break;
				}

			default:
				System.out.println("\n! Ongeldige optie!\n");

			} 

		}

		finally {
			// zinnige code			
		}	



	}

}
