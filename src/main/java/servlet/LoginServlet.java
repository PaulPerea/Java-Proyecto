package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import dao.DAOFactory;
import entidades.Usuario;
import interfaces.InicioSessionInterface;
import util.Constantes;
import util.SessionProject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");

		if (type.equals("login")) {

			String correo = request.getParameter("txtCorreo");
			String clave = request.getParameter("txtPass");

			DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
			InicioSessionInterface dao = daoFactory.getPersona();
			Usuario usuario = dao.verificarSession(correo, clave);
			if (usuario != null) {

				SessionProject sessionProject = new SessionProject();
				sessionProject.saveSessionTimeOut(request, 30);
				sessionProject.saveSessionString(request, Constantes.NAME, usuario.getNom());
				sessionProject.saveSessionString(request, Constantes.LASTNAME, usuario.getApe());
				sessionProject.saveSessionString(request, Constantes.EMAIL, usuario.getEmail());
				sessionProject.saveSessionString(request, Constantes.ROL, usuario.getRol());
				response.sendRedirect("home.jsp");
				return;
			}else {
				request.setAttribute("mensaje", "error");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else if (type.equals("logout")) {

			SessionProject sessionProject = new SessionProject();
			sessionProject.invalidateSession(request);

			response.sendRedirect("login.jsp");
		}
	}

}
