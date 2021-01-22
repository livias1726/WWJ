<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.presentation.bean.JobBean"
	import="logic.presentation.bean.AddressBean"
	import="logic.presentation.bean.OfferBean"
	import="logic.application.control.PublishOfferControl"
	import="logic.application.control.RecruiterAccountControl"
	import="java.util.List"
	import="java.util.ArrayList"
	import="java.time.LocalTime"
	import="java.time.LocalDate"
	import="java.time.format.DateTimeFormatter"
%>

<jsp:useBean id="jobBean" class="logic.presentation.bean.JobBean" scope="session"/>
<jsp:setProperty name="jobBean" property="*"/>	

<jsp:useBean id="addressBean" class="logic.presentation.bean.AddressBean" scope="session"/>
<jsp:setProperty name="addressBean" property="*"/>	

<jsp:useBean id="offerBean" class="logic.presentation.bean.OfferBean" scope="session"/>
<jsp:setProperty name="offerBean" property="*"/>
    
<!DOCTYPE html>

<%Class.forName("com.mysql.jdbc.Driver");%>

<%List<Integer> jobId = new ArrayList<>();
List<Integer> branchId = new ArrayList<>();
%>
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link href="css/style.css" rel="stylesheet">
		<title>WorldWideJob - publish</title>
	</head>
	<body>
		<div>
			<form action="publish.jsp" name="publish" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/recruiterProfile.jsp">Quit</a>
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
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="publish_container" value="" disabled style="background-color:#C6D6D3;height:800px">
	    		<input class="text_title" type="text" name="thejob" value="The Job" disabled style="background-color:#C6D6D3;margin-top:-40px;margin-left:10px">
	    		<input class="text_subsubtitle" type="text" name="position" value="Position" disabled style="background-color:#C6D6D3;margin-top:-40px; margin-left:20px">
	    		<select class="order_select" name="position" style="background-color:white;width:250px;height:30px;margin-left:50px;margin-top:-60px">
	    			<option></option>
	    			<%for(JobBean i: PublishOfferControl.getInstance().retrieveJobs()){
	    				jobId.add(i.getId());%>
	    				<option><%=i.getName()%></option>
	    			<%} %>
	    		</select> 
	    			    		
	    		<button class="add_btn" type="submit" name="addjob" style="border:0; background-color:#C6D6D3; color:dodgerblue;margin-top:230px;margin-left:-102px">Add a new job...</button><br>
	    		<%if(request.getParameter("addjob") != null){%>
	    			<textarea class="text_input" name="jobname" style="background-color:white; margin-top:20px; margin-left:50px; width:150px;height:30px;" onfocus="this.value=''">Job name</textarea>
	    			<textarea class="text_input" name="jobcategory" style="background-color:white; margin-top:20px; margin-left:100px; width:150px;height:30px;" onfocus="this.value=''">Job category</textarea>
	    			<button class="saveresults_btn" type="submit" name="add" style="width:40px; height:30px; top:-10px; left:60px; background-color: dodgerblue; border-color: black">Add</button>
	    		<%}%>
	    		<%if(request.getParameter("jobname") != null && request.getParameter("jobcategory") != null){
	    		jobBean.setName(request.getParameter("jobname"));
	    		jobBean.setCategory(request.getParameter("jobcategory"));
	    		jobBean.verifyFields(jobBean.getName(), jobBean.getCategory());
	    		PublishOfferControl.getInstance().saveNewJob(jobBean);
	    		}%>
	    		</div>
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="requirements" value="Requirements" disabled style="background-color:#C6D6D3;margin-top:100px; margin-left:20px">
	    		<textarea class="text_input" name="requirementsInput" style="background-color:white; height:90px; width:300px; margin-top:88px; margin-left:190px;"></textarea>
	    		<%if(request.getParameter("requirementsInput") != null){
	    			offerBean.setRequirements2(request.getParameter("requirementsInput"));
	    			System.out.println(offerBean.getRequirements2());
	    		}%>
	    		</div>
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="description" value="Activities description" disabled style="background-color:#C6D6D3;margin-top:270px; margin-left:20px">
	    		<textarea class="text_input" name="descriptionInput" style="background-color:white; height:90px; width:500px; margin-top:100px; margin-left:74px;"></textarea>
	    		</div>
	    		<div>
	    		<input class="text_title" type="text" name="information" value="Additional information" disabled style="background-color:#C6D6D3; margin-left:50%;margin-top:-40px ">
	    		<input class="text_subsubtitle" type="text" name="companyBranch" value="Company branch" disabled style="background-color:#C6D6D3;margin-top:-40px; margin-left:50%">
	    		<select class="order_select" name="companyBranchSelect" style="background-color:white;width:250px;height:30px;margin-left:58%;margin-top:-60px">
	    			<option></option>
	    			<%for(AddressBean i: PublishOfferControl.getInstance().retrieveCompanyInfo()){
	    				branchId.add(i.getId());%>
	    				<option><%=i.toString()%></option>
	    			<%}%>
	    		</select> 
	    		</div>
	    		<input class="text_subsubtitle" type="text" name="timeslot" value="Time slot" disabled style="background-color:#C6D6D3;margin-top:40px; margin-left:50%">
	    		<input class="text_input" type="time" name="start" style="background-color:white; top:-360px; margin-left:63%;width:150px;height:30px;">
	    		<input class="text_input" type="time" name="finish" style="background-color:white; top:-395px; margin-left:80%;width:150px;height:30px;">
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="salary" value="Base salary" disabled style="background-color:#C6D6D3;margin-top:130px; margin-left:50%">
	    		<textarea class="text_input" name="salaryInput" style="background-color:white; top:-340px; margin-left:64%;width:250px;height:30px;"></textarea>
	    		<select class="order_select" name="money" style="background-color:white;width:50px;height:35px;margin-left:76%;margin-top:112px">
	    			<option>$</option>
	    			<option>&pound;</option>
	    			<option>&euro;</option>
	    		</select> 
	    		</div>
	    		<div>
	    		<input class="text_title" type="text" name="others" value="Others" disabled style="background-color:#C6D6D3; margin-left:50%;margin-top:300px ">
	    		</div>
	    		<div>
	    		<input class="text_subsubtitle" type="text" name="expirationDate" value="Expiration date" disabled style="background-color:#C6D6D3; margin-left:50%;margin-top:300px ">
	    		<input class="text_input" type="date" name="dateInput" style="background-color:white; top:-212px; margin-left:65%;width:250px;height:30px;">
	    		</div>
	    		<div>
	    		<button class="saveresults_btn" type="submit" name="publish" style="width:150px; height:50px; top:-80px; left:1050px; background-color: dodgerblue; border-color: black">Publish</button>
	    		<%if(request.getParameter("publish") != null){
	    			Integer count = 1;
   					Object o;
   					List<JobBean> jobs = PublishOfferControl.getInstance().retrieveJobs();
   					for(Integer i=0; i<jobs.size(); i++){
   						o = jobs.get(i).toString();
   						if(request.getParameter("position") == o){
   							jobBean.setId(count);
   							break;
   						}else{
   							count++;
   							continue;
   						}
   					}
   					
   					offerBean.setPosition(jobBean);
   					offerBean.setTaskDescription(request.getParameter("descriptionInput"));
   					offerBean.setRequirements2(request.getParameter("requirementsInput"));
   					
   					Integer count2 = 1;
   					Object o2;
   					List<AddressBean> branches = RecruiterAccountControl.getInstance().retrieveCompanyInfo().getBranches();
   					for(Integer i=0; i<branches.size(); i++){
   						o2 = branches.get(i).toString();
   						if(request.getParameter("companyBranchSelect") == o2){
   							addressBean.setId(count2);
   							break;
   						}else{
   							count2++;
   							continue;
   						}
   					}
   					 
   					offerBean.setBranch(addressBean);
   					offerBean.setStart(LocalTime.parse(request.getParameter("start"), DateTimeFormatter.ofPattern("H[:mm]")));
   					offerBean.setFinish(LocalTime.parse(request.getParameter("finish"), DateTimeFormatter.ofPattern("H[:mm]")));
   					
   					offerBean.setBaseSalary(request.getParameter("money") + " " + request.getParameter("salaryInput"));
   					offerBean.setExpiration(LocalDate.parse(request.getParameter("dateInput")));
   					offerBean.verifyFieldsValidity(offerBean.getStart(), offerBean.getFinish(), offerBean.getExpiration());
   					PublishOfferControl.getInstance().publishNewOffer(offerBean);
	    		} %>
	    		</div>
	    	</form>
	    </div>		
	</body>
</html>