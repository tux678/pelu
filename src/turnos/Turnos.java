package turnos;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;
import administracion.Profesional;
import conexion.Conexion;
import sql.SqlBuilder;
import utilidades.Util;

import javax.swing.JScrollPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
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
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

@SuppressWarnings("serial")
class ColorFilasEspeciales extends DefaultTableCellRenderer {
	Font fontTurnoAsistido;
	public ColorFilasEspeciales(int Colpatron)
	{
	}
	
	private boolean esTurnoOcupado(JTable t, int fila) {
		// TODO Auto-generated method stub
		return t.getValueAt(fila, 9) != null && !((Boolean) t.getValueAt(fila, 9));
	}
	@Override
	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
	{        
	    super.getTableCellRendererComponent(table, value, selected, focused, row, column);
	    fontTurnoAsistido = new Font(table.getFont().getName(), Font.ITALIC, 12);
	      if(esTurnoOcupado(table, row))
	    {
	    	  if (esTurnoAsistido(table, row)){
	  	    	this.setFont(fontTurnoAsistido);
	  	    	if (selected) {
	  	    		this.setForeground(Color.gray);
	  	    		this.setBackground(new Color(176,196,222));
	  	    	}else {
	  	    		
	  	    		this.setForeground(Color.gray);
	  	    		this.setBackground(new Color(200,200,200));
	  	    	}
	    	  }else {
	    		  
	    		  if (selected) {
	    			  
	    			  this.setForeground(Color.black);
	    			  this.setBackground(new Color(176,196,222));
	    		  }else {
	    			  
	    			  this.setForeground(new Color(128,0,0));
	    			  this.setBackground(new Color(200,200,200));
	    		  }
	    	  }
	    }else {
	  	  if (selected) {
			  this.setBackground(new Color(135,206,250));
			  this.setForeground(Color.black);
			  
		  }else {
			  
			  this.setBackground(new Color(220,220,220));
			  this.setForeground( Color.blue);
		  }
	
	    }
	    return this;
	  }
	private boolean esTurnoAsistido(JTable turnos, int fila) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(fila, 10) != null ? (Boolean) turnos.getValueAt(fila, 10): false;
	}

}

@SuppressWarnings("serial")
class GestionCeldas extends DefaultTableCellRenderer 
{
   Color bg, fg;
   public GestionCeldas(Color bg, Color fg) {
      super();
      this.bg = bg;
      this.fg = fg;
   }
   private boolean esTurnoOcupado(JTable t, int fila) {
	// TODO Auto-generated method stub
	return t.getValueAt(fila, 9) != null && !((Boolean) t.getValueAt(fila, 9));
}
	public Component getTableCellRendererComponent(JTable table, Object 
	   value, boolean isSelected, boolean hasFocus, int row, int column) 
	   {
	      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	      Font fontTurnoAsistido = new Font(table.getFont().getName(), Font.ITALIC, 12);
	      if(Turnos.esColumnaNumerica(column)) {
	    	  ((JLabel) cell).setHorizontalAlignment(JLabel.RIGHT);
	    	  ((JLabel) cell).setHorizontalTextPosition(JLabel.RIGHT);
	      }
		    fontTurnoAsistido = new Font(table.getFont().getName(), Font.ITALIC, 11);
		      if(esTurnoOcupado(table, row))
		    {
		    	  if (esTurnoAsistido(table, row)){
		  	    	cell.setFont(fontTurnoAsistido);
		  	    	if (isSelected) {
		  	    		cell.setForeground(Color.gray);
		  	    		cell.setBackground(new Color(176,196,222));
		  	    	}else {
		  	    		cell.setForeground(Color.gray);
		  	    		cell.setBackground(new Color(200,200,200));
		  	    	}
		    	  }else {
		    		  if (isSelected) {
		    			  cell.setForeground(Color.black);
		    			  cell.setBackground(new Color(176,196,222));
		    		  }else {
		    			  cell.setForeground(new Color(128,0,0));
		    			  cell.setBackground(new Color(200,200,200));
		    		  }
		    	  }
		    }else {
		  	  if (isSelected) {
				  cell.setBackground(new Color(135,206,250));
				  cell.setForeground(Color.black);
				  
			  }else {
				  
				  cell.setBackground(new Color(220,220,220));
				  cell.setForeground( Color.blue);
			  }
		
		    }
	      return cell;
	   }
	private boolean esTurnoAsistido(JTable turnos, int fila) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(fila, 10) != null ? (Boolean) turnos.getValueAt(fila, 10): false;
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
   
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
        jcomponent.setOpaque(true);
        //jcomponent.setBackground( new Color(236,234,219) );
        jcomponent.setBackground( new Color(65,65,65) );
        jcomponent.setForeground(Color.white);
        
