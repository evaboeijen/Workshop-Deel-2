package jUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.*;
import service.*;


public class BestellingTest {

	private static final Logger logger =  LoggerFactory.getLogger(BestellingTest.class);

	private Klant klant = new Klant();	
	private KlantAdres klantAdres = new KlantAdres();	
	private Adres adres = new Adres();
	private Account account = new Account();

	private Artikel artikel = new Artikel();
	private BestelArtikel bestelArtikel = new BestelArtikel();
	private Bestelling bestelling = new Bestelling();
	private Factuur factuur = new Factuur();
	private Betaling betaling = new Betaling();

	private Artikel nieuweArtikel = new Artikel();
	private Artikel nieuweArtikel2 = new Artikel();
	private BestelArtikel nieuweBestelArtikel = new BestelArtikel();
	private Bestelling nieuweBestelling = new Bestelling();
	private Factuur nieuweFactuur = new Factuur();
	private Betaling nieuweBetaling = new Betaling();

	private KlantDaoService klantService = new KlantDaoService();
	private ArtikelDaoService artikelService = new ArtikelDaoService();
	private AdresDaoService adresService = new AdresDaoService();
	private AccountDaoService accountService = new AccountDaoService();
	private BestelArtikelDaoService bestelArtikelService = new BestelArtikelDaoService();
	private BestellingDaoService bestellingService = new BestellingDaoService();
	private BetalingDaoService betalingService = new BetalingDaoService();
	private FactuurDaoService factuurService = new FactuurDaoService();
	private KlantAdresDaoService klantAdresService = new KlantAdresDaoService();


	@Before
	public void setUp() {

		klant.setVoornaam("jUnitTest");
		klant.setTussenvoegsel("van");
		klant.setAchternaam("BestellingDao");
		klant.setEmail("test@test.com");
		klantService.persist(klant);

		artikel.setArtikel_naam("jUnitTest");
		artikel.setArtikel_nummer();
		artikel.setArtikel_prijs(69.69);
		artikel.setOmschrijving("jUnit testartikel");
		artikelService.persist(artikel);

		adres.setHuisnummer(123);
		adres.setPostcode("90210");
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

		bestelling.setBestelDatum();
		bestelling.setBestelNummer();
		bestelling.setKlant(klant);
		bestellingService.persist(bestelling);

		bestelArtikel.setArtikel(artikel);
		bestelArtikel.setAantal(69);
		bestelArtikel.setBestelling(bestelling);
		bestelling.bestellingHasArtikelen.add(bestelArtikel);
		bestelArtikelService.persist(bestelArtikel);

		bestelling.setBestellingHasArtikelen(bestelling.bestellingHasArtikelen);
		bestellingService.update(bestelling);

		factuur.setFactuurDatum();
		factuur.setFactuurNummer();
		factuur.setKlant(klant);
		factuur.setBestelling(bestelling);

		betaling.setBetaalDatum();
		betaling.setBetaalwijze(Betaling.Betaalwijze.Pinbetaling);    
		betaling.setBetalingsGegevens("jUnit testbetaling");
		betaling.setKlant(klant);			
		betaling.setFactuur(factuur);

		factuur.betalingSet.add(betaling);

		factuurService.persist(factuur);	
	}


	@After
	public void tearDown() {

		klantService.delete(klant.getId());
		artikelService.delete(artikel.getId());
		adresService.delete(adres.getId());		
	} 


	@Test
	public void testCreate() {

		logger.info("klant is: " + klant);	

		nieuweBestelling.setBestelDatum();
		nieuweBestelling.setBestelNummer();
		nieuweBestelling.setKlant(klant);
		bestellingService.persist(nieuweBestelling);

		nieuweArtikel.setArtikel_naam("jUnitCreate");
		nieuweArtikel.setArtikel_nummer();
		nieuweArtikel.setArtikel_prijs(123.99);
		nieuweArtikel.setOmschrijving("jUnit createartikel");
		artikelService.persist(nieuweArtikel);

		nieuweBestelArtikel.setArtikel(nieuweArtikel);
		nieuweBestelArtikel.setAantal(123);
		nieuweBestelArtikel.setBestelling(nieuweBestelling);
		nieuweBestelling.bestellingHasArtikelen.add(nieuweBestelArtikel);
		bestelArtikelService.persist(nieuweBestelArtikel);

		nieuweBestelling.setBestellingHasArtikelen(nieuweBestelling.bestellingHasArtikelen);
		bestellingService.update(nieuweBestelling);

		nieuweFactuur.setFactuurDatum();
		nieuweFactuur.setFactuurNummer();
		nieuweFactuur.setKlant(klant);
		nieuweFactuur.setBestelling(nieuweBestelling);

		nieuweBetaling.setBetaalDatum();
		nieuweBetaling.setBetaalwijze(Betaling.Betaalwijze.IDeal);    
		nieuweBetaling.setBetalingsGegevens("jUnit testbetaling");
		nieuweBetaling.setKlant(klant);			
		nieuweBetaling.setFactuur(nieuweFactuur);

		nieuweFactuur.betalingSet.add(nieuweBetaling);

		factuurService.persist(nieuweFactuur);	

		assertEquals("the first bestelling_id + 1 must equal the second bestelling_id", ((int)bestelling.getId() + 1), (int)nieuweBestelling.getId());
		assertEquals("the first factuur_id + 1 must equal the second factuur_id", ((int)factuur.getId() + 1), (int)nieuweFactuur.getId());
		assertEquals("the first betaling_id + 1 must equal the second betaling_id", ((int)betaling.getId() + 1), (int)nieuweBetaling.getId());
		assertEquals("the first artikel_id + 1 must equal the second artikel_id", ((int)artikel.getId() + 1), (int)nieuweArtikel.getId());
		assertEquals("the first bestelartikel_id + 1 must equal the second bestelartikel_id", ((int)bestelArtikel.getId() + 1), (int)nieuweBestelArtikel.getId());
	} 



