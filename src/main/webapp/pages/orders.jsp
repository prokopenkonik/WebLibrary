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
				<c:choose>
					<c:when test="${order.accepted}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Заказ принят
					</c:when>
					<c:otherwise>
						<form  action="" method="POST">
							<input type="hidden" name="command" value="accept_order"/>
							<input type="hidden" name="order_id" value="<c:out value="${order.id}"/>">
							<input class="accept-order" type="submit" value="Принять">
						</form>
						<form  action="" method="POST">
							<input type="hidden" name="command" value="deny_order"/>
							<input type="hidden" name="order_id" value="<c:out value="${order.id}"/>">
							<input type="hidden" name="book_id" value="<c:out value="${order.book.id}"/>">
							<input class="accept-order" type="submit" value="Отказать">
						</form>
					</c:otherwise>
				</c:choose>
				
            </div>
			</c:forEach>
        </div>
    </div>
	<c:import url="\footer.jsp" />
</body>

</html>