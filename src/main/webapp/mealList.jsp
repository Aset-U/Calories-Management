<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<table class="table table-striped display" id="datatable">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>

    <c:forEach var="meal" items="${meals}" varStatus="iter">

    <c:if test="${meal.exceed}">
        <tr bgcolor="red">
            <td><p>${meal.dateTime}</p></td>
            <td><p>${meal.description}</p></td>
            <td><p>${meal.calories}</p></td>
        </tr>
    </c:if>
    <c:if test="${!meal.exceed}">
        <tr bgcolor="aqua">
            <td><p>${meal.dateTime}</p></td>
            <td><p>${meal.description}</p></td>
            <td><p>${meal.calories}</p></td>
        </tr>
    </c:if>

</c:forEach>
</table>

</body>
</html>
