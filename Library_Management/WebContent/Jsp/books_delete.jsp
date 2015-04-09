<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="books_delete.title"></s:text></title>
<link rel="stylesheet" href="main1.css">
<link rel="stylesheet" href="maven-theme.css">
<link rel="stylesheet" href="table.css">
</head>
<body style="background-image: url('Images/background-image.gif');">
<h1 align="center" style="border-color: red"><s:text name="project.name"></s:text></h1>
<table width="100%">
<tr>
		<td align="left"><a href='<s:url action='librarianHome' />' ><s:text name="project.home"></s:text></a></td>
		<td align="right"><a href='<s:url action='logout' />'><s:text name="project.logout"></s:text></a></td>
	</tr>
</table>
<%
	Date d = new Date();
%>
<h3 align="right"><s:text name="project.today"></s:text><%=(d.getDate() + "-" + (d.getMonth() + 1) + "-" + (1900 + d
					.getYear())).toString()%></h3>
<h4 align="center"><s:text name="books_delete.title"></s:text></h4>
<s:form action="DeleteBookAction">
	<table align="center">
		<tr><td><s:textfield name="book_id" key="books_delete.bookid" /></td></tr>
		<tr><td><s:textfield name="book_name" key="books_delete.bookname" /></td></tr>
		<tr><td align="left"><s:reset theme="simple" name="reset" key="project.reset"></s:reset></td>
		<td align="right"><s:submit  theme="simple" name="delete" key="books_delete.table.button.delete" /></td>
	  </tr>		
	</table>	
</s:form>
</body>
</html>