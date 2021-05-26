<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>

       
        
        <form method="post" action="exo">
            <label for="nom">Nom : </label>
          
            <input type="text" name="nom" value="${ nom }">
            <input type="submit" />
        </form>
        
         <c:if test="${ !empty nom }"><p><c:out value="Bonjour, vous vous appelez ${ nom }" /></p></c:if>
         
    </body>
</html>