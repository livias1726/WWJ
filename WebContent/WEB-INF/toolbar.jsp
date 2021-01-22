<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="logic.application.SessionFacade"
		import="logic.application.Users"
		import="logic.presentation.bean.AccountBean"
		import="logic.application.control.ManageAccountControl"%>
		
<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<div style="width:100%;height:60px;background-color:white;border:1px solid blue;">
	<!-- MENU -->
	<div class="dropdown" style="float:right;">
		<button class="menu_btn"></button>
	    <div class="dropdown-content" style="right:0;">
	      	<%if(SessionFacade.getSession().isPremium()){%>
	      		<a href="">Cancel premium subscription</a>
	      	<%} else {%>
	      		<a href="html/pricing.html">Buy premium version</a>
	      		<a href="html/support.html">Support</a>
	      	<%}%>		     		
	   	</div>
	</div>
	
	<!-- USER -->
	<div class="dropdown" style="float:right;">		     				     		
		<button class="user_btn"></button>
	    <div class="dropdown-content" style="right:0;">
	      	<%if(SessionFacade.getSession().getID() == null){%>
	      		<a href="http://localhost:8080/WorldWideJob/login.jsp">Login</a>
	      	<%} else {
	      		if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {%>
					<a href="http://localhost:8080/WorldWideJob/seeker_profile.jsp">Profile</a>
			   <%}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {%>
			   		<a href="http://localhost:8080/WorldWideJob/recruiter_profile.jsp">Profile</a>
			   <%}else{%>
				    <a href="http://localhost:8080/WorldWideJob/entrepreneur_profile.jsp">Profile</a>
			   <%}%>	      		
	      		<a href="http://localhost:8080/WorldWideJob/index.jsp">Logout</a>
	      	<%}%>		     		
	   	</div>
	</div>
	
	<!-- NOTIFICATIONS -->
	<%if(SessionFacade.getSession().getID() != null){%>
		<div class="dropdown" style="float:right;">
			<button class="notify_btn" id="notify_btn"></button>
			<div id="notif" class="dropdown-content" style="right:0;">
			  <%int count=0;
			  	for(String i: ManageAccountControl.getInstance().retrieveNotifications()) {%>
					<button value=<%=i%>><%=i%></button>
					<div class="notify_window" id="window" style="display:none;"><%=i%></div>
					<button style="display:none;" id="click" value=""></button>
					<%count++;
			    }
			    
			    if(count != 0){%>
			    	<script>$("#notify_btn").css("background-color", "lightblue");</script>
			  <%}%>
			</div>
	    </div>
	<%}%>
	
	<!-- ICON -->
	<div style="float:left;width:70px;height:70px">
		<img alt="" class="" src="icons/main_icon.png" width=50px height=50px>
	</div>
	
	<!-- HOME -->
	<div style="float:left">
		<%if(SessionFacade.getSession().getCurrUserType() == Users.SEEKER) {%>
			<button class="home_btn" type="button" onClick="javascript:window.location='seeker_search.jsp';"></button>
			
	   <%}else if(SessionFacade.getSession().getCurrUserType() == Users.RECRUITER) {%>
	   		<button class="home_btn" type="submit" name="home"></button>
	   		<%if (request.getParameter("home") != null) {
				String redirectURL = "http://localhost:8080/WorldWideJob/recruiterProfile.jsp";
				response.sendRedirect(redirectURL);
			}
	     }else{%>
		    <button class="home_btn" type="button" onClick="javascript:window.location='entrepreneur_search.jsp';"></button>
	   <%}%>
	</div>
</div>
