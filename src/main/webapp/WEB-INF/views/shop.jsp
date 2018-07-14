<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/shop.css">
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<jsp:include page="/WEB-INF/parts/sidebar.jsp"/>
<div id="shop" class="content">
    <div id="buttons">
        <form action="/shop/getWeapons/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
            <input type="submit" value="Оружие"><br/>
        </form>
        <form action="/shop/getArmors/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
            <input type="submit" value="Доспехи">
        </form>
    </div>
</div>
</body>
</html>
