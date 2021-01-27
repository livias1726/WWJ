<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.StringTokenizer"%>	
  
<%@ page import="logic.presentation.bean.BusinessInCountryBean"
		 import="logic.presentation.bean.CountryBean"
		 import="logic.application.control.ManageFavouriteBusinessesControl"%>

<%@page errorPage="WEB-INF/error.jsp"%>
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.presentation.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="businessResult" scope="session" class="logic.presentation.bean.BusinessInCountryBean"/>
<jsp:setProperty name="businessResult" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>
	
<%if(request.getParameter("business") != null){
	String res = request.getParameter("business");
	StringTokenizer tok = new StringTokenizer(res, "&");
	
	businessResult.setId(Integer.parseInt(tok.nextToken()));
	businessResult.setName(tok.nextToken());
	businessResult.setCategory(tok.nextToken());
	businessResult.setDescription(tok.nextToken());
	
	countryBean.setName(tok.nextToken());
	countryBean.setCurrency(tok.nextToken());
	businessResult.setCountry(countryBean);
	
	businessResult.setAverageEarnings(Float.parseFloat(tok.nextToken()));
	businessResult.setAverageCost(Float.parseFloat(tok.nextToken()));
	
	String redirectURL = "http://localhost:8080/WorldWideJob/business_details.jsp";
	response.sendRedirect(redirectURL); 
}%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
	    <link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<title>WorldWideJob</title>
		
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="favourite_businesses.jsp" name="favouriteBusiness" method="POST">	
    			<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
	    		<select class="order_select" id="order" name="orderselect">
	    			<option>Earnings</option>
	    			<option>Costs</option>
	    		</select></p>
	    		<div>
	    			<fieldset class="result_box" style="background-color:white;">
			    		<legend class="research_title">FAVOURITES</legend>
			    			<ul id="res" style="list-style-type:none;">
			    			<%for(BusinessInCountryBean i: ManageFavouriteBusinessesControl.getInstance().retrieveFavourites()){%>
			    				<li>
			    				<button id="res_btn" class="result" type="submit" name="business" value="<%=
				    				i.getId() + "&" + i.getName() + "&" + i.getCategory() + "&" +
				    				i.getDescription() + "&" + i.getCountry().getName() + "&" + 
				    				i.getCountry().getCurrency() + "&" + i.getAverageEarnings() + "&" +
				    				i.getAverageCost()%>"> <%=i.getName()+ " - " +i.getCountry().getName()%>
				    			</button>
			    				<button id="cost" style="display:none;" value="<%=i.getAverageCost()%>"></button>
			    				<button id="earn" style="display:none;" value="<%=i.getAverageEarnings()%>"></button>
			    				</li>
			    			<%}%>  
			    			</ul>
		    		</fieldset>	
	    		</div> 
			</form>
		</div>
	</body>
	<script src="js/results.js"></script>
	<script src="js/toolbar.js"></script>
</html>