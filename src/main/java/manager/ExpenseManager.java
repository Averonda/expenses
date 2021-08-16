package manager;

import java.util.List;

import dao.ExpenseDAO;
import dao.ExpenseDaoImpPostgres;
import pojo.Expense;

public class ExpenseManager {
	
	private ExpenseDAO dao = new ExpenseDaoImpPostgres();
	
	public ExpenseManager() {
		
	}
	
	public List<Expense> getAllExpense(){
		return dao.getAllExpense();
	}

	public Expense getExpense(int id) {
		return dao.getExpense(id);
	}

	public void updateExpense(Expense expense) {
		dao.updateExpense(expense);
	}

	public void deleteExpense(Expense expense) {
		dao.deleteExpense(expense);
	}
	
	public void createExpense(Expense expense) {
		dao.createExpense(expense);
	}
}
