package qtx.test;

import java.util.List;

import qtx.negocio.conceptos.Perro;
import qtx.negocio.servicios.ErrorValidacion;
import qtx.negocio.servicios.GestorPerros;
import qtx.negocio.servicios.IRepositorioPerros;
import qtx.persistencia.memoria.RepositorioMemoria;

public class Test {

	public static void main(String[] args) {
//		testRepositorio();
		testGestorPerros();
	}

	private static void testGestorPerros() {
		GestorPerros gestor = new GestorPerros();
		gestor.insertarPerro(new Perro(0,"Pastor Alemán",7,"Barack"));
		gestor.insertarPerro(new Perro(0,"Pastor Inglés",5,"Ralph"));
		gestor.insertarPerro(new Perro(0,"Dálmata",2,"Manchitas"));
		gestor.insertarPerro(new Perro(0,"Gran Danés",2,"Manchitas"));
		for(Perro perroI : gestor.getPerrosTodos() ) {
			System.out.println(perroI);
		}
		List<ErrorValidacion> errores = gestor.validarPerroInsercion(new Perro(0,"  \t",-1,"  Manchitas"));
		System.out.println(ErrorValidacion.getMensajesErrorPorCampo("nombre", errores));
	}

	private static void testRepositorio() {
		IRepositorioPerros repositorio = new RepositorioMemoria();
		repositorio.insertarPerro(new Perro(0,"Pastor Alemán",7,"Barack"));
		repositorio.insertarPerro(new Perro(0,"Pastor Inglés",5,"Ralph"));
		repositorio.insertarPerro(new Perro(0,"Dálmata",2,"Manchitas"));
		repositorio.insertarPerro(new Perro(0,"Gran Danés",2,"Manchitas"));
		for(Perro perroI : repositorio.getPerros()) {
			System.out.println(perroI);
		}
		Perro perro = repositorio.getPerroXID(2);
		System.out.println("Perro Num. 2: " + perro);
		perro = repositorio.getPerroXNombre("Barack");
		System.out.println("Perro Barack: " + perro);
	}

}
