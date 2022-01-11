import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * This class contains the main method that runs the application
 * @author mgp17hte
 * Created on 28/12/2017
 * 
 */

public class Sudoku extends Thread {


	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException { 
		SwingUtilities.invokeLater(() -> {
			JFrame frm = new SudokuFrame();
			frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			frm.setVisible(true);
			Sudoku solverthread = new Sudoku();
			solverthread.start();
		});
	}
	
	//Thread for solver
//	Thread solverThread = new Thread(new Runnable()){
//		public void run() {
//			solvePuzzle();
//			interrupted = false;
//		}
//	}
}


