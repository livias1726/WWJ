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
	    		<input class="favourite_container" type="text" name="feasibility_container" value="" disabled style="background-color:#C6D6D3; height:700px;">
	    		<input class="favourite_title" type="text" name="feasibility_title" value="Feasibility -" disabled style="background-color:#C6D6D3">
	    		<input class="favourite_title" type="text" name="feasibility_title" value="Business name" disabled style="background-color:#C6D6D3; left:180px">
		     	
		     	<input class="insert_budget" type="text" name="insert_budget" value="Insert budget" disabled style="background-color:#C6D6D3; top:85px; left:500px"><br>
	    		<input class="budget" type="text" name="budget" value="" style="background-color:#E4F5F2; top:50px; left:600px">	
		     	<button class="calculatefeasibility_btn" style="width:150px; height:50px; top:50px; left:630px; background-color: dodgerblue; border-color: black">Recalculate</button>
		     	
		     	<input class="text_title" type="text" name="basicLivingExpense" value="Basic living expense" disabled style="background-color:#C6D6D3;">
		     	<input class="text_subtitle" type="text" name="livingexpenses" value="Total Living Expenses in ExampleCity - 1 person, for year (without rent)" disabled style="background-color:#C6D6D3; top:210px; width:700px">
		     	<input class="text_input" type="text" name="input_livingexpenses" value="" style="background-color:white; width:600px; top:150px;left:40px;">
		     	
		     	<input class="text_title" type="text" name="businesstaxes" value="Business taxes*" disabled style="background-color:#C6D6D3; margin-left:60%; top:180px;">
		     	<input class="text_subsubtitle" type="text" name="incometax" value="Income tax" disabled style="background-color:#C6D6D3; top:220px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_incometax" value="" style="background-color:white; top:130px; left:300px;">
		     	<input class="text_subsubtitle" type="text" name="corporatetax" value="Corporate tax" disabled style="background-color:#C6D6D3; top:260px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_corporatetax" value="" style="background-color:white; top:165px; left:100px;">
		     	<input class="text_subsubtitle" type="text" name="capitalgains" value="Capital gains" disabled style="background-color:#C6D6D3; top:300px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_capitalgains" value="" style="background-color:white; top:205px; left:-120px;">
		     	<input class="text_subsubtitle" type="text" name="salestax" value="Sales tax" disabled style="background-color:#C6D6D3; top:340px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_salestax" value="" style="background-color:white; top:210px; left:900px;">
		     	<input class="text_subsubtitle" type="text" name="propertytax" value="Property tax" disabled style="background-color:#C6D6D3; top:380px; margin-left:60%; width:200px">
		     	<input class="text_input" type="text" name="input_propertytax" value="" style="background-color:white; top:250px; left:700px;">
		     	
		     	<input class="text_title" type="text" name="*" value="*All taxes are based on a year" disabled style="background-color:#C6D6D3; margin-left:60%; top:425px;font-size: 13px;">
		     	
		     	<input class="text_title" type="text" name="busaverage" value="Business average managment costs*" disabled style="background-color:#C6D6D3; top:320px;">
		     	<input class="text_subsubtitle" type="text" name="star" value="Start*" disabled style="background-color:#C6D6D3; top:355px; width:200px">
		     	<input class="text_input" type="text" name="input_payroll" value="" style="background-color:white; top:225px; left:-290px;">
		     	<input class="text_subsubtitle" type="text" name="furniture" value="*(Furniture, supplies, insurance...)" disabled style="background-color:#C6D6D3; top:390px; width:260px">
		     	<input class="text_subsubtitle" type="text" name="maintenance" value="Maintenance" disabled style="background-color:#C6D6D3; top:435px; width:200px">
		     	<input class="text_input" type="text" name="input_maintenance" value="" style="background-color:white; top:300px; left:-460px;">
		     	
		     	<input class="text_title" type="text" name="**" value="*Other specific expenses are not included" disabled style="background-color:#C6D6D3; top:480px;font-size: 13px;">
		     	
		     	<input class="text_title" type="text" name="budget" value="Budget" disabled style="background-color:#C6D6D3; margin-left:30%; top:570px;font-size: 25px;">
		     	<input class="text_input" type="text" name="input_budget" value="" style="background-color:white; top:440px; left:-213px; height:40px; font-size:20px">
		     	<input class="text_title" type="text" name="averageearnings" value="Average earnings" disabled style="background-color:#C6D6D3; margin-left:30%; top:640px;font-size: 25px;">
		     	<input class="text_input" type="text" name="input_averageearnings" value="" style="background-color:white; top:470px; left:635px; height:40px; font-size:20px">
		     	
		     	<input class="text_title" type="text" name="result" value="Result" disabled style="background-color:#C6D6D3; top:720px; margin-left:30%;font-size: 25px; font-weight: bold;">
		     	<input class="text_input" type="text" name="input_result" value="" style="background-color:white; top:545px; left:34%; height:40px; font-size:20px">
		     	
		     	</div>
		     </form>
		</div>	
	</body>
</html>