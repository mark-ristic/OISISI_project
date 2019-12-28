package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
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
		//add(customHelp);
		
		// TODO: Slike za JButtone mozemo naknadno zameniti po potrebi
		
		JTextField search = new JTextField (15);
		JButton magny = new JButton();
		
		magny.setIcon(new ImageIcon("images\\search-35x35.png"));
		magny.setBorderPainted(false);
		magny.setBackground(Color.CYAN);
		magny.setToolTipText("Pretrazi");
		
		customHelp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		customHelp.add(search);
		customHelp.add(magny);
		
		add(customHelp);
		
		addStudent.addActionListener(evt -> {
			
			MyDialog dodaj = new MyDialog(parent, "Dodaj Studenta", true,650,675,"");
			
			StudentPanelAdd spa = new StudentPanelAdd(dodaj, parent,650,750);
			
			dodaj.add(BorderLayout.CENTER, spa);
			dodaj.setResizable(false);
			dodaj.setMinimumSize(new Dimension(650,750));
			dodaj.setMaximumSize(new Dimension(650,750));
			dodaj.setVisible(true); // mora na kraj
			
		});
		
		
	}

}