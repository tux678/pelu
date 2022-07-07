package administracion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import utilidades.Util;

public class ActualizacionDeClave extends JInternalFrame {
	
	private final JPanel contentPanel = new JPanel();
	private static ActualizacionDeClave pwd = null;
	private JPasswordField pwdActual;
	private JPasswordField pwdNuevo;
	private JPasswordField pwdReintento;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ActualizacionDeClave frame = new ActualizacionDeClave();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	private ActualizacionDeClave() {
		setTitle("Cambio de clave");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setFocusCycleRoot(true);
		
		JLabel lblNuevoPwd = new JLabel("Nueva clave");
		lblNuevoPwd.setBounds(56, 116, 133, 13);
		contentPanel.add(lblNuevoPwd);
		{
			JLabel lblReingresoClave = new JLabel("Reingreso de clave");
			lblReingresoClave.setBounds(56, 152, 133, 13);
			contentPanel.add(lblReingresoClave);
		}
		
		JLabel lblClaveActual = new JLabel("Clave Actual");
		lblClaveActual.setBounds(56, 77, 91, 13);
		contentPanel.add(lblClaveActual);
		
		pwdActual = new JPasswordField();
		pwdActual.setBounds(240, 74, 125, 19);
		contentPanel.add(pwdActual);
		
		pwdNuevo = new JPasswordField();
		pwdNuevo.setBounds(240, 113, 125, 19);
		contentPanel.add(pwdNuevo);
		
		pwdReintento = new JPasswordField();
		pwdReintento.setBounds(240, 149, 125, 19);
		contentPanel.add(pwdReintento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sí");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Util.toString( pwdNuevo.getPassword()).equals(Util.toString( pwdReintento.getPassword())) && Peluqueria.getUsuarioActivo().getClave().equals(Util.toString(pwdActual.getPassword()))){
							try {
								Usuarios usr = new Usuarios();
								Peluqueria.getUsuarioActivo().setClave(Util.toString(pwdNuevo.getPassword()));
								usr.setClave(Peluqueria.getUsuarioActivo());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							dispose();
						}else {
							JOptionPane.showMessageDialog(okButton, "No coinciden los ingresos");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						System.exit(ABORT);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}
	
	public static ActualizacionDeClave getActualizacionDeClave() {
		if (pwd == null) {
			pwd = new ActualizacionDeClave();
		}
		return pwd;
	}
}
