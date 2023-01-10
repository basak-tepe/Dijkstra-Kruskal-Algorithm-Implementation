import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra{

	  //data field
	  private HashMap<String, Vertex> vertices; //our graphs all vertices.
	  private PriorityQueue<Vertex> unvisitedVertices; //to be visited - unsettled nodes.
	  private HashSet<Vertex>  visitedVertices; //visited - settled nodes.
	  
	  //constructors
	  
	  public Dijkstra(Graph graph){
		  this.vertices = graph.getVertices(); 
		  visitedVertices = new HashSet<>(); //instantiating visited and uv vertices.
	      unvisitedVertices = new PriorityQueue<>();	   
	  }
	  

	  //methods 
	  /**
	     * Dijkstra's Algorithm
	     * Forms paths from a source vertex to other vertices in a graph
	     * by pulling the minimum distance node (current node) from a priority queue
	     * @param sourceVertex starting Node
	     * @return the length of the MST 
	     */
	  
	   public void computeShortestPath(Vertex sourceVertex){ //we start from our source
		   
		   /**
		    * print check
		    */
		   
		
		   //System.out.println(vertices.get("s2").adjacentVertices.toString());
		   
		  /**
		   * things I do to obtain a usable object. 
		   */
		   
		   String sourceName = sourceVertex.getName();
		   Vertex start = vertices.get(sourceName);
		   start.setCost(0); //cost is 0. 
		   
	        visitedVertices = new HashSet<>(); //instantiating visited and uv vertices.
	        unvisitedVertices = new PriorityQueue<>(new VertexComparator());
	        unvisitedVertices.add(start); //starting fro out source.
	        
	        
	        while (!unvisitedVertices.isEmpty()){ //while we have vertices to settle
	 
	            // Pulling the minimum distance node (current node) from the priority queue
	            Vertex minDistanceVertex = unvisitedVertices.poll();
	            String minName = minDistanceVertex.getName();
	            Vertex minVertex = vertices.get(minName);
	            
	            // Adding the node whose distance is
	            // finalized
	            if (visitedVertices.contains(minVertex)) {
	                // Continue keyword skips execution for
	                // following check
	                continue;
	            }
	            
	            Map<Vertex, Integer> adjVertices = new HashMap<Vertex,Integer>();
	            adjVertices.putAll( minVertex.getAdjacentVertices());
	            for(Map.Entry<Vertex, Integer> adjacentPair: adjVertices.entrySet()){
	                Vertex adjacentVertex = adjacentPair.getKey();
	                //System.out.println(adjacentVertex.getName());
	                Integer weight = adjacentPair.getValue();
	                if (!visitedVertices.contains(adjacentVertex)){
	                    int costOfMinDistanceVertex = minVertex.getCost();
	                    if(costOfMinDistanceVertex + weight < adjacentVertex.getCost()){
	                    	adjacentVertex.setCost(costOfMinDistanceVertex + weight);

	                        LinkedList<Vertex> shortestPath = new LinkedList<>(start.shortestPath);
	                        shortestPath.add(start);
	                        adjacentVertex.shortestPath = shortestPath;
	                    }

	                    unvisitedVertices.add(adjacentVertex);

	                }
	            }

	            visitedVertices.add(minVertex);

	        }	
	     
	   }
	   
	   
       public int getTotalCost(Vertex v) {
    	   
    		  /**
 		   * things I do to obtain a usable object. 
 		   */
 		   String vertexName = v.getName();
 		   Vertex vertex = vertices.get(vertexName);
 		   int cost = vertex.getCost();
 			if(cost == Integer.MAX_VALUE) {
 				cost = -1;
 	    	}
 	   
 		   return cost;
       	
       }
}



























