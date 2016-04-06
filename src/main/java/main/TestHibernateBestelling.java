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

				nieuweBestelling.setBestelNummer();				
				nieuweBestelling.setBestelDatum();
				nieuweBestelling.setKlant(klantService.findById(klant_id));

				logger.info("Klant :" + nieuweBestelling.getKlant());
				logger.info("bestelling_id: " + nieuweBestelling.getId());

				bestellingService.persist(nieuweBestelling);

				System.out.print("Voer ID van artikel in die je wil bestellen: ");
				long artikel_id = input.nextInt();	

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

				System.out.println("COMING SOON");
				break;

			case 3:
				System.out.println("COMING SOON");				
				break;

			case 4:
				System.out.println("COMING SOON");
				break;

			case 5:
				System.out.println("COMING SOON");
				break;

			case 6:
				System.out.println("COMING SOON");
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
