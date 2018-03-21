package fileGUI;

import java.awt.Color;//to make the status bar jlabel blue
import java.awt.GridLayout;//in order to create a grid layout on the window
import java.awt.event.ActionEvent;//in order to make the window act when used
import java.awt.event.ActionListener;//in order to make the GUI listen on what to act on
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;//to create buttons on the window
import javax.swing.JLabel;//to create labels
import javax.swing.JPanel;//to create a GUI, Graphic User Interface
import javax.swing.JTextField;//to create a text box for the user

public class FilePanel extends JPanel implements ActionListener{
	
	//static protected JLabel lblOut;//WHAT is this for?
		private JButton jbCO; //CO = Copy Only
		private JButton jbCC; //CC = Correct and Copy
		private JTextField tfFI;//text box for File In
		private JTextField tfFO;//text box for File Out
		private JLabel jlFI;//title File In next to text box
		private JLabel jlFO;//title File Out next to text box
		static protected JLabel jlSB;//For Status Bar to be visible to user anyway; i wanted it to be in the jframe but left here for now
		
		
		public FilePanel(){//constructor

			
			setLayout(new GridLayout(4,2));//(rows,columns)
			
			tfFI = new JTextField(""); //Creating textFields/text boxes.
			tfFO = new JTextField(""); //Creating textFields/text boxes.
		
		
		jbCO = new JButton("Copy Only"); //the text face of the button
		jbCC = new JButton("Correct and Copy");
		
		jlFI = new JLabel("         File In: ");
		jlFO = new JLabel("         File Out: ");
		jlSB = new JLabel("Status Bar ");
		jlSB.setForeground(Color.BLUE);//Making the Status Bar Blue
		
		jbCO.addActionListener(this);//make the button listen for clicks
		jbCC.addActionListener(this);
		
		add(jlFI);//to display a label for File In
		add(tfFI);//adding the text fields so appears next to the FI
		add(jlFO);//to display a label for File Out
		add(tfFO);
		add(jbCO);//button of Copy Only on the panel is now added
		add(jbCC);//button of the Copy and Correct on the panel is now added
		add(jlSB);//Status bar has also been placed to display
		
		}
		
		@Override //capital O for Override
		 public void actionPerformed(ActionEvent e) {
			String btnClicked = e.getActionCommand().trim();//get buttons Clicked , Copy or Copy and Correct
			//call the JFrame, to use all the file Methods Ive created
			//FileJFrame.StatusBar(btnClicked); //StatusBar will be a method in JFrame grabs Button clicked does an if and else- statement(with try and catch in each) to choose the method it must do which is Copy or Copy and Correct. 
			String getF1 = getStringFromTF(tfFI);
			String getF2 = getStringFromTF(tfFO);
			
			if(btnClicked.equals("Copy Only")){
					
				try{
					copyF1to2(getF1,getF2);
					//jlSB.setForeground(Color.blue);
					jlSB.setText("Status Bar: All Done!");
					}
			catch(Exception ex){//must be Exception ex
			jlSB.setText("Status Bar: Cannot Find One/Both of Those Files, Try again.");
			}
			}
				else{
					try{
					copyNCorrectAll(getF1,getF2);
					jlSB.setText("Status Bar: All Done!");
					}
					catch(Exception ex){
			jlSB.setText("Status Bar: Cannot Find One/Both of Those Files, Try again.");
			}
					
					}
			}		
		



