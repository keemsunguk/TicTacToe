package TicTacToe.src.p2c;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class TTTGui extends GraphicsProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int ROW = 3;
	static final int COL = 3;
	GRect [][] board;
	GObject clickedObj;
	private GPoint c;
	private double side = 0;
	
	private TTT ttt;
	private String currentMove = "X";
	private GLabel currentTurn;
	private GLabel [] xMark; 
	private GLabel [] oMark;
	private GLabel gameOver;
	private int noMarks = 0;
	
	private JButton resetButton;
	
	
	public void init() {
		//println("Tic Tac Toe");
		ttt = new TTT(ROW, COL);
		setSize(400,400);
		setTitle("Tic Tac Toe");
		board = new GRect[ROW][COL];
		noMarks = (ROW*COL+1)/2;
		xMark = new GLabel [noMarks];
		oMark = new GLabel [noMarks];
		c = new GPoint(getWidth()/2, getHeight()/2);
		side = (Math.min(getWidth(), getHeight())- 100)/3;
		currentTurn = new GLabel(currentMove+" turn");
		currentTurn.setFont("Arial-Bold-16");
		
		for(int i = 0; i < noMarks; i++) {
			xMark[i] = new GLabel("X");
			oMark[i] = new GLabel("O");
			xMark[i].setFont("Arial-Bold-72");
			xMark[i].setColor(Color.BLUE);
			oMark[i].setFont("Arial-Bold-72");
			oMark[i].setColor(Color.RED);
		}
		gameOver = new GLabel("Game Over");
		gameOver.setFont("Arial-Bold-90");
		gameOver.setColor(Color.MAGENTA);
		addMouseListeners();
		
		resetButton = new JButton("Reset");
		add(resetButton, SOUTH);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetGame();
			}
		});
	}
	
	private void SetBoard() {
		int xc = 0;		// local X mark count
		int oc = 0;		// local O mark count
		
		for(int i = 0; i < ROW; i++) {
			for(int j = 0; j < COL; j++) {
				board[i][j] = new GRect((c.getX()-side*1.5)+side*i, (c.getY()-side*1.5)+side*j, side, side);
				board[i][j].setFilled(true);
				if( (i*ROW+j)%2 == 0 ) {
					board[i][j].setColor(Color.YELLOW);
				} else {
					board[i][j].setColor(Color.LIGHT_GRAY);
				}
				add(board[i][j]);
				GPoint sel = new GPoint(board[i][j].getX()+board[i][j].getWidth()/2,
						board[i][j].getY()+board[i][j].getHeight()/2);
				
				if(ttt.board[i][j].equals("X")) {
					add(xMark[xc], sel.getX()-xMark[xc].getWidth()/2, sel.getY()+xMark[xc].getHeight()/4);
					xc++;
				} else if (ttt.board[i][j].equals("O")) {
					add(oMark[oc], sel.getX()-oMark[oc].getWidth()/2, sel.getY()+oMark[oc].getHeight()/4);
					oc++;
				}
			}
		}
	}
	
	private void ShowGameOver(String s) {
		System.out.println(s);
		currentTurn.setLabel("Game Over");
		gameOver.setLabel(s);
		gameOver.setVisible(true);
		add(gameOver, c.getX()-gameOver.getWidth()/2, c.getY());
	}
	
	public void mouseClicked(MouseEvent e) {
		boolean inputValid = false;
		double x = e.getX();
		double y = e.getY();
		clickedObj = getElementAt(x,y);
		
		for(int r = 0; r < ROW; r++) {
			for(int c = 0; c < COL; c++) {
				if(clickedObj.equals(board[r][c])) {
					inputValid = ttt.makeMove(r, c, currentMove);
				}
			}
		}
		
		if (inputValid) {
			if (ttt.hasWon(currentMove)) {
				ShowGameOver(currentMove+" WON");
				ChangeTurns();
			} else if(ttt.isDraw()) {
				ShowGameOver("Draw");
			} else {
				ChangeTurns();
				currentTurn.setLabel(currentMove+" turn");
				ttt.ComputerMove(currentMove);
				if (ttt.hasWon(currentMove)) {
					ShowGameOver(currentMove+" WON");
					ChangeTurns();
				} else if(ttt.isDraw()) {
					ShowGameOver("Draw");
				} else {
					ChangeTurns();
				}
			}
			SetBoard();
			gameOver.sendToFront();
		}		
	}
	
	private void ChangeTurns() {
		if(currentMove.equals("X") ) {
			currentMove = "O";
		} else {
			currentMove = "X";
		}
	}
	
	public void ResetGame() {
		ttt = null;
		ttt = new TTT(ROW, COL);
		gameOver.setVisible(false);
		SetBoard();
		currentTurn.setLabel(currentMove+" turn");
	}
	
	public void run() {
		add(currentTurn, c.getX()-currentTurn.getWidth()/2, 20);
		SetBoard();
	}
	
}