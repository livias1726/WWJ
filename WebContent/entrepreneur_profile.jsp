<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.ManageAccountControl"%>   
		
<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%
	accountBean = ManageAccountControl.getInstance().retrieveAccount();
%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="css/style.css" rel="stylesheet">
		
		<title>WorldWideJob - Profile</title>
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div>
			<form action="entrepreneurProfile.jsp" name="entrepreneurProfileform" method="POST">
	    		<div class="recruiter_acc">
	    		<div class="profile_pic">
	    		<div>
	    			<input type="text" id="Lnamefname" name="LnameFname" disabled value="<%=accountBean.getUser().getFirstName() + " " + accountBean.getUser().getLastName()%>" style="margin-left:290px;margin-top:20px;text-align:center;text-style:bold"><br>
	    			<label for="email" style="margin-top:5px;margin-left:325px">Entrepreneur</label>
	    		</div>
	    		<input class="change_pic" disabled value="Change profile picture:" style="text-align:center;background-color:white; border:0;width:250px;height:20px;margin-top:180px;margin-left:-5px"><br>
				<input type='file' id='getval' style="margin-top:10px; margin-left:-5px" name="background-image"/><br/><br/>
				<div id='clock' style="margin-top:-340px; margin-left:-5px"></div>
				<script>
				document.getElementById('getval').addEventListener('change', readURL, true);
				function readURL(){
				      var file = document.getElementById("getval").files[0];
				      var reader = new FileReader();
				      reader.onloadend = function(){
				         document.getElementById('clock').style.backgroundImage = "url(" + reader.result + ")";        
				      }
				      if(file){
				         reader.readAsDataURL(file);
				       }else{
				       }
				}
				</script>
				</div>
	    		<div id="container">
				<ul id="griglia">
				<li><button class="profits_btn" type="button" style="width:200px;height:160px;background-color:lightblue" onClick="javascript:window.location='favouriteBusiness.jsp';">Business Plans</button></li>
				<li><button class="id_btn" type="button" style="width:200px;height:160px;background-color:lightblue" onClick="javascript:window.location='personalInfo.jsp';"><br>Personal Info</button></li>
				</ul>
	    		</div>
	    		</div>
	    	</form>
	    </div>

</body>
</html>