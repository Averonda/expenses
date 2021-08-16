package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import common.util.DBUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Expense;
import pojo.User;

public class UserDaoImpPostgres implements UserDAO {

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
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			session.close();
		}

		return users;
	}

	
//	public static void main(String[] args) {
//		UserDaoImpPostgres impl = new UserDaoImpPostgres();
//		User obj = impl.getUser("test", "1234");
//		impl.updateUser(obj, "123", false);
//		System.out.println(obj);
//	}
//
}
