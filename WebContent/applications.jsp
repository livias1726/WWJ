<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.StringTokenizer"
		 import="java.util.ArrayList"
		 import="java.util.List"%>	
		  
<%@ page import="logic.presentation.bean.ApplicationBean"
		 import="logic.presentation.bean.OfferBean"
		 import="logic.application.control.ViewOfferControl"
		 import="logic.application.control.SeekerAccountControl"%>

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
		
		<script src="js/toolbar.js"></script>
		<script src="js/tables.js"></script>
		<title>WorldWideJob - Applications</title>
	</head>	
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="applications.jsp" name="applicationsJobSeeker" method="POST">
				<table class="table_applications">
					<tr>
					    <th id="check"><input type="checkbox" id="select" name="select" onclick="selectAll(this)"></th>
					    <th id="offer_col">Offer</th>
					    <th id="pos_col">Position</th>
					    <th id="app_col">Application date</th>
					    <th id="exp_col">Expiration date</th>
				  	</tr>
				  	<%for(ApplicationBean i: SeekerAccountControl.getInstance().retrieveApplications()){ %>
						<tr>
						    <td><input type="checkbox" id="sel" name="sel" value="<%=i.getId()%>" onclick="selectElem(this)"></td>
						    <td><button name="offer" value="<%=i.getId()%>"><%=i.getId()%></button></td>
						    <td><%=i.getJobName()%></td>
						    <td><%=i.getApplication()%></td>
						    <td><%=i.getExpiration()%></td>
						</tr>
					<%}%>
				</table>	
    			<button class="delete_btn" id="delete" name="delete" value="">Delete</button>
    			<%if(request.getParameter("delete") != null){
    				List<Integer> list = new ArrayList<>();
    				
    				String res = request.getParameter("delete");
    				StringTokenizer tok = new StringTokenizer(res, "&");
    				while(tok.hasMoreTokens()){
    					list.add(Integer.parseInt(tok.nextToken()));
    				}
    				
    				if(list.size() > 0){
    					SeekerAccountControl.getInstance().removeApplications(list);
    					String redirectURL = "http://localhost:8080/WorldWideJob/applications.jsp";
    					response.sendRedirect(redirectURL); 
    				}
    			}%>			    		    		
			</form>
		</div>
	</body>
</html>