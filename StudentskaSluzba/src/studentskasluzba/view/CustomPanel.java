package studentskasluzba.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CustomPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8955942226434317041L;

	public CustomPanel() {
		
	}
	
	// CustomPanel, da ne bi morao uvek pisati dimenzije
	public CustomPanel(int width, int height, Color color) {
		super();
		setPreferredSize(new Dimension(width,height)); 
		setBackground(color);
		setVisible(true);	
	}
	
	// Postavljamo vidljivost na true/false 
	public CustomPanel(int width,int height, Color color, boolean flag) {
		super();
		setPreferredSize(new Dimension(width,height)); 
		setBackground(color);
		setVisible(flag);	
	}

}
