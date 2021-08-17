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
import servlet.HomeServlet;

public class ExpenseDaoImpPostgres implements ExpenseDAO {
	private static final Logger logger = LoggerFactory.getLogger(ExpenseDaoImpPostgres.class);
	@Override
	public List<Expense> getAllExpense() {
		Session session = null;
		List<Expense> expenses = null;
		try {
			session = DBUtil.getInstance().getSession();

			
			Query<Expense> query = session.createQuery("FROM pojo.Expense");

			expenses = query.list();
		} catch (HibernateException e) {
			// TODO add custom exception
			logger.debug(e.toString());
		} finally {
			session.close();
		}

		return expenses;
	}

	@Override
	public Expense getExpense(int id) {
		Session session = null;
		Expense expense = null;
		try {
			session = DBUtil.getInstance().getSession();

			Query<Expense> query = session.createQuery("FROM pojo.Expense where expense_id = :eID");
			query.setParameter("eID", id);

			expense = query.uniqueResult();
		} catch (HibernateException e) {
			// TODO add custom exception
			logger.debug(e.toString());
		} finally {
			session.close();
		}

		return expense;
	}

	@Override
	public void updateExpense(Expense expense) {
		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			expense.updateTimeStamp();
			session.update(expense);
			tx.commit();
		} catch (HibernateException e) {
			// TODO add custom exception
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
	public void deleteExpense(Expense expense) {
		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(expense);
			tx.commit();
		} catch (HibernateException e) {
			// TODO add custom exception
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
	public void createExpense(Expense expense) {
		Session session = DBUtil.getInstance().getSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
//			expense.setDateSubmitted(null);
			session.save(expense);
			tx.commit();
		} catch (HibernateException e) {
			// TODO add custom exception
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(e.toString());
			throw e;
		} finally {
			session.close();
		}
	}

