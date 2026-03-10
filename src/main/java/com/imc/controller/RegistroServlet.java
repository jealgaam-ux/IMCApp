package com.imc.controller;

import com.imc.dao.UsuarioDAO;
import com.imc.model.Usuario;
import com.imc.util.PasswordUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String edadStr = request.getParameter("edad");
        String estaturaStr = request.getParameter("estatura");

        try {
            int edad = Integer.parseInt(edadStr);
            double estatura = Double.parseDouble(estaturaStr);

            if (edad < 15) {
                request.setAttribute("error", "La edad mínima permitida es 15 años.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            if (estatura < 1.0 || estatura > 2.5) {
                request.setAttribute("error", "La estatura debe estar entre 1.0 m y 2.5 m.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            UsuarioDAO dao = new UsuarioDAO();

            if (dao.existeCorreo(correo)) {
                request.setAttribute("error", "Ese correo ya está registrado.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
                return;
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setPassword(PasswordUtil.hashPassword(password));
            usuario.setEdad(edad);
            usuario.setEstatura(estatura);

            boolean registrado = dao.registrarUsuario(usuario);

            if (registrado) {
                request.setAttribute("mensaje", "Registro exitoso. Ahora inicia sesión.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No se pudo registrar el usuario.");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Edad o estatura inválida.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }
}