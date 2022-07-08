package administracion;

import java.sql.SQLException;

import conexion.Conexion;

public class Secretarix extends Usuario {

	@Override
	public void setMenu(Peluqueria w) {
		// TODO Auto-generated method stub
		w.getJMenuBar().getMenu(1).getItem(0).setEnabled(false);
		w.getJMenuBar().getMenu(1).getItem(1).setEnabled(false);
	}

	public void del(String usuario) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Usuarios WHERE usuario ='" + usuario + "' AND rol = 'Secretarix'";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();
		
	}

}
