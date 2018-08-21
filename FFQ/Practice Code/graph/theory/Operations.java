package graph.theory;

import java.util.*;

public class Operations {
	
	public class GraphNode {
		List<GraphNode> connections;
		int data;
		
		public GraphNode(int data) {
			this.data = data;
			this.connections = new ArrayList<>();
		}
		public void setPaths(List<GraphNode> c) {
			this.connections.addAll(c);
		}
	}
	
//	1) Given a directed graph, design an algorithm to find out whether there is a route between  two nodes. 
	// Assumes no two graph nodes have the same int value
	public boolean existsPath(GraphNode start, GraphNode end) {
		if(start == null) 
			return false;
		
		HashSet<GraphNode> explored = new HashSet<>();
		Stack<GraphNode> unexplored = new Stack<>();
			unexplored.push(start);
		GraphNode curr;
		
		while(!unexplored.empty()) {
			curr = unexplored.pop();
			if(curr.data == end.data) 
				return true;
			
			for(GraphNode g : curr.connections) {
				if(!explored.contains(g)) 
					unexplored.push(g);
			}
			explored.add(curr);
		}
		return false;
	}
	
	public void printGraph(ArrayList<GraphNode> arr) {
		for(GraphNode g : arr) {
			System.out.print("\n" + g.data + " -> ");
			for (GraphNode gn : g.connections) {
				System.out.print(gn.data + " - ");
			}
		}
	}
	
	public void randomizeGraphConnections(ArrayList<GraphNode> arr, int variation) {
		Random r = new Random();
		for(int i = variation; i < arr.size()-variation ; i++) {
			GraphNode g = arr.get(i);
			g.connections.add(arr.get(i - (r.nextInt(variation) + 1) ));
//			g.connections.add(arr.get(i - (r.nextInt(variation) + 1) ));
			g.connections.add(arr.get(i + (r.nextInt(variation) + 1) ));
//			g.connections.add(arr.get(i + (r.nextInt(variation) + 1) ));
		}
	}
	
	public void randomizeGraphConnections(ArrayList<GraphNode> arr) {
		Random r = new Random();
		int range = arr.size();
		
		for(GraphNode g : arr) {
			g.connections.add(arr.get(r.nextInt(range)));
			g.connections.add(arr.get(r.nextInt(range)));
			g.connections.add(arr.get(r.nextInt(range)));
			g.connections.add(arr.get(r.nextInt(range)));
		}
	}
}
