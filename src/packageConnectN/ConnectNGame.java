package packageConnectN;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Title: ConnectNGame
 * 
 * Description: Represents the logic and data management for the Connect-N game.
 * This class handles game initialization, loading, saving, player moves,
 * validation, and win/draw conditions.
 *
 * @author Miguel Rivest
 */

public class ConnectNGame {
	private char[][] board;
	private int rows;
	private int columns;
	private int numToWin;
	private String playerOneName;
	private String playerTwoName;
	private int currentPlay;
	private int lastColumnPlayed;
	private int lastRowPlayed;
	private boolean canUndo;

	// Constructors

	/**
	 * Default constructor initializing game parameters to default values.
	 */

	ConnectNGame() {
		canUndo = false;
		board = new char[0][0];
		rows = 0;
		columns = 0;
		numToWin = 0;
		playerOneName = "Unknown";
		playerTwoName = "Unknown";
		currentPlay = 1;
		lastColumnPlayed = 0;
	} // ConnectNGame()

	/**
	 * Parameterized constructor initializing game parameters based on provided
	 * values.
	 * 
	 * @param p1          Player one's name
	 * @param p2          Player two's name
	 * @param row         Number of rows in the game board
	 * @param col         Number of columns in the game board
	 * @param numberToWin Number of tokens needed in a row to win
	 * @param currentPlay Current player's turn
	 */

	ConnectNGame(String p1, String p2, int row, int col, int numberToWin, int currentPlay) {
		// Initialization of instance variables with provided values
		rows = row;
		columns = col;
		playerOneName = p1;
		playerTwoName = p2;
		numToWin = numberToWin;
		canUndo = false;
		this.currentPlay = currentPlay;
		lastColumnPlayed = 0;
		board = new char[row][col];
	} // ConnectNGame(String p1, String p2, int row, int col, int numberToWin)

	// Getters and Setters for instance variables

	public int getRows() {
		return rows;
	} // getRows()

	public void setRows(int rows) {
		this.rows = rows;
		setBoardDimensions();
	} // setRows(int rows)

	public int getColumns() {
		return columns;
	} // getColumns()

	public void setColumns(int columns) {
		this.columns = columns;
		setBoardDimensions();
	} // setColumns(int columns)

	public char[][] getBoard() {
		return board;
	} // getBoard()

	// To set the dimensions of the board
	// !! WARNING - WILL ERASE ANY DATA STORED IN THE BOARD !!
	private void setBoardDimensions() {
		board = new char[rows][columns];
	} // setBoardDimensions(int row, int col)

	public int getNumToWin() {
		return numToWin;
	} // getNumToWin()

	public void setNumToWin(int numToWin) {
		this.numToWin = numToWin;
	} // setNumToWin(int numToWin)

	public String getPlayerOneName() {
		return playerOneName;
	} // getPlayerOneName()

	public void setPlayerOneName(String playerOneName) {
		this.playerOneName = playerOneName;
	} // setPlayerOneName(String playerOneName)

	public String getPlayerTwoName() {
		return playerTwoName;
	} // getPlayerTwoName()

	public void setPlayerTwoName(String playerTwoName) {
		this.playerTwoName = playerTwoName;
	} // setPlayerTwoName(String playerTwoName)

	public int getLastRowPlayed() {
		return lastRowPlayed;
	} // getLastRowPlayed()

	public int getCurrentPlay() {
		return currentPlay;
	} // getCurrentPlay()

	public void incrementCurrentPlay() {
		currentPlay++;
	} // incrementCurrentPlay()

	public void decrementCurrentPlay() {
		currentPlay++;
	} // decrementCurrentPlay()

	public int getLastColumnPlayed() {
		return lastColumnPlayed;
	} // getLastColumnPlayed()

	public void setLastColumnPlayed(int lastColumnPlayed) {
		this.lastColumnPlayed = lastColumnPlayed;
	} // setLastColumnPlayed()

	public boolean isCanUndo() {
		return canUndo;
	} // isCanUndo()

	public void setCanUndo(boolean canUndo) {
		this.canUndo = canUndo;
	} // setCanUndo(boolean canUndo)

	/**
	 * The following section contains all the methods used for the logic of the
	 * Connect-N Game
	 */

