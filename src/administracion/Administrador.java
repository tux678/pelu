package administracion;

import java.sql.SQLException;

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

}
