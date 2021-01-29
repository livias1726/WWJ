<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.StringTokenizer"%>

<%@ page import="logic.application.control.RecruiterAccountControl"
		 import="logic.presentation.bean.OfferBean"
		 import="logic.application.control.ViewOfferControl"
		 import="java.util.ArrayList"
		 import="java.util.List"%>

<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="offerBean" scope="session" class="logic.presentation.bean.OfferBean"/>
<jsp:setProperty name="offerBean" property="*"/>

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
		<title>WorldWideJob - Offers</title>
	</head>
	
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="published_offers.jsp" name="publishedOffers" method="POST">
				<div style="margin-left:25%;margin-top:20px">
		    		<input type="radio" id="all" name="radio" onclick="filterOffers(this)" checked>All
		    		<input type="radio" id="act" name="radio" onclick="filterOffers(this)">Active
		    		<input type="radio" id="exp" name="radio" onclick="filterOffers(this)">Expired
	    		</div>
	    	
	    		<table class="table_offers" id="row">
					<tr>
					    <th id="num_col">Number</th>
					    <th id="pos_col">Position</th>
					    <th id="up_col">Upload date</th>
					    <th id="exp_col">Expiration date</th>
					    <th id="cand_col">Candidates</th>
				  	</tr>
				  	<%for(OfferBean i: RecruiterAccountControl.getInstance().retrievePublishedOffers()){ %>
						<tr>
						    <td><button name="offer" value="<%=i.getId()%>"><%=i.getId()%></button></td>
						    <td><%=i.getPosition().getName()%></td>
						    <td><%=i.getUpload()%></td>
						    <td><%=i.getExpiration()%></td>
						    <td><%=i.getCandidates()%></td>
						</tr>
					<%}%>
				</table>
	    	</form>
	    </div>
	</body>
	<script src="js/toolbar.js"></script>
	<script src="js/published_offers.js"></script>
</html>