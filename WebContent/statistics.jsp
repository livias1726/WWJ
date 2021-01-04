<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.BusinessBean" %> 
   
<!DOCTYPE html>

<jsp:useBean id="businessBean" scope="session" class="logic.bean.BusinessBean"/>
<jsp:setProperty name="businessBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>


<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="css/style.css?ts=<?=time()?>&quot" rel="stylesheet">
	<title>WorldWideJob - statistics</title>
</head>
<body>
	<div>
			<form action="statistics.jsp" name="statisticsform" method="POST">
				<div style="float:right;">
					<div class="dropdown" style="float:right;">
						<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
					<div class="dropdown-content" style="right:0;">
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
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='businessDetails.jsp';"></button>
	    		</div>
	    		</div>
	    		<div class="scheletro_signUp" style="top:70px; left:5px;">Statistics<br>
	    			<div style="margin-top: 20px; margin-left:-30px">
	    				<input type="text" id="businessName" name="businessName" disabled value="<%=businessBean.getName()%>" style="width:300px; height:40px;margin-top:10px;margin-left:30px">
	    			</div>
	    			<div style="margin-top: 40px;margin-left:-30px">
				    	<label for="popularity" style="color:black;font-size:20px;margin-left:45px">Popularity over the years</label>
	    			</div>
	    			<div class="orizzontale"></div>
	    			<hr width="1" size="300" style="background-color:black;margin-left:300px;margin-top:-300px">
	    			<div style="margin-top: 40px;margin-left:-30px">
				    	<label for="costs" style="color:black;font-size:20px;margin-left:45px">Average earnings and costs</label>
	    			</div>
	    			<div class="orizzontale"></div>
	    			<hr width="1" size="300" style="background-color:black;margin-left:300px;margin-top:-300px">
	    			<div style="margin-top: -1000px; margin-left:700px">
				    	<label for="budget" style="color:black;font-size:20px;margin-left:150px">Insert budget</label>
				        <input type="text" id="budget" name="budget" disabled style="margin-left: 40px;height:30px;width:130px">
				    </div><br>
				    <div style="margin-top:-160px">
				    <button style="width:150px; height:50px; background-color: lightblue; margin-left:1200px;margin-top:-200px">Calculate feasibility</button>
	    			</div>
	    			<div style="margin-top: 40px;margin-left:-30px">
				    	<label for="competitors" style="color:black;font-size:20px;margin-left:800px">Competitors</label>
	    			</div>
	    			<div class="orizzontale" style="margin-left:1000px"></div>
	    			<hr width="1" size="300" style="background-color:black;margin-left:1000px;margin-top:-300px">
	    		</div>
	    	</form>
	    </div>

</body>
</html>