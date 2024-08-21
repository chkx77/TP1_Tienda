package TP1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/*
    En este diseño, opté por mantener la mayor parte de la lógica en la clase "Tienda" para asegurar
    una gestión centralizada de las operaciones, lo que facilita el acceso a métodos clave en un solo
    lugar. Soy consciente de que esto recargó la clase con demasiadas responsabilidades, lo que va en
    contra del principio de responsabilidad única (SRP). En un futuro, sería ideal delegar algunas
    tareas en clases auxiliares o incluso implementar interfaces, especialmente para productos como
    "Comestibles", lo cual no se implementó en esta versión debido a una interpretación inicial en la
    que asumí que todos los productos envasados eran comestibles, dado que los productos de limpieza
    ya contaban con su propia clase. Reconozco que esta fue una interpretación ambigua, y que la
    creación de una interfaz específica para "Comestibles" podría haber sido una solución más adecuada
    para manejar esta diferenciación de manera más explícita y escalable.
*/

//====Clase padre Producto====
abstract class Producto {
    protected String id;
    protected String descripcion;
    protected int stock;
    protected double precioPorUnidad;
    protected double porcentajeGanancia;
    protected double descuento;
    protected boolean disponible;

    public Producto(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia, double descuento) {
        this.descripcion = Objects.requireNonNull(descripcion, "Descripción no puede ser nula");
        this.stock = stock;
        this.precioPorUnidad = precioPorUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
        setDescuento(descuento);
        this.disponible = true;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return String.format(
                "-----------------------------\n" +
                        "ID: %s\n" +
                        "Descripción: %s\n" +
                        "Stock: %d\n" +
                        "Precio: %.2f\n" +
                        "Ganancia: %.2f%%\n" +
                        "Descuento: %.2f%%\n" +
                        "Disponible: %b\n" +
                        "-----------------------------",
                id, descripcion, stock, precioPorUnidad, porcentajeGanancia, descuento, disponible
        );
    }
}

//====SubClase Envasados====
class Envasado extends Producto {
    private static int contador = 0;
    private String tipoEnvase;
    private boolean esImportado;
    private int calorias;
    private Date fechaVencimiento;
    private static final double MAX_DESCUENTO = 15;

    public Envasado(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia,
                    String tipoEnvase, boolean esImportado, int calorias, String fechaVencimiento, double descuento) {
        super(descripcion, stock, precioPorUnidad, porcentajeGanancia, descuento);
        validarDescuento(descuento);
        validarGanancia();
        this.tipoEnvase = Objects.requireNonNull(tipoEnvase, "Tipo de envase no puede ser nulo");
        this.esImportado = esImportado;
        this.calorias = calorias;
        this.fechaVencimiento = convertirFecha(fechaVencimiento);
        this.id = generarId();
    }

    private void validarDescuento(double descuento) {
        if (descuento > MAX_DESCUENTO) {
            throw new IllegalArgumentException("El descuento para productos envasados no puede superar el 15%.");
        }
    }

    private void validarGanancia() {
        if (getPorcentajeGanancia() > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para productos envasados no puede superar el 20%.");
        }
    }

    private String generarId() {
        contador++;
        return String.format("AB%03d", contador);
    }

    private Date convertirFecha(String fechaStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        try {
            return sdf.parse(fechaStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Debe ser MM/yyyy.");
        }
    }

    // Getters y Setters
    public String getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(String tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
    }

    public boolean isEsImportado() {
        return esImportado;
    }

    public void setEsImportado(boolean esImportado) {
        this.esImportado = esImportado;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = convertirFecha(fechaVencimiento);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        return String.format(
                "%s\n" +
                        "Tipo de Envase: %s\n" +
                        "Importado: %b\n" +
                        "Calorías: %d\n" +
                        "Fecha de Vencimiento: %s\n" +
                        "-----------------------------",
                super.toString(), tipoEnvase, esImportado, calorias, sdf.format(fechaVencimiento)
        );
    }
}

//====SubClase Bebidas====
class Bebida extends Producto {
    private static int contador = 0;
    private double graduacionAlcoholica;
    private boolean esImportado;
    private double calorias;
    private Date fechaVencimiento;
    private static final double MAX_DESCUENTO = 10;

