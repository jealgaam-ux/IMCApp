package com.imc.model;

public class IMCCalculadora {

    // Método para calcular el IMC
    public static double calcularIMC(double peso, double estatura) {
        return peso / (estatura * estatura);
    }

    // Método para clasificar el IMC
    public static String clasificarIMC(double imc) {

        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc >= 18.5 && imc < 25) {
            return "Normal";
        } else if (imc >= 25 && imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }
}