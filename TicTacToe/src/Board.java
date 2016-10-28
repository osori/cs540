
/**
 * @author ilkyu
 *
 */
import java.util.*;

public class Board {
	public static final int MAX_VALUE = 2;
	public static final int MIN_VALUE = -2;
	
	String[][] board = new String[3][4];
	String currPlayer; 
	int value;
	int alpha, beta;
	int lastAddedPos;
	
	List<Coords> blankCoords = new ArrayList<Coords>();
	
	public Board(String input, String currPlayer){
		
		// String[][] board = new String[3][4];
		String[] elems = input.split(" ");
		int count = 0;
		for (int row = 0; row < 3; row ++){
			for (int col = 0; col < 4; col ++){
				board[row][col] = elems[count];
				count++;
			}
		}
		

		this.currPlayer = currPlayer;
		
		getBlanks();
		goalCheck();
		
	}
	
/*	public Board(String[][] board){
		this.board = board;
		getBlanks();
	}*/
	
	private String boardarrayToString(){
		String ret = "";
		
		for (int row = 0; row < 3; row ++){
			for (int col = 0; col < 4; col ++){
				ret = ret + this.board[row][col] + " ";
			}
		}
		
		return ret;
	}
	
	private void getBlanks(){
		blankCoords.clear();
		for (int x = 0; x < 3; x ++){
			for (int y = 0; y < 4; y ++){
				if (this.board[x][y].equals("_")){
					Coords coordBlank = new Coords(x, y);
					blankCoords.add(coordBlank);
				}
			}
		}
	}
	
	public int numBlanks(){
		//return arrayToString().length() - arrayToString().replace("_", "").length();
		return blankCoords.size();
	}
	
	public List<Board> successors(){
		this.getBlanks();
		if (this.goalCheck() == 1) return new ArrayList<Board>();
		ArrayList<Board> succs =  new ArrayList<Board>();
		String nextPlayer;
		
		if (currPlayer.equals("X")){
			nextPlayer = "O";
		} else {
			nextPlayer = "X";
		}
	//	System.out.println("Number of BlankCoords: " + numBlanks());
		for (Coords c : blankCoords){
			//System.out.println("Current coords: " + c);
	//		System.out.println("****************");
	//		System.out.println("Current node: \n" + this);
			Board b = new Board(boardarrayToString(), nextPlayer);
			b.changeElem(c, nextPlayer);
			b.lastAddedPos = Integer.valueOf(String.valueOf(c.getX()) + String.valueOf(c.getY()));
			succs.add(b);
	//		System.out.println("Successor added: \n" + b);
	//		System.out.println("****************");
		}
		
		return succs;
	}
	
	private void changeElem(Coords c, String changeTo){
		board[c.getX()][c.getY()] = changeTo;
	}
	

	public boolean isTerminal(){
		this.goalCheck();
		return this.successors().isEmpty();
	}
	
	public int goalCheck(){
		HashMap<String, Boolean> winTable = new HashMap<String, Boolean>();
		winTable.put("O", false);
		winTable.put("X", false);
		// Return 0 if O wins
		// Return 1 if X wins
			//a. three in a row
			for (int row = 0; row < 3; row++){
				if ((board[0][row].equals(board[1][row])) && board[1][row].equals(board[2][row])){
					winTable.put(board[0][row], true);
				}
			}
			for (int row = 1; row < 4; row++){
				if ((board[0][row].equals(board[1][row])) && board[1][row].equals(board[2][row])){
					winTable.put(board[0][row], true);
				}
			}
			
			//b. three in a column
			for (int col = 0; col < 3; col++){
				if ((board[col][0].equals(board[col][1]) && board[col][1].equals(board[col][2]))){
					winTable.put(board[col][0], true);
				}
			}
			
			//c. three diagonally
			if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) winTable.put(board[0][0], true);
			if (board[0][1].equals(board[1][2]) && board[1][2].equals(board[2][3])) winTable.put(board[0][1], true);
			if (board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])) winTable.put(board[2][0], true);
			if (board[2][1].equals(board[1][2]) && board[1][2].equals(board[0][3])) winTable.put(board[2][1], true);
			
		boolean o = winTable.get("O");
		boolean x = winTable.get("X");
		
		if (o || x){ // Not a tie
			if (o && x) { value = 0; return 1; }
			else if (o) { value = +1; return 1; }
			else if (x) { value = -1; return 1; }
		} else {
			//Tie
			return 99;
		}
		return -99;
	}

	public String toString(){
		String ret = "";
		
		for (int row = 0; row < 3; row ++){
			for (int col = 0; col < 4; col ++){
				if (col != 3) ret = ret + board[row][col] + " ";
				else if (col == 3) ret = ret + board[row][col];
			}
			if (row != 2) ret = ret + "\n" ;
		}

		return ret;
	}
}
