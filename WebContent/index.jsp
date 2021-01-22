<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="logic.application.SessionFacade"
		import="logic.application.Users"%>
		
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
		<link rel="stylesheet" href="css/style.css">
		<title>WorldWideJob</title>
	</head>
	<body class="main">
		<form action="index.jsp" name="main" method="POST">
			<div class="dropdown" style="float:right;">
			  	<button class="menu_btn"></button>
			    <div class="dropdown-content" style="right:0;">
			    	<a href="html/pricing.html">Buy premium version</a>
			        <a href="html/support.html">Support</a>	     		
			     </div>
			</div>
			<div class="dropdown" style="float:right;">
				<button class="user_btn"></button>
				<div class="dropdown-content" style="right:0;">
			    	<a href="http://localhost:8080/WorldWideJob/login.jsp">Login</a>
			  	</div>
			</div>
			<div style="margin-left: 300px">
			    <button class="main_btns" name="seek">Job seeker</button>
			 	<%if(request.getParameter("seek") != null) {
			    	SessionFacade.getSession().setCurrUserType(Users.SEEKER);
			    	String redirectURL = "http://localhost:8080/WorldWideJob/seeker_search.jsp";
	    			response.sendRedirect(redirectURL);
			    }%>
			    
			    <button class="main_btns" name="rec">Recruiter</button>
			    <%if(request.getParameter("rec") != null) {
			    	SessionFacade.getSession().setCurrUserType(Users.RECRUITER);
			    	String redirectURL = "http://localhost:8080/WorldWideJob/login.jsp";
	    			response.sendRedirect(redirectURL);
			    }%>
			    
			    <button class="main_btns" name="entr">Entrepreneur</button>
				<%if(request.getParameter("entr") != null) {
			    	SessionFacade.getSession().setCurrUserType(Users.ENTREPRENEUR);
			    	String redirectURL = "http://localhost:8080/WorldWideJob/entrepreneur_search.jsp";
	    			response.sendRedirect(redirectURL);
			    }%>
			</div>
		</form>
	</body>
</html>