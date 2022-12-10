package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.Categoria;
import entidades.Coleccion;
import entidades.Producto;
import interfaces.ProductoInterface;

public class ProductoModel implements ProductoInterface {

	@Override
	public List<Categoria> listarCategorias() {

		List<Categoria> listadoCategorias = new ArrayList<Categoria>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT idCat, nombre FROM categoria;";
			
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idCat"));
				categoria.setNombre(rs.getString("nombre"));
				
				listadoCategorias.add(categoria);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoCategorias;
	}

	@Override
	public List<Coleccion> listarColeccionesSegunCategoria(String categoria) {

		List<Coleccion> listadoColecciones = new ArrayList<Coleccion>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT c.idColl, c.tipoCat, ca.nombre as categoria, c.nombre\r\n"
					+ "FROM colleccion c\r\n"
					+ "LEFT JOIN categoria ca ON ca.idCat = c.tipoCat\r\n"
					+ "WHERE ca.nombre = ?;";
			
			ps = cn.prepareStatement(query);
			ps.setString(1, categoria);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Coleccion coleccion = new Coleccion();
				coleccion.setIdColeccion(rs.getInt("idColl"));
				coleccion.setIdCategoria(rs.getInt("tipoCat"));
				coleccion.setCategoria(rs.getString("categoria"));
				coleccion.setNombre(rs.getString("nombre"));
				
				listadoColecciones.add(coleccion);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoColecciones;
	}

	@Override
	public List<Coleccion> listarColecciones() {
		
		List<Coleccion> listadoColecciones = new ArrayList<Coleccion>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT c.idColl, c.tipoCat, ca.nombre as categoria, c.nombre\r\n"
					+ "FROM colleccion c\r\n"
					+ "LEFT JOIN categoria ca ON ca.idCat = c.tipoCat;";
			
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Coleccion coleccion = new Coleccion();
				coleccion.setIdColeccion(rs.getInt("idColl"));
				coleccion.setIdCategoria(rs.getInt("tipoCat"));
				coleccion.setCategoria(rs.getString("categoria"));
				coleccion.setNombre(rs.getString("nombre"));
				
				listadoColecciones.add(coleccion);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoColecciones;
	}

