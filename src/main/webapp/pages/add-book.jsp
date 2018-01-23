<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
    
	<c:import url="\header.jsp" />
	
	<div class="body-block">
        <h2 class="h2-title">Добавление книги:</h2>
        <form method="POST" action="/" class="add-book">
			<input type="hidden" name="command" value="add_book"/>
            <ul>
            <li><span>Название книги: </span><input type="text" name="title" value=""></li>
            <li><span>Автор: </span><input type="text" name="authors" value=""></li>
            <li><span>Жанр: </span><input type="text" name="genre" value=""></li>
			<li><span>Издательство: </span><input type="text" name="publisher" value=""></li>
			<li><span>Год издания: </span><input type="text" name="publishingYear" value=""></li>
            <li><span>Изображение: </span><input class="image-download" type="file"></li>
            <li><span>Краткое описание:</span><textarea name="description" id="" ></textarea></li>
            <input type="submit" value="Добавить" class="add-submit">
        </form>
    </div>
	
	<c:import url="\footer.jsp" />

</body>

</html>