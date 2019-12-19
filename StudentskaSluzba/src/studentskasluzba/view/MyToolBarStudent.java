package studentskasluzba.view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class MyToolbarStudent extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2956075730143680074L;
	
	public static final JButton addStudent = new JButton();
	public static final JButton editStudent = new JButton();
	public static final JButton removeStudent = new JButton();
	
	public MyToolbarStudent(final JFrame parent) {
		
		super(JToolBar.HORIZONTAL);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setRollover(false);
		setFloatable(false);
		
		// Pomocni panel za popunjavanje mesta pored buttonsa
		CustomPanel customHelp = new CustomPanel(1440-3*55, 55, Color.CYAN);
	
		addStudent.setIcon(new ImageIcon("images\\student-add-55x55.png"));
		addStudent.setToolTipText("Dodaj studenta");
		addStudent.setBorderPainted(true);
		addStudent.setFocusPainted(false);

		editStudent.setIcon(new ImageIcon("images\\student-edit-55x55.png"));
		editStudent.setToolTipText("Izmeni studenta");
		editStudent.setBorderPainted(true);
		editStudent.setFocusPainted(false);
		
		removeStudent.setIcon(new ImageIcon("images\\student-delete-55x55.png"));
		removeStudent.setToolTipText("Obrisi studenta");
		removeStudent.setBorderPainted(true);
		removeStudent.setFocusPainted(false);
		
		add(addStudent);
		add(editStudent);
		add(removeStudent);
		add(customHelp);
		
		// TODO: Slike za JButtone mozemo naknadno zameniti po potrebi
		// TODO: zameniti ime klase na MyToolbarStudent ( "B" -> "b" ) 
	}

}