package business;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table
public class Betaling implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "betaling_id")
	private long id;
	
	@Column
	private Date betaalDatum;
	
	@ManyToOne
	@JoinColumn(name = "betaalwijze_id")
	private Betaalwijze betaalwijze;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "klant_id")
	private Klant klant;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "factuur_id")
	private Factuur factuur;
	
	@Column
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
	
	public Betaalwijze getBetaalwijze(){
		return betaalwijze;
	}
	
	public void setBetaalwijze(Betaalwijze betaalwijze){
		this.betaalwijze = betaalwijze;
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
			   "\n betalingswijze: " + betaalwijze +
			   "\n van klant: " + klant +
			   "\n met betalinggegevens: " + betalingsGegevens;
	}
	
	@Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.betaalwijze);
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
        if (!Objects.equals(this.betaalwijze, other.betaalwijze)) {
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
