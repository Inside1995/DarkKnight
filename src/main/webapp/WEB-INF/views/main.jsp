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
        <div id="person-equip" class="row">
            <table>
                <tr>
                    <td><a href="/game/unequip/?id=${myChar.helm.id}"><img data-tooltip="${myChar.helm}" src="/game/get_armor_avatar/?id=${myChar.helm.id}&type=helm"></a></td>
                    <div id="tooltip"></div>
                    <td rowspan="3" colspan="2" style="padding: 0;"><img
                            style="height: 262px; width: 170px; padding: 0; opacity: 1;"
                            src="/resources/images/avatar_${myChar.type}.jpg">
                    </td>
                    <td><a href="/game/unequip/?id=${myChar.jewellery.id}"><img data-tooltip="${myChar.jewellery}" src="/game/get_armor_avatar/?id=${myChar.jewellery.id}&type=jewellery"></a></td>
                </tr>
                <tr>
                    <td><a href="/game/unequip/?id=${myChar.mainArmor.id}"><img data-tooltip="${myChar.mainArmor}" src="/game/get_armor_avatar/?id=${myChar.mainArmor.id}&type=armor"></a></td>
                    <td><a href="/game/unequip/?id=${myChar.ring.id}"><img data-tooltip="${myChar.ring}" src="/game/get_armor_avatar/?id=${myChar.ring.id}&type=ring"></a></td>
                </tr>
                <tr>
                    <td><a href="/game/unequip/?id=${myChar.boots.id}"><img data-tooltip="${myChar.boots}" src="/game/get_armor_avatar/?id=${myChar.boots.id}&type=boots"></a></td>
                    <td><a href="/game/unequip/?id=${myChar.weapon.id}"><img data-tooltip="${myChar.weapon}" src="/game/get_armor_avatar/?id=${myChar.weapon.id}&type=weapon"></a></td>
                </tr>
            </table>
        </div>
        <div id="inventory" class="row">
            <h3 style="margin-bottom: 2px; color: white;">Inventory</h3>
            <c:if test="${myChar.bag ne null}">
                <table>
                    <c:forEach var="item" items="${myChar.bag}">
                        <tr>
                            <td>
                                <img data-tooltip="${item}" src="/game/get_armor_avatar/?id=${item.id}&type=${item.type}">
                            </td>
                            <td>
                                <form action="/game/equip/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                                    <input type="hidden" name="id" value="${item.id}">
                                    <input type="submit" name="equip" style="width: 100%" value="Equip"><br/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <div id="person-info">
            <table>
                <tr>
                    <th>Name</th>
                    <td>${myChar.name}</td>
                    <th>Strength</th>
                    <td>${myChar.totalStrength}</td>
                </tr>
                <tr>
                    <th>Class</th>
                    <td>${myChar.type}</td>
                    <th>Stamina</th>
                    <td>${myChar.totalStamina}</td>
                </tr>
                <tr>
                    <th>Level</th>
                    <td>${myChar.lvl}</td>
                    <th>Defence</th>
                    <td>${myChar.totalDefence}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>