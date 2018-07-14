<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/duel.css">
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<jsp:include page="/WEB-INF/parts/sidebar.jsp"/>
<div class="content" id="fight-content">
    <div id="allHeroes">
        <c:if test="${allHeroes ne null}">
            <table>
                <tr>
                    <th>Name</th>
                    <th>lvl</th>
                    <th>class</th>
                    <th>Win %</th>
                </tr>
                <c:forEach items="${allHeroes}" var="hero">
                    <c:if test="${hero ne myKnight}">
                        <tr>
                            <td>${hero.name}</td>
                            <td>${hero.lvl}</td>
                            <td><img class="class_image" src="/resources/images/hlem.png"></td>
                            <td>${hero.statistic.winPercentage}</td>
                            <td>
                                <form action="/duel/versus/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                                    <input type="image" src="/resources/images/fight.png" style="width: 50px">
                                    <input type="hidden" name="opponent_id" value="${hero.id}">
                                </form>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</div>
</body>
</html>
