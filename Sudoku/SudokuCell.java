//import java.io.IOException;

/*
 * This class represents a cell on the sudoku board or creates Sudoku cell objects with the 4 instance fields of a cell
 * @author mgp17hte
 * Created on 28/12/2017
 * 
 */

public class SudokuCell {
	
	//instance field variables 
	private int [] possibleValues = {1,2,3,4,5,6,7,8,9}; //array of possible values
	private String values ="";  //for displaying possible values
	private boolean solved; //mark the cell solved or not
	private boolean impossible;  //mark the cell if it is impossible to solve
	private int displayedNumber; //number to display in the cell
	//private int number;
	//class constructor 
	public SudokuCell (){
		this.solved = true; 
		this.impossible = false;
		this.displayedNumber = getDisplayedNumber() ; 
	}

	//getters and setters methods to access and update the field variables 

	public int[] getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(int[] possibleValues) {
		this.possibleValues = possibleValues;
	}
		
	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public boolean isImpossible() {
		return impossible;
	}

	public void setImpossible(boolean impossible) {
		this.impossible = impossible;
	}

	public int getDisplayedNumber() {
		return displayedNumber;
	}
	
	
	// for displaying possible values
	public String getValues() {
		return values;
	}
	public void setValues() {
		values = "";
		for(int i=0; i<9; i++){
			if (possibleValues[i]!=0)
			values  = values + Integer.toString(possibleValues[i]);
		}
	}

	public void setDisplayedNumber(int displayedNumber) {
		this.displayedNumber = displayedNumber;
	}

}
