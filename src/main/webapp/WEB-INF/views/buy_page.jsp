<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>My Game</title>
		<link rel="stylesheet" type="text/css" href="/resources/css/main.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/clock.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/header.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/sidebar.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/shop.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Bubblegum+Sans" rel="stylesheet"> 
	<script type="text/javascript" src="/resources/js/clock.js"></script>
</head>
</head>
<body>
	<div class="container">
		<jsp:include page="../parts/header.jsp"/>
		<jsp:include page="../parts/sidebar.jsp"/>
		<div id="content">
		<div id="user-name">
			<h3>Welcome back, ${myChar.name}</h3>
		</div>
		<div id="buy_page">
			<a href="/shop/getArmors/"><img src="/resources/images/buy_armor.jpg" style="width: 200px;" title="buy armor"></a><br/>
			<a href="/shop/getWeapons/"><img src="/resources/images/buy_weapon.jpg" style="width: 200px;" title="buy weapon"></a>
		</div>
		</div>
	</div>
</body>
</html>