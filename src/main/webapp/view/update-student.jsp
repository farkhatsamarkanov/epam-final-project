<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<fmt:setLocale value="${LOCALE}" scope="session"/>
<fmt:setBundle basename="labels"/>
<c:if test="${empty USER_NAME}"><c:redirect url="/signin"/></c:if>
<c:set var="CURRENT_PAGE" value="/controller?command=LOAD-STUDENT&STUDENT_ID=${FETCHED_STUDENT.id}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Update student page</title>
    <link rel="stylesheet" href="dist/css/bootstrap.css">
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
                <h2 class="display-3"><fmt:message key="label.updateStudentPage.pageTitle"/></h2>
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
                    <input type="hidden" name="command" value="UPDATE-STUDENT"/>
                    <input type="hidden" name="STUDENT_ID" value="${FETCHED_STUDENT.id}"/>
                    <div class="form-group">
                        <label for="FIRST_NAME"><fmt:message key="label.addStudentPage.firstName"/></label>
                        <input type="text" class="form-control" id="FIRST_NAME" name="FIRST_NAME" value="${FETCHED_STUDENT.firstName}" required/>
                    </div>
                    <div class="form-group">
                        <label for="LAST_NAME"><fmt:message key="label.addStudentPage.lastName"/></label>
                        <input type="text" class="form-control" id="LAST_NAME" name="LAST_NAME" value="${FETCHED_STUDENT.lastName}" required/>
                    </div>
                    <div class="form-group">
                        <label for="EMAIL"><fmt:message key="label.listPage.email"/></label>
                        <input type="text" class="form-control" id="EMAIL" name="EMAIL" value="${FETCHED_STUDENT.email}" required/>
                    </div>
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