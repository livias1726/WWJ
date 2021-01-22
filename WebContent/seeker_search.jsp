<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="logic.presentation.bean.JobBean"
		 import="logic.presentation.bean.CountryBean"
		 import="logic.application.control.ViewResultsControl"
		 import="logic.application.control.ViewOfferControl"%>

<%@page errorPage="WEB-INF/error.jsp"%>
		 
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.presentation.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="jobBean" scope="session" class="logic.presentation.bean.JobBean"/>
<jsp:setProperty name="jobBean" property="*"/>

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
			<!-- SEARCH FORM -->
			<form action="seeker_search.jsp" method="POST" name="researchjobseekform">
	    		<div class="search_seek">
	    			<h1 style="text-shadow:-1px -1px 0 #fff,1px -1px 0 #fff,-1px 1px 0 #fff,1px 1px 0 #fff;">Find the right job for you, in the place you've always dreamed of.<br></h1>
		    		<% if (request.getParameter("search") != null){
		    				if(request.getParameter("place") != null && !request.getParameter("place").equals("")){
		    					countryBean.setName(request.getParameter("place"));
		    					
		    					if(request.getParameter("job") != null && !request.getParameter("job").equals("")) {	    					
			    					jobBean.setCategory(request.getParameter("job"));
		    					}
		    					
		    					String redirectURL = "http://localhost:8080/WorldWideJob/offer_results.jsp";
		            			response.sendRedirect(redirectURL);
		    				}else if(request.getParameter("job") != null && !request.getParameter("job").equals("")) {	    					
		    					jobBean.setCategory(request.getParameter("job"));
		    					String redirectURL = "http://localhost:8080/WorldWideJob/offer_results.jsp";
		            			response.sendRedirect(redirectURL);
		        			}else{%>
		    					<script>window.alert("Warning! Enter the country or job you are interested in.")</script>
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
		    		Job:
		    		<select class="search_select" name="job" style="margin-right:100px">
		    			<option></option>
		    			<%for(JobBean i: ViewOfferControl.getInstance().retrieveJobs()){%>
		    				<option><%=i.getCategory()%></option>
		    			<%}%>
		    		</select> 
		    		<p></p>
		    		<button class="search_btn" type="submit" name="search">Search</button>
	    		</div>	    
	    	</form>
		</div>
	</body>
	<script src="js/toolbar.js"></script>
</html>