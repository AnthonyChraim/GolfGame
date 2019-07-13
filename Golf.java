/*
 * Assignment 3
 * Written by: Anthony Chraim 40091014
 * For COMP 248 Section W - Winter 2019
 */

//This program was made by me, Anthony Chraim, on April 6th, 2019.
//The purpose of this program is to create a card game

//Start of the program

import java.util.Scanner;
public class Golf {
	
	public static void main(String[] args) {
		
		
		DeckAndDiscard deck = new DeckAndDiscard();	//declaring and initializing deck as an istance of the class DeckAndDiscard
		Player currentPlayer, player1, player2;	//declaring these three instances of the class Player without initializing them yet
		Scanner keyboard = new Scanner(System.in);	//declaring Scanner
		String nameP1, nameP2;	//declaring player names
		int card1Row, card1Col, card2Row, card2Col;	//declaring card positions
		char discardCard;	//declaring the first card in the discardPile
		int newOrDisc, flippedOrNew, replaceOrToss;	//declaring player input for his turn
		char temp;	//declaring temporrary character in order to substitute cards
		int row, column;	//card positions for the turns arfter the first one
		int numPlayers = 2;	// declaring numbers of players
		int totalPtsP1, totalPtsP2;	//declaring total of point of each players
		char newCard;	//declaring the top card of the deck as a character
		
		//Welcome banner
		System.out.println("-------****-------****-------****-------****-----****-----\n\n" + 
				" \t\tWelcome to my Card Golf Game!\n\n" + 
				"-------****-------****-------****-------****-----****-----\n");
		System.out.println("To win this game you need some luck with the cards and a bit of strategy.\n" + 
				"Just like the outdoor game of golf, the card game known as Golf has a goal of keeping\n" + 
				"the score as low as possible.\n" + 
				"Okay .. Let's start the game! May the best golfer win!!!\n\n");

		//initializing the names of player1 and player2
		System.out.println("What is the name of 1st player? ");
		nameP1 = keyboard.next();
		player1 = new Player(nameP1);
		System.out.println("What is the name of 2nd player? ");
		nameP2 = keyboard.next();
		player2 = new Player(nameP2);
		
		//suffling the deck
		deck.Shuffle(deck.getDeck());
		
		//passing 9 cards to each player and putting the next one on the discardPile
		passCards (player1, player2, deck);
		discardCard = deck.pickACard();
		deck.discard(discardCard);
		
		//each players chooses 2 cards he wishes to turn over
		for(int i = 0; i < numPlayers; i++) {
			if(i == 0) 
				currentPlayer = player1;
			else
				currentPlayer= player2;		
			System.out.println(currentPlayer.getName() + ", time to decide which 2 cards you want to turn over! ");
			
			do {
				System.out.print("What is the first card you want to flip? (row, column) ");
				card1Row = keyboard.nextInt();
				card1Col = keyboard.nextInt();
				if(card1Row > 2 || card1Row < 0 || card1Col > 2 || card1Col < 0)	//cards must be between 0 and 2 inclusively
					System.out.println("row and column must be between 0 and 2 inclusively\n");
			} while(card1Row > 2 || card1Row < 0 || card1Col > 2 || card1Col < 0);
			currentPlayer.turn(card1Row, card1Col);
			
			do {
				do {
					System.out.print("What is the second card you want to flip? (row, column) ");
					card2Row = keyboard.nextInt();
					card2Col = keyboard.nextInt();
					if(card2Row > 2 || card2Row < 0 || card2Col > 2 || card2Col < 0)
						System.out.println("row and column must be between 0 and 2 inclusively\n");
				} while(card2Row > 2 || card2Row < 0 || card2Col > 2 || card2Col < 0);	//cards must be between 0 and 2 inclusively
				currentPlayer.turn(card2Row, card2Col);
				System.out.println();
				if (card1Row == card2Row && card1Col == card2Col)
					System.out.println("Please select 2 different cards");
			}while (card1Row == card2Row && card1Col == card2Col);	//can not flip over the same card twice
		}
		
		// the player enters his next move deciding which cards he wants to discard, turn, or substitude
		//player1 and player2 alternate after every turn
		do {
			for(int i = 0; i < numPlayers; i++) {
				//verifying if player one has turned over all his cards during the past turn
 				if(player1.allTurned())
					break;
				if (i == 0)
					currentPlayer = player1;
				else
					currentPlayer = player2;
				System.out.println("Discard pile has " + discardCard + "\n");
				System.out.println(currentPlayer.getName() +"'s turn: ");
				System.out.println("--------------------------------");
				System.out.println("Here is your board ");
				currentPlayer.displayBoard(currentPlayer);
				do {
				System.out.print("Do you want the card on the discard pile (0) or a new card (1) ");
				newOrDisc = keyboard.nextInt();
				if(!(newOrDisc == 0 || newOrDisc ==1))
					System.out.println("You must enter 0 or 1");
				} while (!(newOrDisc == 0 || newOrDisc == 1));	//user input must be 0 or 1
				
				if (newOrDisc == 0) {
					do {
					System.out.print(currentPlayer.getName() + ", do you want to replace a flipped card (0), or a new card (1)? ");
					flippedOrNew = keyboard.nextInt();
					if (!(flippedOrNew == 0 || flippedOrNew ==1))
						System.out.println("You must enter 0 or 1");
					}while (!(flippedOrNew == 0 || flippedOrNew ==1));	//user input must be 0 or 1
					
					if (flippedOrNew == 0) {
						do {
						do {
						System.out.print("Which flipped card do you want to replace? (row, column) ");
						row = keyboard.nextInt();
						column = keyboard.nextInt();
						if(row > 2 || row < 0 || column > 2 || column < 0)
							System.out.println("row and column must be between 0 and 2 inclusively");
						}while(row > 2 || row < 0 || column > 2 || column < 0);	//cards must be between 0 and 2 inclusively
						
						if(!(currentPlayer.isTurned(row, column)))
							System.out.println("This card has not already been flipped");
						}while (!(currentPlayer.isTurned(row, column)));	//can not flip the same card twice
						
					}
					else {
						do {
						do {
						System.out.print("Which non-flipped card do you want to replace? (row, column) ");
						row = keyboard.nextInt();
						column = keyboard.nextInt();
						if(row > 2 || row < 0 || column > 2 || column < 0)
							System.out.println("row and column must be between 0 and 2 inclusively");
						}while(row > 2 || row < 0 || column > 2 || column < 0);	//cards must be between 0 and 2 inclusively
						currentPlayer.flip(row, column);
						if(currentPlayer.isTurned(row, column))
							System.out.println("This card has already been flipped");
						}while(currentPlayer.isTurned(row, column));	//can not flip the same card twice
					}
					//substituting the discard card with a card on the player's board
					temp = discardCard;
					discardCard = currentPlayer.cardAt(row, column);
					currentPlayer.setTo(row, column, temp);
					deck.discard(discardCard);
				}	
				
				else {
					newCard = deck.pickACard();
					System.out.println("The new card you are playing is " + newCard + "\n");
					do {
					System.out.print("Replace the card (0), or toss it (1)? ");
					replaceOrToss = keyboard.nextInt();
					if (!(replaceOrToss == 0 || replaceOrToss ==1))
						System.out.println("you must enter 0 or 1");
					} while (!(replaceOrToss == 0 || replaceOrToss ==1));	//user input must be 0 or 1
					
					if(replaceOrToss == 0) {
						do {
						System.out.print(currentPlayer.getName() + ", do you want to replace a flipped card(0) or a new card (1)? ");
						flippedOrNew = keyboard.nextInt();
						if(!(flippedOrNew == 0 || flippedOrNew ==1))
							System.out.println("you must enter 0 or 1");
						}while(!(flippedOrNew == 0 || flippedOrNew ==1));	// user input must be zero or one
						
						if (flippedOrNew == 0) {
							do {
							do {
								System.out.print("Which flipped card do you want to replace? (row, column) ");
								row = keyboard.nextInt();
								column = keyboard.nextInt();
								if(row > 2 || row < 0 || column > 2 || column < 0)
									System.out.println("row and column must be between 0 and 2 inclusively");
							}while(row > 2 || row < 0 || column < 0 || column> 2);	//cards must be between 0 and 2 inclusively
							
							if (!(currentPlayer.isTurned(row, column)))
								System.out.println("This card has not already been flipped");
							}while(!(currentPlayer.isTurned(row, column)));	//can't turn the same card twice
							
							//replacing the card on the bord with the card on the top of the deck, and discarding it
							discardCard = currentPlayer.cardAt(row, column);
							deck.discard(discardCard);
							currentPlayer.setTo(row, column, newCard);
						}
						else {
							do {
							do {
								System.out.print("Which non-flipped card do you want to replace? (row, column) ");
								row = keyboard.nextInt();
								column = keyboard.nextInt();
								if(row > 2 || row < 0 || column > 2 || column < 0)
									System.out.println("row and column must be between 0 and 2 inclusively");
							}while(row > 2 || row < 0 || column < 0 || column> 2);	//cards must be between 0 and 2 inclusively
							if(currentPlayer.isTurned(row, column))
								System.out.println("This card has already been flipped");
							}while(currentPlayer.isTurned(row, column));	//can't  turn the same card twice
							currentPlayer.flip(row, column);
							currentPlayer.displayBoard(currentPlayer);
							
							do {
							System.out.print("replace this card (0) or toss(1)?");
							replaceOrToss = keyboard.nextInt();
							if(!(replaceOrToss == 0 || replaceOrToss == 1))
								System.out.println("you must enter 0 or 1");
							}while (!(replaceOrToss == 0 || replaceOrToss == 1));	//user input must be zero or one
							if (replaceOrToss == 0) {
								//replacing the card on the player's board with a new card and discarding it
								discardCard = currentPlayer.cardAt(row, column);
								deck.discard(discardCard);
								currentPlayer.setTo(row, column, newCard);							}
							else {
								//discarding the card on top of the deck without using it
								discardCard = newCard;
								deck.discard(discardCard);
							}
						}
					}
					else {
						//discarding the card on top of the deck without using it
						discardCard = newCard;
						deck.discard(discardCard);
					}
				}		
			}
		}while (!(player1.allTurned()) && !(player2.allTurned()) && !deck.deckOver());	//repeating these steps until one players turns over all his cards or if the deck is empty
		
		if(player1.allTurned())
			System.out.println(player1.getName() + " has turned over all his cards.");
		else if(player2.allTurned())
			System.out.println(player2.getName() + " has turned over all his cards.");
		else
			System.out.println("No more cards in the deck.");
		System.out.println("Time to calculate points! here are your bords with all cards turned over.");
		
		//displaying the boards of both players
		deck.displayBothBoard(player1, player2);
		System.out.println();
		
		//counting the points of each players
		totalPtsP1 = player1.calculatePts();
		totalPtsP2 = player2.calculatePts();
		System.out.println("Final results: ");
		System.out.println(player1.getName() + " scored " + totalPtsP1);
		System.out.println(player2.getName() + " scored " + totalPtsP2);
		System.out.println();
		if (totalPtsP1 == totalPtsP2)
			System.out.println("IT IS A TIE!");
		else
			System.out.println("CONGRATULATIONS!!!!! The winner is " + (totalPtsP1 < totalPtsP2 ? player1.getName():player2.getName()));
		System.out.println("--------------------------------------\n");
		System.out.println("Just for information sake, here are the cards remaining in the deck: \n");
		deck.displayDeck();	//displaying the rest of the deck
		System.out.println("\nHere are the cards in the discard pile: \n");
		deck.displayDiscardPile();	//displaying all the cards that have been discarded
		
		keyboard.close();	//closing keyboard object
		
		//end of the program
	}

	//distributing 9 cards to each players
	public static void passCards(Player player1,Player player2, DeckAndDiscard deck) {
		for (int i = 0; i < Player.COLUMNS; i++) {
			for (int j = 0; j < Player.ROWS; j++) {
				player1.getBoard()[i][j] = deck.pickACard(); 
			}
		}
		
		for (int i = 0; i < Player.COLUMNS; i++) {
			for (int j = 0; j < Player.ROWS; j++) {
				player2.getBoard()[i][j] = deck.pickACard(); 
			}
		}

		for (int i = 0; i < Player.COLUMNS; i++) {
			for (int j = 0; j < Player.ROWS; j++) {
				player1.getTurned() [i][j] = false; 
			}
		}
	
		for (int i = 0; i < Player.COLUMNS; i++) {
			for (int j = 0; j < Player.ROWS; j++) {
				player2.getTurned() [i][j] = false; 
			}
		}
	}
}
