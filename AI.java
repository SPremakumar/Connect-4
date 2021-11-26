import java.util.ArrayList;

public class AI extends Players {

	private final int EXTREME_SCORE_CROSS = 1200;
	private final int HIGH_SCORE_CROSS = 50;
	private final int MEDIUM_SCORE_CROSS = 20;
	private final int LOW_SCORE_CROSS = 1;
	private final int EXTREME_SCORE_CERCLE = 1050;
	private final int HIGH_SCORE_CERCLE = 500;
	private final int MEDIUM_SCORE_CERCLE = 30;
	private final int LOW_SCORE_CERCLE = 1;



	public AI() {
		super(false, true);
	}


    public int score_position(Board board){
        int[] tab;
        int score = 0;

        // | 
        for (int r = 0; r < board.getLIGNE() ; r++) {
            for (int c = 0; c < board.getCOLONNE() - 3; c++) {
                tab = new int[] {board.getBOARD(r,c), board.getBOARD(r,c+1), board.getBOARD(r,c+2), board.getBOARD(r,c+3)};
                score += evaluate(tab);
            } 
        }

        // -- 
        for (int r = 0; r < board.getLIGNE() - 3; r++) {
            for (int c = 0; c < board.getCOLONNE(); c++) {
                tab = new int[] {board.getBOARD(r,c), board.getBOARD(r+1,c), board.getBOARD(r+2,c), board.getBOARD(r+3,c)};
                score += evaluate(tab);
            }
        }

        // /
        for (int r = 3; r < board.getLIGNE(); r++) {
            for (int c = 0; c < board.getCOLONNE() - 3; c++) {
                tab = new int[] {board.getBOARD(r,c), board.getBOARD(r-1,c+1), board.getBOARD(r-2,c+2), board.getBOARD(r-3,c+3)};
                score += evaluate(tab);
            }
        }

        // \
        for (int r = 3; r < board.getLIGNE(); r++) {
            for (int c = 3; c < board.getCOLONNE(); c++) {
                tab = new int[] {board.getBOARD(r,c), board.getBOARD(r-1,c-1), board.getBOARD(r-2,c-2), board.getBOARD(r-3,c-3)};
                score += evaluate(tab);
            }
        }
        return score;
    }


    public int count(int[] tab, int element){
        int count = 0;

        for(int j = 0; j < 4; j++){
            if(tab[j] == element)
                count++;
        }
        return count;
    }


    public int evaluate(int[] tab){
        int score = 0;

        // JOUEUR
        // 1 1 1 1 ou X X X X
        if(count(tab, 1) == 4)
            score += EXTREME_SCORE_CROSS; 

        // 1 1 1 0 ou X X X .
        else if(count(tab, 1) == 3 && count(tab, 0) == 1)
            score += HIGH_SCORE_CROSS;

        // 1 1 0 0 ou X X . .
        else if(count(tab, 1) == 2 && count(tab, 0) == 2)
            score += MEDIUM_SCORE_CROSS;

        // 1 0 0 0 ou X . . .
        else if(count(tab, 1) == 1 && count(tab, 0) == 3)
            score += LOW_SCORE_CROSS;

        // AI 
        // 2 2 2 2 ou O O O O
        else if(count(tab, 2) == 4)
            score -= EXTREME_SCORE_CERCLE;

        // 2 2 2 0 ou O O O .
        else if(count(tab, 2) == 3 && count(tab, 0) == 1)
            score -= HIGH_SCORE_CERCLE;

        // 2 2 0 0 ou O O . .
        else if(count(tab, 2) == 2 && count(tab, 0) == 2)
            score -= MEDIUM_SCORE_CERCLE;

        // 2 0 0 0 ou O . . .
        else if(count(tab, 2) == 1 && count(tab, 0) == 3)
            score -= LOW_SCORE_CERCLE;


        return score;
    }


    public ArrayList<Integer> PossibleMoves(Board board){
        ArrayList<Integer> PossibleMove = new ArrayList<Integer>();
        
        for (int i = 0; i < board.getCOLONNE(); i++){
            if(!board.check_full_column(i)){
                PossibleMove.add(i);
            }
        }
        return PossibleMove;
    }


    public void undoMove(int colonne, Board board) {
        try {
            board.setBOARD(colonne, board.last_line(colonne) + 1, board.getVIDE());
        }

        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Erreur lors de la suppression ");
        }
    }


    public int alphabeta(Board board, boolean maximizing, int depth, int alpha, int beta){
        ArrayList<Integer> listOfAvailableColonne = PossibleMoves(board);
        int score, value, colonne = 0;

        // BASE CASE :
        if (board.GameOver() == true || depth == 0) {
        	return score_position(board);
        }

        // CASE MAXIMIZE :
        if (maximizing) {
        	value = Integer.MIN_VALUE;

        	for (int col : listOfAvailableColonne) {
        		DropPiece(col, board.getCROIX(), board);
        		score = alphabeta(board, false, depth - 1, alpha, beta);
        		undoMove(col, board);

        		if (score > value) {
        			value = score;
        			colonne = col;
        		}

        		alpha = Math.max(alpha, value);
    			if (alpha >= beta) {
    				break;
    			}
        	}
        	return colonne;
        }

        // CASE MINIMIZING :
        else {
        	value = Integer.MAX_VALUE;

        	for (int col : listOfAvailableColonne) {
        		DropPiece(col, board.getCERCLE(), board);
        		score = alphabeta(board, true, depth - 1, alpha, beta);
        		undoMove(col, board);

        		if (score < value) {
        			value = score;
        			colonne = col;
        		}

        		beta = Math.min(beta, value);
        		if (alpha >= beta) {
        			break;
        		}
        	}
        	return colonne;
        }
    }


    public int findBestMove(Board board, int depth){
        int result = alphabeta(board, false, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);        
        return result;
    }

}