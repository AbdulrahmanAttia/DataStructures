import java.util.ArrayList;
import java.util.List;


public class Graph {
	
	private List<Integer>[]adj;
	private int edges;
	private int v;
	
	public Graph(int v){
		adj = (ArrayList<Integer>[])new List[v];
		for(int i = 0; i < v;i++){
			adj[i] = new ArrayList<>();
		}
		this.v = v;
	}
	//Delete Edge
	//check whether a certain edge exist
	public void addEdge(int i, int j){
		adj[i].add(j);
		adj[j].add(i);
		edges++;
	}
	
	public int size(){
		return v;
	}
	
	public void iterarte(){
		
		for(int i = 0; i < adj.length;i++){
			System.out.print(i + ":");
			for(Integer k : adj[i]){
				System.out.print(" " + k);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.iterarte();
	}
}
