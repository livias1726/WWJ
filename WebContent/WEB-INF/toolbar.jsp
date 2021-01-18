<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="logic.application.SessionFacade"%>

<div class="dropdown" style="float:right;">
	<button class="menu_btn"></button>
    <div class="dropdown-content" style="right:0;">
      	<%if(SessionFacade.getSession().isPremium()){%>
      		<a href="">Cancel premium subscription</a>
      	<%} else {%>
      		<a href="src/logic/presentation/resources/html/pricing.html">Buy premium version</a>
      		<a href="src/logic/presentation/resources/html/support.html">Support</a>
      	<%}%>		     		
   	</div>
</div>
<div class="dropdown" style="float:right;">		     				     		
	<button class="user_btn"></button>
    <div class="dropdown-content" style="right:0;">
      	<%if(SessionFacade.getSession().getID() == null){%>
      		<a href="http://localhost:8080/WorldWideJob/login.jsp">Login</a>
      	<%} else {%>
      		<a href="http://localhost:8080/WorldWideJob/entrepreneurProfile.jsp">Profile</a>
      		<a href="http://localhost:8080/WorldWideJob/index.jsp">Logout</a>
      	<%}%>		     		
   	</div>
</div>
<%if(SessionFacade.getSession().getID() != null){%>
	<div class="dropdown" style="float:right;">
		<button class="notify_btn"></button>
		<div class="dropdown-content" style="right:0;"></div>
    </div>
<%}%>
<div style="float:left;width:70px;height:70px">
	<img alt="" class="" src="icons/main_icon.png" width=50px height=50px>
</div>
<div style="float:left">
	<button class="home_btn" type="button" onClick="javascript:window.location='entrepreneur_search.jsp';"></button>
</div>

