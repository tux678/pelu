package administracion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

import conexion.Conexion;

import javax.swing.JRadioButton;

public abstract class UsuarioGui extends JInternalFrame {
	private JTextField txtDir;
	private JTextField txtLocal;
	private JTextField txtTel;
	private JTextField txtEmail;
	private JTextField txtUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	protected static UsuarioGui usuario = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UsuarioGui frame = getUsuarioGui();
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
	protected UsuarioGui() {
		setTitle("Usuario");
		setBounds(100, 100, 676, 407);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 664, 330);
		getContentPane().add(tabbedPane);
		
		JPanel tabBasico = new JPanel();
		tabbedPane.addTab("Basico", null, tabBasico, null);
		tabBasico.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(140, 56, 379, 191);
		tabBasico.add(panel_2);
		panel_2.setLayout(null);
		
		setTxtUsuario(new JTextField());
		getTxtUsuario().setColumns(10);
		getTxtUsuario().setBounds(207, 35, 96, 19);
		panel_2.add(getTxtUsuario());
		
		setTxtNombre(new JTextField());
		getTxtNombre().setColumns(10);
		getTxtNombre().setBounds(207, 99, 96, 19);
		panel_2.add(getTxtNombre());
		
		setTxtApellido(new JTextField());
		getTxtApellido().setColumns(10);
		getTxtApellido().setBounds(207, 128, 96, 19);
		panel_2.add(getTxtApellido());
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(76, 38, 45, 13);
		panel_2.add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("nombre");
		lblNewLabel.setBounds(76, 102, 45, 13);
		panel_2.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("apellido");
		lblApellido.setBounds(76, 131, 45, 13);
		panel_2.add(lblApellido);

		JPanel tabAlternativos = new JPanel();
		tabbedPane.addTab("Datos alternativos", null, tabAlternativos, null);
		tabAlternativos.setLayout(null);
		
		setTxtDir(new JTextField());
		getTxtDir().setBounds(293, 43, 96, 19);
		tabAlternativos.add(getTxtDir());
		getTxtDir().setColumns(10);
		
		JLabel lblDir = new JLabel("direccion");
		lblDir.setBounds(166, 46, 45, 13);
		tabAlternativos.add(lblDir);
		
		txtLocal = new JTextField();
		txtLocal.setBounds(293, 72, 96, 19);
		tabAlternativos.add(txtLocal);
		txtLocal.setColumns(10);
		
		JLabel lblLocal = new JLabel("Localidad");
		lblLocal.setBounds(166, 75, 45, 13);
		tabAlternativos.add(lblLocal);
		
		setTxtTel(new JTextField());
		getTxtTel().setBounds(293, 101, 96, 19);
		tabAlternativos.add(getTxtTel());
		getTxtTel().setColumns(10);
		
		JLabel lblTel = new JLabel("Telefono");
		lblTel.setBounds(166, 104, 45, 13);
		tabAlternativos.add(lblTel);
		
		setTxtEmail(new JTextField());
		getTxtEmail().setBounds(293, 130, 96, 19);
		tabAlternativos.add(getTxtEmail());
		getTxtEmail().setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(166, 133, 45, 13);
		tabAlternativos.add(lblEmail);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});
		btnGuardar.setMnemonic('G');
		btnGuardar.setBounds(359, 340, 85, 21);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(487, 340, 85, 21);
		getContentPane().add(btnCancelar);
		
	}
	
	
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}


	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}


	public JTextField getTxtApellido() {
		return txtApellido;
	}


	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}


	public JTextField getTxtDir() {
		return txtDir;
	}


	public void setTxtDir(JTextField txtDir) {
		this.txtDir = txtDir;
	}


	public JTextField getTxtNombre() {
		return txtNombre;
	}


	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}


	public JTextField getTxtTel() {
		return txtTel;
	}


	public void setTxtTel(JTextField txtTel) {
		this.txtTel = txtTel;
	}


	public JTextField getTxtEmail() {
		return txtEmail;
	}


	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}


	public JTextField getTxtLocal() {
		return txtLocal;
	}


	public void setTxtLocal(JTextField txtLocal) {
		this.txtLocal = txtLocal;
	}
}
