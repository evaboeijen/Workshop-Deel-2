package business;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Betaalwijze {

	@Id
	@Column(name = "betaalwijze_id")
	private long id;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "betaalwijze")
	private BetaalwijzeType type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BetaalwijzeType getType() {
		return type;
	}

	public void setType(BetaalwijzeType type) {
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
		final Betaalwijze other = (Betaalwijze) obj;
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
		return "Betaalwijze {" + "id=" + id + ", type=" + type + '}';
	}
}

