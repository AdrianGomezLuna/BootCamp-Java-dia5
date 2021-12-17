package com.dia5.test1Junit.servicios;

import java.util.List;

import com.dia5.test1Junit.model.Articulo;

public interface CarritoCompraServiceI {
	
	public void limpiarCesta();
	
	public void addArtiuclo(Articulo articulo);
	
	public Integer getNumArticulo();

	public List<Articulo> getArticulo();
	
	public Double totalPrecio();
	
	public Double calcularDescuento(double precio, double porcentajeDescuento);
	
	public Double aplicarDescuento(Integer id, Double descuento);
}