        return jcomponent;
    }


}

@SuppressWarnings("serial")
public class Turnos extends JInternalFrame {
	private static final int TURNOS_DIARIOS = 96;

	protected static boolean esColumnaDeTurnos(int column) {
		// TODO Auto-generated method stub
		return (column > 0 && column < 5);
	}
	public static boolean esColumnaHora(int columna) {
		// TODO Auto-generated method stub
		return columna == 0;
	}
	
	protected static boolean esColumnaNumerica(int columna) {
		// TODO Auto-generated method stub
		return columna > 2 && columna < 5;
	}
	protected static boolean esColumnaTiempo(int columna) {
		// TODO Auto-generated method stub
		return (columna == 3);
	}
	private JTable turnos;
	private DefaultTableModel modeloTurnos;
	protected JComboBox<Profesional> cmbProfesional;
	protected JDateChooser fecha;
	private int cantidadInsert = 0;
	private int cantidadUpdate = 0;

	private int cuentaEliminados = 0;
	private Format f = new SimpleDateFormat("HH:mm");
	private JToolBar toolBar;
	private JButton btnEliminar;

	InternalFrameAdapter ifa = null;
	private Profesional profesional;

	public Turnos() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(( ifa = new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent evt) {
				if( hayActualizados() || hayNuevos() ) {
					int respuesta = JOptionPane.showConfirmDialog(null, "Desea guardar los cambios realizados?" );
					if(respuesta == JOptionPane.YES_OPTION) {
						try {
							guardar(fecha.getDate());
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						((JInternalFrame) evt.getSource()).setDefaultCloseOperation(EXIT_ON_CLOSE);
						((JInternalFrame) evt.getSource()).dispose();
						
					}else if (respuesta == JOptionPane.CANCEL_OPTION) {
						((JInternalFrame) evt.getSource()).setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
						((JInternalFrame) evt.getSource()).removeInternalFrameListener(ifa);
						((JInternalFrame) evt.getSource()).doDefaultCloseAction();
						((JInternalFrame) evt.getSource()).addInternalFrameListener(ifa);
					}else {
						((JInternalFrame) evt.getSource()).setDefaultCloseOperation(EXIT_ON_CLOSE);
						((JInternalFrame) evt.getSource()).dispose();
					}
				}else {
					((JInternalFrame) evt.getSource()).dispose();
					
				}
			}
		}));
		this.setName("turnos");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 671, 421);
		getContentPane().setLayout(null);
		setTurnos();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(turnos);
		
		toolBar = new JToolBar();
		toolBar.setMaximumSize(new Dimension(13, 4));
		toolBar.setBounds(0, 0, 659, 30);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(toolBar,java.awt.BorderLayout.NORTH);
		
		cmbProfesional = new JComboBox<Profesional>();
		try {
			getProfesionales();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cmbProfesional.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setProfesional( (Profesional) cmbProfesional.getSelectedItem());
			}
		});
		toolBar.add(cmbProfesional);
		
		JButton btnGuardar = new JButton();
		btnGuardar.setIcon(new ImageIcon(Turnos.class.getResource("/recursos/save.png")));
		toolBar.add(btnGuardar);
		toolBar.addSeparator();
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardar(fecha.getDate());
					getTurnos(fecha.getDate());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
		btnGuardar.setActionCommand("Guardar");
		
		fecha = new JDateChooser();
		fecha.setCalendar(Calendar.getInstance());
		
		btnEliminar = new JButton();
		btnEliminar.setForeground(new Color(0, 0, 0));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTurno(turnos.getSelectedRow());
			}
		});
		btnEliminar.setIcon(new ImageIcon(Turnos.class.getResource("/recursos/delete.png")));
		toolBar.add(btnEliminar);
		toolBar.add(fecha);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		fecha.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange( PropertyChangeEvent evt) {
				setFecha(evt);
			}

		});
		cmbProfesional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecha.setCalendar(Calendar.getInstance());
				fecha.setDate(fecha.getCalendar().getTime());
				setProfesional( (Profesional) cmbProfesional.getSelectedItem());
				
			}
		});
		
	}

	private void contarEliminado() {
		// TODO Auto-generated method stub
		this.cuentaEliminados++;
	}
	public void contarInsert() {
		this.cantidadInsert++;
	}
	void contarUpdate() {
		// TODO Auto-generated method stub
		this.cantidadUpdate++;
		
	}
	private void descontarEliminado() {
		// TODO Auto-generated method stub
		if (hayEliminados()) {
			this.cuentaEliminados--;
		}
		
	}
	private void descontarModificados() {
		// TODO Auto-generated method stub
		if(hayActualizados()) {
			this.cantidadUpdate--;
		}
	}
	private void descontarNuevos() {
		// TODO Auto-generated method stub
		if (hayNuevos()) {
			this.cantidadInsert--;
		}
	}
	private void eliminarTurno(int fila) {
		// TODO Auto-generated method stub
		for(int f=0; f < turnos.getSelectedRowCount(); f++)
			this.setFila(turnos.getSelectedRows()[f]);
			
	}
	protected boolean esCeldaEditable(int fila, int columna) {
		// TODO Auto-generated method stub
    	if (esColumnaDeTurnos(columna)) {
    		return true;
    	}
		
		return false;
	}
	boolean esColumnaDeTexto(int columna) {
		// TODO Auto-generated method stub
		return columna < 3;
	}
	private boolean esFilaEliminada(int fila) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(fila, 8) != null && ((boolean) turnos.getValueAt(fila, 8))	;
	}
	protected boolean esFilaModificada(int r) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(r, 5) != null && ((Boolean) turnos.getValueAt(r, 5));
	}
	protected boolean esFilaNueva(int r) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(r, 6) != null && ((Boolean) turnos.getValueAt(r, 6));
	}
	protected boolean esFilaVacante(int row) {
		// TODO Auto-generated method stub
		return modeloTurnos.getValueAt(row, 6) == null;
	}
	protected boolean esTiempoDeTurnoValido(Object valor, int fila) {
		boolean esValido = true;
		int tiempo = Util.setInt(valor);
		if(esTurnoExcedido( tiempo)) {
			if((tiempo) > (fila + TURNOS_DIARIOS)) {
				esValido = false;
			}else {
				if (tiempo > (getSiguientesTurnosDisponibles(fila)+1)) {
					esValido = false;
				}
			}
		}
		return esValido;
		
	}
	protected boolean esTrabajoCompletado(int fila) {
		// TODO Auto-generated method stub
		return turnos.getValueAt(fila, 10) != null ? ((Boolean) turnos.getValueAt(fila, 10)) : false;
	}
	protected boolean esTurnoDisponible(int fila) {
		
		return turnos.getValueAt(fila, 9) == null ||  (Boolean) turnos.getValueAt(fila, 9);
	}
	private boolean esTurnoExcedido(int tiempo) {
		// TODO Auto-generated method stub
		return (tiempo > 1);
	}
	protected void extenderTurno(Object valor, int fila) {
		// TODO Auto-generated method stub
		int limite = Util.setInt(valor) + fila +1;
		for(int f=fila+1; f < limite-1; f++) {
			this.setNombre(f, this.getNombre(fila));
		}
	}
	public int getCantidadInsert() {
		return cantidadInsert;
	}

	public int getCantidadUpdate() {
		return cantidadUpdate;
	}
	
	protected long getFecha(int fila, Date fechaDeReferencia) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		c.setTime(fechaDeReferencia);
		String[] hora = ((String) turnos.getValueAt(fila, 0)).split(":");
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 
				Integer.parseInt(hora[0]), Integer.parseInt(hora[1]), 0);
		
		return c.getTimeInMillis();
	}

	private Calendar getFechaDeTurnoFinal(Date fecha) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0,0, 0);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c;
	}

	private Calendar getFechaDeTurnoInicial(Date fecha) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 
				0,0, 0);
		return c;

	}

	protected long getId(int fila) {
		// TODO Auto-generated method stub
		return (long) turnos.getValueAt(fila, 7);
	}
	protected double getImporte(int fila) {
		// TODO Auto-generated method stub
		return Util.setDouble(turnos.getValueAt(fila, 4));
	}

	protected JTable getJTurnos() {
		return turnos;
		
	}
	private String getNombre(int fila) {
		// TODO Auto-generated method stub
		return (String) turnos.getValueAt(fila, 1);
	}
	protected Profesional getProfesional() {
		// TODO Auto-generated method stub
		return this.profesional;
	}
	private void getProfesionales() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		ResultSet rs = cnx.get("Select id, usuario, nombre, apellido from usuarios where rol = 'Profesional'");
		while(rs.next()) {
			cmbProfesional.addItem(new Profesional(rs.getInt("id"), rs.getString("usuario"), rs.getString("nombre"), rs.getString("apellido")) );
		}
		cnx.desconectar();
		setProfesional( (Profesional) cmbProfesional.getSelectedItem());
	}
	private int getSiguientesTurnosDisponibles(int fila) {
		// TODO Auto-generated method stub
		int filas = 0;
		for(int f = fila+1; f < 96 && esTurnoDisponible(f); f++) {
			filas++;
		}
		return filas;
	}
	protected int getTiempo(int fila) {
		// TODO Auto-generated method stub
		return Util.setInt(turnos.getValueAt(fila, 3));
	}
	protected JToolBar getToolBar() {
		return toolBar;
	}
	protected String getTrabajo(int fila) {
		// TODO Auto-generated method stub
		return (String) modeloTurnos.getValueAt(fila, 2);
	}
	private void getTurnos(Date fechaDelTurno) throws ClassNotFoundException, SQLException {
		
		Calendar fechaDesdeTabla = Calendar.getInstance();
		
		int indice = 0;
		limpiarTabla();
		setFilas();
		
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		String sql = "select * from turnos where fecha between ? AND ? AND idProfesional = ?";

		PreparedStatement pst = cnx.getConect().prepareStatement(sql);
		pst.setTimestamp(1, new java.sql.Timestamp(getFechaDeTurnoInicial(fechaDelTurno).getTimeInMillis()));
		pst.setTimestamp(2, new java.sql.Timestamp(getFechaDeTurnoFinal(fechaDelTurno).getTimeInMillis()));
		pst.setInt(3, getProfesional().getId());
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			fechaDesdeTabla.setTime( rs.getTime("fecha"));
			indice = fechaDesdeTabla.get(Calendar.HOUR_OF_DAY) * 4 + fechaDesdeTabla.get(Calendar.MINUTE) / 15;
			modeloTurnos.setValueAt(rs.getString("cliente"), indice, 1);
			modeloTurnos.setValueAt(rs.getString("trabajo"), indice, 2);
			modeloTurnos.setValueAt(rs.getInt("tiempo"), indice, 3);
			modeloTurnos.setValueAt(rs.getDouble("importe"), indice, 4);
			setId(rs.getLong("id"), indice);
			setTrabajoCompletado(rs.getBoolean("hecho"), indice);
			unSetFilaModificada(indice);
			unSetFilaNueva(indice);
		}
		cnx.desconectar();
		setCantidadInsert();
		setCantidadUpdate();
	}
	private void guardar(Date fechaDelTurno) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int eliminados = 0;
		int actualizados = 0;
		int nuevos = 0;
		Conexion cnx = null;
		
		if (hayCambios()) {
			cnx = Conexion.getConexion();
			cnx.conectar();
			
			if (hayEliminados()) {
				SqlBuilder sql = new SqlBuilder();
				sql.crearSqlDelete("turnos");
				PreparedStatement ps = cnx.getConect().prepareStatement(sql.getSqlStringDelete());
				for(int r=0;r < turnos.getRowCount(); r++) {
					if(esFilaEliminada(r) && !esFilaNueva(r)) {
						ps.setInt(1,turnos.getValueAt(r, 7)!= null ?  (int) turnos.getValueAt(r, 7):0);
						ps.addBatch();
					}
				}
				
				eliminados = ps.executeBatch().length;
				ps.closeOnCompletion();
				setContarEliminados();
			}
			if (hayNuevos()) {
				SqlBuilder sql = new SqlBuilder();
				sql.crearSqlInsert("turnos");
				PreparedStatement ps = cnx.getConect().prepareStatement(sql.getSqlStringInsert());
				for(int r=0;r < turnos.getRowCount(); r++) {
					if(esFilaNueva(r)) {
						unSetFilaModificada(r);
						unSetFilaNueva(r);
						ps.setInt(1, getProfesional().getId());
						ps.setTimestamp(2, new java.sql.Timestamp(getFecha(r, fechaDelTurno)));
						ps.setString(3, (String) turnos.getValueAt(r, 1));
						ps.setString(4, getTrabajo(r));
						ps.setInt(5,turnos.getValueAt(r, 3)!= null ? Integer.parseInt( (String) turnos.getValueAt(r, 3)):0);
						ps.setDouble(6, getImporte(r));
						ps.setBoolean(7, esTrabajoCompletado(r));
						ps.addBatch();
					}
				}
				nuevos = ps.executeBatch().length;
				ps.closeOnCompletion();
				setCantidadInsert(0);
			}
			if (hayActualizados()) {
				SqlBuilder sql = new SqlBuilder();
				sql.crearSqlUpdate("turnos");
				PreparedStatement psUpd = cnx.getConect().prepareStatement(sql.getSqlStringUpdate());
				for(int r=0;r < turnos.getRowCount(); r++) {
					if(esFilaModificada(r)) {
						unSetFilaModificada(r);
						psUpd.setString(1, (String) turnos.getValueAt(r, 1));
						psUpd.setString(2,  (String) turnos.getValueAt(r, 2));
						psUpd.setInt(3,this.getTiempo(r));
						psUpd.setDouble(4, getImporte(r));
						psUpd.setBoolean(5, esTrabajoCompletado(r));
						psUpd.setLong(6 ,getId(r));
						psUpd.addBatch();
					}
				}
				actualizados = psUpd.executeBatch().length;
				psUpd.closeOnCompletion();
				setCantidadUpdate(0);
				
			}
			JOptionPane.showMessageDialog(null, eliminados + " registros eliminados correctamente. \n" +
					actualizados + " registros actualizados correctamente. \n" + nuevos  + " registros agregados correctamente. \n" );
			cnx.desconectar();
		}
		
	}
	protected boolean hayActualizados() {
		// TODO Auto-generated method stub
		return getCantidadUpdate() > 0;
	}
	private boolean hayCambios() {
		// TODO Auto-generated method stub
		return hayEliminados() || hayActualizados() || hayNuevos();
	}
	private boolean hayEliminados() {
		// TODO Auto-generated method stub
		return this.cuentaEliminados > 0;
	}
	protected boolean hayNuevos() {
		// TODO Auto-generated method stub
		return getCantidadInsert() > 0;
	}
	private void limpiarTabla() {
		((DefaultTableModel) turnos.getModel()).setRowCount(0);
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
		modeloTurnos.addColumn("B");
		modeloTurnos.addColumn("L");
		modeloTurnos.addColumn("F");
		turnos.getColumnModel().getColumn(5).setMaxWidth(1);
		turnos.getColumnModel().getColumn(6).setMaxWidth(1);
		turnos.getColumnModel().getColumn(7).setMaxWidth(1);
		turnos.getColumnModel().getColumn(8).setMaxWidth(1);
		turnos.getColumnModel().getColumn(9).setMaxWidth(1);
		turnos.getColumnModel().getColumn(10).setMaxWidth(1);
		turnos.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldas(Color.lightGray, Color.blue));
		turnos.getColumnModel().getColumn(3).setCellRenderer(new GestionCeldas(Color.lightGray, Color.blue));
		turnos.getColumnModel().getColumn(4).setCellRenderer(new GestionCeldas(Color.lightGray, Color.blue));
		turnos.getTableHeader().setDefaultRenderer(new GestionEncabezadoTabla());
	}

	private void setContarEliminados() {
		// TODO Auto-generated method stub
		this.cuentaEliminados = 0;
	}

	protected void setFecha(PropertyChangeEvent evt) {
		if (evt.getNewValue()!= null) {
			try {
				if( hayActualizados() || hayNuevos() ) {
					int respuesta = JOptionPane.showConfirmDialog(null, "Desea guardar los cambios realizados?" );
					if(respuesta == JOptionPane.YES_OPTION) {
						guardar((Date)evt.getOldValue());
						getTurnos((Date)evt.getNewValue());
					}else if(respuesta == JOptionPane.CANCEL_OPTION) {
						fecha.removePropertyChangeListener(fecha.getPropertyChangeListeners()[0]);
						fecha.setDate(((Date)evt.getOldValue()));
						fecha.addPropertyChangeListener(new PropertyChangeListener() {
							public void propertyChange( PropertyChangeEvent evt) {
								setFecha(evt);
							}

						});
					}else {
						getTurnos(fecha.getDate());
					}
				}else {
					if(fecha.getDate()!=null)
						getTurnos(fecha.getDate());
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void setFila(int fila) {
		// TODO Auto-generated method stub
		turnos.setValueAt(null, fila, 1); 
		turnos.setValueAt(null, fila, 2); 
		turnos.setValueAt(0, fila, 3); 
		turnos.setValueAt(0, fila, 4);
		setFilaEliminada(fila);
		setTurnoLibre(fila);
		if(!esFilaNueva(fila)) {
			unSetFilaModificada(fila);
		}else {
			unSetFilaNueva(fila);
		}
		
	}
	private void setFilaEliminada(int fila) {
		// TODO Auto-generated method stub
		turnos.setValueAt(Boolean.TRUE, fila, 8);
		if (!esFilaNueva(fila))
			contarEliminado();
	}

	void setFilaModificada(int row) {
		modeloTurnos.setValueAt(Boolean.TRUE, row, 5);
	}

	void setFilaNueva(int row) {
		// TODO Auto-generated method stub
		modeloTurnos.setValueAt(Boolean.TRUE, row, 6);
	}

	private void setFilas() {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha.getDate());
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0,0, 0);
		for(int i = 0; i < 96; i++) {
			modeloTurnos.addRow(new Object[] {f.format(c.getTime())});
			c.add(Calendar.MINUTE, 15);
		}
	}
	private void setId(long id, int fila) {
		// TODO Auto-generated method stub
		modeloTurnos.setValueAt(id , fila, 7);
	}

	protected DefaultTableModel setModelo() {
		return new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return esCeldaEditable(row, column);
		    }
		    @Override
			public void setValueAt(Object aValue, int row, int column) {
				// TODO Auto-generated method stub
		    	if(esColumnaNumerica(column) && !Util.esNumero(aValue)) {
		    		return;
		    	}else {
		    		Object valorViejo = super.getValueAt(row, column);
		    		if(esColumnaTiempo(column)) {
		    			if (esTiempoDeTurnoValido(aValue, row)) {
		    				super.setValueAt(aValue, row, column);
		    				extenderTurno(aValue,row);
		    			}
		    		}else {
		    			super.setValueAt(aValue, row, column);
		    		}
		    		
		    		if (esColumnaDeTurnos(column)) {
		    			if(aValue!= null && !aValue.equals(valorViejo) && ((!esColumnaDeTexto(column) 
		    					&& Util.esNumero(aValue)) || (esColumnaDeTexto(column) && !Util.esTextoVacio(aValue)))) {
		    				unSetFilaEliminada(row);
		    				if ( esFilaVacante(row) || !tieneId(row) ) {
		    					setFilaNueva(row);
		    					contarInsert();
		    				}else if(!esFilaNueva(row)){
		    					setFilaModificada(row);
		    					contarUpdate();
		    				}
		    				setTurnoOcupado(row);
		    			}
		    		}
		    	}
			}
		};
	}
	private void setNombre(int fila, String nombre) {
		// TODO Auto-generated method stub
		turnos.setValueAt(nombre, fila, 1);
	}
	protected void setProfesional(Profesional profesional) {
		// TODO Auto-generated method stub
		this.profesional=profesional;
	}
	
	private void setTrabajoCompletado(boolean esCompletado, int fila) {
		// TODO Auto-generated method stub
		modeloTurnos.setValueAt(esCompletado, fila, 10);
	}
	private void setTurnoLibre(int fila) {
		// TODO Auto-generated method stub
		turnos.setValueAt((Boolean)true, fila, 9);
	}
	protected void setTurnoOcupado(int fila) {
		// TODO Auto-generated method stub
		turnos.setValueAt((Boolean) false, fila, 9);
	}
	private void setTurnos() {
		turnos = new JTable();
		turnos.setName("turnos");
		turnos.setSelectionBackground(new Color(135, 206, 250));
		turnos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		turnos.setBackground(new Color(220, 220, 220));
		turnos.setShowHorizontalLines(false);
		turnos.setShowVerticalLines(false);
		turnos.setGridColor(Color.LIGHT_GRAY);
		turnos.setDefaultRenderer(Object.class, new ColorFilasEspeciales(2));
		modeloTurnos = setModelo();
		turnos.setModel(modeloTurnos);
		setColumnas();
	}
	
	protected boolean tieneId( int fila) {
		// TODO Auto-generated method stub
		return Util.esNumero(turnos.getValueAt(fila, 7));
	}
	protected void unSetFilaEliminada(int fila) {
		// TODO Auto-generated method stub
		turnos.setValueAt(Boolean.FALSE, fila, 8);
		descontarEliminado();
	}
	private void unSetFilaModificada(int indice) {
		modeloTurnos.setValueAt(Boolean.FALSE, indice, 5);
		descontarModificados();
	}
	private void unSetFilaNueva(int indice) {
		modeloTurnos.setValueAt(Boolean.FALSE, indice, 6);
		descontarNuevos();
	}
}
