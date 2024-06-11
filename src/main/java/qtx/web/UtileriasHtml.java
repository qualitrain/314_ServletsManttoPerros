package qtx.web;

import java.util.Date;

import jakarta.servlet.http.HttpSession;

public class UtileriasHtml {
	public static String getEncabezadosHtml(String nombrePestaniaNavegador){
	    String encabezadoHtml="";
	    
	    encabezadoHtml+="<!DOCTYPE HTML>\n";
	    encabezadoHtml+="<HTML><HEAD>\n";
	    encabezadoHtml+="<TITLE>"+nombrePestaniaNavegador+"</TITLE>\n";
	    encabezadoHtml+="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"/> \n";
	    encabezadoHtml+="<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" />\n";
	    encabezadoHtml+="<link rel=\"stylesheet\" href=\"css/formStyle.css\" type=\"text/css\" />\n";
	    encabezadoHtml+="</HEAD>\n";
	    encabezadoHtml+="<BODY>\n";
	    return encabezadoHtml;
	}
	public static String getDatosSesionHtml(HttpSession session){
		String SPAN = "<span class=\"valor\">";
		String FIN_SPAN = "</span>\n";
		String datosSesionHtml="<BR>Estado:";
		if (session.isNew()) 
			datosSesionHtml+=SPAN + "Nueva Sesion." + FIN_SPAN;
		else 
			datosSesionHtml+=SPAN + "Sesion Activa." + FIN_SPAN;
		datosSesionHtml+="<BR>ID: " 		+ SPAN + session.getId() + FIN_SPAN;
		datosSesionHtml+="<BR>Creación: " + SPAN + new Date(session.getCreationTime()) + FIN_SPAN;
		datosSesionHtml+="<BR>Ultimo acceso:"+ SPAN + new Date(session.getLastAccessedTime()) + FIN_SPAN;
		datosSesionHtml+="<BR>Intervalo Maximo de inactividad(segundos):"+ SPAN + session.getMaxInactiveInterval()+ FIN_SPAN;
		return datosSesionHtml;
	}
	public static String getPieHtml(){
		return "</BODY>" + "</HTML>\n";
	}

}
