<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${LOCALE}" scope="session"/>
<fmt:setBundle basename="labels"/>
<c:if test="${empty USER_NAME}"><c:redirect url="/signin"/></c:if>
<c:set var="CURRENT_PAGE" value="/controller?command=LIST" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
			  integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

		<title>Web student tracker</title>
	</head>
	<body>
		<div class="d-flex flex-row justify-content-end">
			<c:import url="/view/header.jsp"/>
		</div>
		<main role="main">
			<div class="jumbotron" style="background-color: #5a78a1">
				<div class="d-flex flex-row justify-content-start">
					<div class="col-1">
						<img class="mb-4" src="view/img/logo.png" alt="logo" width="90" height="90">
					</div>
					<div class="col-8">
						<h2 class="display-3"><fmt:message key="label.listPage.universityTitle"/></h2>
					</div>
					<div class="col-3">
						<div class="card">
							<div class="card-header">
								<h4><fmt:message key="label.listPage.welcome"/>, ${USER_NAME}</h4>
							</div>
							<div class="card-body">
								<div class="row">
									<div class="col-4">
										<!--  UPDATE USER         -->
										<form action="${pageContext.request.contextPath}/controller" method="GET">
											<input type="hidden" name="command" value="LOAD-USER"/>
											<input type="hidden" name="USER_NAME" value="${USER_NAME}"/>
											<input type="submit" value="<fmt:message key="label.listPage.updateUser"/>" class="btn btn-sm btn-primary"/>
										</form>
									</div>
									<div class="col-4">
										<!--  DELETE USER         -->
										<form action="${pageContext.request.contextPath}/controller" method="POST">
											<input type="hidden" name="command" value="DELETE-USER"/>
											<input type="hidden" name="USER_NAME" value="${USER_NAME}"/>
											<input type="submit" value="<fmt:message key="label.listPage.deleteUser"/>" class="btn btn-sm btn-danger"
												   onclick="if(!(confirm('<fmt:message key="label.deleteUserMessage"/>'))) return false"/>
										</form>
									</div>
									<div class="col-4">
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
				<div class="row">
					<div class="col-8">
						<div class="row">
							<h5>Welcome to student web tracker system...</h5>
						</div>
						<div class="row">
							<h5>
								Here you can track all students that are in the database, add students to database, edit each student information,
								delete students and search students by a certain criterion
							</h5>
						</div>
					</div>
				</div>
			</div>
			<br/>
			<div class="container-fluid">
				<div class="d-flex flex-xl-row justify-content-center">
					<form class="form-inline" action="${pageContext.request.contextPath}/controller" method="GET">
						<input type="hidden" name="command" value="SEARCH"/>
						<div class="row">
							<div class="col">
								<input class="form-control" type="text" name="SEARCH_BOX_CONTENT" placeholder="<fmt:message key="label.listPage.searchStudent"/>"/>
							</div>
							<div class="col">
								<input type="submit" value="<fmt:message key="label.listPage.searchButton"/>" class="btn btn-outline-success"/>
							</div>
						</div>
					</form>
				</div>


				<div class="d-flex flex-row justify-content-center">
					<div class="col-md-6">
						<h2 class="h2"><fmt:message key="label.listPage.studentList"/></h2>
					</div>
					<div class="btn-toolbar col-md-6 d-flex justify-content-end">
						<a href="${pageContext.request.contextPath}/add-student">
							<button class="btn btn-sm btn-primary">
								<fmt:message key="label.listPage.addStudent"/>
							</button>
						</a>
					</div>
				</div>


				<div class="d-flex flex-row ">
						<div class="table-responsive">
							<table class="table">
								<thead class="thead-dark">
								<tr>
									<th scope="col"><fmt:message key="label.listPage.firstName"/></th>
									<th scope="col"><fmt:message key="label.listPage.lastName"/></th>
									<th scope="col"><fmt:message key="label.listPage.email"/></th>
									<th scope="col"><fmt:message key="label.listPage.action"/></th>
								</tr>
								</thead>

								<c:forEach var="tempStudent" items="${STUDENT_LIST}">
									<tr>
										<td>${tempStudent.firstName} </td>
										<td>${tempStudent.lastName}</td>
										<td>${tempStudent.email}</td>
										<td>
											<div class="form-row">
												<div class="col-3">
													<!--  UPDATE STUDENT         -->
													<form action="${pageContext.request.contextPath}/controller" method="GET">
														<input type="hidden" name="command" value="LOAD-STUDENT"/>
														<input type="hidden" name="STUDENT_ID" value="${tempStudent.id}"/>
														<input type="submit" value="<fmt:message key="label.listPage.update"/>"
															   class="btn btn-sm btn-primary"/>
													</form>
												</div>
												<div class="col-2">
													<!--  DELETE STUDENT         -->
													<form action="${pageContext.request.contextPath}/controller" method="POST">
														<input type="hidden" name="command" value="DELETE-STUDENT"/>
														<input type="hidden" name="STUDENT_ID" value="${tempStudent.id}"/>
														<input type="submit" value="<fmt:message key="label.listPage.delete"/>"
															   class="btn btn-sm btn-primary"
															   onclick="if(!(confirm('<fmt:message key="label.deleteStudentMessage"/>'))) return false"/>
													</form>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>

				</div>
			</div>
		</main>
	</body>
</html>