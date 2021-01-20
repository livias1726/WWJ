<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.BusinessBean" %> 
<%@page errorPage="WEB-INF/error.jsp"%>
   
<!DOCTYPE html>

<jsp:useBean id="businessBean" scope="session" class="logic.presentation.bean.BusinessBean"/>
<jsp:setProperty name="businessBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="icon" href="icons/main_icon.png">
    <link href="css/style.css" rel="stylesheet">

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
   	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
   	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
	<title>WorldWideJob</title>
</head>
<body>	
	<jsp:include page="WEB-INF/toolbar.jsp"/>
	<div style="background-color:#C6D6D3">
		<form action="statistics.jsp" name="statisticsform" method="POST">
		
	    		<div class="scheletro_signUp" style="top:70px; left:5px;">Statistics<br>
	    			<div style="margin-top: 20px; margin-left:-30px">
	    				<input type="text" id="businessName" name="businessName" disabled value="<%=businessBean.getName()%>" style="width:300px; height:40px;margin-top:10px;margin-left:30px">
	    			</div>
	    			<div style="margin-top: 40px;margin-left:-30px">
				    	<label for="popularity" style="color:black;font-size:20px;margin-left:45px;top:20px">Popularity over the years</label>
	    			</div>
	    			<div class="orizzontale" style="margin-left:50px;"></div>
	    			<hr width="1" size="300" style="background-color:black;margin-left:50px;margin-top:-300px">
	    			<div style="margin-top: 40px;margin-left:250px">
				    	<label for="costs" style="color:black;font-size:20px;margin-left:45px">Average earnings and costs</label>
	    			</div>
	    			<div class="orizzontale"></div>
	    			<hr width="1" size="300" style="background-color:black;margin-left:300px;margin-top:-300px">
	    			<div style="margin-top: -1000px; margin-left:700px">
				    	<label for="budget" style="color:black;font-size:20px;margin-left:40px">Insert budget</label>
				    	<input class="budget" type="text" id="budget" name="budget" value="" style="background-color:#E4F5F2; margin-left:20px; top:0px;height:30px;width:140px">
				    </div><br>
				    <div style="margin-top:-160px">
				    <button class="calculatefeasibility_btn" name="calcFeas" style="width:150px; height:50px; background-color: dodgerblue;border-color:blac;margin-left:1020px;margin-top:-200px">Calculate feasibility</button>
	    			<% if (request.getParameter("calcFeas") != null && !request.getParameter("budget").equals("")) {
					String redirectURL = "http://localhost:8080/WorldWideJob/feasibility.jsp";
        			response.sendRedirect(redirectURL);
    				}
	    			if(request.getParameter("calcFeas") != null && request.getParameter("budget").equals("")) { %>
						<script>window.alert("Warning! Enter your budget.")</script>
					<%}%>
	    			</div>
	    			<div style="margin-top: 40px;margin-left:-30px">
				    	<label for="competitors" style="color:black;font-size:20px;margin-left:700px">Competitors</label>
	    			</div>
	    			<div class="orizzontale" style="margin-left:700px"></div>
	    			<hr width="1" size="300" style="background-color:black;margin-left:700px;margin-top:-300px">
	    		</div>
	    </form>
	</div>
</body>
</html>