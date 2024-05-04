package packageConnectN;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

/*
 * Title: ConnectNUserInputFrame
 * Description: This frame serves as an input section for users to enter their new game details.
 * 
 * @author Miguel Rivest
 */

public class ConnectNUserInputFrame extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fldPlayerOneName;
	private JTextField fldPlayerTwoName;
	private JTextField fldRows;
	private JTextField fldColumns;
	private JTextField fldNumToWin;
	private JButton btnConfirm;
	private ConnectNGame game;
	private ConnectNFrame gameFrame;
	private JLabel lblPlayerOneError;
	private JLabel lblPlayerTwoError;
	private JLabel lblRowError;
	private JLabel lblColumnError;
	private JLabel lblNumToWinError;
	private JLabel lblNumToWinError2;

	/**
	 * Create the input frame GUI
	 */
	public ConnectNUserInputFrame() {
		setTitle("Connect-N: New Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 200, -39, 304, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblInputTitle = new JLabel("Enter Game Details");
		lblInputTitle.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 20));
		GridBagConstraints gbc_lblInputTitle = new GridBagConstraints();
		gbc_lblInputTitle.gridwidth = 4;
		gbc_lblInputTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblInputTitle.gridx = 0;
		gbc_lblInputTitle.gridy = 0;
		contentPane.add(lblInputTitle, gbc_lblInputTitle);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);

		JLabel lblPlayerOneName = new JLabel("Player 1:");
		GridBagConstraints gbc_lblPlayerOneName = new GridBagConstraints();
		gbc_lblPlayerOneName.anchor = GridBagConstraints.EAST;
		gbc_lblPlayerOneName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerOneName.gridx = 0;
		gbc_lblPlayerOneName.gridy = 2;
		contentPane.add(lblPlayerOneName, gbc_lblPlayerOneName);

		fldPlayerOneName = new JTextField();
		GridBagConstraints gbc_fldPlayerOneName = new GridBagConstraints();
		gbc_fldPlayerOneName.insets = new Insets(0, 0, 5, 5);
		gbc_fldPlayerOneName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldPlayerOneName.gridx = 1;
		gbc_fldPlayerOneName.gridy = 2;
		contentPane.add(fldPlayerOneName, gbc_fldPlayerOneName);
		fldPlayerOneName.setColumns(10);

		JLabel lblPlayerTwoName = new JLabel("Player 2:");
		GridBagConstraints gbc_lblPlayerTwoName = new GridBagConstraints();
		gbc_lblPlayerTwoName.anchor = GridBagConstraints.EAST;
		gbc_lblPlayerTwoName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerTwoName.gridx = 2;
		gbc_lblPlayerTwoName.gridy = 2;
		contentPane.add(lblPlayerTwoName, gbc_lblPlayerTwoName);

		fldPlayerTwoName = new JTextField();
		GridBagConstraints gbc_fldPlayerTwoName = new GridBagConstraints();
		gbc_fldPlayerTwoName.insets = new Insets(0, 0, 5, 0);
		gbc_fldPlayerTwoName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldPlayerTwoName.gridx = 3;
		gbc_fldPlayerTwoName.gridy = 2;
		contentPane.add(fldPlayerTwoName, gbc_fldPlayerTwoName);
		fldPlayerTwoName.setColumns(10);

		lblPlayerOneError = new JLabel("Cannot leave this field blank");
		lblPlayerOneError.setForeground(Color.RED);
		GridBagConstraints gbc_lblPlayerOneError = new GridBagConstraints();
		gbc_lblPlayerOneError.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerOneError.gridx = 1;
		gbc_lblPlayerOneError.gridy = 3;
		contentPane.add(lblPlayerOneError, gbc_lblPlayerOneError);

		lblPlayerTwoError = new JLabel("Cannot leave this field blank");
		lblPlayerTwoError.setForeground(Color.RED);
		GridBagConstraints gbc_lblPlayerTwoError = new GridBagConstraints();
		gbc_lblPlayerTwoError.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerTwoError.gridx = 3;
		gbc_lblPlayerTwoError.gridy = 3;
		contentPane.add(lblPlayerTwoError, gbc_lblPlayerTwoError);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		contentPane.add(separator_1, gbc_separator_1);

		JLabel lblRows = new JLabel("Rows:");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.anchor = GridBagConstraints.EAST;
		gbc_lblRows.insets = new Insets(0, 0, 5, 5);
		gbc_lblRows.gridx = 0;
		gbc_lblRows.gridy = 5;
		contentPane.add(lblRows, gbc_lblRows);

		fldRows = new JTextField();
		GridBagConstraints gbc_fldRows = new GridBagConstraints();
		gbc_fldRows.insets = new Insets(0, 0, 5, 5);
		gbc_fldRows.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldRows.gridx = 1;
		gbc_fldRows.gridy = 5;
		contentPane.add(fldRows, gbc_fldRows);
		fldRows.setColumns(10);

		JLabel lblColumns = new JLabel("Columns:");
		GridBagConstraints gbc_lblColumns = new GridBagConstraints();
		gbc_lblColumns.anchor = GridBagConstraints.EAST;
		gbc_lblColumns.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumns.gridx = 2;
		gbc_lblColumns.gridy = 5;
		contentPane.add(lblColumns, gbc_lblColumns);

		fldColumns = new JTextField();
		GridBagConstraints gbc_fldColumns = new GridBagConstraints();
		gbc_fldColumns.insets = new Insets(0, 0, 5, 0);
		gbc_fldColumns.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldColumns.gridx = 3;
		gbc_fldColumns.gridy = 5;
		contentPane.add(fldColumns, gbc_fldColumns);
		fldColumns.setColumns(10);

		lblRowError = new JLabel("Must be between 4-12");
		lblRowError.setForeground(Color.RED);
		GridBagConstraints gbc_lblRowError = new GridBagConstraints();
		gbc_lblRowError.insets = new Insets(0, 0, 5, 5);
		gbc_lblRowError.gridx = 1;
		gbc_lblRowError.gridy = 6;
		contentPane.add(lblRowError, gbc_lblRowError);

		lblColumnError = new JLabel("Must be between 4-12");
		lblColumnError.setForeground(Color.RED);
		GridBagConstraints gbc_lblColumnError = new GridBagConstraints();
		gbc_lblColumnError.insets = new Insets(0, 0, 5, 0);
		gbc_lblColumnError.gridx = 3;
		gbc_lblColumnError.gridy = 6;
		contentPane.add(lblColumnError, gbc_lblColumnError);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 4;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 7;
		contentPane.add(separator_2, gbc_separator_2);

		JLabel lblNumToWin = new JLabel("# to Win:");
		GridBagConstraints gbc_lblNumToWin = new GridBagConstraints();
		gbc_lblNumToWin.anchor = GridBagConstraints.EAST;
		gbc_lblNumToWin.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumToWin.gridx = 0;
		gbc_lblNumToWin.gridy = 8;
		contentPane.add(lblNumToWin, gbc_lblNumToWin);

		fldNumToWin = new JTextField();
		GridBagConstraints gbc_fldNumToWin = new GridBagConstraints();
		gbc_fldNumToWin.insets = new Insets(0, 0, 5, 5);
		gbc_fldNumToWin.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldNumToWin.gridx = 1;
		gbc_fldNumToWin.gridy = 8;
		contentPane.add(fldNumToWin, gbc_fldNumToWin);
		fldNumToWin.setColumns(10);

		lblNumToWinError = new JLabel("Must be between 3-8");
		lblNumToWinError.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumToWinError.setForeground(Color.RED);
		GridBagConstraints gbc_lblNumToWinError = new GridBagConstraints();
		gbc_lblNumToWinError.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumToWinError.gridx = 1;
		gbc_lblNumToWinError.gridy = 9;
		contentPane.add(lblNumToWinError, gbc_lblNumToWinError);

		lblNumToWinError2 = new JLabel("Must be smaller than row or column");
		lblNumToWinError2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumToWinError2.setForeground(Color.RED);
		GridBagConstraints gbc_lblNumToWinError2 = new GridBagConstraints();
		gbc_lblNumToWinError2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumToWinError2.gridx = 1;
		gbc_lblNumToWinError2.gridy = 10;
		contentPane.add(lblNumToWinError2, gbc_lblNumToWinError2);

		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(this);
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridwidth = 4;
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 11;
		contentPane.add(btnConfirm, gbc_btnConfirm);

		lblPlayerOneError.setVisible(false);
		lblPlayerTwoError.setVisible(false);
		lblRowError.setVisible(false);
		lblColumnError.setVisible(false);
		lblNumToWinError.setVisible(false);
		lblNumToWinError2.setVisible(false);

		setPreferredSize(new Dimension(800, 450));
	} // ConnectNUserInputFrame()

	/*
	 * Create the input frame GUI, while passing the other frame's instance the the
	 * game instance for the other frame for manipulation.
	 */
	ConnectNUserInputFrame(ConnectNGame importedGame, ConnectNFrame importedFrame) {
		setTitle("Connect-N: New Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 200, -39, 304, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblInputTitle = new JLabel("Enter Game Details");
		lblInputTitle.setFont(new Font("Source Code Pro Semibold", Font.PLAIN, 20));
		GridBagConstraints gbc_lblInputTitle = new GridBagConstraints();
		gbc_lblInputTitle.gridwidth = 4;
		gbc_lblInputTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblInputTitle.gridx = 0;
		gbc_lblInputTitle.gridy = 0;
		contentPane.add(lblInputTitle, gbc_lblInputTitle);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);

		JLabel lblPlayerOneName = new JLabel("Player 1:");
		GridBagConstraints gbc_lblPlayerOneName = new GridBagConstraints();
		gbc_lblPlayerOneName.anchor = GridBagConstraints.EAST;
		gbc_lblPlayerOneName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerOneName.gridx = 0;
		gbc_lblPlayerOneName.gridy = 2;
		contentPane.add(lblPlayerOneName, gbc_lblPlayerOneName);

		fldPlayerOneName = new JTextField();
		GridBagConstraints gbc_fldPlayerOneName = new GridBagConstraints();
		gbc_fldPlayerOneName.insets = new Insets(0, 0, 5, 5);
		gbc_fldPlayerOneName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldPlayerOneName.gridx = 1;
		gbc_fldPlayerOneName.gridy = 2;
		contentPane.add(fldPlayerOneName, gbc_fldPlayerOneName);
		fldPlayerOneName.setColumns(10);

		JLabel lblPlayerTwoName = new JLabel("Player 2:");
		GridBagConstraints gbc_lblPlayerTwoName = new GridBagConstraints();
		gbc_lblPlayerTwoName.anchor = GridBagConstraints.EAST;
		gbc_lblPlayerTwoName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerTwoName.gridx = 2;
		gbc_lblPlayerTwoName.gridy = 2;
		contentPane.add(lblPlayerTwoName, gbc_lblPlayerTwoName);

		fldPlayerTwoName = new JTextField();
		GridBagConstraints gbc_fldPlayerTwoName = new GridBagConstraints();
		gbc_fldPlayerTwoName.insets = new Insets(0, 0, 5, 0);
		gbc_fldPlayerTwoName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldPlayerTwoName.gridx = 3;
		gbc_fldPlayerTwoName.gridy = 2;
		contentPane.add(fldPlayerTwoName, gbc_fldPlayerTwoName);
		fldPlayerTwoName.setColumns(10);

		lblPlayerOneError = new JLabel("Cannot leave this field blank");
		lblPlayerOneError.setForeground(Color.RED);
		GridBagConstraints gbc_lblPlayerOneError = new GridBagConstraints();
		gbc_lblPlayerOneError.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerOneError.gridx = 1;
		gbc_lblPlayerOneError.gridy = 3;
		contentPane.add(lblPlayerOneError, gbc_lblPlayerOneError);

		lblPlayerTwoError = new JLabel("Cannot leave this field blank");
		lblPlayerTwoError.setForeground(Color.RED);
		GridBagConstraints gbc_lblPlayerTwoError = new GridBagConstraints();
		gbc_lblPlayerTwoError.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerTwoError.gridx = 3;
		gbc_lblPlayerTwoError.gridy = 3;
		contentPane.add(lblPlayerTwoError, gbc_lblPlayerTwoError);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 4;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		contentPane.add(separator_1, gbc_separator_1);

		JLabel lblRows = new JLabel("Rows:");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.anchor = GridBagConstraints.EAST;
		gbc_lblRows.insets = new Insets(0, 0, 5, 5);
		gbc_lblRows.gridx = 0;
		gbc_lblRows.gridy = 5;
		contentPane.add(lblRows, gbc_lblRows);

		fldRows = new JTextField();
		GridBagConstraints gbc_fldRows = new GridBagConstraints();
		gbc_fldRows.insets = new Insets(0, 0, 5, 5);
		gbc_fldRows.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldRows.gridx = 1;
		gbc_fldRows.gridy = 5;
		contentPane.add(fldRows, gbc_fldRows);
		fldRows.setColumns(10);

		JLabel lblColumns = new JLabel("Columns:");
		GridBagConstraints gbc_lblColumns = new GridBagConstraints();
		gbc_lblColumns.anchor = GridBagConstraints.EAST;
		gbc_lblColumns.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumns.gridx = 2;
		gbc_lblColumns.gridy = 5;
		contentPane.add(lblColumns, gbc_lblColumns);

		fldColumns = new JTextField();
		GridBagConstraints gbc_fldColumns = new GridBagConstraints();
		gbc_fldColumns.insets = new Insets(0, 0, 5, 0);
		gbc_fldColumns.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldColumns.gridx = 3;
		gbc_fldColumns.gridy = 5;
		contentPane.add(fldColumns, gbc_fldColumns);
		fldColumns.setColumns(10);

		lblRowError = new JLabel("Must be between 4-12");
		lblRowError.setForeground(Color.RED);
		GridBagConstraints gbc_lblRowError = new GridBagConstraints();
		gbc_lblRowError.insets = new Insets(0, 0, 5, 5);
		gbc_lblRowError.gridx = 1;
		gbc_lblRowError.gridy = 6;
		contentPane.add(lblRowError, gbc_lblRowError);

		lblColumnError = new JLabel("Must be between 4-12");
		lblColumnError.setForeground(Color.RED);
		GridBagConstraints gbc_lblColumnError = new GridBagConstraints();
		gbc_lblColumnError.insets = new Insets(0, 0, 5, 0);
		gbc_lblColumnError.gridx = 3;
		gbc_lblColumnError.gridy = 6;
		contentPane.add(lblColumnError, gbc_lblColumnError);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 4;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 7;
		contentPane.add(separator_2, gbc_separator_2);

		JLabel lblNumToWin = new JLabel("# to Win:");
		GridBagConstraints gbc_lblNumToWin = new GridBagConstraints();
		gbc_lblNumToWin.anchor = GridBagConstraints.EAST;
		gbc_lblNumToWin.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumToWin.gridx = 0;
		gbc_lblNumToWin.gridy = 8;
		contentPane.add(lblNumToWin, gbc_lblNumToWin);

		fldNumToWin = new JTextField();
		GridBagConstraints gbc_fldNumToWin = new GridBagConstraints();
		gbc_fldNumToWin.insets = new Insets(0, 0, 5, 5);
		gbc_fldNumToWin.fill = GridBagConstraints.HORIZONTAL;
		gbc_fldNumToWin.gridx = 1;
		gbc_fldNumToWin.gridy = 8;
		contentPane.add(fldNumToWin, gbc_fldNumToWin);
		fldNumToWin.setColumns(10);

		lblNumToWinError = new JLabel("Must be between 3-8");
		lblNumToWinError.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumToWinError.setForeground(Color.RED);
		GridBagConstraints gbc_lblNumToWinError = new GridBagConstraints();
		gbc_lblNumToWinError.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumToWinError.gridx = 1;
		gbc_lblNumToWinError.gridy = 9;
		contentPane.add(lblNumToWinError, gbc_lblNumToWinError);

		lblNumToWinError2 = new JLabel("Must be smaller than row or column");
		lblNumToWinError2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumToWinError2.setForeground(Color.RED);
		GridBagConstraints gbc_lblNumToWinError2 = new GridBagConstraints();
		gbc_lblNumToWinError2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumToWinError2.gridx = 1;
		gbc_lblNumToWinError2.gridy = 10;
		contentPane.add(lblNumToWinError2, gbc_lblNumToWinError2);

		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(this);
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.gridwidth = 4;
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 11;
		contentPane.add(btnConfirm, gbc_btnConfirm);

		lblPlayerOneError.setVisible(false);
		lblPlayerTwoError.setVisible(false);
		lblRowError.setVisible(false);
		lblColumnError.setVisible(false);
		lblNumToWinError.setVisible(false);
		lblNumToWinError2.setVisible(false);

		setPreferredSize(new Dimension(800, 450));

		contentPane.setVisible(true);

		game = importedGame;
		gameFrame = importedFrame;
	} // ConnectNUserInputFrame(ConnectNGame importedGame, ConnectNFrame
		// importedFrame)

	/*
	 * Validates the text fields that are on the JFrame.
	 */
	public boolean validateFields() {
		boolean isValid = true;
		int row, col, numToWin;
		if (!fldRows.getText().matches("^\\d+$")) {
			row = 0;
		} else {
			row = Integer.parseInt(fldRows.getText());
		}
		if (!fldColumns.getText().matches("^\\d+$")) {
			col = 0;
		} else {
			col = Integer.parseInt(fldColumns.getText());
		}
		if (!fldNumToWin.getText().matches("^\\d+$")) {
			numToWin = 0;
		} else {
			numToWin = Integer.parseInt(fldNumToWin.getText());
		}

		if (fldPlayerOneName.getText().equals("")) {
			lblPlayerOneError.setVisible(true);
			isValid = false;
		} else {
			lblPlayerOneError.setVisible(false);
			game.setPlayerOneName(fldPlayerOneName.getText());
		}

		if (fldPlayerTwoName.getText().equals("")) {
			lblPlayerTwoError.setVisible(true);
			isValid = false;
		} else {
			lblPlayerTwoError.setVisible(false);
			game.setPlayerTwoName(fldPlayerTwoName.getText());
		}

		if (!game.validateRows(row)) {
			lblRowError.setVisible(true);
			isValid = false;
		} else {
			lblRowError.setVisible(false);
			game.setRows(row);
		}

		if (!game.validateColumns(col)) {
			lblColumnError.setVisible(true);
			isValid = false;
		} else {
			lblColumnError.setVisible(false);
			game.setColumns(col);
		}

		if (!game.validateNumToWin(numToWin)) {
			lblNumToWinError.setVisible(true);
			lblNumToWinError2.setVisible(true);
			isValid = false;
		} else {
			lblNumToWinError.setVisible(false);
			lblNumToWinError2.setVisible(false);
			game.setNumToWin(numToWin);
		}

		return isValid;
	} // validateFields()

	// Getter for the game object
	public ConnectNGame getGameObject() {
		return game;
	} // getGameObject()

	/*
	 * Action Listener for the 'Confirm' button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConfirm) {
			boolean isValid = true;
			isValid = validateFields();
			if (isValid) {
				// Creates a new game instance with the field values if the entries are valid
				game = new ConnectNGame(fldPlayerOneName.getText(), fldPlayerTwoName.getText(),
						Integer.parseInt(fldRows.getText()), Integer.parseInt(fldColumns.getText()),
						Integer.parseInt(fldNumToWin.getText()), 1);
				// Ensures the other frame is refreshed with the updated information.
				game.initBoard();
				gameFrame.setGameObject(game);
				gameFrame.refreshBoard();
				this.dispose();
			}
		} // if btnConfirm is pressed

	} // actionPerformed(ActionEvent e)

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO
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

}
