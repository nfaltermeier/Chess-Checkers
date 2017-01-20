package blackop778.chess_checkers;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import blackop778.chess_checkers.graphics.Chess_CheckersFrame;
import blackop778.chess_checkers.graphics.Chess_CheckersPanel;
import blackop778.chess_checkers.net.Client;
import blackop778.chess_checkers.net.Server;

public abstract class Chess_Checkers {
    public static Client client;
    public static Client clientPartner;
    public static boolean offerSurrender;
    public static boolean gameOver = false;
    public static String gameType;
    public static Chess_CheckersPanel panel;
    public static Font font;
    public static String notation;

    public static void main(String[] args) {
	try {
	    font = Font.createFont(Font.TRUETYPE_FONT, new File("resources" + File.separator + "FreeSans.otf"));
	} catch (FontFormatException | IOException e) {
	    e.printStackTrace();
	}

	setupGame();
    }

    public static void startGUI() {
	Chess_CheckersFrame frame = new Chess_CheckersFrame();
	frame.setTitle("Chess-Checkers");
	frame.setPreferredSize(new Dimension(727, 794));
	frame.pack();
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }

    public static void setupGame() {
	offerSurrender = false;
	notation = "";

	gameType = JOptionPane.showInputDialog(null, "Enter 'chess' to play chess or 'checkers' to play checkers.",
		"Which game?", JOptionPane.QUESTION_MESSAGE);
	if (gameType == null) {
	    System.exit(0);
	}
	while (!gameType.equalsIgnoreCase("chess") && !gameType.equalsIgnoreCase("checkers")) {
	    gameType = JOptionPane.showInputDialog(null, "Invalid input. Enter either 'chess' or 'checkers'.",
		    "Ohoes noes", JOptionPane.ERROR_MESSAGE);
	    if (gameType == null) {
		gameType = "null";
	    }
	}
	client = new Server(gameType.equalsIgnoreCase("checkers"), Chess_Checkers.gameType.equalsIgnoreCase("checkers"),
		true);
	((Server) client).startLocalServer();
    }
}
