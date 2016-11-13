<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset='UTF-8'">
<title>CRUD With EJB, JPA And JSP</title>
<!-- Bootstrap core CSS -->
<link href="http://getbootstrap.com/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="http://getbootstrap.com/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="http://getbootstrap.com/examples/jumbotron-narrow/jumbotron-narrow.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="http://getbootstrap.com/assets/js/ie-emulation-modes-warning.js"></script>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
			<nav>
			<ul class="nav nav-pills pull-right">
				<li role="presentation" class="active"><a href="#">Home</a></li>
				<li role="presentation"><a href="#">About</a></li>
				<li role="presentation"><a href="#">Contact</a></li>
			</ul>
			</nav>
			<h3 class="text-muted">CRUD</h3>
		</div>
		<div class="jumbotron">
			<p class="lead">CRUD With EJB And JPA in JSP-Servlet</p>
		</div>

		<div class="container">
			${error }
			<a href="account?action=add" class="btn btn-primary">Add New Account</a>
			<table class="table table-hover table-bordered">
				<tr>
					<th>ID</th>
					<th>USERNAME</th>
					<th>FULL NAME</th>
					<th>EMAIL</th>
					<th>AGE</th>
					<th>OPTION</th>
				</tr>
				<c:forEach var="account" items="${listAccounts }">
					<tr>
						<td>${account.id }</td>
						<td>${account.username }</td>
						<td>${account.fullname }</td>
						<td>${account.email }</td>
						<td>${account.age }</td>
						<td>
							<a href="account?action=delete&userid=${account.id }" onclick="return confirm('Are you sure?')" class="btn btn-danger">Delete</a> 
							<a href="account?action=edit&userid=${account.id }" class="btn btn-info">Update</a>
						</td>
					</tr>
				</c:forEach>

			</table>
		</div>

		<div class="mastfoot">
            <div class="inner">
              <p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by @msoysal</a>.</p>
            </div>
          </div>

	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>