package qtx.negocio.servicios;

import java.util.List;

import qtx.negocio.conceptos.Perro;

public interface IRepositorioPerros {
	public Perro getPerroXID(int id);
	public int insertarPerro(Perro perro);
	public List<Perro> getPerros();
	public Perro getPerroXNombre(String nombre);
}
