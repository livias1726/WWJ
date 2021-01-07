<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
		<link rel="stylesheet" href="css/style.css">
		<title>WorldWideJob</title>
	</head>
	<body class="main">
		<div class="dropdown" style="float:right;">
	    	<button class="user_btn"></button>
		  <div class="dropdown-content" style="right:0;">
		    <a href="http://localhost:8080/WorldWideJob/login.jsp">Login</a>
		  </div>
		</div>
		<div style="margin-left: 300px">
		    <button class="main_btns" type="button" onClick="javascript:window.location='seekerResearch.jsp';">Job seeker</button>
		    <button class="main_btns" type="button" onClick="javascript:window.location='login.jsp';">Recruiter</button>
		    <button class="main_btns" type="button" onClick="javascript:window.location='entrepreneurResearch.jsp';">Entrepreneur</button>
		</div>
	</body>
</html>