package studentskasluzba.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PredmetPanelEdit extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1293735705691265320L;

	private static String[] godine = {"I (prva)" , "II (druga)", "III (treca)", "IV (cetvrta)"};
	
	public static JTextField sifratxt = new JTextField(10);
	public static  JTextField nazivtxt = new JTextField(50);
	public static  JTextField godinatxt = new JTextField(50);
	public static  JTextField profesortxt = new JTextField(50);
	// semestar
	public static  JTextField semestartxt = new JTextField(50);

	// izmeni, odustani
	public static JButton izmeni = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni.png"));
	public static JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
	// Combobox za godine izvodjenja 
	public static JComboBox comboGod = new JComboBox(godine);
	
		PredmetPanelEdit(MyDialog md, final JFrame parent, int width, int height) {
			
			// CustomPanel panel = new CustomPanel(450, 550, Color.PINK);
			CustomPanel top_inset = new CustomPanel(450,50, Color.WHITE); 
			CustomPanel bot_inset = new CustomPanel(450,100, Color.WHITE);
			CustomPanel bot_inset2 = new CustomPanel(450,30,Color.WHITE);
			CustomPanel left_right = new CustomPanel(450,345, Color.WHITE);
			CustomPanel left_inset = new CustomPanel(50, 345, Color.WHITE);
			CustomPanel right_inset = new CustomPanel(50,345,Color.WHITE);
			CustomPanel midpanel = new CustomPanel(350,345, Color.WHITE);
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS )); 
			
			add(top_inset);
			add(left_right);
			add(bot_inset);
			
			bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
			bot_inset.add(new CustomPanel(450,70, Color.WHITE));
			bot_inset.add(bot_inset2);
			
			left_right.setLayout(new BoxLayout(left_right, BoxLayout.X_AXIS));
			left_right.add(left_inset);
			left_right.add(midpanel);
			left_right.add(right_inset);	
				
			CustomPanel top_panel = new CustomPanel(350, 200, Color.WHITE);
			CustomPanel bot_panel = new CustomPanel(300,145, Color.WHITE);	
			
			midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.Y_AXIS));
			midpanel.add(top_panel);
			midpanel.add(bot_panel);
			
			CustomPanel sifra = new CustomPanel(350,30, Color.WHITE);
			CustomPanel naziv = new CustomPanel(350,30, Color.WHITE);
			CustomPanel semestar = new CustomPanel(350,30, Color.WHITE);
			CustomPanel godina = new CustomPanel(350,30, Color.WHITE);
			CustomPanel profesor = new CustomPanel(350,30, Color.WHITE);
			
			top_panel.setLayout(new GridLayout(5,1,0, 10));
			
			sifra.setLayout(new BoxLayout(sifra, BoxLayout.X_AXIS));
			naziv.setLayout(new BoxLayout(naziv, BoxLayout.X_AXIS));	
			semestar.setLayout(new BoxLayout(semestar, BoxLayout.X_AXIS));	
			godina.setLayout(new BoxLayout(godina, BoxLayout.X_AXIS));	
			profesor.setLayout(new BoxLayout(profesor, BoxLayout.X_AXIS));	
			
			// labele
			JLabel sifralab = 	 new JLabel("     Sifra predmeta:*        ");
			JLabel nazivlab = 	 new JLabel("     Naziv predmeta:*        ");
			JLabel semestarlab = new JLabel("     Semestar:*    ");
			JLabel godinalab =   new JLabel("     Godina predavanja:*        ");
			JLabel profesorlab = new JLabel("     Predmetni profesor:*         ");
			
			profesortxt.setToolTipText("Uneti broj licne karte profesora");
			
			sifra.add(sifralab); 	 	
			sifra.add(sifratxt);
			naziv.add(nazivlab); 		
			naziv.add(nazivtxt);
			semestar.add(semestarlab); 
			semestar.add(semestartxt);
			godina.add(godinalab); 		
			godina.add(comboGod);
			profesor.add(profesorlab); 	
			profesor.add(profesortxt);
			
			odustani.setFocusPainted(false);
			odustani.setBorderPainted(false);
			odustani.setContentAreaFilled(false);
			odustani.setOpaque(false);
			izmeni.setFocusPainted(false);
			izmeni.setBorderPainted(false);
			izmeni.setContentAreaFilled(false);
			izmeni.setOpaque(false);
			
			izmeni.setPreferredSize(new Dimension(125,35));
			odustani.setPreferredSize(new Dimension(125,35));
			
			bot_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 90));
			bot_panel.add(izmeni);
			bot_panel.add(odustani);
				
			top_panel.add(sifra);
			top_panel.add(naziv);
			top_panel.add(godina);
			top_panel.add(semestar);
			top_panel.add(profesor);
			
			
			izmeni.addMouseListener(new MouseAdapter() {
				 
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        // the user clicks on the label
			    	izmeni.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni.png"));
			    	md.dispose();
			    }
			 
			    @Override
			    public void mouseEntered(MouseEvent e) {
			        // the mouse has entered the label
			    	izmeni.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni_selected.png"));
			    	
			    }
			 
			    @Override
			    public void mouseExited(MouseEvent e) {
			        // the mouse has exited the label
			    	izmeni.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\izmeni.png"));
			    }
			});
			
			odustani.addMouseListener(new MouseAdapter() {
				 
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        // the user clicks on the label
			    	odustani.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
			    	md.dispose();
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
