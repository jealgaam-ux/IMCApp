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
    <title>Historial IMC</title>
</head>
<body>

<h2>Historial de IMC</h2>

<p>Usuario: <b><%= usuario.getNombre() %></b></p>

<a href="imc.jsp">Volver</a>
<br><br>

<table border="1" cellpadding="8" id="tablaHistorial">
    <thead>
        <tr>
            <th>ID</th>
            <th>Peso</th>
            <th>Estatura</th>
            <th>IMC</th>
            <th>Clasificación</th>
            <th>Fecha</th>
        </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<script>
    fetch('<%= request.getContextPath() %>/api/historial')
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector("#tablaHistorial tbody");
            tbody.innerHTML = "";

            data.forEach(registro => {
                let fila = "<tr>" +
                    "<td>" + registro.idRegistro + "</td>" +
                    "<td>" + registro.peso + "</td>" +
                    "<td>" + registro.estatura + "</td>" +
                    "<td>" + Number(registro.imc).toFixed(2) + "</td>" +
                    "<td>" + registro.clasificacion + "</td>" +
                    "<td>" + registro.fechaRegistro + "</td>" +
                    "</tr>";

                tbody.innerHTML += fila;
            });
        })
        .catch(error => {
            console.error("Error al cargar historial:", error);
        });
</script>

</body>
</html>