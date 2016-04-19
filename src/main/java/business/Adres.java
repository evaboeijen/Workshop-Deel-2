package business;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Entity
@Table
public class Adres implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adres_id")
	private long id;
	
	@Column
	private String straatnaam;
	
	@Column
	private String postcode;
	
	@Column
	private String toevoeging;
	
	@Column
	private int huisnummer;
	
	@Column
	private String woonplaats;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adrestype_id")
	private AdresType adresType;

	@OneToMany(mappedBy = "adres",targetEntity = KlantAdres.class,  
			fetch = FetchType.EAGER) 
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	public Set<BestelArtikel> bestellingHasArtikelen = new HashSet<>();
	
	public Adres(){}
	
	/*public Adres(String straatnaam, String postcode, String toevoeging, int huisnummer, String woonplaats ){
	//this.klant_id   = klant_id;
	this.straatnaam = straatnaam;
	this.postcode   = postcode;
	this.toevoeging = toevoeging;
	this.huisnummer = huisnummer;
	this.woonplaats = woonplaats;
	}
	
	public Adres(long id) {
		
	}*/
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getStraatnaam() {
		return straatnaam;
	}
	public void setStraatnaam(String straatnaam) {
		this.straatnaam = straatnaam;
	}
	
	
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
	public String getToevoeging() {
		return toevoeging;
	}
	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}
	
	
	public int getHuisnummer() {
		return huisnummer;
	}
	public void setHuisnummer(int huisnummer) {
		this.huisnummer = huisnummer;
	}
	
	
	public String getWoonplaats() {
		return woonplaats;
	}
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
	
	
	public AdresType getAdresType(){
		return adresType;
	}
	
	public void setAdresType(AdresType adrestype){
		this.adresType = adrestype;
	}

	@Override
	public String toString() {
		return  "\nAdres id: "   + getId() +
				"\nStraatnaam: " 	+ getStraatnaam() + 
				"\nPostcode: " 		+ getPostcode() + 
				"\nToevoeging: " 	+ getToevoeging() + 
				"\nHuisnummer: " 	+ getHuisnummer() + 
				"\nWoonplaats: " 	+ getWoonplaats() + 
				"\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresType == null) ? 0 : adresType.hashCode());
		result = prime * result + huisnummer;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((straatnaam == null) ? 0 : straatnaam.hashCode());
		result = prime * result + ((toevoeging == null) ? 0 : toevoeging.hashCode());
		result = prime * result + ((woonplaats == null) ? 0 : woonplaats.hashCode());
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
		Adres other = (Adres) obj;
		if (adrestype == null) {
			if (other.adrestype != null)
				return false;
		} else if (!adrestype.equals(other.adrestype))
			return false;
		if (huisnummer != other.huisnummer)
			return false;
		if (id != other.id)
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (straatnaam == null) {
			if (other.straatnaam != null)
				return false;
		} else if (!straatnaam.equals(other.straatnaam))
			return false;
		if (toevoeging == null) {
			if (other.toevoeging != null)
				return false;
		} else if (!toevoeging.equals(other.toevoeging))
			return false;
		if (woonplaats == null) {
			if (other.woonplaats != null)
				return false;
		} else if (!woonplaats.equals(other.woonplaats))
			return false;
		return true;
	}
	
	/* @Override
	public int hashCode(){
			int hash = 7;
	       //hash = 89  hash + (this.name != null ? this.name.hashCode() : 0);
	        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
	        hash = 67 * hash + Objects.hashCode(this.straatnaam);
	        hash = 67 * hash + Objects.hashCode(this.postcode);
	        hash = 67 * hash + Objects.hashCode(this.toevoeging);
	        hash = 67 * hash + this.huisnummer;
	        hash = 67 * hash + Objects.hashCode(this.woonplaats);
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
        final Adres other = (Adres) obj;
        if (!Objects.equals(this.straatnaam, other.straatnaam)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.toevoeging, other.toevoeging)) {
           return false;
        }
        if (!Objects.equals(this.huisnummer, other.huisnummer)){
        	return false;
        }
        if (!Objects.equals(this.woonplaats, other.woonplaats)){
        	return false;
        }
        return true;
	} */
}
