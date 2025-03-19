Ejercicios Spring Batch básicos
Se proporciona el fichero "datos_entrada.csv", en este se contiene un listado de productos cuya estructura es la siguiente:
id - producto - marca - precio - moneda
--------------------------------------------------------------------------------------------------------------------------

1. Generar un fichero EuroProductos.csv de todos los productos cuya moneda sea "Euro", el csv debe tener la siguiente estructura:
id - producto - marca – precio
--------------------------------------------------------------------------------------------------------------------------
2. Generar un fichero LGProductos.csv que haga el siguiente filtrado:
	- La marca debe ser "LG"
	- La moneda debe ser alguna de "Euro" - "Dollar" - "Pound"
  Adicionalmente según el tipo de moneda debe calcular un nuevo precio:
	- "Euro" = 20% aumento
	- "Dollar" = 15% descuento
	- "Pound" = 5% aumento si el precio es < 30 | 5% descuento si el precio es > 80
Se debe recoger la alteración del precio (la diferencia).
La estructura del csv debe ser la siguiente:
id - producto - precio_inicial - precio_final - alteracion_precio - moneda
--------------------------------------------------------------------------------------------------------------------------
3. Pasar a BBDD los datos del fichero de entrada.
