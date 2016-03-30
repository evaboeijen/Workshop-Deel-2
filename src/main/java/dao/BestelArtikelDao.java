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
	

public class BestelArtikelDao implements BestelArtikelDaoInterface<BestelArtikel, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(BestelArtikelDao.class);
	
	private Session currentSession;
	private Transaction currentTransaction;

	public BestelArtikelDao() {
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
	
		public void persist(BestelArtikel entity) {
			getCurrentSession().save(entity);
		}

	    public void update(BestelArtikel entity) {
			getCurrentSession().update(entity);
		}
	
		public BestelArtikel findById(Long id) {
			BestelArtikel bestellingHasArtikel = (BestelArtikel) getCurrentSession().get(BestelArtikel.class, id);
			return bestellingHasArtikel;
		}
	
		public void delete(BestelArtikel entity) {
			getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<BestelArtikel> findAll() {
			List<BestelArtikel> bestellingHasArtikel = (List<BestelArtikel>) getCurrentSession().createQuery("from BestelArtikel").list();
			return bestellingHasArtikel;
		}
	
		public void deleteAll() {
			List<BestelArtikel> entityList = findAll();
			
			for (BestelArtikel entity : entityList) {
				delete(entity);
			}
		}


		
	}

