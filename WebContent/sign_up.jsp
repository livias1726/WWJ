<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.LoginControl"
		 import="logic.application.control.SignUpControl"
		 import="logic.application.SessionFacade"
		 import="logic.application.Users"
		 import="logic.exceptions.DuplicatedUserException"
		 import="logic.exceptions.InvalidFieldException"%>

<%@page errorPage="WEB-INF/error.jsp"%>
		 
<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%if (request.getParameter("signup") != null) {
	userBean.setEmail(request.getParameter("email"));
	userBean.setPassword(request.getParameter("pwd"));
	userBean.setFirstName(request.getParameter("firstName"));
	userBean.setLastName(request.getParameter("lastName"));
	
	try{
		userBean.verifySignUpSyntax(request.getParameter("confEmail"), request.getParameter("confPwd"));
		accountBean.setUser(userBean);
		
		if(request.getParameter("category") == null){%>
			<script>alert("Select a role.");</script>
			<%return;
	    }else{
		  switch(request.getParameter("category")){
			case "seek":
				accountBean.setType("SEEKER");
				break;
			case "rec":
				accountBean.setType("RECRUITER");
				break;
			case "entr":
				accountBean.setType("ENTREPRENEUR");
				break;
			default:%>
				<script>alert("Invalid role.");</script>
				<%return;
	  	  }
		}
		
		SignUpControl.getInstance().trySignUp(accountBean);
		LoginControl.getInstance().tryLogin(accountBean);
		
		String redirectURL;
		switch(accountBean.getType()) {
			case "SEEKER":
				redirectURL = "http://localhost:8080/WorldWideJob/seeker_profile.jsp";
				break;
			case "RECRUITER":
				redirectURL = "http://localhost:8080/WorldWideJob/recruiter_profile.jsp";
				break;
			case "ENTREPRENEUR":
				redirectURL = "http://localhost:8080/WorldWideJob/entrepreneur_profile.jsp";
				break;
			default:
				redirectURL = "http://localhost:8080/WorldWideJob/index.jsp";
		}
		
		response.sendRedirect(redirectURL);
	}catch(DuplicatedUserException e){%>
		<script>alert("User already signed. Change email or log in.");</script>
  <%}catch(InvalidFieldException e){%>
		<script>alert("Empty field or non-matching fields.");</script>
  <%}
}%>

<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="icon" href="icons/main_icon.png">
	<link href="css/style.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	    
	<title>WorldWideJob - Sign Up</title>
</head>
<body>
	<div>
		<form action="sign_up.jsp" name="signUpform" method="POST">
			<button class="login_btns" type="button" style="margin-left:1000px" onClick="javascript:window.location='login.jsp';">Sign In</button>
	    	<fieldset class="signup_frame">
   				<legend style="color: #0080FF; font-size: 30px;">CREATE AN ACCOUNT</legend>
   				
   					<div style="margin-left:250px;margin-top:25px;font-size:15px;color:black">
		    			<input type="radio" name="category" id="seek" value="seek">Job Seeker
		    			&emsp;&emsp;
		    			<input type="radio" name="category" id="rec" value="rec">Recruiter
		    			<%if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER){%>
		    				<script>document.getElementById("rec").checked = true</script>
		    			<%}%>
		    			&emsp;&emsp;
		    			<input type="radio" name="category" id="entr" value="entr">Entrepreneur
					</div>
					
			        <div style="position:absolute;margin-left:200px;margin-top:25px;width:250px">
				    	<h3>First Name</h3>
				        <input type="text" id="firstName" name="firstName">
				    
				    	<h3>Email</h3>
				    	<input type="email" id="email" name="email">
				        
				    	<h3>Confirm Email</h3>
				        <input type="email" id="confEmail" name="confEmail">
					</div>
					
					<div style="position:absolute;margin-left:450px;margin-top:25px;">
				    	<h3>Last Name</h3>
				        <input type="text" id="lastName" name="lastName">
				    
				    	<h3>Password</h3>
				    	<input type="password" id="pwd" name="pwd">
				        
				    	<h3>Confirm Password</h3>
				        <input type="password" id="confPwd" name="confPwd">
					</div>
					
			        <div>
			            <input class="login_btns" type="submit" name="signup" value="Sign Up" style="margin-top:300px;margin-left:375px">
			        </div>
			</fieldset>
		</form>
	</div>
</body>
</html>