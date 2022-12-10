<%@page import="util.Constantes"%>
<%
String name = (String) session.getAttribute(Constantes.NAME);
%>
<%
String lastname = (String) session.getAttribute(Constantes.LASTNAME);
%>
<nav
	class="navbar navbar-expand-md navbar-dark bg-dark justify-content-between pt-3">
	<div class="container-fluid">
		<a class="navbar-brand" href="/LP_PROJECT_2022/home.jsp"><span><img width="50"
				alt="" src="img/iconoLogo.png"></span> SHOP</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="input-group w-10">
			<form action="ProductoServlet" method="get" class="row g-6">
				<div class="col-auto">
					<input type="text" class="form-control " name="txtNombre"
						placeholder="Buscar producto" aria-label="Input group example"
						aria-describedby="basic-addon1">
				</div>
				<div class="col-auto">
					<Button type="submit" class="btn btn-primary">
						<i class="bi bi-search"></i>
					</Button>
				</div>
			</form>
		</div>
		<div class="collapse navbar-collapse container-fluid"
			id="navbarCollapse">
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
				<li class="nav-item"><a class="nav-link"
					href="/LP_PROJECT_2022/pedidos.jsp">Mis Pedidos</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
					role="button" aria-expanded="false">Mi cuenta</a>
					<ul class="dropdown-menu">
						<%
						String mensaje = (String) request.getAttribute("mensaje");
						if (mensaje == "error") {
						%>
						<li><a href="RegistroServlet?type=load" class="dropdown-item"
							href="#"><span><i class="bi bi-box-arrow-in-right"></i></span>
								Iniciar sesión / Registrate </a></li>
						<%
						}
						%>
						<li><a class="dropdown-item" href="#"><span><i
									class="bi bi-person-fill"></i></span> Perfil (<%=name%> <%=lastname%>)</a></li>
						<li><a class="dropdown-item" href="LoginServlet?type=logout">Cerrar
								Sesión</a></li>

					</ul></li>
			</ul>
		</div>
	</div>
</nav>
