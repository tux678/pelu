package administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import conexion.Conexion;

public class SetUsuarioGui extends UsuarioGui {
	
	private Usuario usr;

	private static final int BTN_BUSCAR = 3;
	
	protected SetUsuarioGui() {
		super();
		setTitle("Modificar datos");
		JButton btnGuardar = (JButton) getContentPane().getComponent(BTN_GUARDAR);
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setUsuario();
					JOptionPane.showMessageDialog(null, "Datos guardados" );
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() );
				} finally {
					dispose();
				}

			}

		});
		JButton btnBuscar = (JButton) getContentPane().getComponent(BTN_BUSCAR);
		btnBuscar.setVisible(true);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getUsuario(Peluqueria.getUsuarioActivo().getSelectString(getTxtUsuario().getText()));
					btnGuardar.setEnabled(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage() );
				}
				
			}
			
		});
		
	}
	
	protected void getUsuario(String selectString) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		ResultSet rs = cnx.get(selectString);
		if(rs.next()) {
			getTxtNombre().setText(rs.getString("nombre"));
			getTxtApellido().setText(rs.getString("apellido"));
			getTxtDir().setText(rs.getString( "direccion"));
			getTxtLocal().setText(rs.getString("localidad"));
			getTxtTel().setText(rs.getString("telefono"));
			getTxtEmail().setText(rs.getString("email"));
		}else {
			JOptionPane.showMessageDialog(null, "registro no encontrado" );
		}
		cnx.desconectar();
		
		
	}

	public static UsuarioGui cambiar() {
		if(usuario == null) {
			usuario = new SetUsuarioGui();
		}
		return usuario;
	}
	
	private void setUsuario() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE Usuarios SET nombre = '" + getTxtNombre().getText() + "',apellido = '" 
				+ getTxtApellido().getText() +"',direccion = '" + getTxtDir().getText() + "',localidad = '" 
				+ getTxtLocal().getText() + "',telefono = '" + getTxtTel().getText() + "',email = '" 
				+ getTxtEmail().getText() + "' WHERE usuario = '" + getTxtUsuario().getText() + "'" + Peluqueria.getUsuarioActivo().getRolStringSql();
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		cnx.update(sql);
		cnx.desconectar();
		
	}

}
