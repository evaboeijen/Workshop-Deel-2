package main;


import business.*;
import service.*;

import java.util.List;
/*import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestHibernate { 
	
	private static final Logger logger =  LoggerFactory.getLogger(TestHibernate.class);
	
		public static void main(String[] args){
		
			KlantDaoService service = new KlantDaoService();
			Klant klant1 = new Klant();
			klant1.setVoornaam("Hibernate");
			klant1.setAchternaam("Test");
			klant1.setEmail("hibernate@email.com");
			
			
			System.out.println("*** Persist - start ***");
			
			service.persist(klant1);
			
			List<Klant> klanten = service.findAll();
			
			System.out.println("Books Persisted are :");
			
			for (Klant k : klanten) {
			System.out.println("-" + k.toString());
			}

		}
}
		/*Klant klant = new Klant();
		klant.setVoornaam("Hibernate");
		klant.setAchternaam("Test");
		klant.setEmail("hibernate@email.com");
		
		logger.info("nieuwe klant aangemaakt: " + klant);
		
		Properties props = new Properties();//kunnen ook van properties bestanden gelezen worden
		props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		props.put("hibernate.connection.url", "jdbc:mysql://localhost:3308/workshopdeel2");
		props.put("hibernate.connection.username", "root");
		props.put("hibernate.connection.password", "JaRsvier15");
		props.put("hibernate.transaction.factory_class",
		"org.hibernate.transaction.JDBCTransactionFactory");
		props.put("hibernate.current_session_context_class", "thread");
		
		
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().applySettings(props).build();
		
				Metadata metadata = new MetadataSources(standardRegistry)
						.addAnnotatedClass(Klant.class)
						.getMetadataBuilder().build();
				
				SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
				Session session = sessionFactory.openSession();
				
				session.beginTransaction();
				session.save(klant);
				session.getTransaction().commit();
	}
}*/

