<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="dialogs" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="guest.title"/> </title>
</head>
<body>
<h1> <fmt:message key="guest.title"/></h1>
<br>
<fmt:message key="guest.hello"/> ${sessionScope.user.userName}

<jsp:include page="/logout.jsp"/>
</body>
</html>
