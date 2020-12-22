<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
 
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="icon" href="icons/search_usr.png">
	    <link href="css/style.css" rel="stylesheet">
		<title>WorldWideJob - Feasibility</title>
	</head>
	<body>
		<div>
			<form action="Feasibility.jsp" name="feasibility" method="POST">
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
	    		<input class="favourite_container" type="text" name="feasibility_container" value="" disabled style="background-color:#C6D6D3">
	    		<input class="favourite_title" type="text" name="feasibility_title" value="Feasibility" disabled style="background-color:#C6D6D3">
		     	<input class="insert_budget" type="text" name="insert_budget" value="Insert budget" disabled style="background-color:#C6D6D3; top:85px; left:500px"><br>
	    		<input class="budget" type="text" name="budget" value="" style="background-color:#E4F5F2; top:50px; left:600px">	
		     	<button class="calculatefeasibility_btn" style="width:150px; height:50px; top:50px; left:630px; background-color: dodgerblue; border-color: black">Recalculate</button>
		     	
		     	<input class="text_title" type="text" name="basicLivingExpense" value="Basic living expense" disabled style="background-color:#C6D6D3;">
		     	<input class="text_subtitle" type="text" name="groceries" value="Groceries (per kg)" disabled style="background-color:#C6D6D3;">
		     	<input class="text_subsubtitle" type="text" name="fruit" value="Fruit" disabled style="background-color:#C6D6D3;">
		     	<input class="text_input" type="text" name="input_fruit" value="" style="background-color:white; top:175px;left:-480px;">
		     	<input class="text_subsubtitle" type="text" name="vegetables" value="Vegetables" disabled style="background-color:#C6D6D3; top:270px">
		     	<input class="text_input" type="text" name="input_vegetables" value="" style="background-color:white; top:215px; left:-650px;">
		     	<input class="text_subsubtitle" type="text" name="animalproducts" value="Products of animal original" disabled style="background-color:#C6D6D3; top:310px">
		     	<input class="text_input" type="text" name="input_animalproducts" value="" style="background-color:white; top:215px; left:250px;">
		   		<input class="text_subsubtitle" type="text" name="cerealproducts" value="Cereal products" disabled style="background-color:#C6D6D3; top:345px">
		     	<input class="text_input" type="text" name="input_cerealproducts" value="" style="background-color:white; top:250px; left:-30px;">
		     	
		     	<input class="text_subtitle" type="text" name="apartment" value="Apartment (per square meter)" disabled style="background-color:#C6D6D3; top:390px">
		     	<input class="text_subsubtitle" type="text" name="citycenter" value="Buy apartment in city center" disabled style="background-color:#C6D6D3; top:425px">
		     	<input class="text_input" type="text" name="input_citycenter" value="" style="background-color:white; top:330px; left:-170px;">
		     	<input class="text_subsubtitle" type="text" name="outsidecenter" value="Buy apartment outside the center" disabled style="background-color:#C6D6D3; top:460px">
		     	<input class="text_input" type="text" name="input_ousidecenter" value="" style="background-color:white; top:365px; left:-350px;">
		     	
		     	<input class="text_subtitle" type="text" name="transport" value="Public transport" disabled style="background-color:#C6D6D3; top:500px">
		     	<input class="text_subsubtitle" type="text" name="one-wayticket" value="One-way ticket (local transport)" disabled style="background-color:#C6D6D3; top:530px">
		     	<input class="text_input" type="text" name="input_one-wayticket" value="" style="background-color:white; top:435px; left:-570px;">
		     	<input class="text_subsubtitle" type="text" name="monthlypass" value="Monthly pass (regular price)" disabled style="background-color:#C6D6D3; top:565px">
		     	<input class="text_input" type="text" name="input_monthlypass" value="" style="background-color:white; top:445px; left:260px;">
		     	
		     	<input class="text_title" type="text" name="businesstaxes" value="Business taxes*" disabled style="background-color:#C6D6D3; margin-left:50%;">
		     	<input class="text_subsubtitle" type="text" name="incometax" value="Income tax" disabled style="background-color:#C6D6D3; top:200px; margin-left:50%">
		     	<input class="text_input" type="text" name="input_incometax" value="" style="background-color:white; top:80px; left:570px;">
		     	<input class="text_subsubtitle" type="text" name="corporatetax" value="Corporate tax" disabled style="background-color:#C6D6D3; top:240px; margin-left:50%">
		     	<input class="text_input" type="text" name="input_corporatetax" value="" style="background-color:white; top:120px; left:380px;">
		     	<input class="text_subsubtitle" type="text" name="capitalgains" value="Capital gains" disabled style="background-color:#C6D6D3; top:280px; margin-left:50%">
		     	<input class="text_input" type="text" name="input_capitalgains" value="" style="background-color:white; top:160px; left:160px;">
		     	<input class="text_subsubtitle" type="text" name="salestax" value="Sales tax" disabled style="background-color:#C6D6D3; top:320px; margin-left:50%">
		     	<input class="text_input" type="text" name="input_salestax" value="" style="background-color:white; top:200px; left:-80px;">
		     	<input class="text_subsubtitle" type="text" name="propertytax" value="Property tax" disabled style="background-color:#C6D6D3; top:360px; margin-left:50%">
		     	<input class="text_input" type="text" name="input_propertytax" value="" style="background-color:white; top:215px; left:790px;">
		     	
		     	<input class="text_title" type="text" name="busaverage" value="Business average managment costs*" disabled style="background-color:#C6D6D3; margin-left:50%; top:420px">
		     	<input class="text_subsubtitle" type="text" name="payroll" value="Payroll (per employee)" disabled style="background-color:#C6D6D3; top:455px; margin-left:50%">
		     	<input class="text_input" type="text" name="input_payroll" value="" style="background-color:white; top:310px; left:650px;">
		     	<input class="text_subsubtitle" type="text" name="insurance" value="Insurance" disabled style="background-color:#C6D6D3; top:495px; margin-left:50%">
		     	<input class="text_input" type="text" name="input_insurance" value="" style="background-color:white; top:350px; left:350px;">
		     	
		     	<input class="text_title" type="text" name="*" value="*All taxes are based on a year" disabled style="background-color:#C6D6D3; margin-left:50%; top:560px;font-size: 13px;">
		     	</div>
		     </form>
		</div>	
	</body>
</html>