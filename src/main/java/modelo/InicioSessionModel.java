package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.MysqlConexion;
import entidades.Persona;
import entidades.Usuario;
import entidades.Persona;
import interfaces.InicioSessionInterface;

public class InicioSessionModel implements InicioSessionInterface {

	@Override
	public Usuario verificarSession(String correo, String contra) {
		Usuario usuario = null;

		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;

		try {

			cn = MysqlConexion.getConexion();

			String mysql = "SELECT usuario.idUs, usuario.nom, usuario.ape, usuario.correo,usuario.contra,ro.id FROM usuario AS usuario \r\n"
					+ "INNER JOIN rol AS ro ON usuario.rol_idRol = ro.id\r\n" + "WHERE correo = ? AND contra = ? ";

			psmt = cn.prepareStatement(mysql);
			psmt.setString(1, correo);
			psmt.setString(2, contra);

			rs = psmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setNom(rs.getString("nom"));
				usuario.setApe(rs.getString("ape"));
				usuario.setId(rs.getString("idUs"));
				usuario.setEmail(rs.getString("correo"));
				usuario.setRol(rs.getString("id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return usuario;
	}

}
