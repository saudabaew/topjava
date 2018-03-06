<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обновить</title>
</head>
<body>

<h3><a href="${pageContext.request.contextPath}/meals">Meals</a></h3>

<form method="post">
    <label>ID:<br>
        <input type="number" name="id" disabled="disabled" value="${meal.getId()}"><br />
    </label>

    <label>Время:<br>
        <input type="datetime-local" name="date" value="${meal.getDateTime().toLocalDate()}"><br />
    </label>

    <label>Описание:<br>
        <input type="text" name="description" value="${meal.getDescription()}"><br />
    </label>

    <label>Каллории:<br>
        <input type="number" name="calories" value="${meal.getCalories()}"><br />
    </label><br>
    <button type="submit">Обновить</button>
</form>

</body>
</html>
