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
    <link rel="stylesheet" type="text/css" href="/resources/css/work.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Bubblegum+Sans" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/clock.js"></script>
    <script type="text/javascript" src="/resources/js/timer.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="../parts/header.jsp"/>
    <jsp:include page="../parts/sidebar.jsp"/>
    <div id="content">
        <div id="user-name">
            <h3>Welcome back, ${myChar.name}</h3>
        </div>
        <div id="work">
            <img src="/resources/images/work.jpg" class="row">
            <span class="row">Your level is only ${myChar.lvl}, so you only have access to the dirtiest and low-paid job. You'll have to collect the shells for experienced killers and you get for it $ 25 when you're done</span>

            <c:if test="${workComplete ne true and time ne null}">
                <div id="work_time">
                    <small>Time left on the work:</small>
                    <div id="time">${time}</div>
                    </small>
                </div>
            </c:if>
            <%--<c:if test="${workComplete or myChar.work.endTime eq null}">--%>
            <%--<c:if test="${(goldAmount ne null or crystalAmount ne null or randomEquip ne null) and myChar.work.goldTaked}">--%>
            <%--Your last loot:<br>--%>
            <%--Gold: ${goldAmount}<br>--%>
            <%--Crystal: ${crystalAmount}<br>--%>
            <%--Equipment: ${randomEquip} <br>--%>
            <%--</c:if>--%>
            <%--<form action="/work/start_work/upload?${_csrf.parameterName}=${_csrf.token}" method="post">--%>
            <%--<input type="image" src="/resources/images/go_work.png" style="background-color: #A5A5A5;"/>--%>
            <%--<input type="hidden" name="id" value="${myChar.id}">--%>
            <%--</form>--%>
            <%--</c:if>--%>
            <%--<c:if test="${workComplete}">--%>
            <%--<c:if test="${workComplete}">--%>
            <%--<c:if test="${myChar.work.goldTaked ne true}">--%>
            <%--<form action="/work/get_gold/upload?${_csrf.parameterName}=${_csrf.token}" method="post">--%>
            <%--<input type="image" src="/resources/images/cash.png" title="Take the Loot">--%>
            <%--</form>--%>
            <%--</c:if>--%>
            <%--</c:if>--%>
            <%--</c:if>--%>

            <c:if test="${workComplete and myChar.work.goldTaked ne true}">
                <form action="/work/get_gold/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                    <input type="image" style="width: 100px; height: 100px; margin-left: 150px;"
                           src="/resources/images/cash.png"
                           title="Take the Loot">
                </form>
            </c:if>
            <c:if test="${(workComplete and myChar.work.goldTaked eq true) or myChar.work.endTime eq null}">
                <form action="/work/start_work/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                    <input type="image" src="/resources/images/go_work.png" style="background-color: #A5A5A5;"/>
                    <input type="hidden" name="id" value="${myChar.id}">
                </form>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>