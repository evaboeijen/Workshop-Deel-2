package dao;

import java.sql.*;
import java.util.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import business.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	

public class GenericDao<T> implements GenericDaoInterface<T, Long> {

	private static final Logger logger =  LoggerFactory.getLogger(GenericDao.class);
	
	private Session currentSession;
	private Transaction currentTransaction;
	private Class<T> clazz;

	public GenericDao() {
		this.clazz =(Class<T>)((ParameterizedType)this.getClass()
				.getGenericSuperclass())
				.getActualTypeArguments()[0];
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
	
		public void createOrUpdate(T entity){
			getCurrentSession().saveOrUpdate(entity);
		}
	
		public T findById(Long id) {
			logger.info(clazz.getName() + "findByID method starts");
			String naam = clazz.getName();
			T entity = (T)getCurrentSession().get(clazz, id);
			logger.info(clazz.getName()+ "Klant.findByID method about to end");
			return entity;
		}
	
		public void delete(T entity) {
			getCurrentSession().delete(entity);
		}

		@SuppressWarnings("unchecked")
		public List<T> findAll() {
			List<T> entities = (List<T>) getCurrentSession().createQuery("from " + clazz.getName()).list();
			return entities;
		}
	
		public void deleteAll() {
			List<T> entityList = findAll();
			
			for (T entity : entityList) {
				delete(entity);
			}
		}


		
	}

