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
    <table>
        <tr>
            <th>ID</th>
            <th>Наименование</th>
            <th>Атака</th>
            <th>Защита</th>
            <th>Выносливость</th>
            <th>Цена</th>
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
                    <form action="/shop/buy/upload?${_csrf.parameterName}=${_csrf.token} method="post">
                        <input type="hidden" name="item_id" value="${item.id}">
                        <td><input type="submit" value="Купить"></td>
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
                        <td><input type="submit" value="Купить"></td>
                    </form>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
