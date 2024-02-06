<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>WDF-Servlet | Update</title>
</head>
<body>
	<h1>Welcome, ${loggedinuser}!</h1>
	<p>You may update your username, email, password and profile.</p>
	<div class="link" style="margin-top: 20px; margin-bottom: 10px;">
		<a href="#">Username</a>
	</div>
	
	<div class="link" style="margin-top: 10px; margin-bottom: 10px;">
		<a href="#">Email</a>
	</div>
	
	<div class="link" style="margin-top: 10px; margin-bottom: 10px;">
		<a href="#">Password</a>
	</div>
	
	<div class="link" style="margin-top: 10px; margin-bottom: 10px;">
		<a href="/wdf-servlet/profile">Profile</a>
	</div>
	
	<div class="link" style="margin-top: 30px; margin-bottom: 10px;">
		<a href="/wdf-servlet/home">Dashboard</a>
	</div>
	<div class="link" style="margin-top: 10px; margin-bottom: 10px;">
		<a href="/wdf-servlet/logout">Logout</a>
	</div>
	
	<footer style="margin-top:20px;"><a href="/wdf-servlet">WDF-Servlet</a> &copy; 2024. All right reserved.</footer>
</body>
</html>