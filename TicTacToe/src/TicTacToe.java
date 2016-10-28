///////////////////////////////////////////////////////////////////////////////
//                   
// Name				:   ILKYU JU
// Wisc Username	:	IJU2
// Class Section	:	CS 540 - 1
// Homework #		: 	5
//
// Submission Date	:   Oct 28, 2016
// isLate			:   Yes
// daysLate			:   1 day
//
// ****** I would like to use my one free late day for this assignment. ******
// ******							Thank you.							******
///////////////////////////////////////////////////////////////////////////////
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
		
	    if(args.length != 13) {
	        System.err.println("Usage: java TicTacToe <TicTacToe board> <verbose: Y/N>");
	        return;
	    }
	    
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
