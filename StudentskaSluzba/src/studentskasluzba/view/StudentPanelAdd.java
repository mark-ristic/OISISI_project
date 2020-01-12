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

public class StudentPanelAdd extends JPanel{
	
	// ds, quit, imetxt,prezimetxt,datetxt, adresatxt,teltxt,indekstxt,budzet,samofin,trenGodCombo,test
	
	private static String[] godine = { "I (prva)", "II (druga)","III (treca)","IV (cetvrta)" };
	public static final JButton ds = new JButton ();
	public static final JButton quit = new JButton ();
	public static final JTextField imetxt = new JTextField(50);
	public static final JTextField prezimetxt = new JTextField(50);
	public static final JTextField datumRodjtxt = new JTextField(50); 
	public static final JTextField adresatxt = new JTextField(50);
	public static final JTextField teltxt = new JTextField(50);
	public static final JTextField emailtxt = new JTextField(50); // new
	public static final JTextField indekstxt = new JTextField(50);
	public static final JTextField datumUpisatxt = new JTextField(50); // new 
	public static final JTextField prosektxt = new JTextField(50); // new new 
	public static final JComboBox<String> trenGodCombo = new JComboBox<String>(godine);
	public static final JRadioButton budzet = new JRadioButton("Budzet");
	public static final JRadioButton samofin = new JRadioButton("Samofinansiranje");
	
	private static final long serialVersionUID = 1034413266284921173L;

		StudentPanelAdd(MyDialog md, final JFrame parent, int width, int height) {
		
		setPreferredSize(new Dimension(width,height));
		Vector<CustomPanel> vektor = new Vector<CustomPanel>();
		// ukupna visina 650 x (675+95)          // 420 + 245 665
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
		CustomPanel email = new CustomPanel(500,500-14, Color.CYAN);
		CustomPanel datumUpisa = new CustomPanel(500,500-14, Color.CYAN);
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
		
		
		JLabel imelab =        new JLabel("    Ime:*    ");
		JLabel prezimelab =    new JLabel("    Prezime:*    ");
		JLabel datRodjlab =    new JLabel("    Datum rodjenja:*    ");
		JLabel adresalab =     new JLabel("    Adresa stanovanja:    ");
		JLabel brtellab =      new JLabel("    Broj telefona:*    ");
		JLabel indekslab =     new JLabel("    Broj indeksa:*    ");
		JLabel trenGodlab =    new JLabel("    Trenutna godina studija:*   ");
		JLabel emaillab =      new JLabel("    E-mail adresa:*   ");
		JLabel datumUpisalab = new JLabel("    Datum upisa:*   ");
		JLabel proseklab =     new JLabel("    Prosek:*   ");
		
		
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
		
		
		imetxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		prezimetxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		datumRodjtxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		adresatxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		teltxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		indekstxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		emailtxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		datumUpisatxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		prosektxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		datumRodjtxt.setToolTipText("format datuma : dd.mm.yyyy.");
		datumUpisatxt.setToolTipText("format datuma : dd.mm.yyyy.");
		
		ime.add(imelab); 
		ime.add(imetxt);
		prezime.add(prezimelab); 
		prezime.add(prezimetxt);
		datRodj.add(datRodjlab); 
		datRodj.add(datumRodjtxt);
		adresa.add(adresalab); 
		adresa.add(adresatxt);
		brtel.add(brtellab); 
		brtel.add(teltxt);
		indeks.add(indekslab); 
		indeks.add(indekstxt);
		email.add(emaillab);
		email.add(emailtxt);
		datumUpisa.add(datumUpisalab);
		datumUpisa.add(datumUpisatxt);
		prosek.add(proseklab);
		prosek.add(prosektxt);
		
		
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
		botU_row1.add(trenGodCombo);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(budzet);  bg.add(samofin);
		budzet.setSelected(true);
		
		budzet.setOpaque(false);
		samofin.setOpaque(false);
		
		botU_row2.setLayout(new BoxLayout(botU_row2, BoxLayout.X_AXIS));
		botU_row2.add(new JLabel("            ")); // labela sluzi kao inset sa leve strane
		botU_row2.add(budzet);
		botU_row2.add(new Container());
		
		botU_row3.setLayout(new BoxLayout(botU_row3, BoxLayout.X_AXIS));
		botU_row3.add(new JLabel("            "));
		botU_row3.add(samofin);
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
		
		
		// dodat novi design za buttonse
		quit.addMouseListener(new MouseAdapter() {
					 
			@Override
			public void mouseClicked(MouseEvent e) {
			// the user clicks on the label
		    	quit.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    	md.dispose();
		    }
			 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	quit.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	quit.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		    }
		});
		
		
		ds.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	ds.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	ds.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	ds.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		});
		
		ds.setPreferredSize(new Dimension(125,35));
		ds.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		ds.setFocusPainted(false);
		ds.setBorderPainted(false);
		ds.setContentAreaFilled(false);
		ds.setOpaque(false);
		
		quit.setPreferredSize(new Dimension(125,40));
		quit.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		quit.setFocusPainted(false);
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setOpaque(false);
		
		gbc.insets = new Insets (5,25,5,15);
		gbc.gridx = 4; 
		gbc.gridy = 3; 
		
		botL.add(ds, gbc);
		
		gbc.gridx = 9;
		
		botL.add(quit, gbc);
		
		//recolor comment if you want original colours
		
		for (CustomPanel i : vektor)
			i.setBackground(Color.WHITE);
		// let's add some borders hehe
		top_panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		bot_panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		
	}
}
