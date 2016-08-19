package p2p;

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
				gameOver = true;
				System.out.println(currentMove + "Won!!");
			} else if(ttt.isDraw())  {
				gameOver = true;
				System.out.println("Draw......");
			} else {  //switch player
				if(currentMove == "X") {
					currentMove = "O";
				} else {	//if the current player is O
					currentMove = "X";
				}
				//system("cls");
			}
		}
	}
	
	public static void introGame() {
		System.out.println("==============================");
		System.out.println("Welcome to Tic-Tac-Toe Project");
		System.out.println("This is a project assigned");
		System.out.println("==============================");
	}
}