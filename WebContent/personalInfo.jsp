<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="css/style.css" rel="stylesheet">
	<title>WorldWideWeb - personalInfo</title>
</head>
<body>
	<div>
			<form action="personalInfo.jsp" name="personalInfoform" method="POST">
				<div style="float:right;">
					<div class="dropdown" style="float:right;">
						<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
					<div class="dropdown-content" style="right:0;">
		     			<a href="">Logout</a>
		     		</div>
		     	</div>
		     	</div>
		     	<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px"></button>
	    		</div>
	    		</div>
	    		<div class="scheletro_offer_det">
	    			<div style="margin-top: 40px">
				    	<label for="firstName" style="color:black;font-size:20px;margin-left:15px">First Name</label>
				        <input type="text" id="firstName" name="firstName" style="margin-left: 40px;height:15px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="birthDate" style="color:black;font-size:20px;margin-left:15px">Birth Date</label>
				        <input type="text" id="birthDate" name="birthDate" style="margin-left: 42px;height:15px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="eMail" style="color:black;font-size:20px;margin-left:15px">Email</label>
				        <input type="text" id="eMail" name="eMail" style="margin-left: 75px;height:15px">
				    </div>
				    <div style="margin-top: -140px;float:right">
				    	<label for="lastName" style="color:black;font-size:20px;margin-right:40px">Last Name</label>
				        <input type="text" id="lastName" name="lastName" style="margin-right:100px;height:15px">
				    </div>
				    <div style="margin-top: -75px;float:right">
				    	<label for="city" style="color:black;font-size:20px;margin-right:40px">City</label>
				        <input type="text" id="city" name="city" style="margin-right:100px;height:15px">
				    </div>
				    <div style="margin-top: -10px;float:right">
				    	<label for="password" style="color:black;font-size:20px;margin-right:40px">Password</label>
				        <input type="text" id="password" name="password" style="margin-right:70px;height:15px"><input type="checkbox" name="select_all" style="margin-left:-30px">Show<br>
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="title" style="color:black;font-size:20px;margin-left:350px">Titles/lines</label>
				        <input type="text" id="title" name="title" style="margin-left: 75px;height:150px;width:300px"><button style="width:40px;height:40px;background-color:lightblue;margin-left:10px">Add</button>
				    </div><br>
				    <button class="search_btn" style="width:150px; height:90px; top:40px; background-color: dodgerblue; margin-left:600px">Change Info</button>
				</div>
	    	</form>
	    </div>
</body>
</html>