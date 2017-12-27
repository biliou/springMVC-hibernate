package com.example.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.example.dao.BaseHibernateDao;
import com.example.model.hibernate.Account;

@Repository
public class AccountDao2 extends BaseHibernateDao {

	/**
	 * 增 新建一个用户信息
	 */
	public boolean createAnAccount(String name) {

		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();

			Account account = new Account();
			account.setName(name);
			System.out.println(session.save(account));
			session.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	/**
	 * 删 删除一个用户信息
	 */
	public boolean deleteAnAccount(Integer id) {

		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			Account account = session.load(Account.class, id);
			session.delete(account);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	/**
	 * 改 修改一个用户信息
	 */
	public boolean setAnAccount(Integer id, String name, double money) {

		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			Account account = session.load(Account.class, id);
			account.setName(name);
			account.setMoney(money);
			session.update(account);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	/**
	 * 查 查询一个用户信息
	 */
	public Account getAnAccount(Integer id) {

		Session session = null;
		session = getSession();
		session.beginTransaction();
		Account account = session.get(Account.class, id);
		session.getTransaction().commit();
		return account;
	}

	/**
	 * 查 查询所有用户信息
	 */
	public List<Account> getAccountList() {

		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			List<Account> accounts = session.createQuery("FROM Account").list();
			session.getTransaction().commit();
			return accounts;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	/**
	 * 改 更新一个用户信息
	 */
	public Account updateAnAccount(Integer id, String name) {

		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			Account account= session.get(Account.class,id);
			account.setName(name);
			session.save(account);
			session.getTransaction().commit();
			return account;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
}
