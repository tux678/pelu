package administracion;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
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

	@Override
	public String getSelectString(String usuario) {
		// TODO Auto-generated method stub
		
		return  "SELECT * FROM Usuarios WHERE usuario ='" + usuario + "' AND NOT rol = 'ADMIN'";
	}

	public String getRolStringSql() {
		return " AND NOT rol = 'ADMIN'";
	}

	@Override
	public String getDeleteSql(String usuario) {
		// TODO Auto-generated method stub
		return "DELETE FROM usuarios WHERE usuario = '" + usuario + "' AND NOT rol = 'ADMIN'" ;
	}

}
