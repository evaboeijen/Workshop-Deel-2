package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.*;

public class KlantAdresDao implements KlantAdresDaoInterface<KlantAdres, Long>{
	private static final Logger logger =  LoggerFactory.getLogger(KlantAdresDao.class);
	private Session currentSession;
	private Transaction currentTransaction;

	public KlantAdresDao(){}

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
		properties.put("hibernate.connection.password", "Ja");
		
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

	@Override
	public void persist(KlantAdres entity) {
		getCurrentSession().save(entity);
		
	}

	@Override
	public void update(KlantAdres entity) {
		getCurrentSession().update(entity);
		
	}

	@Override
	public KlantAdres findById(Long id) {
		logger.info("KlantAdres.findByID method starts");
		KlantAdres klantAdres = (KlantAdres) getCurrentSession().get(KlantAdres.class, id);
		logger.info("KlantAdres.findByID method about to end");
		return klantAdres;
	}

	@Override
	public void delete(KlantAdres entity) {
		getCurrentSession().delete(entity);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<KlantAdres> findAll() {
		List<KlantAdres> klantAdressen = (List<KlantAdres>) getCurrentSession().createQuery("from KlantAdres").list();
		return klantAdressen;
	}

	@Override
	public void deleteAll() {
		List<KlantAdres> entityList = findAll();
		
		for (KlantAdres entity : entityList) {
			delete(entity);
		
	}
				
	}
	}
