package gestion_bancaria;

import java.util.Scanner;

public class CuentaAhorro extends Cuenta {

    public static final double INTERES = 0.20;

    public CuentaAhorro(String titular, String sucursal, double saldo, String ultimaAuditoria) {
        super(titular, saldo, sucursal, ultimaAuditoria);
    }

    @Override
    public void operar(Scanner sc) {
        menuOperaciones(sc, "CUENTA AHORRO");
    }

    @Override
    public void auditarOperacion(String detalle) {
        System.out.println("Auditoria [CUENTA AHORRO] " + titular
                + " - " + detalle + " | Interes: " + (INTERES * 100) + "%"
                + " | Ultima auditoria: " + ultimaAuditoria);
    }

    @Override
    public String getResumen() {
        return String.format(
                "[CUENTA AHORRO] Titular: %s | Saldo: %.2f | %s | Interes: %.0f%%",
                titular, saldo, registro, INTERES * 100);
    }

    // En la cuenta de ahorro cada ingreso recibe el interes aplicado
    // sobre la cantidad ingresada.
    @Override
    protected double aplicarBeneficio(double cantidad) {
        return cantidad + (cantidad * INTERES);
    }
}
