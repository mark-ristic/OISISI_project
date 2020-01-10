package studentskasluzba.view;

import java.awt.Color;

import javax.swing.BoxLayout;

public class ProfesorTab extends CustomPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4803294274046341734L;
	
	ProfesorTab() {
		
		CustomPanel top_inset = new CustomPanel(1440,120, Color.CYAN);
		CustomPanel bot_inset = new CustomPanel(1440,120, Color.CYAN);
		CustomPanel centerPanel = new CustomPanel(1440, 630, Color.green);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(top_inset);
		this.add(centerPanel);
		this.add(bot_inset);
		
	}
	
	// TODO: uraditi proftab
}
