package mm.com.gic.ces.base.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;



@SuppressWarnings({ "unused", "deprecation" })
public class HibernateUtil {

	private static final SessionFactory sessionFactory;	
	static {
		
		try {

			Configuration configuration = new Configuration().configure();
			ServiceRegistryBuilder builder = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			sessionFactory = new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);

		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}