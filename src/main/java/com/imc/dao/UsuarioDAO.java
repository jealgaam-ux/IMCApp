package com.imc.dao;

import com.imc.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, correo, password, edad, estatura) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getEdad());
            ps.setDouble(5, usuario.getEstatura());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT id_user FROM usuarios WHERE correo = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al verificar correo: " + e.getMessage());
            return false;
        }
    }

    public Usuario login(String correo, String password) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUser(rs.getInt("id_user"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setEstatura(rs.getDouble("estatura"));

                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }

        return null;
    }
}