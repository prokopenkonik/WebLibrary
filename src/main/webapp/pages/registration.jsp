<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>

    <c:import url="\header.jsp" />
	
    <div class="body-block">
        <h2 class="h2-title">Регистрация</h2>
        <form method="POST" id="form_reg" action="/">
			<input type="hidden" name="command" value="registration"/>
            <p id="reg_message"></p>
            <div id="block-form-registration">
                <ul id="form-registration">
                    <li>
                        <label>Логин</label>
                        <span class="star" >*</span>
                        <input type="text" name="login" id="reg_login" />
                    </li>
                    <li>
                        <label>Пароль</label>
                        <span class="star" >*</span>
                        <input type="password" name="pass" id="reg_pass" />
                        <%--<span id="genpass">Сгенерировать</span>--%>
                    </li>
					<li>
                        <label>Имя</label>
                        <span class="star" >*</span>
                        <input type="text" name="name" id="reg_name" />
                    </li>
                    <li>
                        <label>Фамилия</label>
                        <span class="star" >*</span>
                        <input type="text" name="surname" id="reg_surname" />
                    </li>
                    <li>
                        <label>E-mail</label>
                        <span class="star" >*</span>
                        <input type="text" name="mail" id="reg_email" />
                    </li>
                    <li>
                        <label>Мобильный телефон</label>
                        <span class="star" >*</span>
                        <input type="text" name="phone" id="reg_phone" />
                    </li>
                </ul>
            </div>
            <p><input type="submit" name="reg_submit" id="form_submit" value="Регистрация" /></p>
        </form>
    </div>
	
    <c:import url="\footer.jsp" />
	
</body>

</html>