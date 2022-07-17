<%@page import="qtx.web.ControladorServlet"%>
<%@ page import="java.util.*" %>
<%@ page import="qtx.negocio.servicios.ErrorValidacion" %>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<%
	List<ErrorValidacion> listErr = (List<ErrorValidacion>) session.getAttribute("errores");
%>
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
<% if (listErr != null && listErr.size() > 0){ %>
	<table>
	<thead>
		<tr>
		<th>Campo</th><th>Valor</th>
		</tr>
	</thead>
	<tbody>
	<%	for(ErrorValidacion errI : listErr) { %>
		<tr> 
			<td><%=errI.getCampo()%></td>
			<td><%=errI.getDescripcion()%></td>
		</tr>
	<% } %>
	
	</tbody>
	</table>
<% } %>
<br>
<p>
<span>${mensaje}</span>
</p>

<% 
   Integer modo = (Integer)session.getAttribute("modo");
   boolean debugOn = (modo == null) ? false : (modo == ControladorServlet.MODO_DEBUG) ? true : false;
   if (debugOn){ 
   %>
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
   		<td><%=page.getClass().getName() %></td>
	</tr>
   	<tr>
   		<td>El Servlet generado está en:</td>
   		<td><%=page.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath() %></td>
   		<% 	
   			URL urlServlet = page.getClass().getProtectionDomain().getCodeSource().getLocation();
   		%>
	</tr>
   	<tr>
   		<td>Id de la sesión es:</td>
   		<td><%=session.getId() %></td>
	</tr>
    <tr>
   		<td>Método http:</td>
   		<td><%=request.getMethod() %></td>
	</tr>
     <tr>
   		<td>pageContext:</td>
   		<td><%=pageContext.getClass().getName() %></td>
	</tr>
     <tr>
   		<td>application:</td>
   		<td><%=application.getContextPath()%></td>
	</tr>
   </table>
<% } %>

</body>
</html>