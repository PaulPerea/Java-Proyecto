package dao;

import interfaces.InicioSessionInterface;
import interfaces.PedidoInterface;
import interfaces.ProductoInterface;
import interfaces.RegistroClienteInterface;


public abstract class DAOFactory {

	public static final int MYSQL = 1;
	public static final int SQLSERVER = 2;
	public static final int ORACLE = 3;
	
	public abstract InicioSessionInterface getPersona();
	public abstract RegistroClienteInterface getRegistro();
	public abstract PedidoInterface getPedido();
	public abstract ProductoInterface getProducto();
	
	public static DAOFactory getDaoFactory(int tipo) {
		
		switch (tipo) {
		case MYSQL:
			return new MySqlDAOFactory();
		case SQLSERVER:
			return null;			
		case ORACLE:
			return null;
		default:
			return null;
		}
		
	}
	
	
}
