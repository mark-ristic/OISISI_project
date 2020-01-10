package studentskasluzba.view;

import java.awt.Color;

import javax.swing.BoxLayout;

public class PredmetTab extends CustomPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6503754488827263187L;

	PredmetTab() {
		
		CustomPanel top_inset = new CustomPanel(1440,120, Color.CYAN);
		CustomPanel bot_inset = new CustomPanel(1440,120, Color.CYAN);
		CustomPanel centerPanel = new CustomPanel(1440, 630, Color.YELLOW);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(top_inset);
		this.add(centerPanel);
		this.add(bot_inset);
		
	}
	
}
