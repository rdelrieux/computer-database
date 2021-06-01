<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@   taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application -
				Computer Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${computer.id }</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value=${computer.id } id="id" name="id"/>
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName"
									value=${computer.name }>
							</div>
							<div class="form-group">
											
								
								<label for="introduced">Introduced date</label> 
								
							
								 <c:set var = "date" value = "${computer.introduced  }" />
								 <fmt:parseDate value = "${date}" var = "parsedDate" pattern = "yyyy-MM-dd" /> 
     							 <fmt:formatDate value = "${parsedDate}" var = "formDate" pattern = "yyyy-MM-dd" />
							
                                      
								<input
									type="text" onfocus="(this.type='date')"
									class="form-control" id="introduced"
									name="introduced"
									value=${formDate }>
									
									
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label>
								
								
								 <c:set var = "date" value = "${computer.discontinued  }" />
								 <fmt:parseDate value = "${date}" var = "parsedDate" pattern = "yyyy-MM-dd" /> 
     							 <fmt:formatDate value = "${parsedDate}" var = "formDate" pattern = "yyyy-MM-dd" />
							
								
								 <input
									type="text" onfocus="(this.type='date')"
									class="form-control" id="discontinued"
									name="discontinued"
									value=${formDate }>
								
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> 
								<select
									class="form-control" id="companyId" 
									name="companyId">
									
									<option value="${companyId }" >${computer.companyName }</option>
									<option value="">--</option>
									<c:forEach var="company" items="${listCompany }">
										<option value="${company.id }">${company.name }</option>
									</c:forEach>
									
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>