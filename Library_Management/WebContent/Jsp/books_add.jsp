<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="books_add.title"></s:text></title>
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
<s:form action="BookAddedAction">
	<table bgcolor=#f5f5f5 align="center">
		<tr>
			<td><s:textfield key="books_add.table.bookid" name="book_id" readonly="true"></s:textfield></td>			
		</tr>
		<tr>
			<td><s:textfield key="books_add.table.bookname" name="book_name" required="true"></s:textfield></td>
		</tr>
		<tr>
			<td><s:textfield key="books_add.table.author1" name="author1" required="true"></s:textfield></td>
		</tr>
		<tr>
			<td><s:textfield key="books_add.table.author2" name="author2"></s:textfield></td>		
		</tr>
		<tr>
			<td><s:textfield key="books_add.table.Publication" name="publisher" required="true"></s:textfield></td>			
		</tr>
		<tr>
			<td><s:textfield key="books_add.table.yop" name="year_of_publication" required="true"></s:textfield></td>			
		</tr>
		<tr>
			<td align="center"><s:submit theme="simple" key="books_add.table.button.add"></s:submit></td>
		</tr>
	</table>
</s:form>
</body>
</html>