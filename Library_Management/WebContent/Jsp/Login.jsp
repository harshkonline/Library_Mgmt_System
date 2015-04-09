<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib prefix="s" uri="/struts-tags" %>
	

<%@page import="java.util.Date"%>
<html>
<head>
<link rel="stylesheet"  href="main1.css">
<link rel="stylesheet" href="maven-theme.css">
<link rel="stylesheet"  href="table.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="login.title"></s:text></title>
</head>
<body style="background-image: url('Images/background-image.gif');">
<h1 align="center" style="border-color: red"><s:text name="project.name"></s:text></h1>
<%Date d=new Date();%>
<h3 align="right"><s:text name="project.today"></s:text><%=(d.getDate()+"-"+(d.getMonth()+1)+"-"+(1900+d.getYear())).toString() %></h3>
<h3 align="center"><s:text name="login.title"></s:text></h3>
<s:form action="LoginAction" >
	<table align="center">
		<tr><td><s:textfield key="login.username" name="user_name"></s:textfield></td></tr>
		<tr><td><s:password key="login.password" name="password"></s:password></td></tr>		
	</table>
	<s:submit align="center" key="login.submit"></s:submit>
</s:form>

<jsp:include page="lang_incude.jsp"></jsp:include>

</body>

</html>