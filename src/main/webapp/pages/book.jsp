<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<body>
    
	<c:import url="\header.jsp" />
	<%@include file="set-language.jsp"%>

    <div class="body-block">
        <img class="book-full-img" src="../images/kniga.jpg" alt="image">
        <div class="book-info">
            <p><fmt:message key="label.title" /><c:out value="${book.title}"/></p>
			<p><fmt:message key="label.author" /><c:out value="${book.getAuthorsAsString()}"/></p>
            <p><fmt:message key="label.genre" /><c:out value="${book.genre}"/></p>
            <p><fmt:message key="label.publisher" /><c:out value="${book.publisher}"/></p>
			<p><fmt:message key="label.publishing.year" /><c:out value="${book.publishingYear}"/></p>
			<p>
				<c:choose>
					<c:when test="${not book.taken}">
						<fmt:message key="label.free" />
						<c:if test="${empty sessionScope.admin}">
							<form action="" method="POST">
								<input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
								<input type="hidden" name="command" value="hire_book"/>
								<input class="hire-book" type="submit" value="<fmt:message key="button.book.hire" />">
								<input type="date" name="date" value="">
							</form>
						</c:if>
						<c:if test="${not empty error}">
							<span><fmt:message key="${error}" /></span>
						</c:if>
					</c:when>
					<c:otherwise>
						<fmt:message key="label.taken" />
					</c:otherwise>
				</c:choose>
			</p>
        </div>
        <p class="mini-description-span"><fmt:message key="label.description" /></p>
        <p class="mini-description"><c:out value="${book.description}"/></p>
    </div>
    
	<c:import url="\footer.jsp" />
	
</body>

</html>