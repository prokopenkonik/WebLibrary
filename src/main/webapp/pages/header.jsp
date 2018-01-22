<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>Каталог</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/fonts.css">
</head>

<body>
	<header>
        <a href="/"><img class="logotip" src="../images/logotip.png" alt=""></a>
        <span class="slogan">Онлайн библиотека</span>
        <form method="POST" action="">
			<input type="hidden" name="command" value="search_book"/>
            <input class="search-input" placeholder="Поиск" type="text" name="query" value="">
            <input class="search-button" type="submit" value="">
        </form>
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<img class="user-logo" src="../images/user.png" alt="">
				<span class="hello">Здравствуйте, <c:out value="${sessionScope.user.login}" /></span>
				<form method="POST" action="">
					<input type="hidden" name="command" value="logout"/>
					<input class="enter" type="submit" value="Выход">
				</form>
			</c:when>
			
			<c:when test="${not empty sessionScope.admin}">
				<form action="" method="POST">
					<input type="hidden" name="command" value="add_book"/>
					<input class="add_book" type="submit" value="Добавить товар">
				</form>
				<img class="user-logo" src="../images/user.png" alt="">
				<span class="hello">Приветствую, <c:out value="${sessionScope.admin.login}" /></span>
				<form method="POST" action="">
					<input type="hidden" name="command" value="logout"/>
					<input class="enter" type="submit" value="Выход">
				</form>
			</c:when>
			
			<c:otherwise>
				<form method="POST" action="">
					<input type="hidden" name="command" value="authorization"/>
					<input class="login" type="text" placeholder="Логин" name="login" value="">
					<input class="password" type="password" placeholder="Пароль" name="password" value="">
					<input class="enter" type="submit" value="Вход">
					<a href="pages/registration.jsp" class="registration">Регистрация</a>
					<input class="remind" type="checkbox"><span class="remind-span">Запомнить меня</span>
				</form>
			</c:otherwise>
		</c:choose>
    </header>
</body>

</html>