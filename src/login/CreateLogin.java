package login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilidades.Util;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CreateLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField pwdAdmin;
	private JPasswordField pwdReintento;
	private static CreateLogin login;
	

	/**
	 * Create the dialog.
	 */
	private CreateLogin() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNuevoPwd = new JLabel("Nueva clave");
		lblNuevoPwd.setBounds(56, 98, 133, 13);
		contentPanel.add(lblNuevoPwd);
		{
			JLabel lblReingresoClave = new JLabel("Reingreso de clave");
			lblReingresoClave.setBounds(56, 152, 133, 13);
			contentPanel.add(lblReingresoClave);
		}
		{
			JLabel lblNewLabel = new JLabel("Ingrese nueva clave para el administrador general");
			lblNewLabel.setBounds(100, 26, 235, 13);
			contentPanel.add(lblNewLabel);
		}
		
		pwdAdmin = new JPasswordField();
		pwdAdmin.setBounds(218, 95, 123, 19);
		contentPanel.add(pwdAdmin);
		
		pwdReintento = new JPasswordField();
		pwdReintento.setBounds(218, 149, 123, 16);
		contentPanel.add(pwdReintento);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sí");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Util.toString( pwdAdmin.getPassword()).equals(Util.toString( pwdReintento.getPassword()))){
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
	
	public static CreateLogin getLogin() {
		if(login == null) {
			login = new CreateLogin();
		}
		return login;
	}
	
	public String getClave() {
		return Util.toString( this.pwdAdmin.getPassword());
	}

}
