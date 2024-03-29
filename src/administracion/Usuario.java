package administracion;

import java.util.Calendar;
import java.util.Objects;


public abstract class Usuario {
	private int id;
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

	public Usuario(String usuario2, String nombre2, String apellido2) {
		// TODO Auto-generated constructor stub
		usuario = usuario2;
		nombre = nombre2;
		apellido = apellido2;
	}

	public Usuario(int id, String usuario2, String nombre2, String apellido2) {
		// TODO Auto-generated constructor stub
		this(usuario2, nombre2, apellido2);
		this.setId(id);
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

	public abstract String getSelectString(String usuario);

	protected abstract String getRolStringSql();

	public abstract String getDeleteSql(String usuario);

	@Override
	public String toString() {
		return usuario + ", " + ( categoria != null ?  categoria  + ", ": "") + nombre + ", " + apellido + ", " + (direccion != null ? direccion  + ", ": "" )
				+ (localidad != null ? localidad  + ", " : "" ) + (telefono != null ? telefono + ", " : "" ) + (email != null ? email + ", " : "" )
				+ (fechaDeIngreso != null ? fechaDeIngreso : "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
