<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="labels"/>
<fmt:setLocale value="en_US" scope="session"/>
<!DOCTYPE html>
<html>

<body>
<form action="${pageContext.request.contextPath}/controller" method="POST">
    <input type="hidden" name="command" value="CHANGE-LOCALE">
    <input type="hidden" name="PAGE" value="${CURRENT_PAGE}">
    <input type="radio" id="ENGLISH" value="en_US" name="LOCALE" checked>
    <label for="ENGLISH">English (US)</label>
    <input type="radio" id="DEUTSCH" value="de_DE" name="LOCALE">
    <label for="DEUTSCH">Deutsch (DE)</label>
    <input type="submit" value="<fmt:message key="label.header.changeLanguage"/>" class="btn btn-sm btn-outline-primary">
</form>
</body>
</html>
