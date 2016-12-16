package TicTacToe.src.Skeleton;

//import java.util.*;

public class TTTRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TTT ttt = new TTT();
		String tttOutput;
		boolean gameOver = false;
		String currentMove = "X";
		
		introGame();

		tttOutput = ttt.toString();
		System.out.println(tttOutput);
		
		
		while(!gameOver) {
			System.out.println(currentMove+" turn");
			ttt.makeMove(currentMove);
			tttOutput = ttt.toString();
			System.out.println(tttOutput);
			
			if(ttt.hasWon(currentMove)) {
				//action when one side made the winning move
			} else if(ttt.isDraw())  {
				//game is draw
			} else {  //switch player
				if(currentMove == "X") {
					currentMove = "O";
				} else {	//if the current player is O
					currentMove = "X";
				}
			}
		}
	}
	
	public static void introGame() {
		System.out.println("==============================");
		System.out.println("");
		System.out.println("");
		System.out.println("==============================");
	}
}
