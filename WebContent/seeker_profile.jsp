<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.File"
		 import="java.io.OutputStream"
		 import="java.io.FileOutputStream"
		 import="java.awt.Desktop"
		 import="java.io.IOException"
		 import="java.net.URISyntaxException"
		 import="java.net.URL"
		 import="java.util.Base64"
		 import="java.nio.charset.StandardCharsets"
		 import="java.nio.file.Files"
		 import="java.sql.Blob"
		 import="javax.sql.rowset.serial.SerialBlob"%> 
		 
<%@ page import="logic.presentation.bean.UserBean"
		 import="logic.presentation.bean.AccountBean"
		 import="logic.application.control.SeekerAccountControl"
		 import="logic.application.control.ManageAccountControl"
		 import="logic.exceptions.NoResultFoundException"
		 import="logic.application.SessionFacade"%>
		 
<!DOCTYPE html>

<jsp:useBean id="userBean" class="logic.presentation.bean.UserBean" scope="session"/>
<jsp:setProperty name="userBean" property="*"/>

<jsp:useBean id="accountBean" class="logic.presentation.bean.AccountBean" scope="session"/>
<jsp:setProperty name="accountBean" property="*"/>

<jsp:useBean id="cvBean" class="logic.presentation.bean.CVBean" scope="session"/>
<jsp:setProperty name="cvBean" property="*"/>

<%Class.forName("com.mysql.cj.jdbc.Driver");%>

<%
accountBean = ManageAccountControl.getInstance().retrieveAccount();
userBean =  ManageAccountControl.getInstance().retrievePersonalInfo(SessionFacade.getSession().getID());
%>
  				  
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/main_icon.png">
	    <link href="css/style.css" rel="stylesheet">
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<title>WorldWideJob - Profile</title>	
	</head>
	<body>
		<jsp:include page="WEB-INF/toolbar.jsp"/>
		<div id="main" style="height:680px;background-color:#8ecae6;border:1px solid blue">
			<form action="seeker_profile.jsp" name="seekerProfileform" method="POST">				
	    		<div class="profile_pic">
	    			<%if(accountBean.getPic() != null){
        				String path = "file://" + accountBean.getPic().getAbsolutePath();
					    path = path.replace("\\", "/");%>
	    				
	    				<img src="<%=path%>" alt="profile" id="profile" width="246" height="197">
					<%}else{%>
						<img src="icons/profile_pic.png" alt="profile" id="profile" width="246" height="197">
					<%}%>
	    		</div>
	    		
	    		<input class="change_pic" type="file" id="load_pic" name="load_pic" accept="image/*" onchange="uploadPic(this)">
	    		<input type="hidden" id="store_pic" name="store_pic">
	    		<button id="submit" name="submit" style="display:none"></button>
	    		<%if(request.getParameter("submit") != null){
					byte[] data = Base64.getDecoder().decode(request.getParameter("store_pic").getBytes(StandardCharsets.UTF_8));
				
					File newPic = new File("tmp/target.jpg");
					try (OutputStream stream = new FileOutputStream(newPic)) {
					    stream.write(data);
					}
					
					accountBean.setPic(newPic);
					ManageAccountControl.getInstance().updateAccountPic(accountBean);
				}%>
				
				<div class="name">
	    			<h2><%=accountBean.getUser().getFirstName() + " " + accountBean.getUser().getLastName()%></h2>
	    			<h3>Job Seeker</h3>
	    		</div>
	    		
	    		<div id="container">
					<ul id="griglia">
						<li><button class="cv_btn" id="cv" name="cv">Curriculum Vitae</button></li>
						<li><button class="id_btn" type="button" onClick="javascript:window.location='personal_info.jsp';">Personal Info</button></li>						
						<li><button class="offers_btn" type="button" onClick="javascript:window.location='applications.jsp';">Applications</button></li>
						<li><button class="fav_offers" type="button" onClick="javascript:window.location='favourite_offers.jsp';">Favourites</button></li>
					</ul>
				</div>
				
				<input type="file" id="load_cv" name="load_cv" accept=".pdf" style="position:absolute;top:-100px" onchange="uploadCv(this)">
				<input type="hidden" id="store_cv" name="store_cv">
				<button id="submit2" name="submit2" style="display:none"></button>
				<%if(request.getParameter("cv") != null){
					try {
						SeekerAccountControl.getInstance().retrieveCV(cvBean, accountBean.getId());
						
						String path = "file://" + cvBean.getCv().getAbsolutePath();
					    path = path.replace("\\", "/");
					    URL url;
						try {
							url = new URL(path);
							Desktop.getDesktop().browse(url.toURI());
						} catch (URISyntaxException | IOException e) {%>
							<script>window.alert("Sorry, the document cannot be opened")</script>
					  <%}
				    } catch (NoResultFoundException re) {%>
						<script>
						if (window.confirm("No CV has been uploaded. Do you want to upload one?")) {
							var input = document.getElementById("load_cv");
						    input.click();
						} else {
						    /**/
						}
						</script>
				 <%}
				}%>
				
				<%if(request.getParameter("submit2") != null){
					byte[] data = Base64.getDecoder().decode(request.getParameter("store_cv").getBytes(StandardCharsets.UTF_8));
				
					File newCV = new File("tmp/target.pdf");
					try (OutputStream stream = new FileOutputStream(newCV)) {
					    stream.write(data);
					}
					
					cvBean.setCv(newCV);
					SeekerAccountControl.getInstance().updateCV(newCV); 
				}%>
	    	</form>
	    </div>
	</body>
	<script src="js/toolbar.js"></script>
	<script src="js/files.js"></script>
</html>