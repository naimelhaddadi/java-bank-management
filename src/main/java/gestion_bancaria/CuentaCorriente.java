package gestion_bancaria;

import java.util.Scanner;

public class CuentaCorriente extends Cuenta {

    public CuentaCorriente(String titular, String sucursal, double saldo, String ultimaAuditoria) {
        super(titular, saldo, sucursal, ultimaAuditoria);
    }

    @Override
    public void operar(Scanner sc) {
        menuOperaciones(sc, "CUENTA CORRIENTE");
    }

    @Override
    public void auditarOperacion(String detalle) {
        System.out.println("Auditoria [CUENTA CORRIENTE] " + titular
                + " - " + detalle + " | Ultima auditoria: " + ultimaAuditoria);
    }

    @Override
    public String getResumen() {
        return String.format(
                "[CUENTA CORRIENTE] Titular: %s | Saldo: %.2f | %s",
                titular, saldo, registro);
    }
}
