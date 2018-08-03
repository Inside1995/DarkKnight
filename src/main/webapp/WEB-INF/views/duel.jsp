<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <title>My Game</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/clock.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/duel.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script type="text/javascript" src="/resources/js/main.js"></script>
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
                        <c:if test="${hero ne myChar}">
                            <tr>
                                <td>${hero.name}</td>
                                <td>${hero.lvl}</td>
                                <td><img src="/resources/images/${hero.type}.png"></td>
                                <td>${hero.statistic.winPercentage}</td>
                                <td>
                                    <form action="/duel/versus/upload?${_csrf.parameterName}=${_csrf.token}"
                                          method="post">
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
