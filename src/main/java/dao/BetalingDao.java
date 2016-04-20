package dao;

import java.sql.*;
import java.util.*;

import business.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	

public class BetalingDao implements BetalingDaoInterface<Betaling, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(BetalingDao.class);
	
	private Session currentSession;
	private Transaction currentTransaction;

	public BetalingDao() {
	}
	
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
		}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
		}

		public void closeCurrentSession() {
			currentSession.close();
		}
	
		public void closeCurrentSessionwithTransaction() {
			currentTransaction.commit();
			currentSession.close();
		}
	
		private static SessionFactory getSessionFactory() {
		

			Configuration configuration = new Configuration()
					.addAnnotatedClass(Klant.class)
					.addAnnotatedClass(Account.class)
					.addAnnotatedClass(Bestelling.class)
					.addAnnotatedClass(Factuur.class)
					.addAnnotatedClass(Adres.class)
					.addAnnotatedClass(AdresType.class)
					.addAnnotatedClass(BestelArtikel.class)
					.addAnnotatedClass(Betaalwijze.class)
					.addAnnotatedClass(Betaling.class)
					.addAnnotatedClass(Artikel.class)
					.addAnnotatedClass(KlantAdres.class)
					.configure();
			
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
			
			SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
			return sessionFactory;
		}

		public Session getCurrentSession() {
			return currentSession;
		}
	
		public void setCurrentSession(Session currentSession) {
			this.currentSession = currentSession;
		}

		public Transaction getCurrentTransaction() {
			return currentTransaction;
		}
	
		public void setCurrentTransaction(Transaction currentTransaction) {
			this.currentTransaction = currentTransaction;
		}
	
		public void persist(Betaling entity) {
			getCurrentSession().save(entity);
		}

	    public void update(Betaling entity) {
			getCurrentSession().update(entity);
		}
	
		public Betaling findById(Long id) {
			Betaling betaling = (Betaling) getCurrentSession().get(Betaling.class, id);
			return betaling;
		}
	
		public void delete(Betaling entity) {
			getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<Betaling> findAll() {
			List<Betaling> betaling = (List<Betaling>) getCurrentSession().createQuery("from Betaling").list();
			return betaling;
		}
	
		public void deleteAll() {
			List<Betaling> entityList = findAll();
			
			for (Betaling entity : entityList) {
				delete(entity);
			}
		}


		
	}

