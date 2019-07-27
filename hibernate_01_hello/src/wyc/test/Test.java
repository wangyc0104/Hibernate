package wyc.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import wyc.pojo.User;

/**
 * ����Hibernate��Session����
 * @author Yicheng Wang
 */
public class Test {
	public static void main(String[] args) {
		// ��ȡsrc��hibernate.cfg.xml �����Ϊconfigureָ������ Ĭ�϶�ȡhibernate.cfg.xml
		Configuration cfg = new Configuration().configure();
		// 3.x�汾����ҪServiceRegistry
		// 4.0 ServiceRegistryBuilder
		// ��ȡע�����4.3�Ĵ����취
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		// SessionFactory��һ������������ session�Ĺ��� ���������ǽ��̼���� ֧�ּ�Ⱥ �̰߳�ȫ��
		SessionFactory factory = cfg.buildSessionFactory(registry);
		/**
		 * Session (org.hibernate.Session) ��ʾӦ�ó�����־ô����֮�佻��������һ�����̶߳��󣬴˶��������ں̣ܶ�<br>
		 * Session��������JDBC���ӣ�Ҳ��Transaction�Ĺ�����<br>
		 * Session�����һ����Գ־û�����ı�ѡ����һ�������棻<br>
		 * �ڱ�������ͼ���߸��ݳ־û���ʶ���Ҷ���ʱ���õ� session֧�����ݿ������<br>
		 */
		Session session = null;
		// �������
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			User u = new User("С��", 22);
			// ��������
			session.save(u);
			// �ύ����
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				// �ع�����
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		factory.close();
	}
}
