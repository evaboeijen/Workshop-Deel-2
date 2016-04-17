package dao;

import java.io.Serializable;
import java.util.List;

import business.KlantAdres;

public interface KlantAdresDaoInterface <T, Id extends Serializable>  {
		public void persist(T entity);
	    public void update(T entity);
	    public T findById(Id id);
	    public void delete(T entity);
	    public List<T> findAll();
	    public void deleteAll();
		public List<KlantAdres> findByKlant_Id(Long klant_id);
		public List<KlantAdres> findByAdres_Id(Long adres_id);
}