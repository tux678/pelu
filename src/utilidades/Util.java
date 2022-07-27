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
		try {
			if (valor instanceof String) {
				Double.parseDouble((String) valor);
			}
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
}
