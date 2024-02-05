<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>WDF-Servlet | Login</title>
</head>
<body>
	<h1>Login</h1>
	<form action="login" method="post">
		<div class="form-group" style="margin-bottom: 10px;">
			<label for="email"></label>
			<input type="email" id="email" name="email" placeholder="Enter email" autocomplete="off" required />
		</div>
		
		<div class="form-group" style="margin-bottom: 10px;">
			<label for="password"></label>
			<input type="password" id="password" name="password" placeholder="Enter password" autocomplete="off" required />
		</div>
		
		<div class="form-group" style="margin-top: 20px;">
			<button type="submit">Login</button>
		</div>
	</form>
	
	<footer style="margin-top:20px;"><a href="/wdf-servlet">WDF-Servlet</a> &copy; 2024. All right reserved.</footer>
</body>
</html>