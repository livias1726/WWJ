<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<jsp:useBean id="accountBean" scope="request" class="logic.bean.AccountBean"/>
<jsp:useBean id="userBean" scope="request" class="logic.bean.UserBean"/>

<jsp:setProperty name="accountBean" property="*"/>

<%@ page errorPage="error.jsp" %>  

<% if (request.getParameter("login") != null) {
		userBean.setEmail(request.getParameter("email"));
		userBean.setPassword(request.getParameter("password"));
		accountBean.setUser(userBean);
        accountBean.login(); %>
        <jsp:forward page="next.jsp"/>
      <%
    }
%>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	    
	    <title>WorldWideJob - Login</title>
	</head>
	<body>
		<div>
		    <form action="login.jsp" name="logform" method="POST">
		    	<div style="margin: 100px 0px 0px 750px">
				  <button class="login_btns" type="button" onClick="javascript:window.location='signup.jsp';">Sign Up</button>
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
				            <input class="login_fb" type="submit" name="loginFacebook" value="">
				        </div>
				        <div>
				            <input class="login_gg" type="submit" name="loginGoogle" value="">
				        </div>
				  </fieldset>
		    </form>
		</div>
	</body>
</html>