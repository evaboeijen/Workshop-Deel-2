package business;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table
public class Betaling implements Serializable{
	
	public enum Betaalwijze {
		Contant,
		Pinbetaling,
		IDeal,
		Creditcard
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "betaling_id")
	private long id;
	
	@Column
	private Date betaalDatum;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "betaalwijze")
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
	
	public void setBetaalDatum(){
		Date tempBetaalDatum = new Date() ;
		betaalDatum = new Date() ;
		betaalDatum.setTime(tempBetaalDatum.getTime() + 7 * 24 * 60 * 60 * 1000);	          
		System.out.println("betaalDatum is: " + betaalDatum);
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
	
	public void setKlant(Klant klant){		this.klant = klant;
	}
	
	public String getBetalingsGegevens(){
		return betalingsGegevens;
	}
	
	public void setBetalingsGegevens(String betalingsGegevens){
		this.betalingsGegevens = betalingsGegevens;
	}

	public Factuur getFactuur() {
		return factuur;
	}

	public void setFactuur(Factuur factuur) {
		this.factuur = factuur;
	}

	@Override
	public String toString(){
		return "\n betaling id: " + id +
			   "\n datum van betaling: " + betaalDatum +
			   "\n betalingswijze: " + betaalwijze +
			   "\n van klant: " + klant +
			   "\n met betalinggegevens: " + betalingsGegevens;
	}

	/* @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((betaalDatum == null) ? 0 : betaalDatum.hashCode());
		result = prime * result + ((betaalwijze == null) ? 0 : betaalwijze.hashCode());
		result = prime * result + ((betalingsGegevens == null) ? 0 : betalingsGegevens.hashCode());
		result = prime * result + ((factuur == null) ? 0 : factuur.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((klant == null) ? 0 : klant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Betaling other = (Betaling) obj;
		if (betaalDatum == null) {
			if (other.betaalDatum != null)
				return false;
		} else if (!betaalDatum.equals(other.betaalDatum))
			return false;
		if (betaalwijze != other.betaalwijze)
			return false;
		if (betalingsGegevens == null) {
			if (other.betalingsGegevens != null)
				return false;
		} else if (!betalingsGegevens.equals(other.betalingsGegevens))
			return false;
		if (factuur == null) {
			if (other.factuur != null)
				return false;
		} else if (!factuur.equals(other.factuur))
			return false;
		if (id != other.id)
			return false;
		if (klant == null) {
			if (other.klant != null)
				return false;
		} else if (!klant.equals(other.klant))
			return false;
		return true;
	} */
	
}
