import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {

	/**
	 * Compares edges according to length
	 * For Kruskal
	 */
	
	@Override
	public int compare(Edge e1, Edge e2) {
		 if (e1.getWeight() < e2.getWeight()) {
             return -1;
         }
         else if (e1.getWeight() > e2.getWeight()) {
             return 1;
         }
             return 0;
         }
	}

