package interfaces;

import java.util.List;

import entidades.Registro;
import entidades.Rol;

public interface RegistroClienteInterface {

	public List<Rol> listTipoRol();
	
	public int registrar(Registro Registro);
	
	public List<Registro> listRegistro();
	
	public int eliminarRegistro(String idRegistro);
	
	public int editarRegistro(Registro Registro);
	
	public Registro getRegistro(String id);
	
}
