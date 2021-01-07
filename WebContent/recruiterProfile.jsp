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
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
	    

		<title>WorldWideJob - recruiterProfile</title>
		
	</head>
	<body>
		<div>
			<form action="recruiterProfile.jsp" name="recruiterProfileform" method="POST">
				<div class="dropdown" style="float:right;">
	    				<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
		     			<a href="">Publish Job Offer</a>
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/login.jsp">Quit</a>
		     		</div>
		     	</div>
		     	<div style="float:right;">
	    			<button class="notify_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div style="float:right;">
		        	<div class="dropdown" style="float:right;">
	    				<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
	    			<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/login.jsp">Logout</a>
		     		</div>
		     	</div>
		     	</div>
		     	</div>
	    		<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='login.jsp';"></button>
	    		</div>
	    		</div>
	    		<div class="recruiter_acc">
	    		<div class="profile_pic" >
	    		
	    		<div>
	    			<input type="text" id="Lnamefname" name="LnameFname" disabled value="<%=accountBean.getAccount().getUser().getFirstName()%><%=""%> <%=accountBean.getAccount().getUser().getLastName()%>" style="margin-left:290px;margin-top:20px;text-align:center;text-style:bold"><br>
	    			<label for="email" style="margin-top:5px;margin-left:330px">Recruiter</label>
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
				<li><button class="company_btn" type="button" style="width:200px;height:160px;background-color:lightblue" onClick="javascript:window.location='company.jsp';">Company</button></li>
				<li><button class="id_btn" type="button" style="width:200px;height:160px;background-color:lightblue" onClick="javascript:window.location='personalInfo.jsp';">Personal Info</button></li>
				<li><button class="offers_btn" type="button" style="width:200px;height:160px;background-color:lightblue" onClick="javascript:window.location='offersRecruiter.jsp';">Job Offers</button></li>
				<li><button class="candidate_btn" type="button" style="width:200px;height:160px;background-color:lightblue" onClick="javascript:window.location='candidates.jsp';">Candidates</button></li>
				</ul>
				</div>
	    		</div>
	    	</form>
	    </div>
	</body>
</html>