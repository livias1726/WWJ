<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.CompanyBean"
		import="logic.presentation.bean.AddressBean"
		import="java.util.List"
		import="java.util.ArrayList"
		import="java.util.Arrays"
		import="logic.presentation.bean.AccountBean"
		import="logic.application.control.RecruiterAccountControl"
		import="logic.application.control.AccountControl"
		import="logic.presentation.bean.CountryBean"%>

<!DOCTYPE html>

<jsp:useBean id="companyBean" class="logic.presentation.bean.CompanyBean" scope="session"/>
<jsp:setProperty name="companyBean" property="*"/>

<jsp:useBean id="addressBean" class="logic.presentation.bean.AddressBean" scope="session"/>
<jsp:setProperty name="addressBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<jsp:useBean id="countryBean" class="logic.presentation.bean.CountryBean" scope="session"/>
<jsp:setProperty name="countryBean" property="*"/>

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
		   						String nuova = o.toString().concat(",");
		   						for(Integer j=0;j<nuova.length();j++){
				   					if(nuova.charAt(j) != ','){
				   						str += nuova.charAt(j);
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
				   					}
		   						}
			   				}
		   				}
		   				%>
		   		
	    		<table contenteditable= "true" border="1" id='my_table' style="table-layout:fixed; width:600px;position:absolute;left:550px;top:185px; background-color:white">
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
      							<td style="height:20px"><%=request.getAttribute("country")%></td>
         						<td><%=request.getAttribute("state")%></td>
         						<td><%=request.getAttribute("city")%></td>
         						<td><%=request.getAttribute("postalCode")%></td>
         						<td><%=request.getAttribute("street")%></td>
         						<td><%=request.getAttribute("number")%></td>
      					</thead>
      					<tbody>
      					</tbody>
      			</table>
	    		<input class="companyTitle" type="text" name="companyndescription" value="Description" disabled style="top:200px; left:40px;">
	    		<input class="companyTxt" type="text" name="inputDescription" value="<%=RecruiterAccountControl.getInstance().retrieveCompanyInfo().getDescription()%>" style="top:300px;left:-215px;width:300px;height:150px;">
	    		<script>function addRow(id){
	    					var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
	    					var row = document.createElement("TR");
	    					var td1 = document.createElement("TD");
	    					td1.appendChild(document.createTextNode("colonna1"));
	    					var td2 = document.createElement("TD");
	    					td2.appendChild(document.createTextNode("colonna2"));
	    					var td3 = document.createElement("TD");
	    					td3.appendChild(document.createTextNode("colonna3"));
	    					var td4 = document.createElement("TD");
	    					td4.appendChild(document.createTextNode("colonna4"));
	    					var td5 = document.createElement("TD");
	    					td5.appendChild(document.createTextNode("colonna5"));
	    					var td6 = document.createElement("TD");
	    					td6.appendChild(document.createTextNode("colonna6"));
	    					row.appendChild(td1);
	    					row.appendChild(td2);
	    					row.appendChild(td3);
	    					row.appendChild(td4);
	    					row.appendChild(td5);
	    					row.appendChild(td6);
	    					tbody.appendChild(row);
	    		}
	    		</script>
	    		<a href="javascript:addRow('my_table')" style="width:60px; height:30px; margin-left:1150px;margin-top:-20px; background-color:dodgerblue">Add</a>
	    		</div>
	    		<form action="Company.jsp" name="Company" method="POST">
	   <script>function saveNewRow(id_table){
		   var address = new Array();
			var table = document.getElementById(id_table);
			var celle = table.getElementsByTagName('td');
			for(var j=6; j<celle.length; j++){
				var aggiungi = address.push(celle[j].innerHTML);
			}
			document.getElementById("hiddenField").value = address;
			<%if(request.getParameter("save_changes") != null){
				String string = "";
				String string1 = "";
				Integer p = 1;
				request.setAttribute("lista", request.getParameter("variabile"));
				string = request.getAttribute("lista").toString();
				String nuova1 = string.concat(",");
				for(Integer i=0;i<nuova1.length();i++){
					if(nuova1.charAt(i) != ','){
						string1 += nuova1.charAt(i);
					}else if(p == 1){
						request.setAttribute("country_mod", string1);
						System.out.println(request.getAttribute("country_mod").toString());
						p++;
						string1 = "";
						continue;
					}else if(p == 2){
						request.setAttribute("state_mod", string1);
						System.out.println(request.getAttribute("state_mod").toString());
						p++;
						string1 = "";
						continue;
					}else if(p == 3){
						request.setAttribute("city_mod", string1);
						System.out.println(request.getAttribute("city_mod").toString());
						p++;
						string1 = "";
						continue;
					}else if(p == 4){
						request.setAttribute("postalCode_mod", string1);
						System.out.println(request.getAttribute("postalCode_mod").toString());
						p++;
						string1 = "";
						continue;
					}else if(p == 5){
						request.setAttribute("street_mod", string1);
						System.out.println(request.getAttribute("street_mod").toString());
						p++;
						string1 = "";
						continue;
					}else{
						request.setAttribute("number_mod", string1);
					}
				}
				addressBean.setCity(request.getAttribute("city_mod").toString());
				addressBean.setPostalCode(request.getAttribute("postalCode_mod").toString());
				addressBean.setStreet(request.getAttribute("street_mod").toString());
				addressBean.setNumber(Integer.parseInt(request.getAttribute("number_mod").toString()));
				addressBean.setState(request.getAttribute("state_mod").toString());
				countryBean.setName(request.getAttribute("country_mod").toString());
				addressBean.setCountry(countryBean);
				addressBean.setId(0);
				List<AddressBean> branches = new ArrayList<> ();
				branches.add(addressBean);
				companyBean.setName(request.getParameter("inputName"));
				companyBean.setDescription(request.getParameter("inputDescription"));
				companyBean.setBranches(branches);
				RecruiterAccountControl.getInstance().changeCompanyInfo(companyBean);
				
				
			}%>

	    }</script>
	    <input type="hidden" id="hiddenField" name="variabile"/>
	    <button class="savechange_btn" type="submit" name="save_changes" value="save" style="width:100px; height:50px; top:550px; left:900px; background-color:dodgerblue" onClick="saveNewRow('my_table')">Save changes</button>
	    </form>
	</div>
	</body>
</html>