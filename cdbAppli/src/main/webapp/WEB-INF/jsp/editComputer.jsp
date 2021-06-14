<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@   taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"><fmt:message key="label.home" /> </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${computer.id }</div>
					<h1><fmt:message key="label.editComputer" /></h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value=${computer.id } id="id" name="id"/>
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="name"
									name="name"
									value=${computer.name }>
							</div>
							<div class="form-group">
											
								
								<label for="introduced"><fmt:message key="label.introducedDate" /></label> 
								
							
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
								<label for="discontinued"><fmt:message key="label.discontinuedDate" /></label>
								
								
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
								<label for="companyId"><fmt:message key="label.companyName" /></label> 
								<select
									class="form-control" id="companyId" 
									name="companyId">
									
									<option value="${computer.companyId  }" >${companyName }</option>
									<option value="">--</option>
									<c:forEach var="company" items="${listCompany }">
										<option value="${company.id }">${company.name }</option>
									</c:forEach>
									
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value=<fmt:message key="label.edit" /> class="btn btn-primary">
							<fmt:message key="label.or" /> <a href="dashboard" class="btn btn-default"><fmt:message key="label.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>