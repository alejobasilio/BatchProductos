package com.alejobasilio.batch2.model;

/**
 * Clase que representa un producto, con atributos para id, nombre del producto, marca, precio y moneda.
 * Proporciona constructores y métodos getter y setter para acceder y modificar los atributos.
 * 
 * @author Alejo Basilio Alfonso
 * @version 1.0
 * @since 1.0
 */
public class Producto {

	private Long id;
	private String producto;
	private String marca;
	private Double precio;
	private String moneda;

	public Producto(Long id, String producto, String marca, Double precio, String moneda) {
		super();
		this.id = id;
		this.producto = producto;
		this.marca = marca;
		this.precio = precio;
		this.moneda = moneda;
	}

	public Producto() {
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

}
