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
		String boardInput = "O X O X # O X O X O X O";
		Board board = new Board(boardInput, "O");
		board.print();
		System.out.println(board.goalCheck());
		System.out.println("****************");
		for (Board b : board.successors()){
			b.print();
			System.out.println();
		}
		// System.out.println(board.goalCheck());
	}
	

}
