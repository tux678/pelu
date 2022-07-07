package peluqueria;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestArchivoDeUsuarios {

	@Test
	void TestGuardaYRecuperaUsuarioGustavo() throws Exception {
		Usuario usr = new Administrador("Gustavo", "C", "tux","2205");
		Administracion adm = new Administracion();
		adm.setUsuario(usr);
		adm.guardarUsuarios();
		adm.cargarUsuarios();
		Assertions.assertEquals("C", adm.getUsuario("tux").getApellido());
	}
	
	@Test
	void TestGuardaYRecuperaUsuarioNoemi() throws Exception {
		Usuario usr = new Administrador("Gustavo", "C", "tux","2205");
		Usuario usr2 = new Administrador("Noemi", "S", "noe","1210");
		Administracion adm = new Administracion();
		adm.setUsuario(usr);
		adm.setUsuario(usr2);
		adm.guardarUsuarios();
		adm.cargarUsuarios();
		Assertions.assertEquals("S", adm.getUsuario("noe").getApellido());
	}

}
