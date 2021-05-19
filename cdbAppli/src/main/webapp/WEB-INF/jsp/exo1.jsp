<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@   taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Page</title>
</head>
     <body>
    <p>
    
    <c:forEach var = "item" items = "${listCoyote }">
    	<c:out value = "${item }"/>
    </c:forEach>
    
   	
  
   
    	${message}<br />
       computer ${listCoyote.get(0) }<br />
        ${listCoyote.get(1) }<br />
    </p>
    </body>
</html>