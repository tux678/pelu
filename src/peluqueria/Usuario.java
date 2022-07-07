package peluqueria;

import java.io.Serializable;
import java.util.Objects;

public abstract class Usuario implements Serializable, Comparable<Usuario>{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String usuario;
	private String password;

	@Override
	public int hashCode() {
		return Objects.hash(usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(usuario, other.usuario);
	}
	/**
	 * 
	 */
	
	public Usuario(String nombre, String apellido, String usuario, String password) throws Exception {
		this.setApellido(apellido);
		this.setNombre(nombre);
		this.setUsuario(usuario);
		this.setPassword(password);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() throws Exception {
		return  Encriptacion.desencriptar(password);
	}
	public void setPassword(String password) throws Exception {
		this.password = this.toEncript(password);
	}

	@Override
	public String toString() {
		return this.getClass() + ", " + nombre + ", " + apellido + ", " + usuario + ", "
				+ password + "\n";
	}
	public String toEncript(String data) throws Exception {
		return Encriptacion.encriptar( data);
		
	}
	@Override
	public int compareTo(Usuario o) {
		// TODO Auto-generated method stub
		return this.getUsuario().compareTo(o.getUsuario());
	}

}
