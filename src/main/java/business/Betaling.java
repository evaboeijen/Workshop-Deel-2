package business;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

public class Betaling implements Serializable{

	private long id;
	private Date betaalDatum;
	private Betaalwijze betalingswijze;
	private Klant klant;
	private Factuur factuur;
	private String betalingsGegevens;
	
	
	public Betaling(){
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Date getBetaalDatum(){
		return betaalDatum;
	}
	
	public void setBetaalDatum(Date betaalDatum){
		this.betaalDatum = betaalDatum;
	}
	
	public Betaalwijze getBetalingswijze(){
		return betalingswijze;
	}
	
	public void setBetalingswijze(Betaalwijze betalingswijze){
		this.betalingswijze = betalingswijze;
	}
	
	public Klant getKlant(){
		return klant;
	}
	
	public void setKlant(Klant klant){
		this.klant = klant;
	}
	
	public String getBetalingsGegevens(){
		return betalingsGegevens;
	}
	
	public void setBetalingsGegevens(String betalingsGegevens){
		this.betalingsGegevens = betalingsGegevens;
	}
	
	@Override
	public String toString(){
		return "\n betaling id: " + id +
			   "\n datum van betaling: " + betaalDatum +
			   "\n betalingswijze: " + betalingswijze +
			   "\n van klant: " + klant +
			   "\n met betalinggegevens: " + betalingsGegevens;
	}
	
	@Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.betalingswijze);
	        hash = 67 * hash + Objects.hashCode(this.klant);
	        hash = 67 * hash + Objects.hashCode(this.betalingsGegevens);
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
        final Betaling other = (Betaling) obj;
        if (!Objects.equals(this.betalingswijze, other.betalingswijze)) {
            return false;
        }
        if (!Objects.equals(this.klant, other.klant)) {
            return false;
        }
        if (!Objects.equals(this.betalingsGegevens, other.betalingsGegevens)) {
           return false;
        }
        if (!Objects.equals(this.betaalDatum, other.betaalDatum)){
        	return false;
        }
        return true;
	}
}
