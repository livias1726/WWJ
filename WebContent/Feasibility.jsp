<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="logic.presentation.bean.BusinessInCountryBean"
		 import="logic.application.control.CalculateFeasibilityControl"%> 

<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

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
		<div style="background-color:#C6D6D3;">
			<form action="feasibility.jsp" name="feasibility" method="POST">
	    		<label class="research_title" style="margin-left:40%;font-size:30px"><%=businessResult.getName()%> - Feasibility</label>
			
				<!-- RECALCULATE -->
	    		<div style="position:absolute;right:50px;">
	    			<label for="budget">Insert budget:</label>
		    		<input class="budget" type="text" name="budget" id="budget">
		    		<select name="curr" id="curr" style="height:40px;width:40px;">
					    <option value="USD">$</option>
					    <option value="GBP">&pound;</option>
					    <option value="EUR">&euro;</option>
					</select>
		    		<button class="details_buttons" name="calcFeas">Calculate feasibility</button>
		    		<%if(request.getParameter("calcFeas") != null){
		    			if (!request.getParameter("budget").equals("")) {
	    		  			session.setAttribute("budget", request.getParameter("budget"));
	    		  			session.setAttribute("inserted_curr", request.getParameter("curr"));
	    		  			session.setAttribute("feas", CalculateFeasibilityControl.getInstance().retrieveBusinessFeasibility(businessResult, request.getParameter("budget")));
	    		  			String redirectURL = "http://localhost:8080/WorldWideJob/feasibility.jsp";
		        			response.sendRedirect(redirectURL);
	    				}else{%>
	    					<script>window.alert("Warning! Enter your budget.")</script>
	    			  <%}
		    		 }%>
	    		</div>
	    		
	    		<!-- LIVING EXP -->
	    		<div style="margin-left:25px;">
	    			<h2>Basic living expense</h2>
	    			<h3 style="margin-left:25px">Total Living Expenses in <%=businessResult.getCountry().getExampleCity()%> - 1 person, for year (without rent)</h3>
			     	<div class="feasibility_field">
			     		<%=(String)session.getAttribute("inserted_curr") + " " + 
			     		CalculateFeasibilityControl.getInstance().convertValue(businessResult.getCountry().getLivingExpense(), (String)session.getAttribute("inserted_curr"), businessResult.getCountry().getCurrency())%>
			     	</div>
	    		</div>
	    		
	    		<!-- TAXES -->
	    		<div style="position:absolute;right:100px;">
			     	<fieldset>
		     			<legend class="research_title" style="color:white;">Business taxes*</legend>
		     				<label for="inc" style="margin-left:10px;">Income tax</label>
				     		<div class="feasibility_field" id="inc">
					     		<%=businessResult.getTaxes().get(0) + " %"%>
					     	</div>
					     	<p></p>
					     	<label for="corp" style="margin-left:10px;">Corporate tax</label>
				     		<div class="feasibility_field" id="corp">
					     		<%=businessResult.getTaxes().get(1) + " %"%>
					     	</div>
					     	<p></p>
					     	<label for="cap" style="margin-left:10px;">Capital gains</label>
				     		<div class="feasibility_field" id="cap">
					     		<%=businessResult.getTaxes().get(2) + " %"%>
					     	</div>
					     	<p></p>
					     	<label for="sal" style="margin-left:10px;">Sales tax</label>
				     		<div class="feasibility_field" id="sal">
					     		<%=businessResult.getTaxes().get(3) + " %"%>
					     	</div>
					     	<p></p>
					     	<label for="prop" style="margin-left:10px;">Property tax</label>
				     		<div class="feasibility_field" id="prop">
					     		<%=businessResult.getTaxes().get(4) + " %"%>
					     	</div>
					     	
					     	<h4>*All taxes are based on a year</h4>	     	
		     		</fieldset>
		     	</div> 	
	    		
		     	<!-- MANAGEMENT COST -->
		     	<div>
		     		<h2 style="margin-left:25px">Business average managment costs*</h2>
		     		<label for="start" style="margin-left:50px">Start*</label>
		     		<div class="feasibility_field" id="start">
			     		<%=(String)session.getAttribute("inserted_curr") + " " + CalculateFeasibilityControl.getInstance().convertValue(businessResult.getStartExpense(), (String)session.getAttribute("inserted_curr"), businessResult.getCountry().getCurrency())%>
			     	</div>
			     	<h4 style="margin-left:50px">*(Furniture, supplies, insurance...)</h4>
		     		<label for="main" style="margin-left:50px;top:20px">Maintenance</label>
		     		<div class="feasibility_field" id="main">
			     		<%=(String)session.getAttribute("inserted_curr") + " " + 
			     		   CalculateFeasibilityControl.getInstance().convertValue(businessResult.getAverageCost(), (String)session.getAttribute("inserted_curr"), businessResult.getCountry().getCurrency())%>
			     	</div>
			     	<h4 style="margin-left:25px">*Other specific expenses are not included</h4>	    
		     	</div>
		     	
		     	<!-- RESULTS -->
		     	<div style="margin:auto;height:300px;width:40%;">
		     		<label for="bud" style="font-size:36px;">Budget</label>
		     		<div class="feasibility_field" id="bud">
			     		<%=(String)session.getAttribute("inserted_curr") + " " + (String)session.getAttribute("budget")%>
			     	</div>
			     	<p></p>
			     	<label for="earn" style="font-size:36px;">Average earnings</label>
		     		<div class="feasibility_field" id="earn">
			     		<%=(String)session.getAttribute("inserted_curr") + " " + 
			     		   CalculateFeasibilityControl.getInstance().convertValue(businessResult.getAverageEarnings(), (String)session.getAttribute("inserted_curr") , businessResult.getCountry().getCurrency())%>
			     	</div>
			     	<p></p>
			     	<label for="res" style="font-size:36px;font-weight:bold">Result</label>
		     		<div class="feasibility_field" id="res">
			     		<%=(String)session.getAttribute("inserted_curr") + " " + 
		     		       CalculateFeasibilityControl.getInstance().convertValue(Float.valueOf(session.getAttribute("feas").toString()), (String)session.getAttribute("inserted_curr") , businessResult.getCountry().getCurrency())%>
			     	</div>
		     		<%if(CalculateFeasibilityControl.getInstance().convertValue(Float.valueOf(session.getAttribute("feas").toString()), (String)session.getAttribute("inserted_curr") , businessResult.getCountry().getCurrency()) < 0){%>
		     			<script>
						$("#res").css("color","red");
		     			</script>
		     		<%}else{%>
		     			<script>
						$("#res").css("color","green");
		     			</script>
		     		<%}%>	     		
		     	</div>    	
		     </form>
		</div>	
	</body>
	<script src="js/toolbar.js"></script>
</html>