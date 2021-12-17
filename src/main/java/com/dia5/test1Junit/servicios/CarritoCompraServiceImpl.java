package com.dia5.test1Junit.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dia5.test1Junit.bbdd.BaseDatosImpl;
import com.dia5.test1Junit.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI{
	
	private List<Articulo> cesta = new ArrayList<>();
	
	@Autowired
	private BaseDatosImpl baseDatos;

	@Override
	public void limpiarCesta() {
		cesta.clear();		
	}

	@Override
	public void addArtiuclo(Articulo articulo) {
		cesta.add(articulo);		
	}

	@Override
	public Integer getNumArticulo() {		
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulo() {
		return cesta;
	}

	@Override
	public Double totalPrecio() {
		Double total = 0D;
		for(Articulo articulo : cesta) {
			total = total + articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calcularDescuento(double precio, double porcentajeDescuento) {
		return precio - precio*porcentajeDescuento/100;
	}

	@Override
	public Double aplicarDescuento(Integer id, Double descuento) {
		
		Double resul = null;
		Articulo articulo = baseDatos.findArticuloById(id);
		if (Optional.ofNullable(articulo).isPresent()) {
			resul = calcularDescuento(articulo.getPrecio(), descuento);
		} else {
			System.out.println("No se ha encontrado articulo con ID " + id);
		}
				
		return resul;
	}

	@Override
	public Integer insertar(Articulo articulo) {
		Integer  identificador = baseDatos.insertarArticulo(articulo);
		cesta.add(articulo);
		return identificador;
	}
	

}
