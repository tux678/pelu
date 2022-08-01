package utilidades;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public final class Util {
	static ByteArrayOutputStream baos = new ByteArrayOutputStream();
	static PrintStream ps = new PrintStream(baos);

	public static String toString(char[] charArray) {
		String result ="";
		for(char c: charArray) {
			result += c;
		}
		return result;
	}
	
	public static boolean esNumero(Object valor) {
		if (valor == null) {
			return false;
		}
		try {
			if (valor instanceof String) {
				Double.parseDouble((String) valor);
			}
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}

	public static int setInt(Object valor) {
		// TODO Auto-generated method stub
		int valorInt = 0;
		if(valor instanceof String)
			valorInt = Integer.parseInt(((String)valor).trim());
		else
			valorInt = (int)valor;
		return valorInt;
	}
	public static boolean esTextoVacio(Object texto) {
		// TODO Auto-generated method stub
		return !(((String) texto).length() > 0);
	}
	
}

