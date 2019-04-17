<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<style>
    	body{
        	margin: 0;
        	padding: 0;
        	font-family: sans-serif;
        	background-image: url("http://www.marbellaislesnaples.org/marbellaisles/images/container_bg.jpg");
        	background-size: cover;
    	}
    	.signup-box{
        	width: 280px;
        	position: absolute;
        	top: 50%;
        	left: 50%;
        	transform: translate(-50%,-50%);
        	color: #5DADE2;
    	}
    	.signup-box h1{
        	float: left;
        	font-size: 40px;
        	border-bottom: 6px solid #5DADE2;
        	margin-bottom: 50px;
        	padding: 13px 0
    	}
    	.btn{
        	width:100%;
        	background: none;
        	border: 2px solid #5DADE2;
        	color: white;
        	padding: 5px;
        	font-size: 18px;
        	cursor: pointer;
        	margin: 12px 0;
    	}
    	.textbox{
        	width: 100%;
        	overflow: hidden;
        	font-sie: 20px;
        	padding: 8px 0;
        	margin: 8px 0;
        	border-bottom: 1px solid #5DADE2;
    	}
    	.textbox input{
        	border: none;
        	outline: none;
        	background: none;
        	color: #5DADE2;
        	font-size: 18px;
        	width: 170px;
        	float: left;
        	margin: 0 20px;
   	 	}
    	.textbox i{
        	width: 26px;
        	float: left;
        	text-align: center;
    	}	
	</style>

	<script type="text/javascript">
    		function Validate() {
        		var password = document.getElementById("psw").value;
        		var confirmPassword = document.getElementById("psw-repeat").value;
        		var phnno = document.getElementById("Phone number").value;
        		var mail = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        		var mailid = document.getElementById("email").value;

        		if(mail.test(mailid) == false){
            		alert("Invalid Email Address");
        		}

        		if(phnno.length != 10){
            		alert("Phone number is not valid");
            		return false;
        		}
        
        		if(password.length < 6){
        		    alert("Password should contain atleast 6 characters");
            		return false;
        		}
        
        		if (password.search(/[0-9]/) < 0) {
        		    alert("Password must contain at least one digit");
            		return false;
        		}
        
        		if(password.search(/[A-Z]/i) < 0){
            		alert("Password must contain atleast one upper case letter");
            		return false;
        		}
        		
       			if (password != confirmPassword) {
       			    alert("Passwords do not match");
            		return false;
        		}
        	return true;
    	}
	</script>

	<script type="text/javascript">
		function Cancel() {
			window.location.replace("Login");
		}
	</script>

	<body>
		<div class="signup-box">
        <h2>Sign Up</h2>
        <p>Please fill in the following details to create an account.</p>
		<form action="SignUp" method="POST">
        	<div class="textbox">
            	<input type="text" placeholder="Name" name="Name" required>
        	</div>

        	<div class="textbox">
            	<input type="text" placeholder="Address" name="Address" required>
        	</div>

        	<div class="textbox">
            	<input type="number" placeholder="Phone No" name="Phone number" id="Phone number" required>
        	</div>

        	<div class="textbox">
            	<input type="text" placeholder="Email" name="email" id="email" required>
        	</div>

        	<div class="textbox">
            	<input type="password" placeholder="Password" id="psw" name="psw" required>
        	</div>

        	<div class="textbox">
            	<input type="password" placeholder="Confirm password" id="psw-repeat" name="psw-repeat" required>
        	</div>

        	

        	<input class="btn" type="submit" name="" value="Sign up" onclick="return Validate()">
        	
		</form>
		
		<input class="btn" type="button" onclick="Cancel()" value="Cancel">
		
		</div>
	</body>
</html>