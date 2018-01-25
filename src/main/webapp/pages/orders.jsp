<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
	<c:import url="\header.jsp" />
	<div class="body-block">
        <div class="orders-block">
            <div class="hr">
                <div>№</div>
                <div>Имя пользователя</div>
                <div>Email</div>
                <div>Телефон</div>
                <div>Название книги</div>
                <div>Дата взятия</div>
                <div>Дата возврата</div>
            </div>
			<c:forEach var="order" items="${list}">
				<div class="orders-list">
                <div><c:out value="${order.id}"/></div>
                <div><c:out value="${order.user.name}"/></div>
                <div><c:out value="${order.user.mail}"/></div>
                <div><c:out value="${order.user.phoneNumber}"/></div>
                <div><c:out value="${order.book.title}"/></div>
                <div><c:out value="${order.creationDate}"/></div>
                <div><c:out value="${order.endingDate}"/></div>
				<form  action="" method="POST">
					<input class="accept-order" type="submit" value="Подтвердить">
				</form>
            </div>
			</c:forEach>
        </div>
    </div>
	<c:import url="\footer.jsp" />
</body>

</html>