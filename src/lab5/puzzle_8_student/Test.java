package lab5.puzzle_8_student;

public class Test {

	public static void main(String[] args) {
		Puzzle p = new Puzzle();
		p.readInput("src/lab5/txt/PuzzleMap.txt", "src/lab5/txt/PuzzleGoalState.txt");

		System.out.println(p.getInitialState());

//		List<Node> children = p.getSuccessors(p.getInitialState());

//		 System.out.println(p.heuristic(p.getInitialState(), p.getGoalState()));
		// System.out.println(children.size());
//		for (Node child : children) {
//			System.out.println(child);
//		}

//		GreedyBestFirstPuzzle gbf = new GreedyBestFirstPuzzle();
//		System.out.println(gbf.execute(p));
//	
//		AStartPuzzle aStart = new AStartPuzzle();
//		System.out.println(aStart.execute(p));

//		DFSPuzzle dfs = new DFSPuzzle();
//		dfs.execute(p);
//
//		BFSPuzzle bfs = new BFSPuzzle();
//		System.out.println(bfs.execute(p));
//		
//		HillClimbingPuzzle hill = new HillClimbingPuzzle();
//		System.out.println(hill.execute(p));
	}
}
