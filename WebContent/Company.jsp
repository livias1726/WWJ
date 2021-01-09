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
	    		<button class="savechange_btn" name="save_changes" style="width:100px; height:50px; top:550px; left:900px; background-color:dodgerblue" onClick="changeCompanyInfo()">Save change</button>
	    		<input class="companyTitle" type="text" name="companyname" value="Name" disabled style="top:140px; left:-160px;">	
	    		<input class="companyTxt" type="text" name="inputName" value="<%=companyBean.getCompanyInfo().getName()%>" style="top:180px;left:-416px;">
	    		<input class="companyTitle" type="text" name="companybranches" value="Branches" disabled style="top:140px; left:-250px;">
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
      							<td style="background-color:white; height:20px"></td>
         						<td style="background-color:white"></td>
         						<td style="background-color:white"></td>
         						<td style="background-color:white"></td>
         						<td style="background-color:white"></td>
         						<td style="background-color:white"></td>
         					</tr>
      					</thead>
      					<tbody>
      					</tbody>
      			</table>
	    		<input class="companyTitle" type="text" name="companyndescription" value="Description" disabled style="top:200px; left:40px;">	
	    		<input class="companyTxt" type="text" name="inputDescription" value="<%=companyBean.getCompanyInfo().getDescription()%>" style="top:300px;left:-215px;width:300px;height:150px;">
	    		<input class="savechange_btn" name="add" type="button" value="Add" style="width:60px; height:30px; margin-left:1150px;margin-top:-20px; background-color:dodgerblue" onClick="aggiungiRiga()">
	    		</div>
	    	</form>
	    </div>
	    <script>function changeCompanyInfo() {
		   			<%if (request.getParameter("save_changes") != null) {
		   				List <AddressBean> s = companyBean.getCompanyInfo().getBranches();
		   				s.toString();
		   				System.out.println(s);
				    	companyBean.setName(request.getParameter("inputName"));
						companyBean.setDescription(request.getParameter("inputDescription"));
				    	
		   			}%>
	   }</script>
	   <script type='text/javascript'>
		function aggiungiRiga(){
   			var table = document.getElementById('my_table');
   			var tbody = table.getElementsByTagName('tbody')[0];
			var colonne = table.getElementsByTagName('th').length; 
			var tr = document.createElement('tr');
     		for(var i=0; i<colonne; i++){
				var td = document.createElement('td');
				var tx = document.createTextNode(prompt("Inserisci testo per cella "+(i+1),""));
				td.appendChild(tx);
				tr.appendChild(td);
			}
			tbody.appendChild(tr);
		}

</script>
	   
	</body>
</html>