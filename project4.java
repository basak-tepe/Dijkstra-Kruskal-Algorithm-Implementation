import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class project4 {
	public static void main(String[] args) throws IOException {
		
		/**
		 * checking the time
		 */
		
		long startTime = System.nanoTime();
		/**
		 * 
		 */
		
		/**
		 * @author Basak Tepe, Student ID:2020400117
		 * @since Date: 18.12.2022 submission
		 */

		/**
		 * How my program works
		 * Using Dijkstra's Algorithm, I calculate the shortest path between 2 vertexes in a graph.
		 * For the second part, I use Dijkstra to connect all flag vertices to each other.
		 * Then, I use Kruskal's Algorithm to find the MST of this graph.
		 * @throws IOException 
		 */
		
		try {
		String inputFile = args[0];
		String outputFile = args[1];
		File input = new File(inputFile);
		File output = new File(outputFile);
		Scanner sc = new Scanner(input);
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		
		//reading input
		String firstLine = sc.nextLine();		
		int vertexCount = Integer.parseInt(firstLine);
		String secondLine = sc.nextLine();	
		int flagCount = Integer.parseInt(secondLine);
		String[] thirdLine = sc.nextLine().split(" ");
		String startPoint = thirdLine[0];
		String finishPoint = thirdLine[1];
		Vertex start = new Vertex(startPoint);
		Vertex finish = new Vertex(finishPoint);	
		String[] fourthLine = sc.nextLine().split(" ");
		ArrayList<Vertex> flagPoints = new ArrayList<Vertex>(); //flags 
		
		
		Graph graph = new Graph(); //a weighted graph of the whole map
		
		//create flags
		for(int i = 0;i<flagCount;i++) {
			String flagName = fourthLine[i];
			Vertex flagVertex = new Vertex(flagName);
			flagVertex.makeFlag();
			graph.addVertex(flagName,flagVertex);
			flagPoints.add(flagVertex);
		}
		
		
		while(sc.hasNextLine()) {
			
			try {
			String[] nextLine = sc.nextLine().split(" ");
			int nextLineLength = nextLine.length;
			
			//first vertex
			String firstPoint = nextLine[0];
			Vertex first = new Vertex(firstPoint);
			graph.addVertex(firstPoint,first);
			
			//first element, nL[0] is node to be connected to others.
			//1,2 contains first neighbors info.
			//3,4 the next.
			
			//making neighbors for a node
			for(int i = 1; i<nextLineLength-1; i+=2) {
				String nextPoint = nextLine[i];
				Vertex next = new Vertex(nextPoint);
				int distanceBwFirstAndNext=  Integer.parseInt(nextLine[i+1]);
				graph.addVertex(nextPoint,next);
				
				Vertex v1 = graph.getVertices().get(firstPoint);
				Vertex v2 = graph.getVertices().get(nextPoint);
				
				v1.addAdjacentVertex(v2, distanceBwFirstAndNext);
				v2.addAdjacentVertex(v1, distanceBwFirstAndNext);
			
			
			}
			}
			
			catch(Exception e){ //except we are looking at the final node
				//well do nothing, last node is already added during try block.
			}
			
			
		}
			
		
		//DIJKSTRA FOR FINISH AND START OF THE RACE
		Dijkstra DijkstraAlgorithm1 = new Dijkstra(graph);
		DijkstraAlgorithm1.computeShortestPath(start);
		int cost1 = DijkstraAlgorithm1.getTotalCost(finish);
		System.out.println(cost1);
		writer.write(Integer.toString(cost1));
		writer.newLine();
		
		
		
		//KRUSKAL FOR PICKING FLAGS UP - MINIMUM SPANNING TREE
		/**
		 * I used a new graph. One that consists of only the flag nodes.
		 * Thus I apply Dijkstra between every possible flag Vertex pair
		 * Connect these by new Dijkstra values.
		 * So now we have omitted any non-flag vertex.
		 * Now I can perform MST - Kruskal's algorithm.
		 */
		

		graph.resetDistances();
		Graph flagGraph = shortenGraph(graph,flagPoints);	
		Kruskal KruskalsAlgorithm = new Kruskal(flagGraph);
		int cost2 = KruskalsAlgorithm.computeMSTLength(flagPoints.get(0));
		System.out.println(cost2);
		writer.write(Integer.toString(cost2));
		
		sc.close();
		writer.close();
		
		
		/**
		 * checking the time
		 */
	
		long endTime = System.nanoTime();
		double duration = Double.valueOf(endTime - startTime) /1000000000;
		System.out.println(duration);
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	
	
	public static Graph shortenGraph(Graph graph, ArrayList<Vertex> flagPoints) {
		Graph flagGraph = new Graph();
		int flagCount = flagPoints.size();
		
		/**
		 * print check
		 
		
		System.out.println("FlagPoints are");
		for(int i = 0; i<flagCount;i++) {
			System.out.println(flagPoints.get(i).getName());
		}
		
		 * 
		 */
		
		for(int i =  0; i<flagCount; i++) {
			Vertex flag = flagPoints.get(i);
			String flagName = flag.getName();
			
			//add the new vertex that we made to our new graph.
			Vertex newFlag = new Vertex(flagName);
		    flagGraph.addVertex(flagName,newFlag);
		    
			//Make Dijkstra for each graph.
		    graph.resetDistances();
			Dijkstra FlagDijkstra = new Dijkstra(graph);
			FlagDijkstra.computeShortestPath(flag); 
			for(int j = i+1; j<flagCount;j++) {
				Vertex toFlag = flagPoints.get(j);
				String toFlagName = toFlag.getName();
				Vertex newToFlag = new Vertex(toFlagName);
				
			    int cost = FlagDijkstra.getTotalCost(toFlag);
			    flagGraph.addVertex(toFlagName, newToFlag);
			    //make them neighbors.
			    
			    /**
			     * print Check
			     */
			    
			    //System.out.println(newFlag.getName() + "  is adjacent to " +newToFlag.getName()+" with cost " + cost);
			    
			    /**
			     * 
			     */
			    newFlag.addAdjacentVertex(newToFlag, cost);
			    Edge edge = new Edge(newFlag,cost,newToFlag); 
			    flagGraph.addEdge(edge);
			    newToFlag.addAdjacentVertex(newFlag, cost);
			}
		}
		
		return flagGraph;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
