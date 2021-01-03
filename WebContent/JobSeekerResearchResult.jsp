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
	    <link href="css/style.css" rel="stylesheet">
	
		<title>WorldWideJob - JobSeekerResearchResult</title>
	</head>
	
	<body>
		<div>
			<form action="JobSeekerResearchResult.jsp" name="researchresultjobseekform" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/seeker_research.jsp">Quit</a>
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
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='seeker_research.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<%if (countryBean.getName() != null && jobBean.getName() != null) {%>
	    			<input class="place2" id="name_place" type="text" name="name_place" disabled value="<%=countryBean.getName()%>">
	    			<input class="offer" id="offer" type="submit" name="offer" value="<%=jobBean.getName()%>" style="background-color:#E4F5F2">
	    		<%}else if (countryBean.getName() == null && jobBean.getName() != null) {%>
	    			<input class="offer" id="offer" type="submit" name="offer" value="<%=jobBean.getJobs()%>" style="background-color:#E4F5F2">
	    		<%}else if (countryBean.getName() != null && jobBean.getName() == null) {%>
	    			<input class="place2" id="name_place" type="text" name="name_place" disabled value="<%=countryBean.getCountries()%>">
	    		<%}%>
	    		
	    		<% if (request.getParameter("offer") != null){
					jobBean.setName(request.getParameter("offer"));
					String redirectURL = "http://localhost:8080/WorldWideJob/offerDetails.jsp";
    				response.sendRedirect(redirectURL); }%>
	    		
	    		<input class="joboffers" type="text" name="joboffers" value="JOB OFFERS" disabled style="background-color:dodgerblue">
	    		<input class="line" style="background-color:black">
	    		<input class="order_by" type="text" name="orderby" value="  Ordered by:" disabled style="background-color:lightgrey">
	    		<select class="order_select" name="orderselect" size="1" style="background-color:whitesmoke">
	    			<option>most recent</option>
					<option>alphabetical order</option>
	    		</select> 
	    		<fieldset style="background-color:lightgrey;position: absolute;width:230px;height:140px;left:20px;top:300px;">
	    		<legend>Category:</legend>
	    		<input type="radio" name="category">Consumer goods<br>
	    		<input type="radio" name="category">Financials<br>
	    		<input type="radio" name="category">Health care<br>
	    		<input type="radio" name="category">Industrials<br>
	    		<input type="radio" name="category">Retail<br>
	    		<input type="radio" name="category">Technology
	    		</fieldset>
	    		</div>
	    	</form>	
	    </div>
	</body>
</html>