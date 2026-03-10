<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
</head>
<body>

<h2>Registro de usuario</h2>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="registro" method="post">

    <label>Nombre:</label><br>
    <input type="text" name="nombre" required>
    <br><br>

    <label>Correo:</label><br>
    <input type="email" name="correo" required>
    <br><br>

    <label>Contraseña:</label><br>
    <input type="password" name="password" required>
    <br><br>

    <label>Edad:</label><br>
    <input type="number" name="edad" required>
    <br><br>

    <label>Estatura (metros):</label><br>
    <input type="number" step="0.01" name="estatura" required>
    <br><br>

    <button type="submit">Registrar</button>

</form>

<br>
<a href="login.jsp">Ir a Login</a>

</body>
</html>