<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<fmt:setLocale value="${LOCALE}" scope="session"/>
<fmt:setBundle basename="labels"/>
<c:if test="${empty USER_NAME}"><c:redirect url="/signin"/></c:if>
<c:set var="CURRENT_PAGE" value="/controller?command=LOAD-USER&USER_NAME=${FETCHED_USER.userName}" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>Update user page</title>
	</head>
	<body>
		<div class="d-flex flex-row justify-content-end">
			<c:import url="/view/header.jsp"/>
		</div>
		<div class="jumbotron" style="background-color: #5a78a1">
			<div class="d-flex flex-row justify-content-start">
				<div class="col-1">
					<img class="mb-4" src="view/img/logo.png" alt="logo" width="90" height="90">
				</div>
				<div class="col-8">
					<h2 class="display-3"><fmt:message key="label.listPage.updateUser"/></h2>
				</div>
				<div class="col-3">
					<div class="card">
						<div class="card-header">
							<h4><fmt:message key="label.listPage.welcome"/>, ${USER_NAME}</h4>
						</div>
						<div class="card-body">
							<div class="d-flex flex-row justify-content-center">
									<!--  SIGNOUT       -->
								<form action="${pageContext.request.contextPath}/controller" method="GET">
									<input type="hidden" name="command" value="SIGNOUT"/>
									<input type="submit" value="<fmt:message key="label.listPage.signOut"/>" class="btn btn-sm btn-outline-primary"/>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="d-flex flex-row justify-content-center">
				<div class="col-4 text-left">
					<form action="${pageContext.request.contextPath}/controller" method="POST">
						<input type="hidden" name="command" value="UPDATE-USER"/>
						<input type="hidden" name="USER_ID" value="${FETCHED_USER.id}"/>
						<div class="form-group">
							<label for="USER_NAME"><fmt:message key="label.signinPage.userName"/></label>
							<input type="text" class="form-control" id="USER_NAME" name="USER_NAME" onkeyup="checkUserNameValidity()" value="${FETCHED_USER.userName}" readonly="readonly" required/>
						</div>
						<div class="form-group">
							<label for="OLD_PASSWORD"><fmt:message key="label.updateUserPage.oldPassword"/></label>
							<input type="password" class="form-control" id="OLD_PASSWORD" name="OLD_PASSWORD" onkeyup='checkEquality(); checkPasswordValidity();' value="${FETCHED_USER.password}" readonly="readonly" required/>
						</div>
						<div class="form-group">
							<label for="NEW_PASSWORD"><fmt:message key="label.updateUserPage.newPassword"/></label>
							<input type="password" class="form-control" id="NEW_PASSWORD" name="NEW_PASSWORD" onkeyup="checkNewPassword()" placeholder="<fmt:message key="label.updateUserPage.newPassword"/>" required/>
							<p><span id='newPasswordMessage'></span></p>
							<p><span id='matchingOldPasswordMessage'></span></p>
						</div>
						<div class="form-group">
							<label for="CONFIRMED_NEW_PASSWORD"><fmt:message key="label.updateUserPage.confirmNewPassword"/></label>
							<input type="password" class="form-control" id="CONFIRMED_NEW_PASSWORD" name="CONFIRMED_NEW_PASSWORD" onkeyup="checkConfirmedPassword(); checkEquality()" placeholder="<fmt:message key="label.updateUserPage.confirmNewPassword"/>" required/>
							<p><span id='confirmedNewPasswordMessage'></span></p>
						</div>
						<p style="color: red">${SERVLET_MESSAGE}</p>
						<div class="form-group">
							<input type="submit" value="<fmt:message key="label.listPage.update"/>" class="btn btn-md btn-primary btn-block"/>
						</div>
					</form>
				</div>
			</div>
			<div class="d-flex flex-row justify-content-center">
				<p>
					<a href="${pageContext.request.contextPath}/controller"><fmt:message key="label.addStudentPage.backToList"/></a>
				</p>
			</div>
		</div>
	</body>
</html>

<script>
	function checkNewPassword() {
		var regex = new RegExp("^[a-zA-Z_!@#%0-9]{8,16}$");
		var rex = regex.test(document.getElementById('NEW_PASSWORD').value);
		if (!rex) {
			document.getElementById('newPasswordMessage').style.color = 'red';
			document.getElementById('newPasswordMessage').innerHTML = '<fmt:message key="label.invalidPassword"/>';
		} else {
			document.getElementById('newPasswordMessage').style.color = 'green';
			document.getElementById('newPasswordMessage').innerHTML = '<fmt:message key="label.validPassword"/>';
		}
	}

	function checkEquality() {
		if (document.getElementById('OLD_PASSWORD').value === document.getElementById('NEW_PASSWORD').value) {
			document.getElementById('matchingOldPasswordMessage').style.color = 'red';
			document.getElementById('matchingOldPasswordMessage').innerHTML = '<fmt:message key="label.updateUserPage.samePassword"/>';
		} else {
			document.getElementById('matchingOldPasswordMessage').innerHTML = '';
		}
	}
	function checkConfirmedPassword() {
		if (document.getElementById('NEW_PASSWORD').value ===
				document.getElementById('CONFIRMED_NEW_PASSWORD').value) {
			document.getElementById('confirmedNewPasswordMessage').style.color = 'green';
			document.getElementById('confirmedNewPasswordMessage').innerHTML = '<fmt:message key="label.passwordsMatching"/>';
		} else {
			document.getElementById('confirmedNewPasswordMessage').style.color = 'red';
			document.getElementById('confirmedNewPasswordMessage').innerHTML = '<fmt:message key="label.passwordsMismatch"/>';
		}
	}
</script>