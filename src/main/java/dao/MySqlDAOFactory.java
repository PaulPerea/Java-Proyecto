package dao;

import interfaces.InicioSessionInterface;
import interfaces.PedidoInterface;
import interfaces.ProductoInterface;
import interfaces.RegistroClienteInterface;
import modelo.InicioSessionModel;
import modelo.PedidoModelo;
import modelo.ProductoModel;
import modelo.RegistrarUsuarioModel;

public class MySqlDAOFactory extends DAOFactory {
	@Override
	public InicioSessionInterface getPersona() {
		return new InicioSessionModel();
	}

	@Override
	public RegistroClienteInterface getRegistro() {
		return new RegistrarUsuarioModel();
	}
	
	@Override
	public PedidoInterface getPedido() {
		return new PedidoModelo();
	}

	@Override
	public ProductoInterface getProducto() {
		return new ProductoModel();
	}
}
