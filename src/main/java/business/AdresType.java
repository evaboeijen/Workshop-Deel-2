package business;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class AdresType {

	@Id
	@Column(name = "adrestype_id")
	private long id;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "adrestype")
	private AdresTypeType type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AdresTypeType getType() {
		return type;
	}

	public void setType(AdresTypeType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
		hash = 23 * hash + Objects.hashCode(this.type);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AdresType other = (AdresType) obj;
		if (this.id != other.id) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
			return true;
		}

	@Override
	public String toString() {
		return "AdresType{" + "id=" + id + ", type=" + type + '}';
	}
}
