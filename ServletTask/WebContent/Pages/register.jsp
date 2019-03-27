<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register here</title>
</head>
<body>
<%@ page import="com.zilker.servlet.config.Config"%>  
	<form action="${Config.BASE_PATH}StudentServlet" method="post">
  
Name*:<input type="text" name="userName" required/><br/><br/>  
Password*:<input type="password" name="userPass" required/><br/><br/>  
Email Id*:<input type="text" name="userEmail" required/><br/><br/>  
Country:  
<select name="userCountry">  
<option>India</option>  
<option>Pakistan</option>  
<option>other</option>  
</select>  
  
<br/><br/>  
<input type="submit" value="register"/>  
  
</form>  
</body>
</html>