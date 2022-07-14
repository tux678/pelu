package turnos;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import java.awt.BorderLayout;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import administracion.Profesional;
import conexion.Conexion;

import javax.swing.JScrollPane;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.beans.PropertyChangeEvent;
import javax.swing.JComboBox;

public class Turnos extends JInternalFrame {
	private JTable turnos;
	private DefaultTableModel modeloTurnos;
	private Calendar fechaDeTurno = Calendar.getInstance();
	Format f = new SimpleDateFormat("HH:mm");
	private JComboBox<Profesional> cmbProfesional;

	public Turnos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		modeloTurnos = new DefaultTableModel();
		
		modeloTurnos.addColumn("hora");
		modeloTurnos.addColumn("Cliente");
		modeloTurnos.addColumn("Tarea");
		modeloTurnos.addColumn("importe");
		
		JDateChooser fecha = new JDateChooser();
		fecha.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (fecha.getDate()!= null) {
					fechaDeTurno.setTime(fecha.getDate());
					fechaDeTurno.set(Calendar.HOUR, 0);
					fechaDeTurno.set(Calendar.MINUTE, 0);
					
					modeloTurnos.setRowCount(0);
					for(int i = 0; i < 96; i++) {
						modeloTurnos.addRow(new Object[] {f.format(fechaDeTurno.getTime())});
						fechaDeTurno.add(Calendar.MINUTE, 15);
					}
					
				}
			}
		});
		fecha.setBounds(251, 10, 159, 19);
		getContentPane().add(fecha);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 418, 211);
		getContentPane().add(scrollPane);
		
		turnos = new JTable();
		turnos.setName("turnos");
		turnos.setModel(modeloTurnos);
		scrollPane.setViewportView(turnos);
		
		cmbProfesional = new JComboBox<Profesional>();
		cmbProfesional.setBounds(10, 8, 159, 21);
		getContentPane().add(cmbProfesional);
		
		try {
			getProfesionales();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getProfesionales() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		ResultSet rs = cnx.get("Select usuario, nombre, apellido from usuarios where rol = 'Profesional'");
		while(rs.next()) {
			cmbProfesional.addItem( new Profesional(rs.getString("usuario"), rs.getString("nombre"), rs.getString("apellido")));
		}
		cnx.desconectar();
	}
}
