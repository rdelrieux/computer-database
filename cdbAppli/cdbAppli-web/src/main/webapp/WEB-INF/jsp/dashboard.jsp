<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<a class="navbar-brand" href="reset"> <fmt:message key="label.home" /> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${ session.page.nombreElementRequet }" />
				<fmt:message key="label.computersFound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="search" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder=<fmt:message key="label.searchName" /> /> <input
							type="submit" id="searchsubmit" value=<fmt:message key="label.filter" />
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><fmt:message key="label.add" /></a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><fmt:message key="label.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="delete#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>

						<th><a href="search?orderBy=computer.name&order=ASC"> <i
								class="fa fa-fw  fa-angle-up  fa-clickable"></i>
						</a> <fmt:message key="label.computerName" /> <a href="search?orderBy=computer.name&order=DESC">
								<i class="fa fa-fw  fa-angle-down  fa-clickable"></i>
						</a></th>

						<th><a href="search?orderBy=introduced&order=ASC"> <i
								class="fa fa-fw  fa-angle-up  fa-clickable"></i>
						</a> <fmt:message key="label.introducedDate" /> <a href="search?orderBy=introduced&order=DESC">
								<i class="fa fa-fw  fa-angle-down  fa-clickable"></i>
						</a></th>

						<th><a href="search?orderBy=discontinued&order=ASC"> <i
								class="fa fa-fw  fa-angle-ud  fa-clickable"></i>
						</a> <fmt:message key="label.discontinuedDate" /> <a
							href="search?orderBy=discontinued&order=DESC"> <i
								class="fa fa-fw  fa-angle-down  fa-clickable"></i>
						</a></th>

						<th><a href="search?orderBy=company.name&order=ASC"> <i
								class="fa fa-fw  fa-angle-up  fa-clickable"></i>
						</a> <fmt:message key="label.companyName" /> <a href="search?orderBy=company.name&order=DESC"> <i
								class="fa fa-fw  fa-angle-down  fa-clickable"></i>
						</a></th>

					</tr>

				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${listcomputer }">


						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value=${computer.id }>${computer.id }</td>
							<td><a href="editComputer?id=${computer.id }" onclick="">${computer.name }</a>
							</td>
							<td>${computer.introduced }</td>
							<td>${computer.discontinued }</td>
							<td>${computer.companyName }</td>

						</tr>
					</c:forEach>


				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">


		   <div class="container text-center">
			
			<div class="btn-group btn-group-sm pull-left" role="group">
		
		        <a href="?lang=en"><fmt:message key="label.lang.en" /></a></br>
		        <a href="?lang=fr"><fmt:message key="label.lang.fr" /></a>
		    
			</div>

		<ul class="pagination">
			<li><a href="search?page=1" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>

			<li><c:if test="${ session.page.numPage -2 > 0 }" var="variable">
					<a href="search?page=${session.page.numPage -2}">${session.page.numPage -2}
					</a>
				</c:if></li>


			<li><c:if test="${ session.page.numPage -1 > 0 }" var="variable">
					<a href="search?page=${session.page.numPage -1}">${session.page.numPage -1}
					</a>
				</c:if></li>


			<li><a href="search?page=${session.page.numPage}"> .. </a></li>


			<li><c:if
					test="${ session.page.numPage +1 <= session.page.getNombrePageMax() }"
					var="variable">
					<a href="search?page=${session.page.numPage +1}">${session.page.numPage +1}
					</a>
				</c:if></li>


			<li><c:if
					test="${ session.page.numPage +2 <= session.page.getNombrePageMax() }"
					var="variable">
					<a href="search?page=${session.page.numPage +2}">${session.page.numPage +2}
					</a>
				</c:if></li>

			<li><a href="search?page=${ session.page.getNombrePageMax() }"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<form id="nombreElementPage" action="search#" method="GET">
				<button type="submit" class="btn btn-default"
					name="nombreElementPage" value="10">10</button>
				<button type="submit" class="btn btn-default"
					name="nombreElementPage" value="50">50</button>
				<button type="submit" class="btn btn-default"
					name="nombreElementPage" value="100">100</button>
			</form>
		</div>
		</div>
	</footer>



	<script src="./resources/js/jquery.min.js"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
	<script src="./resources/js/dashboard.js"></script>


</body>
</html>