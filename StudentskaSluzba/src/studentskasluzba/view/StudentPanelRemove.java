package studentskasluzba.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentPanelRemove extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4109397236831412456L;
	public static JButton yes = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
	public static JButton odustani = new JButton(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\odustani.png"));
	
	public StudentPanelRemove(MyDialog md, JFrame parent, int width, int height) {
		
		CustomPanel top_inset = new CustomPanel(650, 50, Color.WHITE);
		CustomPanel bot_inset = new CustomPanel(650,60, Color.WHITE);
		CustomPanel panel = new CustomPanel(650,190, Color.WHITE);
		
		bot_inset.setLayout(new BoxLayout(bot_inset, BoxLayout.Y_AXIS));
		bot_inset.add(new CustomPanel(650,60,Color.WHITE));
	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS )); 
		
		add(top_inset);
		add(panel);
		add(bot_inset);
		
		JLabel question = new JLabel("Da li ste sigurni da zelite da obrisete Studenta?");
		
		CustomPanel paneltop = new CustomPanel(650,95, Color.WHITE);
		CustomPanel panelbot = new CustomPanel(650, 95, Color.WHITE); 
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(paneltop);
		panel.add(panelbot);
		
		paneltop.setLayout(new GridBagLayout());
		paneltop.add(question);
		
		yes.setFocusPainted(false);
		yes.setBorderPainted(false);				
		yes.setOpaque(false);
		yes.setContentAreaFilled(false);
		odustani.setFocusPainted(false);
		odustani.setBorderPainted(false);
		odustani.setOpaque(false);
		odustani.setContentAreaFilled(false);
		
		panelbot.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 25));
		
		panelbot.add(yes);
		panelbot.add(odustani);
		
		yes.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // the user clicks on the label
		    	yes.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
		    	md.dispose();
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // the mouse has entered the label
		    	yes.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi_selected.png"));
		    	
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		        // the mouse has exited the label
		    	yes.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\Buttons\\obrisi.png"));
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
