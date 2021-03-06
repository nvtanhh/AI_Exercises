package lab7_alphabeta;

public class TestNode {
	public static void main(String[] args) {
		Node root = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E", 3);
		Node nodeF = new Node("F", 12);
		Node nodeG = new Node("G", 8);
		Node nodeH = new Node("H", 2);
		Node nodeI = new Node("I", 4);
		Node nodeJ = new Node("J", 6);
		Node nodeK = new Node("K", 14);
		Node nodeL = new Node("L", 5);
		Node nodeM = new Node("M", 2);

		root.addChild(nodeB);
		root.addChild(nodeC);
		root.addChild(nodeD);
		nodeB.addChild(nodeE);
		nodeB.addChild(nodeF);
		nodeB.addChild(nodeG);
		nodeC.addChild(nodeH);
		nodeC.addChild(nodeI);
		nodeC.addChild(nodeJ);
		nodeD.addChild(nodeK);
		nodeD.addChild(nodeL);
		nodeD.addChild(nodeM);

		ISearchAlgo algo = new MiniMaxSearchAlgo();

		algo.execute(root);
		System.out.println("================================");
		ISearchAlgo algo2 = new AlphaBetaSearchAlgo();
		algo2.execute(root);
	}
}