    public Bebida(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia,
                  double graduacionAlcoholica, boolean esImportado, double calorias, String fechaVencimiento, double descuento) {
        super(descripcion, stock, precioPorUnidad, porcentajeGanancia, descuento);
        validarDescuento(descuento);
        validarGanancia();
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.esImportado = esImportado;
        this.calorias = ajustarCalorias(calorias);
        this.fechaVencimiento = convertirFecha(fechaVencimiento);
        this.id = generarId();
    }

    private void validarDescuento(double descuento) {
        if (descuento > MAX_DESCUENTO) {
            throw new IllegalArgumentException("El descuento para bebidas no puede superar el 10%.");
        }
    }

    private void validarGanancia() {
        if (getPorcentajeGanancia() > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para bebidas no puede superar el 20%.");
        }
    }

    private String generarId() {
        contador++;
        return String.format("AC%03d", contador);
    }

    private double ajustarCalorias(double caloriasIngresadas) {
        if (graduacionAlcoholica <= 2) {
            return caloriasIngresadas;
        } else if (graduacionAlcoholica <= 4.5) {
            return caloriasIngresadas * 1.25;
        } else {
            return caloriasIngresadas * 1.5;
        }
    }

    private Date convertirFecha(String fechaStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        try {
            return sdf.parse(fechaStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Debe ser MM/yyyy.");
        }
    }

    // Getters y Setters
    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.calorias = ajustarCalorias(this.calorias);
    }

    public boolean isEsImportado() {
        return esImportado;
    }

    public void setEsImportado(boolean esImportado) {
        this.esImportado = esImportado;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = ajustarCalorias(calorias);
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = convertirFecha(fechaVencimiento);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        return String.format(
                "%s\n" +
                        "Graduación Alcohólica: %.2f%%\n" +
                        "Importado: %b\n" +
                        "Calorías: %.2f\n" +
                        "Fecha de Vencimiento: %s\n" +
                        "-----------------------------",
                super.toString(), graduacionAlcoholica, esImportado, calorias, sdf.format(fechaVencimiento)
        );
    }
}

//====SubClase Limpieza====
class Limpieza extends Producto {
    private static int contador = 0;
    private String tipoAplicacion;
    private static final Set<String> TIPOS_VALIDOS = Set.of("COCINA", "BAÑO", "ROPA", "MULTIUSO");
    private static final double MAX_DESCUENTO = 20;

    public Limpieza(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia,
                    String tipoAplicacion, double descuento) {
        super(descripcion, stock, precioPorUnidad, porcentajeGanancia, descuento);
        setTipoAplicacion(tipoAplicacion);
        validarDescuento(descuento);
        validarGanancia();
        this.id = generarId();
    }

    private void validarDescuento(double descuento) {
        if (descuento > MAX_DESCUENTO) {
            throw new IllegalArgumentException("El descuento para productos de limpieza no puede superar el 20%.");
        }
    }

    private void validarGanancia() {
        if (getPorcentajeGanancia() > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para productos de limpieza no puede superar el 20%.");
        }
    }

    private String generarId() {
        contador++;
        return String.format("LI%03d", contador);
    }

    // Getters y Setters
    public String getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(String tipoAplicacion) {
        if (!TIPOS_VALIDOS.contains(tipoAplicacion.toUpperCase())) {
            throw new IllegalArgumentException("Tipo de aplicación inválido. Debe ser uno de los siguientes: COCINA, BAÑO, ROPA, MULTIUSO.");
        }
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n" +
                        "Tipo de Aplicación: %s\n" +
                        "-----------------------------",
                super.toString(), tipoAplicacion
        );
    }
}
