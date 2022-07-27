package turnos;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;
import administracion.Profesional;
import conexion.Conexion;
import sql.SqlBuilder;
import utilidades.Util;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
class GestionCeldas extends DefaultTableCellRenderer 
{
   Color bg, fg;
   public GestionCeldas(Color bg, Color fg) {
      super();
      this.bg = bg;
      this.fg = fg;
   }
   public Component getTableCellRendererComponent(JTable table, Object 
   value, boolean isSelected, boolean hasFocus, int row, int column) 
   {
      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      if(Turnos.esColumnaNumerica(column)) {
    	  ((JLabel) cell).setHorizontalAlignment(JLabel.RIGHT);
    	  ((JLabel) cell).setHorizontalTextPosition(JLabel.RIGHT);
      }else if(Turnos.esColumnaHora(column)){
    	  
    	  if (isSelected) {
    		  cell.setBackground(new Color(0,120,215));
    		  cell.setForeground(Color.white);
    		  
    	  }else {
    		  
    		  cell.setBackground(bg);
    		  cell.setForeground(fg);
    	  }
      }
      return cell;
   }
}

class GestionEncabezadoTabla  implements TableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JComponent jcomponent = null;
        
        if( value instanceof String ) {
            jcomponent = new JLabel((String) value);
            ((JLabel)jcomponent).setHorizontalAlignment( SwingConstants.CENTER );
            ((JLabel)jcomponent).setSize( 30, jcomponent.getWidth() );   
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth())  );
        }
   
        //jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(221, 211, 211)));
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        //jcomponent.setBackground( new Color(236,234,219) );
        jcomponent.setBackground( new Color(65,65,65) );
//        jcomponent.setToolTipText("Tabla Seguimiento");
        jcomponent.setForeground(Color.white);
        
        return jcomponent;
    }


}

@SuppressWarnings("serial")
public class Turnos extends JInternalFrame {
	public static boolean esColumnaHora(int columna) {
		// TODO Auto-generated method stub
		return columna == 0;
	}
	protected static boolean esColumnaNumerica(int columna) {
		// TODO Auto-generated method stub
		return columna > 2 && columna < 5;
	}
	private JTable turnos;
	private String tablaTurnos;
	private Calendar fechaDeTurno = Calendar.getInstance();
	private DefaultTableModel modeloTurnos;
	private JComboBox<Profesional> cmbProfesional;
	private JDateChooser fecha;
	private int cantidadInsert = 0;
	private int cantidadUpdate = 0;
	private Format f = new SimpleDateFormat("HH:mm");

	private JToolBar toolBar;
	private JButton btnEliminar;

	public Turnos() {
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 671, 421);
		getContentPane().setLayout(null);
		modeloTurnos = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		    	if (esColumnaDeTurnos(column)) {
		    		return true;
		    	}
		       return false;
		    }
		    @Override
			public void setValueAt(Object aValue, int row, int column) {
				// TODO Auto-generated method stub
		    	if(esColumnaNumerica(column) && !Util.esNumero(aValue)) {
		    		return;
		    	}else {
		    		Object valorViejo = super.getValueAt(row, column);
		    		super.setValueAt(aValue, row, column);
		    		
		    		if (esColumnaDeTurnos(column)) {
		    			if(aValue!= null && !aValue.equals(valorViejo) && ((!esColumnaDeTexto(column) 
		    					&& Util.esNumero(aValue)) || (esColumnaDeTexto(column) && !esTextoVacio(aValue)))) {
		    				if ( esFilaVacante(row) ) {
		    					setFilaNueva(row);
		    					contarInsert();
		    				}else if(!esFilaNueva(row)){
		    					setFilaModificada(row);
		    					contarUpdate();
		    				}
		    			}
		    		}
		    	}
			}
		};
		
		setColumnas();
		
		JScrollPane scrollPane = new JScrollPane();
		
		turnos = new JTable();
		turnos.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		turnos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		turnos.setBackground(new Color(220, 220, 220));
		turnos.setShowHorizontalLines(false);
		turnos.setShowVerticalLines(false);
		turnos.setGridColor(Color.LIGHT_GRAY);
		setTurnos();
		scrollPane.setViewportView(turnos);
		
		toolBar = new JToolBar();
		toolBar.setMaximumSize(new Dimension(13, 4));
		toolBar.setBounds(0, 0, 659, 30);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(toolBar,java.awt.BorderLayout.NORTH);
		
		cmbProfesional = new JComboBox<Profesional>();
		toolBar.add(cmbProfesional);
		
		JButton btnGuardar = new JButton();
		btnGuardar.setIcon(new ImageIcon(Turnos.class.getResource("/recursos/save.png")));
