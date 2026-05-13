package gestion_bancaria;

import java.util.Scanner;

public class MainBanco {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            Banco caixa = new Banco("Caixa", "Espana");

            Cuenta cCorriente = new CuentaCorriente("Juan", "Caixa Barcelona", 1000.00, "01/01/2026");
            Cuenta cAhorro    = new CuentaAhorro("Naim", "Caixa Madrid", 500.35, "01/06/2026");
            Cuenta cInversion = new CuentaInversion("Pablo", "Caixa Valencia", 6460.20, "04/05/2026");

            caixa.agregarCuenta(cCorriente);
            caixa.agregarCuenta(cAhorro);
            caixa.agregarCuenta(cInversion);

            caixa.mostrarEstado();
            caixa.auditarTodas("Auditoria de apertura");

            cCorriente.operar(sc);
            cAhorro.operar(sc);

            caixa.resumenGeneral();
            caixa.cerrar();
            caixa.mostrarEstado();

        }
    }
}
