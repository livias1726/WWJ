<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logic.application.control.CheckCandidatesControl"
 	import="logic.presentation.bean.CandidateBean"
 	import="java.util.List"
 	import="java.util.ArrayList"%>

<!DOCTYPE html>

<jsp:useBean id="candidateBean" class="logic.presentation.bean.CandidateBean" scope="session"/>
<jsp:setProperty name="candidateBean" property="*"/>

<%
	Class.forName("com.mysql.jdbc.Driver");
%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
	    <link href="css/style.css" rel="stylesheet">
		
		<title>WorldWideJob - Candidates</title>
	</head>
	<body>
		<div>
			<form action="candidates.jsp" name="candidates" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/publish.jsp">Publish Job Offer</a>
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/index.jsp">Quit</a>
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
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;" onClick="javascript:window.location='recruiterProfile.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='recruiterProfile.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="candidates_container" value="" disabled style="background-color:#C6D6D3">
	    		<input class="favourite_title" type="text" name="candidates_title" value="Candidates" disabled style="background-color:#C6D6D3">
	    		
	    		<table contenteditable= "true" border="0" id='my_table' style="table-layout:fixed; width:800px;position:absolute;left:270px;top:200px;background-color:white">
	    			<thead>
      						<tr>
         						<th style="height:30px; color:black; background-color:dodgerblue">Offer</th>
         						<th style="height:30px; color:black; background-color:dodgerblue">Candidate</th>
      						</tr>
      						<tr>
      							<td style="height:20px"></td>
         						<td><%=CheckCandidatesControl.getInstance().retrieveCandidates()%></td>
   							</tr>
   							<tr>
      							<td style="height:20px"></td>
         						<td></td>
   							</tr>
   							<tr>
      							<td style="height:20px"></td>
         						<td></td>
   							</tr>
      					</thead>
      					<tbody>
      					</tbody>
      			</table>
	    		<input class="candidate_row" type="checkbox" name="check1" style="height:20px; left:-320px; top:230px"><br>
	    		<input class="candidate_row" type="checkbox" name="check2" style="height:20px; left:-320px; top:255px"><br>    
	    		<input class="candidate_row" type="checkbox" name="check3" style="height:20px; left:-320px; top:280px"><br>
	    		<input class="candidate_row" type="checkbox" name="checkall" style="height:20px; left:-320px; top:160px" onClick="Check();">
	    		<input class="candidate_row" type="text" disabled name="selectAll" value="Select all" style="border:0; height:20px; width:60px; left:270px; top:160px">
	    		
	    		<script>
					function Check(){
						if (document.candidates.checkall.checked){
							document.candidates.check1.checked=1;
							document.candidates.check2.checked=1;
							document.candidates.check3.checked=1;
						}
					}
				</script>
				
	    		<button class="delete_btn" name="deleteCand" style="width:100px; height:50px; top:400px; left:550px; background-color:dodgerblue" onClick="deleteCandidates()">Delete</button>
	    		
	    		</div>
	    		
	    	</form>
	    </div>
	    <script> function deleteCandidates(){
	    	<%if(request.getParameter("deleteCand") != null){
	    		System.out.println(CheckCandidatesControl.getInstance().retrieveCandidates());
	    		if(request.getParameter("check1") != null || request.getParameter("check2") != null || request.getParameter("check3") != null){
	    			List<Long> listCandidates = new ArrayList<> ();
	    			listCandidates.add(candidateBean.getSeeker());
	    			List<Integer> listOffers = new ArrayList<> ();
	    			listOffers.add(candidateBean.getOffer());
	    			CheckCandidatesControl.getInstance().removeCandidates(listCandidates, listOffers);
	    			
	    		}
	    	}%>
	    }
	    
	    </script>	
	</body>	
</html>