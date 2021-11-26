import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class GUI {

	private final int SIZE = 7;
	private final int SQUARE_SIZE = 90;
	private final int FRAME_SIZE = 650;
	private int turn = 1;
	private int MouseX, MouseY;
	private Human h = new Human();
	private JLabel[][] grid = new JLabel[7][7];
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();



	public GUI () {
		grid();
		panel();
		frame();
	}


	public JPanel panel() {
		panel.setLayout(new GridLayout(SIZE, SIZE));
		panel.setBackground(Color.WHITE);
		return panel;
	}


	public JLabel[][] grid () {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				grid[i][j] = new JLabel();
				grid[i][j].setBounds(SQUARE_SIZE*j, SQUARE_SIZE*i, SQUARE_SIZE, SQUARE_SIZE);
				grid[i][j].setOpaque(true);
				grid[i][j].setBackground(new Color(52, 152, 235));
				grid[i][j].setHorizontalAlignment(JLabel.CENTER);
				panel.add(grid[i][j]);
			}
		}
		return grid;
	}	


	public JFrame frame () {
		frame.setTitle("Connect Four ");
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
		return frame;
	}


	public JPanel mouse (Board board, AI ai, GUI gui, Human h) {
		panel.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked (MouseEvent e){
				MouseX = e.getX()/90;
				MouseY = e.getY()/90;

				if (turn == 1) {
					board.DropPiece(MouseX, board.getCROIX());
					System.out.println(" MouseX = " + MouseX); 
					turn++;
				}

				if (turn == 2){
					board.DropPiece(ai.findBestMove(board, 5), board.getCERCLE());
					turn--;
				}

				if (board.GameOver() == true) {
					h.show_winner(board);
					System.out.println("FIN");
				}

			}

			@Override
			public void mousePressed (MouseEvent e){}
	
			@Override
   		public void mouseReleased (MouseEvent e){}

			@Override
   		public void mouseEntered (MouseEvent e){}

			@Override
   		public void mouseExited (MouseEvent e){}

		});
		return panel;
	} 


	public JPanel keyboard (Board board, AI ai, GUI gui, Human h) {
		panel.addKeyListener(new KeyAdapter(){

			public void keyReleased (KeyEvent k){
				for (int i = 0; i < SIZE; i++) {
					char ch = Character.forDigit(i, 10);	
					if (ch == k.getKeyChar()){
						int KeyInt = Character.getNumericValue(ch);

						if (turn == 1) {
							board.DropPiece(KeyInt, board.getCROIX());
							System.out.println(" KeyX = " + KeyInt); 
							turn++;
						}

						if (turn == 2){
							board.DropPiece(ai.findBestMove(board, 5), board.getCERCLE());
							turn--;
						}

						if (board.GameOver() == true) {
							h.show_winner(board);
							System.out.println("FIN");
						}
					}
				}
			}

			public void KeyPressed (KeyEvent k){}

			public void keyTyped (KeyEvent k){
			}

		});

		panel.setFocusable(true);
		return panel;
	}





	// GETTERS : 
	public JLabel getGRID(int x, int y) {
		return grid[x][y];
	}

}
