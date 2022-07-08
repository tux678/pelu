package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import conexion.Conexion;

public class DelAdministradorGui extends UsuarioGui {
	private static final int BTN_GUARDAR = 1;

	private DelAdministradorGui() {
		super();
		setTitle("Baja administrador");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.setText("Baja");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					delAdministrador();
					JOptionPane.showMessageDialog(null, "Baja exitosa" );
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() );
				} finally {
					
					dispose();
				}

			}

		});
		
	}
	public static UsuarioGui eliminar() {
		if(usuario == null) {
			usuario = new DelAdministradorGui();
		}
		return usuario;
	}
	
	private void delAdministrador() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Administrador a = new Administrador();
		a.del(getTxtUsuario().getText());
	}
	

}
