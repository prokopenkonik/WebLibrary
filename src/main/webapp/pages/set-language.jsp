<c:set var="language" value="${empty sessionScope.language ? pageContext.request.locale : sessionScope.language}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="pagecontext" />