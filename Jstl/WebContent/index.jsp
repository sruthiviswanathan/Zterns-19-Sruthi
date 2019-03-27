<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:url value = "/index.jsp" var = "myURL">
   <c:param name = "trackingId" value = "1234"/>
   <c:param name = "reportType" value = "summary"/>
</c:url>
${myURL}
</body>
</html>