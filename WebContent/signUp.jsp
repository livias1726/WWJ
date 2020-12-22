<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="css/style.css" rel="stylesheet">
	<title>WorldWideJob - signUp</title>
</head>
<body>
	<div>
			<form action="signUp.jsp" name="signUpform" method="POST">
				<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
				<div style="float:left">
					<button class="arrow_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px"></button>
	    		</div>
	    		<div style="float:right;">
	    			<button class="search_btn" style="width:120px; height:60px; top:10px; background-color: dodgerblue; margin-left:600px">Sign In</button>
				</div>
				<div class="scheletro_offer_det" style="text-size:150px;text-align:center">Create an account<br>
					<div style="margin-top: 40px">
				    	<label for="firstName" style="color:black;font-size:20px;margin-left:-640px">First Name</label>
				        <input type="text" id="firstName" name="firstName" style="margin-left: 40px;height:15px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="eMail" style="color:black;font-size:20px;margin-left:-600px">Email</label>
				        <input type="text" id="eMail" name="eMail" style="margin-left: 42px;height:15px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="password" style="color:black;font-size:20px;margin-left:-670px">Password</label>
				        <input type="text" id="password" name="password" style="margin-left: 75px;height:15px">
				    </div>
				    <div style="margin-top: -150px;float:right">
				    	<label for="lastName" style="color:black;font-size:20px;margin-right:40px">Last Name</label>
				        <input type="text" id="lastName" name="lastName" style="margin-right:250px;height:15px">
				    </div>
				    <div style="margin-top: -75px;float:right">
				    	<label for="confirmEmail" style="color:black;font-size:20px;margin-right:40px">Confirm email</label>
				        <input type="text" id="confirmEmail" name="confirmEmail" style="margin-right:250px;height:15px">
				    </div>
				    <div style="margin-top: -10px;float:right">
				    	<label for="confirmPassword" style="color:black;font-size:20px;margin-right:40px">Confirm Password</label>
				        <input type="text" id="confirmPassword" name="confirmPassword" style="margin-right:250px;height:15px">
				    </div><br>
				    <button class="search_btn" style="width:120px; height:60px; top:40px; background-color: dodgerblue; margin-left:-30px">Sign Up</button>
				</div>
			</form>
	</div>

</body>
</html>