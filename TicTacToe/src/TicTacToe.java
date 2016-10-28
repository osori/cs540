/**
 * 
 */
import java.util.*;
/**
 * @author ilkyu
 *
 */
public class TicTacToe {

	/**
	 * @param args
	 */
	
	static String[][] board = new String[3][4];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlphaBetaPruning abp = new AlphaBetaPruning();
		String boardInput = "";
		boolean showSteps = false;
		
		for (int i = 0; i < 12; i++) {
			boardInput = boardInput + args[i] + " ";
		}

		if (args[12].equals("Y")) {
			showSteps = true;
		} else {
			showSteps = false;
		}
		
		// initial state
		Board board = new Board(boardInput, "O");
		
		// alpha beta pruning
		if (showSteps) {
			abp.alphabeta(board, abp.MIN_VALUE, abp.MAX_VALUE, false);
		} else {
			abp.alphabeta_quiet(board, abp.MIN_VALUE, abp.MAX_VALUE, false);
		}
		
		//print solution
		System.out.println(abp.getSolution(board));

	}
	

}
