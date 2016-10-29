<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
</head>
<body>

<form action="<%=(String)request.getAttribute("userAfterLoginUrl")%>" method="post">
<%  if(session.getAttribute("login_msg")!=null)
            out.write((String)session.getAttribute("login_msg"));%>
    <table >
        <tr><th>Login please</th><tr>
        <tr>
            <td>Login</td>
            <td><input type="text" name ="user"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="login!">
            </td>
        </tr>

    </table>
</form>

</body>
</html>