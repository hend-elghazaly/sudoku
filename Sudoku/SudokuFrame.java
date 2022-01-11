/*
 * This class contains the the frame and actions of the buttons
 * @author mgp17hte
 * Created on 28/12/2017
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Font;


public class SudokuFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static JTextField[][] panel1Text = new JTextField[3][3];
	private static JTextField[][] panel2Text = new JTextField[3][3];
	private static JTextField[][] panel3Text = new JTextField[3][3];
	private static JTextField[][] panel4Text = new JTextField[3][3];
	private static JTextField[][] panel5Text = new JTextField[3][3];
	private static JTextField[][] panel6Text = new JTextField[3][3];
	private static JTextField[][] panel7Text = new JTextField[3][3];
	private static JTextField[][] panel8Text = new JTextField[3][3];
	private static JTextField[][] panel9Text = new JTextField[3][3];

	//status console
	JTextArea textArea = new JTextArea(5,0); //setting a fixed number of lines

	//boolean for buttons and cell status for the console
	boolean solve = false;
	boolean interrupt = false;
	boolean clear = false;
	boolean load = false;
	boolean impossible = false;
	boolean solved = false;
	boolean stuck = false;


	private static final int BLOCK = 3;
	private static String textDisplayed = " ";

	private final static Color SOLVED = Color.YELLOW;
	private final static Color IMPOSSIBLE = Color.RED;
	private final static Color CLEAR = Color.WHITE;

	SudokuSolver object = new SudokuSolver();
	SudokuCell cell = new SudokuCell();

	public static final int DEFAULT_WIDTH = 1050;                                                                                                                                                                     
	public static final int DEFAULT_HEIGHT = 800;

	/**
	 * Create the frame.
	 */
	public SudokuFrame() {
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//buttons of the interface
		JPanel buttonsPanel = new JPanel(new GridLayout(1,5));
		buttonsPanel.setBounds(6, 6, 488, 39);
		contentPane.add(buttonsPanel);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		makeButton(buttonsPanel, "Load File", this);	
		makeButton(buttonsPanel, "Solve", this); 
		makeButton(buttonsPanel, "Interrupt", this); 
		makeButton(buttonsPanel, "Clear", this); 
		makeButton(buttonsPanel, "Quit", this);

		//status console panel
		JPanel statusConsole = new JPanel();
		statusConsole.setBounds(39, 327, 432, 129);
		contentPane.add(statusConsole);
		statusConsole.setLayout(null);
		textArea.setBounds(0, 0, 432, 129);
		textArea.setToolTipText("Status Console");
		textArea.setBackground(new Color(255, 250, 240));
		statusConsole.add(textArea);
		consoleMessages(textArea);

		//first block on the top right
		JPanel panel_1 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_1.setBounds(138, 111, 70, 70);
		panel_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		//board editable cells
		for (int i=0; i< panel1Text.length; i++) {
			for (int j = 0; j < panel1Text.length; j++){
				panel1Text[i][j] = new JTextField();
				panel1Text[i][j].setText(textDisplayed);
				panel1Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_1.add(panel1Text[i][j]);			
			}
		}
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_2.setBounds(205, 111, 70, 70);
		panel_2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		//board editable cells
		for (int i=0; i< panel2Text.length; i++) {
			for (int j = 0; j < panel2Text.length; j++){
				panel2Text[i][j] = new JTextField();
				panel2Text[i][j].setText(textDisplayed);
				panel2Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_2.add(panel2Text[i][j]);
			}
		}
		contentPane.add(panel_2);

		JPanel panel_3 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_3.setBounds(271, 111, 70, 70);
		panel_3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		//board editable cells
		for (int i=0; i<panel3Text.length; i++) {
			for (int j = 0; j < panel3Text.length; j++){
				panel3Text[i][j] = new JTextField();
				panel3Text[i][j].setText(textDisplayed);
				panel3Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_3.add(panel3Text[i][j]);
			}
		}
		contentPane.add(panel_3);

		JPanel panel_4 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_4.setBounds(138, 178, 70, 70);
		panel_4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		for (int i=0; i< panel4Text.length; i++) {
			for (int j = 0; j < panel4Text.length; j++){
				panel4Text[i][j] = new JTextField();
				panel4Text[i][j].setText(textDisplayed);
				panel4Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_4.add(panel4Text[i][j]);
			}
		}
		contentPane.add(panel_4);

		JPanel panel_5 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_5.setBounds(205, 178, 70, 70);
		panel_5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		for (int i=0; i< panel5Text.length; i++) {
			for (int j = 0; j < panel5Text.length; j++){
				panel5Text[i][j] = new JTextField();
				panel5Text[i][j].setText(textDisplayed);
				panel5Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_5.add(panel5Text[i][j]);
			}
		}	
		contentPane.add(panel_5);

		JPanel panel_6 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_6.setBounds(271, 178, 70, 70);
		panel_6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		for (int i=0; i< panel6Text.length; i++) {
			for (int j = 0; j < panel6Text.length; j++){
				panel6Text[i][j] = new JTextField();
				panel6Text[i][j].setText(textDisplayed);
				panel6Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_6.add(panel6Text[i][j]);
			}
		}
		contentPane.add(panel_6);

		JPanel panel_7 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_7.setBounds(138, 245, 70, 70);
		panel_7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		for (int i=0; i< panel7Text.length; i++) {
			for (int j = 0; j < panel7Text.length; j++){
				panel7Text[i][j] = new JTextField();
				panel7Text[i][j].setText(textDisplayed);
				panel7Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_7.add(panel7Text[i][j]);
			}
		}
		contentPane.add(panel_7);

		JPanel panel_8 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_8.setBounds(205, 245, 70, 70);
		panel_8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		for (int i=0; i< panel8Text.length; i++) {
			for (int j = 0; j < panel8Text.length; j++){
				panel8Text[i][j] = new JTextField();
				panel8Text[i][j].setText(textDisplayed);
				panel8Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_8.add(panel8Text[i][j]);
			}
		}
		contentPane.add(panel_8);

		JPanel panel_9 = new JPanel(new GridLayout(3, 3, 0, 0));
		panel_9.setBounds(271, 245, 70, 70);
		panel_9.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		for (int i=0; i< panel9Text.length; i++) {
			for (int j = 0; j < panel9Text.length; j++){
				panel9Text[i][j] = new JTextField();
				panel9Text[i][j].setText(textDisplayed);
				panel9Text[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

				panel_9.add(panel9Text[i][j]);
			}
		}
		contentPane.add(panel_9);
		
		txtpleaseNoteDisplayed = new JTextField();
		txtpleaseNoteDisplayed.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		txtpleaseNoteDisplayed.setText("This is because that part was commented out in the code as it is not working properly.");
		txtpleaseNoteDisplayed.setBounds(6, 73, 488, 26);
		contentPane.add(txtpleaseNoteDisplayed);
		txtpleaseNoteDisplayed.setColumns(10);
		
		txtPleaseGiveIt = new JTextField();
		txtPleaseGiveIt.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		txtPleaseGiveIt.setText("Please note when solve is clicked, it is pausing but not displaying the numbers.");
		txtPleaseGiveIt.setBounds(6, 50, 488, 26);
		contentPane.add(txtPleaseGiveIt);
		txtPleaseGiveIt.setColumns(10);

	}

	//for status console
	//create a list of String - LinkedList because it needs to be ordered and modifiable 
	LinkedList<String> messages = new LinkedList<String>();
	private JTextField txtpleaseNoteDisplayed;
	private JTextField txtPleaseGiveIt;
	//method for adding and removing to list
	private void consoleMessages(JTextArea textArea){
		//add to this list 
		messages.add("Ready");
		//"solve" -> solving,
		if(solve == true){
			messages.add("Solving...");
		}
		//if it is stuck (if there are zeros in the cells)-> stuck
		if(stuck == true){
			messages.add("Stuck");
		}
		//if it succeeds - at the moment solved is when it completed solving
		if(solved == true){
			messages.add("Solved!");
		}
		//impossibility detected (red) - at the moment the program treats stuck and impossible the same as there was no time
		if(impossible == true){
			messages.add("Impossibiliity detected");
		}
		//if the user clicks "load" -> load, 
		if(load == true){
			String file = "Loading " +object.fileName;
			messages.add(file);
		}
		//"interrupt" -> interrupted! -> 
		if(interrupt == true){
			messages.add("Interrupted");
		}
		//if the console is full (text area size is small than the list size)
		if(messages.size() > textArea.getRows()){
			//remove(first element in list at index 0) which is the oldest
			System.out.println("line length exceeded");
			messages.remove(0);
		}
		//update status console
		textArea.setText(String.join("\n", messages));
	}

	private void makeButton(JPanel p, String name, ActionListener target) {
		JButton b = new JButton(name);
		// add it to the specified JPanel and make the JPanel listen
		p.add(b);  
		b.addActionListener(target); 
	}

	public void actionPerformed(ActionEvent e) {

		// Do the appropriate thing depending on which button is pressed.  
		// Use the getActionCommand() method to identify the button. 
		String command = e.getActionCommand();

		if (command.equals("Quit")) {
			System.exit(0);
		}
		if (command.equals("Clear")) {
			clear = true;
			for (int i=0; i < BLOCK; i++) {
				for (int j = 0; j < BLOCK; j++){
					panel1Text[i][j].setText(" ");
					panel1Text[i][j].setBackground(CLEAR);
					panel2Text[i][j].setText(" ");
					panel2Text[i][j].setBackground(CLEAR);
					panel3Text[i][j].setText(" ");
					panel3Text[i][j].setBackground(CLEAR);
					panel4Text[i][j].setText(" ");
					panel4Text[i][j].setBackground(CLEAR);
					panel5Text[i][j].setText(" ");
					panel5Text[i][j].setBackground(CLEAR);
					panel6Text[i][j].setText(" ");
					panel6Text[i][j].setBackground(CLEAR);
					panel7Text[i][j].setText(" ");
					panel7Text[i][j].setBackground(CLEAR);
					panel8Text[i][j].setText(" ");
					panel8Text[i][j].setBackground(CLEAR);
					panel9Text[i][j].setText(" ");
					panel9Text[i][j].setBackground(CLEAR);
				}
			}
		}

		if (command.equals("Load File")) {
			load = true;
			try {
				object.inputFile();
				updateCells(SudokuSolver.cells);
				//for the first panel

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
		if (command.equals("Solve")) {
			solve = true;
			try {
				object.solvePuzzle();
				updateCells(SudokuSolver.cells);

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (command.equals("Interrupt")) {
			interrupt = true;
			System.out.println(Thread.interrupted());
			Sudoku solverthread = new Sudoku();

//	        try {
//	            Thread.sleep(5000);
//	            solverthread.interrupt();
//	 
//	        } catch (InterruptedException ex) {
//	            // do nothing
//	        }

		}
		consoleMessages(textArea);

	}

	public void componentResized(ComponentEvent e) {

		// TODO Auto-generated method stub

	}


	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}


	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}
	public void updateCells (SudokuCell[][] cells){
		//for the first panel
		for (int row=0; row<3; row++) {
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row][column].getDisplayedNumber());
				panel1Text[row][column].setText(textDisplayed);
				//if the cell is solved, set the background colour to yellow
				if(SudokuSolver.cells[row][column].isSolved()){
					panel1Text[row][column].setBackground(SOLVED);	
				}else if(SudokuSolver.cells[row][column].isImpossible()){
					panel1Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel1Text[row][column].setText(object.cells[row][column].getPossibleValues());}
			}

		}
		//for the second panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row][column+3].getDisplayedNumber());
				panel2Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row][column+3].isSolved()){
					panel2Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row][column+3].isImpossible()){
					panel2Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel2Text[row][column].setText(object.cells[row][column].getPossibleValues());}
			}
		}
		//for the third panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row][column+6].getDisplayedNumber());
				panel3Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row][column+6].isSolved()){
					panel3Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row][column+6].isImpossible()){
					panel3Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel3Text[row][column].setText(object.cells[row][column].getPossibleValues());}

			}
		}
		//for the fourth panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row+3][column].getDisplayedNumber());
				panel4Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row+3][column].isSolved()){
					panel4Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row+3][column].isImpossible()){
					panel4Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel4Text[row][column].setText(object.cells[row][column].getPossibleValues());}

			}
		}
		//for the fifth panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row+3][column+3].getDisplayedNumber());
				panel5Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row+3][column+3].isSolved()){
					panel5Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row+3][column+3].isImpossible()){
					panel5Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel5Text[row][column].setText(object.cells[row][column].getPossibleValues());}
			}
		}
		//for the sixth panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row+3][column+6].getDisplayedNumber());
				panel6Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row+3][column+6].isSolved()){
					panel6Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row+3][column+6].isImpossible()){
					panel6Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel6Text[row][column].setText(object.cells[row][column].getPossibleValues());}
			}
		}
		//for the 7th panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row+6][column].getDisplayedNumber());
				panel7Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row+6][column].isSolved()){
					panel7Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row+6][column].isImpossible()){
					panel7Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel7Text[row][column].setText(object.cells[row][column].getPossibleValues());}
			}
		}
		//for the 8th panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row+6][column+3].getDisplayedNumber());
				panel8Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row+6][column+3].isSolved()){
					panel8Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row+6][column+3].isImpossible()){
					panel8Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel8Text[row][column].setText(object.cells[row][column].getPossibleValues());}
			}
		}
		//for the 9th panel
		for (int row=0; row<3; row++) {
			//inner for columns
			for (int column = 0; column < 3; column++){
				textDisplayed = Integer.toString(SudokuSolver.cells[row+6][column+6].getDisplayedNumber());
				panel9Text[row][column].setText(textDisplayed);
				if(SudokuSolver.cells[row+6][column+6].isSolved()){
					panel9Text[row][column].setBackground(SOLVED);
				}else if(SudokuSolver.cells[row+6][column+6].isImpossible()){
					panel9Text[row][column].setBackground(IMPOSSIBLE);
					impossible = true;
					stuck = true;
				}
				//to display the possible values if some were eliminated
			//	else {panel9Text[row][column].setText(object.cells[row][column].getPossibleValues());}
			}
		}
	}
}
