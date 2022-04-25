<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="UpdateServlet" />

<jsp:useBean id="pet" scope="request" type="hu.alkfejl.model.bean.Kisallat"/>

<html>
<head>
    <title>Kisallatok</title>
    <link href="./style.css" rel="stylesheet" />
</head>

<body>

<div id="main">
    <h2>Állat módosítása</h2>
    <form action="${pageContext.request.contextPath}/UpdateServlet"} method="post" class="new-form">
        <table >
            <tr>
                <td>Nev</td>
                <td><input type="text" name="nev" required value="${pet.nev}" /></td>
            </tr>
            <tr>
                <td>Fajta</td>
                <td>
                    <label>
                        <select name="selectedFajta">
                            <c:forEach var="fajta" items="${requestScope.fajtak}">
                                <option value="${fajta}">${fajta}</option>
                            </c:forEach>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Kor</td>
                <td><input type="text" name="kor" required  value="${pet.kor}" /></td>
            </tr>
        </table>

        <input type="hidden" name="id" value="${pet.id}" />

        <button type="submit">
            Mentes
        </button>
    </form>

    <a href="index.jsp">
        Vissza
    </a>

</div>



</body>
</html>
