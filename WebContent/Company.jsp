<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
	    
		<title>WorldWideJob - Company</title>
	</head>
	
	<body>
		<div>
			<form action="candidates.jsp" name="candidates" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Publish Job Offer</a>
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="">Quit</a>
		     		</div>
		     	</div>
		     	<div style="float:right;">
	    			<button class="notify_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Account</a>
		     			<a href="">Logout</a>
		     		</div>
		     	</div>
		     	</div>
	    		<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px;"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="company_container" value="" disabled style="background-color:#C6D6D3">	
	    		<button class="change_btn" style="width:100px; height:50px; top:130px; left:-100px; background-color:dodgerblue">Change</button>
	    		<button class="savechange_btn" style="width:100px; height:50px; top:550px; left:800px; background-color:dodgerblue">Save change</button>
	    		<input class="companyname" type="text" name="companyname" value="Name" disabled style="background-color:#C6D6D3">	
	    		<input class="companybranches" type="text" name="companybranches" value="Branches" disabled style="background-color:#C6D6D3">	
	    		<input class="companydescription" type="text" name="companyndescription" value="Description" disabled style="background-color:#C6D6D3">	
	    		<input class="companyheadquarter" type="text" name="companyheadquarter" value="Headquarter" disabled style="background-color:#C6D6D3">
	    		<input class="companyname" type="text" name="inputName" style="background-color:white;top:175px;left:-305px;">
	    		<input class="companybranches" type="text" name="inputBranches" style="background-color:white;top:230px;left:-197px;height:150px">
	    		<input class="companydescription" type="text" name="inputDescription" style="background-color:white;top:350px;left:280px;">
	    		<input class="companyheadquarter" type="text" name="inputHeadquarter" style="background-color:white;top:350px;left:390px;">
	    		<button class="add_btn" style="width:40px;height:40px;background-color:lightblue;top:76px;left:400px">Add</button>
	    		</div>
	    	</form>
	    </div>
	</body>
</html>