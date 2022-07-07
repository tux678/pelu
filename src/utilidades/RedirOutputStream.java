package utilidades;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class RedirOutputStream extends OutputStream {
	private StringBuilder text = new StringBuilder();
	private JTextArea jText;
	private PrintStream previo;
	
	public RedirOutputStream(PrintStream previo) {
		this.previo = previo;
		System.setOut( new PrintStream(this));
		
	}
	
	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
	     int[] bytes = {b};
	     write(bytes, 0, bytes.length);

	}
	private void write(int[] bytes, int offset, int length) {
		// TODO Auto-generated method stub
	     String s = new String(bytes, offset, length);
	     text.append(s);
	}
	
    public void flush() throws IOException {
    	jText.setText(text.toString());
    }
    
	
	public void setJText(JTextArea jText ) {
		this.jText = jText;
	}
	
	public PrintStream getPrevioPrintStream() {
		return this.previo;
	}
	
	public void close() {
		try {
			flush();
			System.setOut(previo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String[] args) throws IOException {
//        RedirOutputStream rd = new RedirOutputStream(System.out);
//		Rdir window = new Rdir();
//		window.frame.setVisible(true);
//		rd.setJText( window.jText);
//		
//		System.out.println("Hola");
//		
//		rd.flush();
//	}
 
}
