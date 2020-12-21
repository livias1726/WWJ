<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
		
		<title>WorldWideJob - BusinessDetails</title>
	</head>
	<body>
		<div>
			<form action="businessDetails.jsp" name="businessdetailsform" method="POST">
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
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="place2" type="text" name="place2" value="" disabled style="background-color:white">
	    		<input class="busdetails" type="text" name="busdetails" value="" disabled style="background-color:#C6D6D3">
	    		<input class="businessname" type="text" name="businessname" value="" disabled style="background-color:#C6D6D3">
	    		<input class="thebusiness" type="text" name="thebusiness" value="The business" disabled style="background-color:#C6D6D3">
	    		<input class="busdescription" type="text" name="busdescription" value="" disabled style="background-color:#E4F5F2">
	    		<input class="insert_budget" type="text" name="insert_budget" value="Insert budget" disabled style="background-color:#C6D6D3"><br>
	    		<input class="budget" type="text" name="budget" value="" style="background-color:#E4F5F2">
	    		<button class="statistics_btn" style="width:150px; height:50px; top:100px; background-color: dodgerblue; margin-left:1050px; border-color: black">View statistics</button>
	    		<button class="calculatefeasibility_btn" style="width:150px; height:50px; top:500px; background-color: dodgerblue; margin-left:350px; border-color: black">Calculate feasibility</button>
	    		</div>
	    	</form>
		</div>
	</body>
</html>