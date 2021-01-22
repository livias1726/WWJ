<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page isErrorPage="true" %>  

<html lang="en">
	<head>
		<meta charset="ISO-8859-1">
		<title>Error</title>
	</head>
	<body>
		<h2>Sorry an error occured!</h2>
		<em><%=exception.getMessage()%></em>		
	</body>
</html>