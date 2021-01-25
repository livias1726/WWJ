<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.OfferBean"
		 import="logic.presentation.bean.CountryBean"
		 import="logic.application.control.ManageFavouriteOffersControl"
		 import="logic.application.control.ViewOfferControl"%>
<%@page errorPage="WEB-INF/error.jsp"%>    
<!DOCTYPE html>

<jsp:useBean id="countryBean" scope="session" class="logic.presentation.bean.CountryBean"/>
<jsp:setProperty name="countryBean" property="*"/>

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
	offerBean.setStart(offer.getStart());
	offerBean.setFinish(offer.getFinish());
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
		
    	<script src="js/results.js"></script>
		<script src="js/toolbar.js"></script>
		<title>WorldWideJob</title>		
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="favourite_offers.jsp" name="favouriteJobOffers" method="POST">
				<p><input class="order_by" type="text" name="orderby" value="Order by:" disabled>
	    		<select class="order_select" id="order" name="orderselect">
	    			<option>Upload</option>
	    			<option>Expiration</option>
	    		</select></p>
	    		<div>
	    			<fieldset class="result_box" style="background-color:white;">
			    		<legend class="research_title">FAVOURITES</legend>
			    			<ul id="res" style="list-style-type:none;">
			    			<%for(OfferBean i: ManageFavouriteOffersControl.getInstance().retrieveFavourites()){%>
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
			    				</li>
	    			  		<%}%>     
			    			</ul>
		    		</fieldset>	
	    		</div>
			</form>
		</div>	
	</body>
</html>