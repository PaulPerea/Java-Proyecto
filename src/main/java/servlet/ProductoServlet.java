package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.DAOFactory;
import entidades.Categoria;
import entidades.Coleccion;
import entidades.DetallePedido;
import entidades.Pedido;
import entidades.Producto;
import interfaces.PedidoInterface;
import interfaces.ProductoInterface;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String categoria = req.getParameter("categoria");
		String coleccion = req.getParameter("coleccion");
		String nombre = req.getParameter("txtNombre");
		String desde = req.getParameter("desde");
		String hasta = req.getParameter("hasta");
		String precio = req.getParameter("precio");
		String idProducto = req.getParameter("idProducto");
		String eliProducto = req.getParameter("idProductoEli");
		String detalle = req.getParameter("detalle");
		String type = req.getParameter("type");
		System.out.println(type);

		if (categoria != null) {
			listarProductosSegunCategoria(categoria, res);
			return;
		}

		if (coleccion != null) {
			listarProductosSegunColeccion(coleccion, req, res);
			return;
		}

		if (nombre != null) {
			listarProductosSegunNombre(nombre, req, res);
			return;
		}

		if (desde != null || hasta != null) {
			listarProductosSegunRangoPrecios(desde, hasta, res);
			return;
		}

		if (precio != null) {
			listarProductosSegunPrecio(precio, res);
			return;
		}

		if (idProducto != null) {
			obtenerProducto(Integer.parseInt(idProducto), req, res);
			return;
		}
		if (eliProducto != null) {
			eliminarCarrito(Integer.parseInt(eliProducto), req, res);
			return;
		}
		if (detalle != null) {
			listarDetalle(req, res);
			return;
		}

		if (type != null) {
			registrarPedido(req, res);
		}

		listarProductos(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
		Gson gson = new Gson();
		PrintWriter writer = res.getWriter();
		String mensaje = "";

		if (!data.has("entidad")) {
			mensaje = "entidad requerido";
			writer.print(gson.toJson(mensaje));
			writer.flush();
			writer.close();
			return;
		}

		String operacion = data.get("entidad").getAsString();

		if (operacion.equals("categorias")) {
			listarCategorias(res);
		}

		if (operacion.equals("colecciones")) {
			listarColecciones(res);
		}
	}

	private void listarProductos(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Producto> listadoProductos = productoService.listarProductos();
		request.setAttribute("productos", listadoProductos);
		request.getRequestDispatcher("productos.jsp").forward(request, res);
	}

	private void listarProductosSegunCategoria(String categoria, HttpServletResponse res)
			throws ServletException, IOException {

		Gson gson = new Gson();
		PrintWriter writer = res.getWriter();
		res.setContentType("application/json");

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Producto> listadoProductos = productoService.listarProductosSegunCategoria(categoria);

		writer.print(gson.toJson(listadoProductos));
		writer.flush();
		writer.close();
	}

	private void listarProductosSegunColeccion(String coleccion, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Producto> listadoProductos = productoService.listarProductosSegunColeccion(coleccion);

		req.setAttribute("productos", listadoProductos);
		req.getRequestDispatcher("productos.jsp").forward(req, res);
	}

	private void listarProductosSegunNombre(String nombre, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Producto> listadoProductos = productoService.listarProductosSegunNombre(nombre);

		req.setAttribute("productos", listadoProductos);
		req.getRequestDispatcher("productos.jsp").forward(req, res);
	}

	private void listarProductosSegunRangoPrecios(String desde, String hasta, HttpServletResponse res)
			throws ServletException, IOException {

		double desdeVal = 0;
		double hastaVal = 9999999;

		if (desde != null) {
			desdeVal = Double.parseDouble(desde);
		}

		if (hasta != null) {
			hastaVal = Double.parseDouble(hasta);
		}

		Gson gson = new Gson();
		PrintWriter writer = res.getWriter();
		res.setContentType("application/json");

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Producto> listadoProductos = productoService.listarProductosSegunRangoPrecios(desdeVal, hastaVal);

		writer.print(gson.toJson(listadoProductos));
		writer.flush();
		writer.close();
	}

	private void listarProductosSegunPrecio(String direccion, HttpServletResponse res)
			throws ServletException, IOException {

		Gson gson = new Gson();
		PrintWriter writer = res.getWriter();
		res.setContentType("application/json");

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Producto> listadoProductos = productoService.listarProductosSegunPrecio(direccion);

		writer.print(gson.toJson(listadoProductos));
		writer.flush();
		writer.close();
	}

	List<DetallePedido> listpro = new ArrayList<>();
	double totalCanasta = 0.0;

	private void obtenerProducto(int idProducto, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
			ProductoInterface productoService = daoFactory.getProducto();

			Producto pro = productoService.obtenerProducto(idProducto);

			DetallePedido de = new DetallePedido();
			de.setIdProducto(pro.getIdProducto());
			de.setNomPro(pro.getNomPro());
			de.setDescripcion(pro.getDescripcion());
			de.setCantidad(1);
			de.setPrecioProducto(pro.getPrecio());
			de.setSubtotal(pro.getPrecio());

			Optional<DetallePedido> valor = listpro.stream().filter(x -> x.getIdProducto() == pro.getIdProducto())
					.findFirst();

			if (!valor.isEmpty()) {
				for (DetallePedido des : listpro) {
					totalCanasta = 0.0;
					if (des.getIdProducto() == pro.getIdProducto()) {
						des.setCantidad(des.getCantidad() + 1);
						des.setSubtotal(des.getCantidad() * pro.getPrecio());
						req.setAttribute("totalCanasta", totalCanasta);
					}
					totalCanasta += des.getSubtotal();
				}
			} else {
				listpro.add(de);
			}

			if (listpro.size() > 0) {
				totalCanasta = 0.0;
				listpro.forEach(x -> {
					totalCanasta += x.getSubtotal();
				});
			}

			req.setAttribute("totalCanasta", totalCanasta + "");
			req.setAttribute("productosCanasta", listpro);
			listarProductos(req, res);
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	private void eliminarCarrito(int idProducto, HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
			ProductoInterface productoService = daoFactory.getProducto();

			Producto pro = productoService.obtenerProducto(idProducto);

			for (DetallePedido des : listpro) {
				totalCanasta = 0.0;
				if (des.getIdProducto() == idProducto) {
					if (des.getCantidad() > 0) {
						des.setCantidad(des.getCantidad() - 1);
						des.setSubtotal(des.getCantidad() * pro.getPrecio());
					}

				}
				totalCanasta += des.getSubtotal();
			}

			req.setAttribute("totalCanasta", totalCanasta + "");
			req.setAttribute("productosCanasta", listpro);
			listarProductos(req, res);
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	private void listarDetalle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			req.setAttribute("totalCanasta", totalCanasta + "");
			req.setAttribute("productosCanasta", listpro);
			req.getRequestDispatcher("detallePedido.jsp").forward(req, res);
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	private void listarCategorias(HttpServletResponse res) throws ServletException, IOException {

		Gson gson = new Gson();
		PrintWriter writer = res.getWriter();
		res.setContentType("application/json");

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Categoria> listadoCategorias = productoService.listarCategorias();

		writer.print(gson.toJson(listadoCategorias));
		writer.flush();
		writer.close();
	}

	private void listarColecciones(HttpServletResponse res) throws ServletException, IOException {

		Gson gson = new Gson();
		PrintWriter writer = res.getWriter();
		res.setContentType("application/json");

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		ProductoInterface productoService = daoFactory.getProducto();

		List<Coleccion> listadoColecciones = productoService.listarColecciones();

		writer.print(gson.toJson(listadoColecciones));
		writer.flush();
		writer.close();
	}

	private void registrarPedido(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String clave = req.getParameter("txtClave");
		String dni = req.getParameter("txtDni");
		String correo = req.getParameter("txtCor");
		String dire = req.getParameter("txtDirec");

		Pedido pe = new Pedido();
		pe.setNumero(clave);
		pe.setDireccion(dire);
		pe.setCorreo(correo);
		pe.setSubtotal(totalCanasta);
		pe.setDniCliente(dni);

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		PedidoInterface peService = daoFactory.getPedido();

		peService.registrarPedido(pe);

		int i = -1;
		for (DetallePedido y : listpro) {

			DetallePedido de = new DetallePedido();
			de.setIdPedido(clave);
			de.setIdProducto(y.getIdProducto());
			de.setPrecioProducto(y.getPrecioProducto());
			de.setCantidad(y.getCantidad());
			de.setSubtotal(y.getSubtotal());
			peService.editarDetalle(y);

			i = peService.registrarDetalle(de);
		}

		if (i != -1) {
			System.out.println("ingreso");
		}
		
		req.getRequestDispatcher("pedidos.jsp").forward(req, res);

	}

}
