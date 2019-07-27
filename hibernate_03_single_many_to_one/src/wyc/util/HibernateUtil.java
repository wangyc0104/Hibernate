package wyc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate工具类：封装Session的生成方法 <br>
 * @author Yicheng Wang
 */
public class HibernateUtil {
	
	private static Configuration cfg = null;
	private static ServiceRegistry registry = null;
	private static SessionFactory factory = null;
	static {
		cfg = new Configuration().configure();
		registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		factory = cfg.buildSessionFactory(registry);
	}

	/**
	 * 获取Session
	 * @return
	 */
	public static Session getSession() {
		return factory.openSession();
	}
	
}
