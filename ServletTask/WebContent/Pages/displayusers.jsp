<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Our Users</title>
</head>
<body>
<h1>welcome</h1>

<table border="1px">
<%@ page import="com.zilker.servlet.bean.Student"%>  
<%@page import="java.util.ArrayList" %>

<%-- <%  
ArrayList<Student> list = new ArrayList<Student>();
list = (ArrayList<Student>) request.getAttribute("studentList");
%> --%>
<tr>
<th>NAME</th>
<th>EMAIL</th>
<th>COUNTRY</th>
</tr>
<%-- <c:out value = "${'<tag> , &'}"/> --%>
<%-- <%
for(Student stud : list) {
%>	
	<tr>
    <td><%= stud.getName() %></td>
    <td><%= stud.getEmail() %></td>
    <td><%= stud.getCountry() %></td>
	</tr>
<%  
}
%> --%>
<c:forEach var="st" items="${studentList}">
<tr>
<td> <c:out value="${st.getName()}"/></td>
<td> <c:out value="${st.getEmail()}"/></td>
<td> <c:out value="${st.getCountry()}"/></td>
</tr>
</c:forEach>
</table>


</body>
</html>