package com.imc.controller;

import com.imc.dao.RegistroIMCDAO;
import com.imc.model.IMCCalculadora;
import com.imc.model.RegistroIMC;
import com.imc.model.Usuario;
import java.io.IOException;
import java.text.DecimalFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calcularIMC")
public class CalculoIMCServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String pesoStr = request.getParameter("peso");

        try {

            double peso = Double.parseDouble(pesoStr);

            if (peso <= 0) {
                request.setAttribute("error", "El peso debe ser mayor que 0.");
                request.getRequestDispatcher("imc.jsp").forward(request, response);
                return;
            }

            double estatura = usuario.getEstatura();

            double imc = IMCCalculadora.calcularIMC(peso, estatura);
            String clasificacion = IMCCalculadora.clasificarIMC(imc);

            RegistroIMC registro = new RegistroIMC();
            registro.setIdUser(usuario.getIdUser());
            registro.setPeso(peso);
            registro.setEstatura(estatura);
            registro.setImc(imc);
            registro.setClasificacion(clasificacion);

            RegistroIMCDAO dao = new RegistroIMCDAO();
            boolean guardado = dao.guardarRegistro(registro);

            DecimalFormat df = new DecimalFormat("#.##");

            request.setAttribute("resultadoIMC", df.format(imc));
            request.setAttribute("clasificacion", clasificacion);
            request.setAttribute("guardado", guardado);

            request.getRequestDispatcher("imc.jsp").forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute("error", "Peso inválido.");
            request.getRequestDispatcher("imc.jsp").forward(request, response);
        }
    }
}