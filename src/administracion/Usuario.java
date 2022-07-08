package administracion;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;

import conexion.Conexion;

public abstract class Usuario {
	private String usuario;
	private String clave;
	private Rol categoria;
	private String nombre;
	private String apellido;
	private String direccion;
	private String localidad;
	private String telefono;
	private String email;
	private Calendar fechaDeIngreso;
	
	public Usuario(String usuario, String clave, String nombre, String apellido, Rol categoria) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido = apellido;
		this.categoria = categoria;
	}
	
	public Usuario(String usuario, String clave, Rol categoria, String nombre, String apellido, String direccion,
			String localidad, String telefono, String email) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.categoria = categoria;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.localidad = localidad;
		this.telefono = telefono;
		this.email = email;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String clave) {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String log) {
		// TODO Auto-generated constructor stub
		this.usuario = log.split(",")[0].trim();
		this.clave = log.split(",")[1].trim();
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Rol getCategoria() {
		return categoria;
	}
	public void setCategoria(Rol categoria) {
		this.categoria = categoria;
	}
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

	public void setCategoria(String rol) {
		// TODO Auto-generated method stub
		this.categoria = Rol.valueOf(rol);
		
	}
	


	public abstract void setMenu(Peluqueria w);	
}
