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
	//	String boardInput = "O X O X # X X O O O _ _";
		String boardInput = "O X O X # O X O _ _ _ _";
//	String boardInput = "O X X O # _ O X _ _ O _";
		
		Board board = new Board(boardInput, "O");
		
		abp.alphabeta(board, abp.MIN_VALUE, abp.MAX_VALUE, false);
		System.out.println(abp.getSolution(board));

		
	//	System.out.println(board);
	//	System.out.println(board.isTerminal());
	//	System.out.println("****END****");
	//	System.out.println("Number of successors: " + board.successors().size());
		//System.out.println("Number of BlankCoords: " + board.numBlanks());
		

		// System.out.println(board.goalCheck());
	}
	

}
