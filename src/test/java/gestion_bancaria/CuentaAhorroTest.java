package gestion_bancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CuentaAhorroTest {

    private CuentaAhorro cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaAhorro("Naim", "Caixa Madrid", 500.00, "01/06/2026");
    }

    @Test
    @DisplayName("Ingresar aplica el INTERES sobre la cantidad")
    void ingresoAplicaInteres() {
        cuenta.ingresar(100);
        // 500 + (100 + 100*0.20) = 620
        assertEquals(620.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("La constante INTERES tiene el valor esperado")
    void interesEsVeintePorCiento() {
        assertEquals(0.20, CuentaAhorro.INTERES, 0.001);
    }

    @Test
    @DisplayName("El interes no se aplica a cantidades invalidas")
    void cantidadInvalidaNoAplicaInteres() {
        cuenta.ingresar(-100);
        assertEquals(500.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Retirar funciona igual que en cualquier otra cuenta")
    void retirarFuncionaIgual() {
        cuenta.retirar(200);
        assertEquals(300.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("getResumen indica que es CUENTA AHORRO e incluye el interes")
    void resumenIncluyeTipoEInteres() {
        String resumen = cuenta.getResumen();
        assertTrue(resumen.contains("CUENTA AHORRO"));
        assertTrue(resumen.contains("Naim"));
        assertTrue(resumen.contains("Interes"));
    }
}
