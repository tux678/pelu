package administracion;

import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;
import login.Login;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class Peluqueria extends JFrame {

	private JPanel contentPane;
	public static JDesktopPane escritorio;
	
	private static Peluqueria peluqueria;
	private static Usuario usuarioActivo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Conexion cnx = Conexion.getConexion();
		Login login = Login.getLogin();
		login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		login.setVisible(true);
		boolean hayConexion = false; 
		
		if (login.esAceptado()) {
			
			try {
				cnx.getDefaultConnection();
				Usuarios usr = new Usuarios();
				if ((usuarioActivo = usr.getUsuario(login.getLog()))!=null) {
					
					hayConexion = true;
				}else {
					
					JOptionPane.showMessageDialog(null, "Datos de ingreso incorrectos" );
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage() );
			}finally{
				
				try {
					cnx.desconectar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage() );
				}
				if(!hayConexion) {
					return;
				}
			}
			
			login = null;

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Peluqueria w = getPeluqueria();
						w.setExtendedState(MAXIMIZED_BOTH);
						w.setVisible(true);
						usuarioActivo.setMenu(w);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
		}else {
			login = null;
		}
	}

	/**
	 * Create the frame.
	 */
	private Peluqueria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mnCerrarSesion = new JMenuItem("Cerrar sesion");
		mnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioActivo = null;
				
			}
		});
		mnSistema.add(mnCerrarSesion);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnSistema.add(mntmSalir);
		
		JMenu mnAdmin = new JMenu("Administracion");
		menuBar.add(mnAdmin);
		
		JMenu mnAdministrador = new JMenu("Administrador");
		mnAdmin.add(mnAdministrador);
		
		JMenuItem mnAgregarAdministrador = new JMenuItem("Agregar");
		mnAdministrador.add(mnAgregarAdministrador);
		
		JMenuItem mnSetAdmin = new JMenuItem("Modificar");
		mnSetAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnAdministrador.add(mnSetAdmin);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Baja");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelAdministradorGui admin = (DelAdministradorGui) DelAdministradorGui.eliminar();
				escritorio.add(admin);
				admin.setVisible(true);
			}
		});
		mnAdministrador.add(mntmNewMenuItem);
		mnAgregarAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAdministradorGui admin = (AddAdministradorGui) AddAdministradorGui.agregar();
				escritorio.add(admin);
				admin.setVisible(true);
			}
		});
		
		JMenu mnSecretarix = new JMenu("Secretarix");
		mnAdmin.add(mnSecretarix);
		
		JMenuItem mnAddSecretarix = new JMenuItem("Agregar");
		mnSecretarix.add(mnAddSecretarix);
		
		JMenuItem mnSetSecretarix = new JMenuItem("Modificar");
		mnSecretarix.add(mnSetSecretarix);
		
		JMenuItem mnBaja = new JMenuItem("Baja");
		mnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelSecretarixGui sec = (DelSecretarixGui) DelSecretarixGui.eliminar();
				escritorio.add(sec);
				sec.setVisible(true);
			}
		});
		mnSecretarix.add(mnBaja);
		
		JMenu mnPro = new JMenu("Profesional");
		mnAdmin.add(mnPro);
		
		JMenuItem mnAgregarProfesional = new JMenuItem("Agregar");
		mnPro.add(mnAgregarProfesional);
		
		JMenuItem mnSetPro = new JMenuItem("Modificar");
		mnPro.add(mnSetPro);
		
		JMenuItem mnBajaPro = new JMenuItem("Baja");
		mnBajaPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelProfesionalGui pro = (DelProfesionalGui) DelSecretarixGui.eliminar();
				escritorio.add(pro);
				pro.setVisible(true);
			}
		});
		mnPro.add(mnBajaPro);
		mnAgregarProfesional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesionalGui admin = (ProfesionalGui) ProfesionalGui.agregarProfesional();
				escritorio.add(admin);
				admin.setVisible(true);

			}
		});
		
		JMenuItem mnActualizarClave = new JMenuItem("Actualizar clave");
		mnActualizarClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizacionDeClave pwd = ActualizacionDeClave.getActualizacionDeClave();
				escritorio.add(pwd);
				pwd.setVisible(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		mnAdmin.add(separator);
		
		JMenuItem mnActualizarMisDatos = new JMenuItem("Actualizar mis datos");
		mnActualizarMisDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnAdmin.add(mnActualizarMisDatos);
		mnAdmin.add(mnActualizarClave);
		
		JMenu mnTurnos = new JMenu("Turnos");
		menuBar.add(mnTurnos);
		
		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		escritorio = new JDesktopPane();
		contentPane.add(escritorio, "name_183461914586100");
	}
	
	public static Peluqueria getPeluqueria() {
		if(peluqueria == null) {
			peluqueria = new Peluqueria();
		}
		return peluqueria;
	}
	
	public static Usuario getUsuarioActivo() {
		// TODO Auto-generated method stub
		return usuarioActivo;
	}
}
