<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
 
<%Class.forName("com.mysql.jdbc.Driver");%>
 
<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
	    <link href="css/style.css" rel="stylesheet">
		<title>WorldWideJob - feasibility</title>
	</head>
	<body>
		<div>
			<form action="feasibility.jsp" name="feasibility" method="POST">
				<div class="dropdown" style="float:right;">
	    			<button class="menu_btn" style="background-color:lightblue;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="">Buy Premium Version</a>
		     			<a class="dropdown" href="">Support</a>
		     			<a href="http://localhost:8080/WorldWideJob/entrepreneurResearch.jsp">Quit</a>
		     		</div>
		     	</div>
		     	<div style="float:right;">
	    			<button class="notify_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		        <div class="dropdown" style="float:right;">
	    			<button class="user_btn" style="background-color:lightblue;margin-right:10px;width:40px;height:40px;margin-top:10px"></button>
		     		<div class="dropdown-content" style="right:0;">
		     			<a href="http://localhost:8080/WorldWideJob/entrepreneurProfile.jsp">Account</a>
		     			<a href="http://localhost:8080/WorldWideJob/login.jsp">Logout</a>
		     		</div>
		     	</div>
		     	</div>
		     	<div style="float:left;width:70px;height:70px">
	    			<img alt="" class="image" src="icons/main_icon.png" width=70px height=70px>
	    		</div>
	    		<div style="float:left">
	    			<button class="home_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='index.jsp';"></button>
	    		<div style="float:right;">
	    			<button class="arrow_btn" type="button" style="background-color:lightblue;width:40px;height:40px;margin-left:10px;margin-top:10px" onClick="javascript:window.location='businessDetails.jsp';"></button>
	    		</div>
	    		</div>
	    		<div>
	    		<input class="favourite_container" type="text" name="feasibility_container" value="" disabled style="background-color:#C6D6D3; height:1100px;">
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
		     	
		     	<input class="text_subtitle" type="text" name="utilities" value="Utilities (per month)" disabled style="background-color:#C6D6D3; top:610px">
		     	<input class="text_subsubtitle" type="text" name="basic" value="Basic (Electricity, Heating, Cooling, Water, Garbage) for a medium Apartment" disabled style="background-color:#C6D6D3; top:645px">
		    	<input class="text_input" type="text" name="input_basic" value="" style="background-color:white; top:525px; left:370px; width:150px">
		     	<input class="text_subsubtitle" type="text" name="prepaidmobiletariff" value="1 min. of Prepaid Mobile Tariff Local (No Discounts or Plans)" disabled style="background-color:#C6D6D3; top:680px">
		    	<input class="text_input" type="text" name="input_prepaidmobiletariff" value="" style="background-color:white; top:560px; left:100px; width:150px">
		     	<input class="text_subsubtitle" type="text" name="internet" value="Internet (60 Mbps or More, Unlimited Data, Cable/ADSL)" disabled style="background-color:#C6D6D3; top:715px">
		    	<input class="text_input" type="text" name="input_internet" value="" style="background-color:white; top:595px; left:-90px; width:150px">
		     	
		     	<input class="text_title" type="text" name="businesstaxes" value="Business taxes*" disabled style="background-color:#C6D6D3; margin-left:60%;">
		     	<input class="text_subsubtitle" type="text" name="incometax" value="Income tax" disabled style="background-color:#C6D6D3; top:200px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_incometax" value="" style="background-color:white; top:80px; left:200px;">
		     	<input class="text_subsubtitle" type="text" name="corporatetax" value="Corporate tax" disabled style="background-color:#C6D6D3; top:240px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_corporatetax" value="" style="background-color:white; top:120px; left:10px;">
		     	<input class="text_subsubtitle" type="text" name="capitalgains" value="Capital gains" disabled style="background-color:#C6D6D3; top:280px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_capitalgains" value="" style="background-color:white; top:130px; left:910px;">
		     	<input class="text_subsubtitle" type="text" name="salestax" value="Sales tax" disabled style="background-color:#C6D6D3; top:320px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_salestax" value="" style="background-color:white; top:175px; left:680px;">
		     	<input class="text_subsubtitle" type="text" name="propertytax" value="Property tax" disabled style="background-color:#C6D6D3; top:360px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_propertytax" value="" style="background-color:white; top:215px; left:480px;">
		     	
		     	<input class="text_title" type="text" name="*" value="*All taxes are based on a year" disabled style="background-color:#C6D6D3; margin-left:60%; top:395px;font-size: 13px;">
		     	
		     	<input class="text_title" type="text" name="busaverage" value="Business average managment costs*" disabled style="background-color:#C6D6D3; margin-left:60%; top:440px;">
		     	<input class="text_subsubtitle" type="text" name="payroll" value="Payroll (per employee)" disabled style="background-color:#C6D6D3; top:475px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_payroll" value="" style="background-color:white; top:330px; left:345px;">
		     	<input class="text_subsubtitle" type="text" name="insurance" value="Insurance" disabled style="background-color:#C6D6D3; top:515px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_insurance" value="" style="background-color:white; top:370px; left:40px;">
		     	<input class="text_subsubtitle" type="text" name="furniture" value="Furniture" disabled style="background-color:#C6D6D3; top:555px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_furniture" value="" style="background-color:white; top:375px; left:890px;">
		     	<input class="text_subsubtitle" type="text" name="supplies" value="Supplies" disabled style="background-color:#C6D6D3; top:600px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_supplies" value="" style="background-color:white; top:420px; left:670px;">
		     	<input class="text_subsubtitle" type="text" name="maintenance" value="Maintenance" disabled style="background-color:#C6D6D3; top:640px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_maintenance" value="" style="background-color:white; top:460px; left:490px;">
		     	
		     	<input class="text_title" type="text" name="**" value="*Other specific expenses are not included" disabled style="background-color:#C6D6D3; margin-left:60%; top:680px;font-size: 13px;">
		     	
		     	<input class="text_title" type="text" name="averageexpenses" value="Average expenses" disabled style="background-color:#C6D6D3; margin-left:30%; top:800px;font-size: 25px;">
		     	<input class="text_input" type="text" name="input_averageexpenses" value="" style="background-color:white; top:620px; left:0px; height:40px; font-size:20px">
		     	<input class="text_title" type="text" name="averageearnings" value="Averageearnings" disabled style="background-color:#C6D6D3; margin-left:30%; top:870px;font-size: 25px;">
		     	<input class="text_input" type="text" name="input_averageearnings" value="" style="background-color:white; top:685px; left:-213px; height:40px; font-size:20px">
		     	<input class="text_title" type="text" name="budget" value="Budget" disabled style="background-color:#C6D6D3; margin-left:30%; top:930px;font-size: 25px;">
		     	<input class="text_input" type="text" name="input_budget" value="" style="background-color:white; top:700px; left:635px; height:40px; font-size:20px">
		     	
		     	<input class="text_title" type="text" name="result" value="Result" disabled style="background-color:#C6D6D3; top:1020px; left:50%;font-size: 25px; font-weight: bold;">
		     	<input class="text_input" type="text" name="input_result" value="" style="background-color:white; top:830px; left:28%; height:40px; font-size:20px">
		     	
		     	<button class="saveresults_btn" style="width:150px; height:50px; top:900px; left:630px; background-color: dodgerblue; border-color: black">Save results</button>
		     	</div>
		     </form>
		</div>	
	</body>
</html>