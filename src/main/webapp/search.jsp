<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>WDF-Servlet | Search</title>
</head>
<body>
	<h1>Welcome, ${loggedinuser}!</h1>
	
	<div class="searchbox">
		<p>Enter any keyword to start search public user profile.</p>
		<form action="search" method="post" style="margin-bottom: 40px;">
			<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
				<input type="text" id="keyword" name="keyword" autocomplete="off" placeholder="Enter keyword to search" />
			</div>
			
			<div class="form-group" style="margin-top: 10px;">
				<a href="/wdf-servlet/home"><button type="button">Cancel</button></a>
				<button type="submit">Search</button>
			</div>
		</form>
	</div>
	
	<c:if test="${not empty users}">
	<div class="user-list" style="margin-top: 10px; margin-bottom: 20px;">
		<p>Result:-</p>
		<table>
			<thead>
				<tr>
					<th>#</th>
					<th>Username</th>
					<th>Email</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Company</th>
					<th>City</th>
					<th>Country</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}" varStatus="row">
				<tr>
					<td>${row.index+1}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.firstname}</td>
					<td>${user.lastname}</td>
					<td>${user.company}</td>
					<td>${user.city}</td>
					<td>${user.country}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
		
	<c:if test="${empty users}">
	<div class="user-list" style="margin-top: 10px; margin-bottom: 20px;">
		<p>User(s) not found.</p>
	</div>
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