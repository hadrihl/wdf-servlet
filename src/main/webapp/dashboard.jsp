<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Dashboard</title>
</head>
<body>
	<h1>Welcome, ${username}!</h1>
	
	<div class="container" style="margin-top: 2px; margin-bottom: 10px;">
		<a href="/wdf-servlet/update">Update profile</a>
	</div>
	
	<div class="container" style="margin-top: 2px; margin-bottom: 10px;">
		<a href="/wdf-servlet/search">Search public user profile</a>
	</div>
	
	<div class="container" style="margin-top: 2px; margin-bottom: 10px;">
		<a href="/wdf-servlet/chat">Chat</a>
	</div>
	
	<div class="container" style="margin-top: 2px; margin-bottom: 10px;">
		<a href="/wdf-servlet/thread">Discourse (post threads)</a>
	</div>
	
	<div class="container" style="margin-top: 2px; margin-bottom: 10px;">
		<a href="/wdf-servlet/jobs">Jobs opportunities</a>
	</div>
	
	<div class="container" style="margin-top: 30px; margin-bottom: 10px;">
		<a href="/wdf-servlet/logout">Logout</a>
	</div>
	<footer style="margin-top:20px;"><a href="/wdf-servlet">WDF-Servlet</a> &copy; 2024. All right reserved.</footer>
</body>
</html>