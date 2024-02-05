package dao;

import entity.User;

public interface UserDao {

	public boolean emailExists(String email);
	
	public boolean sendTokenConfirmationEmail(String recipient, String token);
	
	public boolean createNewAccount(String username, String email, String password, String token);
	
	public User getUserByToken(String token);
	
	public void requestOTP(String email);
}
