<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
    
	<c:import url="\header.jsp" />
	
	<div class="body-block">
        <h2 class="h2-title">Редактирование книги:</h2>
        <form method="POST" action="" class="add-book">
            <ul>
            <li><span>Название книги: </span><input type="text"></li>
            <li><span>Автор: </span><input type="text"></li>
            <li><span>Жанр: </span><input type="text"></li>
            <li><span>Изображение: </span><input class="image-download" type="file"></li>
            <li><span>Количество: </span><input type="text"></li>
            <li><span>Краткое описание:</span><textarea name="" id=""></textarea></li>
            <input type="submit" value="Редактировать" class="edit-submit">
        </form>
    </div>
	
	<c:import url="\footer.jsp" />
	
</body>

</html>