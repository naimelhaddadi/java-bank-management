# Gestion Bancaria

Ejercicio de Programacion Orientada a Objetos en Java que simula un sistema bancario con varios tipos de cuenta, auditoria y registro de movimientos por sucursal.

## Conceptos de POO demostrados

- **Interfaz** (`Auditable`) con metodos abstractos.
- **Clase abstracta** (`Cuenta`) con metodos concretos y abstractos.
- **Herencia**: `CuentaCorriente`, `CuentaAhorro` y `CuentaInversion` extienden `Cuenta`.
- **Polimorfismo**: tratamiento uniforme de todas las cuentas a traves de la referencia `Cuenta`.
- **Encapsulacion**: campos `private`/`protected` y acceso controlado mediante getters.
- **Composicion**: cada `Cuenta` tiene un `Registro`; cada `Banco` tiene una lista de `Cuenta`.
- **Metodo plantilla** (template method): `Cuenta.ingresar()` define el flujo y delega en el hook `aplicarBeneficio()` que sobreescribe `CuentaAhorro` para aplicar el `INTERES`.
- **Sobrescritura de metodos abstractos**: cada subclase sobrescribe `operar(Scanner)`, `auditarOperacion(String)` y `getResumen()`.

## Estructura del proyecto

```
Gestion_Bancaria/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/java/gestion_bancaria/
    │   ├── Auditable.java          (interfaz)
    │   ├── Registro.java           (sucursal + contador de movimientos)
    │   ├── Cuenta.java             (clase abstracta base)
    │   ├── CuentaCorriente.java
    │   ├── CuentaAhorro.java       (aplica un INTERES del 20% en cada ingreso)
    │   ├── CuentaInversion.java
    │   ├── Banco.java              (gestiona N cuentas)
    │   └── MainBanco.java          (punto de entrada)
    └── test/java/gestion_bancaria/
        ├── CuentaCorrienteTest.java
        ├── CuentaAhorroTest.java
        └── BancoTest.java
```

## Diagrama de clases

```
        <<interface>>
          Auditable
              ^
              |
            Cuenta  (abstract)  -----> Registro
              ^
              |
   +----------+----------+
   |          |          |
 Corriente  Ahorro   Inversion

   Banco --> List<Cuenta>
```

## Como ejecutar

### Con Maven

```bash
# Compilar
mvn compile

# Ejecutar la aplicacion
mvn exec:java

# Lanzar los tests
mvn test
```

### Sin Maven (solo javac/java)

```bash
# Compilar
javac -d out src/main/java/gestion_bancaria/*.java

# Ejecutar
java -cp out gestion_bancaria.MainBanco
```

## Que se ha mejorado respecto a la version inicial

| Aspecto | Antes | Ahora |
|---|---|---|
| Estructura | 1 fichero con 7 clases | 1 clase por fichero + proyecto Maven |
| Bug: retirar exactamente todo el saldo | Bloqueado por `retirar < saldo` | Permitido (`cantidad > saldo` falla) |
| Bug: retirar/ingresar negativo | No validado | Validado en clase base |
| Duplicacion del menu | 3 copias casi identicas (~50 LOC cada una) | 1 implementacion en `Cuenta.operar()` |
| Manejo del Scanner | Multiples Scanner sin cerrar | Un unico Scanner con try-with-resources |
| Entradas no numericas | Crash con `InputMismatchException` | Re-pedidas hasta ser validas |
| Tests | Ninguno | 15 tests con JUnit 5 |
| Titulo del menu de CuentaInversion | "MENU CUENTA CORRIENTE" (copy-paste) | Corregido |

## Posibles mejoras futuras

- Persistencia de cuentas en MySQL.
- API REST sobre el modelo actual con Spring Boot.
- Excepciones de dominio propias (`SaldoInsuficienteException`, etc.) en lugar de `System.out.println`.
- Logger (`java.util.logging` o SLF4J) en lugar de `System.out`.

## Autor

Naim El Haddadi — [naimelhaddadi.com](https://naimelhaddadi.com)
