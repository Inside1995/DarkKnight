<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Dark Knight</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/training.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/work.css">
    <script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/main.js"></script>
    <script type="text/javascript" src="/resources/js/timer.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<jsp:include page="/WEB-INF/parts/sidebar.jsp"/>
<body>
<div class="content">
    <div id="training-image">
        <img src="/resources/images/training.png">
    </div>
    <div id="work_time">
        <c:if test="${trainingComplete}">
            <h1>Тренировка окончена!</h1>
        </c:if>
        <c:if test="${time ne null}">
            <small>Осталось времени до следующей тренировки
                <div id="time">${time}</div>
            </small>
        </c:if>
    </div>
    <c:if test="${exceptions ne null}">
        <div id="errors">
            <c:forEach var="exception" items="${exceptions}">
                <small>${exception}<br/></small>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${trainingComplete or time eq null}">
        <form method="post" action="/training/train_skills/upload?${_csrf.parameterName}=${_csrf.token}">
            <table id="skills-training">
                <tr>
                    <th>Сила</th>
                    <td>${myKnight.totalStrength}</td>
                    <td>
                        <select name="strength">
                            <option value="0,0,0">Не тренировать</option>
                            <option value="1,5,0">Отработать удары на манекене (+1 к силе, -5 от общего состояния)
                            </option>
                            <option value="5,15,0">Провести тренировочный бой со слабым соперником (+5 к силе, -15 от
                                общего
                                состояния)
                            </option>
                            <option value="10,20,0">Провести тренировочный бой со средним соперником (+10 к силе, -20 от
                                общего состояния)
                            </option>
                            <option value="15,25,0">Провести тренировочный бой с сильным соперником (+15 к силе, -25 от
                                общего состояния)
                            </option>
                            <option value="25,30,30">Тренироваться у лучшего мастера в округе (+25 к силе, -30 от общего
                                состояния, -30 золота)
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Выносливость</th>
                    <td>${myKnight.totalStamina}</td>
                    <td>
                        <select name="stamina">
                            <option value="0,0,0">Не тренировать</option>
                            <option value="1,5,0">Сделать разминку (+1 к выносливости, -5 от общего состояния)</option>
                            <option value="5,15,0">Пробежать 5км (+5 к выносливости, -15 от общего состояния)</option>
                            <option value="10,20,0">Пробежать 10км (+10 к выносливости, -20 от общего состояния)
                            </option>
                            <option value="15,25,0">Пробежать марафон 15км (+15 к выносливости, -25 от общего состояния)
                            </option>
                            <option value="25,30,30">Тренироваться у лучшего мастера в округе (+25 к выносливости, -30
                                от
                                общего
                                состояния, -30 золота)
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Защита</th>
                    <td>${myKnight.totalDefence}</td>
                    <td>
                        <select name="defence">
                            <option value="0,0,0">Не тренировать</option>
                            <option value="1,5,0">Тренировать уклоны (+1 к защите, -5 от общего состояния)</option>
                            <option value="5,15,0">Тренировать парирования (+5 к защите, -15 от общего состояния)
                            </option>
                            <option value="10,20,0">Тренировать отскоки (+10 к защите, -20 от общего состояния)</option>
                            <option value="15,25,0">Пройти полосу припятствий (+15 к защите, -25 от общего состояния)
                            </option>
                            <option value="25,30,30">Тренироваться у лучшего мастера в округе (+25 к защите, -30 от
                                общего
                                состояния, -30 золота)
                            </option>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="id" value="${myKnight.id}">
            <input id="start-training" type="submit" value="Start train">
        </form>
    </c:if>
</div>
</body>
</html>
