import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Graph {

	// Data fields
	private HashMap<String,Vertex> vertices; // all vertices of a graph
	private HashSet<Edge> edges; //all edges of a graph


	// constructors
	public Graph() {
		vertices = new HashMap<>();
		edges = new  HashSet<Edge>();
	}

	// methods
	public void addVertex(String name,Vertex vertex) {
		 //hashMap checks for any duplicates.
		 vertices.putIfAbsent(name, vertex);
	}
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	
	/**
	 * A method used to reset distances after doing Dijkstra once.
	 */
	
	public void resetDistances() {
		   for(Map.Entry<String, Vertex> adjacentPair: vertices.entrySet()){
            adjacentPair.getValue().setCost(Integer.MAX_VALUE);;
		   }
	}

	
	// getters and setters


	
	public void setVertices(HashMap<String,Vertex> vertices) {
		this.vertices = vertices;
	}

	public HashSet<Edge> getEdges() {
		return edges;
	}

	public void setEdges(HashSet<Edge> edges) {
		this.edges = edges;
	}

	public HashMap<String, Vertex> getVertices() {
		// TODO Auto-generated method stub
		return this.vertices;
	}


}
