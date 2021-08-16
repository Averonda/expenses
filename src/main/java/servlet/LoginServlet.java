package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.util.ExpensesConstants;
import common.util.HttpUtil;
import pojo.User;
import manager.UserManager;

@WebServlet("/login/*")
public class LoginServlet extends HttpServlet {
	
	private UserManager userManager = new UserManager();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		try {
			//get JSON data from HTTP body
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("mapper bad login");
//			req.toString();
			User user = mapper.readValue(HttpUtil.getJSONData(req), User.class);
			//persist data to backend
			System.out.println("mapping success");
			boolean success = userManager.login(user.getUserName(), user.getPassword());
			//send success response to client
			System.out.println("login success");
			
			resp.getWriter().print( "{\"status\":"+ (success ? "\"success\"" : "\"failure\"") + "}");
			resp.setStatus(ExpensesConstants.HTTP_OK);
		} catch (Exception e) {
			//send failure response to client
			try {
				resp.getWriter().print(HttpUtil.getErrorMessage(e.getMessage()));
				System.out.println("error here");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			resp.setStatus(ExpensesConstants.HTTP_ERROR);
		}

		resp.setContentType(ExpensesConstants.HTTP_JSON_CONTENT);
	}
}
