<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<s:form action="login" method="post" namespace="/">
		<s:textfield name="username" label="Username"></s:textfield> <br/>
		<s:password name="password" label="Password"></s:password>
		<s:submit value="Login"></s:submit>
	</s:form>
	
	<br/><br/>
	<s:form action="adduser" method="post" namespace="/" acceptcharset="UTF-8">
		<s:textfield name="username" label="Username"></s:textfield> <br/>
		<s:password name="password" label="Password"></s:password> <br/>
		<s:textfield name="fullname" label="Fullname"></s:textfield> <br/>
		<s:textfield name="gender" label="Gender" ></s:textfield> <br/>
		<s:textfield name="email" label="Email"></s:textfield> <br/>
		<s:textfield name="picurl" label="Url"></s:textfield>
		<s:submit value="Add User"></s:submit>
	</s:form>

</body>
</html>