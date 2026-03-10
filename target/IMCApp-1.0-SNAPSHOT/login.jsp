<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h2>Iniciar sesión</h2>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<% if (request.getAttribute("mensaje") != null) { %>
    <p style="color:green;"><%= request.getAttribute("mensaje") %></p>
<% } %>

<form action="login" method="post">

    <label>Correo:</label><br>
    <input type="email" name="correo" required>
    <br><br>

    <label>Contraseña:</label><br>
    <input type="password" name="password" required>
    <br><br>

    <button type="submit">Entrar</button>

</form>

<br>
<a href="registro.jsp">Crear cuenta</a>

</body>
</html>