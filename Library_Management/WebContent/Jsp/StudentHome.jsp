<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="StudentHome.title"></s:text></title>
<link rel="stylesheet"  href="main1.css">
<link rel="stylesheet" href="maven-theme.css">
<link rel="stylesheet"  href="table.css">
</head>
<body style="background-image: url('Images/background-image.gif');">

<h1 align="center" style="border-color: red"><s:text name="project.name"></s:text></h1>
<p align="right"><a href='<s:url action='logout' />'><s:text name="project.logout"></s:text></a></p>
<%Date d=new Date();%>
<h3 align="right"><s:text name="project.today"></s:text><%=(d.getDate()+"-"+(d.getMonth()+1)+"-"+(1900+d.getYear())).toString() %></h3><br/>
<h2 align="center"><s:text name="StudentHome.title"></s:text></h2><BR/>	
	<s:text name="project.welcome"></s:text> <s:property value="#session.username"/> <br/><br/>
	<hr/><br/><br/>
	<p align="center"><a href='<s:url action='searchBook' />'><s:text name="StudentHome.searchbook"></s:text></a></p>
</body>
</html>