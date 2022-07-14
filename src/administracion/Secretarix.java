package administracion;

import java.sql.SQLException;

import conexion.Conexion;

public class Secretarix extends Usuario {

	public Secretarix(String usuario, String clave, Rol categoria, String nombre, String apellido, String direccion,
			String localidad, String telefono, String email) {
		// TODO Auto-generated constructor stub
	}

	public Secretarix() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMenu(Peluqueria w) {
		// TODO Auto-generated method stub
		w.getJMenuBar().getMenu(1).getItem(0).setEnabled(false);
		w.getJMenuBar().getMenu(1).getItem(1).setEnabled(false);
	}

	public void del(String usuario) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Usuarios WHERE usuario ='" + usuario + "' AND rol = 'Profesional'";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();
		
	}
	@Override
	public String getSelectString(String usuario) {
		// TODO Auto-generated method stub
		return  "SELECT * FROM Usuarios WHERE usuario ='" + usuario + "' AND rol = 'Profesional'";
		
	}

	public String getRolStringSql() {
		return " AND rol = 'Profesional'";
	}

	@Override
	public String getDeleteSql(String usuario) {
		// TODO Auto-generated method stub
		return "DELETE FROM usuarios WHERE usuario = '" + usuario + "' AND rol = 'Profesional'" ;
	}

}
