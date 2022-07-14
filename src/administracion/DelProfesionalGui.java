package administracion;

import java.sql.SQLException;

import conexion.Conexion;

public class DelProfesionalGui extends DelUsuarioGui {

	@Override
	protected void delUsuario() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		super.delUsuario();
		String sql = "DROP TABLE " + getTxtUsuario().getText() + "_turnos";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		sql = "DROP TABLE " + getTxtUsuario().getText() + "_trabajos";
		cnx.update(sql);
		cnx.desconectar();
	}

}
