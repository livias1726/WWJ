<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.application.control.ManageAccountControl"
		 import="logic.application.SessionFacade"%>

<%@page errorPage="WEB-INF/error.jsp"%>
		 
<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%if(request.getParameter("save") != null){
	UserBean bean = new UserBean();
	
	bean.setFirstName(request.getParameter("firstName"));
	bean.setLastName(request.getParameter("lastName"));
	bean.setBirth(request.getParameter("birthDate"));
	bean.setCity(request.getParameter("city"));
	bean.setEmail(request.getParameter("email"));
	bean.setPassword(request.getParameter("pwd"));
	
	ManageAccountControl.getInstance().changePersonalInfo(bean);
	
	String redirectURL = "http://localhost:8080/WorldWideJob/personal_info.jsp";
	response.sendRedirect(redirectURL); 
}%>

<%userBean =  ManageAccountControl.getInstance().retrievePersonalInfo(SessionFacade.getSession().getID());%>

<html lang="en">
<head>
	<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<title>WorldWideWeb</title>
</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="personal_info.jsp" name="personalInfoform" method="POST">
				<button class="change_btn" id="change" type="button">Change</button>				
	    		<div style="margin-left:350px;margin-top:50px;">
			    	<h3>First Name</h3>
			        <input type="text" id="firstName" name="firstName" value="<%=userBean.getFirstName()%>" readonly>
			    
			    	<h3>Birth Date</h3>
			    	<%if(userBean.getBirth() != null){%>
			    		<input type="date" id="birthDate" name="birthDate" value="<%=userBean.getBirth()%>" readonly>
			    	<%}else{%>
			    		<input type="date" id="birthDate" name="birthDate" value="" readonly>
			    	<%}%>
			        
			    	<h3>Email</h3>
			        <input type="email" id="email" name="email" value="<%=userBean.getEmail()%>" readonly>
				</div>
				
				<div style="position:absolute;right:350px;top:160px">
			    	<h3>Last Name</h3>
			        <input type="text" id="lastName" name="lastName" value="<%=userBean.getLastName()%>" readonly>
			    
			    	<h3>City</h3>
			    	<%if(userBean.getCity() != null){%>
			    		<input type="text" id="city" name="city" value="<%=userBean.getCity()%>" readonly>
			    	<%}else{%>
			    		<input type="text" id="city" name="city" value="" readonly>
			    	<%}%>
			        
			    	<h3>Password</h3>
			        <input type="password" id="pwd" name="pwd" value="<%=userBean.getPassword()%>" readonly><input type="checkbox" onClick="showPwd()">Show
				</div>
				<button class="save_btn" name="save" id="save" style="display:none">Save changes</button>			
	    	</form>
	    </div>
	</body>
	<script src="js/toolbar.js"></script>
	<script src="js/personal_info.js"></script>
</html>