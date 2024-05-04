package packageConnectN;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;


/**
 * Title: ConnectNFrame
 * 
 * Description: Represents the graphical user interface for the Connect-N game.
 * This class handles the display of the game board, user input, and game logic.
 * It provides functionalities such as starting a new game, loading a saved
 * game, saving the current game state, undoing moves, and quitting the game.
 * 
 * @author Miguel Rivest
 */

public class ConnectNFrame extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlGamePanel;
	private JMenuBar menu;
	private JMenu fileOptions;
	private JMenuItem itemSave;
	private JMenuItem itemLoad;
	private JMenuItem itemNew;
	private JMenuItem itemExit;
	private JMenu moveOptions;
	private JMenuItem itemUndo;
	private JMenu infoOptions;
	private JMenuItem itemHelp;
	private JMenuItem itemAbout;
	ConnectNGame game;
	private JLabel lblConnectNGameTitle;
	private JLabel lblCurrentPlayer;
	private JLabel lblPlayerNames;
	private JButton[] buttonArray;
	ImageIcon icon;
	private boolean buttonArrayLoaded = false;
	private char[][] boardArray;
	private boolean isWin = false;
	private Clip clip;
	private Clip winClip;
	private Clip tieClip;

	/**
	 * Launches the Connect-N game GUI.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectNFrame frame = new ConnectNFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame for the Connect-N game GUI
	 */
	public ConnectNFrame() {
		game = new ConnectNGame();
		setTitle("Connect-N Game");
		setBounds(100, 100, 633, 595);
		contentPane = new JPanel();
		menu = new JMenuBar();
		fileOptions = new JMenu("File");
		itemSave = new JMenuItem("Save");
		itemLoad = new JMenuItem("Load");
		itemNew = new JMenuItem("New Game");
		itemExit = new JMenuItem("Exit Game");
		moveOptions = new JMenu("Move");
		itemUndo = new JMenuItem("Undo");
		infoOptions = new JMenu("Info");
		itemHelp = new JMenuItem("Help");
		itemAbout = new JMenuItem("About");
		itemLoad.addActionListener(this);
		itemExit.addActionListener(this);
		itemNew.addActionListener(this);
		itemSave.addActionListener(this);
		itemUndo.addActionListener(this);
		itemHelp.addActionListener(this);
		itemAbout.addActionListener(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		fileOptions.add(itemSave);
		fileOptions.add(itemLoad);
		fileOptions.add(itemNew);
		fileOptions.add(itemExit);
		moveOptions.add(itemUndo);
		infoOptions.add(itemHelp);
		infoOptions.add(itemAbout);
		menu.add(fileOptions);
		menu.add(moveOptions);
		menu.add(infoOptions);
		setJMenuBar(menu);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 583, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 458, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblConnectNGameTitle = new JLabel("");
		lblConnectNGameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnectNGameTitle.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 22));
		GridBagConstraints gbc_lblConnectNGameTitle = new GridBagConstraints();
		gbc_lblConnectNGameTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblConnectNGameTitle.gridx = 0;
		gbc_lblConnectNGameTitle.gridy = 0;
		contentPane.add(lblConnectNGameTitle, gbc_lblConnectNGameTitle);

		lblPlayerNames = new JLabel("");
		lblPlayerNames.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPlayerNames = new GridBagConstraints();
		gbc_lblPlayerNames.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerNames.gridx = 0;
		gbc_lblPlayerNames.gridy = 1;
		contentPane.add(lblPlayerNames, gbc_lblPlayerNames);

		lblCurrentPlayer = new JLabel("");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 2;
		contentPane.add(lblCurrentPlayer, gbc_lblCurrentPlayer);
		pnlGamePanel = new JPanel();
		GridBagConstraints gbc_pnlGamePanel = new GridBagConstraints();
		gbc_pnlGamePanel.fill = GridBagConstraints.BOTH;
		gbc_pnlGamePanel.gridx = 0;
		gbc_pnlGamePanel.gridy = 3;
		contentPane.add(pnlGamePanel, gbc_pnlGamePanel);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		icon = new ImageIcon("media/icon.png");
		this.setIconImage(icon.getImage());
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("media/coin-drop.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File("media/win.wav"));
			winClip = AudioSystem.getClip();
			winClip.open(audioInputStream);
			audioInputStream = AudioSystem.getAudioInputStream(new File("media/tie.wav"));
			tieClip = AudioSystem.getClip();
			tieClip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			JOptionPane.showMessageDialog(this, "There was a problem initializing the sound files.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "There was a problem initializing the sound files.");
		} catch (LineUnavailableException e) {
			JOptionPane.showMessageDialog(this, "There was a problem initializing the sound files.");
		}

	}

	public void loadGame() {
		try {
			if (game.validateGameSave()) {
				game.loadGame();
				displayBoard(game);
				lblConnectNGameTitle.setText("Connect-" + game.getNumToWin() + " Game");
				lblPlayerNames.setText(game.getPlayerOneName() + " VS " + game.getPlayerTwoName());
			} else {
				JOptionPane.showMessageDialog(this, "Error: Saved game file is corrupted. Please start a new game.");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,
					"Error: could not read from the game file. Please try starting a new game now.");
		}
	} // loadGame()

	public void displayBoard(ConnectNGame game) {

		pnlGamePanel.removeAll();
		int rows = game.getRows();
		int columns = game.getColumns();
		buttonArray = new JButton[columns];
		boardArray = game.getBoard();
		// Set the grid to accommodate the imported board
		pnlGamePanel.setLayout(new GridLayout(rows + 1, columns));
		// Fill the button array with new JButton objects
		for (int i = 0; i < buttonArray.length; i++) {
			buttonArray[i] = new JButton("" + (i + 1));
			buttonArray[i].addActionListener(this);
		}
		// Add each button from the array to the panel
		for (int button = 0; button < buttonArray.length; button++) {
			pnlGamePanel.add(buttonArray[button]);
		}
		// Add the game board array to the panel
		for (int row = 0; row < boardArray.length; row++) {
			for (int col = 0; col < boardArray[row].length; col++) {
				JLabel label = new JLabel("");
				if (boardArray[row][col] == 'R') {
					label.setOpaque(true);
					label.setBackground(Color.RED);
				} else if (boardArray[row][col] == 'Y') {
					label.setOpaque(true);
					label.setBackground(Color.YELLOW);
				}
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				pnlGamePanel.add(label);
			}
		}
		if (game.getCurrentPlay() % 2 != 0) {
			lblCurrentPlayer.setText("It's " + game.getPlayerOneName() + "'s Turn! Choose a column!");
		} else {
			lblCurrentPlayer.setText("It's " + game.getPlayerTwoName() + "'s Turn! Choose a column!");
		}
		lblConnectNGameTitle.setText("Connect-" + game.getNumToWin() + " Game");
		lblPlayerNames.setText(game.getPlayerOneName() + " VS " + game.getPlayerTwoName());
		pnlGamePanel.revalidate();
		buttonArrayLoaded = true;
	} // displayBoard(ConnectNGame game)

	public void clearBoard() {
		game = new ConnectNGame();
		lblConnectNGameTitle.setText("");
		lblPlayerNames.setText("");
		lblCurrentPlayer.setText("");
		pnlGamePanel.removeAll();
		pnlGamePanel.revalidate();
		pnlGamePanel.repaint();
		contentPane.revalidate();
		contentPane.repaint();
	} // clearBoard()

	public boolean checkWin() {
		boolean isWin = false;
		if (game.checkHorizontal() || game.checkVertical() || game.checkLeftToRight() || game.checkRightToLeft()) {
			isWin = true;
		}
		return isWin;
	} // checkWin()

	public void saveGame() {
		try {
			game.save();
			JOptionPane.showMessageDialog(this, "Game saved successfully.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Error while writing game data to save file! Game will not be saved.");
		}
	} // saveGame()

	public void setGameObject(ConnectNGame importedGame) {
		game = importedGame;
	} // setGameObject()

	public void newGame() {
		ConnectNUserInputFrame inputFrame = new ConnectNUserInputFrame(game, this);
		inputFrame.setVisible(true);
		inputFrame.setLocationRelativeTo(this);
		inputFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if (game.getNumToWin() != 0) {
			displayBoard(game);
		}
	} // newGame()

	public void refreshBoard() {
		displayBoard(game);
	} // refreshBoard()

	public void quitGame() {
		if (!(game.getCurrentPlay() == 1)) {
		int response = JOptionPane.showConfirmDialog(this,
				"Are you sure you would like to quit? All unsaved progress will be lost.", "Confirm",
				JOptionPane.YES_NO_OPTION);
		if (response == 0) {
			System.exit(0);
		}
		} else {
			System.exit(0);
		}
	} // quitGame()

	@Override
	public void actionPerformed(ActionEvent e) {
		if (buttonArrayLoaded) {
			for (int i = 0; i < buttonArray.length; i++) {
				if (e.getSource() == buttonArray[i]) {
					if (game.validateMove(i + 1)) {
						game.play(i + 1);
						clip.stop();
						clip.setFramePosition(0);
						clip.start();
						displayBoard(game);
						if (checkWin()) {
							if (game.getCurrentPlay() % 2 != 0) {
								winClip.setFramePosition(0);
								winClip.start();
								JOptionPane.showMessageDialog(this,
										"Congratulations " + game.getPlayerOneName() + ", You won the game!");
							} else {
								winClip.setFramePosition(0);
								winClip.start();
								JOptionPane.showMessageDialog(this,
										"Congratulations " + game.getPlayerTwoName() + ", You won the game!");
							}
							game = new ConnectNGame();
							isWin = true;
							clearBoard();
						} else {
							isWin = false;
						}
						if (!isWin) {
							game.incrementCurrentPlay();
							displayBoard(game);
						}
						if (game.checkDraw() && !isWin) {
							tieClip.setFramePosition(0);
							tieClip.start();
							JOptionPane.showMessageDialog(this, "Shucks! It's a draw... No one wins.");
							game = new ConnectNGame();
							clearBoard();
						}
					} else {
						JOptionPane.showMessageDialog(this, "Invalid Move: Please select another column.");
					}
				} // if a button from the buttonArray is pressed.
			}
		}
		if (e.getSource() == itemLoad) { // Load option chosen
			loadGame();
		} else if (e.getSource() == itemExit) { // Exit option chosen
			quitGame();
		} else if (e.getSource() == itemSave) { // Save option chosen
			saveGame();
		} else if (e.getSource() == itemNew) { // New Game option chosen
			newGame();
		} else if (e.getSource() == itemUndo) { // Undo option chosen
			if (game.validateUndo()) {
				game.undo();
				game.decrementCurrentPlay();
				displayBoard(game);
			} else {
				JOptionPane.showMessageDialog(this, "Sorry, you can't undo right now.");
			}
		} else if (e.getSource() == itemHelp) { // Help option chosen
			ConnectNHelp helpWindow = new ConnectNHelp();
			helpWindow.setSize(400, 500);
			helpWindow.setLocationRelativeTo(this);
			helpWindow.setIconImage(icon.getImage());
			helpWindow.setVisible(true);
			helpWindow.setResizable(false);
			helpWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} else if (e.getSource() == itemAbout) { // About option chosen
			ConnectNInfo infoFrame = new ConnectNInfo();
			infoFrame.setSize(450, 200);
			infoFrame.setLocationRelativeTo(this);
			infoFrame.setIconImage(icon.getImage());
			infoFrame.setResizable(false);
			infoFrame.setVisible(true);
			infoFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	} // actionPerformed(ActionEvent e)

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		quitGame();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

} // ConnectNFrame Class
