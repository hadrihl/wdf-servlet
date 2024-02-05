package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;

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

}
