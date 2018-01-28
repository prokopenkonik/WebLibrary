<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<body>
    
	<c:import url="\header.jsp" />
	<%@include file="set-language.jsp"%>

	<div class="body-block">
        <h2 class="h2-title"><fmt:message key="label.book.add" /></h2>
        <form method="POST" action="/" class="add-book">
			<input type="hidden" name="command" value="add_book"/>
            <ul>
            <li><span><fmt:message key="label.title" /></span><input type="text" name="title" value=""></li>
            <li><span><fmt:message key="label.author" /></span><input type="text" name="authors" value=""></li>
            <li><span><fmt:message key="label.genre" /></span><input type="text" name="genre" value=""></li>
			<li><span><fmt:message key="label.publisher" /></span><input type="text" name="publisher" value=""></li>
			<li><span><fmt:message key="label.publishing.year" /></span><input type="text" name="publishingYear" value=""></li>
            <li><span><fmt:message key="label.description" /></span><textarea name="description" id="" ></textarea></li>
            <input type="submit" value="<fmt:message key="button.book.add.accept" />" class="add-submit">
        </form>
    </div>
	
	<c:import url="\footer.jsp" />

</body>

</html>