<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<c:if test="${!empty meals}">
    <table class="data" border="1" width="100%" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>

        <c:forEach items="${meals}" var="m">
            <tr>
                <td>${m.getId()}</td>
                <td>${m.getDateTime().toString()}</td>
                <td>${m.getDescription()}</td>
                <td style="${m.isExceed() eq true ? 'color: red':'color: green'}">${m.getCalories()}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/update" method="get">
                        <input type="hidden" name="id" value="${m.getId()}">
                        <input type="submit" value="Редактировать" style="float:left">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/delete" method="post">
                        <input type="hidden" name="id" value="${m.getId()}">
                        <input type="submit" value="Удалить" style="float:left">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<br><a href="${pageContext.request.contextPath}/addMeal">Добавить еду</a>

</body>
</html>
