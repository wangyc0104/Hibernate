package wyc.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import wyc.pojo.IdCard;
import wyc.pojo.User;
import wyc.util.HibernateUtil;

/**
 * 测试双向一对一（基于外键）
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

			User u1 = new User("大诚", 26);
			u1.setIdCard(idCard);
			User u2 = new User("小诚", 25);
			u2.setIdCard(idCard1);

			session.save(idCard);
			session.save(idCard1);
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
	 * 查询（由于主键的指派方式不一样，因此主控端和被控端的查询主键不一样）
	 */
	@Test
	public void testGet() {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, 1);
			System.out.println("姓名：" + user.getName() + "  身份证：" + user.getIdCard().getId());
			System.out.println("===================");
			IdCard card = (IdCard) session.get(IdCard.class, "111111111111111111");
			System.out.println("身份证：" + card.getId() + "  地址：" + card.getAddress() + "  姓名：" + card.getUser().getName());
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
