package peluqueria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class testAdministrador {

	@Test
	void testCrearCuentaDelAdministradorGeneral() throws Exception {
		AdministradorGeneral admin = AdministradorGeneral.getInstance("Gustavo", "C", "gustavoC", "2205");
		Assertions.assertEquals("gustavoC", admin.getUsuario());
	}
	
	@Test
	void testCrearCuentaDeOtroAdministrador() throws Exception {
		AdministradorGeneral admin = AdministradorGeneral.getInstance("Gustavo", "C", "gustavoC", "2205");
		Assertions.assertEquals("Noemi", admin.crearCuentaAdministradorAdjunto("Noemi", "Sejas", "noemiS", "1210").getNombre() );
	}

	@Test
	void testCrearCuentaDeOtroAdministradorConEncriptacion() throws Exception {
		AdministradorGeneral admin = AdministradorGeneral.getInstance("Gustavo", "C", "gustavoC", Encriptacion.encriptar("Matias-Agustina-Mateo"));
		Assertions.assertEquals("2205", admin.getPassword() );
	}
}
