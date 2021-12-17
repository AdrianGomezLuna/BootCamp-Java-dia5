package com.dia5.test1Junit.model;

public class Articulo {
	
	private String name;
	
	private Double precio;

	public Articulo(String name, double precio) {
		this.name = name;
		this.precio = precio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
}
