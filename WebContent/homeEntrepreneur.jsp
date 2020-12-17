<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>WorldWideJob - homeEntrepreneur</title>
</head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="icon" href="icons/main_icon.png">
	<link href="css/style.css" rel="stylesheet">
<body>
	<div>
			<form action="homeEntrepreneur.jsp" name="researchEntrepreneurform" method="POST">
				<div style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
	    		<div class="dropdown" style="float:left;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div class="dropdown-content" style="right:0;">
		     		<a href="http://localhost:8080/WorldWideJob/login.jsp">Login</a>
		     	</div>
		     	</div>
		     	</div>
	    		<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px"></button>
	    		</div>
	    		</div>
	    		<div class="search_entr">Become your own boss.<br>Start a new business in the Country of your dreams.<br>
	    		<input class="place_btn" type="text" name="place" value="place" style="background-color:white; border-color:black">
	    		<input class="business_btn" type="text" name="business" value="business" style="background-color:white; border-color:black"><br>
	    		<button class="search_btn" style="width:100px; height:50px; top:100px; background-color: dodgerblue; margin-right:-20px; border-color: black">Search</button>
	    		</div>
	    	</form>
		</div>
	</body>
</html>

