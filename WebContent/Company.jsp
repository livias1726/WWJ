<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List"
		 import="java.util.ArrayList"%>

<%@ page import="logic.presentation.bean.CompanyBean"
		 import="logic.presentation.bean.AddressBean"
		 import="logic.presentation.bean.CountryBean"
		 import="logic.application.control.RecruiterAccountControl"
		 import="logic.application.control.ManageAccountControl"%>

<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="companyBean" class="logic.presentation.bean.CompanyBean" scope="session"/>
<jsp:setProperty name="companyBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%if(request.getParameter("save") != null){
	CompanyBean bean = new CompanyBean();
	bean.setName(request.getParameter("name"));   	
	bean.setDescription(request.getParameter("description"));
	
	String[] country = request.getParameterValues("r_country");
	for(String i: country){
		System.out.println(i);
	}
	String[] state = request.getParameterValues("r_state");
	String[] city = request.getParameterValues("r_city");
	String[] street = request.getParameterValues("r_street");
	String[] number = request.getParameterValues("r_number");
	String[] zip = request.getParameterValues("r_zip");
	String[] id = request.getParameterValues("r_id");
	
	List<AddressBean> branches = new ArrayList<>();
	for(int i=0; i<country.length; i++){
		AddressBean newBranch = new AddressBean();
		CountryBean cBean = new CountryBean();
		
		cBean.setName(country[i]);
		newBranch.setCountry(cBean);
		
		newBranch.setState(state[i]);
		newBranch.setCity(city[i]);
		newBranch.setStreet(street[i]);
		newBranch.setNumber(Integer.parseInt(number[i]));
		newBranch.setPostalCode(zip[i]);
		newBranch.setId(Integer.parseInt(id[i]));
		
		branches.add(newBranch);
	}
	
	bean.setBranches(branches);
	
	RecruiterAccountControl.getInstance().changeCompanyInfo(bean);
	
	String redirectURL = "http://localhost:8080/WorldWideJob/company.jsp";
	response.sendRedirect(redirectURL); 
}%>

<%companyBean = RecruiterAccountControl.getInstance().retrieveCompanyInfo();%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		
		<title>WorldWideJob</title>
	</head>	
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="company.jsp" name="company" method="POST">
				<button class="change_btn" id="change" type="button">Change</button>
				<div style="margin-left:50px;margin-top:25px;width:500px">
					<h2>Name</h2>
					<input id="name" name="name" type="text" value="<%=companyBean.getName()%>" style="margin-left:25px;" readonly>
					
					<br>
					<br>
					
					<h2>Description</h2>
					<textarea class="act_desc" id="description" name="description" rows="" cols="" style="margin-left:25px;" readonly><%=companyBean.getDescription()%></textarea>
				</div>
		    		
		    	<div style="position:absolute;right:50px;top:125px">
		    		<h2>Branches</h2>
		    		<table id="table" class="table_branch">
						<tr>
						    <th id="country_col">Country</th>
						    <th id="state_col">State</th>
						    <th id="city_col">City</th>
						    <th id="street_col">Street</th>
						    <th id="num_col">Number</th>
						    <th id="zip_col">Zip Code</th>
						    <th id="id_col" style="display:none"></th>
					  	</tr>
					  	<%for(AddressBean i: companyBean.getBranches()){ %>
							<tr>
							    <td><input type="hidden" name="r_country" value="<%=i.getCountryName()%>"><%=i.getCountryName()%></td>
							    <%if(i.getState() != null){ %>
							    	<td><input type="hidden" name="r_state" value="<%=i.getState()%>"><%=i.getState()%></td>
							    <%}else{ %>
							    	<td><input type="hidden" name="r_state" value=""></td>
							    <%} %>
							    <td><input type="hidden" name="r_city" value="<%=i.getCity()%>"><%=i.getCity()%></td>
							    <td><input type="hidden" name="r_street" value="<%=i.getStreet()%>"><%=i.getStreet()%></td>
							    <td><input type="hidden" name="r_number" value="<%=i.getNumber()%>"><%=i.getNumber()%></td>
							    <td><input type="hidden" name="r_zip" value="<%=i.getPostalCode()%>"><%=i.getPostalCode()%></td>
							    <td style="display:none"><input type="hidden" name="r_id" value="<%=i.getId()%>"></td>
							</tr>
						<%}%>
					</table>
					<div id="new_branch" style="display:none; width:200px">
						<input id="country" type="text" name="country" placeholder="Country">
						<input id="state" type="text" name="state" placeholder="State">
						<input id="city" type="text" name="city" placeholder="City">
						<input id="street" type="text" name="street" placeholder="Street">
						<input id="number" type="text" name="number" placeholder="Number">
						<input id="zip" type="text" name="zip" placeholder="Zip Code">
				    	<button id="add_branch" type="button" name="add_branch" onClick="addBranch()">Add</button>
					</div>
		    	</div>
		    	<button class="save_btn" id="save" name="save" style="display:none">Save changes</button>
			</form>
		</div>
	</body>
	<script src="js/toolbar.js"></script>
	<script src="js/company.js"></script>
</html>