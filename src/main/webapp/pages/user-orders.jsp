<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
	<c:import url="\header.jsp" />
	<div class="body-block">
        <div class="orders-block">
            <div class="hr">
                <span>№</span>
                <span>Название книги</span>
                <span>Дата взятия</span>
                <span>Дата возврата</span>
            </div>
			<c:forEach var="order" items="${list}">
				<div class="my-orders">
                <span><c:out value="${order.id}"/></span>
                <span><c:out value="${order.book.title}"/></span>
                <span><c:out value="${order.creationDate}"/></span>
                <span><c:out value="${order.endingDate}"/></span>
				<span>
					<form action="" method="POST">
						<input type="hidden" name="command" value="return_book"/>
						<input type="hidden" name="order_id" value="<c:out value="${order.id}"/>"/>
						<input type="hidden" name="book_id" value="<c:out value="${order.book.id}"/>"/>
						<input class="put-back" type="submit" value="Сдать">
					</form>
				</span>
            </div>
			</c:forEach>
        </div>
    </div>
	<c:import url="\footer.jsp" />
</body>

</html>