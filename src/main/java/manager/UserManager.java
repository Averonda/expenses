package manager;

import java.util.List;

import dao.UserDAO;
import dao.UserDaoImpPostgres;
import pojo.User;

public class UserManager {

	UserDAO dao = new UserDaoImpPostgres();
	
	public UserManager() {
		
	}
	
	public void create(User user) {
		dao.createUser(user);
	}
	
	public boolean login(String username, String password) {
		if(dao.getUser(username, password) != null) {
			return true;
		}
		return false;
	}
	
	public void deleteUser(User user) {
		if(dao.getUser(user.getUserName(), user.getPassword()) != null) {
			dao.deleteUser(user);
//			TODO: Log
		}
		
	}

	public void updateUser(User user, String newPassword, boolean isApprover) {
		if(dao.getUser(user.getUserName(), user.getPassword()) != null) {
			dao.updateUser(user, newPassword, isApprover);
//			TODO: Log
		}
	}
	
	public List<User> findAll(){
		return dao.findAll();
	}
}
