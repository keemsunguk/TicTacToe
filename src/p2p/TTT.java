package p2p;
import java.util.Scanner;

public class TTT {
	public String [][] board = null;
	
	private static int ROWS = 3; 
	private static int COLS = 3; 
	private static String SPACE = " ";
	private Scanner scan;
	
	/*
	 * Constructor
	 */
	TTT() { 
		board = new String[ROWS][COLS];
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				board[i][j] = SPACE;
			}
		}
        scan = new Scanner(System.in);
	}

	TTT(int r, int c) {
		ROWS = r;
		COLS = c;
		board = new String[ROWS][COLS];
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				board[i][j] = SPACE;
			}
		}
        scan = new Scanner(System.in);
	}

	/*
	 * Name: toString
	 * @return String version of board
	 * 
	 */
	public String toString() {
		String local_board = "";
		
		for(int i = 0; i < ROWS; i++) {
			local_board += "\n+---+---+---+\n|";
			for(int j = 0; j < COLS; j++) {
				local_board = local_board + " "+board[i][j]+" |";
			}
		}
		local_board += "\n+---+---+---+\n";
		
		return local_board;
	}
	
	/*
	 * Name: numMoves
	 * @return  Returns number of occupied locations
	 */
	public int numMoves() {
		int local_occupied = 0;
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if( board[i][j] != SPACE) {
					local_occupied++;
				}
			}
		}
				
		return local_occupied;
	}
	
	/*
	 * Name isValid
	 * @param: row, col poistion vector
	 * @return: true if the location is SPACE
	 */
	private boolean isValid(int row, int col) {	
		
		if( row < 0 || row > 2) {
			return false;
		}
		
		if( col < 0 || col > 2) {
			return false;
		}
	
		if(board[row][col] == SPACE) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Name hasWon
	 * @param XorO
	 * @return true if X or O appears three in a row
	 */
	public boolean hasWon(String XorO) {
		
		for(int r = 0; r< ROWS; r++) {
			if( (board[r][0] == XorO) &&
				(board[r][1] == XorO) &&
				(board[r][2] == XorO) ) {
				return true;
			}
		}

		for(int c = 0; c < COLS; c++) {
			if( (board[0][c] == XorO) &&
				(board[1][c] == XorO) &&
				(board[2][c] == XorO) ) {
				return true;
			}
		}
		
		if( (board[0][0] == XorO) &&
			(board[1][1] == XorO) &&
			(board[2][2] == XorO) ) {
			return true;
		}
		if( (board[0][2] == XorO) &&
			(board[1][1] == XorO) &&
			(board[2][0] == XorO) ) {
			return true;
		}
		
		return false;
	}
	
	/* Name isDraw 
	 * @param
	 * @return true if the game is a draw
	 */
	public boolean isDraw() {
		int local_occupied = numMoves();
		
		if( local_occupied == COLS*ROWS) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * 
	 */
	public void makeMove(String XorO) {
		System.out.println("Please Enter Your Move: ");
		boolean inputValid = false;
        
        int row = 0;
        int col = 0;
        
		while(!inputValid) {
			System.out.print("Row:"); 
			row =scan.nextInt();
			System.out.print("Col:");
			col =scan.nextInt();
			
			if(isValid(row, col)) {
				inputValid = true;
			} else {
				System.out.print("Invalid entry. Please reenter");				
			}
		}
		set(row, col, XorO);
	}
	
	//For GUI
	public boolean makeMove(int r, int c, String XorO) {
        int row = r;
        int col = c;
       
		if(isValid(row, col)) {
			set(row, col, XorO);
			return true;
		} else {
			System.out.println("Invalid entry. Please reenter");		
			return false;
		}
	}
	/*
	 * 
	 */
	private void set(int row, int col, String XorO) {
		board[row][col] = XorO;	
	}
	
	public void closeTTT() {
		scan.close();
	}
}

