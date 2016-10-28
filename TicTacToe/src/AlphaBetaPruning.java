/**
 * 
 */

/**
 * @author ilkyu
 *
 */
public class AlphaBetaPruning {
	
	public static final int MAX_VALUE = 2;
	public static final int MIN_VALUE = -2;
	
	public AlphaBetaPruning() {
		
	}
	
	public int alphabeta(Board b, 
						int alpha, 
						int beta, 
						boolean maximizingPlayer) {
	
	int v;	
		
		if (b.isTerminal()) {
			//return the heuristic value of node
			System.out.println("Terminal node reached \n" + b);
			System.out.println("** a: " + alpha);
			System.out.println("** b: " + beta );
			System.out.println("** value: " + b.value +"\n");
			return b.value;
		}
		
		if (maximizingPlayer) {
			v = MIN_VALUE;
			for (Board succ : b.successors()) {
				//System.out.println("(MAX)Added successor: \n" + b + "\n");
				v = Integer.max(v, alphabeta(succ, alpha, beta, false));
				alpha = Integer.max(alpha,  v);
				
				if (beta <= alpha) {
					System.out.println("beta <= alpha, beta: " + beta + " alpha: " + alpha);
					System.out.println("max: \n" + b);
					System.out.println("Alpha: "+ alpha + " Beta: " + beta);
					break;
				}
				//System.out.println("Current board \n" + b);
				//System.out.println("** v: " + v);
			}
			System.out.println("max: \n" + b);
			System.out.println("Alpha: "+ alpha + " Beta: " + beta);
			return v;
		} 
		
		else {			
			v = MAX_VALUE;
			for (Board succ : b.successors()) {
				//System.out.println("(min)Added successor: \n" + b + "\n");
				v = Integer.min(v, alphabeta(succ, alpha, beta, true));
				beta = Integer.min(v,  beta);
				if (beta <= alpha) {
					System.out.println("beta <= alpha, beta: " + beta + " alpha: " + alpha);
					System.out.println("max: \n" + b);
					System.out.println("Alpha: "+ alpha + " Beta: " + beta);
					break;
				}
				
				//System.out.println("Current board \n" + b);
				//System.out.println("** v: " + v);
				System.out.println("min: \n" + b);
				System.out.println("Alpha: "+ alpha + " Beta: " + beta);
			}
			return v;
		}
		
	}
}
