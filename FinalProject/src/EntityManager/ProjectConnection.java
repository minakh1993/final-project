package EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProjectConnection {

	private static Configuration cfg;
	private static SessionFactory factory;
	private static ProjectConnection instance;

	private ProjectConnection() {
		
		cfg = new Configuration().configure("hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
	}

	public static SessionFactory getConnection() {
		if (instance == null) {
			instance = new ProjectConnection();
			
		}
		return factory;
	}

}
