package TP1;

public class Prueba {

    public static void main(String[] args) {

        Tienda almacen = new Tienda("Don Manolo", 20, 12000);
        Producto manteca = new Envasado("Manteca Rancia", 1, 1000, 10, "Caja", false, 750);

        almacen.comprarProducto(manteca,  21);

        System.out.println(manteca.getStock());

        System.out.println(almacen.getSaldoEnCaja());


    }

}
