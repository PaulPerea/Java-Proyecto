package interfaces;

import entidades.Usuario;

import java.util.List;

import entidades.Persona;

public interface InicioSessionInterface {

	public Usuario verificarSession(String correo, String contra);
}
