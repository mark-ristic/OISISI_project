package studentskasluzba.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;

public class StudentTab extends CustomPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8947984116889772597L;
	
	StudentTab(){
	
		CustomPanel top_inset = new CustomPanel(1440,120, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(1440,120, Color.WHITE);
		CustomPanel centerPanel = new CustomPanel(1440, 630, Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(top_inset);
		this.add(centerPanel);
		this.add(bot_inset);
		
		centerPanel.setLayout(new GridLayout(1,1));
		MyStudentTable myStudentTable = new MyStudentTable();
		myStudentTable.getTableHeader().setReorderingAllowed(false);
		myStudentTable.getTableHeader().setResizingAllowed(false);
		centerPanel.add(myStudentTable.getJScrollPane());
		
	}

}
