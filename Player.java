/*
 * Assignment 4
 * Written by: Anthony Chraim 40091014
 * For COMP 248 Section W - Winter 2019
 */

//This class was made by me, Anthony Chraim, on April 6th, 2019.

//Start of the class

public class Player {

	//declaring variables
	private String name;
	private char[][] board;
	private boolean[][] turned;
	public static final int ROWS = 3, COLUMNS =3;
	
	//default consutructor
	public Player() {
		board = new char[ROWS][COLUMNS];
		turned = new boolean[ROWS][COLUMNS];
	}
	
	//contruction that takes in a String
	public Player(String name) {
		this.name = name;
		board = new char[ROWS][COLUMNS];
		turned = new boolean[ROWS][COLUMNS];
		
	}
	// declare setters (mutator)
	public void setName (String name) {
		this.name = name;
	}
	// declare getters (accessor)
	public char[][] getBoard(){
		return this.board;
	}
	public boolean [][] getTurned(){
		return this.turned;
	}
	public String getName() {
		return this.name;
	}
	
	//returning the card at a certain position on the board
	public char cardAt(int row, int column) {
		return board[row][column];
	}

	//flipping the card at a certain position on the board
	public boolean flip(int row, int column) {
		if(this.turned[row][column])
			return false;
		else {
			this.turned[row][column] = true;
			return true;
		}
	}
	
	//replacing a card on the board with another card 
	public void setTo (int row, int column, char card) {
		this.board[row][column] = card;
	}
	
	//verifying if a certain card on the board is turned
	public boolean isTurned(int row, int column) {
		return this.turned [row][column];
	}
	
	//truning a card at a certain position on the board
	public void turn (int row, int column) {
		turned[row][column] = true;
	}
	
	//verifying if all the cards on the board are turned
	public boolean allTurned() {
		boolean allTurned = true;
		for(int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (turned[i][j] == false)
					allTurned = false;
			}
		}
		return allTurned;
	}
	
	//calculation points of a player using the rules
	public int calculatePts () {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				board[i][0] = '0'; 
				board[i][1] = '0'; 
				board[i][2] = '0';
			}
			if(board[0][i] == board [1][i] && board[1][i] == board[2][i]) {
				board[0][i] = '0';
				board[1][i] = '0';
				board[2][i] = '0';
			}
		}	
		if(board[0][0] == board [1][1] && board[1][1] == board[2][2]) {
			board[0][0] = '0';
			board[1][1] = '0';
			board[2][2] = '0';
		}
		if(board[2][0] == board [1][1] && board[1][1] == board[0][2]) {
			board[2][0] = '0';
			board[1][1] = '0';
			board[0][2] = '0';
		}
		int total = 0;
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++) {
				char card;
				card = (board[j][i]);
				switch (card) {
				case 'A':
					total += 1;
					break;
				case '1':
					total += 1;
					break;
				case '2':
					total += 2;
					break;
				case '3':
					total += 3;
					break;
				case '4':
					total += 4;
					break;
				case '5':
					total += 5;
					break;
				case '6':
					total += 6;
					break;
				case '7':
					total += 7;
					break;
				case '8':
					total += 8;
					break;
				case '9':
					total += 9;
					break;
				case 'T':
					total += 10;
					break;
				case 'J':
					total += 10;
					break;
				case 'Q':
					total += 10;
					break;
				case 'K':
					total += 0;
					break;
				case '?':
					total += -5;
					break;
				case '0':
					total += 0;
					break;
				}
			}	
		return total;
	}
		
	//displating the board of a player
	public void display() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	//displaying the board of both players
	public void displayBoard(Player player) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (isTurned(i, j))
					System.out.print(board[i][j] + "\t");
				else
					System.out.print("*\t");
			}
			System.out.println();
		}
	}
//end of the class
}
