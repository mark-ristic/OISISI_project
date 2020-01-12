package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

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
		CustomPanel customHelp = new CustomPanel(1440-3*55, 55, Color.WHITE);
	
		addStudent.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\student_final_add.png"));
		addStudent.setToolTipText("Dodaj studenta");
		addStudent.setBorderPainted(true);
		addStudent.setFocusPainted(false);
		addStudent.setBorder(new LineBorder(new Color(51, 153, 255, 90)));

		editStudent.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\student_final_edit.png"));
		editStudent.setToolTipText("Izmeni studenta");
		editStudent.setBorderPainted(true);
		editStudent.setFocusPainted(false);
		editStudent.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		removeStudent.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Avatars\\student_final_remove.png"));
		removeStudent.setToolTipText("Obrisi studenta");
		removeStudent.setBorderPainted(true);
		removeStudent.setFocusPainted(false);
		removeStudent.setBorder(new LineBorder(new Color(51, 153, 255, 90)));
		
		add(addStudent);
		add(editStudent);
		add(removeStudent);
		
		
		JTextField search = new JTextField(30);
		search.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		// template za pretragu studenta
		search.setText("indeks<>ime<>prezime<>");
		
		JButton magny = new JButton();
		
		magny.setIcon(new ImageIcon("StudentskaSluzba\\images\\search-35x35.png"));
		magny.setBorderPainted(false);
		magny.setFocusPainted(false);
		magny.setBackground(Color.WHITE);
		magny.setToolTipText("Pretrazi studenta");
		
		customHelp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		customHelp.add(search);
		customHelp.add(magny);
		
		add(customHelp);
		
		addStudent.addActionListener(evt -> {
			
			MyDialog dodaj = new MyDialog(parent, "Dodaj Studenta", true,650,675+55,"");
			
			StudentPanelAdd spa = new StudentPanelAdd(dodaj, parent,650,750+55);
			
			dodaj.add(BorderLayout.CENTER, spa);
			dodaj.setResizable(false);
			dodaj.setMinimumSize(new Dimension(650,750+55));
			dodaj.setMaximumSize(new Dimension(650,750+55));
			dodaj.setVisible(true); // mora na kraj
			
		});
		
		editStudent.addActionListener(evt -> {
			
			if (MyStudentTable.selected != -1) {
				MyDialog izmeni = new MyDialog(parent, "Izmeni Studenta", true,650,675+55,"");
			
				StudentPanelEdit spe = new StudentPanelEdit(izmeni, parent,650,750+55);
			
				izmeni.add(BorderLayout.CENTER, spe);
				izmeni.setResizable(false);
				izmeni.setMinimumSize(new Dimension(650,750));
				izmeni.setMaximumSize(new Dimension(650,750));
				izmeni.setVisible(true); // mora na kraj
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite studenta!");
			}
					
		});
		
		removeStudent.addActionListener(remove -> {
			
			if (MyStudentTable.selected != -1) {
				MyDialog obrisi = new MyDialog(parent, "Obrisi Studenta", true, 650,300, "");
			
				StudentPanelRemove spr = new StudentPanelRemove(obrisi, parent, 650,300);
			
				obrisi.add(BorderLayout.CENTER, spr);
				obrisi.setResizable(false);
				obrisi.setMinimumSize(new Dimension(650,300));
				obrisi.setMaximumSize(new Dimension(650,300));
				obrisi.setVisible(true);	
			}
			else {
				JOptionPane.showMessageDialog(parent, "Molim odaberite studenta!");	
			}	
			
		});
		
		
	}

}
