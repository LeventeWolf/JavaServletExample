<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="MainServlet" />

<html>
<head>
    <title>Kisallatok</title>
    <link href="./style.css" rel="stylesheet" />
</head>

<body>

<div id="main">
    <h2>Kisállatok</h2>

    <table rules="all">
        <thead>
        <tr>
            <th>Nev</th>
            <th>Fajta</th>
            <th>Kor</th>
            <th>Modosit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="pet" items="${requestScope.pets}">
            <tr>
                <td>${pet.nev}</td>
                <td>${pet.fajta}</td>
                <td>${pet.kor}</td>
                <td><a href="${pageContext.request.contextPath}/update.jsp?id=${pet.id}">frissit</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <form action="${pageContext.request.contextPath}/index.jsp"} method="get">
        <label>
            <select name="selectedFajta">
                <c:forEach var="fajta" items="${requestScope.fajtak}">
                    <option value="${fajta}">${fajta}</option>
                </c:forEach>
            </select>
            <button type="submit">Szures</button>
        </label>
    </form>

    <a href="new.jsp">
        Új állat
    </a>

</div>



</body>
</html>
