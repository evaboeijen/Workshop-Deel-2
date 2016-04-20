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
	

public class AccountDao implements AccountDaoInterface<Account, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(AccountDao.class);
	
	private Session currentSession;
	private Transaction currentTransaction;

	public AccountDao() {
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
	
		public void persist(Account entity) {
			getCurrentSession().saveOrUpdate(entity);
		}

	    public void update(Account entity) {
			getCurrentSession().update(entity);
		}
	
		public Account findById(Long id) {
			Account account = (Account) getCurrentSession().get(Account.class, id);
			return account;
		}
	
		public void delete(Account entity) {
			getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<Account> findAll() {
			List<Account> accounten = (List<Account>) getCurrentSession().createQuery("from Account").list();
			return accounten;
		}
	
		public void deleteAll() {
			List<Account> entityList = findAll();
			
			for (Account entity : entityList) {
				delete(entity);
			}
		}


		
	}


