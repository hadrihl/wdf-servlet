package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import com.mysql.cj.protocol.ResultStreamer;
import com.mysql.cj.protocol.Resultset;

import entity.User;
import utility.DBConnection;
import utility.EmailSender;
import utility.OTPGenerator;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean emailExists(String email) {
		String sql = "SELECT email FROM user WHERE email = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, email);
			
			ResultSet resultSet = statement.executeQuery();
			
			return resultSet.next();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean sendTokenConfirmationEmail(String recipient, String token) {
		String content = "Enter your OTP (" + token + ") on WDF-Servlet app to validate your email.";
		
		try {
			EmailSender.sendEmail(recipient, "WDF-Servlet - Email Verification OTP", content);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean createNewAccount(String username, String email, String password, String token) {
		String sql = "INSERT INTO user (username, email, password, token) VALUES (?, ?, ?, ?)";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setString(4, token);
			
			statement.executeUpdate();
			
			sendTokenConfirmationEmail(email, token);
			
			return true;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getUserByToken(String token) {
		User user = new User();
		String sql = "SELECT * FROM user WHERE token = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, token);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				user.setUsername(resultSet.getString("username"));
				user.setToken(resultSet.getString("token"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void requestOTP(String email) {
		String token = OTPGenerator.generate();
		
		String sql = "UPDATE user SET token = ? WHERE email = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, token);
			statement.setString(2, email);
			
			statement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		sendTokenConfirmationEmail(email, token);
	}
	
	@Override
	public void loginOTP(String email, String token) {
		String sql = "UPDATE user SET token = ? WHERE email = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, token);
				statement.setString(2, email);
				
				statement.executeUpdate();
				
				String content = "Enter your OTP (" + token + ") to login into WDF-Servlet portal.";
				
				try {
					EmailSender.sendEmail(email, "WDF-Servlet - Login OTP", content);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User login(String email, String password) {
		String sql = "SELECT username, email FROM user WHERE password = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, password);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setEmail(resultSet.getString("email"));
		
				return user;
				
			} 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setUsername(resultSet.getString("username"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setCompany(resultSet.getString("company"));
				user.setCity(resultSet.getString("city"));
				user.setCountry(resultSet.getString("country"));
				return user;
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User updateProfile(User user) {
		String sql = "UPDATE user SET firstname = ?, lastname = ?, company = ?, city = ?, country = ? WHERE username = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setString(3, user.getCompany());
			statement.setString(4, user.getCity());
			statement.setString(5, user.getCountry());
			statement.setString(6, user.getUsername());
			
			statement.executeUpdate();
			
			return user;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getUsersByKeyword(String keyword) {
		String sql = "SELECT * FROM user WHERE user.username = ? OR "
				+ "user.email = ? OR "
				+ "user.firstname = ? OR "
				+ "user.lastname = ? OR "
				+ "user.company = ? OR "
				+ "user.city = ? OR "
				+ "user.country = ?";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, keyword);
			statement.setString(2, keyword);
			statement.setString(3, keyword);
			statement.setString(4, keyword);
			statement.setString(5, keyword);
			statement.setString(6, keyword);
			statement.setString(7, keyword);
			
			ResultSet resultSet = statement.executeQuery();
			List<User> users = new ArrayList<User>();
			
			while(resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setCompany(resultSet.getString("company"));
				user.setCity(resultSet.getString("city"));
				user.setCountry(resultSet.getString("country"));
				users.add(user);
			}
			
			return users;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM user";
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			
			List<User> users = new ArrayList<User>();
			while(resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setCompany(resultSet.getString("company"));
				user.setCity(resultSet.getString("city"));
				user.setCountry(resultSet.getString("country"));
				users.add(user);
			}
			
			return users;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}



}
