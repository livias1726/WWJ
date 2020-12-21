<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="css/style.css?ts=<?=time()?>&quot" rel="stylesheet">
		
		<title>WorldWideJob - entrepreneur'sProfile</title>
	</head>
	<body>
		<div>
			<form action="entrepreneur'sProfile.jsp" name="entrepreneur'sProfileform" method="POST">
				<div class="dropdown" style="float:right;">
	    				<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
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
	    			<input type="text" id="Lnamefname" name="LnameFname" disabled style="margin-left:290px;margin-top:20px"><br>
	    			<label for="email" style="margin-top:40px;margin-left:325px">Entrepreneur</label>
	    		</div>
	    		<button class="change_pic" style="background-color:lightgrey;width:250px;height:30px;margin-top:180px;margin-left:-7px">Change profile picture</button>
	    		</div>
	    		<div id="container">
				<ul id="griglia">
				<li><button class="profits_btn" style="width:200px;height:160px;background-color:lightblue">Business Plans</button></li>
				<li><button class="id_btn" style="width:200px;height:160px;background-color:lightblue"><br>Personal Info</button></li>
				</ul>
	    		</div>
	    		</div>
	    	</form>
	    </div>

</body>
</html>