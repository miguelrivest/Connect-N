package packageConnectN;

import java.io.IOException;
import java.util.Scanner;

public class ConnectNInterface {
	public static void main(String[] args) {
		String player1, player2, tempString;
		int rows, cols, numToWin;
		ConnectNGame game;
		boolean inputError = false;
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to my Connect-N Game!");
		System.out.println();
		System.out.print("Would you like to load a saved game? Default is N (Y/n): ");
		tempString = input.nextLine();
		do {
			if (tempString.equalsIgnoreCase("Y")) {
				inputError = false;
				game = new ConnectNGame();
				try {
					if (game.validateGameSave()) {
						try {
							System.out.println();
							System.out.println("Loading game file...");
							System.out.println();
							game.loadGame();
							playGame(game, false);
						} catch (IOException e) {
							System.err
									.println("Error: Error reading from saved file. File non-existent or corrupted.\n");
							System.out.println("Exiting now...");
							System.exit(1);
						}
					} else {
						System.out.println();
						System.err.println("Error: Corrupt save file. Exiting now...");
						System.out.println();
						System.exit(1);
					}
				} catch (IOException e) {
					System.out.println();
					System.err.println("Error: could not read from saved game file.");
					System.out.println("Exiting now...");
					System.exit(1);
				}
			} else {
				inputError = false;
				game = new ConnectNGame();
				System.out.println();
				System.out.print("Player 1's Name: ");
				player1 = input.nextLine();
				game.setPlayerOneName(player1);
				System.out.println();
				System.out.print("Player 2's Name: ");
				player2 = input.nextLine();
				game.setPlayerTwoName(player2);
				System.out.println();
				do {
					System.out.print("Enter # of rows for the game: ");
					rows = Integer.parseInt(input.nextLine());
					System.out.println();
					if (!game.validateRows(rows)) {
						System.out.println();
						System.err.println("Must be a number between 4 - 12! Try again.");
						inputError = true;
					} else {
						game.setRows(rows);
						inputError = false;
					}
				} while (inputError); // Row entry loop
				do {
					System.out.print("Enter # of columns for the game: ");
					cols = Integer.parseInt(input.nextLine());
					System.out.println();
					if (!game.validateColumns(cols)) {
						System.out.println();
						System.err.println("Must be a number between 4 - 12! Try again.");
						inputError = true;
					} else {
						game.setColumns(cols);
						inputError = false;
					}
				} while (inputError); // Column entry loop
				do {
					System.out.println();
					System.out.print("Enter the # of checkers needed for a win: ");
					numToWin = Integer.parseInt(input.nextLine());
					if (!game.validateNumToWin(numToWin)) {
						System.out.println();
						System.err.println(
								"Must meet following conditions:\n- Number must be between 3 - 8\n- Number must be the same size or smaller as row #: "
										+ game.getRows() + " or column #: " + game.getColumns()
										+ "\nPlease try again.");
						inputError = true;
					} else {
						game.setNumToWin(numToWin);
						inputError = false;
					}
				} while (inputError);
				playGame(game, true);
			}
		} while (inputError); // Loop for loading new game or not. (Y/n)
		input.close();
	}

