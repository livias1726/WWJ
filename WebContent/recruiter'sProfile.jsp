<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
	    

		<title>WorldWideJob - recruiter'sProfile</title>
		
	</head>
	<body>
		<div>
			<form action="recruiter'sProfile.jsp" name="recruiter'sProfileform" method="POST">
				<div class="dropdown" style="float:right;">
	    				<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
		     			<a href="">Publish Job Offer</a>
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="">Quit</a>
		     		</div>
		     	</div>
		     	<div style="float:right;">
	    			<button class="notify_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div style="float:right;">
		        	<div class="dropdown" style="float:right;">
	    				<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
		     			<a href="">Logout</a>
		     		</div>
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
	    		<div class="recruiter_acc">
	    		<div class="profile_pic">
	    		<div>
	    			<input type="text" id="Lnamefname" name="LnameFname" style="margin-left:290px;margin-top:20px"><br>
	    			<label for="email" style="margin-top:40px;margin-left:330px">Recruiter</label>
	    		</div>
	    		<button class="change_pic" style="background-color:lightgrey;width:250px;height:30px;margin-top:180px;margin-left:-7px">Change profile picture</button>
	    		</div>
	    		<div id="container">
				<ul id="griglia">
				<li><button class="company_btn" style="width:200px;height:160px;background-color:lightblue">Company</button></li>
				<li><button class="id_btn" style="width:200px;height:160px;background-color:lightblue">Personal Info</button></li>
				<li><button class="offers_btn" style="width:200px;height:160px;background-color:lightblue">Job Offers</button></li>
				<li><button class="candidate_btn" style="width:200px;height:160px;background-color:lightblue">Candidates</button></li>
				</ul>
				</div>
	    		</div>
	    	</form>
	    </div>
	</body>
</html>