package administracion;

import java.sql.SQLException;

import javax.swing.JTextField;

import conexion.Conexion;

public class Administrador extends Usuario {

	public Administrador(String usuario, String clave, Rol categoria, String nombre, String apellido, String direccion, String localidad,
			String telefono, String email) throws ClassNotFoundException, SQLException {
		super(usuario, clave, categoria, nombre, apellido, direccion, localidad, telefono, email);
		// TODO Auto-generated constructor stub
		String sql = "INSERT INTO Usuarios ( usuario, clave, rol, nombre, apellido, direccion, localidad, telefono, email ) VALUES ('" 
				+ usuario + "','jara','Administrador','" + nombre + "','" + apellido + "','" + direccion + "','" + localidad + "','" + 
				telefono + "','" + email + "')";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();

	}

	public Administrador() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMenu(Peluqueria w) {
		// TODO Auto-generated method stub
		
	}

	public void del(String usuario) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Usuarios WHERE usuario ='" + usuario + "' AND rol = 'Administrador'";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();
		// TODO Auto-generated method stub
		
	}


}
