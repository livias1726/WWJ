<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.BusinessBean"
		import="logic.presentation.bean.CountryBean"
		import="logic.application.control.ViewBusinessControl"
		import="logic.application.control.ViewResultsControl"
		import="logic.application.SessionFacade"%>
<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="businessBean" class="logic.presentation.bean.BusinessBean" scope="session"/>
<jsp:setProperty name="businessBean" property="*"/>

<jsp:useBean id="countryBean" class="logic.presentation.bean.CountryBean" scope="session"/>
<jsp:setProperty name="countryBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="icon" href="icons/main_icon.png">
	<link href="css/style.css" rel="stylesheet">
<title>WorldWideJob</title>
</head>
	
<body>
	<jsp:include page="WEB-INF/toolbar.jsp"/>
	<div>
    	<!-- SEARCH FORM -->
		<form action="entrepreneur_search.jsp" name="researchEntrepreneurform" method="POST">	
	    	<div class="search_entr">
    			<h1 style="text-shadow:-1px -1px 0 #000,1px -1px 0 #000,-1px 1px 0 #000,1px 1px 0 #000;">Become your own boss.<br>Start a new business in the Country of your dreams.<br></h1>
	    		<% if (request.getParameter("search") != null){
	    				if(request.getParameter("place") != null && !request.getParameter("place").equals("")){
	    					countryBean.setName(request.getParameter("place"));
	    					
	    					if(request.getParameter("business") != null && !request.getParameter("business").equals("")) {	    					
		    					businessBean.setCategory(request.getParameter("business"));
	    					}
	    					
	    					String redirectURL = "http://localhost:8080/WorldWideJob/business_results.jsp";
	            			response.sendRedirect(redirectURL);
	    				}else if(request.getParameter("business") != null && !request.getParameter("business").equals("")) {	    					
	    					businessBean.setCategory(request.getParameter("business"));
	    					String redirectURL = "http://localhost:8080/WorldWideJob/business_results.jsp";
	            			response.sendRedirect(redirectURL);
	        			}else{%>
	    					<script>window.alert("Warning! Enter the country or business you are interested in.")</script>
	    			  <%}
	    		}%>	
	    		Place:
	    		<select class="search_select" name="place">
	    			<option></option>
	    			<%for(String i: ViewResultsControl.getInstance().retrieveCountries()){%>
	    				<option><%=i%></option>
	    			<%} %>	    			
	    		</select> 
	    		&nbsp;&nbsp;
	    		Business:
	    		<select class="search_select" name="business" style="margin-right:100px">
	    			<option></option>
	    			<%for(BusinessBean i: ViewBusinessControl.getInstance().retrieveBusinesses()){%>
	    				<option><%=i.getCategory()%></option>
	    			<%} %>
	    		</select> 
	    		<p></p>
	    		<button class="search_btn" type="submit" name="search">Search</button>
	    	</div>	    		
	    </form>
	</div>
</body>
</html>