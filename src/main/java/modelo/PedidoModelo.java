package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.DetallePedido;
import entidades.Pedido;
import interfaces.PedidoInterface;
import util.Constantes;

public class PedidoModelo implements PedidoInterface {

	@Override
	public List<Pedido> listarPedidos(String dniCliente, String correo) {

		List<Pedido> pedidos = new ArrayList<Pedido>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = MysqlConexion.getConexion();
			String query = "SELECT id, numero, fecha, direccion, correo, total_pedido, "
					+ "dni_cliente FROM pedido WHERE numero Like ? and dni_cliente = ?";
			ps = cn.prepareStatement(query);
			ps.setString(1, "%" + dniCliente + "%");
			ps.setString(2, correo);

			rs = ps.executeQuery();

			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("id"));
				pedido.setNumero(rs.getString("numero"));
				pedido.setFecha(rs.getDate("fecha"));
				pedido.setDireccion(rs.getString("direccion"));
				pedido.setCorreo(rs.getString("correo"));
				pedido.setTotalPedido(rs.getDouble("total_pedido"));
				pedido.setDniCliente(rs.getString("dni_cliente"));

				pedidos.add(pedido);
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

		return pedidos;
	}

	@Override
	public Pedido obtenerPedido(int id) {

		Pedido pedido = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = MysqlConexion.getConexion();
			String query = "SELECT id, numero, fecha, subtotal, descuentos, total_pedido, "
					+ "dni_cliente, estado FROM pedido WHERE id = ?";
			ps = cn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				pedido = new Pedido();
				pedido.setId(rs.getInt("id"));
				pedido.setNumero(rs.getString("numero"));
				pedido.setFecha(rs.getDate("fecha"));
				pedido.setSubtotal(rs.getDouble("subtotal"));
				pedido.setDescuentos(rs.getDouble("descuentos"));
				pedido.setTotalPedido(rs.getDouble("total_pedido"));
				pedido.setDniCliente(rs.getString("dni_cliente"));
				pedido.setEstado(rs.getInt("estado"));
			}

			List<DetallePedido> detalle = obtenerDetalle(id+"");

			pedido.setDetalle(detalle);

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

		return pedido;
	}

	@Override
	public Pedido obtenerPedidoSegunNumero(String numeroPedido) {

		Pedido pedido = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = MysqlConexion.getConexion();
			String query = "SELECT id, numero, fecha, subtotal, descuentos, total_pedido, "
					+ "dni_cliente, estado FROM pedido WHERE numero = ?";
			ps = cn.prepareStatement(query);
			ps.setString(1, numeroPedido);

			rs = ps.executeQuery();

			if (rs.next()) {
				pedido = new Pedido();
				pedido.setId(rs.getInt("id"));
				pedido.setNumero(rs.getString("numero"));
				pedido.setFecha(rs.getDate("fecha"));
				pedido.setSubtotal(rs.getDouble("subtotal"));
				pedido.setDescuentos(rs.getDouble("descuentos"));
				pedido.setTotalPedido(rs.getDouble("total_pedido"));
				pedido.setDniCliente(rs.getString("dni_cliente"));
				pedido.setEstado(rs.getInt("estado"));
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

		return pedido;
	}

	@Override
	public List<DetallePedido> obtenerDetalle(String id) {

		List<DetallePedido> listado = new ArrayList<DetallePedido>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			cn = MysqlConexion.getConexion();
			String query = "SELECT dp.id, dp.id_pedido, posicion, dp.id_producto,pr.nombre as 'producto' ,pr.descri, dp.precio_producto, dp.cantidad, "
					+ "dp.subtotal, dp.descuentos, dp.total_detalle " + "FROM detalle_pedido dp "
					+ "LEFT JOIN pedido p ON  p.id = dp.id_pedido "
					+ "LEFT JOIN producto pr ON pr.idProd = dp.id_producto " + "WHERE dp.id_pedido = ?";
			ps = cn.prepareStatement(query);
			ps.setString(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				DetallePedido tmp = new DetallePedido();
				tmp.setIdPedido(rs.getString("id_pedido"));
				tmp.setDescripcion(rs.getString("producto"));
				tmp.setPrecioProducto(rs.getDouble("precio_producto"));
				tmp.setCantidad(rs.getInt("cantidad"));
				tmp.setSubtotal(rs.getDouble("subtotal"));
				tmp.setTotalDetalle(rs.getDouble("total_detalle"));
				listado.add(tmp);
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

		return listado;
	}

	@Override
	public int registrarPedido(Pedido nuevo) {

		int value = -1;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = MysqlConexion.getConexion();
			String sql = "INSERT INTO pedido VALUES(null, ?, now(), ?, ?, ?, ?, 0)";
			ps = cn.prepareStatement(sql);

			ps.setString(1, nuevo.getNumero());
			ps.setString(2, nuevo.getDireccion());
			ps.setString(3, nuevo.getCorreo());
			ps.setDouble(4, nuevo.getSubtotal());
			ps.setString(5, nuevo.getDniCliente());

			value = ps.executeUpdate();

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	@Override
	public int registrarDetalle(DetallePedido detalle) {
		int value = -1;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = MysqlConexion.getConexion();
			String sql = "INSERT INTO detalle_pedido VALUES(null, ?,0, ?, ?, ?, ?, null, null)";
			ps = cn.prepareStatement(sql);

			ps.setString(1, detalle.getIdPedido());
			ps.setInt(2, detalle.getIdProducto());
			ps.setDouble(3, detalle.getPrecioProducto());
			ps.setInt(4, detalle.getCantidad());
			ps.setDouble(5, detalle.getSubtotal());

			value = ps.executeUpdate();

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	@Override
	public int editarPedido(Pedido pedido) {

		int value = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = MysqlConexion.getConexion();
			String sql = "UPDATE pedido SET subtotal = ?, descuentos = ?, total_pedido = ?, estado = ? WHERE id = ?";

			ps = cn.prepareStatement(sql);

			ps.setDouble(1, pedido.getSubtotal());
			ps.setDouble(2, pedido.getDescuentos());
			ps.setDouble(3, pedido.getTotalPedido());
			ps.setInt(4, pedido.getEstado());
			ps.setInt(5, pedido.getId());

			value = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	@Override
	public int editarDetalle(DetallePedido detalle) {

		int value = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = MysqlConexion.getConexion();
			String sql = "UPDATE detalle_pedido SET cantidad = ?, descuentos = ?, total_detalle = ? WHERE id = ?";

			ps = cn.prepareStatement(sql);

			ps.setInt(1, detalle.getCantidad());
			ps.setDouble(2, detalle.getDescuentos());
			ps.setDouble(3, detalle.getTotalDetalle());
			ps.setInt(4, detalle.getId());

			value = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	@Override
	public int eliminarPedido(int idPedido) {

		int salida = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = MysqlConexion.getConexion();

			String sql = "DELETE FROM pedido WHERE id = ?";
			ps = cn.prepareStatement(sql);

			ps.setInt(1, idPedido);

			salida = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return salida;
	}

	@Override
	public int eliminarDetalle(int idPedido) {

		int salida = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = MysqlConexion.getConexion();

			String sql = "DELETE FROM detalle_pedido WHERE id_pedido = ?";
			ps = cn.prepareStatement(sql);

			ps.setInt(1, idPedido);

			salida = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return salida;
	}

	@Override
	public int actualizarValoresPedidos() {

		int value = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {

			cn = MysqlConexion.getConexion();
			String sql = "UPDATE pedido p SET\r\n"
					+ "subtotal = (SELECT SUM(subtotal) FROM detalle_pedido WHERE id_pedido = p.id),\r\n"
					+ "descuentos = (SELECT SUM(descuentos) FROM detalle_pedido WHERE id_pedido = p.id),\r\n"
					+ "total_pedido = (SELECT SUM(total_detalle) FROM detalle_pedido WHERE id_pedido = p.id);";
			ps = cn.prepareStatement(sql);

			value = ps.executeUpdate();

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}
}