//		btnGuardar.setIcon(new ImageIcon("recursos/ico_guardar.png"));
		toolBar.add(btnGuardar);
		toolBar.addSeparator();
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardar();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
		btnGuardar.setActionCommand("Guardar");
		
		fecha = new JDateChooser();
		fecha.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				inicializarFechaDeTurnos();
			}
		});
		
		btnEliminar = new JButton();
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTurno();
			}
		});
		btnEliminar.setIcon(new ImageIcon(Turnos.class.getResource("/recursos/delete.png")));
		toolBar.add(btnEliminar);
		toolBar.add(fecha);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		fecha.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (fecha.getDate()!= null) {
					try {
						if( hayActualizados() || hayNuevos() ) {
							int respuesta = JOptionPane.showConfirmDialog(null, "Desea guardar los cambios realizados?" );
							if(respuesta == JOptionPane.YES_OPTION) {
								setFechaDeTurno((Date)evt.getOldValue());
								guardar();
								setFechaDeTurno((Date)evt.getNewValue());
								getTurnos(fecha);
							}else if(respuesta == JOptionPane.CANCEL_OPTION) {
								setFechaDeTurno((Date)evt.getOldValue());
							}else {
								getTurnos(fecha);
							}
						}else {
							getTurnos(fecha);
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}

		});
		cmbProfesional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaTurnos =  cmbProfesional.getSelectedItem().toString().split(",")[0].trim() + "_turnos";
			}
		});
		
		try {
			getProfesionales();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void contarInsert() {
		this.cantidadInsert++;
	}
	private void contarUpdate() {
		// TODO Auto-generated method stub
		this.cantidadUpdate++;
		
	}
	private void eliminarTurno() {
		// TODO Auto-generated method stub
		turnos.setValueAt(null, turnos.getSelectedRow(), 1); 
		turnos.setValueAt(null, turnos.getSelectedRow(), 2); 
		turnos.setValueAt(0, turnos.getSelectedRow(), 3); 
		turnos.setValueAt(0, turnos.getSelectedRow(), 4);
		if(!esFilaNueva(turnos.getSelectedRow())) {
			setFilaEliminada(turnos.getSelectedRow());
		}
	}
	private void setFilaEliminada(int fila) {
		// TODO Auto-generated method stub
		turnos.setValueAt(Boolean.TRUE, fila, 8);
	}
	private boolean esColumnaDeTexto(int columna) {
		// TODO Auto-generated method stub
		return columna < 3;
	}

	protected boolean esColumnaDeTurnos(int column) {
		// TODO Auto-generated method stub
		return (column > 0 && column < 5);
	}
	
	protected boolean esFilaModificada(int r) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(r, 5) != null && turnos.getValueAt(r, 5).equals("true");
	}
	
	protected boolean esFilaNueva(int r) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(r, 6) != null && turnos.getValueAt(r, 6).equals("true");
	}

	protected boolean esFilaVacante(int row) {
		// TODO Auto-generated method stub
		return modeloTurnos.getValueAt(row, 6) == null;
	}

	private boolean esTextoVacio(Object texto) {
		// TODO Auto-generated method stub
		return !(((String) texto).length() > 0);
	}

	public int getCantidadInsert() {
		return cantidadInsert;
	}

	public int getCantidadUpdate() {
		return cantidadUpdate;
	}

	private long getFecha(int fila) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		c.setTime(fechaDeTurno.getTime());
		String[] hora = ((String) turnos.getValueAt(fila, 0)).split(":");
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 
				Integer.parseInt(hora[0]), Integer.parseInt(hora[1]), 0);
		
		return c.getTimeInMillis();
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

	private void getTurnos(JDateChooser fecha) throws ClassNotFoundException, SQLException {
		
		Calendar fechaDesdeTabla = Calendar.getInstance();
		Calendar fechaDeTurnoFinal = Calendar.getInstance();
		
		int indice = 0;
		setFechaDeTurno();
		setFechaDeTurnoFinal(fechaDeTurnoFinal);
		limpiarTabla();
		setFilas();
		
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		String sql = "select * from " + tablaTurnos + " where fecha between ? AND ?";

		PreparedStatement pst = cnx.getConect().prepareStatement(sql);
		pst.setTimestamp(1, new java.sql.Timestamp(fecha.getDate().getTime()));
		pst.setTimestamp(2, new java.sql.Timestamp(fechaDeTurnoFinal.getTimeInMillis()));
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			fechaDesdeTabla.setTime( rs.getTime("fecha"));
			indice = fechaDesdeTabla.get(Calendar.HOUR_OF_DAY) * 4 + fechaDesdeTabla.get(Calendar.MINUTE) / 15;
			modeloTurnos.setValueAt(rs.getString("cliente"), indice, 1);
			modeloTurnos.setValueAt(rs.getString("trabajo"), indice, 2);
			modeloTurnos.setValueAt(rs.getInt("tiempo"), indice, 3);
			modeloTurnos.setValueAt(rs.getDouble("importe"), indice, 4);
			modeloTurnos.setValueAt(rs.getInt("id"), indice, 7);
			unSetFilaModificada(indice);
			unSetFilaNueva(indice);
		}
		cnx.desconectar();
		setCantidadInsert();
		setCantidadUpdate();
	}

	private void guardar() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Conexion cnx = null;
		if (hayNuevos()) {
			SqlBuilder sql = new SqlBuilder();
			sql.crearSqlInsert(tablaTurnos);
			cnx = Conexion.getConexion();
			cnx.conectar();
			PreparedStatement ps = cnx.getConect().prepareStatement(sql.getSqlStringInsert());
			for(int r=0;r < turnos.getRowCount(); r++) {
				if(esFilaNueva(r)) {
					unSetFilaModificada(r);
					unSetFilaNueva(r);
					ps.setTimestamp(1, new java.sql.Timestamp(getFecha(r)));
					ps.setString(2, (String) turnos.getValueAt(r, 1));
					ps.setString(3,  (String) turnos.getValueAt(r, 2));
					ps.setInt(4,turnos.getValueAt(r, 3)!= null ? Integer.parseInt( (String) turnos.getValueAt(r, 3)):0);
					ps.setDouble(5, turnos.getValueAt(r, 4)!= null? Double.parseDouble((String) turnos.getValueAt(r, 4)):0);
					ps.addBatch();
				}
			}
			JOptionPane.showMessageDialog(null, ps.executeBatch().length + " registros agregados correctamente" );
			
			ps.closeOnCompletion();
			setCantidadInsert(0);
		}
		if (hayActualizados()) {
			SqlBuilder sql = new SqlBuilder();
			sql.crearSqlUpdate(tablaTurnos);
			cnx = Conexion.getConexion();
			cnx.conectar();
			PreparedStatement psUpd = cnx.getConect().prepareStatement(sql.getSqlStringUpdate());
			for(int r=0;r < turnos.getRowCount(); r++) {
				if(esFilaModificada(r)) {
					unSetFilaModificada(r);
					psUpd.setString(1, (String) turnos.getValueAt(r, 1));
					psUpd.setString(2,  (String) turnos.getValueAt(r, 2));
					psUpd.setInt(3,turnos.getValueAt(r, 3)!= null ? Integer.parseInt( (String) turnos.getValueAt(r, 3)):0);
					psUpd.setDouble(4, turnos.getValueAt(r, 4)!= null? (double) turnos.getValueAt(r, 4) :0);
					psUpd.setInt(5,turnos.getValueAt(r, 7)!= null ?  (int) turnos.getValueAt(r, 7):0);
					psUpd.addBatch();
					
				}
			}
			JOptionPane.showMessageDialog(null, psUpd.executeBatch().length + " registros actualizados correctamente" );
			
			psUpd.closeOnCompletion();
			setCantidadUpdate(0);
			
		}
		if (cnx != null) {
			cnx.desconectar();
		}
		
	}

	protected boolean hayActualizados() {
		// TODO Auto-generated method stub
		return getCantidadUpdate() > 0;
	}

	protected boolean hayNuevos() {
		// TODO Auto-generated method stub
		return getCantidadInsert() > 0;
	}

	private void inicializarFechaDeTurnos() {
		fechaDeTurno.set(Calendar.HOUR_OF_DAY, 0);
		fechaDeTurno.set(Calendar.MINUTE, 0);
		fecha.setDate(fechaDeTurno.getTime());
	}

	private void limpiarTabla() {
		modeloTurnos.setRowCount(0);
	}

	private void setCantidadInsert() {
		// TODO Auto-generated method stub
		this.cantidadInsert = 0;
	}

	public void setCantidadInsert(int cantidadInsert) {
		this.cantidadInsert = cantidadInsert;
	}

	private void setCantidadUpdate() {
		// TODO Auto-generated method stub
		this.cantidadUpdate = 0;
	}

	public void setCantidadUpdate(int cantidadUpdate) {
		this.cantidadUpdate = cantidadUpdate;
	}
	
	private void setColumnas() {
		modeloTurnos.addColumn("hora");
		modeloTurnos.addColumn("Cliente");
		modeloTurnos.addColumn("Trabajo");
		modeloTurnos.addColumn("tiempo");
		modeloTurnos.addColumn("importe");
		modeloTurnos.addColumn("M");
		modeloTurnos.addColumn("N");
		modeloTurnos.addColumn("Id");
		modeloTurnos.addColumn("D");
	}

	private void setFechaDeTurno() {
		// TODO Auto-generated method stub
		setFechaDeTurno(fecha.getDate());
	}
	private void setFechaDeTurno(Date fecha) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		fechaDeTurno.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 
				c.get(Calendar.HOUR_OF_DAY),0, 0);
	}

	private void setFechaDeTurnoFinal(Calendar fechaDeTurnoFinal) {
		fechaDeTurnoFinal.setTime(fecha.getDate());
		fechaDeTurnoFinal.add(Calendar.DAY_OF_MONTH, 1);
	}

	private void setFilaModificada(int row) {
		modeloTurnos.setValueAt("true", row, 5);
	}

	private void setFilaNueva(int row) {
		// TODO Auto-generated method stub
		modeloTurnos.setValueAt("true", row, 6);
	}

	private void setFilas() {
		Calendar c = Calendar.getInstance();
		c.setTime(fechaDeTurno.getTime());
		c.add(Calendar.HOUR_OF_DAY, -c.get(Calendar.HOUR_OF_DAY));
		for(int i = 0; i < 96; i++) {
			modeloTurnos.addRow(new Object[] {f.format(c.getTime())});
			c.add(Calendar.MINUTE, 15);
		}
	}
	private void setTurnos() {
		turnos.setName("turnos");
		turnos.setModel(modeloTurnos);
		turnos.getColumnModel().getColumn(5).setPreferredWidth(20);
		turnos.getColumnModel().getColumn(6).setPreferredWidth(20);
		turnos.getColumnModel().getColumn(5).setMaxWidth(20);
		turnos.getColumnModel().getColumn(6).setMaxWidth(20);
		turnos.getColumnModel().getColumn(7).setPreferredWidth(10);
		turnos.getColumnModel().getColumn(8).setPreferredWidth(10);
		turnos.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldas(Color.lightGray, Color.blue));
		turnos.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldas(Color.lightGray, Color.blue));
		turnos.getColumnModel().getColumn(4).setCellRenderer(new GestionCeldas(Color.lightGray, Color.blue));
		turnos.getTableHeader().setDefaultRenderer(new GestionEncabezadoTabla());
	}

	private void unSetFilaModificada(int indice) {
		modeloTurnos.setValueAt("false", indice, 5);
	}
	
	private void unSetFilaNueva(int indice) {
		modeloTurnos.setValueAt("false", indice, 6);
	}
}