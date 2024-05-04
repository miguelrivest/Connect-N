package packageConnectN;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

/**
 * Title: ConnectNHelp Description: Class to display a JFrame with helpful
 * information.
 * 
 * @author Miguel Rivest
 */

public class ConnectNHelp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// ATTENTION PAUL: Haven't moved the declarations outside of the constructor
	// since this is purely a cosmetic frame.

	/**
	 * Create the help frame GUI
	 */
	public ConnectNHelp() {
		setType(Type.POPUP);
		setBackground(Color.WHITE);
		setTitle("Connect-N Game: Help");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 369);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 30, 30, 30, 0, 0, 0, 0, 0, 0, 30, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 0;
		contentPane.add(separator, gbc_separator);

		JLabel lblHelpTitle = new JLabel("Connect-N Rules");
		lblHelpTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		GridBagConstraints gbc_lblHelpTitle = new GridBagConstraints();
		gbc_lblHelpTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblHelpTitle.gridx = 0;
		gbc_lblHelpTitle.gridy = 1;
		contentPane.add(lblHelpTitle, gbc_lblHelpTitle);

		JLabel lblObjective = new JLabel("Objective:");
		lblObjective.setHorizontalAlignment(SwingConstants.LEFT);
		lblObjective.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblObjective = new GridBagConstraints();
		gbc_lblObjective.anchor = GridBagConstraints.WEST;
		gbc_lblObjective.insets = new Insets(0, 0, 5, 0);
		gbc_lblObjective.gridx = 0;
		gbc_lblObjective.gridy = 3;
		contentPane.add(lblObjective, gbc_lblObjective);

		JTextArea txtFldObjective = new JTextArea();
		txtFldObjective.getCaret().setVisible(false);
		txtFldObjective.setEditable(false);
		txtFldObjective.setWrapStyleWord(true);
		txtFldObjective.setText(
				"The objective of the game is to connect a line of N tokens in a row, column, or diagonal on the game board.");
		txtFldObjective.setLineWrap(true);
		txtFldObjective.setBackground(Color.WHITE);
		txtFldObjective.setRows(3);
		GridBagConstraints gbc_txtFldObjective = new GridBagConstraints();
		gbc_txtFldObjective.anchor = GridBagConstraints.NORTH;
		gbc_txtFldObjective.insets = new Insets(0, 0, 5, 0);
		gbc_txtFldObjective.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFldObjective.gridx = 0;
		gbc_txtFldObjective.gridy = 4;
		contentPane.add(txtFldObjective, gbc_txtFldObjective);

		JLabel lblGameSetup = new JLabel("Game Setup:");
		lblGameSetup.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblGameSetup = new GridBagConstraints();
		gbc_lblGameSetup.anchor = GridBagConstraints.WEST;
		gbc_lblGameSetup.insets = new Insets(0, 0, 5, 0);
		gbc_lblGameSetup.gridx = 0;
		gbc_lblGameSetup.gridy = 5;
		contentPane.add(lblGameSetup, gbc_lblGameSetup);

		JTextArea txtFldGameSetup = new JTextArea();
		txtFldGameSetup.getCaret().setVisible(false);
		txtFldGameSetup.setEditable(false);
		txtFldGameSetup.setWrapStyleWord(true);
		txtFldGameSetup.setText(
				"- Decide the dimensions of the game board (number of rows and columns).\r\n- Choose the value of N, which represents the number of tokens needed to win.\r\n- Enter the names of the two players.\r\n\r\nTip: Use the \"File\" menu to select your options\r\n");
		txtFldGameSetup.setRows(3);
		txtFldGameSetup.setLineWrap(true);
		txtFldGameSetup.setBackground(Color.WHITE);
		GridBagConstraints gbc_txtFldGameSetup = new GridBagConstraints();
		gbc_txtFldGameSetup.insets = new Insets(0, 0, 5, 0);
		gbc_txtFldGameSetup.fill = GridBagConstraints.BOTH;
		gbc_txtFldGameSetup.gridx = 0;
		gbc_txtFldGameSetup.gridy = 6;
		contentPane.add(txtFldGameSetup, gbc_txtFldGameSetup);

		JLabel lblWinning = new JLabel("Winning:");
		lblWinning.setHorizontalAlignment(SwingConstants.LEFT);
		lblWinning.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblWinning = new GridBagConstraints();
		gbc_lblWinning.anchor = GridBagConstraints.WEST;
		gbc_lblWinning.insets = new Insets(0, 0, 5, 0);
		gbc_lblWinning.gridx = 0;
		gbc_lblWinning.gridy = 7;
		contentPane.add(lblWinning, gbc_lblWinning);

		JTextArea txtFldWinning = new JTextArea();
		txtFldWinning.getCaret().setVisible(false);
		txtFldWinning.setWrapStyleWord(true);
		txtFldWinning.setText(
				"- If a player successfully connects N tokens, they win the game.\r\n- If the game board fills up before either player achieves victory, the game is a draw.");
		txtFldWinning.setRows(3);
		txtFldWinning.setLineWrap(true);
		txtFldWinning.setEditable(false);
		txtFldWinning.setBackground(Color.WHITE);
		GridBagConstraints gbc_txtFldWinning = new GridBagConstraints();
		gbc_txtFldWinning.insets = new Insets(0, 0, 5, 0);
		gbc_txtFldWinning.fill = GridBagConstraints.BOTH;
		gbc_txtFldWinning.gridx = 0;
		gbc_txtFldWinning.gridy = 8;
		contentPane.add(txtFldWinning, gbc_txtFldWinning);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 9;
		contentPane.add(separator_1, gbc_separator_1);
	} // ConnectNHelp()

} // ConnectNHelp Class
