<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
		
		<title>WorldWideJob - FavouriteJobOffers</title>
		
	</head>
	<body>
		<div>
			<form action="favouriteJobOffers.jsp" name="favouriteJobOffers" method="POST">
				<div style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
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
	    		<input class="favourite_container" type="text" name="favourite_container" value="" disabled style="background-color:#C6D6D3">
	    		<input class="favourite_title" type="text" name="favourite_title" value="Favourite job offers" disabled style="background-color:#C6D6D3">
	    		<input class="order_by" type="text" name="orderby" value="  Ordered by:" disabled style="background-color:lightgrey">
	    		<select class="order_select" name="orderselect" size="1" style="background-color:whitesmoke">
	    			<option>expiration</option>
	    			<option>most recent</option>
					<option>alphabetical order</option>
	    		</div>
			</form>
	</body>
</html>