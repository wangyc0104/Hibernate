package wyc.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import wyc.pojo.Role;
import wyc.pojo.User;
import wyc.util.HibernateUtil;

/**
 * 测试双向一对多
 * @author Yicheng Wang
 */
public class HibernateTest {
	
	/**
	 * 创建数据库表
	 */
	@Test
	public void testCreateDB() {
		Configuration cfg = new Configuration().configure();
		SchemaExport se = new SchemaExport(cfg);
		// 第一个参数 是否打印脚本，第二个参数是否导入数据库
		se.create(true, true);
	}

	/**
	 * 初始化数据
	 */
	@Test
	public void testInit() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();

			Role r1 = new Role("岛主");
			Role r2 = new Role("峰主");
			session.save(r1);
			session.save(r2);

			User u1 = new User();
			u1.setName("黄药师");
			u1.setAge(66);
			u1.setRole(r1);
			User u2 = new User();
			u2.setName("西毒");
			u2.setAge(56);
			u2.setRole(r2);
			User u3 = new User();
			u3.setName("郭襄");
			u3.setAge(63);
			u3.setRole(r1);
			session.save(u1);
			session.save(u2);
			session.save(u3);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 测试查询
	 */
	@Test
	public void testGet() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, 1);
			System.out.println("姓名：" + user.getName() + "  角色：" + user.getRole().getName());

			System.out.println("=====================");
			Role role = (Role) session.get(Role.class, 1);
			System.out.println("角色名：" + role.getName());
			System.out.println("--------------");
			for (User u : role.getUsers()) {
				System.out.println(u);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) {

				session.close();
			}
		}
	}
	
}
