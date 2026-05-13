package gestion_bancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaCorrienteTest {

    private CuentaCorriente cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaCorriente("Juan", "Caixa Barcelona", 1000.00, "01/01/2026");
    }

    @Test
    @DisplayName("Ingresar una cantidad positiva aumenta el saldo")
    void ingresarPositivoAumentaSaldo() {
        cuenta.ingresar(200);
        assertEquals(1200.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Ingresar 0 no modifica el saldo")
    void ingresarCeroNoModificaSaldo() {
        cuenta.ingresar(0);
        assertEquals(1000.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Ingresar una cantidad negativa no modifica el saldo")
    void ingresarNegativoNoModificaSaldo() {
        cuenta.ingresar(-50);
        assertEquals(1000.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Retirar una cantidad valida disminuye el saldo")
    void retirarValidoDisminuyeSaldo() {
        cuenta.retirar(300);
        assertEquals(700.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Retirar exactamente todo el saldo debe permitirse (saldo final = 0)")
    void retirarTodoElSaldoDejaSaldoCero() {
        cuenta.retirar(1000);
        assertEquals(0.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Retirar mas del saldo disponible no modifica el saldo")
    void retirarSuperiorAlSaldoNoModificaSaldo() {
        cuenta.retirar(2000);
        assertEquals(1000.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Retirar una cantidad negativa no modifica el saldo")
    void retirarNegativoNoModificaSaldo() {
        cuenta.retirar(-100);
        assertEquals(1000.00, cuenta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Cada ingreso valido incrementa el contador de movimientos")
    void movimientosSeContabilizan() {
        cuenta.ingresar(100);
        cuenta.retirar(50);
        cuenta.ingresar(20);
        assertEquals(3, cuenta.getRegistro().getTotalMovimientos());
    }

    @Test
    @DisplayName("Operaciones invalidas no incrementan el contador de movimientos")
    void operacionesInvalidasNoCuentan() {
        cuenta.ingresar(-100);
        cuenta.retirar(5000);
        cuenta.retirar(0);
        assertEquals(0, cuenta.getRegistro().getTotalMovimientos());
    }

    @Test
    @DisplayName("getResumen devuelve una cadena con el tipo de cuenta y el titular")
    void resumenContieneTipoYTitular() {
        String resumen = cuenta.getResumen();
        assertTrue(resumen.contains("CUENTA CORRIENTE"));
        assertTrue(resumen.contains("Juan"));
    }
}
