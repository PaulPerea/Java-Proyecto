package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dao.DAOFactory;
import entidades.DetallePedido;
import entidades.Pedido;
import entidades.Producto;
import interfaces.PedidoInterface;
import interfaces.ProductoInterface;

@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PedidoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String operacion = req.getParameter("type");

		if (operacion.equals("listar")) {
			listar(req, res);
		}
		if (operacion.equals("registrar")) {
			registrar(req, res);
		}

		if (operacion.equals("eliminar")) {
			eliminar(req, res);
		}

	}

	protected void listar(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String clave = req.getParameter("txtClave");
		String dni = req.getParameter("txtDni");
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		PedidoInterface pedidoService = daoFactory.getPedido();

		List<Pedido> pedidoUnico = pedidoService.listarPedidos(clave, dni);

		req.setAttribute("pedidos", pedidoUnico);
		req.getRequestDispatcher("pedidos.jsp").forward(req, res);
	}

	protected void listarDetalle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String clave = req.getParameter("txtClave");
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		PedidoInterface pedidoService = daoFactory.getPedido();
		System.out.println(clave);
		List<DetallePedido> pedidoUnico = pedidoService.obtenerDetalle(clave);

		req.setAttribute("detalle", pedidoUnico);
		req.getRequestDispatcher("pedidos.jsp").forward(req, res);
	}

	protected void registrar(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/*
		 * JsonObject data = body; Gson gson = new Gson(); PrintWriter writer =
		 * res.getWriter(); res.setContentType("application/json"); String mensaje = "";
		 * 
		 * if (!data.has("dniCliente")) { mensaje = "dniCliente requerido";
		 * writer.print(gson.toJson(mensaje)); writer.flush(); writer.close(); return; }
		 * 
		 * if (!data.has("numeroPedido")) { mensaje = "numeroPedido requerido";
		 * writer.print(gson.toJson(mensaje)); writer.flush(); writer.close(); return; }
		 * 
		 * if (!data.has("detalle")) { mensaje = "detalle requerido";
		 * writer.print(gson.toJson(mensaje)); writer.flush(); writer.close(); return; }
		 * 
		 * String dniCliente = data.get("dniCliente").getAsString(); String numeroPedido
		 * = data.get("numeroPedido").getAsString(); JsonArray detalle =
		 * data.get("detalle").getAsJsonArray();
		 * 
		 * DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		 * PedidoInterface pedidoService = daoFactory.getPedido();
		 * 
		 * Pedido nuevo = new Pedido(); nuevo.setDniCliente(dniCliente);
		 * nuevo.setNumero(numeroPedido);
		 * 
		 * int value = pedidoService.registrarPedido(nuevo); Pedido p =
		 * pedidoService.obtenerPedidoSegunNumero(numeroPedido); List<Integer> values =
		 * new ArrayList<Integer>();
		 * 
		 * for (JsonElement i : detalle) { JsonObject item = i.getAsJsonObject();
		 * 
		 * DetallePedido d = new DetallePedido();
		 * d.setPosicion(item.get("posicion").getAsInt());
		 * d.setIdProducto(item.get("idProducto").getAsInt());
		 * d.setPrecioProducto(item.get("precio").getAsDouble());
		 * d.setCantidad(item.get("cantidad").getAsInt());
		 * d.setSubtotal(item.get("subtotal").getAsDouble());
		 * d.setDescuentos(item.get("descuentos").getAsDouble());
		 * d.setTotalDetalle(item.get("total").getAsDouble());
		 * 
		 * int val = pedidoService.registrarDetalle(d); values.add(val); }
		 * 
		 * if (value == 1) { for (int i : values) { if (i != 1) { mensaje =
		 * "error en el registro"; writer.print(gson.toJson(mensaje)); writer.flush();
		 * writer.close(); return; } } }
		 * 
		 * int v = pedidoService.actualizarValoresPedidos();
		 * 
		 * mensaje = "registro exitoso"; writer.print(gson.toJson(mensaje));
		 * writer.flush(); writer.close();
		 */

	}

	protected void eliminar(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		/*
		 * JsonObject data = body; Gson gson = new Gson(); PrintWriter writer =
		 * res.getWriter(); res.setContentType("application/json"); String mensaje = "";
		 * 
		 * if (!data.has("dniCliente")) { mensaje = "dniCliente requerido";
		 * writer.print(gson.toJson(mensaje)); writer.flush(); writer.close(); return; }
		 * 
		 * if (!data.has("idPedido")) { mensaje = "idPedido requerido";
		 * writer.print(gson.toJson(mensaje)); writer.flush(); writer.close(); return; }
		 * 
		 * int idPedido = data.get("idPedido").getAsInt(); DAOFactory daoFactory =
		 * DAOFactory.getDaoFactory(DAOFactory.MYSQL); PedidoInterface pedidoService =
		 * daoFactory.getPedido();
		 * 
		 * int value = pedidoService.eliminarDetalle(idPedido);
		 * 
		 * int valuee = pedidoService.eliminarPedido(idPedido);
		 * 
		 * if (value != 0 && valuee == 1) { mensaje = "eliminaci�n exitosa";
		 * writer.print(gson.toJson(mensaje)); writer.flush(); writer.close(); return; }
		 * 
		 * mensaje = "error en la eliminaci�n"; writer.print(gson.toJson(mensaje));
		 * writer.flush(); writer.close();
		 */
	}
}
