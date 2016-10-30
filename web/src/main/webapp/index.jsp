<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="dialogs" />

<html lang=${language}>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="index.mainLabel"/></title>
</head>
<body>
<h1><fmt:message key="index.mainLabel"/></h1>
<table>
   <tr>
       <td>
           <form action="/" method="get">
               <select id="language" name="language" onchange="submit()" value=${language}>
                   <option value="en" ${language == 'en_EN' ? 'selected' : ''}>English</option>
                   <option value="ru" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
               </select>
           </form>
       </td>
   </tr>
    <tr>
    <td>
        <jsp:include page="/login.jsp"/>
    </td>

    <tr>
    <tr>
        <td>
            <a href="/catalog/"><fmt:message key="index.catalogPageLabel"/></a>
        </td>
    </tr>

    <tr>
        <td>
            <a href="/userpages/adminpage.jsp"><fmt:message key="index.adminPageLabel" /></a>
        </td>
    </tr>

    <tr>
        <td>
            <a href="/userpages/userpage.jsp"><fmt:message key="index.userPageLabel"/></a>
        </td>
    </tr>

    <tr>
        <td>
            <a href="/userpages/guestpage.jsp"><fmt:message key="index.guestPageLabel"/></a>
        </td>
    </tr>


</table>





</body>
</html>