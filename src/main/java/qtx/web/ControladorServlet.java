package qtx.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qtx.negocio.conceptos.Perro;
import qtx.negocio.servicios.ErrorValidacion;
import qtx.negocio.servicios.GestorPerros;

@WebServlet("/perroweb")
public class ControladorServlet extends HttpServlet {
	public static final int MODO_DEBUG = 1;
	public static final int MODO_INFO = 0;
	
	private static final long serialVersionUID = 1L;
	private int MODO = ControladorServlet.MODO_INFO;
	
	private GestorPerros gp = new GestorPerros();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String paramModo  = config.getServletContext()
				                  .getInitParameter("modo");
		if(paramModo == null)
			this.MODO = ControladorServlet.MODO_INFO;
		else
		if(paramModo.equalsIgnoreCase("debug"))
			this.MODO = ControladorServlet.MODO_DEBUG;
		else
			this.MODO = ControladorServlet.MODO_INFO;
	}
	
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
		// Publicar modo debug para que se enteren las vistas
		if(this.MODO == ControladorServlet.MODO_DEBUG)
			sesion.setAttribute("modo", "debug");
		
		if(vistaOrigen == null) {
			invocarA(request,response,"/Formulario.jsp");
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
					  invocarA(request,response,"/Formulario.jsp");
					  return;
				  }
				  sesion.setAttribute("errores", listErr);
				  sesion.setAttribute("params", paramsPerro);
				  sesion.setAttribute("mensaje", "Formulario con errores (" + listErr.size() + ")");
				  invocarA(request,response,"/Formulario.jsp");
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
