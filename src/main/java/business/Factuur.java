package business;

import java.util.Date;
import java.util.HashSet;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class Factuur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "factuur_id")
	private long id;
	
	@Column(name = "factuurnummer")
	private String factuurNummer;
	
	@Column
	private Date factuurDatum;

	@OneToMany(mappedBy = "factuur", targetEntity = Betaling.class,  
			fetch = FetchType.LAZY) 
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	public Set<Betaling> betalingSet = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "bestelling_id") 
	private Bestelling bestelling;
	
	@ManyToOne
	@JoinColumn(name = "klant_id")
	private Klant klant;
	
	public Factuur(){
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
    	this.id = id;
		
	}
		
    public String getFactuurNummer() {
    	return factuurNummer;
    }     
    
    
	public void setFactuurNummer() {
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
		factuurNummer = result.toString();           
		System.out.println("factuurnummer is: " + factuurNummer);
	}  
    
    
    
    public Date getFactuurDatum() {
    	return factuurDatum;
    }
   
    public void setFactuurDatum() {
    	factuurDatum = new Date();
    }
    
    public Set<Betaling> getBetalingSet() {
    	return betalingSet;
    }
    
    public void setBetalingSet(Set<Betaling> betalingSet) {
    	this.betalingSet = betalingSet;
    }
 
    public Bestelling getBestelling() {
    	return bestelling;
    }
    
    public void setBestelling(Bestelling bestelling) {
    	this.bestelling = bestelling;
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
        "\nFactuur id : " + id + ". factuurnummer : " + factuurNummer + 
        "\n-----------------------------------------------------------------" +
        "\nFactuur datum: " + factuurDatum +
        "\nBestelling: " + bestelling + 
        "\tBetalingen: " + betalingSet +   
       "\n\n";
            			    			   			
    }      
    
    /* @Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.factuurNummer);
	        hash = 67 * hash + Objects.hashCode(this.bestelling);
	        hash = 67 * hash + Objects.hashCode(this.betalingSet);
	        return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Factuur other = (Factuur) obj;
        if (!Objects.equals(this.factuurNummer, other.factuurNummer)) {
            return false;
        }
        if (!Objects.equals(this.bestelling, other.bestelling)) {
            return false;
        }
        if (!Objects.equals(this.betalingSet, other.betalingSet)) {
           return false;
        }
        if (!Objects.equals(this.factuurDatum, other.factuurDatum)){
        	return false;
        }
        return true;
	} */
}

/*De servicelaag berekent de totaalprijs, inc/ex btw en per artikel 
etc.*/