package gestion_bancaria;

public class Registro {

    private final String sucursal;
    private int totalMovimientos;

    public Registro(String sucursal) {
        this.sucursal = sucursal;
        this.totalMovimientos = 0;
    }

    public String getSucursal() {
        return sucursal;
    }

    public int getTotalMovimientos() {
        return totalMovimientos;
    }

    public void mover() {
        this.totalMovimientos++;
    }

    @Override
    public String toString() {
        return "Sucursal: " + sucursal + " | Movimientos: " + totalMovimientos;
    }
}
