package n_queens;

public class Test {
	public static void main(String[] args) {

		Node node = new Node();
		node.generateBoard();

		HillClimbing hill = new HillClimbing();

		Node rs = hill.executeHillClimbingWithRandomRestart(node);
		
		rs.displayBoard();
		System.out.println(rs.getH());
	}
}
