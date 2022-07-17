<!--  
<%@page import="qtx.web.ControladorServlet"%>
<%@ page import="java.util.*" %>
<%@ page import="qtx.negocio.servicios.ErrorValidacion" %>
<%@page import="java.net.URL"%>
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"  
            uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Perro</title>
<style>
body{
 padding: 40px;
}
h3{
	text-align: center;
}
form{
	margin-top: 20px;
	width: 60%;
	margin-left: 20%;
}
label{
	display:inline-block;
	width: 5rem;
}
input[type="text"]{
   border-radius: 3px;
   padding:3px;
}
table{
	margin-top: 20px;
	width: 60%;
	margin-left: 20%;
	margin-right: auto;
	border-collapse: collapse;
}
th,td{
	border: 1px solid hsl(120,90%,50%);
	padding: 4px;
}
tr:nth-child(even) {
	background-color: hsl(150,90%,90%)
}
table.debug th, table.debug td{
	border: 1px solid hsl(180,80%,30%);
	padding: 4px;
}
table.debug tr:nth-child(even) {
	background-color: hsl(150,30%,90%)
}
p{
	text-align: center;
}
span{
	color: blue;
	font-weight: bold;
}
</style>
</head>
<body>
<h3>Alta de Perros</h3>
	<form action="perroweb" method="post">
		<label for="idPerro">Id:</label>
		<input type="text" id="idPerro" name="id" value='${params["id"]}'><br><br>
		<label for="nombrePerro">Nombre:</label>
		<input type="text" id="nombrePerro" name="nombre" value='${params["nombre"]}'><br><br>
		<label for="edadPerro">Edad:</label>
		<input type="text" id="edadPerro"  name="edad"  value='${params["edad"]}'><br><br>
		<label for="razaPerro">Raza:</label>
		<input type="text" id="razaPerro"  name="raza" value='${params["raza"]}'><br><br>
		<input type="hidden" name="vista" value="formAlta">
		<input type="hidden" name="operacion" value="registro">
		<input type="submit" value="Registrar">
	</form>
	<c:if test="${not empty errores}">
		<table>
		<thead>
			<tr>
			<th>Campo</th><th>Valor</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${errores}" var="errI">
			<tr> 
				<td>${errI.campo}</td>
				<td>${errI.descripcion}</td>
			</tr>
		</c:forEach>
		
		</tbody>
		</table>
	</c:if>
	<br>
	<p>
	<span>${mensaje}</span>
	</p>

   <c:if test="${modo == 'debug'}">
	   <br>
	   <hr>
	   <br>
	   <table class="debug">
	   	<tr>
	      	<td>Este JSP se está ejecutando en la fecha/hora siguiente:</td>
	      	<td> <%= new java.util.Date() %></td>
		</tr> 
	   	<tr>
	   		<td>La clase de este JSP es:</td>
	   		<td>${pageScope["javax.servlet.jsp.jspPage"].getClass().getName()} </td>
	   		<!--pageScope.class.name  -->
		</tr>
	   	<tr>
	   		<td>El Servlet generado está en:</td>
	   		<td>${pageScope["javax.servlet.jsp.jspPage"].getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()}</td>
	   		<!-- page.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath() -->
		</tr>
	   	<tr>
	   		<td>Id de la sesión es:</td>
	   		<td>${pageScope["javax.servlet.jsp.jspSession"].id}</td>
	   		<!--session.getId()  -->
		</tr>
	    <tr>
	   		<td>Método http:</td> 
	   		<td>${pageScope["javax.servlet.jsp.jspRequest"].method}</td>
	   		<!-- request.getMethod() -->
		</tr>
	     <tr>
	   		<td>pageContext:</td>
	   		<td>${pageScope["javax.servlet.jsp.jspPageContext"]}</td>
	   		<!-- pageContext.getClass().getName() -->
		</tr>
	     <tr>
	   		<td>application:</td>
	   		<td>${pageScope["javax.servlet.jsp.jspApplication"].contextPath}</td>
	   		<!-- application.getContextPath() -->
		</tr>
	   </table>
  </c:if>

</body>
</html>