package qtx.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qtx.negocio.servicios.ErrorValidacion;

@WebServlet("/formPerro")
public class FormularioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FormularioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HttpSession sesion = request.getSession();
		Map<String,String> paramsPerro =(Map<String, String>) sesion.getAttribute("params");
		if(paramsPerro == null)
			paramsPerro= new HashMap<String,String>();
		String mensaje = (String) sesion.getAttribute("mensaje");
		mensaje = mensaje == null ? "" : mensaje;
		String idPerro = paramsPerro.getOrDefault("id", "");
		String edad = paramsPerro.getOrDefault("edad", "");
		String nombre = paramsPerro.getOrDefault("nombre", "");
		String raza = paramsPerro.getOrDefault("raza", "");
		List<ErrorValidacion> listErr = (List<ErrorValidacion>) sesion.getAttribute("errores");

		
		response.getWriter()
		        .append(UtileriasHtml.getEncabezadosHtml("Perro"))
		        .append("<h3>Alta de Perros</h3>")
		        .append("<form action=\"perroweb\" method=\"post\">")
		        .append("<label for=\"idPerro\">Id:</label>")
		        .append("<input type=\"text\" id=\"idPerro\" name=\"id\" "
		        		+ "value="
		        		+ "\"" + idPerro + "\"" +
		        		"><br><br>")
		        .append("<label for=\"nombrePerro\">Nombre:</label>")
		        .append("<input type=\"text\" id=\"nombrePerro\" name=\"nombre\""
		        		+ " value="
		        		+ "\"" + nombre + "\"" 
		        		+ "><br><br>")
		        .append("<label for=\"edadPerro\">Edad:</label>")
		        .append("<input type=\"text\" id=\"edadPerro\"  name=\"edad\""
		        		+ " value="
		        		+ "\"" + edad + "\"" 
		        		+ "><br><br>")
		        .append("<label for=\"razaPerro\">Raza:</label>")
		        .append("<input type=\"text\" id=\"razaPerro\"  name=\"raza\""
		        		+ " value="
		        		+ "\"" + raza + "\"" 
		        		+ "><br><br>")
		        .append("<input type=\"hidden\"  name=\"vista\" value=\"formAlta\"><br><br>")
		        .append("<input type=\"hidden\"  name=\"operacion\" value=\"registro\"><br><br>")
		        .append("<input type=\"submit\" value=\"Registrar\">")
		        .append("</form>")
		        .append(this.getMensaje(mensaje))
		        .append(this.getErrores(listErr))
		        .append(UtileriasHtml.getPieHtml());
	}

	private CharSequence getMensaje(String mensaje) {
		return "<br><span class=\"mensaje\">" + mensaje + "</span><br>";
	}

	private CharSequence getErrores(List<ErrorValidacion> listErr) {
		if(listErr == null)
			return "";
		if(listErr.size() == 0)
			return "";
		String tablaErrores="<br><table><thead>"
				+ "<tr><th>Campo</th><th>Valor</th></tr>"
				+ "</thead><tbody>";
		for(ErrorValidacion errI : listErr) {
			tablaErrores += "<tr>" 
						+"<td>" + errI.getCampo() + "</td>"
						+"<td>" + errI.getDescripcion() + "</td>"
						+ "</tr>";
		}
		tablaErrores +="</tbody></table>";
		return tablaErrores;
	}

}
