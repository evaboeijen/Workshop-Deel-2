package jUnit;

import business.*;
import service.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KlantTest {
	
	private Klant klant = new Klant();	
	private Account account = new Account();
	private Adres adres = new Adres();
	private KlantAdres klantAdres = new KlantAdres();
	
	private KlantDaoService klantService = new KlantDaoService();
	private AdresDaoService adresService = new AdresDaoService();
	private AccountDaoService accountService = new AccountDaoService();
	private KlantAdresDaoService klantAdresService = new KlantAdresDaoService();
	
	Klant nieuweKlant = new Klant();
	Adres nieuweAdres = new Adres();
	KlantAdres nieuweKlantAdres = new KlantAdres();
	Account nieuweAccount = new Account();
	
	
	@Before
	public void setUp(){
		klant.setVoornaam("jUnitTest");
		klant.setTussenvoegsel("van");
		klant.setAchternaam("KlantDao");
		klant.setEmail("KlantDao@test.com");
		klantService.persist(klant);
        
        adres.setHuisnummer(123);
        adres.setPostcode("9021AB");
        adres.setStraatnaam("jUnitstraat");
        adres.setToevoeging("A");
        adres.setWoonplaats("Amsterdam");
        adresService.persist(adres);
        
        klantAdres.setKlant(klant);
        klantAdres.setAdres(adres);
        klantAdres.setAdresType(KlantAdres.AdresType.Postadres);
        klantAdresService.persist(klantAdres);
        
        account.setAccountNaam("jUnit account");
        account.setDateCreated();
        account.setKlant(klant);
        accountService.persist(account);
	
        
	}
	
	
 	@Test
	public void testCreateUpdate() {
		
    	// testCreate2
    		
    		nieuweKlant.setVoornaam("jUnitCreate");
    		nieuweKlant.setTussenvoegsel("de");
    		nieuweKlant.setAchternaam("CreateTest");
    		nieuweKlant.setEmail("createjUnit@email.com");
    		klantService.persist(nieuweKlant);
            
    		nieuweAdres.setHuisnummer(69);
            nieuweAdres.setPostcode("1000XX");
            nieuweAdres.setStraatnaam("createjUnitstraat");
            nieuweAdres.setToevoeging("j");
            nieuweAdres.setWoonplaats("createjUnitPlaats");
            adresService.persist(nieuweAdres);
            
            klantAdres.setKlant(nieuweKlant);
            klantAdres.setAdres(nieuweAdres);
            klantAdres.setAdresType(KlantAdres.AdresType.Postadres);
            klantAdresService.persist(klantAdres);
            
            nieuweAccount.setAccountNaam("create jUnit account");
            nieuweAccount.setDateCreated();
            nieuweAccount.setKlant(nieuweKlant);
            accountService.persist(nieuweAccount);
    	
    		assertEquals("the first klant_id + 1 must be the same as the second klant_id", ((int)klant.getId() + 1), (int)nieuweKlant.getId());
    	
    	// testUpdate
    		
    		nieuweKlant.setVoornaam("jUnitUpdate");
    		nieuweKlant.setTussenvoegsel("de");
    		nieuweKlant.setAchternaam("UpdateTest");
    		nieuweKlant.setEmail("UpdatejUnit@email.com");
    		klantService.update(nieuweKlant);
    		
    		List<Klant> klantList = new ArrayList<Klant>();
    		klantList.add(klant);
    		klantList.add(nieuweKlant);
    		
    		assertEquals("Lists must be the same", klantList.hashCode(), klantService.findAll().hashCode());
    		
    		nieuweAdres.setHuisnummer(100);
            nieuweAdres.setPostcode("1111ZZ");
            nieuweAdres.setStraatnaam("updatejUnitstraat");
            nieuweAdres.setToevoeging("u");
            nieuweAdres.setWoonplaats("updatejUnitPlaats");
            adresService.persist(nieuweAdres);
            
            klantAdres.setKlant(nieuweKlant);
            klantAdres.setAdres(nieuweAdres);
            klantAdres.setAdresType(KlantAdres.AdresType.Bezoekadres);
            klantAdresService.persist(klantAdres);
            
            nieuweAccount.setAccountNaam("create jUnit account");
            nieuweAccount.setDateCreated();
            nieuweAccount.setKlant(klant);
            accountService.persist(nieuweAccount);
	} 
	
    @Test
    public void testFind(){
    		nieuweKlant = klantService.findById(klant.getId());
    		assertNotNull(nieuweKlant);
    		
    		nieuweAdres= adresService.findById(adres.getId());
    		assertNotNull(nieuweAdres);
    		
    		nieuweKlantAdres = klantAdresService.findById(klantAdres.getId());
    		assertNotNull(nieuweKlantAdres);
    		
    		nieuweAccount = accountService.findById(account.getId());
    		assertNotNull(nieuweAccount);
  		
    		List<Klant> klanten = klantService.findAll();			
    		assertNotNull(klanten);
    		
    		List<Adres> adressen = adresService.findAll();			
    		assertNotNull(adressen);
    		
    		List<KlantAdres> klantAdressen = klantAdresService.findAll();			
    		assertNotNull(klantAdressen);
    		
    		List<Account> accounten = accountService.findAll();			
    		assertNotNull(accounten);
    }
    
    
    	@Test
    	public void testDelete(){
    	// testDeleteKlant() {
    		Long klant_id  = klant.getId();
    		klantService.delete(klant_id);
    		assertNull("klant must be deleted", klantService.findById(klant_id));
    	
    	// testDelete() {
    		Long adres_id = adres.getId();
    		adresService.delete(adres_id);
    		assertNull("adres must be deleted", adresService.findById(adres_id));
    		assertNull("adresKlant must also be deleted", klantAdresService.findById(klantAdres.getId()));
    		assertNull("account must be deleted", accountService.findById(account.getId()));
    	}
    
	
    @Test
    public void testDeleteAll(){
    	
    			klantService.deleteAll();
    			assertEquals("Lists klant must be empty", 0, klantService.findAll().size());
    				
      			adresService.deleteAll();
    			assertEquals("Lists Adres must be empty", 0, adresService.findAll().size());
    
    			accountService.deleteAll();
    			assertEquals("Lists acocunt must be empty", 0, accountService.findAll().size());
	}
	
	
	@After
    public void tearDown() {
		
		accountService.deleteAll();
		adresService.deleteAll();
		klantAdresService.deleteAll();
		klantService.deleteAll();
	}
	
	

}
