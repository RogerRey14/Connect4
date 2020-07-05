import java.util.Scanner;

public class Board {
	
	//-----Private variables-----
	
	private int[][] matrix_board; 
	private int size_x;
	private int size_y;
	private int value;
	private String string_board;
	private int lastX;
	private int lastY;
	
	//-----Functions and methods-----
	
	
	//Getters
	public int getSizeX() { return this.size_x; }
	public int getSizeY() { return this.size_y; }
	public int getValue() { return this.value; }
	public int getLastX() {return this.lastX;}
	public int getLastY() {return this.lastY;}
	public int getValue(int posX, int posY) { return this.matrix_board[posX][posY]; }
	public int[][] getMatrixBoard() { return this.matrix_board; }
	public String getStringBoard() { return this.string_board; }
	
	
	//Board Constructor
	Board(int size_x, int size_y) {
		
		this.size_x = size_x;
		this.size_y = size_y;
		this.lastX = 0;
		this.lastY = 0;
		this.value = 0;
		
		this.matrix_board = new int[this.size_x][this.size_y];
		
		for (int i = 0; i < this.size_x; i++) {
			
			for (int j = 0; j < this.size_y; j++) {
				
				this.matrix_board[i][j] = this.value;
			}
		}
	}
	
	
	//Initialization of the board 8x8
	public String initBoard (int[][] matrix) {
		
		String board = "";
		board = board + ("| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | \n");
		for (int i = 0; i < this.size_x; i++) {
			board = board + ("|---|---|---|---|---|---|---|---| \n");
			board = board + ("|");  
			for (int j = 0; j < size_y; j++)
			{
				if (matrix[i][j] != 0) {
					
					if (matrix[i][j] == 1) {
						
						board = board+(" " + "X" + " |");
						
					} else if (matrix[i][j] == 2) {
						
						board = board+(" " + "O" + " |");
						
					}
				} else
					board = board+(" " + " " + " |");
			}
			board = board + " \n";
		}	
		board = board + ("|---|---|---|---|---|---|---|---| \n");
		
		
		return board;
	}
	
	
	//Prints the board
	public void showBoard() {
		
		System.out.println(initBoard(this.matrix_board));
		
	}
	
	
	//Modify a value in a specific position of the matrix
	public void modifyValue(int posX, int posY, int val) {
		
		this.matrix_board[posX][posY] = val;
		
	}
	
	
	//It comprove if the value of a specific position of the matrix is empty(0) or not (1 or 2) 
	public boolean notEmptyValue(int posX, int posY) {
		
		if ( this.matrix_board[posX][posY] == 0 ) {
			return false;
		} 
		else
			return true;
		
	}
	
	
	//Comprove if an integer is between 1 and 8
	public boolean comproveInteger(int number) {
		
		if (number <= 7 && number >= 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	//These function introduce a token in a specific column. It comproves if position is empty or not, because the token will simulate gravity 
	//so it will be positioned on the last row of the matrix or over the last one. 
	public boolean introduceToken(int posY, int value) {
		
		boolean empty_position = false;
		int posX = 7;
		//posY--;
		
		if ( comproveInteger(posY) ) {
			
			while ( posX >= 0 && empty_position == false) {
				
				if (!notEmptyValue(posX,posY)) {
					
					modifyValue(posX, posY, value);
					this.lastX = posX;
					this.lastY = posY;
					empty_position = true;
					return true;
				}
				else {
					
					posX--;
				}
				
			}
			
			System.out.println("Column full. Introduce the token in another column. ");
			System.out.println();
			return false;
		}
		
		else {
			
			System.out.println("Invalid number. Enter one between 1 & 8. ");
			System.out.println();
			return false;
			
		}
	}
	
	
	//Comproves all the cases that could have been possible to take a win
	public boolean win() {
		
		boolean win = false;
		
		if (verticalWin()) {
			win = true;
		} else if (horitzontalWin()) {
			win = true;
		} else if (diagonalLeftRightWin()) {
			win = true;
		} else if (diagonalRightLeftWin()) {
			win = true;
		}
		
		return win;
	}
	
	
	//Comproves if there are 4 tokens of the same player connected vertical
	public boolean verticalWin() {
		
		int actualX = this.lastX;
		int actualY = this.lastY;
		int actual_value = getValue(actualX, actualY);
		boolean win = false;
		int counter = 0;
		
		//DOWN validation
		while (actualX < this.size_x && this.matrix_board[actualX][actualY] == actual_value) {
			counter++;
			actualX++;
		}
		
		if (counter >= 4) {
			win = true;
		}
		
		if (win)
			return true;
		else 
			return false;
		
	}
	
	
	//Comproves if there are 4 tokens of the same player connected horitzontal
	public boolean horitzontalWin() {
		
		int actualX = this.lastX;
		int actualY = this.lastY;
		int actual_value = getValue(actualX, actualY);
		boolean win = false;
		int counter = 0;

		//RIGTH validation
		while (actualY < this.size_y && this.matrix_board[actualX][actualY] == actual_value) {
			counter++;
			actualY++;
		}
		
		actualY = this.lastY - 1;
		
		//LEFT validation
		while (actualY >= 0 && this.matrix_board[actualX][actualY] == actual_value) {
			counter++;
			actualY--;
		}
		
		if(counter >= 4) {
			win = true;
		}
		
		if (win)
			return true;
		else 
			return false;
		
	}
	
	
	////Comproves if there are 4 tokens of the same player connected diagonal left-right
	public boolean diagonalLeftRightWin() {
		
		int actualX = this.lastX;
		int actualY = this.lastY;
		int actual_value = getValue(actualX, actualY);
		boolean win = false;
		int counter = 0;
		
		//DOWN RIGHT
         while (actualX < this.size_x && actualY < this.size_y && this.matrix_board[actualX][actualY] == actual_value) {
             counter++;
             actualX++;
             actualY++;
         }
         
         actualX = this.lastX - 1;
         actualY = this.lastY - 1;
         
         //UP LEFT
         while (actualX >= 0 && actualY >= 0 && this.matrix_board[actualX][actualY] == actual_value) {
             counter++;
             actualX--;
             actualY--;
         }

         if (counter >= 4) {
 			win = true;
 		}
		
		if (win)
			return true;
		else 
			return false;
	}
	
	
	////Comproves if there are 4 tokens of the same player connected diagonal right-left
	public boolean diagonalRightLeftWin() {
		
		int actualX = this.lastX;
		int actualY = this.lastY;
		int actual_value = getValue(actualX, actualY);
		boolean win = false;
		int counter = 0;
		
		//DOWN LEFT
         while (actualX < this.size_x && actualY >= 0 && this.matrix_board[actualX][actualY] == actual_value) {
             counter++;
             actualX++;
             actualY--;
         }
         
         actualX = this.lastX - 1;
         actualY = this.lastY + 1;;
         
         //UP RIGHT
         while (actualX >= 0 && actualY < this.size_y && this.matrix_board[actualX][actualY] == actual_value) {
             counter++;
             actualX--;
             actualY++;
         }

         if (counter >= 4) {
 			win = true;
 		}
		
		if (win)
			return true;
		else 
			return false;
	}
	
	
	//Comproves that the board is full
	public boolean fullBoard() {
		
		boolean full = true;
		
		for (int i = 0; i < this.size_x; i++) {
			
			for (int j = 0; j < this.size_y; j++) {
				
				if (this.matrix_board[i][j] == 0) {
					full = false;
					break;
				}
			}
		} 
		
		if (full) {
			
			showBoard();
			System.out.println("Board is full. Try again.");
			System.out.println();
			return true;
		}
		else
			return false;
	}
	
	
	//Returns the number of notFull columns
	public int numOfChildren() {
		
		int numChilds = 0;
		boolean zero = false;
		
		for (int i = 0; i < this.size_x; i++) {
			int j = 0;
			
			while (!zero && j < this.size_y) {
				
				if (this.matrix_board[j][i] == 0) {
					
					numChilds++;
					zero = true;
				}
				else 
					j++;
			}
			zero = false;
		} 
		
		return numChilds;
	}
	
	
	//Copy the content of a matrix in another one
	public void copyBoard(int parentBoard[][]) {
		
		for (int i = 0; i < this.size_x; i++) {
			
			for (int j = 0; j < this.size_y; j++) {
				
				this.matrix_board[i][j] = parentBoard[i][j];
			}
		}
		
	}

}
