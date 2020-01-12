package studentskasluzba.view;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class StudentPanelEdit extends JPanel{

	private static String[] godine = { "I (prva)", "II (druga)","III (treca)","IV (cetvrta)" };
	public static final JButton es = new JButton ();
	public static final JButton quit1 = new JButton ();
	public static final JTextField imetxt1 = new JTextField(50);
	public static final JTextField prezimetxt1 = new JTextField(50);
	public static final JTextField datRodjtxt1 = new JTextField(50);
	public static final JTextField adresatxt1 = new JTextField(50);
	public static final JTextField teltxt1 = new JTextField(50);
	public static final JTextField emailtxt1 = new JTextField(50);
	public static final JTextField indekstxt1 = new JTextField(50);
	public static final JTextField datumUpisatxt1 = new JTextField(50);
	public static final JTextField prosektxt1 = new JTextField(50); //new new 
	public static final JRadioButton budzet1 = new JRadioButton("Budzet");
	public static final JRadioButton samofin1 = new JRadioButton("Samofinansiranje");
	public static final JComboBox<String> trenGodCombo1 = new JComboBox<String>(godine);
	
	private static final long serialVersionUID = 1034413266284921173L;

		StudentPanelEdit(MyDialog md, final JFrame parent, int width, int height) {
		
		setPreferredSize(new Dimension(width,height));
		Vector<CustomPanel> vektor = new Vector<CustomPanel>();
		
		CustomPanel top_inset = new CustomPanel(650,45, Color.PINK);
		CustomPanel left_inset = new CustomPanel(75, 675+95+55, new Color(255,102,255));
		CustomPanel right_inset = new CustomPanel(75,675+95+55, new Color(255,102,255));
		CustomPanel bot_inset = new CustomPanel(650,30, Color.PINK);
		CustomPanel top_panel = new CustomPanel(500, 400-75 + 95+55, new Color(255,0,255)); // gornji deo midpanela
		CustomPanel bot_panel = new CustomPanel(500, 245+10, new Color(170,0,170)); // donji deo midpanela
		CustomPanel full_panelLR = new CustomPanel(650, 400-75 + 95+55, Color.LIGHT_GRAY); // midpanel sa insetima
		CustomPanel mid_panelTB = new CustomPanel(500,675+95, Color.white); // top + bot
		Collections.addAll(vektor, top_inset,bot_inset,left_inset,right_inset,top_panel
						 , bot_panel, full_panelLR, mid_panelTB);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(top_inset);
		add(full_panelLR); // top_panelLR = top_panel + left_inset + right_inset
		add(bot_inset);
		
		full_panelLR.setLayout(new BoxLayout(full_panelLR, BoxLayout.X_AXIS));
		full_panelLR.add(left_inset);
		full_panelLR.add(mid_panelTB);
		full_panelLR.add(right_inset);
		mid_panelTB.setLayout(new BoxLayout(mid_panelTB, BoxLayout.Y_AXIS));
		mid_panelTB.add(top_panel); 
		mid_panelTB.add(bot_panel); 
		
		CustomPanel botU = new CustomPanel (500,115, new Color(153,255,153));// gornji deo donjeg panela
		CustomPanel botL = new CustomPanel (500, 120, new Color(102,255,102));// donji deo donjeg panela
		CustomPanel botU_topinset = new CustomPanel(500,15, Color.PINK);
		
		bot_panel.setLayout(new BoxLayout(bot_panel, BoxLayout.Y_AXIS));
		bot_panel.add(botU_topinset);
		bot_panel.add(botU);
		bot_panel.add(botL);
		
		vektor.add(botU);
		vektor.add(botL);
		vektor.add(botU_topinset);
		
		top_panel.setLayout(new GridLayout(9,1,0,15));
		
		CustomPanel ime = new CustomPanel(500,54-14, Color.CYAN);			
		CustomPanel prezime = new CustomPanel(500,54-14, Color.CYAN); 	
		CustomPanel datRodj = new CustomPanel(500,54-14, Color.CYAN);		
		CustomPanel adresa = new CustomPanel(500,54-14, Color.CYAN);		
		CustomPanel brtel = new CustomPanel(500,54-14, Color.CYAN);		
		CustomPanel indeks = new CustomPanel(500,54-14, Color.CYAN);	
		CustomPanel email = new CustomPanel(500,54-14, Color.CYAN);
		CustomPanel datumUpisa = new CustomPanel(500,54-14, Color.CYAN);
		CustomPanel prosek = new CustomPanel(500,500-14,Color.CYAN);
		
		
		vektor.add(ime);
		vektor.add(prezime);
		vektor.add(datRodj);
		vektor.add(adresa);
		vektor.add(brtel);
		vektor.add(indeks);
		vektor.add(email);
		vektor.add(datumUpisa);
		vektor.add(prosek);
		
		ime.setLayout(new BoxLayout(ime, BoxLayout.X_AXIS));
		prezime.setLayout(new BoxLayout(prezime, BoxLayout.X_AXIS));
		datRodj.setLayout(new BoxLayout(datRodj, BoxLayout.X_AXIS));
		adresa.setLayout(new BoxLayout(adresa, BoxLayout.X_AXIS));
		brtel.setLayout(new BoxLayout(brtel, BoxLayout.X_AXIS));
		indeks.setLayout(new BoxLayout(indeks, BoxLayout.X_AXIS));
		email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
		datumUpisa.setLayout(new BoxLayout(datumUpisa, BoxLayout.X_AXIS));
		prosek.setLayout(new BoxLayout(prosek, BoxLayout.X_AXIS));
		
		
		JLabel imelab =      new JLabel("    Ime:*    ");
		JLabel prezimelab =  new JLabel("    Prezime:*    ");
		JLabel datRodjlab =  new JLabel("    Datum rodjenja:*    ");
		JLabel adresalab =   new JLabel("    Adresa stanovanja:    ");
		JLabel brtellab =    new JLabel("    Broj telefona:*    ");
		JLabel indekslab =   new JLabel("    Broj indeksa:*    ");
		JLabel trenGodlab =  new JLabel("    Trenutna godina studija:*   ");
		JLabel emaillab =      new JLabel("    E-mail adresa:*   ");
		JLabel datumUpisalab = new JLabel("    Datum upisa:*   ");
		JLabel proseklab =     new JLabel("    Prosek*:   ");
		
		
		imelab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		prezimelab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		datRodjlab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		adresalab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		brtellab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		indekslab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		trenGodlab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		emaillab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		datumUpisalab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		proseklab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		
		
		imetxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		prezimetxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		datRodjtxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		adresatxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		teltxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		indekstxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		emailtxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		datumUpisatxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		prosektxt1.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		
		
		ime.add(imelab); 
		ime.add(imetxt1);
		prezime.add(prezimelab); 
		prezime.add(prezimetxt1);
		datRodj.add(datRodjlab); 
		datRodj.add(datRodjtxt1);
		adresa.add(adresalab); 
		adresa.add(adresatxt1);
		brtel.add(brtellab); 
		brtel.add(teltxt1);
		indeks.add(indekslab); 
		indeks.add(indekstxt1);
		email.add(emaillab);
		email.add(emailtxt1);
		datumUpisa.add(datumUpisalab);
		datumUpisa.add(datumUpisatxt1);
		prosek.add(proseklab);
		prosek.add(prosektxt1);
		
		
		top_panel.add(ime);
		top_panel.add(prezime);
		top_panel.add(datRodj);
		top_panel.add(adresa);
		top_panel.add(brtel);
		top_panel.add(indeks);
		top_panel.add(email);
		top_panel.add(datumUpisa);
		top_panel.add(prosek);
		
		// botU = botUpper code 
		botU.setLayout(new GridLayout(3,1,0, 15));
		CustomPanel botU_row1 = new CustomPanel(500, 54, Color.LIGHT_GRAY);
		CustomPanel botU_row2 = new CustomPanel(500, 54, Color.white);
		CustomPanel botU_row3 = new CustomPanel(500, 54, Color.LIGHT_GRAY);
		botU_row1.setLayout(new BoxLayout(botU_row1, BoxLayout.X_AXIS));
		botU_row1.add(trenGodlab);
		botU_row1.add(trenGodCombo1);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(budzet1);  bg.add(samofin1);
		budzet1.setSelected(true);
		
		budzet1.setOpaque(false);
		samofin1.setOpaque(false); 
		
		botU_row2.setLayout(new BoxLayout(botU_row2, BoxLayout.X_AXIS));
		botU_row2.add(new JLabel("            ")); // labela sluzi kao inset sa leve strane
		botU_row2.add(budzet1);
		botU_row2.add(new Container());
		
		botU_row3.setLayout(new BoxLayout(botU_row3, BoxLayout.X_AXIS));
		botU_row3.add(new JLabel("            "));
		botU_row3.add(samofin1);
		botU_row3.add(new Container());
		
		botU.add(botU_row1); 
		botU.add(botU_row2);
		botU.add(botU_row3);
		
		vektor.add(botU_row1);
		vektor.add(botU_row2);
		vektor.add(botU_row3);
		
		//botL = bottomLower code	
		botL.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		quit1.addMouseListener(new MouseAdapter() {
			 
			@Override
			public void mouseClicked(MouseEvent e) {
			// the user clicks on the label
		    	quit1.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    	md.dispose();
		    }
			 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	quit1.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	quit1.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});
		
		es.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	es.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni.png"));
		    	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	es.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	es.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni.png"));
		    }
		});
		
			
		es.setPreferredSize(new Dimension(125,35));
		es.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni.png"));
		es.setFocusPainted(false);
		es.setBorderPainted(false);
		es.setContentAreaFilled(false);
		es.setOpaque(false);
		
		quit1.setPreferredSize(new Dimension(125,40));
		quit1.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		quit1.setFocusPainted(false);
		quit1.setBorderPainted(false);
		quit1.setContentAreaFilled(false);
		quit1.setOpaque(false);
		
		gbc.insets = new Insets (5,25,5,15);
		gbc.gridx = 4; 
		gbc.gridy = 3; 
		
		botL.add(es, gbc);
		
		gbc.gridx = 9;
		
		botL.add(quit1, gbc);
		
		//recolor comment if you want original colours
		
		for (CustomPanel i : vektor)
			i.setBackground(Color.WHITE);
		// let's add some borders 
		top_panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		bot_panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		
	}
}
