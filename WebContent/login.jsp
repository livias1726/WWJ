<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.ManageAccountControl"
		 import="logic.application.control.LoginControl"
		 import="logic.application.SessionFacade"
		 import="logic.application.Users"%>
		 
<%@ page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%if (request.getParameter("login") != null) {
	userBean.setEmail(request.getParameter("email"));
	userBean.setPassword(request.getParameter("password"));
	accountBean.setUser(userBean);
	
	LoginControl.getInstance().tryLogin(accountBean); 
	
	if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {
		String redirectURL = "http://localhost:8080/WorldWideJob/seeker_profile.jsp";
    	response.sendRedirect(redirectURL);
	}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {
		String redirectURL = "http://localhost:8080/WorldWideJob/recruiter_profile.jsp";
    	response.sendRedirect(redirectURL);
    	return;
	}else{
	    String redirectURL = "http://localhost:8080/WorldWideJob/entrepreneur_profile.jsp";
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
	    <script src="js/login.js"></script>
	    <title>WorldWideJob - Login</title>
	</head>
	<body>
	    <form action="login.jsp" name="logform" method="POST">
	    	<button class="login_btns" type="button" style="margin-left:750px" onClick="javascript:window.location='sign_up.jsp';">Sign Up</button>
	    	<fieldset class="login_frame">
   				<legend style="color: #0080FF; font-size: 30px;">LOGIN</legend>
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
			        <br>
			        <div>
			        	<button type="button" class="login_gg" id="loginGg"></button>
			        </div>
			        <div id="unavailable" style="display:none;color:red;font-weight:bold;margin-left:15px;margin-top:10px">
					    Sorry, this service is not available yet.
				  	</div>
			  </fieldset>
	    </form>
	</body>
</html>