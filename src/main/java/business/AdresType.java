package business;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public enum AdresType{
		Postadres,
		Factuuradres,
		Bezoekadres
	}