package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.Registro;
import entidades.Rol;

import entidades.Registro;
import interfaces.RegistroClienteInterface;

public class RegistrarUsuarioModel implements RegistroClienteInterface{

	@Override
	public List<Rol> listTipoRol() {
		List<Rol> listDocumentos = new ArrayList<Rol>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			
			String sql = "SELECT * FROM rol";
			
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				
				Rol documento = new Rol();
				documento.setId(rs.getString("id"));
				documento.setRolName(rs.getString("nomRol"));
				
				listDocumentos.add(documento);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listDocumentos;
	}

	@Override
	public int registrar(Registro Registro) {
		int value = 0;
		Connection cn = null; 
		PreparedStatement pstm = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			String sql = "INSERT INTO usuario VALUES (null,?,?,?,?,1)";
			pstm = cn.prepareStatement(sql);
			
			pstm.setString(1, Registro.getNom());
			pstm.setString(2, Registro.getApe());
			pstm.setString(3, Registro.getEmail());
			pstm.setString(4, Registro.getPass());
			
			value = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public List<Registro> listRegistro(){
		List<Registro> listuss = new ArrayList<Registro>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			
			String sql = "SELECT us.idUs , us.nom, us.ape, us.correo,rol.id, rol.nomRol FROM usuario as us INNER JOIN rol as rol on us.rol_idRol = rol.id";
			
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Registro reg = new Registro();
				reg.setId(rs.getString("idUs"));
				reg.setNom(rs.getString("nom"));
				reg.setApe(rs.getString("ape"));
				reg.setEmail(rs.getString("correo"));
				reg.setIdRol(rs.getString("id"));
				reg.setRolName(rs.getString("nomRol"));
				
				listuss.add(reg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return listuss;
	}

	
	public int eliminarRegistro(String idRegistro) {
		int salida = 0;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			
			String sql = "DELETE FROM usuario WHERE idUs = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, idRegistro);
			
			salida = pstm.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return salida;
	}

	
	public int editarRegistro(Registro Registro) {
		int value = 0;
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			
			cn = MysqlConexion.getConexion();
			
			String mysql = "UPDATE usuario SET contra = ? WHERE correo = ? and nom = ? and  ape = ?";
			
			pstm = cn.prepareStatement(mysql);
			
			
			pstm.setString(1, Registro.getPass());

			pstm.setString(2, Registro.getEmail());
			pstm.setString(3, Registro.getNom());
			pstm.setString(4, Registro.getApe());
			
			value = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}

	
	public Registro getRegistro(String id) {
		Registro regis = null;
		Connection cn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConexion();
			String mysql = "SELECT us.idUs , us.nom, us.ape, us.correo,rol.id, rol.nomRol FROM usuario as us \r\n"
					+ "INNER JOIN rol as rol ON us.rol_idRol = rol.id WHERE idUs = ?";
			psmt = cn.prepareStatement(mysql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				regis = new Registro();
				regis.setId(rs.getString("idUs"));
				regis.setNom(rs.getString("nom"));
				regis.setApe(rs.getString("ape"));
				regis.setEmail(rs.getString("correo"));
				regis.setIdRol(rs.getString("id"));
				regis.setRolName(rs.getString("nomRol"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (cn != null) cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return regis;
	}

}
