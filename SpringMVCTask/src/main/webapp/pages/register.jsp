<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<form:form action="registerProcess" modelAttribute="user" method="post">
			<table style="with: 50%">
				
				<tr>
					<td>UserName</td>
					<td><form:input type="text" name="name" path="name" required="true"/></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input type="email" name="email" path="email" required="true"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:input type="password" name="password" path="password" required="true" /></td>
				</tr>
				<tr>
					<td>Age</td>
					<td><form:input type="number" name="age" path="age" /></td>
				</tr>
				<tr>
					<td>Contact No</td>
					<td><form:input type="number" name="phoneNumber" path="phoneNumber" required="true" /></td>
				</tr></table>
			     <form:button type="submit">SUBMIT </form:button>
			      </form:form>
</body>
</html>