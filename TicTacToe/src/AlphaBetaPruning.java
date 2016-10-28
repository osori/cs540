/**
 * 
 */

import java.util.*;

/**
 * @author ilkyu
 *
 */
public class AlphaBetaPruning {
	
	public static final int MAX_VALUE = 2;
	public static final int MIN_VALUE = -2;
	
	public AlphaBetaPruning() {
		
	}
	
	HashMap<Board, Integer> statesWithValue = new HashMap<Board, Integer>();
//	List<Board> solBoards = new ArrayList<Board>();
	
	public int alphabeta(Board b, 
						int alpha, 
						int beta, 
						boolean maximizingPlayer) {
	
	int v;	
		
		if (b.isTerminal()) {
			//return the heuristic value of node
			System.out.println(b);
			System.out.println("Alpha: "+ alpha + " Beta: " + beta);
			statesWithValue.put(b, b.value);
		//	System.out.println("** value: " + b.value +"\n");
			return b.value;
		}
		
		if (maximizingPlayer) {
			v = MIN_VALUE;
			for (Board succ : b.successors()) {
				//System.out.println("(MAX)Added successor: \n" + b + "\n");

				v = Integer.max(v, alphabeta(succ, alpha, beta, false));
				alpha = Integer.max(alpha,  v);
			//	statesWithValue.put(succ, alpha);
				
				if (beta < alpha) {
		//			System.out.println("beta < alpha, beta: " + beta + " alpha: " + alpha);
		//			System.out.println( b);
		//			System.out.println("Alpha: "+ alpha + " Beta: " + beta);
					break;
				}

			}
			System.out.println(b);
			System.out.println("Alpha: "+ alpha + " Beta: " + beta);
			statesWithValue.put(b, v);
			return v;
		} 
		
		else {			
			v = MAX_VALUE;
			for (Board succ : b.successors()) {
				//System.out.println("(min)Added successor: \n" + b + "\n");
				
				v = Integer.min(v, alphabeta(succ, alpha, beta, true));
				beta = Integer.min(beta, v);
			//	statesWithValue.put(succ, beta);
				if (beta < alpha) {
		//			System.out.println("beta < alpha, beta: " + beta + " alpha: " + alpha);
		//			System.out.println(b);
		//			System.out.println("Alpha: "+ alpha + " Beta: " + beta);
					break;
				}

			}
			System.out.println(b);
			System.out.println("Alpha: "+ alpha + " Beta: " + beta);
			statesWithValue.put(b, v);
			return v;
		}
		
	}
	
	public Board getSolution(Board root) {
		
		HashMap<Board, Integer> temp = new HashMap<Board, Integer>();
		HashMap<Board, Integer> boardMins = new HashMap<Board, Integer>();
		for (Board b : statesWithValue.keySet()) {
			for (Board succOfRoot : root.successors()) {
				if (b.toString().equals(succOfRoot.toString())) {
					temp.put(b, statesWithValue.get(b));
				}
			}
		}
		System.out.println("SOLUTION");
		//System.out.println(solBoards.get(0));
		Integer min = Collections.min(temp.values());
		for (Board b : temp.keySet()) {
			if (temp.get(b).equals(min)) {
				boardMins.put(b, temp.get(b));
			}
		}
		return breakTie(boardMins);
	}
	
	private Board breakTie(HashMap<Board, Integer> hm) {
		HashMap<Board, Integer> boardWithFirstBlankPos = new HashMap<Board, Integer>();
		for (Board b : hm.keySet()) {
			for (int row = 0; row < 3; row ++){
				for (int col = 0; col < 4; col ++){
						boardWithFirstBlankPos.put(b, b.lastAddedPos);
				}
			}
		}
		Integer min = Collections.min(boardWithFirstBlankPos.values());
		for (Board b : hm.keySet()) {
			if(boardWithFirstBlankPos.get(b).equals(min)) {
				return b;
			}
		}
		return null;
	}
}
