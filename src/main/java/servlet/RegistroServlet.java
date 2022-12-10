package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import entidades.Registro;
import entidades.Rol;
import interfaces.RegistroClienteInterface;

/**
 * Servlet implementation class RegistroServlet
 */
@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistroServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String type = req.getParameter("type");
		if (type.equals("load")) {
			configuracionInicial(req, resp);
		} else if (type.equals("register")) {
			registrar(req, resp);

		} else if (type.equals("editar")) {
			editarRegistro(req, resp);
		} else if (type.equals("delete")) {
			eliminarRegistro(req, resp);
		} else if (type.equals("info")) {
			obtenerRegistro(req, resp);
		}
	}

	private void configuracionInicial(HttpServletRequest req, HttpServletResponse resp) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		RegistroClienteInterface dao = daoFactory.getRegistro();
		List<Rol> data = dao.listTipoRol();
		List<Registro> dataRegistro = dao.listRegistro();

		req.setAttribute("data", data);
		req.setAttribute("dataRegistro", dataRegistro);
//		req.getRequestDispatcher("el jsp").forward(req, resp); dessacer cua tenga el jsp
	}

	private void registrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("txtNombre");
		String ape = req.getParameter("txtApellido");
		String email = req.getParameter("txtCorreo");
		String pass = req.getParameter("txtPass");

		Registro reg = new Registro();
		reg.setNom(nombre);
		reg.setApe(ape);
		reg.setEmail(email);
		reg.setPass(pass);

		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		RegistroClienteInterface dao = daoFactory.getRegistro();

		int value = dao.registrar(reg);
		if (value == 1) {
			req.setAttribute("validar", "correcto");
			configuracionInicial(req, resp);
			resp.sendRedirect("login.jsp");
		}
	}

	private void editarRegistro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("txtNombre");
		String ape = req.getParameter("txtApellido");
		String email = req.getParameter("txtCorreo");
		String pass = req.getParameter("txtPass");
		String pass1 = req.getParameter("txtPass1");
		System.out.println(pass + "  " +pass1);
		if (pass.equals(pass1)) {
			Registro reg = new Registro();
			reg.setNom(nombre);
			reg.setApe(ape);
			reg.setEmail(email);
			reg.setPass(pass);
			DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
			RegistroClienteInterface dao = daoFactory.getRegistro();
			int value = dao.editarRegistro(reg);
			if (value == 1) {
				configuracionInicial(req, resp);
				req.setAttribute("exitoso", "si");
				resp.sendRedirect("login.jsp");
			} else {
				req.setAttribute("exitoso", "nose");
				req.getRequestDispatcher("editar.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("exitoso", "no");
			req.getRequestDispatcher("editar.jsp").forward(req, resp);
		}

	}

	private void eliminarRegistro(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		RegistroClienteInterface dao = daoFactory.getRegistro();

		int value = dao.eliminarRegistro(id);
		if (value == 1) {
			configuracionInicial(req, resp);
		} else {

		}

	}

	private void obtenerRegistro(HttpServletRequest req, HttpServletResponse resp) {
		String idRegistro = req.getParameter("id de la pagina");
		DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
		RegistroClienteInterface dao = daoFactory.getRegistro();
		Registro reg = dao.getRegistro(idRegistro);
		List<Rol> data = dao.listTipoRol();
		List<Registro> dataRegistro = dao.listRegistro();
		req.setAttribute("estudianteData", reg);
		req.setAttribute("data", data);
		req.setAttribute("dataEstudiantes", dataRegistro);
//		req.getRequestDispatcher("el jsp").forward(req, resp);
	}
}
