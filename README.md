TP - 1

Clase Producto();
El sistema de gestión de productos para una tienda está compuesto por una clase padre: Producto, y tres clases hijas: Envasado, Bebida, y Limpieza. Estas clases permiten representar y gestionar distintos tipos de productos en una tienda, cada uno con atributos y comportamientos específicos. El objetivo es mantener la lógica centralizada en la clase Tienda, aunque esto ha llevado a una sobrecarga de responsabilidades que puede ser revisada en versiones futuras.

Clase Producto

La clase Producto es abstracta y sirve como base para las subclases específicas. Incluye atributos comunes a todos los productos, como id, descripcion, stock, precioPorUnidad, porcentajeGanancia, descuento, y disponible. La clase proporciona métodos para acceder y modificar estos atributos y define un formato de salida para representar el producto en un formato legible. La validación del descuento y la ganancia se realiza en las subclases específicas, asegurando que las restricciones sean aplicadas de acuerdo con el tipo de producto.

Subclase Envasado

La clase Envasado extiende Producto y añade atributos adicionales como tipoEnvase, esImportado, calorias, y fechaVencimiento. El identificador del producto envasado se genera automáticamente utilizando un contador estático. Se incluye una validación para asegurar que la fecha de vencimiento sea futura, el descuento no exceda el 15%, y el porcentaje de ganancia no sea mayor al 20%. El formato de la fecha es MM/yyyy, y se convierte mediante un método específico. La clase también ajusta el formato de salida para incluir estos nuevos atributos.

Subclase Bebida

La clase Bebida, otra extensión de Producto, agrega atributos como graduacionAlcoholica, esImportado, calorias, y fechaVencimiento. Similar a Envasado, el identificador se genera con un contador estático y se valida que el descuento no supere el 10% y la ganancia no exceda el 20%. La calorías se ajustan basándose en la graduación alcohólica. La conversión y validación de la fecha siguen el mismo formato MM/yyyy, y la salida se ajusta para mostrar estos detalles específicos.

Subclase Limpieza

Finalmente, Limpieza también extiende Producto, añadiendo el atributo tipoAplicacion y utilizando un conjunto estático para validar los tipos de aplicación permitidos. El identificador es generado con un contador estático y se asegura que el descuento no sea mayor al 20%. La clase valida que el tipo de aplicación esté en el conjunto de tipos válidos y ajusta el formato de salida para reflejar estos detalles.


Clase Tienda();

La clase Tienda gestiona los productos en una tienda, incluyendo operaciones de compra, venta y filtrado de productos. Aunque esta clase centraliza muchas responsabilidades, se reconoce que una estructura más modular podría mejorar la escalabilidad y el mantenimiento del código. Para el manejo de productos, se utilizan listas específicas para cada tipo: listaEnvasados, listaBebidas, y listaLimpieza. El saldo en caja y el máximo stock permitido también se gestionan dentro de la clase.

Constructor y Atributos

El constructor de Tienda inicializa el nombre, el máximo stock permitido, el saldo en caja y las listas para los productos en stock y de cada tipo específico. Los atributos son nombre, maximoStock, saldoEnCaja, productosEnStock, listaEnvasados, listaBebidas, y listaLimpieza.

Métodos

toString: Proporciona una representación en texto del estado actual de la tienda, mostrando el nombre, el máximo stock permitido y el saldo en caja.

comprarProducto: Este método maneja la compra de productos, actualizando el stock y el saldo en caja. Primero verifica si el producto ya existe en stock; si es así, actualiza la cantidad. Si el producto no existe, verifica si se puede agregar sin exceder el máximo stock permitido. También clasifica los productos según su tipo e incluye validaciones para evitar errores de stock y saldo insuficiente.

venderProductos: Maneja la venta de productos con una lista de productos a comprar y sus cantidades. Este método incluye validaciones como el límite de productos por venta y unidades por producto. Calcula el precio de venta aplicando descuentos y ajusta el saldo en caja según el total de la venta. También actualiza el stock y la disponibilidad de los productos vendidos.

obtenerComestiblesConMenorDescuento: Filtra y retorna una lista de productos comestibles (envasados y bebidas) que no son importados y cuyo descuento es menor a un porcentaje dado. Los productos se ordenan por precio y las descripciones se convierten a mayúsculas.

La elección de ArrayList para las listas de productos permite una gestión flexible y eficiente del stock. El uso de stream() mejora la legibilidad del código al realizar operaciones como filtrado y ordenación. La clasificación de productos y la verificación de stock y saldo en caja aseguran una gestión precisa y organizada. En futuras versiones, se recomienda delegar algunas responsabilidades a clases o interfaces adicionales para mejorar la modularidad y facilitar futuras extensiones del sistema.
