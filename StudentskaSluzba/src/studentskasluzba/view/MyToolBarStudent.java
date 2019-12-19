package studentskasluzba.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class MyToolBarStudent extends JToolBar {

	public static final JButton addStudent = new JButton("add");
	public static final JButton editStudent = new JButton("edit");
	public static final JButton removeStudent = new JButton("del");
	
	public MyToolBarStudent(final JFrame parent) {
		
		super(JToolBar.HORIZONTAL);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setRollover(false);
		setFloatable(false);
		
		// Pomocni panel za popunjavanje mesta pored buttonsa
		CustomPanel customHelp = new CustomPanel(1440-3*55, 55, Color.CYAN);
		
		addStudent.setPreferredSize(new Dimension(55,55));
		addStudent.setToolTipText("Dodaj studenta");
		
		editStudent.setPreferredSize(new Dimension(55, 55));
		editStudent.setToolTipText("Izmeni studenta");
		
		removeStudent.setPreferredSize(new Dimension(55,55));
		removeStudent.setToolTipText("Obrisi studenta");
		
		CustomPanel buttonPanel = new CustomPanel(3*55 , 55 , Color.YELLOW); 
	
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(addStudent);
		buttonPanel.add(editStudent);
		buttonPanel.add(removeStudent);
		
		add(buttonPanel);
		add(customHelp);
		
		//TODO: Dodati slike za buttone + search bar deo uraditi
		
	}

}
