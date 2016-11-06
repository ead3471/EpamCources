<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="dialogs" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="device.insert.title"/></title>
</head>
<body>
<table border="1px">
    <tr>
        <th><fmt:message key="devices.head.type"/></th>
        <th><fmt:message key="devices.head.serial"/></th>
        <th> <fmt:message key="devices.head.location"/> </th>
        <th> <fmt:message key="devices.head.lastVerification"/> </th>
        <th> <fmt:message key="devices.head.nextVerification"/> </th>
        <th> <fmt:message key="device.insert.insert"/></th>
    </tr>

    <tr><form action="/userpages/edit/device/" method="post">

        <td><input name="name" type="text" ></td>
        <td><input name="serial" type="text"></td>
        <td><input name="mountPlace" type="text"></td>
        <td><input name="lastVerificationDate" type="text"></td>
        <td><input name="nextVerificationDate"type="text" ></td>
        <td>
            <input type="submit" name=insert value="Вставить">
        </td>
    </form>
    </tr>

</table>

<a href="/catalog/">Back to Catalog</a>
</body>
</html>


</body>
</html>
