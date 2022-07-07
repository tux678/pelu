package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AdministradorGui extends UsuarioGui {
	
	private static final int BTN_GUARDAR = 1;
	private AdministradorGui() {
		super();
		setTitle("Nuevo administrador");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addAdministrador();
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
	public static UsuarioGui agregarAdministrador() {
		if(usuario == null) {
			usuario = new AdministradorGui();
		}
		return usuario;
	}
	
	private void addAdministrador() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Administrador u = new Administrador(getTxtUsuario().getText(), "jara", Rol.Administrador, getTxtNombre().getText(), getTxtApellido().getText(), 
				getTxtDir().getText(), getTxtLocal().getText(), getTxtTel().getText(), getTxtEmail().getText());
		
	}

}
