package main;


import business.*;

import service.*;

import java.util.List;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestHibernateAccount { 

	private static final Logger logger =  LoggerFactory.getLogger(TestHibernateBestelling.class);

	public static void main(String[] args){

		KlantDaoService klantService = new KlantDaoService();
		AccountDaoService accountService = new AccountDaoService();

		System.out.println("\t-------------------------");
		System.out.println("\t Test Account Domain  ");
		System.out.println("\t-------------------------");
		System.out.println("1. persist");
		System.out.println("2. update");
		System.out.println("3. findById");
		System.out.println("4. delete");
		System.out.println("5. findAll");
		//System.out.println("6. deleteAll");
		System.out.print("Voer optie in en druk op Enter:");


		try (Scanner input = new Scanner(System.in);) {		     

			int keuze = input.nextInt();

			switch (keuze) {
			case 1:
				System.out.println("*** Persist - start ***");
				Account nieuweAccount = new Account();
				input.nextLine();
				System.out.print("Voer uw klant ID in waarvoor u een Account wilt aanmaken: ");
				long klant_id = input.nextLong();	

				nieuweAccount.setDateCreated();
				nieuweAccount.setKlant(klantService.findById(klant_id));
				
				System.out.print("Voer de naam van uw nieuwe account in: ");
				String nieuweAccountNaam = input.next();	
												
				nieuweAccount.setAccountNaam(nieuweAccountNaam);
				

				System.out.println("Toe te voegen nieuwe account: " + nieuweAccount);
				accountService.persist(nieuweAccount);
				System.out.println("Account toegevoegd: " + nieuweAccount);
				
				break;

			case 2: //werkt nog niet
				
				System.out.println("*** Update - start ***");
				Account bestaandeAccount = new Account();	
				
				System.out.println("Voer uw klant id in: ");
				klant_id = input.nextLong();
				
				System.out.print("Voer het ID in van het Account dat u wilt aanpassen: ");				
				long account_id = input.nextLong();
				
				System.out.println("Voer uw nieuwe accountnaam in: ");
				String accountNaam = input.next();
				
				
				bestaandeAccount.setId(account_id);
				bestaandeAccount.setKlant(klantService.findById(klant_id));
				bestaandeAccount.setAccountNaam(accountNaam);
				bestaandeAccount.setDateCreated();

				logger.info("Account is: " + bestaandeAccount);
				accountService.update(bestaandeAccount); 
				
				break;

			case 3:
				System.out.println("*** findById - start ***");							
			
				System.out.print("Voer het ID in van uw Account: ");
				account_id = input.nextLong();
				
				System.out.println(accountService.findById(account_id));
				
				break;

			case 4:
				System.out.println("*** Delete - start ***");
				bestaandeAccount = new Account();	
				
				System.out.print("Voer het ID in van uw account die u wilt deleten: ");				
				account_id = input.nextLong();				
				accountService.delete(account_id);
				break;

			case 5:
				logger.info("findAll klanten aangeroepen");
				List<Account> accounts = accountService.findAll();

				System.out.println("De volgende klanten staan in de Klant tabel :");

				for (Account a : accounts) {
					System.out.println("-" + a.toString());
				}

				break;
				
			/*case 6:
				System.out.println("*** DeleteAll - start ***");
				accountService.deleteAll();
				break;*/

			default:
				System.out.println("\n! Ongeldige optie!\n");

			} 

		}

		finally {
			// zinnige code			
		}	



	}

}