	// Initialize the board
	public int initBoard() {
		if (rows == 0 || columns == 0) {
			return -1; // Rows or columns not initialized
		} else {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					board[i][j] = 'E';
				} // for each column
			} // for each row
			return 0;
		}
	} // initBoard()

	// Loads an existing game file
	public void loadGame() throws IOException {
		File saveFile = new File("currentGame.txt");
		Scanner reader = new Scanner(saveFile);
		setRows(Integer.parseInt(reader.nextLine()));
		setColumns(Integer.parseInt(reader.nextLine()));
		setNumToWin(Integer.parseInt(reader.nextLine()));
		setPlayerOneName(reader.nextLine());
		setPlayerTwoName(reader.nextLine());
		currentPlay = Integer.parseInt(reader.nextLine());
		board = new char[getRows()][getColumns()];
		for (int row = 0; row < board.length; row++) {
			StringTokenizer token = new StringTokenizer(reader.nextLine(), "~");
			for (int col = 0; col < getColumns(); col++) {
				if (token.hasMoreTokens()) {
					board[row][col] = token.nextToken().charAt(0);
				}
			}
		}
		reader.close();
	} // loadGame() throws FileNotFoundException

	// Play a move
	public void play(int col) {
		boolean isFound = false;
		for (int i = board.length - 1; i >= 0 && !isFound; i--) {
			if (board[i][col - 1] == 'E') {
				if (currentPlay % 2 != 0) {
					board[i][col - 1] = 'Y';
					lastRowPlayed = i;
					isFound = true;
				} else {
					board[i][col - 1] = 'R';
					lastRowPlayed = i;
					isFound = true;
				}
				setLastColumnPlayed(col - 1);
				setCanUndo(true);
			}
		}
	} // play()

	// Validates the player's move
	public boolean validateMove(int col) {
		boolean isFound = false;
		if (col > 0 && col <= board[0].length) {
			for (int i = 0; i < board.length && !isFound; i++) {
				if (board[i][col - 1] == 'E') {
					isFound = true;
				}
			}
			return isFound;
		} else {
			return false;
		}
	} // validateMove()

	// Undo move
	public void undo() {
		board[lastRowPlayed][lastColumnPlayed] = 'E';
		setCanUndo(false);
	} // undo()

	// Checks if an undo is possible
	public boolean validateUndo() {
		return currentPlay > 1 && canUndo;
	} // validateUndo()

	// Quits the game
	public void quit() {
		System.exit(0);
	} // quit()

	// Validates the saved game file, making use of regex patterns.
	public boolean validateGameSave() throws IOException {
		File saveFile = new File("currentGame.txt");
		Scanner reader = new Scanner(saveFile);
		String tempString;
		boolean isValid = false;
		tempString = reader.nextLine();
		if (tempString.matches("[4-9]|1[0-2]")) {
			setRows(Integer.parseInt(tempString));
			tempString = reader.nextLine();
			if (tempString.matches("[4-9]|1[0-2]")) {
				setColumns(Integer.parseInt(tempString));
				tempString = reader.nextLine();
				if (tempString.matches("[3-8]")) {
					setNumToWin(Integer.parseInt(tempString));
					if (validateNumToWin(getNumToWin())) {
						boolean isEnd = false;
						reader.nextLine();
						reader.nextLine();
						tempString = reader.nextLine();
						if (tempString.matches("[1-2]")) {
							for (int row = 1; row <= getRows() && !isEnd; row++) {
								if (reader.hasNextLine()) {
									tempString = reader.nextLine();
								} else {
									isEnd = true;
								}
								StringTokenizer token = new StringTokenizer(tempString, "~");
								if (token.countTokens() == getColumns()) {
									for (int col = 1; col <= getColumns() && !isEnd; col++) {
										if (token.hasMoreTokens()) {
											tempString = token.nextToken();
											isEnd = !(tempString.equalsIgnoreCase("E")
													|| tempString.equalsIgnoreCase("R")
													|| tempString.equalsIgnoreCase("Y"));
										} else {
											isEnd = true;
										}
									}
								} else {
									isEnd = true;
								}
								isValid = !isEnd;
							}
						}
					}
				}
			}
		}
		reader.close();
		return isValid;
	} // validateGameSave()

	// Writes current game data to save file.
	public void save() throws IOException {
		File saveFile = new File("currentGame.txt");
		FileWriter write = new FileWriter(saveFile);
		write.write(getRows() + "\n" + getColumns() + "\n" + getNumToWin() + "\n" + getPlayerOneName() + "\n"
				+ getPlayerTwoName() + "\n" + getCurrentPlayer() + "\n");
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < getColumns(); col++) {
				write.write(board[row][col]);
				if (col < getColumns() - 1) {
					write.write("~");
				}
			}
			write.write("\n");
			write.flush();
		}
		write.close();
	} // save()

	// Method for validating columns
	public boolean validateColumns(int col) {
		return (col >= 4 && col <= 12);
	} // validateColumns()

	// Method for validating rows
	public boolean validateRows(int rows) {
		return (rows >= 4 && rows <= 12);
	} // validateRows()

	// Method to validate the # to Win
	public boolean validateNumToWin(int num) {
		if ((num >= 3 && num <= 8)) {
			return (num <= columns || num <= rows);
		} else {
			return false;
		}
	} // validateNumToWin()

	// Check for horizontal win
	public boolean checkHorizontal() {
		int column = lastColumnPlayed;
		int row = lastRowPlayed;
		int tokenCount = 0;
		boolean isEnd = false;
		if (board[lastRowPlayed][lastColumnPlayed] != 'E') {
			for (int i = column; i < board[row].length && tokenCount <= numToWin && !isEnd; i++) {
				if (board[row][i] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
			isEnd = false;
			for (int i = column; i - 1 >= 0 && tokenCount <= numToWin && !isEnd; i--) {
				if (board[row][i - 1] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
		}
		return tokenCount == numToWin;
	} // checkHorizontal()

	// Returns the current player
	public int getCurrentPlayer() {
		if (currentPlay % 2 != 0) {
			return 1;
		} else {
			return 2;
		}
	} // getCurrentPlayer()

	// Check for Vertical Win
	public boolean checkVertical() {
		int column = lastColumnPlayed;
		int row = lastRowPlayed;
		int tokenCount = 0;
		boolean isEnd = false;
		if (board[lastRowPlayed][lastColumnPlayed] != 'E') {
			for (int i = row; i < board.length && tokenCount <= numToWin && !isEnd; i++) {
				if (board[i][column] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
			isEnd = false;
			for (int i = row; i - 1 >= 0 && tokenCount <= numToWin && !isEnd; i--) {
				if (board[i - 1][column] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
		}
		return tokenCount == numToWin;
	} // checkVertical()

	// Checks for win in the Left to Right Diagonal
	public boolean checkLeftToRight() {
		int column = lastColumnPlayed;
		int row = lastRowPlayed;
		int tokenCount = 0;
		boolean isEnd = false;
		if (board[lastRowPlayed][lastColumnPlayed] != 'E') {
			for (int i = 0; row - i >= 0 && column + i < board[row].length && !isEnd && tokenCount <= numToWin; i++) {
				if (board[row - i][column + i] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
			isEnd = false;
			for (int i = 1; row + i < board.length && column - i >= 0 && !isEnd && tokenCount <= numToWin; i++) {
				if (board[row + i][column - i] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
		}
		return tokenCount == numToWin;
	} // checkLeftToRight()

	// Checks for win in the Left to Right Diagonal
	public boolean checkRightToLeft() {
		int column = lastColumnPlayed;
		int row = lastRowPlayed;
		int tokenCount = 0;
		boolean isEnd = false;
		if (board[lastRowPlayed][lastColumnPlayed] != 'E') {
			for (int i = 0; row + i < board.length && column + i < board[row].length && !isEnd
					&& tokenCount <= numToWin; i++) {
				if (board[row + i][column + i] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
			isEnd = false;
			for (int i = 1; row - i > 0 && column - i > 0 && !isEnd && tokenCount <= numToWin; i++) {
				if (board[row - i][column - i] == board[row][column]) {
					tokenCount++;
				} else {
					isEnd = true;
				}
			}
		}
		return tokenCount == numToWin;
	} // checkRightToLeft

	// Checks for a draw
	public boolean checkDraw() {
		int count = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == 'E') {
					count++;
				}
			}
		}
		return count == 0;
	} // checkDraw()
}
