<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="logic.bean.BusinessBean"
 	import="logic.bean.CountryBean"
 	import="logic.bean.BusinessInCountryBean"%>
		
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="businessBean" scope="session" class="logic.bean.BusinessBean"/>
<jsp:setProperty name="businessBean" property="*"/>

<jsp:useBean id="businessInCountryBean" scope="session" class="logic.bean.BusinessInCountryBean"/>
<jsp:setProperty name="businessInCountryBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>

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
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/entrepreneur_research.jsp">Quit</a>
		     		</div>
		     	</div>		
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
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='EntrResearchResult.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="place2" type="text" name="place2" value="<%=countryBean.getName()%>" disabled style="background-color:white">	
	    		<input class="busdetails" type="text" name="busdetails" value="" disabled style="background-color:#C6D6D3">
	    		<input class="businessname" type="text" name="businessname" value="<%=businessBean.getName()%>" disabled style="background-color:#C6D6D3">
	    		<% if (request.getParameter("businessname") != null){
					businessBean.setName(request.getParameter("businessname"));
					String redirectURL = "http://localhost:8080/WorldWideJob/statistics.jsp";
    				response.sendRedirect(redirectURL);}%>
	    		<input class="thebusiness" type="text" name="thebusiness" value="The business" disabled style="background-color:#C6D6D3">
	    		<input class="busdescription" type="text" name="busdescription" value="<%=businessInCountryBean.getDescription()%>" disabled style="background-color:#E4F5F2">
	    		<input class="insert_budget" type="text" name="insert_budget" value="Insert budget" disabled style="background-color:#C6D6D3"><br>
	    		<input class="budget" type="text" name="budget" id="budget" value="" style="background-color:#E4F5F2">
	    		<button class="statistics_btn" type="button" style="width:150px; height:50px; top:10px; right:-30px;background-color: dodgerblue; margin-left:1050px; border-color: black" onClick="javascript:window.location='statistics.jsp';">View statistics</button>
	    		<button class="calculatefeasibility_btn" name="calcFeas" style="width:150px; height:50px; top:500px; background-color: dodgerblue; margin-left:350px; border-color: black">Calculate feasibility</button>
	    		<% if (request.getParameter("calcFeas") != null && !request.getParameter("budget").equals("")) {
					String redirectURL = "http://localhost:8080/WorldWideJob/Feasibility.jsp";
        			response.sendRedirect(redirectURL);
    				}
	    			if(request.getParameter("calcFeas") != null && request.getParameter("budget").equals("")) { %>
						<script>window.alert("Warning! Enter your budget.")</script>
				<%}%>
				<button class="star_btn" name="star" style="width:40px;height:40px;margin-left:1180px;margin-top:-600px;background-color:transparent"></button> 
				<% if (request.getParameter("star") != null){
					businessBean.setName(request.getParameter("businessname"));%>
  					<script>window.alert("The business has been added to your favorites.")</script>
				<%}%>
				</div>
	    	</form>
		</div>
	</body>
</html>