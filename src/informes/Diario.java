package informes;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import com.toedter.calendar.JCalendar;

public class Diario extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public Diario() {
		setBounds(100, 100, 644, 519);
		
		JCalendar calendar = new JCalendar();
		getContentPane().add(calendar, BorderLayout.CENTER);

	}

}
