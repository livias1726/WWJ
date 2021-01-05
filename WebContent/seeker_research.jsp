<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.JobBean"
		import="logic.bean.CountryBean"%>
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="jobBean" scope="session" class="logic.bean.JobBean"/>
<jsp:setProperty name="jobBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css?ts=<?=time()?>&quot" rel="stylesheet">
		
		<title>WorldWideJob - seeker_research</title>
	</head>
	<body>
		<div>
			<form action="seeker_research.jsp" method="POST" name="researchjobseekform">
				<div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div class="dropdown-content" style="right:0;">
		     		<a href="http://localhost:8080/WorldWideJob/login.jsp">Login</a>
		     	</div>
		     	</div>
	    		<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		</div>
	    		</div>
	    		<div class="search_usr">Find the right job for you,<br>In the place you've always dreamed of.<br>
	    		<%if (request.getParameter("search") != null && (!request.getParameter("place").equals("") || !request.getParameter("job").equals(""))){
					countryBean.setName(request.getParameter("place"));
					jobBean.setName(request.getParameter("job"));
					String redirectURL = "http://localhost:8080/WorldWideJob/JobSeekerResearchResult.jsp";
    				response.sendRedirect(redirectURL);
  				} 
	    		if (request.getParameter("search") != null && (request.getParameter("place").equals("") && request.getParameter("job").equals(""))){%>
  					<script>window.alert("Warning! Enter the country and job you are interested in.")</script>
				<%}%>
	    		<select class="order_select" name="place" style="background-color:white;width:250px;height:30px;margin-left:200px;margin-right:130px;margin-top:130px">
	    			<option>Place</option>
	    			<option><%=countryBean.getCountries()%></option>
	    		</select> 
	    		<select class="order_select" name="job" style="background-color:white;width:250px;height:30px;margin-right:200px;margin-left:750px;margin-top:130px">
	    			<option>Job</option>
	    			<option><%=jobBean.getJobs()%></option>
	    		</select> 
	    		<button class="search_btn" type="submit" name="search" style="width:100px; height:50px; top:300px; background-color: dodgerblue; margin-right:-20px; border-color: black">Search</button>
	    		</div>
	    	</form>
		</div>
	</body>
</html>