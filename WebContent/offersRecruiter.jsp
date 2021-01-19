<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.application.control.RecruiterAccountControl" 
	import="logic.presentation.bean.OfferBean"
	import="java.util.List"
	import="javafx.collections.ObservableList"
%>    

<jsp:useBean id="offerBean" class="logic.presentation.bean.OfferBean" scope="session"/>
<jsp:setProperty name="offerBean" property="*"/>
    
<!DOCTYPE html>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
		
		<title>WorldWideJob - offersRecruiter</title>
	</head>
	
	<body>
		<div>
			<form action="offersRecruiter.jsp" name="offersRecruiter" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/publish.jsp">Publish Job Offer</a>
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/index.jsp">Quit</a>
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
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;" onClick="javascript:window.location='recruiterProfile.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='recruiterProfile.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="offers_container" value="" disabled style="background-color:#C6D6D3">
	    		<input class="favourite_title" type="text" name="offers_title" value="Offers" disabled style="background-color:#C6D6D3">
	    		<fieldset style="background-color:#C6D6D3;position:absolute;width:300px;height:30px;left:20px;top:170px;border:0">
	    		<input type="radio" name="number">All
	    		<input type="radio" name="number">Active
	    		<input type="radio" name="number">Expired
	    		</fieldset>
	    	
	    		<table border="0" id='offerTable' style="table-layout:fixed; width:900px;position:absolute;left:230px;top:250px;background-color:white">
	    			<thead>
      						<tr>
         						<th style="height:30px; color:black; background-color:dodgerblue">NUMBER</th>
         						<th style="height:30px; color:black; background-color:dodgerblue">POSITION</th>
         						<th style="height:30px; color:black; background-color:dodgerblue">UPLOAD DATE</th>
         						<th style="height:30px; color:black; background-color:dodgerblue">EXPIRATION DATE</th>
         						<th style="height:30px; color:black; background-color:dodgerblue">CANDIDATES</th>
      						</tr>
      						<tr>
      							<td style="height:20px"></td>
         						<td></td>
         						<td></td>
         						<td></td>
         						<td></td>
   							</tr>
   							<tr>
      							<td style="height:20px"></td>
         						<td></td>
         						<td></td>
         						<td></td>
         						<td></td>
   							</tr>
   							<tr>
      							<td style="height:20px"></td>
         						<td></td>
         						<td></td>
         						<td></td>
         						<td></td>
   							</tr>
      					</thead>
      					<tbody>
      					</tbody>
      			</table>
      			
	    		<button class="delete_btn" style="width:100px; height:50px; top:500px; left:400px; background-color:dodgerblue">Delete</button>
	    		</div>
	    	</form>
	    </div>
	</body>
</html>