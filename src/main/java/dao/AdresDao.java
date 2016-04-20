package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.Account;
import business.Adres;
import business.AdresType;
import business.Artikel;
import business.BestelArtikel;
import business.Bestelling;
import business.Betaalwijze;
import business.Betaling;
import business.Factuur;
import business.Klant;
import business.KlantAdres;


public class AdresDao implements AdresDaoInterface<Adres, Long> {
private static final Logger logger =  LoggerFactory.getLogger(AdresDao.class);
private Session currentSession;
private Transaction currentTransaction;

public AdresDao(){}

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
 public void persist(Adres entity) {
	getCurrentSession().save(entity);
	
}

@Override
public void update(Adres entity) {
	getCurrentSession().update(entity);
	
}

@Override
public Adres findById(Long id) {
	logger.info("Adres.findByID method starts");
	Adres adres = (Adres) getCurrentSession().get(Adres.class, id);
	logger.info("Klant.findByID method about to end");
	return adres;
}

@Override
public void delete(Adres entity) {
	getCurrentSession().delete(entity);
	
}

@Override
@SuppressWarnings("unchecked")
public List<Adres> findAll() {
	List<Adres> adressen = (List<Adres>) getCurrentSession().createQuery("from Adres").list();
	return adressen;
}

@Override
public void deleteAll() {
	List<Adres> entityList = findAll();
	
	for (Adres entity : entityList) {
		delete(entity);
	
}
			
}
}
