<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="logic.application.SessionFacade"%>
		 
<%
SessionFacade.getSession().setID(null);
SessionFacade.getSession().setCurrUserType(null);
session.invalidate();
response.sendRedirect("http://localhost:8080/WorldWideJob/index.jsp");
%>