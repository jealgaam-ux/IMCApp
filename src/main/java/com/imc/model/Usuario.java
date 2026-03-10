package com.imc.model;

public class Usuario {

    private int idUser;
    private String nombre;
    private String correo;
    private String password;
    private int edad;
    private double estatura;

    public Usuario() {
    }

    public Usuario(int idUser, String nombre, String correo, String password, int edad, double estatura) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.edad = edad;
        this.estatura = estatura;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }
}