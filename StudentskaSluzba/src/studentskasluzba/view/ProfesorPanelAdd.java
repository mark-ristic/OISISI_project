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

public class ProfesorPanelAdd extends JPanel {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7067957782052842203L;
	public static JTextField imetxt = new JTextField(50);
	public static JTextField prezimetxt = new JTextField(50);
	public static JTextField datumRodjtxt = new JTextField(50);
	public static JTextField adresatxt = new JTextField(50);
	public static JTextField teltxt = new JTextField(50);
	public static JTextField emailtxt = new JTextField(50); //
	public static JTextField adresaKanctxt = new JTextField(50);//
	public static JTextField brojLKtxt = new JTextField(50);
	public static JTextField titulatxt = new JTextField(50);//
	//public static JTextField zvanjetxt = new JTextField(50);//
	public static JButton dp = new JButton ();
	public static JButton quit = new JButton ();
	public static JRadioButton stalni = new JRadioButton("Redovni");
	public static JRadioButton vanredni = new JRadioButton("Vanredni");
	public static JRadioButton docent = new JRadioButton("Docent");
	public static JComboBox<String>combo;
	
	ProfesorPanelAdd(MyDialog md, final JFrame parent, int width, int height) {
	
		setPreferredSize(new Dimension(width,height));
		Vector<CustomPanel> vektor = new Vector<CustomPanel>();
		
		CustomPanel top_inset = new CustomPanel(650,45, Color.PINK);
		CustomPanel left_inset = new CustomPanel(75, 645-75, new Color(255,102,255));
		CustomPanel right_inset = new CustomPanel(75,645-75, new Color(255,102,255));
		CustomPanel bot_inset = new CustomPanel(650,30, Color.PINK);
		CustomPanel top_panel = new CustomPanel(500, 400-75+205-55, new Color(255,0,255)); // gornji deo midpanela
		CustomPanel bot_panel = new CustomPanel(500, 245, new Color(170,0,170)); // donji deo midpanela
		CustomPanel full_panel = new CustomPanel(650, 645-75+205-55, Color.LIGHT_GRAY); // midpanel sa insetima
		CustomPanel mid_panelTB = new CustomPanel(500,645-75, Color.white); // top + bot
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		vektor.add(top_inset);
		vektor.add(bot_inset);
		vektor.add(left_inset);
		vektor.add(right_inset);
		vektor.add(top_panel);
		vektor.add(bot_panel);
		vektor.add(full_panel);
		vektor.add(mid_panelTB);
		
		add(top_inset);
		add(full_panel); // top_panelLR = top_panel + left_inset + right_inset
		add(bot_inset);
		full_panel.setLayout(new BoxLayout(full_panel, BoxLayout.X_AXIS));
		full_panel.add(left_inset);
		full_panel.add(mid_panelTB);
		full_panel.add(right_inset);
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
		vektor.add(ime);
		CustomPanel prezime = new CustomPanel(500,54-14, Color.CYAN); 	
		vektor.add(prezime);
		CustomPanel datRodj = new CustomPanel(500,54-14, Color.CYAN);	
		vektor.add(datRodj);
		CustomPanel adresa = new CustomPanel(500,54-14, Color.CYAN);		
		vektor.add(adresa);
		CustomPanel brtel = new CustomPanel(500,54-14, Color.CYAN);		
		vektor.add(brtel);
		CustomPanel brojLK = new CustomPanel(500,54-14, Color.CYAN);	
		vektor.add(brojLK);
		CustomPanel email = new CustomPanel(500, 54-14, Color.CYAN);
		vektor.add(email);
		CustomPanel adresaKanc = new CustomPanel(500,54-14, Color.CYAN);
		vektor.add(adresaKanc);
		CustomPanel titula = new CustomPanel(500,54-14, Color.CYAN);
		vektor.add(titula);
		//CustomPanel zvanje = new CustomPanel(500,54-14, Color.CYAN);;
		//vektor.add(zvanje);
		
		
		ime.setLayout(new BoxLayout(ime, BoxLayout.X_AXIS));
		prezime.setLayout(new BoxLayout(prezime, BoxLayout.X_AXIS));
		datRodj.setLayout(new BoxLayout(datRodj, BoxLayout.X_AXIS));
		adresa.setLayout(new BoxLayout(adresa, BoxLayout.X_AXIS));
		brtel.setLayout(new BoxLayout(brtel, BoxLayout.X_AXIS));
		brojLK.setLayout(new BoxLayout(brojLK, BoxLayout.X_AXIS));
		email.setLayout(new BoxLayout(email, BoxLayout.X_AXIS));
		adresaKanc.setLayout(new BoxLayout(adresaKanc, BoxLayout.X_AXIS));
		titula.setLayout(new BoxLayout(titula, BoxLayout.X_AXIS));
		//zvanje.setLayout(new BoxLayout(zvanje, BoxLayout.X_AXIS));
		
		
		JLabel imelab =     	 new JLabel("    Ime:*    ");
		JLabel prezimelab =  	new JLabel("    Prezime:*    ");
		JLabel datRodjlab =  	new JLabel("    Datum rodjenja:*    ");
		JLabel adresalab =   	new JLabel("    Adresa stanovanja:    ");
		JLabel brtellab =    	new JLabel("    Broj telefona:*    ");
		JLabel indekslab =   	new JLabel("    Broj licne karte:*    ");
		JLabel predmetilab =    new JLabel("    Lista predmeta:    ");
		JLabel emaillab =       new JLabel("    E-mail adresa:*    ");
		JLabel adresaKanclab =  new JLabel("    Adresa Kancelarije:    ");
		//JLabel zvanjelab =  	new JLabel("    Zvanje:    ");
		JLabel titulalab = 		new JLabel("    Titula:    ");
		
		
		imelab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		prezimelab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		datRodjlab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		adresalab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		brtellab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		indekslab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		predmetilab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		emaillab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		adresaKanclab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		//zvanjelab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		titulalab.setFont(new Font ("Consolas", Font.PLAIN, 16));
		
		
		imetxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		prezimetxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		datumRodjtxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		adresatxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		teltxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		brojLKtxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		emailtxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		adresaKanctxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		//zvanjetxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		titulatxt.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		
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
		brojLK.add(indekslab);
		brojLK.add(brojLKtxt);
		email.add(emaillab);
		email.add(emailtxt);
		adresaKanc.add(adresaKanclab);
		adresaKanc.add(adresaKanctxt);
		//zvanje.add(zvanjelab);
		//zvanje.add(zvanjetxt);
		titula.add(titulalab);
		titula.add(titulatxt);
		
		top_panel.add(ime);
		top_panel.add(prezime);
		top_panel.add(datRodj);
		top_panel.add(adresa);
		top_panel.add(brtel);
		top_panel.add(brojLK);
		top_panel.add(email);
		top_panel.add(adresaKanc);
		top_panel.add(titula);
		//top_panel.add(zvanje);
		
		// botU = botUpper code 
		
		botU.setLayout(new GridLayout(4,1,0, 10));
		
		String[] predmeti = { "" };
		combo = new JComboBox<String>(predmeti);
			
		CustomPanel botU_row1 = new CustomPanel(500, 54, Color.LIGHT_GRAY);
		CustomPanel botU_row2 = new CustomPanel(500, 54, Color.white);
		CustomPanel botU_row3 = new CustomPanel(500, 54, Color.LIGHT_GRAY);
		CustomPanel botU_row4 = new CustomPanel(500, 54, Color.white);
		botU_row1.setLayout(new BoxLayout(botU_row1, BoxLayout.X_AXIS));
		botU_row1.add(predmetilab);
		botU_row1.add(combo);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(stalni);
		bg.add(vanredni);
		bg.add(docent);
		stalni.setSelected(true);
		
		stalni.setOpaque(false);
		vanredni.setOpaque(false);
		docent.setOpaque(false);
		
		botU_row2.setLayout(new BoxLayout(botU_row2, BoxLayout.X_AXIS));
		botU_row2.add(new JLabel("            ")); // labela sluzi kao inset sa leve strane
		botU_row2.add(stalni);
		botU_row2.add(new Container());
		
		botU_row3.setLayout(new BoxLayout(botU_row3, BoxLayout.X_AXIS));
		botU_row3.add(new JLabel("            "));
		botU_row3.add(vanredni);
		botU_row3.add(new Container());
		
		botU_row4.setLayout(new BoxLayout(botU_row4, BoxLayout.X_AXIS));
		botU_row4.add(new JLabel("            "));
		botU_row4.add(docent);
		botU_row4.add(new Container());
		
		
		botU.add(botU_row1); 
		botU.add(botU_row2);
		botU.add(botU_row3);
		botU.add(botU_row4);
		
		vektor.add(botU_row1);
		vektor.add(botU_row2);
		vektor.add(botU_row3);
		//botL = bottomLower code
		
		botL.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		dp.setPreferredSize(new Dimension(125,35));
		dp.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		dp.setFocusPainted(false);
		dp.setBorderPainted(false);
		dp.setContentAreaFilled(false);
		dp.setOpaque(false);
		
		quit.setPreferredSize(new Dimension(125,35));
		quit.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
		quit.setFocusPainted(false);
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		quit.setOpaque(false);
		
		gbc.insets = new Insets (5,25,5,25);
		gbc.gridx = 4; 
		gbc.gridy = 3; 
		
		botL.add(dp, gbc);
		
		gbc.gridx = 9;
		
		botL.add(quit, gbc);
		
		//recolor comment if you want original colours
		
		for (CustomPanel i : vektor)
			i.setBackground(Color.WHITE);
		// let's add some borders hehe
		top_panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		bot_panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		dp.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	dp.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	dp.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	dp.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\dodaj.png"));
		    }
		});
		
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
	
	
	}
	
}
