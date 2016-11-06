<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="dialogs" />


<%@ page import="model.Device" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="device.edit.title"/></title>
    <jsp:useBean id="devices" class="java.util.ArrayList" scope="request"/>
</head>
<body>
<h1>Edit device</h1>
<table border="1px">
    <tr>
        <th><fmt:message key="devices.head.type"/></th>
        <th><fmt:message key="devices.head.serial"/></th>
        <th> <fmt:message key="devices.head.location"/> </th>
        <th> <fmt:message key="devices.head.lastVerification"/> </th>
        <th> <fmt:message key="devices.head.nextVerification"/> </th>
        <th> <fmt:message key="devices.head.edit"/></th>
    </tr>
    <% for (Device device: (List<Device>) devices) {%>
    <tr><form action="/userpages/edit/device/" method="post">
        <td><input name="name" type="text" readonly value="<%=device.getName()%>"></td>
        <td><input name="serial" type="text" readonly value="<%=device.getSerialNumber()%>"></td>
        <td><input name="mountPlace" type="text" value="<%=device.getMountPlace()%>"></td>
        <td><input name="lastVerificationDate" type="text" value="<%=device.getDateOfLastVerification()%>"></td>
        <td><input name="nextVerificationDate"type="text" value="<%=device.getDateOfNextVerification()%>"></td>
        <td>
            <input type="submit" name=update value=<fmt:message key="device.edit.update"/>>>
            <input type="submit" name=delete value=<fmt:message key="device.edit.delete"/>>
        </td>
        </form>
    </tr>
    <%}
    %>
</table>

<a href="/catalog/"><fmt:message key="device.edit.update"/></a>
</body>
</html>
