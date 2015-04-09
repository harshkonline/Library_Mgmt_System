<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.Date"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="books_inventory.title"></s:text></title>
<link rel="stylesheet"  href="main1.css">
<link rel="stylesheet" href="maven-theme.css">
<link rel="stylesheet"  href="table.css">
</head>
<body style="background-image: url('Images/background-image.gif');">

<h1 align="center" style="border-color: red"><s:text name="project.name"></s:text></h1>
<table width="100%">
<tr>
		<td align="left"><a href='<s:url action='librarianHome' />' ><s:text name="project.home"></s:text></a></td>
		<td align="right"><a href='<s:url action='logout' />'><s:text name="project.logout"></s:text></a></td>
	</tr>
</table>
<%Date d=new Date();%>
<h3 align="right"><s:text name="project.today"></s:text><%=(d.getDate()+"-"+(d.getMonth()+1)+"-"+(1900+d.getYear())).toString() %></h3>

	<h2 align="center"><s:text name="books_inventory.title"></s:text></h2>
	<hr/>
	<br><br>
	<p align="center" style="font-size:x-large"><a href="<s:url action='AddBook'/>"><s:text name="books_inventory.addbook"></s:text></a></p>
	<br><br>
	<p align="center"><a href="<s:url action='ModifyBook'/>"><s:text name="books_inventory.modifybook"></s:text></a></p>
	<br><br>
	<p align="center"><a href="<s:url action='DeleteBook'/>"><s:text name="books_inventory.deletebook"></s:text></a></p>
	<br><br>
	<hr/>
	
</body>
</html>