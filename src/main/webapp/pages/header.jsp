<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="set-language.jsp"%>

<html>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.head.title" /></title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/fonts.css">
</head>

<body>
	<header>
        <a href="/"><img class="logotip" src="../images/logotip.png" alt=""></a>
        <span class="slogan"><fmt:message key="label.slogan" /></span>
        <form method="POST" action="">
			<input type="hidden" name="command" value="search_book"/>
            <input class="search-input" placeholder="<fmt:message key="input.search"/>" type="text" name="query" value=""/>
            <input class="search-button" type="submit" value=""/>
        </form>
		<form method="POST" action="">
			<input type="hidden" name="command" value="change_language"/>
			<input type="hidden" name="language" value="ua"/>
			<input class="ua" type="submit" value="">
		</form>
		<form method="POST" action="">
			<input type="hidden" name="command" value="change_language"/>
			<input type="hidden" name="language" value="en"/>
			<input class="en" type="submit" value="">
		</form>
		<form method="POST" action="">
			<input type="hidden" name="command" value="change_language"/>
			<input type="hidden" name="language" value="ru"/>
			<input class="ru" type="submit" value="">
		</form>
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<img class="user-logo" src="../images/user.png" alt="">
				<span class="hello" tabindex="1"><fmt:message key="label.user.hello" /><c:out value="${sessionScope.user.login}" /></span>
				<ul class="user-menu">
					<li>
						<form action="" method="POST">
							<input type="hidden" name="command" value="get_orders"/>
							<input class="my_orders" type="submit" value="<fmt:message key="button.mybooks" />">
						</form>
					</li>
					<li>
						<form action="" method="POST">
							<input type="hidden" name="command" value="edit_profile"/>
							<input class="profile" type="submit" value="<fmt:message key="button.profile" />">
						</form>
					</li>
					<li>
						<form method="POST" action="">
							<input type="hidden" name="command" value="logout"/>
							<input class="exit" type="submit" value="<fmt:message key="button.exit" />">
						</form>
					</li>
				</ul>
			</c:when>
			
			<c:when test="${not empty sessionScope.admin}">
				<img class="user-logo" src="../images/user.png" alt="">
				<span class="hello" tabindex="1"><fmt:message key="label.admin.hello" /><c:out value="${sessionScope.admin.login}" /></span>
				<ul class="user-menu">
					<li>
						<form action="" method="POST">
							<input type="hidden" name="command" value="get_orders_for_admin"/>
							<input class="my_orders" type="submit" value="<fmt:message key="button.takenbooks" />">
						</form>
					</li>
					<li>
						<form action="" method="POST">
							<input type="hidden" name="command" value="get_page_to_add"/>
							<input class="add_book" type="submit" value="<fmt:message key="button.book.add" />">
						</form>
					</li>
					<li>
						<form method="POST" action="">
							<input type="hidden" name="command" value="logout"/>
							<input class="exit" type="submit" value="<fmt:message key="button.exit" />">
						</form>
					</li>
				</ul>
			</c:when>
			
			<c:otherwise>
				<form method="POST" action="">
					<input type="hidden" name="command" value="authorization"/>
					<input class="login" type="text" placeholder="<fmt:message key="input.login" />" name="login" value="">
					<input class="password" type="password" placeholder="<fmt:message key="input.password" />" name="password" value="">
					<input class="enter" type="submit" value="<fmt:message key="button.enter" />">
					<a href="../pages/registration.jsp" class="registration"><fmt:message key="button.registration" /></a>
				</form>
				<c:if test="${not empty error}">
					<span class="enter-error"><fmt:message key="${error}" /></span>
				</c:if>
			</c:otherwise>
		</c:choose>
    </header>
</body>

</html>