package dao;

import java.util.List;

import pojo.User;

public interface UserDAO {

	public User getUser(String userName, String password);

	public void createUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user, String newPassword, boolean isApprover);
	
	public List<User> findAll();
}
