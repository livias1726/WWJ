<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.StringTokenizer"%>

<%@ page import="logic.application.control.CheckCandidatesControl"
 		 import="logic.presentation.bean.CandidateBean"
 		 import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.ManageAccountControl"
 		 import="java.util.List"
 		 import="java.util.ArrayList"%>
 		 
<%@page errorPage="WEB-INF/error.jsp"%>

<!DOCTYPE html>

<jsp:useBean id="candidateBean" class="logic.presentation.bean.CandidateBean" scope="session"/>
<jsp:setProperty name="candidateBean" property="*"/>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%if(request.getParameter("candidate") != null){ 
	String res = request.getParameter("candidate");
	accountBean.setId(Long.parseLong(res));
	String redirectURL = "http://localhost:8080/WorldWideJob/public_seeker_profile.jsp";
	response.sendRedirect(redirectURL); 
}%>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
	    <link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<title>WorldWideJob - Candidates</title>
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="candidates.jsp" name="candidates" method="POST">
				<table class="table_candidates">
					<tr>
					    <th id="check"><input type="checkbox" id="select" name="select" onclick="selectAll(this)"></th>
					    <th id="offer_col">Offer</th>
					    <th id="cand_col">Candidate</th>
				  	</tr>
				  	<%for(CandidateBean i: CheckCandidatesControl.getInstance().retrieveCandidates()){ %>
						<tr>
						    <td><input type="checkbox" id="sel" name="sel" value="<%=i.getSeeker() + "&" + i.getOffer()%>" onclick="selectElem(this)"></td>
						    <td><%=i.getOffer()%></td>
						    <td><button name="candidate" value="<%=i.getSeeker()%>"><%=i.getName()%></button></td>
						</tr>
					<%}%>
				</table>	
    			<button class="delete_btn" id="delete" name="delete" value="">Delete</button>
    			<%if(request.getParameter("delete") != null){
    				List<Long> list = new ArrayList<>();
    				List<Integer> offers = new ArrayList<>();
    				
    				String res = request.getParameter("delete");
    				StringTokenizer tok = new StringTokenizer(res, "&");
    				
    				while(tok.hasMoreTokens()){
    					list.add(Long.parseLong(tok.nextToken()));
    					offers.add(Integer.parseInt(tok.nextToken()));
    				}
    				
    				if(list.size() > 0){
    					CheckCandidatesControl.getInstance().removeCandidates(list, offers);
    					String redirectURL = "http://localhost:8080/WorldWideJob/candidates.jsp";
    					response.sendRedirect(redirectURL); 
    				}
    			}%>
	    	</form>
	    </div>	
	</body>	
	<script src="js/toolbar.js"></script>
	<script src="js/tables.js"></script>
</html>