<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
    
	<c:import url="\header.jsp" />

    <div class="body-block">
        <img class="book-full-img" src="../images/kniga.jpg" alt="image">
        <div class="book-info">
            <p>Название: <c:out value="${book.title}"/></p>
            <p>Жанр: <c:out value="${book.genre}"/></p>
            <p>Книг в наличии: <c:out value="${book.copiesCount}"/></p>
            <p class="hire-book">Заказать</p>
        </div>
        <p class="mini-description-span">Краткое содержание:</p>
        <p class="mini-description"><c:out value="${book.description}"/></p>
    </div>
    
	<c:import url="\footer.jsp" />
	
</body>

</html>