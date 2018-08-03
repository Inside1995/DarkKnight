<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="sidebar">
    <div id="nav">
        <ul>
            <li><a href="/game">Main page</a></li>
            <li><a href="/training">Training</a></li>
            <li><a href="/shop">Shop</a></li>
            <li><a href="/work">Work</a></li>
            <li><a href="/duel">Duel</a></li>
            <li><a href="/game/statistic">Statistic</a></li>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <li>
                    <a id="logout"
                       onclick="document.forms['logoutForm'].submit()">Log out</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="clock">
        <div id="Date"></div>
        <ul>
            <li id="hours"></li>
            <li id="point">:</li>
            <li id="min"></li>
            <li id="point">:</li>
            <li id="sec"></li>
        </ul>
    </div>
</div>
