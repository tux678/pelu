package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import conexion.Conexion;

public class AddProfesionalGui extends UsuarioGui {

	private static final int BTN_GUARDAR = 1;
	private AddProfesionalGui() {
		super();
		setTitle("Nuevo profesional");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addProfesional();
					JOptionPane.showMessageDialog(null, "Datos guardados" );
					limpiar();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() );
					dispose();
				} finally {
					
				}

			}

		});
		
	}
	public static UsuarioGui agregar() {
		if(usuario == null) {
			usuario = new AddProfesionalGui();
		}
		return usuario;
	}
	
	private void addProfesional() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Usuarios ( usuario, clave, rol, nombre, apellido, direccion, localidad, telefono, email ) VALUES ('" 
				+ getTxtUsuario().getText() + "','jara','Profesional','" + getTxtNombre().getText() + "','" + 
				getTxtApellido().getText() + "','" + getTxtDir().getText() + "','" + getTxtLocal().getText() +
				"','" + getTxtTel().getText() + "','" + getTxtEmail().getText() + "')";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		sql = "CREATE TABLE IF NOT EXISTS " + getTxtUsuario().getText() 
				+  "_trabajos ( id INT AUTO_INCREMENT PRIMARY KEY, nombreCliente VARCHAR(55) NOT NULL," 
		+" fecha dateTIME NOT NULL DEFAULT NOW() unique, trabajo varchar(200) NOT NULL, tiempo int, importe DOUBLE NOT NULL)";
		cnx.update(sql);
		sql = "CREATE TABLE IF NOT EXISTS " + getTxtUsuario().getText() 
				+ "_turnos (id INT AUTO_INCREMENT PRIMARY KEY, fecha DATETIME NOT NULL DEFAULT NOW() unique, cliente varchar(50), trabajo varchar(100), tiempo int, importe double)";
		cnx.update(sql);
		cnx.desconectar();
		
	}

}
