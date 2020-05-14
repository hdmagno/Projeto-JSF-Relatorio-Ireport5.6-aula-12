package persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	static {
		try {
			Configuration cfg = new Configuration().configure("META-INF/hibernate.cfg.xml");
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
			sessionFactory = cfg.buildSessionFactory(builder.build());
		} catch (HibernateException e) {
			System.err.println("Initial creation SessionFactory failed " + e.getMessage());
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void closeSessionFactory() {
		sessionFactory.close();
	}

}
