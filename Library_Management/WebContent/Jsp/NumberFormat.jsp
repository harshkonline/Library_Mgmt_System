<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="registerError.title"></s:text> </title>
</head>
<body>
<table width="100%">
<tr>
		<td align="right"><a href='<s:url action='logout' />'><s:text name="project.logout"></s:text></a></td>
	</tr>
</table>
<h1 align="center">
<s:action name="studentHome" executeResult="true"></s:action>
<s:text name="numberFormat.msg"></s:text>
</h1> 
</body>
</html>