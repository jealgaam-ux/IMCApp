package com.imc.controller;

import com.imc.dao.RegistroIMCDAO;
import com.imc.model.RegistroIMC;
import com.imc.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/historial")
public class HistorialRestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            if (usuario == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.print("{\"error\":\"Usuario no autenticado\"}");
                return;
            }

            RegistroIMCDAO dao = new RegistroIMCDAO();
            List<RegistroIMC> historial = dao.obtenerHistorialPorUsuario(usuario.getIdUser());

            StringBuilder json = new StringBuilder();
            json.append("[");

            for (int i = 0; i < historial.size(); i++) {
                RegistroIMC r = historial.get(i);

                json.append("{")
                    .append("\"idRegistro\":").append(r.getIdRegistro()).append(",")
                    .append("\"peso\":").append(r.getPeso()).append(",")
                    .append("\"estatura\":").append(r.getEstatura()).append(",")
                    .append("\"imc\":").append(r.getImc()).append(",")
                    .append("\"clasificacion\":\"").append(r.getClasificacion()).append("\",")
                    .append("\"fechaRegistro\":\"").append(r.getFechaRegistro()).append("\"")
                    .append("}");

                if (i < historial.size() - 1) {
                    json.append(",");
                }
            }

            json.append("]");
            out.print(json.toString());
        }
    }
}