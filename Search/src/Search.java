import java.util.*;

public class Search {
	static int m, n, d;
	
	static int jugM;
	static int jugN;
	
	static String initState = "0-0"; // initial state
								 // split by "-", left is jugM and right is jugN
	static int goal;
	
	public static void main(String[] args){

		m = Integer.parseInt(args[0]);
		n = Integer.parseInt(args[1]);
		d = Integer.parseInt(args[2]);
		
		jugM = m;
		jugN = n;
		goal = d;

		bfs(initState);
	}
	
	static String formatState(String unformattedState){
		String left = stateAsPair(unformattedState)[0];
		String right = stateAsPair(unformattedState)[1];
		String formatted = "(" + left + ", " + right +")";
		return formatted;
	}
	
	static String[] stateAsPair(String state){
		String[] pair = state.split("-");
		if (pair.length != 2){
			System.out.println("Invalid state was input.");
			return null;
		}
		return pair;
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
	
	static void bfs(String start){
		Queue<String> queue = new LinkedList<String>();
		ArrayList<String> CLOSED = new ArrayList<String>();
		
		//path = path.add(start);
		
		int numIteration = 0;
		queue.add(start);
		CLOSED.add(start);
		System.out.println("BFS");
		while (!queue.isEmpty()){
			ArrayList<String> OPEN = new ArrayList<String>();
			System.out.println("Iteration:");
			System.out.println(numIteration);
			String currState = queue.poll();
			if (isGoal(currState)){
				
				System.out.println("Result: " + formatState(currState)); // success!
				return;
			}
			else {
				// generate successors
				for (String succState : generateSuccessors(currState)){
					// ignore successors already in queue or CLOSED
					if (queue.contains(succState) || CLOSED.contains(succState)){
					//	System.out.println("Not adding " +succState);
					}
					else{
					//	System.out.println("Adding " +succState);
						queue.add(succState);
						OPEN.add(0,succState);
					}
					// add successors to queue
				}
				CLOSED.add(currState);
				numIteration++;
			}
			printList(CLOSED);
			printList(OPEN);
		}
		System.out.println( "Unsolvable");
	}
	//3 5 2
	static ArrayList<String> generateSuccessors(String state){
		
		ArrayList<String> successors = new ArrayList<String>();
		Set<String> noDups = new HashSet<String>();
		
		int M = Integer.parseInt(stateAsPair(state)[0]);
		int N = Integer.parseInt(stateAsPair(state)[1]);
		
		// 1. fill M
		String state1 = Integer.toString(m) + "-" + N;
		
		// 2. fill N
		String state2 = M + "-" + Integer.toString(n);
		
		// 3. empty M
		String state3 = "0" + "-" + N;
		
		// 4. empty N
		String state4 = M + "-" + "0";
		
		// 5. pour from M to N
		String state5;
		if (M > 0){
			int tempM = Math.max(0, M+N-n);
			int tempN = Math.min(M+N, n);

			state5 = Integer.toString(tempM)+"-" +Integer.toString(tempN);
		}
		else {
			state5 = Integer.toString(M)+"-" +Integer.toString(N);
		}
		
		// 6. pour from N to M
		String state6;
		if (N > 0){
			int tempM = Math.min(M+N, m);
			int tempN = Math.max(0, M+N-m);
			state6 = Integer.toString(tempM)+"-" +Integer.toString(tempN);
		}
		else {
			state6 = Integer.toString(M)+"-" +Integer.toString(N);
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
		if (Integer.parseInt(stateAsPair(state)[0])==goal){
			return true;
		}
		else if (Integer.parseInt(stateAsPair(state)[1])==goal){
			return true;
		}
		return false;
		
	}


}