	@Override
	public List<Producto> listarProductos() {
		
		List<Producto> listadoProductos = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT p.idProd, c.nombre as categoria, p.tipoCat, co.nombre as coleccion,"
					+ " p.nombre, p.descri, p.stock, p.precio\r\n"
					+ "FROM producto p\r\n"
					+ "LEFT JOIN colleccion co ON co.idColl = p.tipoCat\r\n"
					+ "LEFT JOIN categoria c ON c.idCat = co.tipoCat;";
			
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProd"));
				producto.setCategoria(rs.getString("categoria"));
				producto.setIdColeccion(rs.getInt("tipoCat"));
				producto.setColeccion(rs.getString("coleccion"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descri"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("precio"));
				
				listadoProductos.add(producto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoProductos;
	}

	@Override
	public List<Producto> listarProductosSegunCategoria(String categoria) {
		
		List<Producto> listadoProductos = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT p.idProd, c.nombre as categoria, p.tipoCat, co.nombre as coleccion,"
					+ " p.nombre, p.descri, p.stock, p.precio\r\n"
					+ "FROM producto p\r\n"
					+ "LEFT JOIN colleccion co ON co.idColl = p.tipoCat\r\n"
					+ "LEFT JOIN categoria c ON c.idCat = co.tipoCat "
					+ "WHERE c.nombre = ?;";
			
			ps = cn.prepareStatement(query);
			ps.setString(1, categoria);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProd"));
				producto.setCategoria(rs.getString("categoria"));
				producto.setIdColeccion(rs.getInt("tipoCat"));
				producto.setColeccion(rs.getString("coleccion"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descri"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("precio"));
				
				listadoProductos.add(producto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoProductos;
	}

	@Override
	public List<Producto> listarProductosSegunColeccion(String coleccion) {
		
		List<Producto> listadoProductos = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT p.idProd, c.nombre as categoria, p.tipoCat, co.nombre as coleccion,"
					+ " p.nombre, p.descri, p.stock, p.precio\r\n"
					+ "FROM producto p\r\n"
					+ "LEFT JOIN colleccion co ON co.idColl = p.tipoCat\r\n"
					+ "LEFT JOIN categoria c ON c.idCat = co.tipoCat "
					+ "WHERE co.nombre = ?;";
			
			ps = cn.prepareStatement(query);
			ps.setString(1, coleccion);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProd"));
				producto.setCategoria(rs.getString("categoria"));
				producto.setIdColeccion(rs.getInt("tipoCat"));
				producto.setColeccion(rs.getString("coleccion"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descri"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("precio"));
				
				listadoProductos.add(producto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoProductos;
	}

	@Override
	public List<Producto> listarProductosSegunNombre(String nombre) {
		
		List<Producto> listadoProductos = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT p.idProd, c.nombre as categoria, p.tipoCat, co.nombre as coleccion,"
					+ " p.nombre, p.descri, p.stock, p.precio\r\n"
					+ "FROM producto p\r\n"
					+ "LEFT JOIN colleccion co ON co.idColl = p.tipoCat\r\n"
					+ "LEFT JOIN categoria c ON c.idCat = co.tipoCat "
					+ "WHERE p.nombre LIKE ? OR p.descri LIKE ?;";
			
			ps = cn.prepareStatement(query);
			ps.setString(1, "%" + nombre + "%");
			ps.setString(2, "%" + nombre + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProd"));
				producto.setCategoria(rs.getString("categoria"));
				producto.setIdColeccion(rs.getInt("tipoCat"));
				producto.setColeccion(rs.getString("coleccion"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descri"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("precio"));
				
				listadoProductos.add(producto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoProductos;
	}

	@Override
	public List<Producto> listarProductosSegunRangoPrecios(double desde, double hasta) {
		
		List<Producto> listadoProductos = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT p.idProd, c.nombre as categoria, p.tipoCat, co.nombre as coleccion,"
					+ " p.nombre, p.descri, p.stock, p.precio\r\n"
					+ "FROM producto p\r\n"
					+ "LEFT JOIN colleccion co ON co.idColl = p.tipoCat\r\n"
					+ "LEFT JOIN categoria c ON c.idCat = co.tipoCat "
					+ "WHERE p.precio BETWEEN ? AND ?"
					+ "ORDER BY p.precio ASC;";
			
			ps = cn.prepareStatement(query);
			ps.setDouble(1, desde);
			ps.setDouble(2, hasta);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProd"));
				producto.setCategoria(rs.getString("categoria"));
				producto.setIdColeccion(rs.getInt("tipoCat"));
				producto.setColeccion(rs.getString("coleccion"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descri"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("precio"));
				
				listadoProductos.add(producto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoProductos;
	}

	@Override
	public List<Producto> listarProductosSegunPrecio(String direccion) {
		
		List<Producto> listadoProductos = new ArrayList<Producto>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT p.idProd, c.nombre as categoria, p.tipoCat, co.nombre as coleccion,"
					+ " p.nombre, p.descri, p.stock, p.precio\r\n"
					+ "FROM producto p\r\n"
					+ "LEFT JOIN colleccion co ON co.idColl = p.tipoCat\r\n"
					+ "LEFT JOIN categoria c ON c.idCat = co.tipoCat "
					+ "ORDER BY p.precio " + direccion;
			
			ps = cn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProd"));
				producto.setCategoria(rs.getString("categoria"));
				producto.setIdColeccion(rs.getInt("tipoCat"));
				producto.setColeccion(rs.getString("coleccion"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descri"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("precio"));
				
				listadoProductos.add(producto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listadoProductos;
	}

	@Override
	public Producto obtenerProducto(int idProducto) {
		
		Producto producto = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String query = "SELECT p.idProd,p.nombre as 'producto', c.nombre as categoria, p.tipoCat, co.nombre as coleccion,"
					+ " p.nombre, p.descri, p.stock, p.precio\r\n"
					+ "FROM producto p\r\n"
					+ "LEFT JOIN colleccion co ON co.idColl = p.tipoCat\r\n"
					+ "LEFT JOIN categoria c ON c.idCat = co.tipoCat "
					+ "WHERE p.idProd = ?;";
			
			ps = cn.prepareStatement(query);
			ps.setInt(1, idProducto);
			rs = ps.executeQuery();
			
			if  (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getInt("idProd"));
				producto.setNomPro(rs.getString("producto"));
				producto.setCategoria(rs.getString("categoria"));
				producto.setIdColeccion(rs.getInt("tipoCat"));
				producto.setColeccion(rs.getString("coleccion"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descri"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecio(rs.getDouble("precio"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return producto;
	}

}
