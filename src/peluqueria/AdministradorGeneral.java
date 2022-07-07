package peluqueria;

public class AdministradorGeneral extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = -689016307619641016L;
	private static AdministradorGeneral instancia;
	
	private AdministradorGeneral(String nombre, String apellido, String usuario, String password) throws Exception {
		super(nombre, apellido, usuario, password);
		// TODO Auto-generated constructor stub
	}
	
	public static AdministradorGeneral getInstance(String nombre, String apellido, String usuario, String password) throws Exception {
		if (instancia == null) {
			instancia = new AdministradorGeneral(nombre, apellido, usuario, password);
		}
		return instancia;
	}

	public Administrador crearCuentaAdministradorAdjunto(String nombre, String apellido, String usuario, String password) throws Exception {
		// TODO Auto-generated method stub
		return new Administrador(nombre, apellido, usuario, password);
	}
	
	public Profesional crearCuentaDelProfesional(String nombre, String apellido, String usuario, String password) throws Exception {
		return new Profesional(nombre, apellido, usuario, password);
		
	}
	
}
