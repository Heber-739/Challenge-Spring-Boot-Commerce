Challenge:

Desarrollar una api rest para una tienda, donde un usuario (el dueño de la tienda) pueda llevar un registro sobre sus proveedores, clientes, productos y ventas realizadas.

De cada cliente, se debe saber su nombre, apellido, dni, teléfono, dirección ().
La información necesaria de cada proveedor es: nombre, cuit, teléfono, dirección y productos que provee.
Los productos deben contar con un nombre, descripción, precio y stock.
Las ventas deben ser registradas con la fecha, cliente, productos, cantidades y precio total.

El usuario del sistema debe poder:

•	Consultar el listado de todos sus proveedores, clientes, productos o ventas, como así también la descripción de cada uno de ellos. Cada cliente debe tener las compras que ha realizado.

•	Dar de alta/baja o editar a un cliente. Se debe poder dar de alta nuevamente a cualquier registro que haya sido dado de baja anteriormente, y este conservaría su estado previo al proceso de baja.

•	Asentar ventas realizadas.

•	Consultar un listado con los productos que tienen un stock bajo, junto con los datos de los proveedores que lo venden. En cada consulta se debe poder determinar cuál será el stock considerado como "bajo", no es un valor fijo.

•	Consultar las ventas realizadas en X día. (por campo Date) 
