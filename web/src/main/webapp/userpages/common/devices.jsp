<%@ page import="model.Device" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registered devices</title>
    <jsp:useBean id="devices" class="java.util.ArrayList" scope="request"/>
</head>
<body>
<h1>Registered devices</h1>
<table border="1px">
    <tr>
        <th>Device Type</th>
        <th> Serial </th>
        <th> Location </th>
        <th> Next Verification before </th>
        <th> Next Verification after</th>
        <th>  </th>
    </tr>
    <tr>
        <form action=/catalog/>
            <td><input name="name"  type="text"/></td>
            <td><input name="serial"  type="text"/> </td>
            <td> <input name="mountPlace" type="text"/> </td>
            <td> <input name="nextVerificationBefore" type="text"/> </td>
            <td> <input name="nextVerificationAfter" type="text"/> </td>
            <td> <input type="submit" value="Use filter"/> </td>
        </form>>
    </tr>


    <tr>
        <th>Device Type</th>
        <th> Serial </th>
        <th> Location </th>
        <th> Last Verification </th>
        <th> Next Verification </th>
        <th> Edit </th>
     </tr>


<% for (Device device: (List<Device>) devices) {%>

<tr>
    <td><%=device.getName()%></td>
    <td><%=device.getSerialNumber()%></td>
    <td><%=device.getMountPlace()%></td>
    <td><%=device.getDateOfLastVerification()%></td>
    <td><%=device.getDateOfNextVerification()%></td>
    <td>
        <a href="/catalog/?serial=<%=device.getSerialNumber()%>&forward=/userpages/common/deviceEdit.jsp">Edit</a>
        <a href="/userpages/remove/?serial=<%=device.getSerialNumber()%>">Remove</a>
    </td>
</tr>
<%}
%>
</table>

</body>
</html>

