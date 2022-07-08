package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DelProfesionalGui extends UsuarioGui {
	private static final int BTN_GUARDAR = 1;

	private DelProfesionalGui() {
		super();
		setTitle("Baja profesional");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.setText("Baja");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					delProfesional();
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
			usuario = new DelProfesionalGui();
		}
		return usuario;
	}
	
	private void delProfesional() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Profesional p = new Profesional();
		p.del(getTxtUsuario().getText());
	}

}
