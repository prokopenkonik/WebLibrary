<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<body>

    <c:import url="\header.jsp" />
	<%@include file="set-language.jsp"%>
    <div class="body-block">
	
        <h2 class="h2-title"><fmt:message key="label.registration" /></h2>
        <form method="POST" id="form_reg" action="/">
			<input type="hidden" name="command" value="registration"/>
            <p id="reg_message"></p>
            <div id="block-form-registration">
                <ul id="form-registration">
                    <li>
                        <label><fmt:message key="label.reg.login" /></label>
                        <span class="star" >*</span>
                        <input type="text" name="login" id="reg_login" />
                    </li>
                    <li>
                        <label><fmt:message key="label.reg.password" /></label>
                        <span class="star" >*</span>
                        <input type="password" name="pass" id="reg_pass" />
                    </li>
					<li>
                        <label><fmt:message key="label.reg.name" /></label>
                        <span class="star" >*</span>
                        <input type="text" name="name" id="reg_name" />
                    </li>
                    <li>
                        <label><fmt:message key="label.reg.surname" /></label>
                        <span class="star" >*</span>
                        <input type="text" name="surname" id="reg_surname" />
                    </li>
                    <li>
                        <label><fmt:message key="label.reg.mail" /></label>
                        <span class="star" >*</span>
                        <input type="text" name="mail" id="reg_email" />
                    </li>
                    <li>
                        <label><fmt:message key="label.reg.phone" /></label>
                        <span class="star" >*</span>
                        <input type="text" name="phone" id="reg_phone" />
                    </li>
                </ul>
            </div>
            <p><input type="submit" name="reg_submit" id="form_submit" value="<fmt:message key="button.registrate" />" /></p>
        </form>
    </div>
	
    <c:import url="\footer.jsp" />
	
</body>

</html>