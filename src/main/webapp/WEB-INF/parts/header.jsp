<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="header">
    <div id="logo">
        <a href="/"><img src="/resources/images/logo.png"></a>
    </div>
    <div id="right-panel">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <a id="logout"
               onclick="document.forms['logoutForm'].submit()">Выйти из игры</a>
        </c:if>
    </div>
    <nav id="top-navigator">
        <ul>
            <li><p>Уровень персонажа:<br/>
                ${myKnight.lvl}</p>
            </li>
            <li><a href="#"><img src="/resources/images/gold.png">Золото:<br/>
                ${myKnight.goldAmount}</a>
            </li>
            <li><a href="#"><img src="/resources/images/crystal.png">Кристалл:<br/>
                ${myKnight.crystalAmount}</a>
            </li>
        </ul>
    </nav>
</div>