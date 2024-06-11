package qtx.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import qtx.negocio.conceptos.Perro;
import qtx.negocio.servicios.ErrorValidacion;
import qtx.negocio.servicios.GestorPerros;

@WebServlet("/perroweb")
public class ControladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GestorPerros gp = new GestorPerros();
    public ControladorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	private void invocarA(HttpServletRequest request, HttpServletResponse response, String destino) 
			throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext()
				                   .getRequestDispatcher(destino);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vistaOrigen = request.getParameter("vista");
		HttpSession sesion = request.getSession(true);
		if(vistaOrigen == null) {
			invocarA(request,response,"/formPerro");
			return;
		}
		switch(vistaOrigen) {
		case "formAlta": 
			  String operacion = request.getParameter("operacion");
			  switch(operacion) {
			  case "registro":
				  Map<String,String> paramsPerro = this.getParamsPerro(request);
				  List<ErrorValidacion> listErr = gp.validarPerroInsercion(paramsPerro);
				  if(listErr.size() == 0) {
					  Perro perroNvo = gp.crearPerroIns(paramsPerro);
					  gp.insertarPerro(perroNvo);
					  sesion.removeAttribute("errores");
					  sesion.removeAttribute("params");
					  sesion.setAttribute("mensaje", "Perro dado de alta: " + perroNvo);
					  invocarA(request,response,"/formPerro");
					  return;
				  }
				  sesion.setAttribute("errores", listErr);
				  sesion.setAttribute("params", paramsPerro);
				  sesion.setAttribute("mensaje", "Formulario con errores (" + listErr.size() + ")");
				  invocarA(request,response,"/formPerro");
				  return;
			  }
		case "menu": return;
		}
	}
	private Map<String, String> getParamsPerro(HttpServletRequest request) {
		Map<String,String> paramPerro = new HashMap<>();
		paramPerro.put("id",request.getParameter("id"));
		paramPerro.put("nombre",request.getParameter("nombre"));
		paramPerro.put("edad",request.getParameter("edad"));
		paramPerro.put("raza",request.getParameter("raza"));
		return paramPerro;
	}


}
