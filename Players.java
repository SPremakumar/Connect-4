
public class Players {

	private boolean HUMAN;
	private boolean AI;



	public Players (boolean HUMAN, boolean AI) {
		this.HUMAN = HUMAN;
		this.AI = AI;
	}


    public void DropPiece(int colonne, int symbole, Board board) {
    	try {
    		if(board.check_full_column(colonne) == false){
    			board.setBOARD(colonne, board.last_line(colonne), symbole);
    		} else {
    			System.out.println("La colonne est remplie, choisissez une autre");
    		}
    	}

    	catch(ArrayIndexOutOfBoundsException e){
    		System.out.println("La colonne n'existe pas");
    	}
    }



	// GETTERS AND SETTERS :
	public boolean getHUMAN(){
		return this.HUMAN;
	}

	public boolean setHUMAN(boolean HUMAN){
		return this.HUMAN = HUMAN;
	}

	public boolean getAI(){
		return this.AI;
	}

	public boolean setAI(boolean AI){
		return this.AI = AI;
	}

}