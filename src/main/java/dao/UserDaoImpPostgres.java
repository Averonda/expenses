package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.util.DBUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Expense;
import pojo.User;
import servlet.HomeServlet;

public class UserDaoImpPostgres implements UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpPostgres.class);

	public UserDaoImpPostgres() {

	}

	@Override
	public User getUser(String userName, String password) {
		Session session = null;
		User user = null;
		try {
			session = DBUtil.getInstance().getSession();

			Query<User> query = session.createQuery("FROM pojo.User where username = :uname and password = :pwd");
			query.setParameter("uname", userName);
			query.setParameter("pwd", (String) password);
			System.out.println(userName + " " + password);

			user = (User) query.uniqueResult();
			logger.debug(user.toString());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			logger.debug(e.toString());
			} finally {
			session.close();
		}

		return user;
	}

	@Override
	public void createUser(User user) {

		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(e.toString());
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void deleteUser(User user) {
		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(e.toString());
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void updateUser(User user, String newPassword, boolean isApprover) {
		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			user.setPassword(newPassword);
			user.setApprover(isApprover);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(e.toString());
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public List<User> findAll() {
		Session session = null;
		List<User> users = null;
		try {
			session = DBUtil.getInstance().getSession();

			
			Query<User> query = session.createQuery("FROM pojo.User");

			users = query.list();
		} catch (HibernateException e) {
			// TODO add custom exception
			logger.debug(e.toString());
		} finally {
			session.close();
		}

		return users;
	}

	

}
