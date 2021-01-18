 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="logic.presentation.bean.BusinessBean"
		  import="logic.presentation.bean.CountryBean"
		  import="logic.presentation.bean.BusinessInCountryBean"
		  import="logic.application.control.ViewResultsControl"
		  import="logic.application.control.ViewBusinessControl"%>
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.presentation.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

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
		<div>
			<form action="business_results.jsp" name="researchresultjobseekform" method="POST">
	    		<div>
	    			<h1 style="text-align:center; text-shadow:-1px -1px 0 #000,1px -1px 0 #000,-1px 1px 0 #000,1px 1px 0 #000;">BUSINESSES</h1>
		    		<%if(businessBean.getCategory() == null) {%>
		    			<input class="order_by" type="text" name="orderby" value="Order by:" disabled style="background-color:lightgrey">
			    		<select class="order_select" id="order" name="orderselect" style="background-color:lightgrey">
			    			<option>Earnings</option>
			    			<option>Costs</option>
			    		</select>
		    			<input class="result_label" name="resultLbl" value="<%=countryBean.getName()%>">
		    			<ul id="res">
		    			<%for(BusinessInCountryBean i: ViewBusinessControl.getInstance().retrieveBusinessesByCountry(countryBean)){%>
		    				<li><button class="result" type="submit" name="business" value="<%=i%>"> <%=i.getName()+ " - " +i.getCountry().getName()%> </button></li>
		    			<%}%>    
		    			</ul>			
		    			<fieldset style="background-color:lightgrey;position: absolute;width:230px;height:140px;left:20px;top:300px;">
				    		<legend>Category:</legend>
				    			<%for(BusinessBean i: ViewBusinessControl.getInstance().retrieveBusinesses()){%>
				    				<input type="checkbox" id="cat1" name="cat1" value="<%=i.getCategory()%>" onclick="filterResults()">
  									<label for="cat1"><%=i.getCategory()%></label><br>
				    			<%}%>
			    		</fieldset>
		    		<%}%>
		    		
		    		<!-- else if(countryBean.getName() == null) {%>
		    			<input class="order_by" type="text" name="orderby" value="Ordered by:" disabled style="background-color:lightgrey">
			    		<select class="order_select" name="orderselect" size="1" style="background-color:lightgrey">
			    			<option>Earnings</option>
			    			<option>Costs</option>
			    		</select>
		    			<a class="offer" type="submit" href="http://localhost:8080/WorldWideJob/businessDetails.jsp"  style="background-color:#E4F5F2"></a>
		    			<input class="place2" name="place2" value="" style="background-color:white">
		    			<fieldset style="background-color:lightgrey;position: absolute;width:230px;height:140px;left:20px;top:300px;">
				    		<legend>Countries:</legend>
					    		<input type="radio" name="category">Arts and Entertainment<br>
			    		</fieldset>
		    		}else{%>
		    			<a class="offer" type="submit" href="http://localhost:8080/WorldWideJob/businessDetails.jsp"  style="background-color:#E4F5F2"></a>
		    			<input class="place2" name="place2" value="" style="background-color:white">
		    		}%> -->
	    		
	    		<%if (request.getParameter("business") != null){
					businessBean.setName(request.getParameter("business"));
					String redirectURL = "http://localhost:8080/WorldWideJob/businessDetails.jsp";
    				response.sendRedirect(redirectURL); 
    			 }%>
	    		</div>
	    	</form>	
	    </div>
	</body>
	<script src="js/business_results.js"></script>
</html>