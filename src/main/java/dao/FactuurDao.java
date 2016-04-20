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
	

public class FactuurDao implements FactuurDaoInterface<Factuur, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(FactuurDao.class);
	
	private Session currentSession;
	private Transaction currentTransaction;

	public FactuurDao() {
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
	
		public void persist(Factuur entity) {
			getCurrentSession().save(entity);
		}

	    public void update(Factuur entity) {
			getCurrentSession().update(entity);
		}
	
		public Factuur findById(Long id) {
			Factuur factuur = (Factuur) getCurrentSession().get(Factuur.class, id);
			return factuur;
		}
	
		public void delete(Factuur entity) {
			getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<Factuur> findAll() {
			List<Factuur> factuur = (List<Factuur>) getCurrentSession().createQuery("from Factuur").list();
			return factuur;
		}
	
		public void deleteAll() {
			List<Factuur> entityList = findAll();
			
			for (Factuur entity : entityList) {
				delete(entity);
			}
		}


		
	}

