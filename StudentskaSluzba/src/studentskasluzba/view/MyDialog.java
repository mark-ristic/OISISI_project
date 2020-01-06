package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class MyDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3919027807831445056L;

	MyDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
	
		setSize(850,450);
		setLocationRelativeTo(parent);
		
	}
	
	MyDialog(JFrame parent, String title, boolean modal,int width,int height, String dialogType) {
		
		super(parent, title, modal);
		
		setSize(width,height);
		setLocationRelativeTo(parent);
		
		if(dialogType.equals("removeStudentFromPredmet")) {
			removeStudentFromPredmet();
		}

		if(dialogType.equals("addStudentToPredmet")) {
			addStudentToPredmet();
		}
		
		
	}
	
	// metoda za brisanje studenta sa predmeta - GUI 
	private void removeStudentFromPredmet() {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = new JLabel("Broj indeksa studenta: ");
		JTextField indeks = new JTextField(20);

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.PINK);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);

		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(indeks);

		JButton obrisi = new JButton(new ImageIcon("StudentskaSluzba\\images//obrisi-120.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\odustani-100.png"));

		obrisi.setFocusPainted(false);
		obrisi.setBorderPainted(false);
		obrisi.setOpaque(false);
		obrisi.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);

		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));

		panelbot.add(obrisi);
		panelbot.add(odustani);

		odustani.addActionListener(evt -> this.dispose());

	}

	// metoda za dodavanje studenta na predmet - GUI
	private void addStudentToPredmet() {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = new JLabel("Broj indeksa studenta: ");
		JTextField indeks = new JTextField(20);

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.PINK);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);

		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(indeks);

		JButton dodaj = new JButton(new ImageIcon("StudentskaSluzba\\images//round-100x35.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\odustani-100.png"));

		dodaj.setFocusPainted(false);
		dodaj.setBorderPainted(false);
		dodaj.setOpaque(false);
		dodaj.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);

		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));

		panelbot.add(dodaj);
		panelbot.add(odustani);

		odustani.addActionListener(evt -> this.dispose());

	}
	
	
}