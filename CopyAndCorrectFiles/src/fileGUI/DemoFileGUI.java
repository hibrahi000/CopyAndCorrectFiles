package fileGUI;

public class DemoFileGUI {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable(){//to create the window, so tht it appears to the user
			public void run(){
				FileJFrame gui = new FileJFrame();//bringing in the JFrame[the one i created and called FileJFrame] and calling it gui
				gui.setDefaultCloseOperation(gui.EXIT_ON_CLOSE);//stop running.. when user X's out of the GUI Window
				gui.setSize(300,200);// (width, length)
				gui.setVisible(true);//to make gui visible
		
				}
			} );//from new Runnable
		}
	}


