package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import common.util.DBUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Expense;

public class ExpenseDaoImpPostgres implements ExpenseDAO {

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
			e.printStackTrace();
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
			e.printStackTrace();
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
			throw e;
		} finally {
			session.close();
		}
	}

//	public static void main(String[] args) {
//		ExpenseDaoImpPostgres impl = new ExpenseDaoImpPostgres();
//		List<Expense> obj = impl.getAllExpense();
//		Expense exp = new Expense(1, 99, false,10.12,"Travel", "gas");
//		System.out.println(obj);
//		
//		exp.setExpenseAmount(1.05);
//		impl.createExpense(exp);
//		
//		
//		System.out.println(exp);
//	}

}
