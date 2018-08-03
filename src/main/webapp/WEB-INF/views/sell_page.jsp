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
    <script type="text/javascript" src="/resources/js/main.js"></script>
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
                <th>Avatar</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <c:if test="${myChar.bag ne null}">
                <c:forEach var="item" items="${myChar.bag}">
                    <div id="tooltip"></div>
                    <tr>
                        <td><img data-tooltip="${item}" style="width: 40px; height: 40px;" src="/game/get_armor_avatar/?id=${item.id}&type=${item.type}"></td>
                        <td>${item.name}</td>
                        <td>${item.price / 2}</td>
                        <td>
                            <form action="/game/sell/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                                <input type="submit" value="Sell">
                                <input type="hidden" name="id" value="${item.id}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>
</div>
</body>
</html>
