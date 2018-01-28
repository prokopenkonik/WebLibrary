<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<body>
	<c:import url="\header.jsp" />
	<%@include file="set-language.jsp"%>
	<div class="body-block">
        <div class="orders-block">
            <div class="my-hr">
                <div><fmt:message key="label.number" /></div>
                <div><fmt:message key="label.order.title" /></div>
                <div><fmt:message key="label.date.take" /></div>
                <div><fmt:message key="label.date.return" /></div>
            </div>
			<c:forEach var="order" items="${list}">
				<div class="my-orders">
                <div><c:out value="${order.id}"/></div>
                <div><c:out value="${order.book.title}"/></div>
                <div><c:out value="${order.creationDate}"/></div>
                <div><c:out value="${order.endingDate}"/></div>
				<div>
					<c:choose>
						<c:when test="${order.accepted}">
							<form action="" method="POST">
								<input type="hidden" name="command" value="return_book"/>
								<input type="hidden" name="order_id" value="<c:out value="${order.id}"/>"/>
								<input type="hidden" name="book_id" value="<c:out value="${order.book.id}"/>"/>
								<input class="put-back" type="submit" value="<fmt:message key="button.book.return" />">
							</form>
						</c:when>
						<c:otherwise>
							<fmt:message key="label.order.notaccepted" />
						</c:otherwise>
					</c:choose>
				</div>
            </div>
			</c:forEach>
        </div>
    </div>
	<c:import url="\footer.jsp" />
</body>

</html>