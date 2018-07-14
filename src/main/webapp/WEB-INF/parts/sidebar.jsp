<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="left-side">
    <nav id="left-side-nav">
        <ul>
            <li><a href="/">Главная</a></li>
            <li><a href="/training">Тренировка</a></li>
            <li><a href="/duel">Найти противника</a></li>
            <li><a href="/shop">Магазин</a></li>
            <li><a href="/work">Крестовый поход</a></li>
        </ul>
    </nav>
    <div id="ratings">
        <h3 style="text-align: center; font-size: 15px;">Рейтинг</h3>
        <table>
            <tr>
                <th>№</th>
                <th>Имя</th>
                <th>Раса</th>
                <th>Процент побед</th>
            </tr>
            <c:if test="${allHeroes ne null}">
                <c:forEach var="hero" items="${allHeroes}" varStatus="count">
                    <tr>
                        <td>${count.count}</td>
                        <td>${hero.name}</td>
                        <td>Рыцарь</td>
                        <td>${hero.statistic.winPercentage}%</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <div id="news">
        <h3 style="text-align: center; font-size: 15px; margin-bottom: 5px;">Главные новости</h3>
        <span>Скоро в сеть запустится самая лучшая игра!</span>
    </div>
</div>
