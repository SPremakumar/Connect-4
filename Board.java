import javax.swing.*;
import java.awt.*;

public class Board {

	private final int VIDE = 0;
	private final int CROIX = 1;
	private final int CERCLE = 2;
	private final int LIGNE = 7; 
	private final int COLONNE = 7;
	private final int[][] board;



	public Board() {
		this.board = new int[LIGNE][COLONNE];
		for (int l = 0; l < LIGNE; ++l) {
			for (int col = 0; col < COLONNE; ++col) {
				this.board[l][col] = VIDE;
			}
		}
	}


    public void afficher(GUI gui){
        for (int ligne = 0; ligne < LIGNE; ++ligne) {
            for (int col = 0; col < COLONNE; ++col) {

                if (this.board[col][ligne] == VIDE)
                    gui.getGRID(ligne, col).setIcon(new ImageIcon("white-circle.png"));

                else if (this.board[col][ligne] == CROIX)
                    gui.getGRID(ligne, col).setIcon(new ImageIcon("red-circle-md.png"));

                else if (this.board[col][ligne] == CERCLE)
                    gui.getGRID(ligne, col).setIcon(new ImageIcon("yellow-circle-md.png"));
            }
        }
    }


    public boolean check_full_column(int col){
    	int counter = 0;

    	for(int i = 0; i < LIGNE; i++){    		
    		if(this.board[col][i] != VIDE){
    			counter++;
    			if(counter == LIGNE)
    				return true;
    		}
    	}
    	return false;
    }


    public int last_line(int col){
    	int counter = LIGNE-1;

    	for(int i = 0; i < LIGNE; i++){
    		if(this.board[col][i] != VIDE){
    			counter--;
    		}
    	}
    	return counter;
    }


    public boolean connected_lines(int player){  	
    	// |
    	for (int r = 0; r < LIGNE ; r++) {
            for (int c = 0; c < COLONNE - 3; c++) {
            	if (this.board[r][c] == player && this.board[r][c+1] == player && this.board[r][c+2] == player && this.board[r][c+3] == player){
            		return true;
            	}
            } 
        }

        // -- 
        for (int r = 0; r < LIGNE - 3; r++) {
            for (int c = 0; c < COLONNE; c++) {
            	if (this.board[r][c] == player && this.board[r+1][c] == player && this.board[r+2][c] == player && this.board[r+3][c] == player){
            		return true;
            	}
            }
        }

        // /
        for (int r = 3; r < LIGNE; r++) {
           for (int c = 0; c < COLONNE - 3; c++) {
            	if (this.board[r][c] == player && this.board[r-1][c+1] == player && this.board[r-2][c+2] == player && this.board[r-3][c+3] == player){
            		return true;
            	}
            }
        }

        // \
        for (int r = 3; r < LIGNE; r++) {
            for (int c = 3; c < COLONNE; c++) {
            	if (this.board[r][c] == player && this.board[r-1][c-1] == player && this.board[r-2][c-2] == player && this.board[r-3][c-3] == player){
            		return true;
            	}
            }
        }
    	return false;
    }


    public boolean BoardFull(){
        for(int i = 0; i < LIGNE; i++){
            for(int j = 0; j < COLONNE; j++){
                if (this.board[i][j] == VIDE)
                    return false;
            }
        }
        return true;
    }


    public boolean GameOver(){
        if (connected_lines(CROIX) == true || connected_lines(CERCLE) == true || BoardFull() == true){
            return true;
        }
        return false;
    }


    public void DropPiece(int colonne, int symbole) {
        try {
            if(check_full_column(colonne) == false){
                setBOARD(colonne, last_line(colonne), symbole);
            }
            else {
                System.out.println("la colonne est remplie !");
            }
        }

        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("La colonne n'existe pas");
        }
    }


    // GETTERS AND SETTERS :

    public int getLIGNE(){
    	return LIGNE;
    }
   	
   	public int getCOLONNE(){
    	return COLONNE;
    }

    public int getVIDE(){
    	return VIDE;
    }

    public int getCROIX(){
    	return CROIX;
    }

    public int getCERCLE(){
    	return CERCLE;
    }

    public int getBOARD(int x, int y){
    	return board[x][y];
    }

    public int setBOARD(int x, int y, int value){
    	return board[x][y] = value;
    }
    
}