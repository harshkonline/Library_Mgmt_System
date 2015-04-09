<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@page import="java.util.Date"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="project.name"></s:text> </title>
<link rel="stylesheet"  href="main1.css">
<link rel="stylesheet" href="maven-theme.css">
<link rel="stylesheet"  href="table.css">
</head>
<body style="background-image: url('Images/background-image.gif');">
<h1 align="center" style="border-color: red"><s:text name="project.name"></s:text> </h1>
<table width="100%">
<tr>
		<td align="left"><a href='<s:url action='librarianHome' />' ><s:text name="project.home"></s:text></a></td>
		<td align="right"><a href='<s:url action='logout' />'><s:text name="project.logout"></s:text></a></td>
	</tr>
</table>
<%Date d=new Date();%>
<h3 align="right">Today:<%=(d.getDate()+"-"+(d.getMonth()+1)+"-"+(1900+d.getYear())).toString() %></h3>
<br></br>
<h3 align="center"><s:text name="books_issue.issuebook"></s:text></h3><hr/><br/><br/>
<s:form action="issueAction">
	<table align="center">
	<tr><td><s:select name="book_id" headerValue="--Please Select--" label="Select ID" list="book_id_list"></s:select></td></tr>
	<tr><td><s:submit name="button" value="Issue Book"></s:submit></td></tr>
	</table>
</s:form>
</body>
</html>