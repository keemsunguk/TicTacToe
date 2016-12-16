package TicTacToe.src.Skeleton;

public class TTT {
	public String [][] board = null;
	
	private static int ROWS = 3; 
	private static int COLS = 3; 
//	private static String SPACE = " ";
	
	/*
	 * Constructor
	 */
	TTT() { 

	}
	
	TTT(int r, int c) {
		ROWS = r;
		COLS = c;
	}
	/*
	 * Name: toString
	 * @return String version of board
	 * Printing the board
	 * 
	 */
	public String toString() {
		String local_board = "";
		local_board = String.valueOf(ROWS)+" by "+String.valueOf(COLS);
		
		return local_board;
	}
	
	/*
	 * Name: numMoves
	 * @return  Returns number of occupied locations
	 */
	public int numMoves() {
		int local_occupied = 0;
		
				
		return local_occupied;
	}
	
	/*
	 * Name isValid
	 * @param: row, col poistion vector
	 * @return: true if the location is SPACE
	 */
	private boolean isValid(int row, int col) {	
		
		return false;
		
	}

	/*
	 * Name hasWon
	 * @param XorO
	 * @return true if X or O appears three in a row
	 */
	public boolean hasWon(String XorO) {
		
		if( (board[0][0] == XorO) &&
			(board[0][1] == XorO) &&
			(board[0][2] == XorO) ) {
			return true;
		}
		//do the rest of 8 cases
		
		return false;
	}
	
	/* Name isDraw 
	 * @param
	 * @return true if the game is a draw
	 */
	public boolean isDraw() {
		return false;
	}
	
	/*
	 * 
	 */
	public void makeMove(String XorO) {
		System.out.println("Please Enter Your Move: ");
        
        int row = 0;
        int col = 0;
        
		set(row, col, XorO);
	}

	//For GUI
	public boolean makeMove(int r, int c, String XorO) {
        int row = r;
        int col = c;
        
		return true;
	}
	
	/*
	 * 
	 */
	private void set(int row, int col, String XorO) {
		board[row][col] = XorO;	
	}
}

