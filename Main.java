
public class Main {

    private static boolean gameState;
	public static Board board = new Board();
	private static Human h = new Human();
	private static AI ai = new AI();
	private static GUI gui = new GUI();



	public static void main(String[] args) {
		gui.mouse(board, ai, gui, h);
		gui.keyboard(board, ai, gui, h);
		gameState = board.GameOver();

		while (gameState == false) {
			board.afficher(gui);
		}
	}





}