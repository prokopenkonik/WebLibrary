<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

	<body>
		<%@include file="set-language.jsp"%>
		<ul class="books">
			<c:forEach var="book" items="${list}">
				<li>
				
					<c:if test="${not empty sessionScope.admin}">
						<div class="control-panel">							
							<form method="POST" action="">
								<input type="hidden" name="command" value="get_book_to_update"/>
								<input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
								<input class="edit" type="submit" value="" />
							</form>
							
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
					<p><fmt:message key="label.author" />
						<span>
						<c:forEach var="author" items="${book.authors}">
							<c:out value="${author.name}"/> <c:out value="${author.surname}"/></br>
						</c:forEach>
						</span>
					</p>
					<p class="genre"><fmt:message key="label.genre" /><c:out value="${book.genre}"/></p>
				</li>
			</c:forEach>
        </ul>

	</body>

</html>