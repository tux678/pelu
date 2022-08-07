package informes;


import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;



public class VistaPrevia extends JInternalFrame {



	/**
	 * Create the frame.
	 */
	public VistaPrevia() {
		
		setBounds(100, 100, 630, 460);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		JWebBrowser browser = new JWebBrowser();
		panel.add(browser);

	}

}
