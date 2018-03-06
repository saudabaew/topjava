
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<h3><a href="${pageContext.request.contextPath}/meals">Meals</a></h3>
<form method="post">
    <label>Время:<br>
        <input type="datetime-local" name="date"><br />
    </label>

    <label>Описание:<br>
        <input type="text" name="description"><br />
    </label>

    <label>Каллории:<br>
        <input type="number" name="calories"><br />
    </label><br>
    <button type="submit">Добавить</button>
</form>

</body>
</html>
