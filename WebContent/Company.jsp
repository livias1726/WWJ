<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.CompanyBean"
		import="logic.presentation.bean.AddressBean"
		import="java.util.List"
		import="java.util.ArrayList"
		import="java.util.Arrays"
		import="logic.presentation.bean.AccountBean"
		import="logic.application.control.RecruiterAccountControl"
		import="logic.application.control.AccountControl"%>

<!DOCTYPE html>

<jsp:useBean id="companyBean" class="logic.presentation.bean.CompanyBean" scope="session"/>
<jsp:setProperty name="companyBean" property="*"/>

<jsp:useBean id="addressBean" class="logic.presentation.bean.AddressBean" scope="session"/>
<jsp:setProperty name="addressBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.jdbc.Driver");%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
	    
		<title>WorldWideJob - company</title>
	</head>
	
	<body>
		<div>
			<form action="Company.jsp" name="Company" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/publish.jsp">Publish Job Offer</a>
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
		     			<a href="http://localhost:8080/WorldWideJob/recruiterProfile.jsp">Account</a>
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
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='recruiterProfile.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="company_container" value="" disabled style="background-color:#C6D6D3">	
	    		<button class="savechange_btn" type="submit" name="save_changes" style="width:100px; height:50px; top:550px; left:900px; background-color:dodgerblue" onClick="saveCompanyInfo()">Save change</button>
	    		<input class="companyTitle" type="text" name="companyname" value="Name" disabled style="top:140px; left:-160px;">	
	    		<input class="companyTxt" type="text" name="inputName" value="<%=RecruiterAccountControl.getInstance().retrieveCompanyInfo().getName()%>" style="top:180px;left:-416px;">
	    		<input class="companyTitle" type="text" name="companybranches" value="Branches" disabled style="top:140px; left:-250px;">
	    		<%Object o;
	    			Integer count = 1;
	    			String str = "";
		   				List <AddressBean> s = RecruiterAccountControl.getInstance().retrieveCompanyInfo().getBranches();
		   				for(Integer i=0;i<s.size();i++){
		   					if(i+1 == AccountControl.getInstance().retrieveAccount().getId()){
		   						o=s.get(i);
		   						for(Integer j=0;j<o.toString().length();j++){
				   					if(o.toString().charAt(j) != ','){
				   						str += o.toString().charAt(j);
				   					}else if(count == 1){
				   						request.setAttribute("country", str);
				   						count++;
				   						str = "";
				   						continue;
				   					}else if(count == 2){
				   						request.setAttribute("state", str);
				   						count++;
				   						str="";
				   						continue;
				   					}else if(count==3){
				   						request.setAttribute("city", str);
				   						count++;
				   						str="";
				   						continue;
				   					}else if(count==4){
				   						request.setAttribute("postalCode", str);
				   						count++;
				   						str="";
				   						continue;
				   					}else if(count==5){
				   						request.setAttribute("street", str);
				   						count++;
				   						str="";
				   						continue;
				   					}else{
				   						request.setAttribute("number", str);
				   						count++;
				   						str="";
				   					}
		   						}
			   				}
		   				}
		   				%>
	    		<table border="1" id='my_table' style="table-layout:fixed; width:600px;position:absolute;left:550px;top:185px">
	    			<thead>
      						<tr>
         						<th style="color:black">Country</th>
         						<th style="color:black">State</th>
         						<th style="color:black">City</th>
         						<th style="color:black">Street</th>
         						<th style="color:black">Number</th>
         						<th style="color:black">Zip Code</th>
      						</tr>
      						<tr>
      							<td contenteditable= "true" style="background-color:white; height:20px"><%=request.getAttribute("country")%></td>
         						<td contenteditable= "true" style="background-color:white"><%=request.getAttribute("state")%></td>
         						<td contenteditable= "true" style="background-color:white"><%=request.getAttribute("city")%></td>
         						<td contenteditable= "true" style="background-color:white"><%=request.getAttribute("postalCode")%></td>
         						<td contenteditable= "true" style="background-color:white"><%=request.getAttribute("street")%></td>
         						<td contenteditable= "true" style="background-color:white"><%=request.getAttribute("number")%></td>
         					</tr>
         					<%if(request.getParameter("add") != null){%>
         						<tr>
      								<td contenteditable= "true" id="country_modified" style="background-color:white; height:20px"></td>
         							<td contenteditable= "true" id="state_modified" style="background-color:white"></td>
         							<td contenteditable= "true" id="city_modified" style="background-color:white"></td>
         							<td contenteditable= "true" id="postalCode_modified" style="background-color:white"></td>
         							<td contenteditable= "true" id="street_modified" style="background-color:white"></td>
         							<td contenteditable= "true" id="number_modified" style="background-color:white"></td>
         						</tr>
         					<%}%>
      					</thead>
      					<tbody>
      					</tbody>
      			</table>
	    		<input class="companyTitle" type="text" name="companyndescription" value="Description" disabled style="top:200px; left:40px;">
	    		<input class="companyTxt" type="text" name="inputDescription" value="<%=RecruiterAccountControl.getInstance().retrieveCompanyInfo().getDescription()%>" style="top:300px;left:-215px;width:300px;height:150px;">
	    		<button class="savechange_btn" name="add" type="submit" value="Add" style="width:60px; height:30px; margin-left:1150px;margin-top:-20px; background-color:dodgerblue" onClick="saveNewRow('my_table')">Add</button>
	    		</div>
	    		<input type="hidden" id="hiddenField" name="variabile" value=""/>
	    	</form>
	    </div>
	    <script>function saveNewRow(id_table){
	    			var address = new Array();
	    			var table = document.getElementById(id_table);
					var celle = table.getElementsByTagName('th');
					for(var i=0; i<2;i++){
						if(i == 2){
							for(var j=0; j<celle.length; j++){
								var aggiungi = address.push(celle[j].innerHTML);
							}
						}
						
					}
					document.getElementById("hiddenField").value = address;

	    			
	    }</script>
	</body>
</html>