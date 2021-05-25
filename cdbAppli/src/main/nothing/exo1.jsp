<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@   taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Page</title>
</head>
<body>
	<p>

		<c:forEach var="item" items="${listcomputer }">
			<c:out value="${item }" />
			<br />
		</c:forEach>

		<br />
		<c:out value="Bonjour !" />
		<br />

		<c:out value="${message }" />
		<br />
		<c:out value="computer" />
		<c:out value="${listcomputer.get(0) }" />
		<br />
		<c:out value="${listcomputer.get(1) }" />
		<br />
		<c:out value="${ listcomputer.get(0).company.name  }">Valeur par défaut</c:out>
		<br />
		<c:if test="${ 50 > 10 }" var="variable">
			<c:out value="${variable }" />
			<c:out value="C'est vrai !" />
		</c:if>
		<br />

		<c:choose>
			<c:when test="${ !variable }">Du texte1</c:when>
			<c:when test="${ variable }">Du texte2</c:when>
			<c:when test="${ encoreUneAutreVariable }">Du texte3</c:when>
			<c:otherwise>
				<c:out value="Bonjour !" />
			</c:otherwise>
		</c:choose>
		<br />
		<c:forEach var="i" begin="0" end="10" step="2">
			
				Un message n°
				<c:out value="${ i }" />
				!
			
		</c:forEach>
		<c:forEach items="${ titres }" var="titre" varStatus="status">
			
				N°
				<c:out value="${ status.count }" />
				:
				<c:out value="${ titre }" />
				!
			
		</c:forEach>
	</p>





















</body>
</html>