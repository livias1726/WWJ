<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">

		<title>WorldWideWeb - offerDetails</title>

</head>
<body>
	<div>
			<form action="offerDetails.jsp" name="offerDetailsform" method="POST">
				<div style="float:right;">
	    				<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     	<div style="float:left;">
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
	    		<div>
	    		<input class="place2" type="text" name="place2" value="" disabled style="background-color:white">
	    		</div>
	    		<div class="scheletro_offer_det">The Job<br>
	    			<div style="margin-top: 40px">
				    	<label for="position" style="color:black;font-size:20px">Position</label>
				        <input type="text" id="position" name="position" style="margin-left: 40px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="qualificationNeeded" style="color:black;font-size:20px">Qualification needed</label>
				        <input type="text" id="qualificationNeeded" name="qualificationNeeded" style="margin-left: 40px;overflow-x:scroll;overflow-y:scroll">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="activitiesDescription" style="color:black;font-size:20px">Activities description</label><br>
				        <input type="text" id="activitiesDescription" name="activitiesDescription" style="margin-left: 40px;overflow-x:scroll;overflow-y:scroll;margin-top:10px">
				        </div>
	    		</div>
	    	</form>
	    </div>
</body>
</html>