<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>

    <c:import url="\header.jsp" />
	
    <div class="body-block">
        <h2 class="h2-title">Регистрация</h2>
        <form method="post" id="form_reg" action="">
            <p id="reg_message"></p>
            <div id="block-form-registration">
                <ul id="form-registration">
                    <li>
                        <label>Логин</label>
                        <span class="star" >*</span>
                        <input type="text" name="reg_login" id="reg_login" />
                    </li>
                    <li>
                        <label>Пароль</label>
                        <span class="star" >*</span>
                        <input type="text" name="reg_pass" id="reg_pass" />
                        <span id="genpass">Сгенерировать</span>
                    </li>
                    <li>
                        <label>Фамилия</label>
                        <span class="star" >*</span>
                        <input type="text" name="reg_surname" id="reg_surname" />
                    </li>
                    <li>
                        <label>Имя</label>
                        <span class="star" >*</span>
                        <input type="text" name="reg_name" id="reg_name" />
                    </li>
                    <li>
                        <label>Отчество</label>
                        <span class="star" >*</span>
                        <input type="text" name="reg_patronymic" id="reg_patronymic" />
                    </li>
                    <li>
                        <label>E-mail</label>
                        <span class="star" >*</span>
                        <input type="text" name="reg_email" id="reg_email" />
                    </li>
                    <li>
                        <label>Мобильный телефон</label>
                        <span class="star" >*</span>
                        <input type="text" name="reg_phone" id="reg_phone" />
                    </li>
                </ul>
            </div>
            <p><input type="submit" name="reg_submit" id="form_submit" value="Регистрация" /></p>
        </form>
    </div>
	
    <c:import url="\footer.jsp" />
	
</body>

</html>