package peluqueria;

import java.util.Calendar;
import java.util.Objects;

public class Turno implements Comparable<Turno>{
	
	private static int tiempoDeTurnoPrefijado = 25;

	private Calendar turno;
	private Profesional profesional;
	private String cliente;
	private String detalle;
	private double importe;
	private Calendar finDeTurno;
	private int tiempoDeTurno = 25;
	
	
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Calendar getTurno() {
		return turno;
	}
	public void setTurno(Calendar turno) {
		this.turno = turno;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "Turnera [cliente=" + cliente + ", turno=" + turno + ", detalle=" + detalle + "]";
	}
	public Turno(String cliente, Calendar turno, String detalle, Profesional profesional) {
		super();
		this.cliente = cliente;
		this.turno = turno;
		this.detalle = detalle;
		this.profesional = profesional;
		this.setTiempoDeTurno(tiempoDeTurnoPrefijado);
		this.finDeTurno.add(Calendar.MINUTE, +tiempoDeTurno);
	}
	@Override
	public int hashCode() {
		return Objects.hash(profesional, turno, finDeTurno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		
		Turno other = (Turno) obj;
		return Objects.equals(profesional, other.profesional) && (turno.after(other.turno)  && (this.finDeTurno.before(other.finDeTurno) || this.turno.before(other.finDeTurno)));
	}
	public  int getTiempoDeTurno() {
		return tiempoDeTurno;
	}
	public  void setTiempoDeTurno(int tiempoDeTurno) {
		this.tiempoDeTurno = tiempoDeTurno;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public Calendar getFinDeTurno() {
		return finDeTurno;
	}
	public void setFinDeTurno(Calendar finDeTurno) {
		this.finDeTurno = finDeTurno;
	}
	public static int getTiempoDeTurnoPrefijado() {
		return tiempoDeTurnoPrefijado;
	}
	public static void setTiempoDeTurnoPrefijado(int tiempoDeTurnoPrefijado) {
		Turno.tiempoDeTurnoPrefijado = tiempoDeTurnoPrefijado;
	}
	@Override
	public int compareTo(Turno o) {
		// TODO Auto-generated method stub
		if(this.equals(o))
			return 0;
		if( this.getProfesional().compareTo(o.getProfesional()) == 0) {
			return this.getTurno().compareTo(o.getTurno());
		}else {
			return this.getProfesional().compareTo(o.getProfesional());
		}
	}
	
}
