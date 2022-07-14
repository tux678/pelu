package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import conexion.Conexion;

public class AddSecretarieGui extends UsuarioGui {
	private static final int BTN_GUARDAR = 1;
	private AddSecretarieGui() {
		super();
		setTitle("Nuevx secretarie");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addSecretarie();
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
			usuario = new AddSecretarieGui();
		}
		return usuario;
	}
	
	private void addSecretarie() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Usuarios ( usuario, clave, rol, nombre, apellido, direccion, localidad, telefono, email ) VALUES ('" 
				+ getTxtUsuario().getText() + "','jara','Secretarix','" + getTxtNombre().getText() + "','" + 
				getTxtApellido().getText() + "','" + getTxtDir().getText() + "','" + getTxtLocal().getText() +
				"','" + getTxtTel().getText() + "','" + getTxtEmail().getText() + "')";
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();
		
	}

}
