<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorisation</title>
</head>
<body>
<c:if test="${requestScope.doesUserExist eq false}">
    <div>Error: User ${requestScope.userName} doesn't exist.</div>
</c:if>

<c:if test="${requestScope.doesUserExist eq true}">
    <c:if test="${requestScope.doesPasswordRight eq false}">
        <div>Error: Password don't correct.</div>
    </c:if>
</c:if>

<form action="login" modelAttribute="user" method="post">
    Username: <input type="text" name="name"><br>
    Password: <input type="password" name="password"><br>
    <input type="Submit" value="login">
</form>
<form action="registration" method="get">
    <input type="submit" value="sign up">
</form>
</body>
</html>