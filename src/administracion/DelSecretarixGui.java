package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DelSecretarixGui extends UsuarioGui {
	private static final int BTN_GUARDAR = 1;

	private DelSecretarixGui() {
		super();
		setTitle("Baja secretarie");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.setText("Baja");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					delSecretarix();
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
			usuario = new DelSecretarixGui();
		}
		return usuario;
	}
	
	private void delSecretarix() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Secretarix s = new Secretarix();
		s.del(getTxtUsuario().getText());
	}

}
