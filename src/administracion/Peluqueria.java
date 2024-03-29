package administracion;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;
import informes.Diario;
import informes.InfoDiario;
import informes.VistaPrevia;
import login.Login;
import turnos.MisTurnos;
import turnos.Turnos;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;

import com.toedter.calendar.JDateChooser;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

@SuppressWarnings("serial")
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int respuesta = JOptionPane.showConfirmDialog(null, "Quiere abandonar la aplicacion?", "Jara",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					((JFrame) e.getSource()).dispose();
				}
			}
		});
		setName("Jara");
		setTitle("Jara");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 810, 514);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mnCerrarSesion = new JMenuItem("Cerrar sesion");
		mnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioActivo = null;
				dispose();
				Cargardor.recargar();
				
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
		
		JMenuItem mnAdAdmin = new JMenuItem("Agregar administrador");
		mnAdAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAdministradorGui admin = (AddAdministradorGui) AddAdministradorGui.agregar();
				escritorio.add(admin);
				admin.setVisible(true);
			}
		});
		mnAdmin.add(mnAdAdmin);
		
		JMenuItem mnAgregarSecretarie = new JMenuItem("Agregar secretarie");
		mnAgregarSecretarie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSecretarieGui admin = (AddSecretarieGui) AddSecretarieGui.agregar();
				escritorio.add(admin);
				admin.setVisible(true);
			}
		});
		mnAdmin.add(mnAgregarSecretarie);
		
		JMenuItem mnAddProfesional = new JMenuItem("Agregar profesional");
		mnAddProfesional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProfesionalGui admin = (AddProfesionalGui) AddProfesionalGui.agregar();
				escritorio.add(admin);
				admin.setVisible(true);
			}
		});
		mnAdmin.add(mnAddProfesional);
		
		JMenuItem mnActualizar = new JMenuItem("Actualizar datos");
		mnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetUsuarioGui admin = (SetUsuarioGui) SetUsuarioGui.cambiar();
				escritorio.add(admin);
				admin.setVisible(true);
			}
		});
		mnAdmin.add(mnActualizar);
		
		JMenuItem mnBajaUsuarios = new JMenuItem("Baja usuarios");
		mnAdmin.add(mnBajaUsuarios);
		mnBajaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelUsuarioGui admin = (DelUsuarioGui) DelUsuarioGui.eliminar();
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
				SetUsuarioGui admin = (SetUsuarioGui) SetUsuarioGui.cambiar();
				escritorio.add(admin);
				admin.setVisible(true);
			}
		});
		mnAdmin.add(mnActualizarMisDatos);
		mnAdmin.add(mnActualizarClave);
		
		JMenu mnTurnos = new JMenu("Turnos");
		menuBar.add(mnTurnos);
		
		JMenuItem mnItTurnos = new JMenuItem("Turnos");
		mnItTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Turnos frame = new Turnos();
				escritorio.add(frame);
				frame.setVisible(true);
			}
		});
		mnTurnos.add(mnItTurnos);
		
		JMenuItem mntmMisTurnos = new JMenuItem("Mis turnos");
		mntmMisTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Turnos frame = new MisTurnos(getUsuarioActivo());
				escritorio.add(frame);
				frame.setVisible(true);
			}
		});
		mnTurnos.add(mntmMisTurnos);
		
		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);
		
		JMenuItem mnInformeDiario = new JMenuItem("Diario");
		mnInformeDiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat formatoLocal = new SimpleDateFormat("dd/MM/yyyy");
				try {
					new InfoDiario(JOptionPane.showInputDialog("Ingrese la fecha para el informe", formatoLocal.format( Calendar.getInstance(new Locale("es", "ES")).getTime())));
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnInformes.add(mnInformeDiario);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NativeInterface.open();
				VistaPrevia frame = new VistaPrevia();
				escritorio.add(frame);
				frame.setVisible(true);
			}
		});
		mnInformes.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		escritorio = new JDesktopPane();
		escritorio.setBounds(new Rectangle(0, 0, 50, 0));
		contentPane.add(escritorio, "name_183461914586100");
	}
	
	public static Peluqueria getPeluqueria() {
		if(peluqueria == null) {
			peluqueria = new Peluqueria();
		}
		return peluqueria;
	}
	
	public static void limpiar() {
		peluqueria = null;
	}
	
	public static Usuario getUsuarioActivo() {
		// TODO Auto-generated method stub
		return usuarioActivo;
	}

	public JDesktopPane getEscritorio() {
		return escritorio;
		// TODO Auto-generated method stub
		
	}
}
