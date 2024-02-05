<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>WDF-Servlet | Account Verified</title>
</head>
<body>
	<h1>Hi, ${username}</h1>
	<p>Your account had been successfully verified. You can now login into the portal.</p>
	
	<p>You will be re-direct to the homepage in <span id="count">5</span></p>
	
	<footer style="margin-top:20px;"><a href="/wdf-servlet">WDF-Servlet</a> &copy; 2024. All right reserved.</footer>
	<script>
    	let counter = +document.getElementById('count').innerText;
    	let count = counter - 1;
    	
    	setInterval(() => {
         document.getElementById('count').textContent = count;
    		count--;
    		if(count < 1) location.replace('http://localhost:8080/wdf-servlet/');
    	}, 1000);
    </script>
</body>
</html>