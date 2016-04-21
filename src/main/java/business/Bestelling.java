package business;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class Bestelling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bestelling_id")
	private long id;

	@Column(name = "bestelnummer")
	private String bestelNummer;

	@Column(name = "besteldatum")
	private Date bestelDatum;

	@OneToMany(mappedBy = "bestelling",targetEntity = BestelArtikel.class,  
			fetch = FetchType.EAGER) 
	//@Cascade({org.hibernate.annotations.CascadeType.ALL})
	public Set<BestelArtikel> bestellingHasArtikelen = new HashSet<>();

	@OneToMany(mappedBy = "bestelling", targetEntity = Factuur.class, 
			fetch = FetchType.LAZY)
	//@Cascade({org.hibernate.annotations.CascadeType.ALL})
	private Set<Factuur> facturen = new HashSet<>();

	@ManyToOne
    @JoinColumn(name = "klant_id")
	private Klant klant;

	public Bestelling() {		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;

	}

	public String getBestelNummer() {
		return bestelNummer;
	}     

	public void setBestelNummer() {
		int lengthOfRandomString = 10;
		Random rand = new Random();
		String alphaNumericCharacters = "abcdefghijklmnopqrstuvwxyz"
				+ "ABCDEFGHIJLMNOPQRSTUVWXYZ"
				+ "1234567890";

		StringBuilder result = new StringBuilder();

		for (int i =0; i< lengthOfRandomString ; i++) {
			result.append(
					alphaNumericCharacters.
					charAt(rand.nextInt(alphaNumericCharacters.length())));
		}
		bestelNummer = result.toString();           
		System.out.println("bestelnummer is: " + bestelNummer);
	}  

	public Date getBestelDatum() {
		return bestelDatum;
	}

	public void setBestelDatum() {	    	
		bestelDatum = new Date();
		System.out.println("besteldatum is: " + bestelDatum);
	}

	public Set<BestelArtikel> getBestellingHasArtikelen() {
		return bestellingHasArtikelen;
	}

	public void setBestellingHasArtikelen (Set<BestelArtikel> bestellingHasArtikelen) {
		this.bestellingHasArtikelen = bestellingHasArtikelen;
	}

	public Set<Factuur> getFacturen() {
		return facturen;
	}

	public void setFacturen(Set<Factuur> facturen) {
		this.facturen = facturen;
	}

	public Klant getKlant() {
		return klant;
	}


	public void setKlant(Klant klant) {
		this.klant = klant;
	} 

	@Override
	public String toString(){
		return 	 
				"\nBestelling id : " + id + ". Bestellingnummer : " + bestelNummer + 
				"\n-----------------------------------------------------------------" +
				"\nBestel datum: " + bestelDatum + 
				"\tArtikelen: " + this.getBestellingHasArtikelen() +  
				"\nKlant " + this.getKlant() +
				"\n\n";

	} 
	
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestelDatum == null) ? 0 : bestelDatum.hashCode());
		result = prime * result + ((bestelNummer == null) ? 0 : bestelNummer.hashCode());
		//result = prime * result + ((bestellingHasArtikelen == null) ? 0 : bestellingHasArtikelen.hashCode());
		result = prime * result + ((facturen == null) ? 0 : facturen.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((klant == null) ? 0 : klant.hashCode());
		return result;
	} */
	
 	@Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.bestelDatum);
	        hash = 67 * hash + Objects.hashCode(this.bestelNummer);
	        //hash = 67 * hash + Objects.hashCode(this.facturen);
	        hash = 67 * hash + Objects.hashCode(this.klant);
	       // hash = 67 * hash + Objects.hashCode(this.accountSet);
	       // hash = 67 * hash + Objects.hashCode(this.bestellingSet);
	       // hash = 67 * hash + Objects.hashCode(this.factuurSet);
	        return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bestelling other = (Bestelling) obj;
		if (bestelDatum == null) {
			if (other.bestelDatum != null)
				return false;
		} else if (!bestelDatum.equals(other.bestelDatum))
			return false;
		if (bestelNummer == null) {
			if (other.bestelNummer != null)
				return false;
		} else if (!bestelNummer.equals(other.bestelNummer))
			return false;
		/*if (bestellingHasArtikelen == null) {
			if (other.bestellingHasArtikelen != null)
				return false;
		} else if (!bestellingHasArtikelen.equals(other.bestellingHasArtikelen))
			return false;*/
		if (facturen == null) {
			if (other.facturen != null)
				return false;
		} else if (!facturen.equals(other.facturen))
			return false;
		if (id != other.id)
			return false;
		if (klant == null) {
			if (other.klant != null)
				return false;
		} else if (!klant.equals(other.klant))
			return false;
		return true;
	} 

	
}
