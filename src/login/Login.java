package login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilidades.Util;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwdClave;
	private static Login login;
	private boolean aceptado = false;


	/**
	 * Create the dialog.
	 */
	
	private Login() {
		
		setTitle("Aplicacion");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(93, 65, 45, 13);
			contentPanel.add(lblUsuario);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.setBounds(203, 62, 96, 19);
			contentPanel.add(txtUsuario);
			txtUsuario.setColumns(10);
		}
		{
			JLabel lblClave = new JLabel("Clave");
			lblClave.setBounds(93, 135, 45, 13);
			contentPanel.add(lblClave);
		}
		{
			pwdClave = new JPasswordField();
			pwdClave.setBounds(203, 132, 96, 19);
			contentPanel.add(pwdClave);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sí");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptado = true;
						dispose();
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
						aceptado = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean esAceptado() {
		// TODO Auto-generated method stub
		return this.aceptado;
	}

	public String getLog() {
		return this.txtUsuario.getText() + "," + Util.toString(pwdClave.getPassword());
	}

	public static Login getLogin() {
		if( login == null) {
			login = new Login();
		}
		return login;
	}

}
