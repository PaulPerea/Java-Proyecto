<%@page import="entidades.DetallePedido"%>
<%@page import="entidades.Producto"%>
<%@page import="java.util.List"%>
<%@page import="util.Constantes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
if (session.getAttribute(Constantes.NAME) == null) {
	response.sendRedirect("login.jsp");
}
%>
<head>
<meta charset="ISO-8859-1">
<title>SHOP</title>
<link rel="shortcut icon" href="img/logo.ico" type="image/x-icon">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="css/estilosNav.css">

</head>
<body>

	<div class="cabecera">
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
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false"
					aria-label="Toggle navigation">
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
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">Mi cuenta</a>
							<ul class="dropdown-menu">
								<%
								String mensaje = (String) request.getAttribute("mensaje");
								if (mensaje == "error") {
								%>
								<li><a href="RegistroServlet?type=load"
									class="dropdown-item" href="#"><span><i
											class="bi bi-box-arrow-in-right"></i></span> Iniciar sesión /
										Registrate </a></li>
								<%
								}
								%>
								<li><a class="dropdown-item" href="#"><span><i
											class="bi bi-person-fill"></i></span> Perfil (<%=name%> <%=lastname%>)</a></li>
								<li><a class="dropdown-item"
									href="LoginServlet?type=logout">Cerrar Sesión</a></li>

							</ul></li>
				</div>

			</div>
		</nav>


		<hr>
	</div>
	<div class="container">
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Descripción</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th>SubTotal</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<DetallePedido> productosCanasta = (List<DetallePedido>) request.getAttribute("productosCanasta");
				if (productosCanasta != null) {
					for (DetallePedido x : productosCanasta) {
				%>
				<tr>
					<td><%=x.getNomPro()%></td>
					<td><%=x.getDescripcion()%></td>
					<td><%=x.getPrecioProducto()%></td>
					<td><%=x.getCantidad()%></td>
					<td><%=x.getSubtotal()%></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>
	<div class="container">
		<%
		String totalCanasta = (String) request.getAttribute("totalCanasta");
		if (totalCanasta != null) {
			out.println("Total: " + totalCanasta);
		} else {
			out.println("Total: 0");
		}
		%>
		</br> </br>
		<form action="ProductoServlet" method="get">
			<input type="hidden" name="type" value="registrar">
			<legend>Estas a un solo paso | Completa tus datos</legend>
			<div class="mb-3">
				<label for="clave" class="form-label">Ingresa la clave de tu
					compra</label> <input type="text" class="form-control" id="clave"
					aria-describedby="emailHelp" name="txtClave">
				<div id="emailHelp" class="form-text">Esta clave te ayudara a
					encontrar tu pedido | ejem: NOM-APE-DNI</div>
			</div>
			<div class="mb-3">
				<label for="dni" class="form-label">DNI</label> <input type="text"
					class="form-control" id="dni" aria-describedby="emailHelp"
					name="txtDni">
			</div>
			<div class="mb-3">
				<label for="correo" class="form-label">Correo</label> <input
					type="text" class="form-control" id="correo"
					aria-describedby="emailHelp" name="txtCor">
			</div>
			<div class="mb-3">
				<label for="direc" class="form-label">Dirección</label> <input
					type="text" class="form-control" id="direc"
					aria-describedby="emailHelp" name="txtDirec">
			</div>
			<button type="submit" class="btn btn-primary">Solicitar
				Pedido</button>
		</form>

	</div>
	</br>
	</br>
</body>
<footer>
	<%@ include file="snippet/footer.jsp"%>
</footer>
<script type="js/script.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</html>