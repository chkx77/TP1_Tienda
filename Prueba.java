package TP1;
import java.util.Arrays;
import java.util.List;

public class TestTienda {
        public static void main(String[] args) {

                                //=====Creacion de la tienda=====

            Tienda tiendaYermo = new Tienda("Tienda del Yermo", 100, 5000.00);


                                //=====Creacion de los productos=====

            Envasado palitosIguana = new Envasado("Palitos de iguana", 0, 500.00,
                    5, "Paquete", false, 500, "02/2025", 5);
            Envasado raciones = new Envasado("Raciones de Emergencia", 0, 100.00,
                    19, "Paquete", false, 200, "08/2025", 15);
            Bebida nukaQuan = new Bebida("Nuka-Cola Quantum", 0, 500, 15,
                    0, false, 400, "05/2025", 7);
            Bebida nuka = new Bebida("Nuka-Cola", 5, 200, 5,
                    0, false, 200, "02/2025", 6);
            Limpieza abrax = new Limpieza("Limpiador Abraxo", 0, 120,
                    20, "BAÑO", 10);
            Limpieza sanitiz = new Limpieza("Sanitizante de comida", 0, 500,
                    15, "COCINA", 12);



            //=====Creacion de nuevo producto (Para testear los limites de las consignas)=====

            Producto sugarBombs = new Envasado("Sugar Bombs", 0, 1000, 40,
                    "Papel", true, 20000, "02/2025", 10);





            //                  =====Casos de prueba=====

            // Caso 1: Compra y venta de un producto envasado
            /*System.out.println("======================================");
            System.out.println("=== CASO 1: COMPRA Y VENTA DE RACIONES ===");
            System.out.println("=======================================");
            System.out.println("Estado inicial de la tienda:");
            System.out.println(tiendaYermo);
            System.out.println("Saldo en caja:");
            System.out.println(tiendaYermo.getSaldoEnCaja());

            tiendaYermo.comprarProducto(raciones, 5);
            System.out.println("===============================================");
            System.out.println("Estado de la tienda después de comprar raciones:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de raciones:");
            System.out.println(raciones);

            List<Producto> productosAVender = Arrays.asList(raciones);
            List<Integer> cantidades = Arrays.asList(3);

            tiendaYermo.venderProductos(productosAVender, cantidades);
            System.out.println("=====================================");
            System.out.println("Stock de raciones después de la venta:");
            System.out.println(raciones);
            System.out.println("Saldo en caja después de la venta:");
            System.out.println(tiendaYermo.getSaldoEnCaja());
            System.out.println("==========================================");*/



            // Caso 2: Compra y venta de una bebida importada
            /*
            System.out.println("======================================");
            System.out.println("=== CASO 2: COMPRA Y VENTA DE NUKA-COLA QUANTUM ===");
            System.out.println("=======================================");
            System.out.println("Estado inicial de la tienda:");
            System.out.println(tiendaYermo);
            System.out.println("Saldo en caja:");
            System.out.println(tiendaYermo.saldoEnCaja());

            tiendaYermo.comprarProducto(nukaQuan, 1);
            System.out.println("===============================================");
            System.out.println("Estado de la tienda después de comprar Nuka-Cola Quantum:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Nuka-Cola Quantum:");
            System.out.println(nukaQuan);
            System.out.println("Saldo en caja después de la compra:");
            System.out.println(tiendaYermo.saldoEnCaja());

            List<Producto> productosAVender = Arrays.asList(nukaQuan);
            List<Integer> cantidades = Arrays.asList(1);

            tiendaYermo.venderProductos(productosAVender, cantidades);
            System.out.println("=====================================");
            System.out.println("Estado de la tienda después de vender Nuka-Cola Quantum:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Nuka-Cola Quantum después de la venta:");
            System.out.println(nukaQuan);
            System.out.println("Saldo en caja después de la venta:");
            System.out.println(tiendaYermo.saldoEnCaja());
            System.out.println("==========================================");
            */



            // Caso 3: Compra de productos de limpieza y su venta
            /*
            System.out.println("======================================");
            System.out.println("=== CASO 3: COMPRA Y VENTA DE LIMPIADOR ABRAXO ===");
            System.out.println("=======================================");
            System.out.println("Estado inicial de la tienda:");
            System.out.println(tiendaYermo);
            System.out.println("Saldo en caja:");
            System.out.println(tiendaYermo.saldoEnCaja());

            tiendaYermo.comprarProducto(abrax, 10);
            System.out.println("===============================================");
            System.out.println("Estado de la tienda después de comprar Limpiador Abraxo:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Limpiador Abraxo:");
            System.out.println(abrax);
            System.out.println("Saldo en caja después de la compra:");
            System.out.println(tiendaYermo.saldoEnCaja());

            List<Producto> productosAVender = Arrays.asList(abrax);
            List<Integer> cantidades = Arrays.asList(5);

            tiendaYermo.venderProductos(productosAVender, cantidades);
            System.out.println("=====================================");
            System.out.println("Estado de la tienda después de vender Limpiador Abraxo:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Limpiador Abraxo después de la venta:");
            System.out.println(abrax);
            System.out.println("Saldo en caja después de la venta:");
            System.out.println(tiendaYermo.saldoEnCaja());
            System.out.println("==========================================");
            */



            // Caso 4: Compra y venta de productos envasados y bebidas
            /*
            System.out.println("======================================");
            System.out.println("=== CASO 4: COMPRA Y VENTA DE PALITOS DE IGUANA Y NUKA-COLA ===");
            System.out.println("=======================================");
            System.out.println("Estado inicial de la tienda:");
            System.out.println(tiendaYermo);
            System.out.println("Saldo en caja:");
            System.out.println(tiendaYermo.saldoEnCaja());

            tiendaYermo.comprarProducto(palitosIguana, 20);
            tiendaYermo.comprarProducto(nuka, 15);
            System.out.println("===============================================");
            System.out.println("Estado de la tienda después de comprar Palitos de Iguana y Nuka-Cola:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Palitos de Iguana:");
            System.out.println(palitosIguana);
            System.out.println("Stock de Nuka-Cola:");
            System.out.println(nuka);
            System.out.println("Saldo en caja después de la compra:");
            System.out.println(tiendaYermo.saldoEnCaja());

            List<Producto> productosAVender = Arrays.asList(palitosIguana, nuka);
            List<Integer> cantidades = Arrays.asList(10, 5);

            tiendaYermo.venderProductos(productosAVender, cantidades);
            System.out.println("=====================================");
            System.out.println("Estado de la tienda después de vender Palitos de Iguana y Nuka-Cola:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Palitos de Iguana después de la venta:");
            System.out.println(palitosIguana);
            System.out.println("Stock de Nuka-Cola después de la venta:");
            System.out.println(nuka);
            System.out.println("Saldo en caja después de la venta:");
            System.out.println(tiendaYermo.saldoEnCaja());
            System.out.println("==========================================");
            */



            // Caso 5: Intento de venta de productos sin stock suficiente
            /*
            System.out.println("======================================");
            System.out.println("=== CASO 5: INTENTO DE VENTA DE SANITIZANTE DE COMIDA CON STOCK INSUFICIENTE ===");
            System.out.println("=======================================");
            System.out.println("Estado inicial de la tienda:");
            System.out.println(tiendaYermo);
            System.out.println("Saldo en caja:");
            System.out.println(tiendaYermo.saldoEnCaja());

            tiendaYermo.comprarProducto(sanitiz, 5);
            System.out.println("===============================================");
            System.out.println("Estado de la tienda después de comprar Sanitizante de comida:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Sanitizante de comida:");
            System.out.println(sanitiz);
            System.out.println("Saldo en caja después de la compra:");
            System.out.println(tiendaYermo.saldoEnCaja());

            List<Producto> productosAVender = Arrays.asList(sanitiz);
            List<Integer> cantidades = Arrays.asList(10);

            tiendaYermo.venderProductos(productosAVender, cantidades);
            System.out.println("=====================================");
            System.out.println("Estado de la tienda después del intento de venta de Sanitizante de comida con stock insuficiente:");
            System.out.println(tiendaYermo);
            System.out.println("Stock de Sanitizante de comida después del intento de venta:");
            System.out.println(sanitiz);
            System.out.println("Saldo en caja después del intento de venta:");
            System.out.println(tiendaYermo.saldoEnCaja());
            System.out.println("==========================================");
            */


            //System.out.println(tiendaYermo.obtenerComestiblesConMenorDescuento(19));


        }
    }


