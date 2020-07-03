<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<fmt:setLocale value="${LOCALE}" scope="session"/>
<fmt:setBundle basename="labels"/>
<c:set var="CURRENT_PAGE" value="/signup" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign-up page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
	<body>
		<div class="d-flex flex-row justify-content-end">
			<c:import url="/view/header.jsp"/>
		</div>
		<div class="d-flex flex-row justify-content-center">
			<div class="col-8 text-center">
				<img class="mb-4" src="view/img/logo.png" alt="logo" width="72" height="72">
				<h2><fmt:message key="label.signupPage.pageTitle"/></h2>
				<p class="lead">
					Welcome, please enter your user name and password...
					User name should be 3-45 characters long and contain only a-z, A-Z, 0-9 and _.
					Password should be 8-16 characters long and contain only a-z, A-Z, 0-9 or _!@#%.
				</p>
			</div>
		</div>
        <form action="${pageContext.request.contextPath}/controller" method="POST">
		<div class="d-flex flex-row justify-content-center">
			<div class="col-4 text-left">
                <input type="hidden" name="command" value="SIGNUP"/>
                <div class="form-group">
                    <label for="USER_NAME"><fmt:message key="label.signinPage.userName"/></label>
                    <input type="text" class="form-control" id="USER_NAME" name="USER_NAME" onkeyup="checkUserNameValidity()" placeholder="<fmt:message key="label.signinPage.userName"/>" required/>
                    <p><span id='invalidUserNameMessage'></span></p>
                </div>
                <div class="form-group">
                    <label for="PASSWORD"><fmt:message key="label.signinPage.password"/></label>
                    <input type="password" class="form-control" id="PASSWORD" name="PASSWORD" onkeyup='checkEquality(); checkPasswordValidity();' placeholder="<fmt:message key="label.signinPage.password"/>" required/>
                    <p><span id='invalidPasswordMessage'></span></p>
                </div>
                <div class="form-group">
                    <label for="CONFIRMED_PASSWORD"><fmt:message key="label.signupPage.confirmPassword"/></label>
                    <input type="password" class="form-control" id="CONFIRMED_PASSWORD" name="CONFIRMED_PASSWORD" onkeyup='checkEquality();' placeholder="<fmt:message key="label.signupPage.confirmPassword"/>" required/>
                    <p><span id='passwordsNotMatchingMessage'></span></p>
                </div>
                <p style="color: red">${SERVLET_MESSAGE}</p>
			</div>
		</div>
            <div class="d-flex flex-row justify-content-center">
                <div class="col-2 text-center">
                    <input type="submit" value="<fmt:message key="label.signinPage.signupButton"/>" class="btn btn-md btn-primary btn-block"/>
                    <a href="${pageContext.request.contextPath}/signin"><fmt:message key="label.signupPage.backToLoginPage"/></a>
                </div>
            </div>
        </form>
        <br>
        <div class="d-flex flex-row justify-content-center">
            <p class="mt-5 mb-3 text-muted">&copy; 2020 Made by Farkhat Samarkanov</p>
        </div>
	</body>
</html>

<script>
    function checkPasswordValidity() {
        var regex = new RegExp("^[a-zA-Z_!@#%0-9]{8,16}$");
        var rex = regex.test(document.getElementById('PASSWORD').value);
        if (!rex) {
            document.getElementById('invalidPasswordMessage').style.color = 'red';
            document.getElementById('invalidPasswordMessage').innerHTML = '<fmt:message key="label.invalidPassword"/>';
        } else {
            document.getElementById('invalidPasswordMessage').style.color = 'green';
            document.getElementById('invalidPasswordMessage').innerHTML = '<fmt:message key="label.validPassword"/>';
        }
    }

    function checkEquality() {
        if (document.getElementById('PASSWORD').value ===
            document.getElementById('CONFIRMED_PASSWORD').value) {
            document.getElementById('passwordsNotMatchingMessage').style.color = 'green';
            document.getElementById('passwordsNotMatchingMessage').innerHTML = '<fmt:message key="label.passwordsMatching"/>';
        } else {
            document.getElementById('passwordsNotMatchingMessage').style.color = 'red';
            document.getElementById('passwordsNotMatchingMessage').innerHTML = '<fmt:message key="label.passwordsMismatch"/>';
        }
    }
    function checkUserNameValidity() {
        var regex = new RegExp("^[a-zA-Z_0-9]{3,45}$");
        var rex = regex.test(document.getElementById('USER_NAME').value);
        if (!rex) {
            document.getElementById('invalidUserNameMessage').style.color = 'red';
            document.getElementById('invalidUserNameMessage').innerHTML = '<fmt:message key="label.invalidUserName"/>';
        } else {
            document.getElementById('invalidUserNameMessage').style.color = 'green';
            document.getElementById('invalidUserNameMessage').innerHTML = '<fmt:message key="label.validUserName"/>';
        }
    }
</script>