package peluqueria;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Administracion {
	private Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
	
	public void setUsuario(Usuario user) {
		usuarios.put(user.getUsuario(), user);
	}
	
	public Usuario getUsuario(String usuario) {
		return usuarios.get(usuario);
	}
	
	public void cargarUsuarios() {
		resetUsuarios();
		if(existeArchivoDeUsuarios()) {
			FileInputStream fu = null;
			ObjectInputStream ou = null;
			Usuario registro;
			
			try {
				fu = new FileInputStream("recursos/usuarios.key");
				ou = new ObjectInputStream(fu);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while((registro = (Usuario) ou.readObject())!= null) {
					setUsuario(registro);
				}
			} catch (EOFException f) {
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void resetUsuarios() {
		// TODO Auto-generated method stub
		usuarios.clear();
	}

	private boolean existeArchivoDeUsuarios() {
		// TODO Auto-generated method stub
		File au = new File("recursos/usuarios.key");
		return au.exists();
	}

	public void guardarUsuarios() throws Exception {
		FileOutputStream fu = null;
		ObjectOutputStream ou = null;
		Usuario userProfile = new Administrador("*","*", "*", "*");
		try {
			fu = new FileOutputStream("recursos/usuarios.key");
			ou = new ObjectOutputStream(fu);
			ou.writeObject(userProfile);
			
			for(Map.Entry<String, Usuario> user : usuarios.entrySet()) {
				ou.writeObject(user.getValue());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fu.close();
			}catch (IOException f) {
				f.printStackTrace();
			}
		}
		
	}
	
}
