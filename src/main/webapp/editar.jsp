<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form Design One</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet" href="css/bootstrapValidator.css">
<link rel="stylesheet" href="css/estilosLogin.css">
</head>
<body>

	<div class="wrapper">
		<form action="RegistroServlet" method="post" id="id_form"
			class="login">
			<input type="hidden" name="type" value=editar> <a
				href="/LP_PROJECT_2022/login.jsp"><p class="title">Recupera
					tu contraseña</p></a>

			<!-- USERNAME INPUT -->
			<div class="form-group">
				<label for="correo">Correo</label> <input type="text" id="correo"
					placeholder="Ingrese Correo" name="txtCorreo">
			</div>
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
			<!-- PASSWORD INPUT -->
			<div class="form-group">
				<label for="password">Nueva Contraseña</label></br> <input
					type="password" id="password" placeholder="Ingrese Contraseña"
					name="txtPass">
			</div>
			<!-- PASSWORD INPUT -->
			<div class="form-group">
				<label for="password1">Confirmar Contraseña</label></br> <input
					type="password" id="password1" placeholder="Ingrese Contraseña"
					name="txtPass1">
			</div>
			<br>
			<button type="submit" name="validateBtn">Actualizar
				Contraseña</button>
			<%
			String mensaje = (String) request.getAttribute("exitoso");
			if (mensaje == "no") {
				out.println("Las contraseñas no coinciden");
			} else if (mensaje == "si") {
				out.println("Actualizacion con exito");
			} else if (mensaje == "nose") {
				out.println("Verifique los datos ingresados");
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
				},
				txtPass1 : {
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