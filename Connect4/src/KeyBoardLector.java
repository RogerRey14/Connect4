import java.util.Scanner;

public class KeyBoardLector {

	//-----Private variables-----
	
	private int column;
	
	//-----Functions and methods-----
	
	
	//Getters
	public int getColumn() {
		return this.column;
	}
	
	
	//Constructor
	public KeyBoardLector() {
		this.column = 0;
	}
	
	
	//Scanner. Reads an integer and returns it
	public int introduceColumn() {
			
		Scanner sc = new Scanner(System.in);
		char coord;
		
		System.out.print("Enter a column to introduce a token: ");
		coord = sc.next().charAt(0);
		System.out.println();
		
		this.column = translate(coord);
		
		return this.column;
	}
	
	
	//Translates a char to an int
	public int translate(char c) {
		
		int value = -1;
		
		switch(c) {
		
		case '1':
			value = 0;
			break;
		case '2':
			value = 1;
			break;
		case '3':
			value = 2;
			break;
		case '4':
			value = 3;
			break;
		case '5':
			value = 4;
			break;
		case '6':
			value = 5;
			break;
		case '7':
			value = 6;
			break;
		case '8':
			value = 7;
			break;	
		default:
			value = -1;
			break;
		}
		return value;
	}
	
	
	//Translates a char to an int
		public int translate2(char c) {
			
			int value = -1;
			
			switch(c) {
			
			case '1':
				value = 1;
				break;
			case '2':
				value = 2;
				break;
			case '3':
				value = 3;
				break;
			default:
				value = 0;
				break;
			}
			return value;
		}
	
	
	//Scanner for option Menu	
	public int introduceOptionMenu() {
		
		Scanner sc = new Scanner(System.in);
		char coord;
		
		System.out.print("Enter an option: ");
		coord = sc.next().charAt(0);
		System.out.println();
		
		this.column = translate2(coord);
		
		return this.column;
	}
	
}
