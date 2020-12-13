<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<c:if test="${requestScope.isError eq true}">
    <div>Error: ${requestScope.message}</div>
</c:if>

<c:if test="${requestScope.usingExistLogin eq true}">
    <div>Error: ${requestScope.message}</div>
</c:if>

<form modelAttribute="user" method="post">
    Username: <input type="text" name="name"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="submit">
</form>
</body>
</html>