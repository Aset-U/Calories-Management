<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
           display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>

<body>
    <section>
        <h2><a href="">Home</a></h2>
        <h3>Edit meal</h3>
        <hr>
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.UserMeal" scope="request"/>
        <form action="meals" method="post">
            <input type="hidden" name="id" value="${meal.id}">
            <dl>
                <dt>DataTime:</dt>
                <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}"></dd>
            </dl>
            <dl>
                <dt>Description:</dt>
                <dd><input type="text" name="description" value="${meal.description}" size="40"></dd>
            </dl>
            <dl>
                <dt>Calories:</dt>
                <dd><input type="number" name="calories" value="${meal.calories}"></dd>
            </dl>
            <button type="submit">Save</button>
            <button onclick="window.history.back()">Cancel</button>
        </form>
    </section>
</body>
</html>