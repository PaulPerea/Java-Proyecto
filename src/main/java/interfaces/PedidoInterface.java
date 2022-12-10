package interfaces;

import java.util.List;

import entidades.DetallePedido;
import entidades.Pedido;

public interface PedidoInterface {
	
	// Listar Pedidos de un cliente según DNI
	public List<Pedido> listarPedidos(String dniCliente, String correo);
	
	// Obtener un pedido y su detalle
	public Pedido obtenerPedidoSegunNumero(String numeroPedido);
	
	public Pedido obtenerPedido(int idPedido);
	
	public List<DetallePedido> obtenerDetalle(String idPedido);
	
	// Registrar un pedido y su detalle
	public int registrarPedido(Pedido nuevo);
	
	public int registrarDetalle(DetallePedido detalle);
	
	// Editar un pedido
	public int editarPedido(Pedido pedido);
	
	// Editar el detalle de un pedido
	public int editarDetalle(DetallePedido detalle);
	
	// Eliminar un pedido
	public int eliminarPedido(int idPedido);

	public int eliminarDetalle(int idPedido);
	
	// Actualizar valores de los pedidos
	public int actualizarValoresPedidos();
}
