package binary.trees;

import binary.trees.Operations.Node;

public class Main {

	public static void main(String[] args) {
		Operations o = new Operations();
		Node root = o.new Node(5);
		
		root.left = o.new Node(2);
	    root.left.left = o.new Node(1);
	    root.left.right = o.new Node(4);
	    root.left.left.left = o.new Node(-5);
	    
	    root.right = o.new Node(8);
	    root.right.left = o.new Node(7);
	    root.right.left.left = o.new Node(6);
	    root.right.right = o.new Node(9);
	    root.right.right.right = o.new Node(12);
	    
	    String s = o.recursiveSerialize(root);

	    o.recursiveLevelPrint(root);
	    System.out.println();
	    root = o.convert(s);
	    System.out.println();
	    o.recursiveLevelPrint(root);
//   	    o.printTreeByLayers(root);
	}

	
}
