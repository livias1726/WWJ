<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.ManageAccountControl"%>

<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%accountBean = ManageAccountControl.getInstance().retrieveAccount();%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

		<title>WorldWideJob - Profile</title>
		
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div id="main" style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="recruiter_profile.jsp" name="recruiterProfileform" method="POST">
				
	    		<div class="profile_pic" id="profile"></div>
	    		<input class="change_pic" type="file" id="file_selector" name="pic" accept="image/*">
				
				<div class="name">
	    			<h2><%=accountBean.getUser().getFirstName() + " " + accountBean.getUser().getLastName()%></h2>
	    			<h3>Recruiter</h3>
	    		</div>
	    		
	    		<div id="container">
					<ul id="griglia">
						<li><button class="company_btn" type="button" onClick="javascript:window.location='Company.jsp';">Company</button></li>
						<li><button class="id_btn" type="button" onClick="javascript:window.location='personalInfo.jsp';">Personal Info</button></li>
						<li><button class="offers_btn" type="button" onClick="javascript:window.location='published_offers.jsp';">Job Offers</button></li>
						<li><button class="candidate_btn" type="button" onClick="javascript:window.location='candidates.jsp';">Candidates</button></li>
					</ul>
				</div>
	    	</form>
	    </div>
	</body>
	<script src="js/toolbar.js"></script>
	<script src="js/files.js"></script>
</html>