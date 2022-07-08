package administracion;

import java.sql.SQLException;

import conexion.Conexion;

public class Profesional extends Usuario {

	public Profesional(String usuario, String clave, Rol categoria, String nombre, String apellido, String direccion, String localidad,
			String telefono, String email) throws ClassNotFoundException, SQLException {
		super(usuario, clave, categoria, nombre, apellido, direccion, localidad, telefono, email);
		// TODO Auto-generated constructor stub
		String sql = "INSERT INTO Usuarios ( usuario, clave, rol, nombre, apellido, direccion, localidad, telefono, email ) VALUES ('" 
				+ usuario + "','jara','Profesional','" + nombre + "','" + apellido + "','" + direccion + "','" + localidad + "','" + 
				telefono + "','" + email + "')";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();

	}

	public Profesional() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMenu(Peluqueria w) {
		// TODO Auto-generated method stub
		w.getJMenuBar().getMenu(1).getItem(0).setEnabled(false);
		w.getJMenuBar().getMenu(1).getItem(1).setEnabled(false);
		w.getJMenuBar().getMenu(1).getItem(2).setEnabled(false);
	}

	public void del(String usuario) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Usuarios WHERE usuario ='" + usuario + "' AND rol = 'Profesional'";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();
		
	}

}
