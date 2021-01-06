<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.BusinessBean"
		import="logic.bean.BusinessInCountryBean"%>

<!DOCTYPE html>

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
		
		<title>WorldWideJob - FavouriteBusiness</title>
		
	</head>
	<body>
		<div>
			<form action="favouriteBusiness.jsp" name="favouriteBusiness" method="POST">
				<div class="dropdown" style="float:right;">
	    				<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/entrepreneur_research.jsp">Quit</a>
		     		</div>
		     	</div>
		     	<div style="float:right;">
	    			<button class="notify_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/entrepreneur'sProfile.jsp">Account</a>
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
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='entrepreneur'sProfile.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="favourite_container" value="" disabled style="background-color:#C6D6D3">
	    		<input class="favourite_title" type="text" name="favourite_title" value="Favourite business" disabled style="background-color:#C6D6D3">
	    		<input class="favouritejoboffer_container" type="text" name="favouritebusiness_container" value="<%=businessInCountryBean.getFavouriteBusinesses()%>" disabled style="text-align:center;background-color:#56A6F0">
	    		<button class="star_btn" name="star" style="width:40px;height:40px;margin-left:725px;margin-top:145px;background-color:transparent"></button> 
	    		<% if (request.getParameter("star") != null){%>
	    			<input class="favouritejoboffer_container" type="text" name="favouritebusiness_container" value="" disabled style="text-align:center;background-color:#56A6F0">
  					<script>window.alert("The business has been delete to your favorites.")</script>
				<%}%>
	    		<input class="order_by" type="text" name="orderby" value="  Ordered by:" disabled style="background-color:lightgrey">
	    		<select class="order_select" name="orderselect" size="1" style="background-color:whitesmoke">
	    			<option>popularity</option>
					<option>alphabetical order</option>
				</select>	
	    		</div>	
			</form>
		</div>

	</body>
</html>