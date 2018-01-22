<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
    
	<c:import url="\header.jsp" />
	
	<div class="body-block">
        <h2 class="h2-title">Редактирование книги:</h2>
        <form method="POST" action="" class="add-book">
			<input type="hidden" name="command" value="edit_book"/>
			<input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
            <ul>
            <li><span>Название книги: </span><input type="text" name="title" value="<c:out value="${book.title}"/>"></li>
            <li><span>Автор: </span><input type="text" name="authors" value="<c:out value="${book.getAuthorsAsString()}"/>"></li>
            <li><span>Жанр: </span><input type="text" name="genre" value="<c:out value="${book.genre}"/>"></li>
			<li><span>Издательство: </span><input type="text" name="publisher" value="<c:out value="${book.publisher}"/>"></li>
			<li><span>Год издания: </span><input type="text" name="publishingYear" value="<c:out value="${book.publishingYear}"/>"></li>
            <li><span>Изображение: </span><input class="image-download" type="file"></li>
            <li><span>Краткое описание:</span><textarea name="description" id="" ><c:out value="${book.description}"/></textarea></li>
            <input type="submit" value="Редактировать" class="edit-submit">
        </form>
    </div>
	
	<c:import url="\footer.jsp" />

</body>

</html>