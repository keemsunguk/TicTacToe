package p2c;
import java.util.Scanner;

public class TTT {
	public String [][] board = null;
	
	private static int ROWS = 3; 
	private static int COLS = 3; 
	private static String SPACE = " ";
	
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
        Scanner scan = new Scanner(System.in);
        
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
		boolean inputValid = false;
        
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
	
	public void ComputerMove(String XorO) {
		if(!SetLine(XorO, XorO)) {
			if(IsCenterSPACE()) {
				set(ROWS/2,COLS/2, XorO);
			} else if( !Need2Defend(XorO) ){	//defending in the Need2Defend
				Attack(XorO);
			}
		}
	}
	
	private boolean IsCenterSPACE() {
		if( board[ROWS/2][COLS/2] == SPACE ) {
			return true;
		} else {
			return false;
		}
	}
	
	private String NotComputer(String s) {
		if(s.equals("X")) { return "O";}
		else {return "X";}
	}
	
	private boolean Need2Defend(String s) {
		String opponent = NotComputer(s);		
		return SetLine(opponent, s);
	}
	
	private void Attack(String s) {
		if( !SetLine(s, s) ) {
			if( !SetFreeLine(s) ) {
				if ( !SetSPACECorner(s) ) {
					if (!SetCenterLine(s)) {
						//else fill any spot to tie
						SetAnySpace(s);
					}
				}
			}
		}
	}
	
	//s1, s2 is diff: Defending, s1==s2: Attacking
	private boolean SetLine(String s1, String s2) {
		for(int r = 0; r < ROWS; r++) {
			int occupancy = 0;
			for(int c = 0; c < COLS; c++) {
				if(board[r][c].equals(s1) ) {
					occupancy++;
				}
			}
			if(occupancy == 2) {
				for(int c = 0; c < COLS; c++) {
					if ( setIfSPACE(r,c,s2) )  return true;
				}				
			}
		}
		for(int c = 0; c < COLS; c++) {
			int occupancy = 0;
			for(int r = 0; r < ROWS; r++) {
				if(board[r][c].equals(s1) ) {
					occupancy++;
				}
			}
			if(occupancy == 2) {
				for(int r = 0; r < ROWS; r++) {
					if ( setIfSPACE(r,c,s2) )  return true;
				}
			}
		}

		int occupancy = 0;
		for(int d = 0; d < COLS; d++) {
			if(board[d][d].equals(s1) ) {
				occupancy++;
			}
		}
		if(occupancy == 2) {
			for(int d = 0; d < COLS; d++) {
				if( setIfSPACE(d,d,s2) ) return true;
			}
		}
		occupancy = 0;
		for(int d = 0; d < COLS; d++) {
			if(board[d][d].equals(s1) ) {
				occupancy++;
			}
		}
		if(occupancy == 2) {
			for(int d = 0; d < ROWS; d++) {
				if (setIfSPACE(d,ROWS-1-d,s2) )	return true;
			}
		}
		return false;
	}
	
	private boolean setIfSPACE(int r, int c, String s) {
		if(board[r][c] == SPACE ) {
			board[r][c] = s;
			return true;
		} else {
			return false;
		}
	}
		
	private boolean SetSPACECorner(String s) {
		if(	board[0][0] == SPACE ) {
			if( (board[0][1] == SPACE && board[0][COLS-1].equals(s)) ||
				(board[0][COLS-1] == SPACE && board[0][1].equals(s)) ||
				(board[1][0] == SPACE && board[2][0].equals(s)) ||
				(board[2][0] == SPACE && board[1][0].equals(s)) ||
				(board[2][COLS-1] == SPACE && board[1][1].equals(s))) {
				set(0,0,s);
				return true;
			}
		} else if( board[0][COLS-1] == SPACE ) {
			if( (board[0][1] == SPACE && board[0][0].equals(s)) ||
				(board[1][2] == SPACE && board[2][2].equals(s)) ||
				(board[2][2] == SPACE && board[1][2].equals(s)) ||
				(board[2][0] == SPACE && board[1][1].equals(s))) {
				set(0,COLS-1,s);
				return true;
			}
		} else if( board[ROWS-1][0] == SPACE ) {
			if( (board[2][1] == SPACE && board[2][2].equals(s)) ||
				(board[2][2] == SPACE && board[2][1].equals(s)) ) {
				set(ROWS-1,0,s);
				return true;
			}
		} else if( board[ROWS-1][COLS-1] == SPACE ) {
			set(ROWS-1, COLS-1, s);
			return true;
		} 
		
		return false;
	
	}
	private boolean SetCenterLine(String s) {
		if(	board[1][0] == SPACE ) {
			if( board[1][2] == SPACE ) {
				set(1,0,s);
				return true;
			}
		} else if( board[0][1] == SPACE ) {
			if( board[2][1] == SPACE ) {
				set(0,1,s);
				return true;
			}
		} 
		
		return false;
	
	}
	
	private boolean SetFreeLine(String XorO) {
		if (board[0][0] == SPACE) {
		//take corner
			if( (board[0][1] == SPACE && board[0][2] == SPACE )||
				(board[1][0] == SPACE && board[2][0] == SPACE )||
				(board[1][1] == SPACE && board[2][2] == SPACE )){
				board[0][0] = XorO;
				return true;
			} 
		} else if(board[0][2] == SPACE) {
			if( (board[1][1] == SPACE && board[2][0] == SPACE) ||
			    (board[1][2] == SPACE && board[2][2] == SPACE)) {
				  board[0][2] = XorO;
				  return true;
			  }
		} else if(board[2][0] == SPACE && board[2][1] == SPACE&& board[2][2] == SPACE)  {
			board[2][0] = XorO;
			return true;
		} else if (board[1][1] == SPACE) {
			if(board[0][1] == SPACE && board[2][1] == SPACE) {
				board[1][1] = XorO;
				return true;
			} else if(board[1][0] == SPACE && board[1][2] == SPACE) {
				board[1][2] = XorO;
				return true;
			}
		} 
		return false;
	}
	
	private void SetAnySpace(String XorO) {
		for(int r = 0; r < ROWS; r++) {
			for(int c = 0; c < COLS; c++) {
				if(board[r][c] == SPACE) {
					set(r, c, XorO);
				}
			}
		}
	}
}

