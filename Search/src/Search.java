import java.util.*;

public class Search {
	static int m, n, d;
	
	//ArrayList<Integer> currState = new ArrayList<Integer>();
	
	static int jugM;
	static int jugN;
	
	static String state = "00"; // initial state
	static int goal;
	
	public static void main(String[] args){

		m = Integer.parseInt(args[0]);
		n = Integer.parseInt(args[1]);
		d = Integer.parseInt(args[2]);
		
		jugM = m;
		jugN = n;
		goal = d;

		System.out.println(bfs());
	}
	
	static String formatState(String unformatted){
		char left = unformatted.charAt(0);
		char right = unformatted.charAt(1);
		String formatted = "(" + left + ", " + right +")";
		return formatted;
	}
	
	static void printList(List<String> l){
		for (String elem : l){
			String formatted = formatState(elem);
			System.out.print(formatted + " ");
		}
		System.out.println();
	}
	
	static void printQueue(Queue<String> l){
		for (String elem : l){
			String formatted = formatState(elem);
			System.out.print(formatted + " ");
		}
		System.out.println();
	}
	
	static String bfs(){
		Queue<String> OPEN = new LinkedList<String>();
		ArrayList<String> CLOSED = new ArrayList<String>();
		
		int numIteration = 0;
		OPEN.add(state);
		
		while (!OPEN.isEmpty()){
			System.out.println("Iteration:");
			System.out.println(numIteration);
			String currState = OPEN.remove();
			if (isGoal(currState)){
				
				return currState; // success!
			}
			else {
				// generate successors
				for (String succState : generateSuccessors(currState)){
					// ignore successors already in OPEN or CLOSED
					if (OPEN.contains(succState) || CLOSED.contains(succState)){
					//	System.out.println("Not adding " +succState);
					}
					else{
					//	System.out.println("Adding " +succState);
						OPEN.add(succState);
					}
					// add successors to OPEN
				}
				CLOSED.add(currState);
				numIteration++;
			}
			printQueue(OPEN);
			printList(CLOSED);
		}
		return "FAIL";
	}
	//3 5 2
	static ArrayList<String> generateSuccessors(String state){
		
		ArrayList<String> successors = new ArrayList<String>();
		Set<String> noDups = new HashSet<String>();
		
		int M = state.charAt(0)-'0';
		int N = state.charAt(1)-'0';
		
		// 1. fill M
		String state1 = Integer.toString(m) + N;
		
		// 2. fill N
		String state2 = M + Integer.toString(n);
		
		// 3. empty M
		String state3 = "0" + N;
		
		// 4. empty N
		String state4 = M + "0";
		
		// 5. pour from M to N
		String state5;
		if (M > 0){
			int tempM = Math.max(0, M+N-n);
			int tempN = Math.min(M+N, n);

			state5 = Integer.toString(tempM)+Integer.toString(tempN);
		}
		else {
			state5 = Integer.toString(M)+Integer.toString(N);
		}
		
		// 6. pour from N to M
		String state6;
		if (N > 0){
			int tempM = Math.min(M+N, m);
			int tempN = Math.max(0, M+N-m);
			state6 = Integer.toString(tempM)+Integer.toString(tempN);
		}
		else {
			state6 = Integer.toString(M)+Integer.toString(N);
		}
		
		//add into successors
		successors.add(state1);
		successors.add(state2);
		successors.add(state3);
		successors.add(state4);
		successors.add(state5);
		successors.add(state6);
		
		
		//remove duplicates
		noDups.addAll(successors);
		successors.clear();
		successors.addAll(noDups);
		
		return successors;
	}
	
	static boolean isGoal(String state){
		if (state.charAt(0)-'0'==goal){
			return true;
		}
		else if (state.charAt(1)-'0'==goal){
			return true;
		}
		return false;
		
	}


}
