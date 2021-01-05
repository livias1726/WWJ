<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.bean.CompanyBean"
		import="logic.bean.AddressBean"
		import="java.util.List"
		import="java.util.ArrayList"%>

<!DOCTYPE html>

<jsp:useBean id="companyBean" class="logic.bean.CompanyBean" scope="session"/>
<jsp:setProperty name="companyBean" property="*"/>

<jsp:useBean id="addressBean" class="logic.bean.AddressBean" scope="session"/>
<jsp:setProperty name="addressBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
	    
		<title>WorldWideJob - Company</title>
	</head>
	
	<body>
		<div>
			<form action="candidates.jsp" name="candidates" method="POST">
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
		        <div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/recruiter'sProfile.jsp">Account</a>
		     			<a href="http://localhost:8080/WorldWideJob/login.jsp">Logout</a>
		     		</div>
		     	</div>
		     	</div>
	    		<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='recruiter'sProfile.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="company_container" value="" disabled style="background-color:#C6D6D3">	
	    		<button class="savechange_btn" style="width:100px; height:50px; top:550px; left:800px; background-color:dodgerblue" onClick="changeInfo()">Save change</button>
	    		<input class="companyname" type="text" name="companyname" value="Name" disabled style="background-color:#C6D6D3">	
	    		<input class="companybranches" type="text" name="companybranches" value="Branches" disabled style="background-color:#C6D6D3">	
	    		<input class="companydescription" type="text" name="companyndescription" value="Description" disabled style="background-color:#C6D6D3">	
	    		<input class="companyheadquarter" type="text" name="companyheadquarter" value="Headquarter" disabled style="background-color:#C6D6D3">
	    		<input class="companyname" type="text" name="inputName" value="<%=companyBean.getName()%>" style="background-color:white;top:175px;left:-305px;">
	    		<input class="companybranches" type="text" name="inputBranches"	<ol value="<%=companyBean.getBranches()%>"</ol> style="background-color:white;top:230px;left:-197px;height:150px">
	    		<input class="companydescription" type="text" name="inputDescription" value="<%=companyBean.getDescription()%>" style="background-color:white;top:350px;left:280px;">
	    		<input class="companyheadquarter" type="text" name="inputHeadquarter" value="<%=companyBean.getHeadquarter()%>" style="background-color:white;top:350px;left:390px;">
	    		<button class="add_btn" style="width:40px;height:40px;background-color:lightblue;top:76px;left:400px">Add</button>
	    		</div>
	    	</form>
	    </div>
	    <script>function changeInfo() {
		   			<%if (request.getParameter("save_changes") != null) {
				    	companyBean.setName(request.getParameter("inputName"));
				    	companyBean.setDescription(request.getParameter("inputDescription"));
				    	companyBean.setHeadquarter(addressBean);
				    	List<AddressBean> branches = new ArrayList<>();
				    	companyBean.setBranches(branches);
				    	companyBean.saveCompanyInfo();
				    	
		   			}%>
	   }</script>
	</body>
</html>