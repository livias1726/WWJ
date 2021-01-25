 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="java.util.StringTokenizer"%>	
   
 <%@ page import="logic.presentation.bean.BusinessBean"
		  import="logic.presentation.bean.CountryBean"
		  import="logic.presentation.bean.BusinessInCountryBean"
		  import="logic.application.control.ViewResultsControl"
		  import="logic.application.control.ViewBusinessControl"
		  import="logic.exceptions.NoResultFoundException"%>
		  
<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.presentation.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="businessBean" scope="session" class="logic.presentation.bean.BusinessBean"/>
<jsp:setProperty name="businessBean" property="*"/>

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
    	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    	
    	<script src="js/results.js"></script>
		<script src="js/toolbar.js"></script>
		<title>WorldWideJob</title>
	</head>	
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="background-color:#AED6F1;">
			<form action="business_results.jsp" name="businessresultform" method="POST">
	    		
    			<!-- COUNTRY RESEARCH -->
	    		<%if(businessBean.getCategory() == null) {%>
	    			<p><input class="result_label" name="resultLbl" value="<%=countryBean.getName()%>"></p>
	    			<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
		    		<select class="order_select" id="order_bus" name="orderselect">
		    			<option>Earnings</option>
		    			<option>Costs</option>
		    		</select></p>
		    		<div>
		    			<fieldset class="result_box">
				    		<legend class="research_title">BUSINESSES</legend>
				    			<ul id="res" style="list-style-type:none;">
				    			<%try{
					    			for(BusinessInCountryBean i: ViewBusinessControl.getInstance().retrieveBusinessesByCountry(countryBean)){%>
					    				<li>
					    				<button id="res_btn" class="result" type="submit" name="business" value="<%=
						    				i.getId() + "&" + i.getName() + "&" + i.getCategory() + "&" +
						    				i.getDescription() + "&" + i.getCountry().getName() + "&" + 
						    				i.getCountry().getCurrency() + "&" + i.getAverageEarnings() + "&" +
						    				i.getAverageCost()%>"> <%=i.getName()+ " - " +i.getCountry().getName()%>
						    			</button>
										<button id="cost" style="display:none;" value="<%=i.getAverageCost()%>"></button>
					    				<button id="earn" style="display:none;" value="<%=i.getAverageEarnings()%>"></button>
					    				<button id="cat" style="display:none;" value="<%=i.getCategory()%>"></button>
										</li>
									<%} 
				    			  }catch(NoResultFoundException e){%>
				    				<script>window.alert("No result found.")</script>				    			
				    			<%}%>    
				    			</ul>
			    		</fieldset>	
		    		</div>		    			    					
	    			<fieldset class="filter_box" id="filters">
			    		<legend style="font-weight:bold">Categories</legend>
			    			<%for(BusinessBean i: ViewBusinessControl.getInstance().retrieveBusinesses()){%>
			    				<input type="checkbox" id="cat1" name="category" value="<%=i.getCategory()%>" onclick="filterResults()"><%=i.getCategory()%>
			    				<p></p>
			    			<%}%>
		    		</fieldset>
		    		
		    	<!-- BUSINESS RESEARCH -->
	    		<%}else if(countryBean.getName() == null) {%>
	    			<p><input class="result_label" name="resultLbl" value="<%=businessBean.getCategory()%>"></p>
	    			<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
		    		<select class="order_select" id="order_bus" name="orderselect">
		    			<option>Earnings</option>
		    			<option>Costs</option>
		    		</select></p>
		    		<div>
		    			<fieldset class="result_box">
				    		<legend class="research_title">BUSINESSES</legend>
				    			<ul id="res" style="list-style-type:none;">
				    			<%businessResult.setCategory(businessBean.getCategory());
				    			  try{
					    			for(BusinessInCountryBean i: ViewBusinessControl.getInstance().retrieveBusinessesByCategory(businessResult)){%>
					    				<li>
					    				<button id="res_btn" class="result" type="submit" name="business" value="<%=
						    				i.getId() + "&" + i.getName() + "&" + i.getCategory() + "&" +
						    				i.getDescription() + "&" + i.getCountry().getName() + "&" + 
						    				i.getCountry().getCurrency() + "&" + i.getAverageEarnings() + "&" +
						    				i.getAverageCost()%>"> <%=i.getName()+ " - " +i.getCountry().getName()%>
						    			</button>
					    				<button id="cost" style="display:none;" value="<%=i.getAverageCost()%>"></button>
					    				<button id="earn" style="display:none;" value="<%=i.getAverageEarnings()%>"></button>
					    				<button id="cat" style="display:none;" value="<%=i.getCountry().getName()%>"></button>
					    				</li>
				    			  <%}
					    		  }catch(NoResultFoundException e){%>
					    				<script>window.alert("No result found.")</script>				    			
					    		<%}%>     
				    			</ul>
			    		</fieldset>	
		    		</div>		    			    					
	    			<fieldset class="filter_box" id="filters">
			    		<legend style="font-weight:bold">Countries</legend>
				    		<%for(String i: ViewResultsControl.getInstance().retrieveCountries()){%>
			    				<input type="checkbox" id="cat1" name="cat1" value="<%=i%>" onclick="filterResults()"><%=i%>
			    				<p></p>
			    			<%}%>
		    		</fieldset>
	    	
		    	<!-- BUSINESS AND COUNTRY RESEARCH -->
	    		<%}else{%>
	    			<p><input class="result_label" name="resultLbl" value="<%=businessBean.getCategory() + " in " +countryBean.getName()%>"></p>
	    			<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
		    		<select class="order_select" id="order_bus" name="orderselect">
		    			<option>Earnings</option>
		    			<option>Costs</option>
		    		</select></p>
		    		<div>
		    			<fieldset class="result_box">
				    		<legend class="research_title">BUSINESSES</legend>
				    			<ul id="res" style="list-style-type:none;">
				    			<%businessResult.setCategory(businessBean.getCategory());
				    			  try{
					    			for(BusinessInCountryBean i: ViewBusinessControl.getInstance().retrieveBusinesses(countryBean, businessResult)){%>
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
				    			  <%}
					    		  }catch(NoResultFoundException e){%>
					    				<script>window.alert("No result found.")</script>				    			
					    		<%}%>     
				    			</ul>
			    		</fieldset>	
		    		</div> 
		    	<%}%>
	    	</form>	
	    </div>
	</body>
</html>