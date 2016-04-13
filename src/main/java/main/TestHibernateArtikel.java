package main;


import business.*;

import service.*;

import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestHibernateArtikel { 

	private static final Logger logger =  LoggerFactory.getLogger(TestHibernateArtikel.class);

	public static void main(String[] args){

		ArtikelDaoService artikelService = new ArtikelDaoService();

		System.out.println("\t-------------------------");
		System.out.println("\t Test Artikel Domain  ");
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
				Artikel nieuweArtikel = new Artikel();
				
				input.nextLine();
				System.out.println("Voer het artikelnaam in wat in het assortiment geplaatst wordt: ");
				String artikelNaam = input.nextLine();		
				
				nieuweArtikel.setArtikel_naam(artikelNaam);
				nieuweArtikel.setArtikel_nummer();
				
				System.out.println("Voer de omschrijving van het artikel in: ");
				String artikelOmschrijving = input.nextLine();	
												
				nieuweArtikel.setOmschrijving(artikelOmschrijving);
				
				System.out.println("Bepaal de prijs van het artikel: ");
				double artikelPrijs = input.nextDouble();	
				
				nieuweArtikel.setArtikel_prijs(artikelPrijs);
				
				artikelService.persist(nieuweArtikel);
				System.out.println("Artikel toegevoegd: " + nieuweArtikel);
				
				
				break;

			case 2:
				
				System.out.println("*** Update - start ***");	
				
				System.out.print("Voer het ID in van het artikel die u wilt aanpassen: ");				
				long artikelId = input.nextLong();
				input.nextLine();
				
				Artikel bestaandeArtikel = artikelService.findById(artikelId);
				
				System.out.println("Voer de naam van het artikel in: ");
				String nieuwArtikelNaam = input.nextLine();
				
				bestaandeArtikel.setArtikel_naam(nieuwArtikelNaam);
				
				System.out.println("Voer de artikel omschrijving in: ");
				String nieuwArtikelOmschrijving = input.nextLine();
				
				bestaandeArtikel.setOmschrijving(nieuwArtikelOmschrijving);	
				
				System.out.println("Bepaal de prijs van het artikel: ");
				double nieuwArtikelPrijs = input.nextDouble();
				
				bestaandeArtikel.setArtikel_prijs(nieuwArtikelPrijs);
				
				System.out.println("het artikelnummer blijft hetzelfde");
				//System.out.println(artikelService.findById(artikelId));			

				logger.info("Artikel is: " + bestaandeArtikel);
				artikelService.update(bestaandeArtikel); 
				
				
				break;

			case 3:
				System.out.println("*** findById - start ***");			
							
				System.out.print("Voer het ID in van het artikel die je wil zoeken: ");
				artikelId = input.nextLong();
				
				System.out.println(artikelService.findById(artikelId));				
				
				break;

			case 4:
				System.out.println("*** Delete - start ***");
				bestaandeArtikel = new Artikel();	
				
				System.out.print("Voer het ID in van het artikel die je wil deleten: ");				
				artikelId = input.nextLong();				
				artikelService.delete(artikelId);
				
				break;

			case 5:
				logger.info("findAll artikellen aangeroepen");
				List<Artikel> artikellen = artikelService.findAll();

				System.out.println("De volgende artikellen staan in het assortiment:");

				for (Artikel a : artikellen) {
					System.out.println("-" + a.toString());
				}

				break;
				
			case 6:
				System.out.println("*** DeleteAll - start ***");
				
				System.out.println("Weet u zeker dat u alle artikelen uit het assortiment wil verwijderen? ");
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
