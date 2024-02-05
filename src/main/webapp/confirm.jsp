<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Confirmation</title>
</head>
<body>
	<h1>Verify Your Email</h1>
	<p>Enter OTP (one-time password) that we had sent to your email (<span id="email">${email}</span>). Timeout: <span id="timer"></span></p>
	
	<form action="confirm" method="post">
		<label for="token">Token</label>
		<input type="text" id="token" name="token" autocomplete="off" required /><br>
		
		<div class="form-group" id="form-group" style="margin-top: 10px;">
			<button type="submit" id="otpBtn">Submit</button>
		</div>
	</form>
	
	<script>
		const container = document.getElementById("form-group");
		let timeoutDuration = 120 * 1000;
		
		function updateTimer() {
			const timerElement = document.getElementById("timer");
			const minutes = Math.floor(timeoutDuration / 60000);
			const seconds = Math.floor((timeoutDuration % 60000) / 1000);
			
			// display timer in mm:ss format
			timerElement.innerText = minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
			
			// update timeout duration
			timeoutDuration -= 1000;
			
			// check if timeout is reached
			if(timeoutDuration < 0) {
				const email = document.getElementById("email").textContent;
				const linkRequestOTP = document.createElement('a');
				linkRequestOTP.href = "http://localhost:8080/wdf-servlet/request?email=" + email;
				linkRequestOTP.textContent = "Request OTP";
				container.removeChild(document.getElementById("otpBtn"));
				container.appendChild(linkRequestOTP);
				
			} else {
				setTimeout(updateTimer, 1000);
				
			}
		}
		
		updateTimer();
	</script>
</body>
</html>