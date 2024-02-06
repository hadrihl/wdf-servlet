package dao;

import java.util.List;

import entity.User;

public interface UserDao {

	public boolean emailExists(String email);
	
	public boolean sendTokenConfirmationEmail(String recipient, String token);
	
	public boolean createNewAccount(String username, String email, String password, String token);
	
	public User getUserByToken(String token);
	
	public void requestOTP(String email);
	
	public User login(String email, String password);
	
	public void loginOTP(String email, String token);
	
	public User getUserByUsername(String username);
	
	public User updateProfile(User user);
	
	public List<User> getUsersByKeyword(String keyword);
	
	public List<User> getAllUsers();
}
