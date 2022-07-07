package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SecretarixGui extends UsuarioGui{
	private static final int BTN_GUARDAR = 1;
	private SecretarixGui() {
		super();
		setTitle("Nuevo profesional");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addProfesional();
					JOptionPane.showMessageDialog(null, "Datos guardados" );
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}

		});
		
	}
	public static UsuarioGui agregarProfesional() {
		if(usuario == null) {
			usuario = new SecretarixGui();
		}
		return usuario;
	}
	
	private void addProfesional() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		new Profesional(getTxtUsuario().getText(), "jara", Rol.Secretarix, getTxtNombre().getText(), getTxtApellido().getText(), 
				getTxtDir().getText(), getTxtLocal().getText(), getTxtTel().getText(), getTxtEmail().getText());
		
	}

}
