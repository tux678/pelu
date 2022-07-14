package turnos;

import java.util.Date;

public class Turno {
	Date fecha;
	int turno;
	String cliente;
	String trabajo;
	Double importe;

	public Turno(Date fecha, int turno, String cliente, String trabajo, Double importe) {
		super();
		this.fecha = fecha;
		this.turno = turno;
		this.cliente = cliente;
		this.trabajo = trabajo;
		this.importe = importe;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return fecha + ", " + turno + ", " + cliente + ", " + trabajo + ", " + importe + "]";
	}
	
}
