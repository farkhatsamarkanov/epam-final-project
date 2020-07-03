<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="CURRENT_PAGE" value="/signin" scope="session"/>
<fmt:setLocale value="${LOCALE}" scope="session"/>
<fmt:setBundle basename="labels"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>Sign-in page</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
			  integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	</head>
	<body class="text-center">
		<div class="d-flex flex-row justify-content-end">
			<c:import url="/view/header.jsp"/>
		</div>
		<form action="${pageContext.request.contextPath}/controller" method="POST">
			<input type="hidden" name="command" value="SIGNIN"/>
			<div class="d-flex flex-row justify-content-center">
				<div class="col-3">
					<img class="mb-4" src="view/img/logo.png" alt="logo" width="72" height="72">
					<h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label.signinPage.pageTitle"/></h1>
					<label for="USER_NAME" class="sr-only"><fmt:message key="label.signinPage.userName"/></label>
					<input type="text" name="USER_NAME" id="USER_NAME" class="form-control" placeholder="<fmt:message key="label.signinPage.userName"/>" required autofocus>
					<label for="PASSWORD" class="sr-only"><fmt:message key="label.signinPage.password"/></label>
					<input type="password" name="PASSWORD" id="PASSWORD" class="form-control" placeholder="<fmt:message key="label.signinPage.password"/>" required>
					<p style="color: red">${SERVLET_MESSAGE}</p>
					<input class="btn btn-md btn-primary btn-block" type="submit" value=" <fmt:message key="label.signinPage.signinButton"/>"/>
					<fmt:message key="label.signinPage.noAccaountMessage"/> <a href="${pageContext.request.contextPath}/signup"><fmt:message key="label.signinPage.signupButton"/></a>
					<p class="mt-5 mb-3 text-muted">&copy; 2020 Made by Farkhat Samarkanov</p>
				</div>
			</div>
		</form>
	</body>
</html>