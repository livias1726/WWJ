<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
		
		<title>WorldWideJob - Profile</title>
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="entrepreneur_profile.jsp" name="entrepreneurProfileform" method="POST">
			
				<div class="profile_pic" id="profile"></div>
				<input class="change_pic" type="file" id="file_selector" accept=".jpg">
				
				<script>
				document.getElementById("file_selector").addEventListener("change", function changePic(){
				      var input = document.getElementById("file_selector");
				      var img = document.getElementById("profile");
				          
				      if (input.files && input.files[0]) {
				      	var reader = new FileReader();

					      reader.onload = function (e) {
					          img.style.backgroundImage = "url(" + e.target.result + ")";
					      };
					      
					      reader.readAsDataURL(input.files[0]);
				      }
				}, true);
				</script>
				
				<div class="name">
	    			<h2><%=accountBean.getUser().getFirstName() + " " + accountBean.getUser().getLastName()%></h2>
	    			<h3>Entrepreneur</h3>
	    		</div>
	    		
		    	<div id="container">
					<ul id="griglia">
						<li><button class="fav_business" type="button" onClick="javascript:window.location='favourite_businesses.jsp';">Business Plans</button></li>
						<li><button class="id_btn" type="button" onClick="javascript:window.location='personal_info.jsp';">Personal Info</button></li>
					</ul>
	    		</div>				
	    	</form>
	    </div>
	</body>
	<script src="js/toolbar.js"></script>
</html>