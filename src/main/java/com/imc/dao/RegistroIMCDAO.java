package com.imc.dao;

import com.imc.model.RegistroIMC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistroIMCDAO {

    public boolean guardarRegistro(RegistroIMC registro) {
        String sql = "INSERT INTO registros_imc(id_user, peso, estatura, imc, clasificacion) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, registro.getIdUser());
            ps.setDouble(2, registro.getPeso());
            ps.setDouble(3, registro.getEstatura());
            ps.setDouble(4, registro.getImc());
            ps.setString(5, registro.getClasificacion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al guardar registro IMC: " + e.getMessage());
            return false;
        }
    }

    public List<RegistroIMC> obtenerHistorialPorUsuario(int idUser) {
        List<RegistroIMC> lista = new ArrayList<>();

        String sql = "SELECT * FROM registros_imc WHERE id_user = ? ORDER BY fecha_registro DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUser);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RegistroIMC registro = new RegistroIMC();
                registro.setIdRegistro(rs.getInt("id_registro"));
                registro.setIdUser(rs.getInt("id_user"));
                registro.setPeso(rs.getDouble("peso"));
                registro.setEstatura(rs.getDouble("estatura"));
                registro.setImc(rs.getDouble("imc"));
                registro.setClasificacion(rs.getString("clasificacion"));
                registro.setFechaRegistro(rs.getString("fecha_registro"));

                lista.add(registro);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener historial IMC: " + e.getMessage());
        }

        return lista;
    }
}