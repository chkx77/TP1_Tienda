package TP1;

import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private String nombre;
    private int maximoStock;
    private double saldoEnCaja;
    private List<Producto> productosEnStock;

    public Tienda(String nombre, int maximoStock, double saldoEnCaja) {
        this.nombre = nombre;
        this.maximoStock = maximoStock;
        this.saldoEnCaja = saldoEnCaja;
        this.productosEnStock = new ArrayList<>(); // ==== Inicialización de la lista ====
    }

    @Override
    public String toString() {
        String resultado = "===== Tienda =====\n";
        resultado += "Nombre: " + nombre + "\n";
        resultado += "Máximo Stock Permitido: " + maximoStock + " productos\n";
        resultado += "Saldo en Caja: $" + String.format("%.2f", saldoEnCaja) + "\n";
        resultado += "==================\n";
        return resultado;
    }

    // ==== Getters y setters ====
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaximoStock() {
        return maximoStock;
    }

    public void setMaximoStock(int maximoStock) {
        this.maximoStock = maximoStock;
    }

    public double getSaldoEnCaja() {
        return saldoEnCaja;
    }

    public void setSaldoEnCaja(double saldoEnCaja) {
        this.saldoEnCaja = saldoEnCaja;
    }

    public List<Producto> getProductosEnStock() {
        return productosEnStock;
    }

    // ==== Método para comprar un item y añadirlo al stock ====
    public void comprarProducto(Producto item, int cantidadAComprar) {
        boolean productoExistente = false;

        // ==== Verificar si el producto ya está en stock ====
        for (Producto productoEnStock : productosEnStock) {
            if (productoEnStock.getDescripcion().equals(item.getDescripcion())) {
                productoExistente = true;
                int nuevoStock = productoEnStock.getStock() + cantidadAComprar;

                // ==== Verificar si el nuevo stock excede el máximo permitido ====
                if (nuevoStock > maximoStock) {
                    System.out.println("No se puede agregar más de este producto porque se excede el stock máximo permitido.");
                    return;
                }

                // ==== Verificar si hay suficiente saldo en caja ====
                double costoTotal = cantidadAComprar * item.getPrecioPorUnidad();
                if (saldoEnCaja < costoTotal) {
                    System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
                    return;
                }

                // ==== Actualizar el stock y el saldo en caja ====
                productoEnStock.setStock(nuevoStock);
                saldoEnCaja -= costoTotal;
                System.out.println("Producto existente actualizado con éxito.");
                return;
            }
        }

        // ==== Si el producto no existe en el stock, agregarlo como nuevo ====
        int cantidadActualEnStock = productosEnStock.stream().mapToInt(Producto::getStock).sum();

        // ==== Verificar si agregar nuevos productos excede el máximo stock ====
        if (cantidadActualEnStock + cantidadAComprar > maximoStock) {
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock");
            return;
        }

        // ==== Verificar si hay suficiente saldo en caja ====
        double costoTotal = cantidadAComprar * item.getPrecioPorUnidad();
        if (saldoEnCaja < costoTotal) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
            return;
        }

        // ==== Agregar el producto al stock y descontar el saldo ====
        item.setStock(cantidadAComprar);
        productosEnStock.add(item);
        saldoEnCaja -= costoTotal;
        System.out.println("Producto agregado con éxito.");
    }
}
