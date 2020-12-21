<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
		
		<title>WorldWideJob - Applications</title>
	</head>
	
	<body>
		<div>
			<form action="applicationsJobSeeker.jsp" name="applicationsJobSeeker" method="POST">
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
		        <div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Account</a>
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
	    		<div>
	    		<input class="favourite_container" type="text" name="applications_container" value="" disabled style="background-color:#C6D6D3">
	    		<input class="favourite_title" type="text" name="applications_title" value="Applications" disabled style="background-color:#C6D6D3">
	    		<fieldset style="background-color:#C6D6D3;position:absolute;width:150px;height:30px;left:20px;top:170px;border:0">
	    		<input type="checkbox" name="select_all" style="">Select all<br>
	    		</fieldset>
	    		<input class="candidate_row_title" type="text" name="application_row_title" value="" disabled style="background-color:#56A6F0; border:0;">
	    		<input class="candidate_row_title" type="text" name="offer_row_title" value="OFFER" disabled style="background-color:#56A6F0; width:10%; left:250px;border:0;">
	    		<input class="candidate_row_title" type="text" name="position_row_title" value="POSITION" disabled style="background-color:#56A6F0; width:10%; left:450px;border:0;">
	    		<input class="candidate_row_title" type="text" name="applicationdate_row_title" value="APPLICATION DATE" disabled style="background-color:#56A6F0; width:10%; left:700px;border:0;">
	    		<input class="candidate_row_title" type="text" name="expirationdate_row_title" value="EXPIRATION DATE" disabled style="background-color:#56A6F0; width:10%; left:950px;border:0;">
	    		<input class="candidate_row" type="text" name="application_row" value="" disabled style="background-color:white;border:0;">
	    		<input class="candidate_row" type="text" name="offer_row" value="" disabled style="background-color:white; width:30%; left:250px;border:0;">
	    		<input class="candidate_row" type="text" name="position_row" value="" disabled style="background-color:white;width:30%; left:450px; border:0;">
	    		<input class="candidate_row" type="text" name="applicationdate_row" value="" disabled style="background-color:white;width:30%; left:700px; border:0;">
	    		<input class="candidate_row" type="text" name="expirationdate_row" value="" disabled style="background-color:white;width:18%; left:950px; border:0;">
	    		<input class="candidate_row" type="checkbox" name="candidate_checkbox" style="left:-500px"><br>
	    		<button class="delete_btn" style="width:100px; height:50px; top:500px; left:400px; background-color:dodgerblue">Delete</button>
	    		</div>
	    		
			</form>
		</div>
	</body>
</html>