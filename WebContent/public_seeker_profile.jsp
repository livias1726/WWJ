<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.InputStream"
		 import="java.io.File"
		 import="java.io.FileOutputStream"
		 import="java.io.FileInputStream"
		 import="java.awt.Desktop"
		 import="java.io.IOException"
		 import="java.net.URISyntaxException"
		 import="java.net.URL"%>
		 
<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.SeekerAccountControl"
		 import="logic.application.control.ManageAccountControl"
		 import="logic.exceptions.NoResultFoundException"%>

<%@page errorPage="WEB-INF/error.jsp"%>
		 
<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<jsp:useBean id="cvBean" class="logic.presentation.bean.CVBean" scope="session"/>
<jsp:setProperty name="cvBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%
accountBean = ManageAccountControl.getInstance().retrieveAccount(accountBean.getId());
userBean = ManageAccountControl.getInstance().retrievePersonalInfo(accountBean.getId());
%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>		
		<title>WorldWideJob - <%=accountBean.getUser().getFirstName() + " " + accountBean.getUser().getLastName()%></title>
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div id="main" style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="public_seeker_profile.jsp" name="publicSeekerProfileform" method="POST">
				<div class="profile_pic">
	    			<%if(accountBean.getPic() != null){	
	    				String path = "file://" + accountBean.getPic().getAbsolutePath();
	    				path = path.replace("\\", "/");%>
	    				
	    				<img src="<%=path%>" alt="profile" id="profile" width="246" height="197">
					<%}else{%>
						<img src="icons/profile_pic.png" alt="profile" id="profile" width="246" height="197">
					<%}%>
	    		</div>
	    		
				<div class="name">
	    			<h2><%=accountBean.getUser().getFirstName() + " " + accountBean.getUser().getLastName()%></h2>
	    			<h3>Job Seeker</h3>
	    		</div>
	    		
	    		<div id="container">
					<ul id="griglia">
						<li><button class="cv_btn" name="cv">Curriculum Vitae</button></li>					
						<li><button class="id_btn" type="button" onClick="javascript:window.location='personal_info_public.jsp';">Personal Info</button></li>
					</ul>
				</div>
				<%if(request.getParameter("cv") != null){
					try {
						SeekerAccountControl.getInstance().retrieveCV(cvBean, accountBean.getId());
						
						String path = "file://" + cvBean.getCv().getAbsolutePath();
					    path = path.replace("\\", "/");
					    URL url;
						try {
							url = new URL(path);
							Desktop.getDesktop().browse(url.toURI());
						} catch (URISyntaxException | IOException e) {%>
							<script>window.alert("Sorry, the document cannot be opened")</script>
					  <%}
				    } catch (NoResultFoundException re) {%>
						<script>window.alert("No CV has been uploaded.");</script>
				  <%}
				}%>
	    	</form>
	    </div>
	</body>
	<script src="js/toolbar.js"></script>
</html>