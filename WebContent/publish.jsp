<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<%Class.forName("com.mysql.jdbc.Driver");%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="css/style.css" rel="stylesheet">
		<title>WorldWideJob - publish</title>
	</head>
	<body>
		<div>
			<form action="publish.jsp" name="publish" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/recruiterProfile.jsp">Quit</a>
		     		</div>
		     	</div>
		     	<div style="float:right;">
	    			<button class="notify_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/recruiterProfile.jsp">Account</a>
		     			<a href="http://localhost:8080/WorldWideJob/login.jsp">Logout</a>
		     		</div>
		     	</div>
		     	</div>
	    		<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='recruiterProfile.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="publish_container" value="" disabled style="background-color:#C6D6D3;height:800px">
	    		<input class="text_title" type="text" name="thejob" value="The Job" disabled style="background-color:#C6D6D3;margin-top:-40px;margin-left:10px">
	    		<input class="text_subsubtitle" type="text" name="position" value="Position" disabled style="background-color:#C6D6D3;margin-top:-40px; margin-left:20px">
	    		<select class="order_select" name="position" style="background-color:white;width:250px;height:30px;margin-left:50px;margin-top:-60px">
	    			<option>Job</option>
	    		</select> 
	    		<button class="add_btn" type="button" name="addjob" style="border:0; background-color:#C6D6D3; color:dodgerblue;margin-top:230px;margin-left:-102px">Add a new job...</button>
	    		<%if(request.getParameter("addjob") != null){%>
	    			<input class="text_input" type="text" name="jobname" value="" style="background-color:white; margin-top:250px; margin-left:100px; width:250px;height:30px;">
	    			<input class="text_input" type="text" name="jobcategory" value="" style="background-color:white; margin-top:250px; margin-left:170px; width:250px;height:30px;">
	    		<%}%>
	    		</div>
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="requirements" value="Requirements" disabled style="background-color:#C6D6D3;margin-top:100px; margin-left:20px">
	    		<input class="text_input" type="text" name="requirementsInput" value="" style="background-color:white; height:90px; width:300px; margin-top:88px; margin-left:190px;">
	    		</div>
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="description" value="Activities description" disabled style="background-color:#C6D6D3;margin-top:270px; margin-left:20px">
	    		<input class="text_input" type="text" name="descriptionInput" value="" style="background-color:white; height:90px; width:500px; margin-top:100px; margin-left:74px;">
	    		</div>
	    		<div>
	    		<input class="text_title" type="text" name="information" value="Additional information" disabled style="background-color:#C6D6D3; margin-left:50%;margin-top:-40px ">
	    		<input class="text_subsubtitle" type="text" name="companyBranch" value="Company branch" disabled style="background-color:#C6D6D3;margin-top:-40px; margin-left:50%">
	    		<select class="order_select" name="companyBranchSelect" style="background-color:white;width:250px;height:30px;margin-left:58%;margin-top:-60px">
	    			<option></option>
	    		</select> 
	    		</div>
	    		<input class="text_subsubtitle" type="text" name="timeslot" value="Time slot" disabled style="background-color:#C6D6D3;margin-top:40px; margin-left:50%">
	    		<input class="text_input" type="text" name="start" value="Start (hh:mm)" style="background-color:white; top:-360px; margin-left:63%;width:150px;height:30px;">
	    		<input class="text_input" type="text" name="finish" value="Finish (hh:mm)" style="background-color:white; top:-395px; margin-left:80%;width:150px;height:30px;">
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="salary" value="Base salary" disabled style="background-color:#C6D6D3;margin-top:130px; margin-left:50%">
	    		<input class="text_input" type="text" name="salaryInput" value="" style="background-color:white; top:-340px; margin-left:64%;width:250px;height:30px;">
	    		<select class="order_select" name="money" style="background-color:white;width:50px;height:35px;margin-left:76%;margin-top:112px">
	    			<option>$</option>
	    			<option>&pound;</option>
	    			<option>&euro;</option>
	    		</select> 
	    		</div>
	    		<div>
	    		<input class="text_title" type="text" name="others" value="Others" disabled style="background-color:#C6D6D3; margin-left:50%;margin-top:300px ">
	    		</div>
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="expirationDate" value="Expiration date" disabled style="background-color:#C6D6D3; margin-left:50%;margin-top:300px ">
	    		<input class="text_input" type="text" name="dateInput" value="" style="background-color:white; top:-212px; margin-left:65%;width:250px;height:30px;">
	    		</div>
	    		<div>
	    		<button class="saveresults_btn" style="width:150px; height:50px; top:-80px; left:1050px; background-color: dodgerblue; border-color: black">Publish</button>
	    		</div>
	    	</form>
	    </div>		
	</body>
</html>