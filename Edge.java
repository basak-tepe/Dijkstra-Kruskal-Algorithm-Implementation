public class Edge implements Comparable<Edge>{

		
	//data fields
	Vertex v1;
	Vertex v2;
	int weight; //the length of the edge
	String name;

	//constructors
	public Edge(Vertex v1, int weight, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
		this.name = ""+v1.getName()+v2.getName();
	}

	
	//methods
		
	
	@Override
	public int compareTo(Edge other) {
		return Integer.compare(this.weight, other.getWeight());
	    
	}

	
	//getters and setters
	//auto generated with eclipse.
	
	public Vertex getV1() {
		return v1;
	}

	public void setV1(Vertex v1) {
		this.v1 = v1;
	}

	public Vertex getV2() {
		return v2;
	}

	public void setV2(Vertex v2) {
		this.v2 = v2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	

	
	
}
