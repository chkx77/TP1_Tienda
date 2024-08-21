package TP1;

import java.util.ArrayList;
import java.util.List;

/* En esta clase "Tienda", soy consciente de que estoy asignandole muchas responsabilidades.
   Sé que esto puede complicar la escalabilidad y el mantenimiento del código.
   Reviendolo, acepto que una mejora sería delegar ciertas funcionalidades a clases o interfaces específicas,
   como crear una interfaz para comestibles o dividir la lógica de ventas en una clase separada.
   Esto permitiría una estructura más modular y flexible, facilitando futuras modificaciones.

    Elegí usar ArrayList para las listas de productos porque es flexible y permite manejar cambios
    en el stock de manera eficiente. Para los cálculos de stock, usé stream()
    porque hace el código más limpio y más fácil de entender. El operador instanceof me ayudo a clasificar los
    productos y mantenerlos organizados en listas específicas. Ademas, reviso el stock y el saldo en caja antes
    de hacer compras o ventas para evitar errores y asegurarme de que todo esté en orden. Al calcular los precios,
    aplico descuentos y ajustes según las reglas de negocio para obtener un precio final correcto.
    Finalmente, actualizo la disponibilidad del producto cuando se agota el stock para evitar vender artículos
    que ya no están disponibles.
*/

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

                                          // =====Método para comprar un item====
    
    public void comprarProducto(Producto item, int cantidadAComprar) {
        boolean productoExistente = false;

        // Verifico si el producto ya está en stock
        for (Producto productoEnStock : productosEnStock) {
            if (productoEnStock.getDescripcion().equals(item.getDescripcion())) {
                productoExistente = true;
                int nuevoStock = productoEnStock.getStock() + cantidadAComprar;

                // Verifico si el nuevo stock excede el máximo permitido
                if (nuevoStock > maximoStock) {
                    System.out.println("No se puede agregar más de este producto porque se excede el stock máximo permitido.");
                    return;
                }

                // Verifico si hay suficiente saldo en caja
                double costoTotal = cantidadAComprar * item.getPrecioPorUnidad();
                if (saldoEnCaja < costoTotal) {
                    System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
                    return;
                }

                // Actualizo el stock y el saldo en caja
                productoEnStock.setStock(nuevoStock);
                saldoEnCaja -= costoTotal;
                System.out.println("Producto existente actualizado con éxito.");
                return;
            }
        }

        // Si el producto no existe en el stock, lo agrego como nuevo
        int cantidadActualEnStock = productosEnStock.stream().mapToInt(Producto::getStock).sum();

        // Verifico si agregar nuevos productos excede el máximo stock
        if (cantidadActualEnStock + cantidadAComprar > maximoStock) {
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock.");
            return;
        }

        // Verifico si hay suficiente saldo en caja
        double costoTotal = cantidadAComprar * item.getPrecioPorUnidad();
        if (saldoEnCaja < costoTotal) {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
            return;
        }

        // Clasifico y agrego el producto
        if (item instanceof Envasado) {
            listaEnvasados.add(item);
        } else if (item instanceof Bebida) {
            listaBebidas.add(item);
        } else if (item instanceof Limpieza) {
            listaLimpieza.add(item);
        }

        // Agrego el producto al stock y descuento el saldo
        item.setStock(cantidadAComprar);
        productosEnStock.add(item);
        saldoEnCaja -= costoTotal;
        System.out.println("Producto agregado con éxito.");
    }

                                    // =====Método para vender productos====
    
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
                producto.setDisponible(false); // Si no hay stock, lo hago no disponible
            }


            // Calculo el precio de venta base
            double precioVentaBase = producto.getPrecioPorUnidad() * (1 + producto.getPorcentajeGanancia() / 100);
            double descuentoAplicado = precioVentaBase * producto.getDescuento() / 100;
            double precioFinal = precioVentaBase - descuentoAplicado;

            // Aplico descuento adicional del 12% si el producto es importado
            double precioVentaFinal = precioFinal;
            if (producto instanceof Envasado) {
                Envasado envasado = (Envasado) producto;
                if (envasado.isEsImportado()) {
                    precioVentaFinal *= 1.12;
                }
            } else if (producto instanceof Bebida) {
                Bebida bebida = (Bebida) producto;
                if (bebida.isEsImportado()) {
                    precioVentaFinal *= 1.12;
                }
            }

            double subtotal = precioVentaFinal * cantidad;
            totalVenta += subtotal;

            // Descuento la cantidad vendida del stock
            producto.setStock(producto.getStock() - cantidad);

            System.out.printf("%s %s %d x $%.2f = $%.2f%n", producto.getId(), producto.getDescripcion(), cantidad, precioVentaFinal, subtotal);

            // Verifico si después de la venta ya no hay más stock y cambio disponibilidad
            if (producto.getStock() == 0) {
                producto.setDisponible(false);
            }
        }

        // Actualizo el saldo en caja con el total de la venta
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




