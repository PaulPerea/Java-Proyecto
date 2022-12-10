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
				<a class="navbar-brand" href="/LP_PROJECT_2022/home.jsp"><span><img
						width="50" alt="" src="img/iconoLogo.png"></span> SHOP</a>
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
						<li class="nav-item">
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								<span><i class="bi bi-cart3"></i></span>
							</button>
						</li>
				</div>

			</div>
		</nav>
		<hr>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="ProductoServlet">¿Que estas
					buscando?</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarText"
					aria-controls="navbarText" aria-expanded="false"
					aria-label="Toggle navigation">
					<span><i class="bi bi-arrows-angle-contract"></i></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarText">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">Tecnologia</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Celulares"><span><i
											class="bi bi-phone-fill"></i></span>Celulares</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Televisores"><span><i
											class="bi bi-tv-fill"></i></span>Televisores</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Laptops"><span><i
											class="bi bi-laptop-fill"></i></span>Laptops</a></li>
							</ul></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">Electrodomesticos</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Lavadoras"><span><i
											class="bi bi-basket-fill"></i></span>Lavadoras</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Refrigeradoras"><span><i
											class="bi bi-basket-fill"></i></span>Refrigeradoras</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Cocinas"><span><i
											class="bi bi-basket-fill"></i></span>Cocinas</a></li>
							</ul></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">Hogar</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Mesas"><span><i
											class="bi bi-house-heart-fill"></i></span>Mesas</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Centro de entretenimiento"><span><i
											class="bi bi-house-heart-fill"></i></span>Centro de Entretenimiento</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Muebles"><span><i
											class="bi bi-house-heart-fill"></i></span>Muebles</a></li>
							</ul></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">Alimentos</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Carnes"><span><i
											class="bi bi-cart-plus-fill"></i></span>Carnes</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Verduras y Frutas"><span><i
											class="bi bi-cart-plus-fill"></i></span>Verduras y Frutas</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Lacteos"><span><i
											class="bi bi-cart-plus-fill"></i></span>Lacteos</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Abarrotes"><span><i
											class="bi bi-cart-plus-fill"></i></span>Abarrotes</a></li>
							</ul></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">Ropa</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Deporte"><span><i
											class="bi bi-people-fill"></i></span> Deporte</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Casual"><span><i
											class="bi bi-people-fill"></i></span> Casual</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Urbano"><span><i
											class="bi bi-people-fill"></i></span> Urbano</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Elegante"><span><i
											class="bi bi-people-fill"></i></span> Elegante</a></li>
							</ul></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-expanded="false">Juguetes</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Marvel"><span><i
											class="bi bi-brush-fill"></i></span>Marvel</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Disney"><span><i
											class="bi bi-brush-fill"></i></span>Disney</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Barbie"><span><i
											class="bi bi-brush-fill"></i></span>Barbie</a></li>
								<li><a class="dropdown-item"
									href="ProductoServlet?coleccion=Juegos de Mesa"><span><i
											class="bi bi-brush-fill"></i></span>Juegos de Mesa</a></li>
							</ul></li>

					</ul>
					<span class="navbar-text"> ¿Compras de ultimo minuto? </span>
				</div>
			</div>
		</nav>
	</div>
	</br></br>
	<div class="container">
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<th>CodProducto</th>
					<th>Categoria</th>
					<th>Nombre</th>
					<th>Descripcion</th>
					<th>Stock</th>
					<th>Precio</th>
					<th>Seleccionar</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Producto> lstProductos = (List<Producto>) request.getAttribute("productos");
				if (lstProductos != null) {
					for (Producto x : lstProductos) {
				%>
				<tr>
					<td><%=x.getIdProducto()%></td>
					<td><%=x.getCategoria()%></td>
					<td><%=x.getNombre()%></td>
					<td><%=x.getDescripcion()%></td>
					<td><%=x.getStock()%></td>
					<td><%=x.getPrecio()%></td>
					<td><a
						href="ProductoServlet?idProducto=<%=x.getIdProducto()%>"><i
							class="bi bi-file-plus-fill"></i></a></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container">

						<table class="table table-dark table-hover">
							<thead>
								<tr>
									<th>Descripción</th>
									<th>Precio</th>
									<th>Cantidad</th>
									<th>SubTotal</th>
									<th>Eliminar</th>
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
									<td><%=x.getPrecioProducto()%></td>
									<td><%=x.getCantidad()%></td>
									<td><%=x.getSubtotal()%></td>
									<td><a
										href="ProductoServlet?idProductoEli=<%=x.getIdProducto()%>"><i
											class="bi bi-trash3-fill"></i></a></td>
								</tr>
								<%
								}
								}
								%>
							</tbody>
						</table>

					</div>
					<%
					String totalCanasta = (String) request.getAttribute("totalCanasta");
					if (totalCanasta != null) {
						out.println("Total: " + totalCanasta);
					} else {
						out.println("Total: 0");
					}
					%>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<a href="ProductoServlet?detalle=a">Confirmar Pedido</a>
				</div>
			</div>
		</div>

	</div>
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