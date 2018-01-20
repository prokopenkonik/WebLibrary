<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

	<body>

		<ul class="books">
			<c:forEach var="book" items="${list}">
				<li>
				
					<c:if test="${not empty sessionScope.admin}">
						<div class="control-panel">
							<a class="edit-href" href="pages/edit-book.jsp"><img class="edit" src="../images/edit.png" alt=""></a>
							<form method="POST" action="">
								<input type="hidden" name="command" value="delete_book"/>
								<input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
								<input class="delete" type="submit" value="" />
							</form>
						</div>
					</c:if>
				
					<form method="POST" action="">
						<input type="hidden" name="command" value="get_book"/>
						<input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
						<input class="book-title" type="submit" value="<c:out value="${book.title}"/>" />
					</form>

					<img src="../images/kniga.jpg" alt="image">
					<p>Автор:
						<span>
						<c:forEach var="author" items="${book.authors}">
							<br><c:out value="${author.name}"/> <c:out value="${author.surname}"/>
						</c:forEach>
						</span>
					</p>
					<p class="genre">Жанр: <c:out value="${book.genre}"/></p>
				</li>
			</c:forEach>
        </ul>

	</body>

</html>