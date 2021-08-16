package dao;

import java.util.List;

import pojo.Expense;

public interface ExpenseDAO {

	public List<Expense> getAllExpense();

	public Expense getExpense(int id);

	public void updateExpense(Expense expense);

	public void deleteExpense(Expense expense);
	
	public void createExpense(Expense expense);
}
