/*
 * Assignment 4
 * Written by: Anthony Chraim 40091014
 * For COMP 248 Section W - Winter 2019
 */

//This class was made by me, Anthony Chraim, on April 6th, 2019.

//Start of the class

public class DeckAndDiscard {
	
	private char[] deck;
	private	char[] discardPile;
	private int indexDiscard, indexDeck, cardsLeft;
	
	
	//default constructor creates 2 arrays and assign cards
	public DeckAndDiscard() {
		deck = new char[] {'A','A','A','A','2','2','2','2','3','3','3','3','4','4','4','4','5','5','5','5','6','6','6','6','7','7','7','7','8','8','8','8','9','9','9','9','T','T','T','T','J','J','J','J','Q','Q','Q','Q','K','K','K','K','K','?','?'};
		discardPile =  new char [54];
		indexDiscard = 0;
		indexDeck = 0;
		cardsLeft = 54;
	}
	
	//shuffle cards
	public void Shuffle(char[] deck) {
		for (int i = 0; i < 2000; i++) {
			int random1 = (int)(Math.random() * 54);
			int random2 = (int)(Math.random() * 54);
			char temp = deck[random1];
			deck[random1] = deck[random2];
			deck[random2] = temp;
		}
	}
	
	//return a character at the index and increment the index
	public char pickACard() {
		indexDeck++;
		return deck[indexDeck-1];	
	}
	
	//takes a character and adds it to the discard pile and increments
	public void discard(char discardedCard) {
		discardPile[indexDiscard] = discardedCard;
		indexDiscard++;
	}
	
	//print out the remaining of the deck
	public void displayDeck() {
		for (int i = indexDeck; i < deck.length; i++) {
			System.out.print(this.deck[i] + ", ");
		}
		System.out.println();
	}
	
	//print out the remaining of the discard pile
	public void displayDiscardPile() {
		for (int i = 0; i < indexDiscard; i++) {
			System.out.print(discardPile[i] + ", ");
		}
		System.out.println();
	}
	
	//returns the next card in the discard pile
	public char NextDisc (){
		return discardPile[indexDiscard - 1];
	}
	
	// accessor
	public char[] getDeck() {
		return this.deck;
	}
	
	//returns true when the deck is over
	public boolean deckOver() {
		return (this.indexDeck == 54);
	}
	
	//displayer both boards at the end of the game
	public void displayBothBoard(Player player1, Player player2) {
		final int COLUMNS = 3;
		System.out.println(player1.getName() + "\t\t\t\t\t" + player2.getName());
		System.out.println("----------------------------------------------------------\n");
			for(int i = 0; i < COLUMNS; i++) 
				System.out.print(player1.getBoard()[0][i] + "\t");
			System.out.print("\t\t");
			
			for(int i = 0; i < COLUMNS; i++) 
				System.out.print(player2.getBoard()[0][i] + "\t");
			System.out.println();
			
			for(int i = 0; i < COLUMNS; i++) 
				System.out.print(player1.getBoard()[1][i] + "\t");
			System.out.print("\t\t");
			
			for(int i = 0; i < COLUMNS; i++) 
				System.out.print(player2.getBoard()[1][i] + "\t");
			System.out.println();
			
			for(int i = 0; i < COLUMNS; i++) 
				System.out.print(player1.getBoard()[2][i] + "\t");
			System.out.print("\t\t");
			
			for(int i = 0; i < COLUMNS; i++) 
				System.out.print(player2.getBoard()[2][i] + "\t");
			System.out.println();
	}
//end of class
}
