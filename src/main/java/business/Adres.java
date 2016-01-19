package business;

import java.util.Objects;

public class Adres {
	
	private long id;
	private String straatnaam;
	private String postcode;
	private String toevoeging;
	private int huisnummer;
	private String woonplaats;
	private AdresType adresType;

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
	
	public void setAdresType(AdresType adresType){
		this.adresType = adresType;
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
	}
}
