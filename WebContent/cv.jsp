<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="css/style.css" rel="stylesheet">
	<title>WorldWideJob - curriculmVitae</title>
</head>
<body>
	<div>
			<form action="cv.jsp" name="cvform" method="POST">
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
	    		<div class="scheletro_offer_det">
	    			<div style="margin-top: 20px">
	    				<input type="text" id="showCV" name="showCV" disabled style="margin-left: 42px;height:15px"><button style="width:60px; height:30px;background-color: lightblue; margin-left:10px">Upload</button>
	    			</div>
	    			<button style="width:60px; height:30px;background-color: lightblue; margin-left:45px;margin-top:20px">Change</button><br>
	    			<div style="margin-left:45px;margin-top:20px">Contacts</div>
	    			<div style="margin-top: 40px">
				    	<label for="telNumber" style="color:black;font-size:20px;margin-left:45px">Telephone Number</label>
				        <input type="text" id="telNumber" name="telNumber" style="margin-left: 40px;height:15px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="email" style="color:black;font-size:20px;margin-left:150px">Email</label>
				        <input type="text" id="email" name="email" style="margin-left: 40px;height:15px">
				    </div><br>
				    <div style="margin-left:45px;margin-top:20px">Instruction years</div><button style="width:60px; height:30px;background-color: lightblue; margin-left:50px;margin-top:40px">Add</button>
				    <table border="1" style="margin-left:45px;margin-top:10px">
   						<thead>
      						<tr>
         						<th style="color:black">Type</th>
         						<th style="color:black">From(year)</th>
         						<th style="color:black">To(year)</th>
         						<th style="color:black">Main Subject</th>
      						</tr>
      						<tr>
      							<th style="height:20px;background-color:white"></th>
      							<th style="height:20px;background-color:white"></th>
      							<th style="height:20px;background-color:white"></th>
      							<th style="height:20px;background-color:white"></th>
      						</tr>
   						</thead>
   					</table>
   					<div style="margin-top: -500px;margin-right:400px;float: right">Working Experience</div><button style="width:60px; float:right; height:30px;background-color: lightblue; margin-right:500px;margin-top:-450px">Add</button>
   					<table border="1" style="margin-left:820px;margin-top:-400px">
   						<thead>
      						<tr>
         						<th style="color:black">Job</th>
         						<th style="color:black">Years</th>
         					</tr>
      						<tr>
      							<th style="height:20px;background-color:white"></th>
      							<th style="height:20px;background-color:white"></th>
      						</tr>
      					</thead>
      				</table>
      				<div style="margin-top: -300px;margin-right:520px;float: right">Skills</div><button style="width:60px; float:right; height:30px;background-color: lightblue; margin-right:500px;margin-top:-250px">Add</button>
      				<div style="margin-top: 130px; margin-left:630px">
				    	<label for="knowLang" style="color:black;font-size:20px;margin-left:150px">Know Languages</label>
				        <input type="text" id="knowLang" name="knowLang" style="margin-left: 40px;height:150px;width:250px">
				    </div><br>
				    <button style="width:60px; float:right; height:30px;background-color: lightblue; margin-right:480px;margin-top:60px">Add</button>
				    <div style="margin-top: 130px; margin-left:630px">
				    	<label for="others" style="color:black;font-size:20px;margin-left:250px">Others</label>
				        <input type="text" id="others" name="others" style="margin-left: 40px;height:150px;width:250px">
				    </div>
				    <button style="width:90px; height:50px; background-color: lightblue; margin-left:600px;margin-top:-600px">Save</button>
	    		</div>
	    	</form>
	    </div>

</body>
</html>