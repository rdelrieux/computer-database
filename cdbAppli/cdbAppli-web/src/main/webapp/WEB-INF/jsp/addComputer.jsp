<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title> <fmt:message key="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="./resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="./resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <fmt:message
					key="label.home" />
			</a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<fmt:message key="label.addComputer" />
					</h1>
					<form action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><fmt:message
										key="label.computerName" /></label> <input type="text"
									class="form-control" id="name" name="name"
									placeholder="<fmt:message key="label.computerName" />"
									required="required" value=${computer.name }>
								
								<span class="error">
								<c:out value="${errors['name']}" />
								</span>
								
							</div>
							<div class="form-group">
								<label for="introduced"><fmt:message
										key="label.introducedDate" /></label> <input type="date"
									class="form-control" id="introduced" name="introduced"
									placeholder="Introduced date" value=${computer.introduced }>
									<span class="error"><c:out
										value="${errors['discontinued']}" /></span> 
							</div>
							<div class="form-group">
								<label for="discontinued"><fmt:message
										key="label.discontinuedDate" /></label> <input type="date"
									class="form-control" id="discontinued" name="discontinued"
									placeholder="Discontinued date" value=${computer.discontinued }>
								<span class="error"><c:out
										value="${errors['discontinued']}" /></span> 

							</div>
							<div class="form-group">
								<label for="companyId"><fmt:message
										key="label.companyName" /></label> <select class="form-control"
									id="companyId" name="companyId">
									<option value="${computer.companyId }">${companyName }</option>
									<option value="">--</option>
									<c:forEach var="company" items="${listCompany }">
										<option value="${company.id }">${company.name }</option>
									</c:forEach>
								</select> <span class="error"><c:out
										value="${errors['companyId']}" /></span>

							</div>
						</fieldset>

						<div class="alert alert-danger page-alert" id="alert-message"
							style="display: none;">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								
							<span aria-hidden="true">×</span>
							</button>
							<strong>Action add Canceled ! </strong> <label id=erroradd></label>
						
						
						
						</div>


						<div class="actions pull-right">
							<input type="submit" value=<fmt:message key="label.add" />
								class="btn btn-primary">
							<fmt:message key="label.or" />
							<a href="addComputer/cancel" class="btn btn-default"><fmt:message
									key="label.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="./resources/js/jquery.min.js"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
	<!--  script src="./js/addComputer.js"></script-->
</body>
</html>