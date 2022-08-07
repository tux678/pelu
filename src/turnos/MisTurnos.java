package turnos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import administracion.Profesional;
import administracion.Usuario;
import conexion.Conexion;
import sql.SqlBuilder;

@SuppressWarnings("serial")
public class MisTurnos extends Turnos {

	public MisTurnos(Usuario usuarioActivo) {
		setProfesional((Profesional) usuarioActivo);
		cmbProfesional.setVisible(false);
		setColumnaInvisible(Columna.importe);
		JButton btnMarcarFinalizado = new JButton();
		btnMarcarFinalizado.setIcon(new ImageIcon(Turnos.class.getResource("/recursos/iconCompletado.png")));
		btnMarcarFinalizado.setToolTipText("Trabajo con cliente finalizado");
		btnMarcarFinalizado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getJTurnos().getSelectedRow() > -1 && !esTrabajoCompletado( getJTurnos().getSelectedRow())) {
					
					marcarFinalizado(getJTurnos().getSelectedRow());
					try {
						guardarFinalizado(getJTurnos().getSelectedRow());
					} catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		getToolBar().add(btnMarcarFinalizado);
	}
	
	protected void guardarFinalizado(int fila) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		PreparedStatement ps = cnx.getConect().prepareStatement("UPDATE turnos SET HECHO = ? WHERE id = ?");
		ps.setBoolean(1, esTrabajoCompletado(fila));
		ps.setLong(2, getId(fila));
		ps.addBatch();
		ps.executeUpdate();
		ps.isCloseOnCompletion();
		cnx.desconectar();
		
	}

	protected void guardarTrabajo(int fila) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		SqlBuilder sql = new SqlBuilder();
		sql.crearSqlInsert("trabajos");
		Conexion cnx = Conexion.getConexion();
		cnx.conectar();
		PreparedStatement ps = cnx.getConect().prepareStatement(sql.getSqlStringInsert());
		ps.setInt(1, getProfesional().getId());
		ps.setTimestamp(2, new java.sql.Timestamp(getFecha(fila, fecha.getDate())));
		ps.setString(3, getCliente(fila));
		ps.setString(4, getTrabajo(fila));
		ps.setInt(5, getTiempo(fila));
		ps.setDouble(6, getImporte(fila));
		ps.addBatch();
		ps.executeUpdate();
		ps.closeOnCompletion();
		cnx.desconectar();
		
	}

	private String getCliente(int fila) {
		// TODO Auto-generated method stub
		return (String) getJTurnos().getValueAt(fila, 1);
	}

	protected void marcarFinalizado(int fila) {
		// TODO Auto-generated method stub
		getJTurnos().setValueAt((Boolean) true, fila, 10);
		
	}

	private void setColumnaInvisible(Columna columna) {
		// TODO Auto-generated method stub
		getJTurnos().getColumnModel().getColumn(columna.ordinal()).setPreferredWidth(0);;
		getJTurnos().getColumnModel().getColumn(columna.ordinal()).setMaxWidth(0);;
	}


	protected boolean esColumnaImporte(int column) {
		// TODO Auto-generated method stub
		return column == Columna.importe.ordinal();
	}
	
	protected boolean esCeldaEditable(int fila, int columna) {
		// TODO Auto-generated method stub
    	if (esColumnaImporte(columna)) {
    		return false;
    	}
		
		return super.esCeldaEditable(fila, columna);
	}

}

