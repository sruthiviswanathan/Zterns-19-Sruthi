<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
    $(function() {
    	  $('#applyforjob').submit(function(event) {
    	    event.preventDefault();

    	    var formEl = $(this);
    	    var submitButton = $('input[type=submit]', formEl);

    	    $.ajax({
    	      type: 'POST',
    	      url: formEl.prop('action'),
    	      accept: {
    	        javascript: 'application/javascript'
    	      },
    	      data: formEl.serialize(),
    	      beforeSend: function() {
    	        submitButton.prop('disabled', 'disabled');
    	      }
    	    }).done(function(data) {
    	      submitButton.prop('disabled', false);
    	    });
    	  });
    	});
    </script>


</head>
<body>
<input type="text" name="name"/>
<a href="./Pages/register.jsp">REGISTER</a>
<a href="./Pages/graph.jsp">GRAPH</a>
<br>
<a href="StudentServlet">DISPLAY USERS</a>
<form id="applyforjob" action="GetUserServlet" method="POST" accept-charset="UTF-8">
  <input type="hidden" name="utf8" value="✓">
  <input name="email" type="email">
  <input name="name" type="text">
  <input name="location" type="text">
  <input value="Submit" type="submit">
</form>
</body>
</html>