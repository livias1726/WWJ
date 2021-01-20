<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="logic.presentation.bean.BusinessInCountryBean"
		 import="logic.application.control.CalculateFeasibilityControl"%> 
		 
<%@page errorPage="WEB-INF/error.jsp"%>
   
<!DOCTYPE html>

<jsp:useBean id="businessResult" scope="session" class="logic.presentation.bean.BusinessInCountryBean"/>
<jsp:setProperty name="businessResult" property="*"/>

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
   	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<title>WorldWideJob</title>
</head>
<body>	
	<jsp:include page="WEB-INF/toolbar.jsp"/>
	<div style="background-color:#C6D6D3">
		<form action="statistics.jsp" name="statisticsform" method="POST">
			<label class="research_title" style="margin-left:40%;font-size:30px"><%=businessResult.getName()%> - Statistics</label>
			
			<!-- FEASIBILITY -->
    		<div style="margin-left:800px;">
    			<label for="budget">Insert budget:</label>
	    		<input class="budget" type="text" name="budget" id="budget">
	    		<select name="curr" id="curr" style="height:40px;width:40px;">
				    <option value="$">$</option>
				    <option value="&pound">&pound;</option>
				    <option value="&euro">&euro;</option>
				</select>
	    		<button class="details_buttons" name="calcFeas">Calculate feasibility</button>
	    		<%if(request.getParameter("calcFeas") != null){
	    			if (!request.getParameter("budget").equals("")) {
    		  			CalculateFeasibilityControl.getInstance().retrieveBusinessFeasibility(businessResult, request.getParameter("budget"));
	    				String redirectURL = "http://localhost:8080/WorldWideJob/feasibility.jsp";
	        			response.sendRedirect(redirectURL);
    				}else{ %>
    					<script>window.alert("Warning! Enter your budget.")</script>
    			  <%}
	    		 }%>
    		</div>
    		
    		<!-- CHARTS -->
    		<h2 style="text-align:center">Popularity over the years</h2>
   			<div class="charts" id="chart_pop"></div>
   			
   			<h2 style="text-align:center">Average earnings and costs</h2>
   			<div class="charts" id="chart_avg"></div>
   
   			<h2 style="text-align:center">Competitors</h2>
   			<div class="charts" id="chart_comp"></div>
   			
   			<button id="pop_list" style="display:none;" value="<%=
   				businessResult.getPopularity().get(0) + "$$" +
   	   			businessResult.getPopularity().get(1) + "$$" +
   	   			businessResult.getPopularity().get(2) + "$$" +
   	   			businessResult.getPopularity().get(3) + "$$" +
   	   			businessResult.getPopularity().get(4)%>"></button>
 			<button id="avg_list" style="display:none;" value="<%=
 				businessResult.getAverageEarnings() + "$$" + 
 				businessResult.getAverageCost() + "$$" + 
 				businessResult.getCountry().getCurrency()%>"></button>
 			<button id="comp_list" style="display:none;" value="<%=
 				businessResult.getCompetitors().get(0) + "$$" +
 				businessResult.getCompetitors().get(1) + "$$" +
 				businessResult.getCompetitors().get(2) + "$$" +
 				businessResult.getCompetitors().get(3) + "$$" +
 				businessResult.getCompetitors().get(4)%>"></button>
	    </form>
	</div>
</body>
<script src="js/statistics.js"></script>
</html>