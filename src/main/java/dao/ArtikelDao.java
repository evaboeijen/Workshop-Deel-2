package dao;

import java.util.*;

import business.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	

public class ArtikelDao implements AccountDaoInterface<Artikel, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(ArtikelDao.class);
	
	private Session currentSession;
	private Transaction currentTransaction;

	public ArtikelDao() {
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
			/*Properties properties = new Properties();
			
			properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			properties.put("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
			properties.put("hibernate.connection.url", "jdbc:mysql://localhost/workshopdeel2");
			properties.put("hibernate.connection.username", "root");
			properties.put("hibernate.connection.password", "JaRsvier15");
			
			return new Configuration()
						.addProperties(properties)
						.addAnnotatedClass(Klant.class)
						.buildSessionFactory(
								new StandardServiceRegistryBuilder()
										.applySettings(properties)
										.build()
			);*/

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
	
		public void persist(Artikel entity) {
			getCurrentSession().save(entity);
		}

	    public void update(Artikel entity) {
			getCurrentSession().update(entity);
		}
	
		public Artikel findById(Long id) {
			Artikel artikel = (Artikel) getCurrentSession().get(Artikel.class, id);
			return artikel;
		}
	
		public void delete(Artikel entity) {
			getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<Artikel> findAll() {
			List<Artikel> artikellen = (List<Artikel>) getCurrentSession().createQuery("from Artikel").list();
			return artikellen;
		}
	
		public void deleteAll() {
			List<Artikel> entityList = findAll();
			
			for (Artikel entity : entityList) {
				delete(entity);
			}
		}


		
	}


