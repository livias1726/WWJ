<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.awt.Desktop"
		 import="java.io.IOException"
		 import="java.net.URISyntaxException"
		 import="java.net.URL"%>
		 
<%@ page import="logic.presentation.bean.JobBean"
		 import="logic.presentation.bean.CountryBean"
		 import="logic.presentation.bean.OfferBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.presentation.bean.ApplicationBean"
		 import="logic.application.control.ApplyToOfferControl"
		 import="logic.application.control.SeekerAccountControl"
		 import="logic.application.control.ManageFavouriteOffersControl"
 		 import="logic.application.SessionFacade"%>
  

  
<!DOCTYPE html>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<jsp:useBean id="countryBean" class="logic.presentation.bean.CountryBean" scope="session"/>
<jsp:setProperty name="countryBean" property="*"/>

<jsp:useBean id="jobBean" class="logic.presentation.bean.JobBean" scope="session"/>
<jsp:setProperty name="jobBean" property="*"/>

<jsp:useBean id="offerBean" class="logic.presentation.bean.OfferBean" scope="session"/>
<jsp:setProperty name="offerBean" property="*"/>

<jsp:useBean id="applicationBean" class="logic.presentation.bean.ApplicationBean" scope="session"/>
<jsp:setProperty name="applicationBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    	<script src="js/toolbar.js"></script>
		<title>WorldWideWeb</title>

	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#C6D6D3">
			<form action="offer_details.jsp" name="offerDetailsform" method="POST">		
				<label class="research_title" style="margin-left:35%;font-size:36px">Offer details</label>
				
				<div style="margin-left:25px">
	    			<h1><%=offerBean.getCompanyName()%></h1>
	    		</div>
	    		
	    		<!-- FAVOURITE -->
	    		<div style="position:absolute;right:25px;top:80px;height:40px;width:40px;">
	    			<button class="star_button_nset" id="star" name="star"></button>
					<%if(SessionFacade.getSession().getID() != null) {
						if(accountBean.getId() != 0 && SessionFacade.getSession().getID() != accountBean.getId()){%>
		     				<script>document.getElementById("star").style.display = "none";</script>
		     	  	  <%}else{
							for(OfferBean i: ManageFavouriteOffersControl.getInstance().retrieveFavourites()) {
								if(i.getId() == offerBean.getId()) {%>
									<script>
									$("#star").removeClass("star_button_nset");
									$("#star").addClass("star_button_set");
									</script>
								<%}
							}
		     	  	    }
					}%>
					
					<%if(request.getParameter("star") != null){
					  	if(SessionFacade.getSession().getID() == null){%>
							<script>window.alert("You need to be logged to manage your favourites.")</script>
					  <%}else{
						  	boolean flag = false;
						    for(OfferBean i: ManageFavouriteOffersControl.getInstance().retrieveFavourites()) {
								if(i.getId() == offerBean.getId()) {
								  	ManageFavouriteOffersControl.getInstance().removeFavourites(offerBean.getId());%>
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
						    	ManageFavouriteOffersControl.getInstance().addNewFavourite(offerBean.getId());%>
								<script>
								$("#star").removeClass("star_button_nset");
								$("#star").addClass("star_button_set");
								</script>
						    <%}
						}		
					}%>
	    		</div>
									
				<!-- JOB -->
				<div style="margin-left:25px;">
	    			<h2>The Job</h2>
	    			<label for="position" style="margin-left:50px">Position</label>
		     		<div class="offer_field" id="position">
			     		<%=offerBean.getPosition().getName()%>
			     	</div>
			     	
			     	<br><br>
			     	
	    			<label for="req" style="margin-left:50px">Requirements</label>
		     		<div id="req">
			     		<%if(offerBean.getRequirements() == null || offerBean.getRequirements().isEmpty()) {%>
			     			Not Provided
			    		<%}else {
			    			for(String i: offerBean.getRequirements()){%>
			     				<div class="offer_field"><%=i%></div>
			     		  <%}
			     	      }%>
			     	</div>
			     	
					<br><br>
					
			     	<label for="desc" style="margin-left:50px">Activities description</label>
		     		<div class="act_desc" id="desc">
			     		<%=offerBean.getTaskDescription()%>
			     	</div>
	    		</div>
				
				<!-- ADDITIONAL INFO -->
				<div style="position:absolute;right:200px;top:170px;">
	    			<h2>Additional Information</h2>
	    			<label for="company" style="margin-left:50px">Company branch</label>
		     		<div class="comp_field" id="company">
		     			<%=offerBean.getBranch()%>
		     			<button class="map_btn" name="maps"></button>
			     		<%if(request.getParameter("maps") != null){
			     			String map = offerBean.getBranch().buildMapAddress();
			     	    	URL url;
			     			try {
			     				url = new URL("https://www.google.com/maps/place/" + map + "/");
			     				Desktop.getDesktop().browse(url.toURI());
			     			} catch (URISyntaxException | IOException e) {%>
			     				<script>window.alert("Sorry, the address cannot be opened in Maps. Try searching it manually.")</script>
			     		  <%}
			     		  }%>
			     	</div>	
	    				     	
			     	<br><br>
			     	
	    			<label for="time" style="margin-left:50px">Time slot</label>
		     		<div class="offer_field" id="time">
			     		<%=offerBean.getStart() + " - " + offerBean.getFinish()%>
			     	</div>
			     	
			     	<br><br>
			     	
			     	<label for="sal" style="margin-left:50px">Base salary</label>
		     		<div class="offer_field" id="sal">
		     			<%offerBean.convertCurrencyToStr();%>
			     		<%=offerBean.getBaseSalary()%>
			     	</div>
	    		</div>
	    		
				<!-- OTHERS -->
	    		<div style="position:absolute;right:400px;top:425px;">
	    			<h2>Others</h2>
	    			<label for="exp" style="margin-left:50px">Expiration date</label>
		     		<div class="offer_field" id="exp">
			     		<%=offerBean.getExpiration()%>
			     	</div>
			     	
			     	<br><br>
			     	
			     	<button class="apply_btn" name="apply" id="apply">Apply</button>
			     	<%if(SessionFacade.getSession().getID() != null) {
			     		if(accountBean.getId() != 0 && SessionFacade.getSession().getID() != accountBean.getId()){%>
			     			<script>document.getElementById("apply").style.display = "none";</script>
			     	  <%}else{
					     	for(ApplicationBean i: SeekerAccountControl.getInstance().retrieveApplications()) {
								if(i.getId() == offerBean.getId()) {%>
									<script>
									var x = document.getElementById("apply");
									x.disabled = true;
									x.style.opacity=0.5;
									</script>
							  <%}
						  	}
			     		}
			     	  }%>
					  
					<%if(request.getParameter("apply") != null){
						if(SessionFacade.getSession().getID() != null){
							 applicationBean.setId(offerBean.getId());
							 ApplyToOfferControl.getInstance().apply(applicationBean);
							 
							 String redirectURL = "http://localhost:8080/WorldWideJob/offer_results.jsp";
							 response.sendRedirect(redirectURL); 
						}else{%>
							<script>window.alert("You need to be logged to perform this operation. Please, log in or create an account!")</script>
					  <%} 
					 }%>
	    		</div>
	    	</form>
	    </div>
	</body>
</html>