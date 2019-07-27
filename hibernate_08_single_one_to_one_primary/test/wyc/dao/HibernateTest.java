package wyc.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import wyc.pojo.IdCard;
import wyc.pojo.User;
import wyc.util.HibernateUtil;

/**
 * 测试基于主键的单向一对一
 * @author Yicheng Wang
 */
public class HibernateTest {
	
	/**
	 * 建表
	 */
	@Test
	public void testCreateDB() {
		Configuration cfg = new Configuration().configure();
		SchemaExport se = new SchemaExport(cfg);
		// 第一个参数 是否打印脚本，第二个参数是否导入数据库
		se.create(true, true);
	}

	/**
	 * 初始化
	 */
	@Test
	public void testInit() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			IdCard idCard = new IdCard();
			idCard.setId("111111111111111111");
			idCard.setAddress("北京");
			IdCard idCard1 = new IdCard();
			idCard1.setId("222222222222222222");
			idCard1.setAddress("上海");
			session.save(idCard);
			session.save(idCard1);

			User u1 = new User("大诚", 26, idCard);
			User u2 = new User("小诚", 25, idCard1);
			session.save(u1);
			session.save(u2);
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
	 * 查询（由于主键的策略是绑定外键，因此查询时需要拿id作为主键）
	 */
	@Test
	public void testGet() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, "111111111111111111");
			System.out.println("姓名：" + user.getName() + "  身份证：" + user.getIdCard().getId() + " 地址：" + user.getIdCard().getAddress());
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