		public static void copyF1to2(String fN1, String fN2){
			Scanner inStream = null;//creates a scanner with no data
			PrintWriter outStream = null;//Prints formatted representations of objects to a text-output stream
			try{
				inStream = new Scanner(new File(fN1));//connect to incoming file //creating the pipeline to later stream in the info from file1
				outStream = new PrintWriter(fN2);//overwrite anything that existed with that file name//creating the pipeline to later stream out the info to file2
				while(inStream.hasNextLine()){//for file 1
					outStream.println( inStream.nextLine());//prints out on file #2, from what is in file #1.
					
				}
			}catch(FileNotFoundException fnfe){
				System.err.println("COULD NOT COPY From FILE "+fN1+ " to "+fN2 +fnfe.getMessage());
			}
			finally{
				inStream.close();
				outStream.close();
			}
		}
		

public static String copyNCorrectAll(String FN1, String FN2){

	Scanner inStream = null;//creates a scanner with no data
	PrintWriter outStream = null;//Prints formatted representations of objects to a text-output stream
	try{
		inStream = new Scanner(new File(FN1));//connect to incoming file //creating the pipeline to later stream in the info from file1
		outStream = new PrintWriter(FN2);//overwrite anything that existed with that file name//creating the pipeline to later stream out the info to file2
		while(inStream.hasNextLine()){//for file 1
			//outStream.println( inStream.nextLine());
			String line= inStream.nextLine();
			line = puncToUpp(line);
			line = noSlang(line);
			line = CCSpaces(line);
			outStream.println(line);
		}


	}catch(FileNotFoundException fnfe){
		System.err.println("COULD NOT COPY AND CORRECT From FILE "+FN1+ " to "+FN2 +fnfe.getMessage());
		String a = "COULD NOT COPY AND CORRECT From FILE "+FN1+ " to "+FN2 +fnfe.getMessage();
		return a; //"COULD NOT COPY AND CORRECT From FILE "+FN1+ " to "+FN2 +fnfe.getMessage();
	}
	finally{
		inStream.close();
		outStream.close();
	}
	return null;//so doesnt do anything

}


public static String puncToUpp(String line){//method used for !,?,.

	int lengthOfline= line.length()-1;

	int indexOfsNp = line.indexOf(". ");
	char firstLetter= line.charAt( indexOfsNp + 2 );
	String firstL= String.valueOf(firstLetter);//from stockoverflow.com
	int count=0;

	while (count <= lengthOfline) {
		if(line.substring(count,count+1).equals(".")){

			firstLetter= line.charAt( count + 2 );
			firstL= String.valueOf(firstLetter);
			line=line.replace(". "+firstLetter, ". "+ firstL.toUpperCase());

		}
		else if(line.substring(count, count+1).equals("?")){

			firstLetter= line.charAt( count + 2 );
			firstL= String.valueOf(firstLetter);
			line=line.replace("? "+firstLetter, "? "+ firstL.toUpperCase());

		}

		else if(line.substring(count, count+1).equals("!")){

			firstLetter= line.charAt( count + 2 );
			firstL= String.valueOf(firstLetter);
			line=line.replace("! " + firstLetter, "! " + firstL.toUpperCase());
		}
		count++;
	}
	return line;
}


public static String noSlang(String line){
	/*Scanner inStream = null;//creates a scanner with no data
	PrintWriter outStream = null;//Prints formatted representations of objects to a text-output stream
	try{
		inStream = new Scanner(new File(fN1));//connect to incoming file //creating the pipeline to later stream in the info from file1
		outStream = new PrintWriter(fN2);//overwrite anything that existed with that file name//creating the pipeline to later stream out the info to file2
		while(inStream.hasNextLine()){//for file 1
			//outStream.println( inStream.nextLine());
			
			String line= inStream.nextLine();
			*/
			line=line.replaceAll("ttyl", "talk to you later");
			line=line.replaceAll("lol", "laughing out loud");
			line=line.replaceAll("brb", "be right back");
			
			return line;
		/*	outStream.println(line);//java understands ->"\\s{2,}" as any two/more spaces
			//System.out.println(line);
		}
	}catch(FileNotFoundException fnfe){
		System.err.println("COULD NOT COPY AND CORRECT From FILE "+fN1+ " to "+fN2 +fnfe.getMessage());
	}
	finally{
		inStream.close();
		outStream.close();
	}*/
}


public static String CCSpaces(String line){
	/*Scanner inStream = null;//creates a scanner with no data
	PrintWriter outStream = null;//Prints formatted representations of objects to a text-output stream
	try{
		inStream = new Scanner(new File(fN1));//connect to incoming file //creating the pipeline to later stream in the info from file1
		outStream = new PrintWriter(fN2);//overwrite anything that existed with that file name//creating the pipeline to later stream out the info to file2
		while(inStream.hasNextLine()){//for file 1
			//outStream.println( inStream.nextLine());
			String line= inStream.nextLine();*/
			//outStream.println(line.trim().replaceAll("\\s{2,}", " "));//java understands ->"\\s{2,}" as any two/more spaces
	String a = (line.trim().replaceAll("\\s{2,}", " "));		
	//System.out.println(line);
	return a;	
	}


		
		
		private static String getStringFromTF(JTextField jtf){
			String a = jtf.getText().trim(); //turn String into double
			return a;
		}}