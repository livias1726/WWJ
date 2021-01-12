<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.UserBean"
		import="logic.presentation.bean.AccountBean"
		import="logic.application.control.AccountControl"
		import="logic.application.control.LoginControl"%>
<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<% if (request.getParameter("login") != null) {
	userBean.setEmail(request.getParameter("email"));
	userBean.setPassword(request.getParameter("password"));
	accountBean.setUser(userBean);
	LoginControl.getInstance().tryLogin(accountBean); 
	
	if("SEEKER".equals(AccountControl.getInstance().retrieveAccount().getType())) {
    	String redirectURL = "http://localhost:8080/WorldWideJob/seekerProfile.jsp";
    	response.sendRedirect(redirectURL);
    	return;
    }
    if("RECRUITER".equals(AccountControl.getInstance().retrieveAccount().getType())) {
    	String redirectURL = "http://localhost:8080/WorldWideJob/recruiterProfile.jsp";
    	response.sendRedirect(redirectURL);
    	return;
    }
    if("ENTREPRENEUR".equals(AccountControl.getInstance().retrieveAccount().getType())) {
      	String redirectURL = "http://localhost:8080/WorldWideJob/entrepreneurProfile.jsp";
    	response.sendRedirect(redirectURL);
    	return;
    }
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
	    <title>WorldWideJob - login</title>
	</head>
	<body>
		    <form action="login.jsp" name="logform" method="POST">
		    	<div style="margin: 100px 0px 0px 750px">
				  <button class="login_btns" type="button" onClick="javascript:window.location='signUp.jsp';">Sign Up</button>
				</div>	
		    	<fieldset class="login_frame">
    				<legend style="color: #0080FF; font-size: 30px; font-family: System;">LOGIN</legend>
				        <div>
				            <div style="margin-top: 40px">
				                <label for="email">Email:</label>
				                <input type="text" id="email" name="email" style="margin-left: 52px">
				            </div>
				        </div>
				        <div>
				            <div style="margin-top: 30px">
				                <label for="password">Password:</label>
				                <input type="password" id="password" name="password" style="margin-left: 30px">
				            </div>
				        </div>
				        <div>
				            <input class="login_btns" type="submit" name="login" value="Sign In" style="margin: 30px 120px">
				        </div>
				        <div>
				        	<button type="button" class="login_fb" id="loginFb"></button>
				        </div>
				        <div>
				            <input class="login_gg" type="submit" name="loginGoogle" value="">
				        </div>
				        <div class="alert_msg" id="unavailable" style="display:none;color: red;font-weight: bold;">
						    Sorry, this function is not available yet.
					  	</div>
				  </fieldset>
		    </form>
	</body>
	<script src="js/login.js"></script>
</html>