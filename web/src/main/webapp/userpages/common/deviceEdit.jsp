<%@ page import="model.Device" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Devices</title>
    <jsp:useBean id="devices" class="java.util.ArrayList" scope="request"/>
</head>
<body>
<h1>Edit device</h1>
<table border="1px">
    <tr>
        <th>Device Type</th>
        <th> Serial </th>
        <th> Location </th>
        <th> Last Verification </th>
        <th> Next Verification </th>
        <th> Edit </th>
    </tr>
    <% for (Device device: (List<Device>) devices) {%>
    <tr><form action="/userpages/edit/device/?serial=<%=device.getSerialNumber()%>" method="post">
        <td><input disabled type="text" value="<%=device.getName()%>"></td>
        <td><input type="text" disabled value="<%=device.getSerialNumber()%>"></td>
        <td><input name="mountPlace" type="text" value="<%=device.getMountPlace()%>"></td>
        <td><input name="lastVerificationDate" type="text" value="<%=device.getDateOfLastVerification()%>"></td>
        <td><input name="nextVerificationDate"type="text" value="<%=device.getDateOfNextVerification()%>"></td>
        <td><input type="submit"></td>
        </form>
    </tr>
    <%}
    %>
</table>

<a href="/catalog/">Back to Catalog</a>
</body>
</html>
