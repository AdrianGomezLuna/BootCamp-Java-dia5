package com.dia5.test1Junit.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dia5.test1Junit.bbdd.BaseDatosImpl;
import com.dia5.test1Junit.model.Articulo;


@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {
	
	@InjectMocks
	private CarritoCompraServiceImpl carritoService;
		
	@Mock
	private BaseDatosImpl basedatos;
	
	//Esto solo para cuando no esté moskito
	/*@BeforeEach
	void setUp() throws Exception {
		carritoService = new CarritoCompraServiceImpl();
	}*/

	@Test
	void testLimpiarCesta() {		
		//SIn articulos
		assertTrue(carritoService.getArticulo().isEmpty());
		carritoService.limpiarCesta();
		assertEquals(0, carritoService.getNumArticulo());
		assertTrue(carritoService.getArticulo().isEmpty());
		//Con articulos
		Articulo articulo = new Articulo("Camiseta", 18.99);
		carritoService.addArtiuclo(articulo);
		assertEquals(1, carritoService.getNumArticulo());
		carritoService.limpiarCesta();
		assertEquals(0, carritoService.getNumArticulo());
	}

	@Test
	void testAddArtiuclo() {
		assertTrue(carritoService.getArticulo().isEmpty());
		carritoService.addArtiuclo(new Articulo("casmisa", 15.99));
		assertFalse(carritoService.getArticulo().isEmpty());		
	}

	@Test
	void testGetNumArticulo() {
		//Vacía
		assertEquals(0, carritoService.getNumArticulo());
		assertTrue(carritoService.getArticulo().isEmpty());
		//Con artículos
		Articulo articulo = new Articulo("Camiseta", 18.99);
		carritoService.addArtiuclo(articulo);
		Articulo articulo2 = new Articulo("Camiseta", 18.99);
		carritoService.addArtiuclo(articulo2);
		assertEquals(2, carritoService.getNumArticulo());
		assertFalse(carritoService.getArticulo().isEmpty());
	}

	@Test
	void testGetArticulo() {
		//Vacía
		assertEquals(0, carritoService.getNumArticulo());
		//Con artículos
		Articulo articulo = new Articulo("Camiseta", 18.99);
		carritoService.addArtiuclo(articulo);
		Articulo articulo2 = new Articulo("Camiseta", 18.99);
		carritoService.addArtiuclo(articulo2);
		assertEquals(2, carritoService.getNumArticulo());
		List<Articulo> lista = carritoService.getArticulo();
		assertEquals(articulo2, lista.get(1));
		assertFalse(carritoService.getArticulo().isEmpty());
	}

	@Test
	void testTotalPrecio() {
		//Vacía
		assertEquals(0D, carritoService.totalPrecio());
		//Con artículos
		Articulo articulo = new Articulo("Camiseta", 18.99);
		carritoService.addArtiuclo(articulo);
		Articulo articulo2 = new Articulo("Camiseta", 18.99);
		carritoService.addArtiuclo(articulo2);
		assertEquals(37.98, carritoService.totalPrecio());
	}

	@Test
	void testCalcularDescuento() {
		// 50 % descuento
		assertEquals(5D, carritoService.calcularDescuento(10D,50D));
	}
	
	@Test
	void testAplicarDescuento() {
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(basedatos.findArticuloById(any(Integer.class))).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(18D, res);		
		//Con lo verify
		verify(basedatos).findArticuloById(any(Integer.class));
	}
	
	@Test
	void testInsertar() {
		Articulo articulo = new Articulo("chandal", 20.00);
		when(basedatos.insertarArticulo(any(Articulo.class))).thenReturn(0);
		Integer identificador = carritoService.insertar(articulo);
		List<Articulo> articulos = carritoService.getArticulo();
		
		assertEquals(0, identificador);
		assertEquals("chandal", articulos.get(identificador).getName());
		assertEquals(20D, articulos.get(identificador).getPrecio());

		verify(basedatos, atLeast(1)).insertarArticulo(any(Articulo.class));
	}

}
