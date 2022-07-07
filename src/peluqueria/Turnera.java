package peluqueria;

import java.util.HashMap;
import java.util.TreeSet;

public class Turnera {
	
	private HashMap<String, TreeSet<Turno>> turnera = new HashMap<String, TreeSet<Turno>>();
	TreeSet<Turno> turnos; 
	
	public void setTurno(Turno turno) {
		
		if( turnera.containsKey(turno)) {
			
		}
		//turnera.put(turno.getProfesional().getUsuario(), turno);
	}
	
	public void getTurno() {
		
	}
}
