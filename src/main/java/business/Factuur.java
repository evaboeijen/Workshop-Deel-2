package business;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Factuur {

	private long id;
	private String factuurNummer;
	private Date bestelDatum;
	private Set<Betaling> betalingSet;
	private Bestelling bestelling;
	
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
    
    public void setFactuurNummer(String factuurNummer) {
    	this.factuurNummer = factuurNummer;
    }  
    
    public Date getBestelDatum() {
    	return bestelDatum;
    }
   
    public void setBEstelDatum(Date bestelDatum) {
    	this.bestelDatum = bestelDatum;
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
    
    @Override
    public String toString(){
    	return 	 
        "\nFactuur id : " + id + ". factuurnummer : " + factuurNummer + 
        "\n-----------------------------------------------------------------" +
        "\nBestel datum: " + bestelDatum +
        "\nBestelling: " + bestelling + 
        "\tBetalingen: " + betalingSet +   
       "\n\n";
            			    			   			
    }      
    
    @Override
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
        if (!Objects.equals(this.bestelDatum, other.bestelDatum)){
        	return false;
        }
        return true;
	}
}

/*De servicelaag berekent de totaalprijs, inc/ex btw en per artikel 
etc.*/