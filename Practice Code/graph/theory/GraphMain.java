package graph.theory;

import java.util.ArrayList;
import java.util.Random;

import graph.theory.Operations.GraphNode;

public class GraphMain {

	public static void main(String[] args) {
		Random r= new Random();
		Operations o = new Operations();
		ArrayList<GraphNode> graphNodes = new ArrayList<>();

		for(int i = 0 ; i < 50 ; i++) 
			graphNodes.add(o.new GraphNode(i));
		
		o.randomizeGraphConnections(graphNodes, 5);
		o.printGraph(graphNodes);
		
		int start = r.nextInt(50); 
		int end = r.nextInt(50);
		boolean path = o.existsPath( graphNodes.get(start), graphNodes.get(end) );
		
		System.out.println("\nPath between " + start + " and " + end + " : "  + path);
	}

}
