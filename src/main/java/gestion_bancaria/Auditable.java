package gestion_bancaria;

public interface Auditable {

    void auditarOperacion(String detalle);

    String getResumen();
}
