package interfaces;

import java.util.List;

import entidades.Categoria;
import entidades.Coleccion;
import entidades.Producto;

public interface ProductoInterface {

	List<Categoria> listarCategorias();
	
	List<Coleccion> listarColeccionesSegunCategoria(String categoria);
	
	List<Coleccion> listarColecciones();
	
	List<Producto> listarProductos();
	
	List<Producto> listarProductosSegunCategoria(String categoria);
	
	List<Producto> listarProductosSegunColeccion(String coleccion);
	
	List<Producto> listarProductosSegunNombre(String nombre);
	
	List<Producto> listarProductosSegunRangoPrecios(double desde, double hasta);
	
	List<Producto> listarProductosSegunPrecio(String direccion);
	
	Producto obtenerProducto(int idProducto);
}
