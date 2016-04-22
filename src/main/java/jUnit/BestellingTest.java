package jUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import business.*;
import service.*;


public class BestellingTest {
	
	private Klant klant = new Klant();	
	private Artikel artikel = new Artikel();
	private Adres adres = new Adres();
	private Account account = new Account();
	private BestelArtikel bestelArtikel = new BestelArtikel();
	private Bestelling bestelling = new Bestelling();
	private Betaling betaling = new Betaling();
	private Factuur factuur = new Factuur();
	private KlantAdres klantAdres = new KlantAdres();
	
	private KlantDaoService klantService = new KlantDaoService();
	private ArtikelDaoService artikelService = new ArtikelDaoService();
	private AdresDaoService adresService = new AdresDaoService();
	private AccountDaoService accountService = new AccountDaoService();
	private BestelArtikelDaoService bestelArtikelService = new BestelArtikelDaoService();
	private BestellingDaoService bestellingService = new BestellingDaoService();
	private BetalingDaoService betalingService = new BetalingDaoService();
	private FactuurDaoService factuurService = new FactuurDaoService();
	private KlantAdresDaoService klantAdresService = new KlantAdresDaoService();
	
	BestelArtikel nieuweBestelArtikel = new BestelArtikel();
	Bestelling nieuweBestelling = new Bestelling();
	Factuur nieuweFactuur = new Factuur();
	Betaling nieuweBetaling = new Betaling();
	Artikel nieuweArtikel = new Artikel();
	

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
        adres.setPostcode("90210AB");
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
		
		betaling.setBetaalDatum();
        betaling.setBetaalwijze(Betaling.Betaalwijze.Pinbetaling);    
        betaling.setBetalingsGegevens("jUnit testbetaling");
        betaling.setKlant(klant);			
		betaling.setFactuur(factuur);
		
		factuur.betalingSet.add(betaling);
		
		factuurService.persist(factuur);	
		
		betalingService.persist(betaling);
	
	}
	
	
	@After
	public void tearDown() {
		
		klantService.delete(klant.getId());
	}
	
	
	@Test
	public void testCreateBestelling() {
		
		

		
		
		nieuweBestelling.setBestelDatum();
        nieuweBestelling.setBestelNummer();
        nieuweBestelling.setKlant(klant);
        bestellingService.persist(nieuweBestelling);
        
        bestelArtikel.setArtikel(artikel);
        bestelArtikel.setAantal(69);
        bestelArtikel.setBestelling(nieuweBestelling);
        nieuweBestelling.bestellingHasArtikelen.add(nieuweBestelArtikel);
		bestelArtikelService.persist(bestelArtikel);
        
		nieuweBestelling.setBestellingHasArtikelen(nieuweBestelling.bestellingHasArtikelen);
		bestellingService.update(nieuweBestelling);
        
		nieuweFactuur.setFactuurDatum();
		nieuweFactuur.setFactuurNummer();
		nieuweFactuur.setKlant(klant);
		
		nieuweBetaling.setBetaalDatum();
		nieuweBetaling.setBetaalwijze(Betaling.Betaalwijze.Pinbetaling);    
		nieuweBetaling.setBetalingsGegevens("jUnit testbetaling");
		nieuweBetaling.setKlant(klant);			
		nieuweBetaling.setFactuur(nieuweFactuur);
		
		factuur.betalingSet.add(nieuweBetaling);
		
		factuurService.persist(nieuweFactuur);	
		
		betalingService.persist(nieuweBetaling);
	
	
		assertEquals((int)bestelling.getId() + 1, (int)nieuweBestelling.getId());
	}
		
		
		@Test
		public void testDeleteBestelling() {
			bestellingService.delete(nieuweBestelling);
			assertNull(nieuweBestelling);
		}
		
		@Test
		public void testDeleteBestellingById() {		
			bestellingService.delete(nieuweBestelling.getId());
			assertNull(nieuweBestelling);
		}
			
		
		@Test
		public void testDeleteAlleBestellingen() {
			bestellingService.deleteAll();
			assertEquals(null, bestellingService.findAll());
		}
		
				
		@Test
		public void testUpdateBestellingNieuweArtikel() {
			
			nieuweArtikel.setArtikel_naam("jUnitTest");
			nieuweArtikel.setArtikel_nummer();
			nieuweArtikel.setArtikel_prijs(123.99);
			nieuweArtikel.setOmschrijving("nieuwe jUnit testartikel");
			artikelService.persist(nieuweArtikel);
			
			nieuweBestelArtikel.setArtikel(nieuweArtikel);
			nieuweBestelArtikel.setAantal(500);
			nieuweBestelArtikel.setBestelling(bestelling);

			nieuweBestelling.bestellingHasArtikelen.add(nieuweBestelArtikel);
			bestelArtikelService.persist(nieuweBestelArtikel);

			bestelling.setBestellingHasArtikelen(bestelling.bestellingHasArtikelen);
			
			assertEquals(2, (int)bestelling.getBestellingHasArtikelen().size());
		}
		
		
		
		}
		
		
		@Test
		public void testFindBestellingById() {

		}
		
		@Test
		public void testFindAlleBestellingen() {
	
		}
	

}
