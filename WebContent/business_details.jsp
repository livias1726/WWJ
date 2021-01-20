<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="logic.presentation.bean.BusinessBean"
 		  import="logic.presentation.bean.CountryBean"
 		  import="logic.presentation.bean.BusinessInCountryBean"
 		  import="logic.application.control.ManageFavouriteBusinessesControl"
 		  import="logic.application.control.ViewStatisticsControl"
 		  import="logic.application.control.CalculateFeasibilityControl"
 		  import="logic.application.SessionFacade"%>

		
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.presentation.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

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
		
		<title>WorldWideJob</title>
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="background-color:#C6D6D3">
			<form action="business_details.jsp" name="businessdetailsform" method="POST">
	    		<label class="research_title" style="margin-left:45%;font-size:36px"><%=businessResult.getName()%></label>
	    		
	    		<div style="margin-left:50px">
	    			<h2>Description</h2>
	    			<div class="bus_desc">
	    				<%=businessResult.getDescription()%>
	    			</div>
	    		</div>
	    		
	    		<div style="top:50px;margin-left:700px; text-align:center">
					<!-- FAVOURITE -->
					<button class="star_button_nset" id="star" name="star"></button>
					<%if(SessionFacade.getSession().getID() != null) {
						for(BusinessInCountryBean i: ManageFavouriteBusinessesControl.getInstance().retrieveFavourites()) {
							if(i.getId() == businessResult.getId()) {%>
								<script>
								$("#star").removeClass("star_button_nset");
								$("#star").addClass("star_button_set");
								</script>
							<%}
						}
					}%>
					
					<%if(request.getParameter("star") != null){
					  	if(SessionFacade.getSession().getID() == null){%>
							<script>window.alert("You need to be logged to manage your favourites.")</script>
					  <%}else{
						  	boolean flag = false;
						    for(BusinessInCountryBean i: ManageFavouriteBusinessesControl.getInstance().retrieveFavourites()) {
								if(i.getId() == businessResult.getId()) {
								  	ManageFavouriteBusinessesControl.getInstance().removeFavourite(businessResult.getId());%>
								  	<script>
									$("#star").removeClass("star_button_set");
									$("#star").addClass("star_button_nset");
									</script>	
									<%
									flag = true;
									break;
								}
					  		}
						    
						    if(!flag){
						    	ManageFavouriteBusinessesControl.getInstance().addNewFavourite(businessResult.getId());%>
								<script>
								$("#star").removeClass("star_button_nset");
								$("#star").addClass("star_button_set");
								</script>
						    <%}
						}		
					}%>
					
					<!-- STATISTICS -->
					<h2>How to start</h2>
		    		<p>Know your numbers!</p>
		    		<p>Know the minimum turnover you must gain to cope with management costs</p>
		    		<p>Know which services can help you reach your goals</p>
		    		<p>Know how many connections you need to get faster targeted clientele</p>
		    		<p>Know your statistics!</p>
		    		<button class="details_buttons" type="submit" name="statBtn">View statistics</button>
					<%if(request.getParameter("statBtn") != null){
		    			if(SessionFacade.getSession().getID() == null){%>
							<script>window.alert("You need to be logged to view the statistics.")</script>
					  <%}else{
						  	ViewStatisticsControl.getInstance().retrieveBusinessStatistics(businessResult);
						  	String redirectURL = "http://localhost:8080/WorldWideJob/statistics.jsp";
		        			response.sendRedirect(redirectURL);
		    		 	}
		    		 }%>
				</div>
				
	    		<!-- FEASIBILITY -->
	    		<div style="margin-left:50px;">
	    			<label for="budget">Insert budget:</label>
		    		<input class="budget" type="text" name="budget" id="budget">
		    		<select name="curr" id="curr" style="height:40px;width:40px;">
					    <option value="$">$</option>
					    <option value="&pound">&pound;</option>
					    <option value="&euro">&euro;</option>
					</select>
		    		<button class="details_buttons" name="calcFeas">Calculate feasibility</button>
		    		<%if(request.getParameter("calcFeas") != null){
		    			if(SessionFacade.getSession().getID() == null){%>
							<script>window.alert("You need to be logged to calculate the feasibility.")</script>
					  <%}else{
		    		  		if (!request.getParameter("budget").equals("")) {
		    		  			CalculateFeasibilityControl.getInstance().retrieveBusinessFeasibility(businessResult, request.getParameter("budget"));
			    				String redirectURL = "http://localhost:8080/WorldWideJob/feasibility.jsp";
			        			response.sendRedirect(redirectURL);
		    				}else{ %>
		    					<script>window.alert("Warning! Enter your budget.")</script>
		    			  <%}
		    		 	}
		    		 }%>
	    		</div>
	    	</form>
		</div>
	</body>
</html>