	@Test
	public void testUpdate() {

		List<BestelArtikel> bestelArtikelList = bestelArtikelService.findAll();
		assertEquals(1, bestelArtikelList.size());

		bestelling.setBestelNummer();	//update bestelnummer met random waarde
		nieuweArtikel.setArtikel_naam("jUnitCreate");
		nieuweArtikel.setArtikel_nummer();
		nieuweArtikel.setArtikel_prijs(123.99);
		nieuweArtikel.setOmschrijving("jUnit createartikel");
		artikelService.persist(nieuweArtikel);

		assertNotSame(artikel, nieuweArtikel);

		bestelArtikel.setArtikel(nieuweArtikel);	// vervang vorige bestelde artikel met nieuwe artikel
		bestelArtikel.setAantal(50);
		bestelArtikelService.update(bestelArtikel);

		nieuweArtikel2.setArtikel_nummer();
		nieuweArtikel2.setArtikel_prijs(55.50);
		nieuweArtikel2.setOmschrijving("jUnit createartikel2");
		nieuweArtikel2.setArtikel_naam("jUnitCreate2");
		artikelService.persist(nieuweArtikel2);

		assertNotSame(nieuweArtikel, nieuweArtikel2);

		nieuweBestelArtikel.setArtikel(nieuweArtikel2);
		nieuweBestelArtikel.setBestelling(bestelling);
		nieuweBestelArtikel.setAantal(100);

		//bestelling.bestellingHasArtikelen.add(nieuweBestelArtikel); // voeg nog een nieuwe artikel toe

		bestelArtikelService.persist(nieuweBestelArtikel); // voeg nog een nieuwe artikel toe

		betaling.setBetaalwijze(Betaling.Betaalwijze.Contant);    
		betaling.setBetalingsGegevens("UPDATED");
		betalingService.update(betaling);

		factuur.setFactuurNummer();
		factuurService.update(factuur);

		bestelArtikelList = bestelArtikelService.findAll();		
		assertEquals(2, bestelArtikelList.size());
	} 


	@Test
	public void testFind() {

		nieuweBestelling = bestellingService.findById(bestelling.getId());
		assertNotNull(nieuweBestelling);

		List<Bestelling> bestellingen = bestellingService.findAll();			
		assertNotNull(bestellingen);

		nieuweFactuur = factuurService.findById(factuur.getId());
		assertNotNull(nieuweFactuur);

		List<Factuur> facturen = factuurService.findAll();			
		assertNotNull(facturen);	

		nieuweBetaling = betalingService.findById(betaling.getId());
		assertNotNull(nieuweBetaling);

		List<Betaling> betalingen = betalingService.findAll();			
		assertNotNull(betalingen);	

		nieuweBestelArtikel = bestelArtikelService.findById(bestelArtikel.getId());
		assertNotNull(nieuweBestelArtikel);

		List<BestelArtikel> bestelArtikelen = bestelArtikelService.findAll();			
		assertNotNull(bestelArtikelen);	

		nieuweArtikel = artikelService.findById(artikel.getId());
		assertNotNull(nieuweArtikel);

		List<Artikel> artikelen = artikelService.findAll();			
		assertNotNull(artikelen);	
	} 


	@Test
	public void testDelete(){
		
		bestellingService.delete(bestelling.getId());
		assertNull("bestelling must have been deleted", bestellingService.findById(bestelling.getId()));
		assertNull("factuur must have been deleted", factuurService.findById(factuur.getId()));
		assertNull("betaling must have been deleted", betalingService.findById(betaling.getId()));
		assertNull("bestelArtikel must have been deleted", bestelArtikelService.findById(bestelArtikel.getId()));
	} 


	
	@Test
	public void testDeleteAll(){

		bestellingService.deleteAll();
		assertEquals("List bestelling must be empty", 0, bestellingService.findAll().size());

		bestelArtikelService.deleteAll();
		assertEquals("List bestelArtikel must be empty", 0, bestelArtikelService.findAll().size());

		factuurService.deleteAll();
		assertEquals("List factuur must be empty", 0, factuurService.findAll().size());

		betalingService.deleteAll();
		assertEquals("List betaling must be empty", 0, betalingService.findAll().size());
				
		/* artikelService.deleteAll();
		assertEquals("List artikel must be empty", 0, artikelService.findAll().size());
		*/
	} 


}
