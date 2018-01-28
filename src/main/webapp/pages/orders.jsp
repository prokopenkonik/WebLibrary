<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<body>
	<c:import url="\header.jsp" />
	<%@include file="set-language.jsp"%>
	<div class="body-block">
        <div class="orders-block">
            <div class="hr">
                <div><fmt:message key="label.number" /></div>
                <div><fmt:message key="label.name" /></div>
                <div><fmt:message key="label.mail" /></div>
                <div><fmt:message key="label.phone" /></div>
                <div><fmt:message key="label.order.title" /></div>
                <div><fmt:message key="label.date.take" /></div>
                <div><fmt:message key="label.date.return" /></div>
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
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="label.order.accepted" />
					</c:when>
					<c:otherwise>
						<form  action="" method="POST">
							<input type="hidden" name="command" value="accept_order"/>
							<input type="hidden" name="order_id" value="<c:out value="${order.id}"/>">
							<input class="accept-order" type="submit" value="<fmt:message key="button.accept" />">
						</form>
						<form  action="" method="POST">
							<input type="hidden" name="command" value="deny_order"/>
							<input type="hidden" name="order_id" value="<c:out value="${order.id}"/>">
							<input type="hidden" name="book_id" value="<c:out value="${order.book.id}"/>">
							<input class="accept-order" type="submit" value="<fmt:message key="button.deny" />">
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