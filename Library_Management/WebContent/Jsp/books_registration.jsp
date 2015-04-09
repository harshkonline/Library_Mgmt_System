<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Date"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="project.name"></s:text></title>
<link rel="stylesheet" href="main1.css">
<link rel="stylesheet" href="maven-theme.css">
<link rel="stylesheet" href="table.css">
</head>
<body style="background-image: url('Images/background-image.gif');">
<h1 align="center" style="border-color: red"><s:text name="project.name"></s:text></h1>
<table width="100%">
<tr>
		<td align="left"><a href='<s:url action='studentHome' />' ><s:text name="project.home"></s:text></a></td>
		<td align="right"><a href='<s:url action='logout' />'><s:text name="project.logout"></s:text></a></td>
	</tr>
</table>
<%Date d=new Date();%>
<h3 align="right"><s:text name="project.today"></s:text><%=(d.getDate()+"-"+(d.getMonth()+1)+"-"+(1900+d.getYear())).toString() %></h3><br/>

<s:form action="Registration">
	
	<s:radio list="#{'1':'Bookname ','2':'Book Id ','3':'Author'}"
		name="Search_by" key="books_transaction.searchby"></s:radio>
	<s:textfield name="registration_id " key="books_registration.regid" />
	<s:textfield name="book_name" key="books_transaction.bookname" />
	<s:textfield name="book_id" key="books_transaction.bookid" />
	<s:textfield name="author1" key="books_transaction.author" />
	<s:textfield name="user_id" key="books_request.userid" />
	<s:textfield name="registration_date "
		key="books_registration.date" />
	<s:reset></s:reset>
	<s:submit name="search" key="books_transaction.search" />

</s:form>
</body>
</html>