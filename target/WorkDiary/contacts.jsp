<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="io.github.thestigger.model.Contact" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 10/14/15
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
    <jsp:include page="includes/references.jsp"/>
</head>
<body>
    <%--Person Add/Edit logic--%>
    <c:if test="${error ne null}">
        <strong style="color: red;">
            <c:out value="${error}" />
        </strong>
    </c:if>
    <c:if test="${success ne null}">
        <strong style="color: green;">
            <c:out value="${success}" />
        </strong>
    </c:if>
    <c:url value="/addContact" var="addURL" />
    <c:url value="/editContact" var="editURL" />

    <%--Edit Request--%>
    <c:if test="${contact ne null}">
        <form action='<c:out value="${editURL}"/>' method="post">
            ID: <input type="text" value="${contact.id}" readonly="readonly" name="id"><br/>
            Name: <input type="text" value="${contact.name}" name="name"><br/>
            Surname: <input type="text" value="${contact.surname}" name="surname"><br/>
            Phone number: <input type="text" value="${contact.phoneNumber}" name="phoneNumber"><br/>
            Email: <input type="text" value="${contact.email}" name="email"><br/>
            <input type="submit" value="Edit Person">
        </form>
    </c:if>

    <%--Add Request--%>
    <c:if test="${contact eq null}">
        <form action='<c:out value="${addURL}"/>' method="post">
            Name: <input type="text" name="name"><br/>
            Surname: <input type="text" name="surname"><br/>
            PhoneNumber: <input type="text" name="phoneNumber"><br/>
            Email: <input type="text" name="email"><br/>
            <input type="submit" value="Add Person">
        </form>
    </c:if>

    <%--Contact List logic--%>
    <c:if test="${not empty contacts}">
        <table>
            <tbody>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>PhoneNumber</th>
                <th>email</th>
            </tr>
            <c:forEach items="${contacts}" var="contact">
                <c:url value="/editContact" var="editURL">
                    <c:param name="id" value="${contact.id}"/>
                </c:url>
                <c:url value="/deleteContact" var="deleteURL">
                    <c:param name="id" value="${contact.id}"/>
                </c:url>
                <tr>
                    <td><c:out value="${contact.id}"/></td>
                    <td><c:out value="${contact.name}"/></td>
                    <td><c:out value="${contact.surname}"/></td>
                    <td><c:out value="${contact.phoneNumber}"/></td>
                    <td><c:out value="${contact.email}"/></td>
                    <td><a href='<c:out value="${editURL}" escapeXml="true" />'>Edit</a></td>
                    <td><a href='<c:out value="${deleteURL}" escapeXml="true" />'>Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
