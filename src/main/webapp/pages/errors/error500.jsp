<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/pages/set-language.jsp"%>

<html>
	<title><fmt:message key="error" /></title>
	<body>
		<c:import url="..\header.jsp" />
		<div class="body-block">
			<h2 class="h2-title"><fmt:message key="error.server" /></h2>
		</div>
		<c:import url="..\footer.jsp" />
	</body>
</html>