<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/clock.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/shop.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Bubblegum+Sans" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/clock.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/parts/header.jsp"/>
    <jsp:include page="/WEB-INF/parts/sidebar.jsp"/>
    <div id="content">
        <div id="user-name">
            <h3>Welcome back, ${myChar.name}</h3>
        </div>
        <div id="shop-container"/>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Attack</th>
                <th>Defence</th>
                <th>Stamina</th>
                <th>Price</th>
            </tr>
            <c:if test="${armorItems ne null}">
                <c:forEach var="item" items="${armorItems}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>0</td>
                        <td>${item.defence}</td>
                        <td>${item.stamina}</td>
                        <td>${item.price}</td>
                        <form action="/shop/buy/upload?${_csrf.parameterName}=${_csrf.token} method=" post
                        ">
                        <input type="hidden" name="item_id" value="${item.id}">
                        <td><input type="submit" value="Buy"></td>
                        </form>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${weaponItems ne null}">
                <c:forEach var="item" items="${weaponItems}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.attack}</td>
                        <td>0</td>
                        <td>${item.stamina}</td>
                        <td>${item.price}</td>
                        <form action="/shop/buy/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                            <input type="hidden" name="item_id" value="${item.id}">
                            <td><input type="submit" value="Buy"></td>
                        </form>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <c:if test="${exception ne null}">
            <div id="errors">
                <small>${exception}</small>
            </div>
        </c:if>
    </div>
</div>
</div>
</body>
</html>
