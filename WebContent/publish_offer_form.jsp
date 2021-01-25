<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.StringTokenizer"
		import="java.time.LocalDate"
		import="java.util.ArrayList"
		import="java.util.List"%>	  

<%@ page import="logic.presentation.bean.JobBean"
		 import="logic.presentation.bean.AddressBean"
		 import="logic.presentation.bean.OfferBean"
		 import="logic.application.control.PublishOfferControl"
		 import="logic.application.control.RecruiterAccountControl"
		 import="logic.exceptions.InvalidFieldException"%>
		 
<%@page errorPage="WEB-INF/error.jsp"%>

<jsp:useBean id="jobBean" class="logic.presentation.bean.JobBean" scope="session"/>
<jsp:setProperty name="jobBean" property="*"/>	

<jsp:useBean id="addressBean" class="logic.presentation.bean.AddressBean" scope="session"/>
<jsp:setProperty name="addressBean" property="*"/>	

<jsp:useBean id="offerBean" class="logic.presentation.bean.OfferBean" scope="session"/>
<jsp:setProperty name="offerBean" property="*"/>
    
<!DOCTYPE html>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%if(request.getParameter("publish") != null){
	
	String id = request.getParameter("publish");
	StringTokenizer tok = new StringTokenizer(id, "&");

	if(tok.hasMoreTokens()){
		jobBean.setId(Integer.parseInt(tok.nextToken()));
		offerBean.setPosition(jobBean);
		if(tok.hasMoreTokens()){
			addressBean.setId(Integer.parseInt(tok.nextToken()));
			offerBean.setBranch(addressBean);
		}else{%>
			<script>window.alert("Please, select a branch.");</script>
			<%return;
    	}
	}else{%>
		<script>window.alert("Please, select a position.");</script>
	   <%return;
    }

	if(!request.getParameter("activities").isEmpty()){
		offerBean.setTaskDescription(request.getParameter("activities"));
	}else{%>
		<script>window.alert("Please, insert a description.");</script>
		<%return;
    }
	
	String req = request.getParameter("req_hid");
	System.out.println(req);
	tok = new StringTokenizer(req, "&");
	List<String> list = new ArrayList<>();
	while(tok.hasMoreTokens()){
		list.add(tok.nextToken());
	}
	offerBean.setRequirements(list);
	
	if(!request.getParameter("start").isEmpty()){
		offerBean.setStart(request.getParameter("start"));
	}else{%>
		<script>window.alert("Please, select a start time.");</script>
		<%return;
    }
	
	if(!request.getParameter("finish").isEmpty()){
		offerBean.setFinish(request.getParameter("finish"));
	}else{%>
		<script>window.alert("Please, select a finish time.");</script>
		<%return;
    }

	if(!request.getParameter("sal").isEmpty()){
		offerBean.setBaseSalary(request.getParameter("curr") + " " + request.getParameter("sal"));
	}else{%>
		<script>window.alert("Please, insert a salary.");</script>
		<%return;
    }
	
	if(!request.getParameter("exp").isEmpty()){
		offerBean.setExpiration(request.getParameter("exp"));
	}else{%>
		<script>window.alert("Please, select an expiration date.");</script>
		<%return;
    }
	
	try {
		offerBean.verifyFieldsValidity(offerBean.getStart(), offerBean.getFinish(), offerBean.getExpiration());
		offerBean.convertCurrencyToSym();
		
		PublishOfferControl.getInstance().publishNewOffer(offerBean);%>
		
		<script>window.alert("Offer correctly published.");</script>
		
	  <%String redirectURL = "http://localhost:8080/WorldWideJob/recruiter_profile.jsp";
		response.sendRedirect(redirectURL); 
  	} catch (InvalidFieldException e) { %>
		<script>window.alert("The 'Finish' time must be after the 'Start' time");</script>
  <%} 
}%>
			     	 
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="js/toolbar.js"></script>
		<script src="js/publish_offer_form.js"></script>
		<title>WorldWideJob</title>
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:780px;background-color:#8ecae6;border:1px solid blue">
			<form action="publish_offer_form.jsp" name="offerForm" method="POST">
				<!-- JOB -->
				<div style="margin-left:25px;margin-top:50px">
		   			<h2>The Job</h2>
		   			<label for="position" style="margin-left:25px;font-weight:bold">Position:</label>
		     		<select class="search_select" id="position">
		    			<option></option>
		    			<%for(JobBean i: PublishOfferControl.getInstance().retrieveJobs()){%>
		    				<option id="<%=i.getId()%>"><%=i.getName()%></option>
		    			<%}%>	    			
		    		</select>
		    		
		    		<br><br>
		    		
		    		<button id="new_job" name="new_job" type="button" style="margin-left:25px" onClick="addNewJob()">Add a new job</button>
		    		<div id="new" style="display:none;margin-left:25px">
		    			<input type="text" name="job_name" placeholder="Job name" value="">
			    		&nbsp;
			    		<input type="text" name="job_category" placeholder="Job category" value="">
			    		&nbsp;
			    		<button name="add_job">Add</button>
			    		<%if(request.getParameter("add_job") != null){
							jobBean.setName(request.getParameter("job_name"));
							jobBean.setCategory(request.getParameter("job_category"));
							
							try {
								jobBean.verifyFields(jobBean.getName(), jobBean.getCategory());
								PublishOfferControl.getInstance().saveNewJob(jobBean);
							} catch (InvalidFieldException e) {%>
								<script>window.alert("Please, fill out every field");</script>
						  <%}%>
					   <%}%>
		    		</div>
		    		
			     	<br><br>
			     	
		   			<label for="req" style="margin-left:25px;font-weight:bold">Requirements:</label>
		     		<div id="req" style="margin-left:25px">
		     			<ul class="requirements" id="req_list"></ul>
			     		<input type="text" name="requirement" placeholder="Requirement">
			     		<input type="text" id="req_hid" name="req_hid" value="" style="display:none">
			    		<button type="button" name="add_req" onClick="addRequirement()">Add</button>
			     	</div>
			     	
			     	<br><br>
			     	
			     	<label style="margin-left:25px;font-weight:bold">Activities description:</label><br><br>
		     		<textarea class="act_desc" id="activities" name="activities" rows="" cols="" style="margin-left:25px;"></textarea>
		   		</div>
				
				<!-- ADDITIONAL INFO -->
				<div style="position:absolute;right:200px;top:100px;">
		   			<h2>Additional Information</h2>
		   			
		   			<label for="company" style="margin-left:50px;font-weight:bold">Company branch</label>
		     		<select class="search_select" id="company">
		    			<option></option>
		    			<%for(AddressBean i: PublishOfferControl.getInstance().retrieveCompanyInfo()){%>
		    				<option id="<%=i.getId()%>"><%=i.toString()%></option>
		    			<%}%>	    			
		    		</select>		     	
			     	
			     	<br><br>
			     	
		   			<label style="margin-left:50px;font-weight:bold">Time slot</label><br><br>
		   			<div>
		   				<label for="start" style="margin-left:50px">Start:</label>
			     		<input type="time" id="start" name="start">
			     		
			     		<label for="finish" style="margin-left:50px">Finish:</label>
			     		<input type="time" id="finish" name="finish">
		   			</div>
			     	
			     	<br><br>
			     	
			     	<label for="sal" style="margin-left:50px;font-weight:bold">Base salary:</label><br>
			     	<div id="sal">
			     		<input type="text" name="sal" placeholder="Salary" style="margin-left:50px">
			     		&nbsp;
				     	<select name="curr" id="curr" style="height:20px;width:40px;">
						    <option value="USD">$</option>
						    <option value="GBP">&pound;</option>
						    <option value="EUR">&euro;</option>
						</select>
			     	</div>	
		   		</div>
		   		
				<!-- OTHERS -->
		   		<div style="position:absolute;right:325px;top:400px;">
		   			<h2>Others</h2>
		   			<label for="exp" style="margin-left:50px;font-weight:bold">Expiration date</label>
		     		<input type="date" id="exp" name="exp" min="<%=LocalDate.now()%>">
			     	<p></p>
		   		</div>
		   		
		   		<div style="margin-top:200px;margin-left:40%">
		   			<button class="cancel_btn" name="cancel" id="cancel">Cancel</button>
		   			&nbsp;
		   			<button class="publish_btn" name="publish" id="publish">Publish</button>
		   		</div>
		   		
    		</form>	
	    </div>
	</body>
</html>