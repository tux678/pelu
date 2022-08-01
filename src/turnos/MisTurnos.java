package turnos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import administracion.Usuario;

@SuppressWarnings("serial")
public class MisTurnos extends Turnos {
	private Usuario profesional;

	public MisTurnos(Usuario usuarioActivo) {
		this.profesional = usuarioActivo;
		cmbProfesional.setVisible(false);
		setColumnaInvisible(Columna.importe);
		JButton btnMarcarFinalizado = new JButton();
		btnMarcarFinalizado.setIcon(new ImageIcon(Turnos.class.getResource("/recursos/iconCompletado.png")));
		btnMarcarFinalizado.setToolTipText("Trabajo con cliente finalizado");
		btnMarcarFinalizado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marcarFinalizado(getJTurnos().getSelectedRow());
			}
		});
		
		getToolBar().add(btnMarcarFinalizado);
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

	protected String getTablaTurnos() {
		return getProfesional().getUsuario().split(",")[0].trim() + "_turnos";
		
	}

	private Usuario getProfesional() {
		// TODO Auto-generated method stub
		return this.profesional;
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