	// Starts the game
	public static void playGame(ConnectNGame game, boolean isNewGame) {
		Scanner input = new Scanner(System.in);
		String tempString;
		boolean isDraw = false;
		boolean inputError = false;
		boolean isGameEnded = false;
		if (isNewGame)
			game.initBoard();
		while (!isGameEnded) {
			displayBoard(game);
			if (game.getCurrentPlayer() == 1) {
				do {
					System.out.println("Options: Columns 1 to " + game.getColumns() + " - Save: S - Quit: Q - Undo: U");
					System.out.print(game.getPlayerOneName() + "'s Turn. Please select a column: ");
					tempString = input.nextLine();
					if (tempString.equalsIgnoreCase("u")) {
						if (game.validateUndo()) {
							game.undo();
							game.decrementCurrentPlay();
						} else {
							System.out.println();
							System.err.println("Error: Undo move not allowed right now.");
							System.out.println();
						}
					} else if (tempString.equalsIgnoreCase("s")) {
						try {
							game.save();
						} catch (IOException e) {
							System.err.println(
									"Error: Error creating or appending to the save file. Game will not be saved.\n");
						}
					} else if (tempString.equalsIgnoreCase("q")) {
						String answer;
						System.out.println();
						System.out.print("Are you sure you want to quit? (Y/n): ");
						answer = input.nextLine();
						if (answer.equalsIgnoreCase("y")) {
							game.quit();
						} else {
							System.out.println();
							System.out.println("Ok, resuming game.");
							System.out.println();
						}
					} else if (tempString.matches("\\d+") && game.validateMove(Integer.parseInt(tempString))) {
						game.play(Integer.parseInt(tempString));
						inputError = false;
					} else {
						System.out.println();
						System.err.println("Error: Invalid input. Please try again");
						System.out.println();
						inputError = true;
					}
				} while (inputError);
			} else {
				do {
					System.out.println("Options: Columns 1 to " + game.getColumns() + " - Save: S - Quit: Q - Undo: U");
					System.out.print(game.getPlayerTwoName() + "'s Turn. Please select a column: ");
					tempString = input.nextLine();
					if (tempString.equalsIgnoreCase("u")) {
						if (game.validateUndo()) {
							game.undo();
							game.decrementCurrentPlay();
						} else {
							System.out.println();
							System.err.println("Error: Undo move not allowed right now.");
							System.out.println();
						}
					} else if (tempString.equalsIgnoreCase("s")) {
						try {
							game.save();
						} catch (IOException e) {
							System.err.println(
									"Error: Error creating or appending to the save file. Game will not be saved.\n");
						}
					} else if (tempString.equalsIgnoreCase("q")) {
						String answer;
						System.out.println();
						System.out.print("Are you sure you want to quit? (Y/n): ");
						answer = input.nextLine();
						if (answer.equalsIgnoreCase("y")) {
							game.quit();
						} else {
							System.out.println();
							System.out.println("Ok, resuming game.");
							System.out.println();
						}
					} else if (tempString.matches("\\d+") && game.validateMove(Integer.parseInt(tempString))) {
						game.play(Integer.parseInt(tempString));
						inputError = false;
					} else {
						System.out.println();
						System.err.println("Error: Invalid input. Please try again");
						System.out.println();
						inputError = true;
					}
				} while (inputError);
			}
			// TODO
			// ADD WIN/DRAW CHECK ABOVE THE INCREMENT AND DISPLAY THE APPROPRIATE WINNER
			if (game.checkHorizontal()) {
				isGameEnded = true;
			} else if (game.checkVertical()) {
				isGameEnded = true;
			} else if (game.checkLeftToRight()) {
				isGameEnded = true;
			} else if (game.checkRightToLeft()) {
				isGameEnded = true;
			} else if (game.checkDraw()) {
				isGameEnded = true;
				isDraw = true;
			}
			if (isDraw) {
				System.out.println();
				System.out.println();
				displayBoard(game);
				System.out.println();
				System.out.println();
				System.out.println("It's a draw! No one wins.");
			} else if (isGameEnded) {
				if (game.getCurrentPlayer() == 1) {
					System.out.println();
					System.out.println();
					displayBoard(game);
					System.out.println();
					System.out.println();
					System.out.println("Congratulations, " + game.getPlayerOneName() + "! You win the game!");
				} else {
					System.out.println();
					System.out.println();
					displayBoard(game);
					System.out.println();
					System.out.println();
					System.out.println("Congratulations, " + game.getPlayerTwoName() + "! You win the game!");
				}
			}
			if (!(tempString.equalsIgnoreCase("u") || tempString.equalsIgnoreCase("q")
					|| tempString.equalsIgnoreCase("s"))) {
				game.incrementCurrentPlay();
			}
		}
		input.close();
	} // playGame(ConnectNGame game, boolean isNewGame)

	// Displays the board in the CLI
	public static void displayBoard(ConnectNGame game) {
		char[][] board = game.getBoard();
		System.out.println();
		System.out.println("Connect-" + game.getNumToWin() + " Game - " + game.getPlayerOneName() + " vs. "
				+ game.getPlayerTwoName());
		System.out.println();
		for (int i = 0; i < board[0].length; i++) {
			System.out.printf("%-4d", i + 1);
		}
		System.out.print("\n\n");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.printf("%-4s", board[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println();
		System.out.println("Board Legend:");
		System.out.println("R = Red\nY = Yellow\nE = Empty");
		System.out.println();
	} // displayBoard()
}
