package studentskasluzba.view;

import javax.swing.JDialog;
import javax.swing.JFrame;



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
	}
	
}