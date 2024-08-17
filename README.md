Proyecto Tienda:

Descripción del Proyecto:

Este proyecto consiste en una aplicación desarrollada en Java, orientada a la gestión de una tienda. La clase principal, denominada Tienda, ofrece funcionalidades para administrar el inventario de productos y controlar el saldo disponible en caja.

Clase Tienda:

Atributos:

Nombre: Atributo de tipo String que define el nombre de la tienda.
Máximo Stock: Atributo de tipo int que establece el límite máximo de productos que se pueden almacenar en el inventario de la tienda.
Saldo en Caja: Atributo de tipo double que representa la cantidad de dinero disponible en la caja de la tienda.
Productos en Stock: Atributo de tipo List<Producto> que mantiene una lista de los productos actualmente en inventario.

Constructor:

El constructor de la clase Tienda inicializa una nueva instancia de la tienda con el nombre especificado, el máximo stock permitido y el saldo inicial en caja. La lista de productos en stock se inicializa como vacía.

Métodos:

Método toString().

Este método proporciona una representación textual de la instancia de la tienda. Incluye el nombre de la tienda, el límite máximo de stock y el saldo disponible en caja. La salida está formateada para ofrecer una visión clara y concisa del estado de la tienda.

Getters y Setters.

getNombre() / setNombre(String nombre): Métodos para obtener o asignar el nombre de la tienda.
getMaximoStock() / setMaximoStock(int maximoStock): Métodos para obtener o modificar el límite máximo de productos permitidos en el inventario.
getSaldoEnCaja() / setSaldoEnCaja(double saldoEnCaja): Métodos para obtener o actualizar el saldo en caja.
getProductosEnStock(): Método para obtener la lista de productos actualmente en inventario.
Método comprarProducto(Producto item, int cantidadAComprar)
Este método gestiona la compra de un producto y su adición al inventario de la tienda. Realiza las siguientes operaciones:

Verificación de Producto Existente:

Recorre la lista de productos en stock para determinar si el producto ya se encuentra en el inventario.
Si el producto existe, se actualiza la cantidad en stock y se verifica si el nuevo total excede el límite máximo permitido.
Se comprueba que haya suficiente saldo en caja para cubrir el costo total de la compra.
Agregar Producto Nuevo:

Si el producto no está presente en el inventario, se realiza una verificación adicional para asegurar que añadir nuevos productos no supere el límite máximo de stock.
Se verifica que el saldo en caja sea suficiente para cubrir el costo total del nuevo stock.
Si todas las verificaciones son satisfactorias, el producto se agrega al inventario y se actualiza el saldo en caja.

Ejemplo de Uso:

Se puede crear una instancia de la clase Tienda y un objeto Producto. Posteriormente, se puede realizar una compra del producto y verificar que tanto el inventario como el saldo en caja se actualicen correctamente. Esta operación puede ser comprobada mediante la impresión del estado actual de la tienda.
