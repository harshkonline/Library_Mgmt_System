<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>i18n Demo</title>
</head>
<body>
	<center>
	<font style="">
	<hr style="color: #67030D;border-width: 2px" width="100%"/>
			<s:form method="post">
				<b><s:text name="lang_incude.changelanguage"></s:text></b>
					<s:url id="url" action="Login">
							<s:param name="request_locale">en</s:param>
					</s:url>
					<s:a href="%{url}">English</s:a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<s:url id="url1" action="Login">
							<s:param name="request_locale">es</s:param>
					</s:url>
					<s:a href="%{url1}">Spanish</s:a>
					&nbsp;&nbsp;|&nbsp;&nbsp;
					<s:url id="url2" action="Login">
							<s:param name="request_locale">fr</s:param>
					</s:url>
					<s:a href="%{url2}">French</s:a>
			</s:form>
	</font>
	</center>
</body>
</html>