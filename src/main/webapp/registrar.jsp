<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SHOP</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet" href="css/bootstrapValidator.css">
<link rel="stylesheet" href="css/estilosLogin.css">
</head>
<body>

	<div class="wrapper">
		<form action="RegistroServlet" method="post" id="id_form"
			class="login">
			<input type="hidden" name="type" value="register"> <a
				href="/LP_PROJECT_2022/login.jsp"><p class="title">Registrate
					con nosotros | SHOP</p></a>

			<!-- NOMBRE INPUT -->
			<div class="form-group">
				<label for="nombre">Nombre</label> <input type="text" id="nombre"
					placeholder="Ingrese Nombre" name="txtNombre">
			</div>
			<!-- APELLIDO INPUT -->
			<div class="form-group">
				<label for="apellido">Apellido</label> <input type="text"
					id="apellido" placeholder="Ingrese Apellido" name="txtApellido">
			</div>
			<!-- Correo INPUT -->
			<div class="form-group">
				<label for="correo">Correo</label> <input type="text" id="correo"
					placeholder="Ingrese Correo" name="txtCorreo">
			</div>
			<!-- PASSWORD INPUT -->
			<div class="form-group">
				<label for="password">Contraseña</label></br> <input type="password"
					id="password" placeholder="Ingrese Contraseña" name="txtPass">
			</div>
			<br>
			<button type="submit" name="validateBtn">Registrate</button>
			<%
			String mensaje = (String) request.getAttribute("validar");
			if (mensaje != null) {
				if (mensaje == "correcto") {
					out.println("Registro Exitoso");
				} else {
					out.println("Algo fallo al registrar");
				}
			}
			%>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#id_form').bootstrapValidator({
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				txtNombre : {
					validators : {
						notEmpty : {
							message : "El campo es obligatorio"
						}
					}
				},
				txtApellido : {
					validators : {
						notEmpty : {
							message : "El campo es obligatorio"
						}
					}
				},
				txtCorreo : {
					validators : {
						notEmpty : {
							message : "El campo es obligatorio"
						},
						emailAddress : {
							message : "El formato de correo es incorrecto"
						}
					}
				},
				txtPass : {
					validators : {
						notEmpty : {
							message : "El campo es obligatorio"
						}
					}
				}
			}
		});

		$('#validateBtn').click(function() {
			$('#id_form').bootstrapValidator('validate');
		});
	});
</script>
</html>