package TP1;

import java.util.Objects;


//====Clase padre Producto====

abstract class Producto {
    protected String id;
    protected String descripcion;
    protected int stock;
    protected double precioPorUnidad;
    protected double porcentajeGanancia;
    protected boolean disponible;

    public Producto(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia) {
        this.descripcion = Objects.requireNonNull(descripcion, "Descripción no puede ser nula");
        this.stock = stock;
        this.precioPorUnidad = precioPorUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
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
                        "Disponible: %b\n" +
                        "-----------------------------",
                id, descripcion, stock, precioPorUnidad, porcentajeGanancia, disponible
        );
    }
}

//====SubClase Envasados====

class Envasado extends Producto {
    private static int contador = 0;
    private String tipoEnvase;
    private boolean esImportado;
    private int calorias;

    public Envasado(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia, String tipoEnvase, boolean esImportado, int calorias) {
        super(descripcion, stock, precioPorUnidad, porcentajeGanancia);
        this.tipoEnvase = Objects.requireNonNull(tipoEnvase, "Tipo de envase no puede ser nulo");
        this.esImportado = esImportado;
        this.calorias = calorias;
        this.id = generarId();
    }

    private String generarId() {
        contador++;
        return String.format("AB%03d", contador);
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

    @Override
    public String toString() {
        return String.format(
                "%s\n" +
                        "Tipo de Envase: %s\n" +
                        "Importado: %b\n" +
                        "Calorías: %d\n" +
                        "-----------------------------",
                super.toString(), tipoEnvase, esImportado, calorias
        );
    }
}

//====SubClase Bebidas====

class Bebida extends Producto {
    private static int contador = 0;
    private double graduacionAlcoholica;
    private boolean esImportado;
    private double calorias; // Atributo para las calorías

    public Bebida(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia, double graduacionAlcoholica, boolean esImportado) {
        super(descripcion, stock, precioPorUnidad, porcentajeGanancia);
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.esImportado = esImportado;
        this.calorias = calcularCalorias();
        this.id = generarId();
    }

    private String generarId() {
        contador++;
        return String.format("AC%03d", contador);
    }

    private double calcularCalorias() {
        if (graduacionAlcoholica <= 2) {
            return graduacionAlcoholica;
        } else if (graduacionAlcoholica <= 4.5) {
            return graduacionAlcoholica * 1.25;
        } else {
            return graduacionAlcoholica * 1.5;
        }
    }

    // Getters y Setters
    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
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
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n" +
                        "Graduación Alcohólica: %.2f%%\n" +
                        "Importado: %b\n" +
                        "Calorías: %.2f\n" +
                        "-----------------------------",
                super.toString(), graduacionAlcoholica, esImportado, calorias
        );
    }
}

//====SubClase Productos de Limpieza====

class Limpieza extends Producto {
    private static int contador = 0;
    private String tipoAplicacion;

    public Limpieza(String descripcion, int stock, double precioPorUnidad, double porcentajeGanancia, String tipoAplicacion) {
        super(descripcion, stock, precioPorUnidad, porcentajeGanancia);
        this.tipoAplicacion = Objects.requireNonNull(tipoAplicacion, "Tipo de aplicación no puede ser nulo");
        this.id = generarId();
    }

    private String generarId() {
        contador++;
        return String.format("AZ%03d", contador);
    }

    // Getters y Setters
    public String getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(String tipoAplicacion) {
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

