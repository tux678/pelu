package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AddAdministradorGui extends UsuarioGui {
	
	private static final int BTN_GUARDAR = 1;
	private AddAdministradorGui() {
		super();
		setTitle("Nuevo administrador");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addAdministrador();
					JOptionPane.showMessageDialog(null, "Datos guardados" );
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() );
				} finally {
					
					dispose();
				}

			}

		});
		
	}
	public static UsuarioGui agregar() {
		if(usuario == null) {
			usuario = new AddAdministradorGui();
		}
		return usuario;
	}
	
	private void addAdministrador() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Administrador u = new Administrador(getTxtUsuario().getText(), "jara", Rol.Administrador, getTxtNombre().getText(), getTxtApellido().getText(), 
				getTxtDir().getText(), getTxtLocal().getText(), getTxtTel().getText(), getTxtEmail().getText());
		
	}

}
