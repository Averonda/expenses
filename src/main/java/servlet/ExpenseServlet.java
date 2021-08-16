package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.util.ExpensesConstants;
import common.util.HttpUtil;
import pojo.Expense;
import manager.ExpenseManager;

@WebServlet("/expenses/*")
public class ExpenseServlet extends HttpServlet{
	
	private ExpenseManager emanager = new ExpenseManager();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String jsonInString = null;
		ObjectMapper mapper = new ObjectMapper();

		String[] pathVariables = HttpUtil.getPathVariables(req);
		
		if(pathVariables == null || pathVariables.length == 0) {
			//get data from backend
			List<Expense> expenses = emanager.getAllExpense();
			//transform java object to JSON string
			jsonInString = mapper.writeValueAsString(expenses);
		} 
		// GET /menus/:id
		// fetch individual menu item
		if(pathVariables != null && pathVariables.length == 2) {
			//get data from backend
			int id = Integer.parseInt(pathVariables[1]);
			Expense expense = emanager.getExpense(id);
			//transform java object to JSON string
			if(expense != null)
				jsonInString = mapper.writeValueAsString(expense);
			else
				jsonInString = HttpUtil.getErrorMessage("No Record Found");
		}
		
		//send success response to client
		resp.getWriter().print(jsonInString);
		resp.setContentType(ExpensesConstants.HTTP_JSON_CONTENT);
		resp.setStatus(ExpensesConstants.HTTP_OK);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//get JSON data from HTTP body
			ObjectMapper mapper = new ObjectMapper();
			Expense expense = mapper.readValue(HttpUtil.getJSONData(req), Expense.class);
			//persist data to backend
			emanager.createExpense(expense);;
			//send success response to client
			String jsonResponse = mapper.writeValueAsString(expense);
			resp.getWriter().print(jsonResponse);
			resp.setStatus(ExpensesConstants.HTTP_OK);
		} catch (Exception e) {
			//send failure response to client
			resp.getWriter().print(HttpUtil.getErrorMessage(e.getMessage()));
			resp.setStatus(ExpensesConstants.HTTP_ERROR);
		}

		resp.setContentType(ExpensesConstants.HTTP_JSON_CONTENT);

	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] pathVariables = HttpUtil.getPathVariables(req);
		// GET /menus/:id
		// fetch individual menu item
		if(pathVariables != null && pathVariables.length == 2) {
			//get data from backend
			int id = Integer.parseInt(pathVariables[1]);
			emanager.deleteExpense(emanager.getExpense(id));
			resp.setStatus(ExpensesConstants.HTTP_OK);
		}
	}

}
