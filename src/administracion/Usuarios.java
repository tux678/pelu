package administracion;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;

public class Usuarios {
	
	private Usuario[] u = new Usuario[3];
	
	public Usuarios() {
		u[0] = new Administrador();
		u[1] = new Secretarix();
		u[2] = new Profesional();
	}
	
	public Usuario getUsuario(String log) throws SQLException {
		String usuario[] = log.split(",");
		Conexion cnx = Conexion.getConexion();
		ResultSet rs = null;
		Usuario usr = null;

		try {
			cnx.conectar();
			cnx.update("USE pelos;");
			String sql = "SELECT usuario, clave, rol FROM usuarios WHERE usuario = '" + usuario[0] +
			"' AND clave = '" + usuario[1] + "';";
			rs = cnx.get(sql);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rs.next() != false) {
			Rol r = Rol.valueOf(rs.getString(3));
			u[r.ordinal()].setUsuario(rs.getString(1));
			u[r.ordinal()].setClave(rs.getString(2));
			u[r.ordinal()].setCategoria(rs.getString(3));
			usr = u[r.ordinal()];
		}
		return usr;
	}

	public void setClave(Usuario usuarioActivo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update("UPDATE usuarios SET clave = '" + usuarioActivo.getClave() + 
				"' WHERE usuario = '" + Peluqueria.getUsuarioActivo().getUsuario() + "';");
		cnx.desconectar();
		
	}

}
