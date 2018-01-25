<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
    
	<c:import url="\header.jsp" />

    <div class="body-block">
        <aside>
            <span class="filter-span">Сортировать по:</span>
                <span class="filter-name">Автор</span>
                	<ul class="authors">
						<c:forEach var="author" items="${authors}">
							<li>
								<form method="POST" action="">
									<input type="hidden" name="command" value="get_books_by_author"/>
									<input type="hidden" name="author_id" value="<c:out value="${author.id}"/>"/>
									<input type="submit" class="author_name" value="<c:out value="${author}"/>" />
								</form>
							</li>
						</c:forEach>
                	</ul>
                <span class="filter-name">Жанр</span>
					<ul class="janr">
						<c:forEach var="genre" items="${genres}">
							<li>
								<form method="POST" action="">
									<input type="hidden" name="command" value="get_books_by_genre"/>
									<input type="submit" class="author_name" name="genre" value="<c:out value="${genre}"/>" />
								</form>
							</li>
						</c:forEach>
					</ul>
        </aside>
        
		<c:import url="\booklist.jsp" />
		
		 <div class="pstrnav">
			<ul>
				<li><a class="pstr-prev" href="">&lt;</a></li>
				<li><a class="pstr-active" href="">1</a></li>
				<li><a class="pstr-active" href="">2</a></li>
				<li><a class="pstr-active" href="">3</a></li>
				<li><a class="pstr-active" href="">4</a></li>
				<li><a class="pstr-active" href="">5</a></li>
				<li><a class="pstr-active" href="">6</a></li>
				<li><a class="pstr-next" href="">&gt;</a></li>
			</ul>
		</div>
    </div>
    
	<c:import url="\footer.jsp" />
	
</body>

</html>