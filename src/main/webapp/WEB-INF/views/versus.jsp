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
    <link rel="stylesheet" type="text/css" href="/resources/css/versus.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("[data-tooltip]").mousemove(function (eventObject) {
                $data_tooltip = $(this).attr("data-tooltip");
                $(".tooltip").html($data_tooltip)
                    .css({
                        "top" : eventObject.pageY + 5,
                        "left" : eventObject.pageX + 5
                    })
                    .show();
            }).mouseout(function () {
                $(".tooltip").hide()
                    .html("")
                    .css({
                        "top" : 0,
                        "left" : 0
                    });
            });
        });
    </script>
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
        <div id="battle-button">
            <form action="/duel/start_fighting/upload?${_csrf.parameterName}=${_csrf.token}">
                <input type="submit" value="BATTLE">
                <input type="hidden" name="opponent_id" value="${opponent.id}">
            </form>
        </div>
        <div id="versus">
            <div id="left-hero">
                <div class="person-equip" class="row">
                    <table>
                        <tr>
                            <td><img data-tooltip="${myChar.helm}" src="/game/get_armor_avatar/?id=${myChar.helm.id}&type=helm"></td>
                            <div class="tooltip"></div>
                            <td rowspan="3" colspan="2" style="padding: 0;"><img
                                    style="height: 252px; width: 140px; padding: 0; opacity: 1;"
                                    src="/resources/images/avatar_${myChar.type}.jpg">
                            </td>
                            <td><img data-tooltip="${myChar.jewellery}" src="/game/get_armor_avatar/?id=${myChar.jewellery.id}&type=jewellery"></td>
                        </tr>
                        <tr>
                            <td><img data-tooltip="${myChar.mainArmor}" src="/game/get_armor_avatar/?id=${myChar.mainArmor.id}&type=armor"></td>
                            <td><img data-tooltip="${myChar.ring}" src="/game/get_armor_avatar/?id=${myChar.ring.id}&type=ring"></td>
                        </tr>
                        <tr>
                            <td><img data-tooltip="${myChar.boots}" src="/game/get_armor_avatar/?id=${myChar.boots.id}&type=boots"></td>
                            <td><img data-tooltip="${myChar.weapon}" src="/game/get_armor_avatar/?id=${myChar.weapon.id}&type=weapon"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="right-hero">
                <div class="person-equip" style="float: right; margin-right: 30px;" class="row">
                    <table>
                        <tr>
                            <td><img data-tooltip="${opponent.helm}" src="/game/get_armor_avatar/?id=${opponent.helm.id}&type=helm"></td>
                            <div class="tooltip"></div>
                            <td rowspan="3" colspan="2" style="padding: 0;"><img
                                    style="height: 252px; width: 140px; padding: 0; opacity: 1;"
                                    src="/resources/images/avatar_${opponent.type}.jpg">
                            </td>
                            <td><img data-tooltip="${opponent.jewellery}" src="/game/get_armor_avatar/?id=${opponent.jewellery.id}&type=jewellery"></td>
                        </tr>
                        <tr>
                            <td><img data-tooltip="${opponent.mainArmor}" src="/game/get_armor_avatar/?id=${opponent.mainArmor.id}&type=armor"></td>
                            <td><img data-tooltip="${opponent.ring}" src="/game/get_armor_avatar/?id=${opponent.ring.id}&type=ring"></td>
                        </tr>
                        <tr>
                            <td><img data-tooltip="${opponent.boots}" src="/game/get_armor_avatar/?id=${opponent.boots.id}&type=boots"></td>
                            <td><img data-tooltip="${opponent.weapon}" src="/game/get_armor_avatar/?id=${opponent.weapon.id}&type=weapon"></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div id="skills">
            <table id="left-skills">
                <tr>
                    <td><img src="/resources/images/lvl.png"></td>
                    <td>Level</td>
                    <td>${myChar.lvl}</td>
                </tr>
                <tr>
                    <td>
                        <img src="/resources/images/strength.png"></td>
                    <td>Strength</td>
                    <td>${myChar.totalStrength}</td>
                </tr>
                <tr>
                    <td><img src="/resources/images/stamina.png"></td>
                    <td>Stamina</td>
                    <td>${myChar.totalStamina}</td>
                </tr>
                <tr>
                    <td><img src="/resources/images/defence.png"></td>
                    <td>Defence</td>
                    <td>${myChar.totalDefence}</td>
                </tr>
                <tr>
                    <td><img src="/resources/images/health.png"></td>
                    <td>Health</td>
                    <td>${myChar.health}</td>
                </tr>
            </table>
            <table id="right-skills">
                <tr>
                    <td><img src="/resources/images/lvl.png"></td>
                    <td>Level</td>
                    <td>${opponent.lvl}</td>
                </tr>
                <tr>
                    <td>
                        <img src="/resources/images/strength.png"></td>
                    <td>Strength</td>
                    <td>${opponent.totalStrength}</td>
                </tr>
                <tr>
                    <td><img src="/resources/images/stamina.png"></td>
                    <td>Stamina</td>
                    <td>${opponent.totalStamina}</td>
                </tr>
                <tr>
                    <td><img src="/resources/images/defence.png"></td>
                    <td>Defence</td>
                    <td>${opponent.totalDefence}</td>
                </tr>
                <tr>
                    <td><img src="/resources/images/health.png"></td>
                    <td>Health</td>
                    <td>${opponent.health}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
