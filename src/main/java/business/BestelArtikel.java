package business;

import java.util.Objects;

import javax.persistence.*;

import java.io.Serializable;

@Entity
public class BestelArtikel implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bestelartikel_id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "artikel_id")
	private Artikel artikel;
	
	@ManyToOne
	@JoinColumn(name = "bestelling_id")
	private Bestelling bestelling;
	
	@Column
	private int aantal;
	
	public BestelArtikel(){
	}
	
	public long setId(){
		return id;
	}
	
	public void getId(long id){
		this.id = id;
	}
	
	public Artikel getArtikel(){
		return artikel;
	}
	
	public void setArtikel(Artikel artikel){
		this.artikel = artikel;
	}
	
	public Bestelling getBestelling(){
		return bestelling;
	}
	
	public void setBestelling(Bestelling bestelling){
		this.bestelling = bestelling;
	}
	
	public int getAantal(){
		return aantal;
	}
	
	public void setAantal(int aantal){
		this.aantal = aantal;
	}
	
	@Override
	public String toString(){
		return "/nBestellingArtikel id: " + id +
			   "/n van bestelling: " + bestelling + 
			   "/n met artikel: " + artikel + 
			   "/n met aantal: " + aantal;
	}
	
	@Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.bestelling);
	        hash = 67 * hash + Objects.hashCode(this.artikel);
	        hash = 67 * hash + this.aantal;
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
        final BestelArtikel other = (BestelArtikel) obj;
        if (!Objects.equals(this.bestelling, other.bestelling)) {
            return false;
        }
        if (!Objects.equals(this.artikel, other.artikel)) {
            return false;
        }
        if (!Objects.equals(this.aantal, other.aantal)) {
           return false;
        }
        return true;
	}
}

/*Omdat de Set interface gebruik wordt in Bestelling kun je niet 
meerdere Artikel van hetzelfde erin kwijt en daar aantallen mee 
bijhouden. Het is ook beknopter om een specifiek BestelArtikel 
op te nemen met de aantallen voor de bestelling, anders zou je 
honderden van een bepaald artikel in een lijst kunnen hebben. 
Op deze manier houd je de verantwoordelijkheden ook 
gescheiden van Artikel en BestelArtikel. De 
verantwoordelijkheid is van Artikel, de informatie betreffende 
het artikel verschaffen, van BestelArtikel de informatie nodig 
voor een bestelling te verschaffen.*/