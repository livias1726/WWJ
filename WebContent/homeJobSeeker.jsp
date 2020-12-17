<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<jsp:useBean id="countryBean" scope="request" class="logic.bean.CountryBean"/>

<jsp:setProperty name="countryBean" property="*"/>

<% if (request.getParameter("country") != null) {
        if (countryBean.verifyJobSeekerResearch()) { %>
        	<jsp:forward page="jobOffers.jsp"/>
      <%}
    }
%>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
		
		<title>WorldWideJob - HomeJobSeeker</title>
	</head>
	<body>
		<div>
			<form action="homeJobSeeker.jsp" name="researchjobseekform" method="POST">
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
	    		<div class="search_usr">Find the right job for you,<br>In the place you've always dreamed of.<br>
	    		<input class="place_btn" type="text" name="place" value="place" style="background-color:white">
	    		<input class="job_btn" type="text" name="job" value="job" style="background-color:white"><br>
	    		<button class="search_btn" style="width:100px; height:50px; top:100px; background-color: dodgerblue; margin-right:-100px; border-color: black">Search</button>
	    		</div>
	    	</form>
		</div>
	</body>
</html>