package gestion_bancaria;

import java.util.Scanner;

public class CuentaInversion extends Cuenta {

    public CuentaInversion(String titular, String sucursal, double saldo, String ultimaAuditoria) {
        super(titular, saldo, sucursal, ultimaAuditoria);
    }

    @Override
    public void operar(Scanner sc) {
        menuOperaciones(sc, "CUENTA INVERSION");
    }

    @Override
    public void auditarOperacion(String detalle) {
        System.out.println("Auditoria [CUENTA INVERSION] " + titular
                + " - " + detalle + " | Ultima auditoria: " + ultimaAuditoria);
    }

    @Override
    public String getResumen() {
        return String.format(
                "[CUENTA INVERSION] Titular: %s | Saldo: %.2f | %s",
                titular, saldo, registro);
    }
}
