<%@page import="entidades.Pedido"%>
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
	<%@ include file="snippet/nav.jsp"%>
	</br>
	</br>
	<div class="container">
		<form action="PedidoServlet" method="get">
			<input type="hidden" name="type" value="listar">
			<legend>Busca tu Pedido a travez de tu clave</legend>
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
			<button type="submit" class="btn btn-primary">Buscar</button>
		</form>
	</div>
	</br>
	</br>
	<div class="container">
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<th>Codigo de Pedido</th>
					<th>DNI</th>
					<th>Correo</th>
					<th>Fecha de Registro</th>
					<th>Direccion</th>
					<th>Total de Venta</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Pedido> pedido = (List<Pedido>) request.getAttribute("pedidos");
				if (pedido != null) {
					for (Pedido x : pedido) {
				%>
				<tr>
					<td><%=x.getNumero()%></td>
					<td><%=x.getDniCliente()%></td>
					<td><%=x.getCorreo()%></td>
					<td><%=x.getFecha()%></td>
					<td><%=x.getDireccion()%></td>
					<td><%=x.getTotalPedido()%></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
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