package servlet;

import java.io.IOException;
import java.util.logging.LogManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@WebServlet("/")
public class HomeServlet extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(HomeServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		resp.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString("It's alive");
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			logger.debug(jsonString, e1.toString());
		}
	
		try {
			resp.getWriter().print(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.debug(jsonString, e.toString());
		}
	}

}

