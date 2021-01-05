<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.UserBean"
			import="logic.bean.AccountBean"%>
<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>

<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="css/style.css" rel="stylesheet">
	<title>WorldWideWeb - personalInfo</title>
</head>
<body>
	<div>
			<form action="personalInfo.jsp" name="personalInfoform" method="POST">
				<div style="float:right;">
					<div class="dropdown" style="float:right;">
						<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
					<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/login.jsp">Logout</a>
		     		</div>
		     	</div>
		     	</div>
		     	<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='login.jsp';"></button>
	    		</div>
	    		</div>
	    		<div class="scheletro_offer_det">
	    			<div style="margin-top: 40px">
				    	<label for="firstName" style="color:black;font-size:20px;margin-left:15px">First Name</label>
				        <input type="text" id="firstName" name="firstName" value="<%=accountBean.getAccount().getUser().getFirstName()%>" style="margin-left: 40px;height:15px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="birthDate" style="color:black;font-size:20px;margin-left:15px">Birth Date</label>
				        <input type="text" id="birthDate" name="birthDate" value="<%=accountBean.getAccount().getUser().getBirth()%>" style="margin-left: 42px;height:15px">
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="eMail" style="color:black;font-size:20px;margin-left:15px">Email</label>
				        <input type="text" id="eMail" name="eMail" value="<%=userBean.getEmail()%>" style="margin-left: 75px;height:15px">
				    </div>
				    <div style="margin-top: -140px;float:right">
				    	<label for="lastName" style="color:black;font-size:20px;margin-right:40px">Last Name</label>
				        <input type="text" id="lastName" name="lastName" value="<%=accountBean.getAccount().getUser().getLastName()%>" style="margin-right:100px;height:15px">
				    </div>
				    <div style="margin-top: -75px;float:right">
				    	<label for="city" style="color:black;font-size:20px;margin-right:40px">City</label>
				        <input type="text" id="city" name="city" value="<%=accountBean.getAccount().getUser().getCity()%>" style="margin-right:100px;height:15px">
				    </div>
				    <div style="margin-top: -10px;float:right">
				    	<label for="password" style="color:black;font-size:20px;margin-right:40px">Password</label>
				        <input type="password" id="pwd" name="pwd" value="<%=userBean.getPassword()%>" style="margin-right:70px;height:15px"><input type="checkbox" onClick="showPwd()" style="margin-left:-30px">Show<br>
				    </div>
				    <div style="margin-top: 40px">
				    	<label for="title" style="color:black;font-size:20px;margin-left:350px">Titles/lines</label>
				        <input type="text" id="title" name="title" style="margin-left: 75px;height:150px;width:300px"><button style="width:40px;height:40px;background-color:lightblue;margin-left:10px">Add</button>
				    </div><br>
				    <button class="search_btn" type="submit" name="save_changes" id="info" onClick="changeText()" style="width:150px; height:90px; top:40px; background-color: dodgerblue; margin-left:600px">Change Info</button>
				</div>
	    	</form>
	    </div>
	    <script>function showPwd() {
	        		var input = document.getElementById('pwd');
	        			if (input.type === "password") {
	          				input.type = "text";
	        			} else {
	          				input.type = "password";
	        			}
	      		}</script>
	   <script>function changeText() {
		   			document.getElementById('info').innerHTML = "Save Changes";
		   			<%if (request.getParameter("save_changes") != null) {
				    	userBean.setEmail(request.getParameter("eMail"));
						userBean.setPassword(request.getParameter("pwd"));
						userBean.setFirstName(request.getParameter("firstName"));
						userBean.setLastName(request.getParameter("lastName"));
						userBean.setCity(request.getParameter("city"));
						userBean.savePersonalInfo();
				    	
		   			}%>
	   }</script>
</body>
</html>