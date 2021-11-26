import javax.swing.*;
import java.awt.*;

public class Human extends Players {

	private final String msg_win = "Bravo, vous avez gagné";
	private final String msg_lose = "Dommage, vous avez perdu";


	public Human () {
		super(true, false);
	}


	public void show_winner(Board board) {
		if (board.connected_lines(board.getCROIX()) == true) {
			JOptionPane.showMessageDialog(null, msg_win);
			System.exit(0);
		}

		else if (board.connected_lines(board.getCERCLE()) == true) {
			JOptionPane.showMessageDialog(null, msg_lose);
			System.exit(0);
		}
	}


}