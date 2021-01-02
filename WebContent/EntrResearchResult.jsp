<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="logic.bean.BusinessBean"
		import="logic.bean.CountryBean"%>
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="businessBean" scope="session" class="logic.bean.BusinessBean"/>
<jsp:setProperty name="businessBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>


<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<title>WorldWideJob - EntrepreneurResearchResult</title>
	</head>
	
	<body>
		<div>
			<form action="jobSeekerResearchResult.jsp" name="researchresultjobseekform" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="">Quit</a>
		     		</div>
		     	</div>		
		        <div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Login</a>
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
	    		<input class="place2" type="text" name="place2" value="<%=countryBean.getName()%>" disabled style="background-color:white">
	    		<input class="joboffers" type="text" name="businesses" value="BUSINESSES" disabled style="background-color:dodgerblue">
	    		<input class="line" style="background-color:black">
	    		<input class="offer" type="text" name="business" value="<%=businessBean.getName()%>" disabled style="background-color:#E4F5F2" >
	    		<input class="order_by" type="text" name="orderby" value="  Ordered by:" disabled style="background-color:lightgrey">
	    		<select class="order_select" name="orderselect" size="1" style="background-color:lightgrey">
	    			<option>popularity</option>
	    			<option>alphabetical order</option>
	    		</select> 
	    		<fieldset style="background-color:lightgrey;position: absolute;width:230px;height:140px;left:20px;top:300px;">
	    		<legend>Category:</legend>
	    		<input type="radio" name="category">Arts and Entertainment<br>
	    		<input type="radio" name="category">Education<br>
	    		<input type="radio" name="category">Food services<br>
	    		<input type="radio" name="category">Health care and Wellness<br>
	    		<input type="radio" name="category">Manufacturing<br>
	    		<input type="radio" name="category">Trade
	    		</fieldset>
	    		</div>
	    	</form>	
	    </div>
	</body>
</html>