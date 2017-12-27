package hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.model.hibernate.Account;

public class TransactionalTest {
	public static SessionFactory sessionFactory = null;

	@BeforeClass
	public static void beforeClass() {
		try {
			// 方式一 读取hibernate.cfg.xml文件
			// sf = new Configuration().configure().buildSessionFactory();

			// 方式二 读取
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"classpath:springmvc/spring-hibernate.xml");
			sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	// 增
	public void createAnAccount() {

		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			Account account = new Account();
			account.setName("Yoyo");

			session.save(account);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	// 删
	public void deleteAnAccount() {

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Account account = session.load(Account.class, 3);
			session.delete(account);
			tx = session.getTransaction();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	// 改

	public void setAnAccount() {

		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Account account = session.load(Account.class, 4);
			account.setName("GUGU");
			account.setMoney(11.88);
			session.update(account);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	// 查询所有用户
	public void getAccountList() {

		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<Account> accounts = session.createQuery("FROM Account").list();

			for (Iterator iterator = accounts.iterator(); iterator.hasNext();) {
				Account account = (Account) iterator.next();
				System.out.print("First Name: " + account.getId());
				System.out.print("  Last Name: " + account.getName());
				System.out.println("  Salary: " + account.getMoney());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

}
