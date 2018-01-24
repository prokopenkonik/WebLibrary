<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
	<c:import url="\header.jsp" />
	<div class="body-block">
        <div class="orders-block">
            <div class="hr">
                <span>№</span>
                <span>Имя пользователя</span>
                <span>Email</span>
                <span>Телефон</span>
                <span>Название книги</span>
                <span>Дата взятия</span>
                <span>Дата возврата</span>
            </div>
			<c:forEach var="order" items="${list}">
				<div class="orders-list">
                <span><c:out value="${order.id}"/></span>
                <span><c:out value="${order.user.name}"/></span>
                <span><c:out value="${order.user.mail}"/></span>
                <span><c:out value="${order.user.phoneNumber}"/></span>
                <span><c:out value="${order.book.title}"/></span>
                <span><c:out value="${order.creationDate}"/></span>
                <span><c:out value="${order.endingDate}"/></span>
            </div>
			</c:forEach>
        </div>
    </div>
	<c:import url="\footer.jsp" />
</body>

</html>