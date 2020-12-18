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
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
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
	    		<div class="change_pic">
	    		<a href="" style="margin-left:40px;color:black">ChangeProfilePic</a>
	    		</div>
	    		</div>
	    		<div id="container">
				<ul id="griglia">
				<li><button class="company_btn" style="width:200px;height:160px">Company</button></li>
				<li><button class="id_btn" style="width:200px;height:160px">Personal Info</button></li>
				<li><button class="offers_btn" style="width:200px;height:160px">Job Offers</button></li>
				<li><button class="candidate_btn" style="width:200px;height:160px">Candidates</button></li>
				</ul>
				</div>
	    		</div>
	    	</form>
	    </div>
	</body>
</html>