<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.StringTokenizer"%>
		
<%@ page import="logic.presentation.bean.JobBean"
		 import="logic.presentation.bean.CountryBean"
		 import="logic.presentation.bean.OfferBean"
		 import="logic.presentation.bean.AddressBean"
		 import="logic.application.control.ViewResultsControl"
		 import="logic.application.control.ViewOfferControl"
		 import="logic.exceptions.NoResultFoundException"%>
		 
<%@page errorPage="WEB-INF/error.jsp"%>
		
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.presentation.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="jobBean" scope="session" class="logic.presentation.bean.JobBean"/>
<jsp:setProperty name="jobBean" property="*"/>

<jsp:useBean id="offerBean" scope="session" class="logic.presentation.bean.OfferBean"/>
<jsp:setProperty name="offerBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%if(request.getParameter("offer") != null){ 
	String res = request.getParameter("offer");
	
	OfferBean offer = ViewOfferControl.getInstance().retrieveOfferById(Integer.parseInt(res));
	
	offerBean.setId(Integer.parseInt(res));	
	offerBean.setCompanyName(offer.getCompanyName()); 
	offerBean.setTaskDescription(offer.getTaskDescription());
	offerBean.setPosition(offer.getPosition());
	offerBean.setBranch(offer.getBranch());
	offerBean.setExpiration(offer.getExpiration());
	offerBean.setRequirements(offer.getRequirements());
	offerBean.setStart(offer.getStart());offerBean.setFinish(offer.getFinish());
	offerBean.setBaseSalary(offer.getBaseSalary());
	
	String redirectURL = "http://localhost:8080/WorldWideJob/offer_details.jsp";
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
		<title>WorldWideJob</title>
	</head>
	
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div>
			<form action="offer_results.jsp" name="researchresultjobseekform" method="POST">
	    		<!-- COUNTRY RESEARCH -->
	    		<%if(jobBean.getCategory() == null) {%>
	    			<p><input class="result_label" name="resultLbl" value="<%=countryBean.getName()%>"></p>
	    			<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
		    		<select class="order_select" id="order_off" name="orderselect">
		    			<option>Upload</option>
		    			<option>Expiration</option>
		    		</select></p>
		    		<div>
		    			<fieldset class="result_box">
				    		<legend class="research_title">JOB OFFERS</legend>
				    			<ul id="res" style="list-style-type:none;">
				    			<%try{
					    			for(OfferBean i: ViewOfferControl.getInstance().retrieveOffersByCountry(countryBean)){%>
					    				<li>
					    				<button id="res_btn" class="result" type="submit" name="offer" value="<%=i.getId()%>"> 
						    				<%if(i.getBranch().getState() == null) {%>
						    					<%="'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName()%>
						    				<%}else {%>
						    					<%="'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getState() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName()%>
						    			    <%}%>
						    			</button>
										<button id="upl" style="display:none;" value="<%=i.getUpload()%>"></button>
					    				<button id="exp" style="display:none;" value="<%=i.getExpiration()%>"></button>
					    				<button id="cat" style="display:none;" value="<%=i.getPosition().getCategory()%>"></button>
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
			    			<%for(JobBean i: ViewOfferControl.getInstance().retrieveJobs()){%>
			    				<input type="checkbox" id="cat1" name="category" value="<%=i.getCategory()%>" onclick="filterResults()"><%=i.getCategory()%>
			    				<p></p>
			    			<%}%>
		    		</fieldset>
		    		
		    	<!-- JOB RESEARCH -->
	    		<%}else if(countryBean.getName() == null) {%>
	    			<p><input class="result_label" name="resultLbl" value="<%=jobBean.getCategory()%>"></p>
	    			<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
		    		<select class="order_select" id="order_off" name="orderselect">
		    			<option>Upload</option>
		    			<option>Expiration</option>
		    		</select></p>
		    		<div>
		    			<fieldset class="result_box">
				    		<legend class="research_title">JOB OFFERS</legend>
				    			<ul id="res" style="list-style-type:none;">
				    			<%try{
					    			for(OfferBean i: ViewOfferControl.getInstance().retrieveOffersByJob(jobBean)){%>
					    				<li>
					    				<button class="result" id="res_btn" type="submit" name="offer" value="<%=i.getId()%>"> 
						    				<%if(i.getBranch().getState() == null) {%>
						    					<%="'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName()%>
						    				<%}else {%>
						    					<%="'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getState() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName()%>
						    			    <%}%>
						    			</button>
										<button id="upl" style="display:none;" value="<%=i.getUpload()%>"></button>
					    				<button id="exp" style="display:none;" value="<%=i.getExpiration()%>"></button>
					    				<button id="cat" style="display:none;" value="<%=i.getPosition().getCategory()%>"></button>
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
		    		
		    	<!-- JOB AND COUNTRY RESEARCH -->
	    		<%}else{%>
	    			<p><input class="result_label" name="resultLbl" value="<%=jobBean.getCategory() + " in " +countryBean.getName()%>"></p>
	    			<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
		    		<select class="order_select" id="order_off" name="orderselect">
		    			<option>Upload</option>
		    			<option>Expiration</option>
		    		</select></p>
		    		<div>
		    			<fieldset class="result_box">
				    		<legend class="research_title">JOB OFFERS</legend>
				    			<ul id="res" style="list-style-type:none;">
				    			<%try{
					    			for(OfferBean i: ViewOfferControl.getInstance().retrieveOffers(countryBean, jobBean)){%>
					    				<li>
					    				<button class="result" id="res_btn" type="submit" name="offer" value="<%=i.getId()%>"> 
						    				<%if(i.getBranch().getState() == null) {%>
						    					<%="'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName()%>
						    				<%}else {%>
						    					<%="'" + i.getCompanyName() + "'" + " - " + i.getBranch().getCountryName() + ", " + i.getBranch().getState() + ", " + i.getBranch().getCity() + " (Exp: " + i.getExpiration() + ")" + "\n" + i.getPosition().getName()%>
						    			    <%}%>
						    			</button>
										<button id="upl" style="display:none;" value="<%=i.getUpload()%>"></button>
					    				<button id="exp" style="display:none;" value="<%=i.getExpiration()%>"></button>
					    				<button id="cat" style="display:none;" value="<%=i.getPosition().getCategory()%>"></button>
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
	<script src="js/results.js"></script>
	<script src="js/toolbar.js"></script>
</html>