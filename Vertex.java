import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Vertex implements Comparable<Vertex>{

	//DATA FIELDS
	 boolean isFlag;
	 String name;
	 private Integer cost; //the cost of reaching to a node from root. infinite at first. 
	 public Map<Vertex, Integer> adjacentVertices; //this is a map(dictionary to store all adjacent vertices with their weights to a node.
	 public LinkedList<Vertex> shortestPath; //this linked list stores the shortest path to the root
	 
	
	//CONSTRUCTORS
	public Vertex(String name) {
		isFlag = false;
		this.name = name;
		shortestPath = new LinkedList<>();
		adjacentVertices = new HashMap<>();
		cost = Integer.MAX_VALUE; //our first cost for a node is infinity.
	}
	//METHODS
	

	public void makeFlag() {
		this.isFlag = true;
	}
	
    public void addAdjacentVertex(Vertex neighbor, int weight) {
    	adjacentVertices.put(neighbor, weight); //put the neighbor vertex to hashMap
    }
    

    //@override? 
    
    public int compareTo(Vertex other) {
        return Integer.compare(this.cost, other.getCost());
    }
    
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
	

	
	//getters and setters
	//that I automatically generated with eclipse

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Map<Vertex, Integer> getAdjacentVertices() {
		return adjacentVertices;
	}

	public void setAdjacentVertices(Map<Vertex, Integer> adjacentVertices) {
		this.adjacentVertices = adjacentVertices;
	}

	public LinkedList<Vertex> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(LinkedList<Vertex> shortestPath) {
		this.shortestPath = shortestPath;
	}

	
}










