package com.dia5.test1Junit.bbdd;

import com.dia5.test1Junit.model.Articulo;

public interface BaseDatosI {
	
	public Integer insertarArticulo(Articulo articulo);
	
	public Articulo findArticuloById(Integer identificador);
	
	public void iniciar();

}
