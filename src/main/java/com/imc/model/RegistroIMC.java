package com.imc.model;

public class RegistroIMC {

    private int idRegistro;
    private int idUser;
    private double peso;
    private double estatura;
    private double imc;
    private String clasificacion;
    private String fechaRegistro;

    public RegistroIMC() {
    }

    public RegistroIMC(int idRegistro, int idUser, double peso, double estatura, double imc, String clasificacion, String fechaRegistro) {
        this.idRegistro = idRegistro;
        this.idUser = idUser;
        this.peso = peso;
        this.estatura = estatura;
        this.imc = imc;
        this.clasificacion = clasificacion;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}