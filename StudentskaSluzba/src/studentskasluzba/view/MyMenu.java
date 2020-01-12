package studentskasluzba.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

public class MyMenu extends JMenuBar {

	/**
	 * 
	 */

	private static final long serialVersionUID = -1595761791693873356L;
	
	public MyMenu(final JFrame parent) {
		// radi preglednosti sve ispod mFileide u File prozor itd.. , mneumonike sam radio kao sto smo na vezbama radili 
		JMenu mFile = new JMenu("File"); 
		mFile.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem miNew   = new JMenuItem("New");
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miNew.setMnemonic(KeyEvent.VK_N);
		miNew.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\add.png"));
		
		
		JMenuItem miClose = new JMenuItem("Close"); 
		miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miClose.setMnemonic(KeyEvent.VK_C);
		miClose.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\close.png"));
		mFile.add(miNew);
		mFile.add(miClose);
		add(mFile);
		
		// mEdit - "m" ispred stavke znaci da je u pitanju menu , "mi" ispred stavke znaci da je u pitanju menu item
		JMenu mEdit = new JMenu("Edit");
		mEdit.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem miEdit = new JMenuItem("Edit");
		miEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miEdit.setMnemonic(KeyEvent.VK_E);
		miEdit.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\edit.png"));
		
		JMenuItem miDelete = new JMenuItem("Delete");
		miDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miDelete.setMnemonic(KeyEvent.VK_D);
		miDelete.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\delete.png"));
		
		mEdit.add(miEdit);
		mEdit.add(miDelete);
		add(mEdit);
		
		JMenu mHelp = new JMenu("Help");
		mHelp.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem miHelp = new JMenuItem("Help");
		miHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miHelp.setMnemonic(KeyEvent.VK_H);
		miHelp.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\help.png"));
		
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		miAbout.setMnemonic(KeyEvent.VK_A);
		miAbout.setIcon(new ImageIcon("StudentskaSluzba\\images\\actual_images\\menu_images\\about.png"));
		
		mHelp.add(miHelp);
		mHelp.add(miAbout);
		add(mHelp);
		
		// TODO: napisati help sekciju, about sekciju
		
		miClose.addActionListener(event -> System.out.println("Close"));

	
	}
		
	
}
