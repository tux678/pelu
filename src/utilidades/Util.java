package utilidades;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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

	public static double setDouble(Object valor) {
		// TODO Auto-generated method stub
		double valorDouble = 0;
		if (valor == null) {
			
		}else if (valor instanceof String)
			valorDouble = Double.parseDouble(((String) valor).trim());
		else
			valorDouble = (double) valor;
		return valorDouble;
	}
	public static boolean esFechaValida(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}	

