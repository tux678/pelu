package instalador;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

import conexion.Conexion;
import login.CreateLogin;
import utilidades.RedirOutputStream;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Instalacion {

	private JFrame frame;
	public static Instalacion install;
	private JTextArea jText;
	private JTextArea jTextError;
	private JButton jBCerrar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instalacion window = getInstalacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Conexion cnx = Conexion.getConexion();
				
				//Pide crear clave de Administrador
				CreateLogin dialog = CreateLogin.getLogin();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				RedirOutputStream os = new RedirOutputStream(System.out);
				//RedirOutputStream es = new RedirOutputStream(System.err);
				os.setJText(getInstalacion().getJTextArea());
				//es.setJText(getInstalacion().getJTextError());
				
				try {
					System.out.println("Conectando...");
					cnx.getDefaultConnection();
					if(cnx != null) {
						System.out.println("Creando base de datos...");
						getInstalacion().crearBase(cnx);
						System.out.println("Creando cuenta del Administrador...");
						getInstalacion().crearUsuarioAdmin(dialog.getClave(), cnx);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					System.err.println( e.getMessage());
//					try {
//						es.flush();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
				}
				try {
					System.out.println("Desconectando...");
					cnx.desconectar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println( e.getMessage());
				}
				System.out.println("Hecho");
				getInstalacion().jBCerrar.setEnabled(true);
				try {
					os.flush();
					os.close();
					//es.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Instalacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		frame.setBounds(100, 100, 662, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Instalacion del sistema de peluquerias");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(38, 28, 360, 39);
		panel.add(lblNewLabel);
		
		JTextArea txtSalida = new JTextArea();
		txtSalida.setBackground(Color.BLACK);
		txtSalida.setForeground(Color.GREEN);
		txtSalida.setBounds(38, 77, 569, 167);
		panel.add(txtSalida);
		this.jText = txtSalida;
		
		JTextArea txtError = new JTextArea();
		txtError.setForeground(Color.RED);
		txtError.setBackground(Color.GRAY);
		txtError.setBounds(38, 271, 569, 48);
		panel.add(txtError);
		this.jTextError = txtError;
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCerrar.setEnabled(false);
		btnCerrar.setBounds(522, 363, 85, 21);
		panel.add(btnCerrar);
		jBCerrar = btnCerrar;
	}
	
	private void crearUsuarioAdmin(String clave, Conexion cnx) throws SQLException {
		String sql = "CREATE USER IF NOT EXISTS admin IDENTIFIED BY '" + clave + "';";
		cnx.update(sql);
		sql = "GRANT ALL PRIVILEGES ON pelos.* TO admin  WITH GRANT OPTION";
		cnx.update(sql);
		//Crear la cuenta en la tabla de usuarios
		sql = "INSERT INTO usuarios (usuario, clave, rol, nombre, apellido) VALUES ('admin','" + clave + "', 'Administrador','admin','admin')";
		cnx.update(sql);
	}

	private void crearBase(Conexion cnx) throws SQLException {
		// TODO Auto-generated method stub
		//Crea la base de datos
		String sql = "CREATE SCHEMA IF NOT EXISTS pelos DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;";
		cnx.update(sql);
		//Crea la tabla usuarios
		cnx.update("USE pelos;");
		sql = "CREATE TABLE IF NOT EXISTS usuarios ( id INT AUTO_INCREMENT PRIMARY KEY, rol ENUM('Administrador','Secreatarix','profesional') NOT NULL, "
				+ "usuario VARCHAR(55) NOT NULL UNIQUE, "
				+ "clave VARCHAR(55) NOT NULL, fecha_ingreso DATE DEFAULT(CURRENT_DATE), nombre VARCHAR(55) NOT NULL,"
				+ "apellido VARCHAR(55) NOT NULL, direccion VARCHAR(100), localidad VARCHAR(55), telefono VARCHAR(55), "
				+ "email VARCHAR(100), INDEX (usuario)); ";
		cnx.update(sql);
	}
	public static Instalacion getInstalacion() {
		if (install == null) {
			install = new Instalacion();
		}
		return install;
	}
	
	public JTextArea getJTextArea() {
		return this.jText;
	}
	
	public JTextArea getJTextError() {
		return this.jTextError;
	}
}
