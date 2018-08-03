<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Game</title>
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/resources/css/registration.css" media="screen" type="text/css" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
</head>

<body>
<div id="login-form">
    <h1>AUTHORIZATION</h1>
    <fieldset>
        <form action="${contextPath}/login" method="post">
            <input type="text" name="username" placeholder="Login">
            <input type="password" name="password" placeholder="Password" onBlur="if(this.value=='')this.value='Пароль'" onFocus="if(this.value=='Пароль')this.value='' ">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <small>${error}</small>
            <input type="submit" value="LOG IN">
            <input type="button" onclick="location.href='/registration';" value="REGISTRATION"></button>
        </form>
    </fieldset>
</div>
</body>
</html>
