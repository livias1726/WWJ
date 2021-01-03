<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.JobBean"
		import="logic.bean.CountryBean"
		import="logic.bean.OfferBean"%>
    
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="jobBean" scope="session" class="logic.bean.JobBean"/>
<jsp:setProperty name="jobBean" property="*"/>

<jsp:useBean id="offerBean" scope="session" class="logic.bean.OfferBean"/>
<jsp:setProperty name="offerBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>

<html lang="en">
<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">

		<title>WorldWideWeb - offerDetails</title>

	</head>
	<body>
		<div>
			<form action="offerDetails.jsp" name="offerDetailsform" method="POST">
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
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='JobSeekerResearchResult.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="place2" type="text" name="place2" value="" disabled style="background-color:white">
	    		</div>
	    		<div class="scheletro_offer_det"><%=jobBean.getName()%><br>
	    			<div style="margin-top: 40px">
				    	<label for="position" style="color:black;font-size:20px;margin-left:15px">Position</label>
				        <input type="text" id="position" name="position" value="<%=offerBean.getPosition()%>" disabled style="margin-left: 40px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="qualificationNeeded" style="color:black;font-size:20px;margin-left:15px">Qualification needed</label>
				        <input type="text" id="qualificationNeeded" name="qualificationNeeded" style="margin-left: 40px;overflow-x:scroll;overflow-y:scroll">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="activitiesDescription" style="color:black;font-size:20px;margin-left:15px">Activities description</label><br>
				        <input type="text" id="activitiesDescription" name="activitiesDescription" style="margin-left: 40px;overflow-x:scroll;overflow-y:scroll;margin-top:10px">
				    </div>
				    <div style="margin-top:-290px;margin-right:20px;float:right">
				    	<button class="star_btn" style="width:40px;height:40px;margin-left:1300px;margin-top:-2500px;background-color:transparent"></button> 
				    </div><br>
				    <div style="margin-top:-130px;margin-right:130px;float:right">
				    	<button class="map-offer_btn" style="width:40px;height:40px;margin-left:1300px;margin-top:-2500px;background-color:transparent"></button>
				    </div>   
				    <div style="margin-top:-270px;float:right;margin-right:100px;colore:grey;font-style:bold;font-size:20px;padding-right:40px;padding-top:50px">Additional Information<br>
				    	<div style="margin-top: 40px;margin-right:-40px">
				    		<label for="companyBranch" style="color:black;font-size:20px">Company branch</label>
				        	<input type="text" id="companyBranch" name="companyBranch" style="height:40px"></div>
				    	<div style="margin-top: 40px;margin-right:-40px">
				    		<label for="timeSlot" style="color:black;font-size:20px">Time slot</label>
				        	<input type="text" id="timeSlot" name="timeSlot" style="width:60px"><input type="text" id="timeSlot" name="timeSlot" style="width:60px;margin-left:10px">
				    	</div>
				    	<div style="margin-top: 40px;margin-right:-40px">
				    		<label for="baseSalary" style="color:black;font-size:20px">Base salary</label>
				        	<input type="text" id="baseSalary" name="baseSalary">
				    	</div>
				    	<div style="margin-top:-30px;float:right;margin-right:100px;colore:grey;font-style:bold;font-size:20px;padding-right:40px;padding-top:50px">Others<br>
				    		<div style="margin-top: 40px;margin-right:-40px">
				    			<label for="expirationDate" style="color:black;font-size:20px">Expiration date</label>
				        		<input type="text" id="expirationDate" name="expirationDate">
				    		</div>
				    	</div><br>
				    	<button class="search_btn" style="width:100px; height:50px; top:40px; background-color: dodgerblue; margin-right:100px">Apply</button>
				    </div>
				</div>
	    	</form>
	    </div>
</body>
</html>