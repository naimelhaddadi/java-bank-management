package gestion_bancaria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private final String nombre;
    private final String pais;
    private boolean abierto = true;
    private final List<Cuenta> cuentas = new ArrayList<>();

    public Banco(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public boolean isAbierto() {
        return abierto;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void operarTodas(Scanner sc) {
        for (Cuenta c : cuentas) {
            c.operar(sc);
        }
    }

    public void auditarTodas(String detalle) {
        for (Cuenta c : cuentas) {
            c.auditarOperacion(detalle);
        }
    }

    public void resumenGeneral() {
        System.out.println();
        System.out.println("==== RESUMEN " + nombre + " (" + pais + ") ====");
        for (Cuenta c : cuentas) {
            System.out.println(c.getResumen());
        }
        System.out.println("================================");
    }

    public void mostrarEstado() {
        System.out.println("Banco " + nombre + " (" + pais + "): " + (abierto ? "Abierto" : "Cerrado"));
    }

    public void cerrar() {
        this.abierto = false;
        System.out.println("Cerrando " + nombre + " (" + pais + ")...");
    }
}
