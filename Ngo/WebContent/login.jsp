<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
    	<style>
        	@import "https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css";
        	body{
            	margin: 0;
            	padding: 0;
            	font-family: sans-serif;
            	height: 800px;
            	background-image: url("https://image.freepik.com/free-photo/abstract-blank-chalkboard-black-background-texture-text-drawing_52494-312.jpg");
            	background-size: cover;
        	}
        	.login-box{
            	width: 280px;
            	position: absolute;
            	top: 50%;
            	left: 50%;
            	transform: translate(-50%,-50%);
            	color: white;
        	}
        	.login-box h1{
            	float: left;
            	font-size: 40px;
            	border-bottom: 6px solid #95A5A6;
            	margin-bottom: 50px;
            	padding: 13px 0
        	}
        	.textbox{
            	width: 100%;
            	overflow: hidden;
            	font-sie: 20px;
            	padding: 8px 0;
            	margin: 8px 0;
            	border-bottom: 1px solid #95A5A6;
        	}
        	.textbox i{
            	width: 26px;
            	float: left;
            	text-align: center;
        	}
        	.textbox input{
            	border: none;
            	outline: none;
            	background: none;
            	color: white;
            	font-size: 18px;
            	width: 200px;
            	float: left;
            	margin: 0 10px;
        	}
        	.btn{
            	width:100%;
            	background: none;
            	border: 2px solid #95A5A6;
            	color: white;
            	padding: 5px;
            	font-size: 18px;
            	cursor: pointer;
            	margin: 12px 0;
        	}
    	</style>
    	<title>NGO Login Form</title>
	</head>
	
	<body>
		<div class="login-box">
    		<h1> NGO Login</h1>
    		<form action="Login" method="POST">
    			<div class="textbox">
        			<i class="fa fa-user" aria-hidden="true"></i>
        			<input type = "text" placeholder="Username" name="username" value="">
    			</div>
    			<div class="textbox">
        			<i class="fa fa-lock" aria-hidden="true"></i>
        			<input type = "password" placeholder="Password" name="password" value="">
    			</div>
    			<input class="btn" type="submit" name="" value="Sign in">
			</form>

			<form action="SignUp" method="GET">
    			<input class="btn" type="submit" name="" value="Sign up">
			</form>
		</div>
	</body>
</html>
