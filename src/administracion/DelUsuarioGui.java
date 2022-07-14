package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import conexion.Conexion;

public class DelUsuarioGui extends SetUsuarioGui {
	protected DelUsuarioGui() {
		super();
		setTitle("Baja usuario");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.setText("Baja");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if( JOptionPane.showConfirmDialog(null, "Confirma Baja") == JOptionPane.YES_OPTION) {
						delUsuario();
						JOptionPane.showMessageDialog(null, "Baja exitosa" );
					}
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
			usuario = new DelUsuarioGui();
		}
		return usuario;
	}
	
	protected void delUsuario() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = Peluqueria.getUsuarioActivo().getDeleteSql(getTxtUsuario().getText());
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();
	}

}
