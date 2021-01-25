<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.File"
		 import="java.io.OutputStream"
		 import="java.io.FileOutputStream"
		 import="java.util.Base64"
		 import="java.nio.charset.StandardCharsets"%> 

<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.ManageAccountControl"
		 import="logic.application.SessionFacade"%>

<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%
accountBean = ManageAccountControl.getInstance().retrieveAccount();
userBean =  ManageAccountControl.getInstance().retrievePersonalInfo(SessionFacade.getSession().getID());
%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
		<script src="js/toolbar.js"></script>
		<script src="js/files.js"></script>
		<title>WorldWideJob - Profile</title>
		
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div id="main" style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="recruiter_profile.jsp" name="recruiterProfileform" method="POST">
				
	    		<div class="profile_pic">
	    			<%if(accountBean.getPic() != null){	
	    				String path = "file://" + accountBean.getPic().getAbsolutePath();
	    				path = path.replace("\\", "/");%>
	    				
	    				<img src="<%=path%>" alt="profile" id="profile" width="246" height="197">
					<%}else{%>
						<img src="icons/profile_pic.png" alt="profile" id="profile" width="246" height="197">
					<%}%>
	    		</div>
	    		
	    		<input class="change_pic" type="file" id="load_pic" name="load_pic" accept="image/*" onchange="uploadPic(this)">
	    		<input type="hidden" id="store_pic" name="store_pic">
	    		<button id="submit" name="submit" style="display:none"></button>
	    		<%if(request.getParameter("submit") != null){
					byte[] data = Base64.getDecoder().decode(request.getParameter("store_pic").getBytes(StandardCharsets.UTF_8));
				
					File newPic = new File("tmp/target.jpg");
					try (OutputStream stream = new FileOutputStream(newPic)) {
					    stream.write(data);
					}
					
					accountBean.setPic(newPic);
					ManageAccountControl.getInstance().updateAccountPic(accountBean);
				}%>
				
				<div class="name">
	    			<h2><%=accountBean.getUser().getFirstName() + " " + accountBean.getUser().getLastName()%></h2>
	    			<h3>Recruiter</h3>
	    		</div>
	    		
	    		<div id="container">
					<ul id="griglia">
						<li><button class="company_btn" type="button" onClick="javascript:window.location='company.jsp';">Company</button></li>
						<li><button class="id_btn" type="button" onClick="javascript:window.location='personal_info.jsp';">Personal Info</button></li>
						
						<li><button class="offers_btn" type="button" onClick="javascript:window.location='published_offers.jsp';">Job Offers</button></li>
						<li><button class="candidate_btn" type="button" onClick="javascript:window.location='candidates.jsp';">Candidates</button></li>
					</ul>
				</div>
	    	</form>
	    </div>
	</body>
</html>