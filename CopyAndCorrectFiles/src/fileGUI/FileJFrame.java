package fileGUI;




import java.awt.GridLayout;//in order to create a border layout on the window
import java.awt.event.ActionEvent;//in order to make the window act when used
import java.awt.event.ActionListener;//in order to make the GUI listen on what to act on
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JButton;//to create buttons on the window
import javax.swing.JLabel;//to create labels
import javax.swing.JPanel;//to create a GUI, Graphic User Interface
import javax.swing.JTextField;//to create a text box for the user
import javax.swing.SwingConstants;//used for positioning and orienting components on the screen
import java.awt.BorderLayout;


public class FileJFrame extends JFrame{ //implements ActionListener{

	//static protected JLabel lblOut;//WHAT is this for?
	//private JButton btnCO; //CO = Copy Only
	//private JButton btnCC; //CC = Correct and Copy
	//private JTextField tfFI;//text box for File In
	//private JTextField tfFO;//text box for File Out
	//private JLabel jlFI;//title File In next to text box
	//private JLabel jlFO;//title File Out next to text box
	static protected JLabel jlSB;//For Status Bar (Static Protected because we want the Status bar info to be visible to the user anyway.
	
	
	public FileJFrame(){
		
		JPanel container= new JPanel();//to organize jlSB btn nd File Panel better
		//container.setLayout(new BorderLayout());//also to organize
		FilePanel FP = new FilePanel();
		jlSB = new JLabel("Status Bar");
		//jlSB.setHorizontalAlignment(SwingConstants.SOUTH);
		
		//container.add(jlSB, BorderLayout.SOUTH);//hopefully this appears
		//container.add(FP, BorderLayout.CENTER);
		
		container.add(FP);//to finally add the jpanel to the jframe
		add(container);
	}
	
	//public static void StatusBar(String textOnBtn){
		//String btnTouched= FileJFrame.
	
	
//}

	public static void copyF1to2(String fN1, String fN2){ //Method For Copy Only Button..be sure to grab string file1 and string file2 using scanner class
		Scanner inStream = null;//creates a scanner with no data
		PrintWriter outStream = null;//Prints formatted representations of objects to a text-output stream
		try{
			inStream = new Scanner(new File(fN1));//connect to incoming file //creating the pipeline to later stream in the info from file1
			outStream = new PrintWriter(fN2);//overwrite anything that existed with that file name//creating the pipeline to later stream out the info to file2
			while(inStream.hasNextLine()){//for file 1
				outStream.println( inStream.nextLine());//prints out on file #2, from what is in file #1.
				
			}
		}catch(FileNotFoundException fnfe){
			System.err.println("Could NOT Copy From FILE "+ fN1 + " to "+ fN2 +fnfe.getMessage());
		}
		finally{
			inStream.close();
			outStream.close();
		}
	
	
	
}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		String btnClicked= e.getActionCommand().trim();
		
		if(btnClicked.equals("Copy Only")){
			try{
				
				copyF1to2(String F1, String f2);
				
			}
		catch(Exception ex){
			jlSB.setText("Sorry We Could not Find One/Both of Those Files, Try again.");
			
		}
	}
	
}
}*/


}