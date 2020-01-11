package studentskasluzba.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		if(dialogType.equals("addProfesorToPredmet")) {
			addProfesorToPredmet();
		}

		if(dialogType.equals("removeProfesorFromPredmet")) {
			removeProfesorFromPredmet();
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

		JButton obrisi = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

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
		
		obrisi.addActionListener(evt -> this.dispose());
		 
		obrisi.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	obrisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		  //  	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	obrisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	obrisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		    }
		});
		
		odustani.addActionListener(evt -> this.dispose());

		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    //	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});

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

		JButton dodaj = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

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
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    //	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});
		
		dodaj.addActionListener(evt -> this.dispose());
		
		dodaj.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		  //  	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		});

	}
	
	private void removeProfesorFromPredmet() {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,190, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = new JLabel("Da li ste sigurni da zelite da obrisete profesora sa predmeta?");

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(panelbot);

		paneltop.setLayout(new GridBagLayout());
		paneltop.add(question);

		JButton brisi = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

		brisi.setFocusPainted(false);
		brisi.setBorderPainted(false);
		brisi.setOpaque(false);
		brisi.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);

		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));

		panelbot.add(brisi);
		panelbot.add(odustani);

		odustani.addActionListener(evt -> this.dispose());
		brisi.addActionListener(evt -> this.dispose());
		
		brisi.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	brisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	brisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	brisi.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		    }
		});
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});

	}

	private void addProfesorToPredmet() {

		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,390, Color.WHITE);

		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));

		this.add(BorderLayout.NORTH, top_inset);
		this.add(BorderLayout.CENTER, panel);
		this.add(BorderLayout.SOUTH, bot_inset);

		JLabel question = new JLabel("Broj licne karte profesora: ");
		JTextField brojLK = new JTextField(20);

		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		// mesto za tabelu profesora
		CustomPanel tablepanel = new CustomPanel(650, 200, Color.YELLOW);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(tablepanel);
		panel.add(panelbot);

		paneltop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		paneltop.add(question);
		paneltop.add(brojLK);

		JButton dodaj = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));

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
		dodaj.addActionListener(evt -> this.dispose());
		 
		dodaj.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	dodaj.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		});
		
		odustani.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});

}

	
}