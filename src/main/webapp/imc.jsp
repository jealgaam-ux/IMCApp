<%@page import="com.imc.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

if (usuario == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cálculo de IMC</title>
</head>
<body>

<h2>Calculadora de IMC</h2>

<p>Bienvenido: <b><%= usuario.getNombre() %></b></p>

<a href="logout">Cerrar sesión</a>

<br><br>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="calcularIMC" method="post">

    <label>Peso (kg):</label><br>
    <input type="number" step="0.1" name="peso" required>

    <br><br>

    <button type="submit">Calcular IMC</button>

</form>

<br>

<% if (request.getAttribute("resultadoIMC") != null) { %>

    <h3>Resultado</h3>

    <p>IMC: <b><%= request.getAttribute("resultadoIMC") %></b></p>

    <p>Clasificación: <b><%= request.getAttribute("clasificacion") %></b></p>

<% } %>

<br>

<a href="historial.jsp">Ver historial</a>

</body>
</html>