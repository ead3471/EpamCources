<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="dialogs" />

<%@ page import="model.Device" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="devices.title"/></title>
    <jsp:useBean id="devices" class="java.util.ArrayList" scope="request"/>
</head>
<body>
<h1><fmt:message key="devices.title"/></h1>
<table border="1px">
    <tr>
        <th><fmt:message key="devices.head.type"/></th>
        <th> <fmt:message key="devices.head.serial"/></th>
        <th> <fmt:message key="devices.head.location"/> </th>
        <th> <fmt:message key="devices.head.nextVerificationBefore"/> </th>
        <th> <fmt:message key="devices.head.nextVerificationAfter"/></th>
        <th>  </th>
    </tr>
    <tr>
        <form action=/catalog/>
            <td><input name="name"  type="text"/></td>
            <td><input name="serial"  type="text"/> </td>
            <td> <input name="mountPlace" type="text"/> </td>
            <td> <input name="nextVerificationBefore" type="text"/> </td>
            <td> <input name="nextVerificationAfter" type="text"/> </td>
            <td> <input type="submit" value=<fmt:message key="devices.head.useFilter"/>/> </td>
        </form>>
    </tr>

    <tr>
        <th><fmt:message key="devices.head.type"/></th>
        <th><fmt:message key="devices.head.serial"/></th>
        <th> <fmt:message key="devices.head.location"/> </th>
        <th> <fmt:message key="devices.head.lastVerification"/> </th>
        <th> <fmt:message key="devices.head.nextVerification"/> </th>
        <th> <fmt:message key="devices.head.edit"/></th>
     </tr>


<% for (Device device: (List<Device>) devices) {%>

<tr>
    <td><%=device.getName()%></td>
    <td><%=device.getSerialNumber()%></td>
    <td><%=device.getMountPlace()%></td>
    <td><%=device.getDateOfLastVerification()%></td>
    <td><%=device.getDateOfNextVerification()%></td>
    <td>
        <a href="/catalog/?serial=<%=device.getSerialNumber()%>&forward=/userpages/common/deviceEdit.jsp"><fmt:message key="devices.head.edit"/></a>
    </td>
</tr>
<%}%>

</table>
<c:if test="${fn:length(devices) gt 1}">
<a href="/catalog/?startColumn=serial&minValue=${devices[fn:length(devices)-1].serialNumber}"><fmt:message key="devices.nextPage"/></a>
</c:if>


<a href="/userpages/common/insertDevice.jsp"><fmt:message key="devices.addNew"/></a>
</body>
</html>

