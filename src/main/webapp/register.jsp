<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>WDF-Servlet | Registration</title>
</head>
<body>
	<h1>Register</h1>
	<form action="register" method="post">
		<div class="form-group" style="margin-bottom: 10px;">
			<label for="username"></label>
			<input type="text" id="username" name="username" placeholder="Username" required /><br>
		</div>
		
		<div class="form-group" style="margin-bottom: 10px;">
			<label for="email"></label>
			<input type="email" id="email" name="email" placeholder="Email" required />
		</div>
		
		<div class="form-group" style="margin-bottom: 10px;">
			<label for="password"></label>
			<input type="password" id="password" name="password" placeholder="Password" required />
		</div>
		
		<div class="form-group" style="margin-bottom: 10px;">
			<label for="cpassword"></label>
			<input type="password" id="cpassword" name="cpassword" placeholder="Confirm Password" required />
		</div>
		
		<c:if test="${not empty errmsg}">
		<p style="color: red;">${errmsg}</p>
		</c:if>
		
		<div class="form-group" style="margin-top: 20px;">
			<button type="submit">Register</button>
		</div>
	</form>
	<footer style="margin-top:20px;"><a href="/wdf-servlet">WDF-Servlet</a> &copy; 2024. All right reserved.</footer>
</body>
</html>