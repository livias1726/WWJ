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
	<title>WorldWideJob - signUp</title>
</head>
<body>
	<div>
			<form action="signUp.jsp" name="signUpform" method="POST">
				<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
				<div style="float:left">
					<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='login.jsp';"></button>
	    		</div>
	    		<div style="float:right;">
	    			<button class="search_btn" type="button" style="width:110px; height:50px; top:10px; background-color: dodgerblue; margin-left:600px" onClick="javascript:window.location='login.jsp';">Sign In</button>
				</div>
				<div class="scheletro_signUp" style="top:70px;left:5px;text-align:center;">CREATE AN ACCOUNT
					<div style="margin-top:100px;font-size:20px;color:black">
	    			<input type="radio" name="category" id="Seek" value="Job Seeker">Job Seeker
	    			&emsp;&emsp;
	    			<input type="radio" name="category" id="Rec" value="Recruiter">Recruiter
	    			&emsp;&emsp;
	    			<input type="radio" name="category" id="Entr" value="Entrepreneur">Entrepreneur
					</div>
					<div style="margin-top: 40px">
				    	<label for="firstName" style="color:black;font-size:20px;margin-left:-640px">First Name</label>
				        <input type="text" id="firstName" name="firstName" style="margin-left: 40px;height: 29px; width: 174px">
				    </div>
				    <div style="margin-top: -25px">
				    	<label for="eMail" style="color:black;font-size:20px;margin-left:-600px">Email</label>
				        <input type="text" id="eMail" name="eMail"style="margin-left: 40px;height: 29px; width: 174px">
				    </div>
				    <div style="margin-top: -25px">
				    	<label for="password" style="color:black;font-size:20px;margin-left:-625px">Password</label>
				        <input type="password" id="password" name="password" style="margin-left: 40px;height: 29px; width: 174px">
				    </div>
				    <div style="margin-top: -195px;float:right">
				    	<label for="lastName" style="color:black;font-size:20px;margin-right:40px">Last Name</label>
				        <input type="text" id="lastName" name="lastName" style="margin-right:250px;height: 29px; width: 174px">
				    </div>
				    <div style="margin-top: -140px;float:right">
				    	<label for="confirmEmail" style="color:black;font-size:20px;margin-right:40px">Confirm email</label>
				        <input type="text" id="confirmEmail" name="confirmEmail" style="margin-right:250px;height: 29px; width: 174px">
				    </div>
				    <div style="margin-top: -85px;float:right">
				    	<label for="confirmPassword" style="color:black;font-size:20px;margin-right:40px">Confirm Password</label>
				        <input type="password" id="confirmPassword" name="confirmPassword" style="margin-right:250px;height: 29px; width: 174px">
				    </div><br>
				    <button class="search_btn" id="signup" type="submit" name="signup" style="width:110px; height:50px; top:40px; background-color: dodgerblue; margin-left:-30px">Sign Up</button>
				</div>
			</form>
	</div>
	<script>var rr = document.signUpform.category;
			if(rr[0].checked){
				<%if(request.getParameter("signup") != null) {
					userBean.setFirstName(request.getParameter("firstName"));
					userBean.setLastName(request.getParameter("lastName"));
					userBean.setEmail(request.getParameter("eMail"));
					userBean.setPassword(request.getParameter("password"));
					accountBean.setType("SEEKER");
					accountBean.setUser(userBean);
					accountBean.signUp();
					
					String redirectURL = "http://localhost:8080/WorldWideJob/seekerProfile.jsp";
			        response.sendRedirect(redirectURL);
			        return;
				}%>
			}else if(rr[1].checked) {
				<%if(request.getParameter("signup") != null) {
					userBean.setFirstName(request.getParameter("firstName"));
					userBean.setLastName(request.getParameter("lastName"));
					userBean.setEmail(request.getParameter("eMail"));
					userBean.setPassword(request.getParameter("password"));
					accountBean.setType("RECRUITER");
					accountBean.setUser(userBean);
					accountBean.signUp();
					
					String redirectURL = "http://localhost:8080/WorldWideJob/recruiter'sProfile.jsp";
			        response.sendRedirect(redirectURL);
			        return;
				}%>
			}else if(rr[2].checked) {
				<%if(request.getParameter("signup") != null) {
					userBean.setFirstName(request.getParameter("firstName"));
					userBean.setLastName(request.getParameter("lastName"));
					userBean.setEmail(request.getParameter("eMail"));
					userBean.setPassword(request.getParameter("password"));
					accountBean.setType("ENTREPRENEUR");
					accountBean.setUser(userBean);
					accountBean.signUp();
					
					String redirectURL = "http://localhost:8080/WorldWideJob/entrepreneur'sProfile.jsp";
			        response.sendRedirect(redirectURL);
			        return;
				}%>
			}
	</script>
</body>
</html>