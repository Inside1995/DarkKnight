<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Game</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/clock.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main_page.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/statistic.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Bubblegum+Sans" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/clock.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="../parts/header.jsp"/>
    <jsp:include page="../parts/sidebar.jsp"/>
    <div id="content">
        <div id="user-name">
            <h3>Welcome back, ${myChar.name}</h3>
        </div>
        <div id="rating">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Class</th>
                    <th>Total battle</th>
                    <th>Win battle</th>
                    <th>Lose battle</th>
                    <th>Win percentage</th>
                </tr>
                <c:if test="${allHeroes ne null}">
                    <c:forEach var="hero" items="${allHeroes}">
                        <tr>
                            <td>${hero.name}</td>
                            <td><img src="/resources/images/${hero.type}.png" title="${hero.type}"></td>
                            <td>${hero.statistic.totalBattle}</td>
                            <td>${hero.statistic.winBattle}</td>
                            <td>${hero.statistic.loseBattle}</td>
                            <td>${hero.statistic.winPercentage}%</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</div>
</body>
</html>
