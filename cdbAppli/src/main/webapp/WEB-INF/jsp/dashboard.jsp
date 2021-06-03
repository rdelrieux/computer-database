<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@   taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard?search=#"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${ page.nombreElementRequet }" />
				Computers found
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
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

						<th><a href="?orderBy=computer.name,up"> <i
								class="fa fa-fw  fa-angle-up  fa-clickable"></i>
						</a> Computer name <a href="?orderBy=computer.name,dowm"> <i
								class="fa fa-fw  fa-angle-down  fa-clickable"></i>
						</a></th>

						<th><a href="?orderBy=introduced,up"> <i
								class="fa fa-fw  fa-angle-up  fa-clickable"></i>
						</a> Introduced date <a href="?orderBy=introduced,down"> <i
								class="fa fa-fw  fa-angle-down  fa-clickable"></i>
						</a></th>
						
						<th><a href="?orderBy=discontinued,up"> 
						<i class="fa fa-fw  fa-angle-ud  fa-clickable"></i>
						</a> Discontinued date <a href="?orderBy=discontinued,down"> <i
								class="fa fa-fw  fa-angle-down  fa-clickable"></i>
						</a></th>

						<th><a href="?orderBy=company.name,up"> <i
								class="fa fa-fw  fa-angle-up  fa-clickable"></i>
						</a> Company <a href="?orderBy=company.name,down"> <i
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
			<ul class="pagination">
				<li><a href="?page=1" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>

				<li><c:if test="${ page.numPage -2 > 0 }" var="variable">
						<a href="?page=${page.numPage -2}">${page.numPage -2} </a>
					</c:if></li>


				<li><c:if test="${ page.numPage -1 > 0 }" var="variable">
						<a href="?page=${page.numPage -1}">${page.numPage -1} </a>
					</c:if></li>


				<li><a href="?page=${page.numPage}"> .. </a></li>


				<li><c:if
						test="${ page.numPage +1 <= page.getNombrePageMax() }"
						var="variable">
						<a href="?page=${page.numPage +1}">${page.numPage +1} </a>
					</c:if></li>


				<li><c:if
						test="${ page.numPage +2 <= page.getNombrePageMax() }"
						var="variable">
						<a href="?page=${page.numPage +2}">${page.numPage +2} </a>
					</c:if></li>

				<li><a href="?page=${ page.getNombrePageMax() }"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<form id="nombreElementPage" action="#" method="GET">
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

	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>


</body>
</html>