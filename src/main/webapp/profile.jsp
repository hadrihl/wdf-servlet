<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>WDF-Servlet | Update Profile</title>
</head>
<body>
	<h1>Welcome, ${loggedinuser}</h1>
	
	<c:if test="${not empty user}">
	<h3>Update Profile</h3>
	<form action="profile" method="post" style="width: 300px; border: 1px solid #000;
	padding: 10px 0 10px 10px;">
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<label for="firstname" style="padding-right: 10px;">Firstname</label>
			<input type="text" id="firstname" name="firstname" value="${user.firstname}" autocomplete="off" />
		</div>
		
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<label for="lastname" style="padding-right: 10px;">Lastname</label>
			<input type="text" id="lastname" name="lastname" value="${user.lastname}" autocomplete="off" />
		</div>
		
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<label for="company" style="padding-right: 10px;">Company</label>
			<input type="text" id="company" name="company" value="${user.company}" autocomplete="off" />
		</div>
		
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<label for="city" style="padding-right: 45px;">City</label>
			<input type="text" id="city" name="city" value="${user.city}" autocomplete="off" />
		</div>
		
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<label for="country" style="padding-right: 20px;">Country</label>
			<input type="text" id="country" name="country" value="${user.country}" autocomplete="off" />
		</div>
		
		<c:if test="${not empty errmsg}">
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<p style="color: red;">${errmsg}</p>
		</div>
		</c:if>
		
		<c:if test="${not empty msg}">
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<p style="color: green;">${msg}</p>
		</div>
		</c:if>
		
		<div class="form-group" style="margin-top: 20px; margin-bottom: 10px;">
			<a href="/wdf-servlet/update"><button type="button">Cancel</button></a>
			<button type="submit">Update</button>
		</div>
	</form>
	</c:if>
	
	<div class="link" style="margin-top: 50px; margin-bottom: 10px;">
		<a href="/wdf-servlet/home">Dashboard</a>
	</div>
	<div class="link" style="margin-top: 10px; margin-bottom: 10px;">
		<a href="/wdf-servlet/logout">Logout</a>
	</div>
	
	<footer style="margin-top:20px;"><a href="/wdf-servlet">WDF-Servlet</a> &copy; 2024. All right reserved.</footer>
</body>
</html>