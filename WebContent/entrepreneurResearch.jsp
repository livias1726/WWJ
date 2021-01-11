<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<jsp:useBean id="businessBean" class="logic.presentation.bean.BusinessBean" scope="session"/>
<jsp:setProperty name="businessBean" property="*"/>

<jsp:useBean id="countryBean" class="logic.presentation.bean.CountryBean" scope="session"/>
<jsp:setProperty name="countryBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>

<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>WorldWideJob - entrepreneurResearch</title>
</head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="icon" href="icons/main_icon.png">
	<link href="css/style.css" rel="stylesheet">
<body>
	<div>
			<form action="entrepreneurResearch.jsp" name="researchEntrepreneurform" method="POST">
	    		<div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div class="dropdown-content" style="right:0;">
		     		<a href="http://localhost:8080/WorldWideJob/login.jsp">Login</a>
		     	</div>
		     	</div>
	    		<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		</div>
	    		</div>
	    		<div class="search_entr">Become your own boss.<br>Start a new business in the Country of your dreams.<br>
	    		<% if (request.getParameter("search") != null && (!request.getParameter("place").equals("") || !request.getParameter("business").equals(""))) {
					countryBean.setName(request.getParameter("place"));
					businessBean.setName(request.getParameter("business"));
					String redirectURL = "http://localhost:8080/WorldWideJob/entrResearchResult.jsp";
        			response.sendRedirect(redirectURL);
    			}
				if (request.getParameter("search") != null && (request.getParameter("place").equals("") && request.getParameter("business").equals(""))){%>
						<script>window.alert("Warning! Enter the country or business you are interested in.")</script>
				<%}%>
				<div style="float:left;margin-top:157px;margin-left:240px;">
	    			<img alt="" class="image" src="icons/map.png" width=30px height=29px>
	    		</div>
	    		<select class="order_select" name="place" style="background-color:white;width:250px;height:30px;margin-left:162px;margin-right:130px;margin-top:130px">
	    			<option>Place</option>
	    			<option><%=countryBean.getCountries()%></option>
	    		</select> 
	    		<div style="float:left;margin-top:157px;margin-left:440px;color:white;">
	    			<img alt="" class="image" src="icons/search.png" width=30px height=29px>
	    		</div>
	    		<select class="order_select" name="business" style="background-color:white;width:250px;height:30px;margin-right:200px;margin-left:632px;margin-top:130px">
	    			<option>Businesses</option>
	    			<option><%=businessBean.getBusinesses()%></option>
	    		</select> 
	    		<button class="search_btn" type="submit" name="search" style="width:100px; height:50px; top:300px; background-color: dodgerblue; margin-left:-750px; border-color: black">Search</button>
	    		</div>
	    	</form>
		</div>
	</body>
</html>

