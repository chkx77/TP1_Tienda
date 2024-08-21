package TP1;

import java.util.ArrayList;
import java.util.List;

/* Esta clase "Tienda" está asumiendo muchas responsabilidades en su implementación actual.
 Soy consciente de que esto puede dificultar la escalabilidad y el mantenimiento del código.
 Una mejora sería delegar ciertas funcionalidades a clases o interfaces específicas,
 como crear una interfaz para comestibles o dividir la lógica de ventas en una clase separada.
 Esto permitiría una estructura más modular y flexible, facilitando futuras modificaciones. */

public class Tienda {
    private String nombre;
    private int maximoStock;
    private double saldoEnCaja;
    private List<Producto> productosEnStock;
    private List<Producto> listaEnvasados;
    private List<Producto> listaBebidas;
    private List<Producto> listaLimpieza;

    public Tienda(String nombre, int maximoStock, double saldoEnCaja) {
        this.nombre = nombre;
        this.maximoStock = maximoStock;
        this.saldoEnCaja = saldoEnCaja;
        this.productosEnStock = new ArrayList<>();
        this.listaEnvasados = new ArrayList<>();
        this.listaBebidas = new ArrayList<>();
        this.listaLimpieza = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format(
                "===== Tienda =====\n" +
                        "Nombre: %s\n" +
                        "Máximo Stock Permitido: %d productos\n" +
                        "Saldo en Caja: $%.2f\n" +
                        "==================\n",
                nombre, maximoStock, saldoEnCaja
        );
    }

    // Getters y Setters
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

    // Método para comprar un item y añadirlo al stock
    public void comprarProducto(Producto item, int cantidadAComprar) {
        boolean productoExistente = false;

        // Verificar si el producto ya está en stock
        for (Producto productoEnStock : productosEnStock) {
            if (productoEnStock.getDescripcion().equals(item.getDescripcion())) {
                productoExistente = true;
                int nuevoStock = productoEnStock.getStock() + cantidadAComprar;

                // Verificar si el nuevo stock excede el máximo permitido
                if (nuevoStock > maximoStock) {
                    System.out.println("No se puede agregar más de este producto porque se excede el stock máximo permitido.");
                    return;
                }

                // Verificar si hay suficiente saldo en caja
                double costoTotal = cantidadAComprar * item.getPrecioPorUnidad();
                if (saldoEnCaja < costoTotal) {
                    System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
                    return;
                }

                // Actualizar el stock y el saldo en caja
                productoEnStock.setStock(nuevoStock);
                saldoEnCaja -= costoTotal;
                System.out.println("Producto existente actualizado con éxito.");
                return;
            }
        }

        // Si el producto no existe en el stock, agregarlo como nuevo
        int cantidadActualEnStock = productosEnStock.stream().mapToInt(Producto::getStock).sum();

        // Verificar si agregar nuevos productos excede el máximo stock
        if (cantidadActualEnStock + cantidadAComprar > maximoStock) {
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock.");
            return;
        }

        // Verificar si hay suficiente saldo en caja
        double costoTotal = cantidadAComprar * item.getPrecioPorUnidad();
        if (saldoEnCaja < costoTotal) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
            return;
        }

        // Clasificar y agregar el producto
        if (item instanceof Envasado) {
            listaEnvasados.add(item);
        } else if (item instanceof Bebida) {
            listaBebidas.add(item);
        } else if (item instanceof Limpieza) {
            listaLimpieza.add(item);
        }

        // Agregar el producto al stock y descontar el saldo
        item.setStock(cantidadAComprar);
        productosEnStock.add(item);
        saldoEnCaja -= costoTotal;
        System.out.println("Producto agregado con éxito.");
    }

    // Método para vender productos
    public void venderProductos(List<Producto> productosAComprar, List<Integer> cantidades) {
        if (productosAComprar.size() > 3) {
            System.out.println("No se pueden incluir más de 3 productos en una venta.");
            return;
        }

        double totalVenta = 0;
        boolean stockMenorAlSolicitado = false;
        boolean productoNoDisponible = false;

        System.out.println("==========================================");
        System.out.println("               DETALLE DE VENTA           ");
        System.out.println("==========================================");
        for (int i = 0; i < productosAComprar.size(); i++) {
            Producto producto = productosAComprar.get(i);
            int cantidad = cantidades.get(i);

            if (cantidad > 12) {
                System.out.println("No se pueden vender más de 12 unidades de un mismo producto.");
                continue;
            }

            if (!producto.isDisponible()) {
                System.out.printf("El producto %s %s no se encuentra disponible.%n", producto.getId(), producto.getDescripcion());
                productoNoDisponible = true;
                continue;
            }

            if (producto.getStock() < cantidad) {
                System.out.printf("Hay productos con stock disponible menor al solicitado para %s %s. Solo se venderán %d unidades.%n",
                        producto.getId(), producto.getDescripcion(), producto.getStock());
                cantidad = producto.getStock();
                stockMenorAlSolicitado = true;
                producto.setDisponible(false); // Si no hay stock, lo hacemos no disponible
            }

            // Calcular el precio de venta base
            double precioVentaBase = producto.getPrecioPorUnidad() * (1 + producto.getPorcentajeGanancia() / 100);

            // Aplicar descuento adicional del 12% si el producto es importado
            double precioVentaFinal = precioVentaBase;
            if (producto instanceof Envasado) {
                Envasado envasado = (Envasado) producto;
                if (envasado.isEsImportado()) {
                    precioVentaFinal *= 1.12; // Aplicar el 12% extra al precio base
                }
            } else if (producto instanceof Bebida) {
                Bebida bebida = (Bebida) producto;
                if (bebida.isEsImportado()) {
                    precioVentaFinal *= 1.12; // Aplicar el 12% extra al precio base
                }
            }

            double subtotal = precioVentaFinal * cantidad;
            totalVenta += subtotal;

            // Descontar la cantidad vendida del stock
            producto.setStock(producto.getStock() - cantidad);

            System.out.printf("%s %s %d x $%.2f = $%.2f%n", producto.getId(), producto.getDescripcion(), cantidad, precioVentaFinal, subtotal);
            System.out.println("-------------------------------------");

            // Verificar si después de la venta ya no hay más stock y cambiar disponibilidad
            if (producto.getStock() == 0) {
                producto.setDisponible(false);
            }
        }

        // Actualizar el saldo en caja con el total de la venta
        saldoEnCaja += totalVenta;
        System.out.println("-------------------------------------");
        System.out.printf("TOTAL VENTA: $%.2f%n", totalVenta);

        if (stockMenorAlSolicitado) {
            System.out.println("Hay productos con stock disponible menor al solicitado.");
        }

        if (productoNoDisponible) {
            System.out.println("Uno o más productos no se encuentran disponibles y no se incluyeron en la venta.");
        }
    }


}
