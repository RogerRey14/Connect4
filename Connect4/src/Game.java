import java.util.concurrent.TimeUnit;

public class Game {

	//-----Private variables-----
	
	private static final int PLAYER_TURN = 1;
	private static final int AI_TURN = 2;
	private static final int PLAYER_VALUE = 1;
	private static final int AI_VALUE = 2;
	private int turn, value;
	private Board board;
	private KeyBoardLector lector;
	private AI ai;
	boolean valid_value;
	
	//-----Functions and methods-----
	
	
	//Getters
		public int getTurn() {
			return this.turn;
		}
		
		
		public int getValue() {
			return this.value;
		}
		
		
	//Game contructor, inicialitzates the board 8x8
	public Game() {
		
		this.turn = PLAYER_TURN;
		this.value = PLAYER_VALUE;
		this.board = new Board(8,8);
		this.lector = new KeyBoardLector();
		boolean valid_value = false;
		
		System.out.println();
		System.out.println();
		System.out.println("----- WELCOME TO CONNECT 4 -----");
		System.out.println();
		System.out.println();
	}
	
	
	//It changes the turn to the opposite of the last one
	public void changeTurn() {
		
		if (this.turn == PLAYER_TURN) {
			this.turn = AI_TURN;
			this.value =AI_VALUE;
		}
		else if(this.turn == AI_TURN) {
			this.turn = PLAYER_TURN;
			this.value = PLAYER_VALUE;
		}
			
	}
	
	
	//All the actions that a player does
	public void playerAction() {
		
		while (!this.valid_value) {
			System.out.println("Player's turn. Token X");
			System.out.println();
			int column = this.lector.introduceColumn();
			this.valid_value = this.board.introduceToken(column, this.value);
		}
	}
	
	
	
	//All the actions that a player does
		public void player2Action() {
			
			while (!this.valid_value) {
				System.out.println("Player's 2 turn. Token O");
				System.out.println();
				int column = this.lector.introduceColumn();
				this.valid_value = this.board.introduceToken(column, this.value);
			}
		}
	
	
	//All the actions that AI does
	public void aiAction() throws InterruptedException {
		
		while (!this.valid_value) {
			System.out.println("AI's turn. Token O");
			System.out.println();
			
			this.ai = new AI(this.board);
			
			int column = ai.getFinalValue();
			this.valid_value = this.board.introduceToken(column, this.value);	
			column++;
			TimeUnit.MILLISECONDS.sleep(700);
			System.out.println("AI's entered the colum: " + column);
			System.out.println();
			}
	}
	
	
	//Function that comproves if a player has won the game or the board is full
	public boolean someoneWin() {
		
		if (this.board.win()) {
			board.showBoard();
			
			System.out.println();
			System.out.println();
			
			if ( this.turn == PLAYER_TURN ) {
				
				System.out.println("----- Congratulations! Player 1 won the game! -----");
				
			}
			else {
				
				System.out.println("----- You lose! AI's won the game! ----- ");
				
			}
			
			System.out.println();
			
			return true;
		}
		else if (board.fullBoard()) {
			return true;
		}
		else {
			this.valid_value = false;
			changeTurn();
			return false;
		}
	}
	
	
	//Player against AI. MODE
	public void playerVSAI() throws InterruptedException {
		
		boolean end = false;
		
		while(!end) {
			
			this.board.showBoard();
			
			//Turn player 1
			if (this.turn == PLAYER_TURN) {
			
				playerAction();
			}
			
			//Turn AI
			if (this.turn == AI_TURN) {
				
				aiAction();
			}
			
			//Someone win?
			end = someoneWin();
		
		}
		
	}
	
	
	//Player 1 against Player 2. MODE
	public void playerVSplayer() {
		
		boolean end = false;
		
		while(!end) {
			
			this.board.showBoard();
			
			//Turn player 1
			if (this.turn == PLAYER_TURN) {
			
				playerAction();
			}
			
			//Turn player 2
			if (this.turn == AI_TURN) {
				
				player2Action();
			}
			
			//Someone win?
			end = someoneWin();
		
		}
		
	}
	
	
	//Prints the harcoded Menu
	public void menu() {
		
		System.out.println("-----         MENU         -----");
		System.out.println();
		System.out.println("-- 1. PLAYER 1 VS PLAYER 2    --");
		System.out.println();
		System.out.println("-- 2. PLAYER VS AI            --");
		System.out.println();
		System.out.println("-- 3. EXIT                    --");
		System.out.println();
		System.out.println("-----                      -----");
		System.out.println();
		
	}
	
	
	//Main function of the program
	public void run() throws InterruptedException {
			
		boolean play = false;
			
		menu();
			
		do {
				
			int option = this.lector.introduceOptionMenu();
				
			switch (option) {
				
			case (1): 
				playerVSplayer();
				play = true;
				break;
			case (2): 
				playerVSAI();
				play = true;
				break;
			case (3):
				play = true;
				break;
			default:
				break;
			}
				
		} while(!play);
		
	}
	
}
