package studentskasluzba.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
		
		JMenuItem miClose = new JMenuItem("Close"); 
		miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		
		mFile.add(miNew);
		mFile.addSeparator(); // separator moze ali ne mora 
		mFile.add(miClose);
		add(mFile);
		
		// mEdit - "m" ispred stavke znaci da je u pitanju menu , "mi" ispred stavke znaci da je u pitanju menu item
		JMenu mEdit = new JMenu("Edit");	
		JMenuItem miEdit = new JMenuItem("Edit");
		JMenuItem miDelete = new JMenuItem("Delete");
		
		mEdit.setMnemonic(KeyEvent.VK_E);
		mEdit.add(miEdit);
		mEdit.addSeparator();
		mEdit.add(miDelete);
		add(mEdit);
		
		JMenu mHelp = new JMenu("Help");
		mHelp.setMnemonic(KeyEvent.VK_H);
		JMenuItem miHelp = new JMenuItem("Help");
		JMenuItem miAbout = new JMenuItem("About");
		JMenuItem miOptions = new JMenuItem("Options"); // za kasnije ako budemo hteli  neke opcije mozda
		mHelp.add(miHelp);
		mHelp.add(miAbout);
		mHelp.add(miOptions);
		add(mHelp);
		
		// TODO: napisati help sekciju, about sekciju, options sekciju

	
	}
		
	
}
