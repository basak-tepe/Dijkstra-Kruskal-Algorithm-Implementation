import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Kruskal {

	//data fields
	private HashMap<String,Vertex> vertices; // all vertices of a graph
	private PriorityQueue<Vertex> unvisitedVertices; //to be visited - unsettled nodes.
	private PriorityQueue<Edge>  unvisitedEdges; //to be visited - unsettled edges.
	private HashSet<String>  visitedVertices; //visited - settled nodes.
	private HashSet<Edge> edges;
	
	
	//constructor
    public Kruskal(Graph graph){
    	  this.edges = graph.getEdges();
		  this.vertices = graph.getVertices(); 
		  visitedVertices = new HashSet<>(); //instantiating visited and unvisited vertices.
		  unvisitedVertices = new PriorityQueue<>();
		  unvisitedEdges = new PriorityQueue<Edge>(edges);
		  for(Entry<String, Vertex> adjacentPair: vertices.entrySet()){
              Vertex vertex  = adjacentPair.getValue();
              unvisitedVertices.add(vertex);
		  }
	     	   
	  }
	  
	
	//methods

    int edgeCount = 0;
    ArrayList<Vertex> startVertices = new  ArrayList<Vertex>();
   
    /**
     * Kruskal's Algorithm
     * Collect edges from a priority queue that store them into a non-decreasing order
     * add edge by edge without making a cycle
     * @param sourceVertex starting Node
     * @return the length of the MST 
     */
    
    public int computeMSTLength(Vertex sourceVertex) {

    	int totalCost = 0; 
    	int i = 10;
    	while(!unvisitedVertices.isEmpty() && !unvisitedEdges.isEmpty()) { //while we have vertices to travel to

    		Edge edge = unvisitedEdges.poll(); //start from the shortest  edge.
    	
    		Vertex source = edge.getV1();
    		Vertex sink = edge.getV2();
    		int weight = edge.getWeight();
    		/**
    		 * print check
    		 */
    		//System.out.println("Our first edge is " +source.getName() + " to " + sink.getName() + " with cost " + weight);
    		/**
    		 * 
    		 */
    		
    		/**
    		 * cycle detection.
    		 * If you already included both nodes to the tree
    		 * dont add that new edge it will form a cycle
    		 */
    		
    		if(visitedVertices.contains(edge.getV2().getName())  && visitedVertices.contains(edge.getV1().getName())) {	//if that 2 vertices are already connected 
    			// 
    			//continue to connect other vertices.
    			
    			/**
    			 * print cjeck
    			 */
    			//System.out.println("skipped");
    			/**
    			 * 
    			 */
    			//if it is a loop.
    			//do nothing continue
    			continue;
    		} 
    		else {
    			/**
    			 * print check
    			 */
    			//System.out.println("counted");
    			/**
    			 * 
    			 */
    			//include the source vertex + edge  + sink vertex in the tree
    			totalCost += weight; //add the edge to our MST
    			unvisitedVertices.remove(edge.getV1());
    			unvisitedVertices.remove(edge.getV2());
    			visitedVertices.add(edge.getV1().getName());
    			visitedVertices.add(edge.getV2().getName());
    			edgeCount++;
    		}
    		
    	}
    	if(totalCost == Integer.MAX_VALUE) {
    		totalCost = -1;
    	}
    	
		return totalCost;
    }
    
    
}
    	 
    	
  
	
    

