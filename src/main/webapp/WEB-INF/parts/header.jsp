<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="header">
    <div id="cp-panel">
        <div id="cp" style="width: ${myChar.condition.condition}%;">
        </div>
        <d>CP ${myChar.condition.condition}/100</d>
    </div>
    <div id="health-panel">
        <div id="health" style="width: ${myChar.health}%;">
        </div>
        <d>HP ${myChar.health}/100</d>
    </div>
    <div id="currency-panel">
        <div class="cash">
            <img src="/resources/images/cash.png" style="height: 80px; margin-top: 20px;">
            <d>${myChar.goldAmount}$</d>
        </div>
        <div class="cash">
            <img src="/resources/images/brilliant.png" style="height: 80px; margin-top: 20px;">
            <d>${myChar.crystalAmount}</d>
        </div>
    </div>
</div>