package gestion_bancaria;

import java.util.Scanner;

public abstract class Cuenta implements Auditable {

    protected final String titular;
    protected double saldo;
    protected final Registro registro;
    protected String ultimaAuditoria;

    protected Cuenta(String titular, double saldo, String sucursal, String ultimaAuditoria) {
        this.titular = titular;
        this.saldo = saldo;
        this.registro = new Registro(sucursal);
        this.ultimaAuditoria = ultimaAuditoria;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public Registro getRegistro() {
        return registro;
    }

    public String getUltimaAuditoria() {
        return ultimaAuditoria;
    }

    // Metodos abstractos que cada subclase debe sobrescribir
    // (segun el enunciado del ejercicio).
    public abstract void operar(Scanner sc);

    @Override
    public abstract void auditarOperacion(String detalle);

    @Override
    public abstract String getResumen();

    // Hook que pueden modificar las subclases (ej. CuentaAhorro
    // aplica un interes a cada ingreso).
    protected double aplicarBeneficio(double cantidad) {
        return cantidad;
    }

    public void ingresar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("ERROR: cantidad invalida (debe ser > 0).");
            return;
        }
        double total = aplicarBeneficio(cantidad);
        this.saldo += total;
        registro.mover();
        System.out.printf("Ingresado: %.2f | Saldo: %.2f%n", total, this.saldo);
    }

    public void retirar(double cantidad) {
        if (cantidad <= 0) {
            System.out.println("ERROR: cantidad invalida (debe ser > 0).");
            return;
        }
        if (cantidad > this.saldo) {
            System.out.println("ERROR: saldo insuficiente.");
            return;
        }
        this.saldo -= cantidad;
        registro.mover();
        System.out.printf("Retirado: %.2f | Saldo: %.2f%n", cantidad, this.saldo);
    }

    public void mostrarInfo() {
        System.out.println("Titular: " + titular + " | Saldo: " + saldo);
        System.out.println("Registrado en " + registro);
    }

    // Helper compartido para no duplicar el bucle del menu en
    // las tres subclases. Cada subclase decide su titulo.
    protected void menuOperaciones(Scanner sc, String titulo) {
        int opcion;
        do {
            System.out.println();
            System.out.println("------- MENU " + titulo + " -------");
            System.out.printf("Saldo actual: %.2f%n", saldo);
            System.out.println("1. Ingresar");
            System.out.println("2. Retirar");
            System.out.println("3. Salir");
            System.out.print("Elige una opcion: ");
            opcion = leerEntero(sc);
            switch (opcion) {
                case 1 -> {
                    System.out.print("Cuanto quieres ingresar? ");
                    ingresar(leerDouble(sc));
                }
                case 2 -> {
                    System.out.print("Cuanto quieres retirar? ");
                    retirar(leerDouble(sc));
                }
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 3);
    }

    private int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Entrada invalida. Introduce un numero: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private double leerDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Entrada invalida. Introduce un importe: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}
