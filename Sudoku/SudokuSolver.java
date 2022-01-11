/*
 * This class contains the algorithms that fill the cells from a file and solve the sudoku 
 * @author mgp17hte
 * Created on 28/12/2017
 * 
 */

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


public class SudokuSolver {
	// 2D array of SudokuCells objects consisting of 9 rows and 9 columns 
	static SudokuCell[][] cells = new SudokuCell[9][9];
	String path;
	String fileName;
	//class constructor
	public SudokuSolver(){
	}

	//selecting and reading from the file
	// I used this https://www.youtube.com/watch?v=vP3lnY-MUu0 and the assignment sheet
	public void inputFile() throws IOException{
		BufferedReader br = null; //since file is unknown
		String line; 
		//selecting a file
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Please select a plain text file to fill the cells.");
		//allowing the user to filter the selection to only plain text files(.txt) whilst still having the option to select from all files
		jfc.setAcceptAllFileFilterUsed(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain-text files","txt");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath();
			fileName = selectedFile.getName();
			//to check that the file is valid
			try{
				br = new BufferedReader(new FileReader(path));
			}
			catch (FileNotFoundException fnfex){
				System.out.println(fnfex.getMessage() + " The file was not found");
				System.exit(0);
			}
			//reading the file
			try {
				//counter to check the number of lines
				int lineCounter = 0;
				//read the file as long as there is a line to read and the line counter reaches 9
				while ((line = br.readLine()) != null && lineCounter < 9) {  
					// process the line:					
					//check if line has 9 characters
					//c is character index from 0 to 8
					for(int c = 0; c < 9; c++){
						int i;
						try {
							//if there is a character convert it to an integer
							i = Integer.parseInt(line.substring(c, c+1));

						} catch (NumberFormatException e) {
							// if it is a non_digit, blank cell
							i = 0;
							//	cell.isSolved() = false;
							//System.out.println("not a number"+i);
						}
						//store it in the cell object
						SudokuCell cell = new SudokuCell();
						cells[lineCounter][c] = cell;
						cell.setDisplayedNumber(i);

						if(i==0)
							cell.setSolved(false);

						System.out.println("the displayed number at "+lineCounter +c +" is " + cell.getDisplayedNumber());

					}
					// update the counter every time a line is read
					lineCounter ++;
				}

			} 

			catch (Exception e) {
				System.out.println(e.getMessage() + "Error cannot read the file");
				//upload another file if it is the wrong format
				inputFile();

			}	
			finally {
				if (br!=null){
					br.close();
				}
			}
		}
	}


	//method that solves the puzzle by eliminating possible values
	public void solvePuzzle() throws InterruptedException{
	//	for(int run=0; !interrupted; run++){
			//loop through the array of cells
			for(int i = 0; i< cells.length; i++){
				for(int j=0; j<cells.length; j++){
					//for pauses
					Thread.sleep(500);
					//if the cell is empty
					if (cells[i][j].getDisplayedNumber() == 0) {	
						//loop through the possible values and check if each is in row, column, or block of the cell
						int[] values = cells[i][j].getPossibleValues();

						for (int value= 0; value<values.length; value++){
							//check row - go through the columns so same row but different columns
							for(int column = 0; column < 9; column++ ){
								if(values[value] == cells[i][column].getDisplayedNumber()){
									//if it is found in the row eliminate it from possible values(value=0)
									values[value] = 0;
									cells[i][j].setPossibleValues(values);
								}
							}
							//check column- go through the rows so same column but different rows
							for(int row = 0; row < 9; row++ ){
								if(values[value] == cells[row][j].getDisplayedNumber()){
									//if it is found in the column eliminate it from possible values(value=0)
									values[value] = 0;
									cells[i][j].setPossibleValues(values);
								}
							}

							// check which block it is in based on index
							int blockRow;
							int blockColumn;
							//identifying the row of the block
							if (0<=i && i<3){
								blockRow = 0; //blocks 1 to 3
							} else if(3<=i && i<6){
								blockRow = 3; //blocks 4 to 6
							} else {
								blockRow = 6; //blocks 7 to 9
							}

							//identifying the column of the block
							if(0<=j && j<3){
								blockColumn = 0; //blocks 1 to 3
							} else if(3<=j && j<6){
								blockColumn = 3; //blocks 4 to 6
							} else {
								blockColumn = 6; //blocks 7 to 9
							}

							//search the block found for that value
							for(int row = blockRow; row < blockRow+3; row++){
								for(int column = blockColumn; column< blockColumn+3; column++){
									if(values[value] == cells[row][column].getDisplayedNumber()){			
										//if the value is found in the cell's block eliminate it from possible values(value=0)
										values[value] = 0;
										cells[i][j].setPossibleValues(values);
									}
								}
							}
						}//checks finished 

						//if it is not found in the row or column or block, set text of cell to that value 	
						for(int b= 0; b<9; b++ ){
							if (values[b]!=0){
								cells[i][j].setDisplayedNumber(values[b]);
							} 
						}
					}
					//to display the possible values
				//	cells[i][j].setValues();
					
					//if the value in the cell remains 0 it means it is impossible
					if(cells[i][j].getDisplayedNumber() == 0){
						cells[i][j].setImpossible(true);
					} else{ //else it is solved
						cells[i][j].setSolved(true);
					}

				}

			}
		}
//	}

}


