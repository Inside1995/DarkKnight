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
    <link rel="stylesheet" type="text/css" href="/resources/css/duel.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script type="text/javascript" src="/resources/js/main.js"></script>
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
        <div id="history">
            <c:if test="${history ne null}">
                <table>
                    <c:forEach var="move" items="${history}">
                        <tr>
                            <td>${move}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <div id="whoWin">
            <h2>
                <c:choose>
                    <c:when test="${iWin eq true}">
                        YOU WIN!
                    </c:when>
                    <c:otherwise>
                        YOU LOSE!
                    </c:otherwise>
                </c:choose>
            </h2>
        </div>
    </div>
</div>
</body>
</html>
