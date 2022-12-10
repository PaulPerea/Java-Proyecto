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
		<form action="LoginServlet" method="post" id="id_form" class="login">
			<input type="hidden" name="type" value="login">
			<p class="title">Bienvenido a SHOP</p>
			
			<!-- USERNAME INPUT -->
			<div class="form-group">
				<label for="correo">Correo</label>
				<input type="text" id="correo" placeholder="Ingrese Correo" name="txtCorreo">
			</div>
			<!-- PASSWORD INPUT -->
			<div class="form-group">
				<label for="password">Contraseña</label></br> 
				<input type="password" id="password" placeholder="Ingrese Contraseña" name="txtPass">
			</div>
			<a href="/LP_PROJECT_2022/editar.jsp">¿Se olvido su contraseña?</a><br>
			<a href="/LP_PROJECT_2022/registrar.jsp">¿Todavia no tienes Cuenta?</a>
			<br>
			<button type="submit" name="validateBtn">
			Iniciar Sesión
			</button>
			<%
			String mensaje = (String) request.getAttribute("mensaje");
			if (mensaje == "error") {

				out.println("Ingreso un Usuario y/o contraseña incorrecta");
				/*out.println("<script type=\"text/javascript\">");
				out.println("alert('Invalid Username or Password');");
				out.println("</script>");*/
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