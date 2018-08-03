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
    <link rel="stylesheet" type="text/css" href="/resources/css/training.css">
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
        <div id="training">
            <img src="/resources/images/training.png" style="width: 500px;">
            <table>
                <tr>
                    <th>
                        <d>Strength</d>
                    </th>
                    <td>
                        <d id="strength">${myChar.skills.strength}</d>
                    </td>
                    <td>
                        <d>100</d>
                        <img src="/resources/images/cash.png" style="height: 20px"></td>
                    <td>
                        <form action="/training/train_skill/upload?${_csrf.parameterName}=${_csrf.token}"
                              method="post">
                            <input type="hidden" name="param" value="strength">
                            <input type="submit" value="upgrade">
                        </form>
                    </td>
                </tr>
                <tr>
                    <th>
                        <d>Stamina</d>
                    </th>
                    <td>
                        <d>${myChar.skills.stamina}</d>
                    </td>
                    <td>
                        <d>100</d>
                        <img src="/resources/images/cash.png" style="height: 20px"></td>
                    <td>
                        <form action="/training/train_skill/upload?${_csrf.parameterName}=${_csrf.token}"
                              method="post">
                            <input type="hidden" name="param" value="stamina">
                            <input type="submit" value="upgrade">
                        </form>
                    </td>
                </tr>
                <tr>
                    <th>
                        <d>Defence</d>
                    </th>
                    <td>
                        <d>${myChar.skills.defence}</d>
                    </td>
                    <td>
                        <d>100</d>
                        <img src="/resources/images/cash.png" style="height: 20px">
                    </td>
                    <td>
                        <form action="/training/train_skill/upload?${_csrf.parameterName}=${_csrf.token}"
                              method="post">
                            <input type="hidden" name="param" value="defence">
                            <input type="submit" value="upgrade">
                        </form>
                    </td>
                </tr>
            </table>
            <c:if test="${exceptions ne null}">
                <div id="error-panel">
                    <c:forEach items="${exceptions}" var="exception">
                        <small style="color: red;">
                            ${exception}.
                        </small>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
