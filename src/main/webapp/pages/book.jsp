<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
    
	<c:import url="\header.jsp" />

    <div class="body-block">
        <img class="book-full-img" src="../images/kniga.jpg" alt="image">
        <div class="book-info">
            <p>Название: <c:out value="${book.title}"/></p>
			<p>Автор: <c:out value="${book.getAuthorsAsString()}"/></p>
            <p>Жанр: <c:out value="${book.genre}"/></p>
            <p>Издательство: <c:out value="${book.publisher}"/></p>
			<p>Год издания: <c:out value="${book.publishingYear}"/></p>
			<p>
				<c:choose>
					<c:when test="${not book.taken}">
						Есть в наличии
						<c:if test="${empty sessionScope.admin}">
							<form action="" method="POST">
								<input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
								<input type="hidden" name="command" value="hire_book"/>
								<input class="hire-book" type="submit" value="Заказать">
								<input type="date" name="date" value="">
							</form>
						</c:if>
					</c:when>
					<c:otherwise>
						Книгу уже читают
					</c:otherwise>
				</c:choose>
			</p>
        </div>
        <p class="mini-description-span">Краткое содержание:</p>
        <p class="mini-description"><c:out value="${book.description}"/></p>
    </div>
    
	<c:import url="\footer.jsp" />
	
</body>

</html>