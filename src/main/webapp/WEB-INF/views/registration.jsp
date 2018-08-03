<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Game</title>
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/resources/css/registration.css" media="screen" type="text/css"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <style>
        label > input{ /* HIDE RADIO */
            visibility: hidden; /* Makes input not-clickable */
            position: absolute; /* Remove input from document flow */
        }
        label > input + img{ /* IMAGE STYLES */
            cursor:pointer;
            border:2px solid transparent;
        }
        label > input:checked + img{ /* (RADIO CHECKED) IMAGE STYLES */
            border:2px solid yellow;
        }
    </style>
</head>

<body>
<div id="login-form">
    <h1>REGISTRATION</h1>
    <form:form method="POST" modelAttribute="userForm">
        <fieldset>
            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control"
                                placeholder="Password"></form:input>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control"
                                placeholder="Confirm your password"></form:input>
                </div>
            </spring:bind>

            <!-- Try paste class info -->
            <label>
                <input type="radio" name="class" value="zombie"/>
                <img src="/resources/images/ZOMBIE.png">
            </label>
            <label>
                <input type="radio" name="class" value="human"/>
                <img src="/resources/images/HUMAN.png">
            </label> <br/>
            <!-- -->
            <c:if test="${errors ne null}">
                <c:forEach var="error" items="${errors}">
                    <small>${error}</small>
                    <br>
                </c:forEach>
            </c:if>
            <input type="submit" value="REGISTRATION"></button>
        </fieldset>
    </form:form>
</div>
</body>
</html>
