import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {

	/**
	 * Compares vertices according to cost 
	 * For Dijkstra
	 */
	@Override
	public int compare(Vertex v1, Vertex v2) {
            if (v1.getCost() < v2.getCost()) {
                return -1;
            }
            else if (v1.getCost() > v2.getCost()) {
                return 1;
            }
                return 0;
            }

}
