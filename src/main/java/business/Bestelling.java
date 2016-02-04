package business;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table
public class Bestelling {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bestelling_id")
	private long id;
	
	@Column(name = "bestelnummer")
	private String bestelNummer;
	
	@Column
	private Date bestelDatum;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="bestelArtikel_id")
	private Set<BestelArtikel> bestelArtikelSet;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="factuurNummer")
	private Set<Factuur> factuurSet;
	
	@ManyToOne
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
    
    public void setBestelNummer(String bestelNummer) {
    	this.bestelNummer = bestelNummer;
    }  
    
    public Date getBestelDatum() {
    	return bestelDatum;
    }
   
    public void setBEstelDatum(Date bestelDatum) {
    	this.bestelDatum = bestelDatum;
    }
    
    public Set<BestelArtikel> getBestelArtikelSet() {
    	return bestelArtikelSet;
    }
    
    public void setBestelArtikelSet(Set<BestelArtikel> bestelArtikelSet) {
    	this.bestelArtikelSet = bestelArtikelSet;
    }
 
    public Set<Factuur> getFactuurSet() {
    	return factuurSet;
    }
    
    public void setFactuurSet(Set<Factuur> factuurSet) {
    	this.factuurSet = factuurSet;
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
        "\tArtikellen: " + bestelArtikelSet +   
       "\n\n";
            			    			   			
    } 
    
    @Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.bestelNummer);
	        hash = 67 * hash + Objects.hashCode(this.bestelArtikelSet);
	        hash = 67 * hash + Objects.hashCode(this.factuurSet);
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
        final Bestelling other = (Bestelling) obj;
        if (!Objects.equals(this.bestelNummer, other.bestelNummer)) {
            return false;
        }
        if (!Objects.equals(this.bestelArtikelSet, other.bestelArtikelSet)) {
            return false;
        }
        if (!Objects.equals(this.factuurSet, other.factuurSet)) {
           return false;
        }
        if (!Objects.equals(this.bestelDatum, other.bestelDatum)){
        	return false;
        }
        return true;
	}
}


    
