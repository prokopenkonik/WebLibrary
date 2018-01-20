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
                		<a href=""><li>Имя автора с базы</li></a>
                		<a href=""><li>Имя автора с базы</li></a>
                		<a href=""><li>Имя автора с базы</li></a>
                		<a href=""><li>Имя автора с базы</li></a>
                		<a href=""><li>Имя автора с базы</li></a>
                	</ul>
                <span class="filter-name">Жанр</span>
					<ul class="janr">
						<a href=""><li>Жанр с базы</li></a>
						<a href=""><li>Жанр с базы</li></a>
						<a href=""><li>Жанр с базы</li></a>
						<a href=""><li>Жанр с базы</li></a>
						<a href=""><li>Жанр с базы</li></a>
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