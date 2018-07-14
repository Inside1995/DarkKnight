<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Dark Knight</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
    <script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/main.js"></script>
    <script>
        $(document).ready(function () {
            $("input[name='sell']").click(function () {
                return (confirm("Вы уверены, что хотите продать предмет?"));
            });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<div class="row">
    <jsp:include page="/WEB-INF/parts/sidebar.jsp"/>
    <div class="content">
        <div id="person-equip">
            <table>
                <tr>
                    <td>
                        <a data-tooltip="${myKnight.helm}" href="/game/unequip/?id=${myKnight.helm.id}">
                            <img class="person-equip-img" src="/game/get_armor_avatar/?id=${myKnight.helm.id}">
                        </a>
                    </td>
                    <div id="tooltip"></div>
                    <td rowspan="3" colspan="2"><img src="/resources/images/knight.png" style="opacity: 1;"></td>
                    <td>
                        <a data-tooltip="${myKnight.jewellery}" href="/game/unequip/?id=${myKnight.jewellery.id}">
                            <img class="person-equip-img" src="/game/get_armor_avatar/?id=${myKnight.jewellery.id}">
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a data-tooltip="${myKnight.mainArmor}" href="/game/unequip/?id=${myKnight.mainArmor.id}"
                           title="Снять Экипировку">
                            <img class="person-equip-img" src="/game/get_armor_avatar/?id=${myKnight.mainArmor.id}">
                        </a>
                    </td>
                    <td>
                        <a data-tooltip="${myKnight.ring}" href="/game/unequip/?id=${myKnight.ring.id}"
                           title="Снять Экипировку">
                            <img class="person-equip-img" src="/game/get_armor_avatar/?id=${myKnight.ring.id}">
                        </a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a data-tooltip="${myKnight.boots}" href="/game/unequip/?id=${myKnight.boots.id}"
                           title="Снять Экипировку">
                            <img class="person-equip-img" src="/game/get_armor_avatar/?id=${myKnight.boots.id}">
                        </a>
                    </td>
                    <td>
                        <a data-tooltip="${myKnight.weapon}" href="/game/unequip/?id=${myKnight.weapon.id}"
                           title="Снять Экипировку">
                            <img class="person-equip-img" src="/game/get_armor_avatar/?id=${myKnight.weapon.id}">
                        </a>
                    </td>
                </tr>
            </table>
        </div>
        <div id="person-info">
            <h3 style="font-size: 14px; margin-bottom: 10px;">Информация о персонаже</h3>
            <table style="text-align: left; font-size: 10px; margin-bottom: 10px;">
                <tr>
                    <th>Уровень</th>
                    <td>${myKnight.lvl}</td>
                    <th>Выносливость</th>
                    <td>${myKnight.totalStamina}</td>
                </tr>
                <tr>
                    <th>Сила</th>
                    <td>${myKnight.totalStrength}</td>
                    <th>Защита от магии</th>
                    <td>15</td>
                </tr>
                <tr>
                    <th>Защита</th>
                    <td>${myKnight.totalDefence}</td>
                    <th>Кондиции</th>
                    <td>${myKnight.condition.condition}</td>
                </tr>
            </table>
        </div>
        <div id="invertar">
            <h1>Инвертарь</h1>
            <c:if test="${myKnight.bag ne null}">
                <table id="item-list">
                    <c:forEach var="item" items="${myKnight.bag}">
                        <tr>
                            <td><img data-tooltip="${item}" src="/game/get_armor_avatar/?id=${item.id}"></td>
                            <td>${item.name}</td>

                            <td>
                                <form action="/game/equip/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                                    <input type="hidden" name="id" value="${item.id}">
                                    <input type="submit" name="equip" style="width: 100%" value="Надеть"><br/>
                                </form>
                                <form action="/game/sell/upload?${_csrf.parameterName}=${_csrf.token}" method="post">
                                    <input type="hidden" name="id" value="${item.id}">
                                    <input type="submit" name="sell" style="width: 100%" value="Продать">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <div id="statistic">
            <h1>Статистика</h1>
            <table>
                <tr>
                    <th>Всего боев</th>
                    <th>Побед</th>
                    <th>Поражений</th>
                    <th>Процент побед</th>
                </tr>
                <tr>
                    <td>${myKnight.statistic.totalBattle}</td>
                    <td>${myKnight.statistic.winBattle}</td>
                    <td>${myKnight.statistic.loseBattle}</td>
                    <td>${myKnight.statistic.winPercentage}%</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>