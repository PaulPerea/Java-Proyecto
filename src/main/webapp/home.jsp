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
		<header class="site-header sticky-top py1">
			<%@ include file="snippet/nav.jsp"%>
		</header>
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
	<br>
	<br>
	<div>
		<div id="carouselExampleDark" class="carousel carousel-dark slide"
			data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleDark"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleDark"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleDark"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="10000">
					<img src="img/imagen1.png" class="d-block w-100" alt="...">
					<div class="carousel-caption d-none d-md-block">
						<h5>First slide label</h5>
						<p>Some representative placeholder content for the first
							slide.</p>
					</div>
				</div>
				<div class="carousel-item" data-bs-interval="2000">
					<img src="img/imagen2.png" class="d-block w-100" alt="...">
					<div class="carousel-caption d-none d-md-block">
						<h5>Second slide label</h5>
						<p>Some representative placeholder content for the second
							slide.</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="img/imagen3.png" class="d-block w-100" alt="...">
					<div class="carousel-caption d-none d-md-block">
						<h5>Third slide label</h5>
						<p>Some representative placeholder content for the third
							slide.</p>
					</div>
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleDark" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleDark" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="row row-cols-md-1">
			<div class="imgAparecer">
				<img class="img-fluid " alt="" src="img/panel1.png">
			</div>
			<div class="imgOcultar">
				<img class="img-fluid" alt="" src="img/panel2.png">
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="bg-light">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 g-3">
					<div class="col">
						<div class="card shadow-sm">
							<img class="card-img-top" src="img/CUPON1.png" alt="image"
								width="100%" height="225">
							<div class="card-body">
								<p class="card-text">Entrega desde 24 horas por delivery o
									recojo en tienda.</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-primary my-2">Visitar</button>
									</div>
									<small class="text-muted">20% Desc. solo por hoy</small>
								</div>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="card shadow-sm">
							<img class="card-img-top" src="img/CUPON2.png" alt="image"
								width="100%" height="225">
							<div class="card-body">
								<p class="card-text">¡Todo lo que necesitas para ti y tu
									hogar!</p>
								<div class="d-flex justify-content-between align-items-center">
									<div class="btn-group">
										<button type="button" class="btn btn-primary my-2">Visitar</button>
									</div>
									<small class="text-muted">45% de Desc. pagando con tu
										tarjeta</small>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br> <br>
		<div class="container py5">
			<div
				class="row row-cols-1 row-cols-sm-1 row-cols-md-4 g-4 cl-auto p-5">
				<div class="col">
					<!-- Button trigger modal -->
					<button type="button" class="btn" data-bs-toggle="modal"
						data-bs-target="#primeraOrden">
						<img src="img/orden1.png" />
					</button>

					<!-- Modal -->
					<div class="modal fade" id="primeraOrden" data-bs-backdrop="static"
						data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content container">
								<div class="modal-header">
									<h5 class="modal-title col-11 text-center"
										id="staticBackdropLabel">Recojo en tienda</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body text-center">
									<img alt="" src="img/orden11.png">
									<hr>
									<h5>Programa tu pedido y recógelo en tu tienda favorita.</h5>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Cerrar</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col">
					<button type="button" class="btn" data-bs-toggle="modal"
						data-bs-target="#segundaOrden">
						<img src="img/orden2.png" />
					</button>
					<!-- Modal -->
					<div class="modal fade" id="segundaOrden" data-bs-backdrop="static"
						data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content container">
								<div class="modal-header">
									<h5 class="modal-title col-11 text-center"
										id="staticBackdropLabel">Delivery Programado</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body text-center">
									<img alt="" src="img/orden22.png">
									<hr>
									<h5>Programa tus compras de la semana y nos encargamos de
										llevarlas a tu hogar.</h5>
									<div class="">
										<div class="">
											<h3 class="">Beneficios</h3>
											<ul class="">
												<li>Puedes elegir la fecha y el rango horario de tu
													preferencia.</li>
												<li>No necesitas cargarlo, nosotros te lo llevamos.</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Cerrar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<button type="button" class="btn" data-bs-toggle="modal"
						data-bs-target="#terceraOrden">
						<img src="img/orden3.png" />
					</button>
					<!-- Modal -->
					<div class="modal fade" id="terceraOrden" data-bs-backdrop="static"
						data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content container">
								<div class="modal-header">
									<h5 class="modal-title col-11 text-center"
										id="staticBackdropLabel">Delivery Express</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body text-center">
									<img alt="" src="img/orden33.png">
									<hr>
									<h5>¿Compra de último minuto? No te preocupes, te lo
										llevamos el mismo día de tu compra.</h5>
									<div class="">
										<div class="">
											<h3 class="">Pago online</h3>
											<ul class="">
												<li>Máximo 15 productos por pedido.</li>
												<li>Precio regular: S/ 7.90 por delivery</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Cerrar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<button type="button" class="btn" data-bs-toggle="modal"
						data-bs-target="#cuartaOrden">
						<img src="img/orden4.png" />
					</button>
					<!-- Modal -->
					<div class="modal fade" id="cuartaOrden" data-bs-backdrop="static"
						data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content container">
								<div class="modal-header">
									<h5 class="modal-title col-11 text-center"
										id="staticBackdropLabel">Métodos de pago</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body text-center">
									<img alt="" src="img/orden44.png">
									<hr>
									<h5>Realiza tus compras, con total seguridad, de las
										siguiente formas:</h5>
									<div class="">
										<div class="">
											<h3 class="">Pago online</h3>
											<ul class="">
												<li>Tarjeta oh! Visa o Mastercard</li>
												<li>Tarjeta crédito o débito</li>
												<li>Tarjeta Agora PAY Visa</li>
												<li>Efectivo / Banca por internet *</li>
												<li>Cuotéalo</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Cerrar</button>
								</div>
							</div>
						</div>
					</div>
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