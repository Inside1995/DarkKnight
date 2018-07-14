<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/resources/css/work.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <script type="text/javascript" src="/resources/js/timer.js"></script>
</head>
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<jsp:include page="/WEB-INF/parts/sidebar.jsp"/>
<div class="content">
    <img id="work-image" src="/resources/images/travel.png" width="400px" height="200px">
    <c:if test="${workComplete ne true and time ne null}">
        <div id="work_time">
            <small>Осталось времени в крестовом походе:
                <div id="time">${time}</div>
            </small>
        </div>
    </c:if>
    <div id="work_panel">
        <c:if test="${workComplete or myKnight.work.endTime eq null}">
            <c:if test="${(goldAmount ne null or crystalAmount ne null or randomEquip ne null) and myKnight.work.goldTaked}">
                Ваш последний лут:<br>
                Золото: ${goldAmount}<br>
                Кристаллы: ${crystalAmount}<br>
                Вещи: ${randomEquip} <br>
            </c:if>
            <form action="/work/start_work/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                <input type="image" src="/resources/images/christ_travel.png" title="Отправиться в поход">
                <input type="hidden" name="id" value="${myKnight.id}">
            </form>
        </c:if>
        <c:if test="${workComplete}">
            <c:if test="${workComplete}">
                <c:if test="${myKnight.work.goldTaked ne true}">
                    <form action="/work/get_gold/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                        <input type="image" src="/resources/images/chest.jpg" title="Забрать добычу">
                    </form>
                </c:if>
            </c:if>
        </c:if>
    </div>
</div>
</body>
</html>