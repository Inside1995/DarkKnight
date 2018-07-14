<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/versus.css">
    <script src="/resources/js/jquery-3.3.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.control').click(function () {
                var param1 = $(this).val();
                var opponentName = $('#opponentName').text();
                $.ajax({
                    url: '/duel/doMove/upload?${_csrf.parameterName}=${_csrf.token}',
                    data: {
                        param: param1,
                        opponentName: opponentName
                    },
                    method: 'POST',
                    success: function (msg) {
                        var battleText = msg.split("\n")[0];
                        $("#course-of-events").hide("slow");
                        $("#course-of-events p").text(battleText);
                        $("#course-of-events").show("slow");
                        var damages = msg.split("\n")[1];
                        var myHP = damages.split(":")[0];
                        var opponentHP = damages.split(":")[1];
                        $(".hp:first").text(myHP);
                        $(".hp:last").text(opponentHP);
                        if (myHP <= 0 || opponentHP <= 0) {
                            $('.control').hide();
                            var result = myHP < opponentHP ? "You lose" : "You win";
                            alert(result);
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/parts/header.jsp"/>
<jsp:include page="/WEB-INF/parts/sidebar.jsp"/>
<div class="content">
    <div id="first-player">
        <img src="/resources/images/knight.png">
        <table class="player-info">
            <tr>
                <td>Name</td>
                <td>${myKnight.name}</td>
            </tr>
            <tr>
                <td>HP</td>
                <td class="hp">${myKnight.health}</td>
            </tr>
            <tr>
                <td>Class</td>
                <td>Knight</td>
            </tr>
            <tr>
                <td>Win %</td>
                <td>${myKnight.statistic.winPercentage}</td>
            </tr>
            <tr>
                <td>Strength</td>
                <td>${myKnight.skills.strength}</td>
            </tr>
            <tr>
                <td>Stamina</td>
                <td>${myKnight.skills.stamina}</td>
            </tr>
        </table>
    </div>
    <div id="second-player">
        <img src="/resources/images/knight.png">
        <table class="player-info">
            <tr>
                <td>Name</td>
                <td id="opponentName">${opponent.name}</td>
            </tr>
            <tr>
                <td>HP</td>
                <td class="hp">${opponent.health}</td>
            </tr>
            <tr>
                <td>Class</td>
                <td>Knight</td>
            </tr>
            <tr>
                <td>Win %</td>
                <td>${opponent.statistic.winPercentage}</td>
            </tr>
            <tr>
                <td>Strength</td>
                <td>${opponent.skills.strength}</td>
            </tr>
            <tr>
                <td>Stamina</td>
                <td>${opponent.skills.stamina}</td>
            </tr>
        </table>
    </div>
    <div id="vs-image">
        <img src="/resources/images/vs.png">
    </div>
    <div id="course-of-events">
        <p style="font-size: 10px;"></p>
    </div>
    <div id="fight-panel">
        <input class="control" type="image" value="attack" src="/resources/images/fight.png"><br/>
        <input class="control" type="image" value="defence" src="/resources/images/block.png"><br/>
    </div>
</div>
</body>
</html>