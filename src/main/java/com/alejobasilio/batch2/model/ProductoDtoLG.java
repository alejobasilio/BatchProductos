package com.alejobasilio.batch2.model;

/**
 * Clase que representa un producto con información adicional para el análisis de precios, 
 * incluyendo el id, nombre del producto, precio inicial, precio final, alteración del precio y moneda.
 * 
 * @author Alejo Basilio Alfonso
 * @version 1.0
 * @since 1.0
 */
public class ProductoDtoLG {

	private Long id;
	private String producto;
	private Double precio_inicial;
	private Double precio_final;
	private Double aleteracion_precio;
	private String moneda;

	public ProductoDtoLG() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Double getPrecio_inicial() {
		return precio_inicial;
	}

	public void setPrecio_inicial(Double precio_inicial) {
		this.precio_inicial = precio_inicial;
	}

	public Double getPrecio_final() {
		return precio_final;
	}

	public void setPrecio_final(Double precio_final) {
		this.precio_final = precio_final;
	}

	public Double getAleteracion_precio() {
		return aleteracion_precio;
	}

	public void setAleteracion_precio(Double aleteracion_precio) {
		this.aleteracion_precio = aleteracion_precio;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

}
