package gestion_bancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BancoTest {

    private Banco banco;

    @BeforeEach
    void setUp() {
        banco = new Banco("Caixa", "Espana");
    }

    @Test
    @DisplayName("Un banco recien creado esta abierto y sin cuentas")
    void bancoNuevoEstaAbiertoYVacio() {
        assertTrue(banco.isAbierto());
        assertTrue(banco.getCuentas().isEmpty());
    }

    @Test
    @DisplayName("Agregar cuentas las almacena en orden")
    void agregarCuentasLasAlmacena() {
        Cuenta c1 = new CuentaCorriente("Juan", "Madrid", 1000, "01/01/2026");
        Cuenta c2 = new CuentaAhorro("Naim", "Madrid", 500, "01/01/2026");
        banco.agregarCuenta(c1);
        banco.agregarCuenta(c2);
        assertEquals(2, banco.getCuentas().size());
        assertSame(c1, banco.getCuentas().get(0));
        assertSame(c2, banco.getCuentas().get(1));
    }

    @Test
    @DisplayName("Cerrar el banco cambia su estado a no abierto")
    void cerrarCambiaEstado() {
        banco.cerrar();
        assertFalse(banco.isAbierto());
    }
}
