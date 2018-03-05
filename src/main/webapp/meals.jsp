<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<c:if  test="${!empty meals}">
<table class="data" border="1" width="100%" cellpadding="5">
    <tr>
        <th>Время</th>
        <th>Описание</th>
        <th>Каллории</th>
    </tr>
    <%
        List<MealWithExceed> mealWithExceedList = (List<MealWithExceed>) request.getAttribute("meals");
        if (mealWithExceedList != null && !mealWithExceedList.isEmpty()){
            for (MealWithExceed m: mealWithExceedList
                 ) {
                out.println("<tr>");
                out.println("<td>" + m.getDateTime().toLocalDate() + " " + m.getDateTime().toLocalTime() + "</td>");
                out.println("<td>" + m.getDescription() + "</td>");
                if (m.isExceed()) {
                    out.println("<td>" + "<font color =\"red\">" + m.getCalories() + "</td>");
                } else out.println("<td>" + "<font color=\"green\">" + m.getCalories() + "</td>");
                out.println("</tr>");
            }
        }
    %>
</table>
</c:if>

</body>
</html>
