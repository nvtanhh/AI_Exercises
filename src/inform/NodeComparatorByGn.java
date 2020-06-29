package inform;

import java.util.Comparator;

public class NodeComparatorByGn implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		Double f1 = o1.getF();
		Double f2 = o2.getF();

		int result = f1.compareTo(f2);
		if (result == 0)
			return o1.getLabel().compareTo(o2.getLabel());
		else
			return result;
	}

}
