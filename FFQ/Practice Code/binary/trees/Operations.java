package binary.trees;

import java.util.*;

public class Operations {

	public class Node {
		Node left;
		Node right;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	/* Iterative In-Order traversal */
	public void iterativeInorder(Node root) {
		Stack<Node> s = new Stack<Node>();
		Node traveler = root;

		while (traveler != null || s.size() > 0) {
            while (traveler !=  null) {
                s.push(traveler);
                traveler = traveler.left;
            }
            traveler = s.pop();
            // ADD TO LIST / RETURN VALUE HERE
            traveler = traveler.right;
        }
	}

	/* Iterative Pre-Order traversal */
	public void iterativePreorder(Node root) {
		if (root == null) { return; }
		
		Stack<Node> nodes = new Stack<>();
		Node traveler;
		nodes.push(root);
		
		while(!nodes.empty()) {
			traveler = nodes.pop();	
			System.out.print(traveler.data + ",");
            // ADD TO LIST / RETURN VALUE HERE
			if(traveler.right != null) { nodes.push(traveler.right); }
			if(traveler.left != null) { nodes.push(traveler.left); }
		}
	}
	
	/* Recursive Pre-Order traversal */
	public void recursivePreorder(Node root) {
		if(root == null) { return; }
		
		System.out.print(root.data + " ");
		recursivePreorder(root.left);
		recursivePreorder(root.right);
 	}
	
	/* Validate a BST recursively*/
	public boolean recursiveValidateBST(Node root) {
		return recursiveValidateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	public boolean recursiveValidateBST(Node root, int min, int max) {
		if(root == null) 
			return true;
		
		if(root.data < min || root.data > max) 
			return false;
		
		return recursiveValidateBST(root.left,min, root.data) && recursiveValidateBST(root.right, root.data, max);
	}
	
	/* Validate a BST using iterative in-order traversal */
	public boolean iterativeValidateBST(Node root) {
		if (root == null) { return false; }
		
		Stack<Node> s = new Stack<Node>();
		Node traveler = root;
		int min = Integer.MIN_VALUE;

		while (traveler != null || s.size() > 0) {
            while (traveler !=  null) {
                s.push(traveler);
                traveler = traveler.left;
            }
            traveler = s.pop();
            
            if(traveler.data < min) 
            	return false;
            
            min = traveler.data;
            traveler = traveler.right;
        }
		return true;
	}
	
	/* Verify that a binary tree is balanced */
	public boolean verifyBalance(Node root) {
		if(root == null) 
			return false;

		Queue<Node> curLevel = new LinkedList<>(), nextLevel = new LinkedList<>();
		curLevel.add(root);
		Node temp;
		int expectedSize = 1;
		boolean finalRun = false;
		
		while(!curLevel.isEmpty()) {
			temp = curLevel.poll();
			
			if(temp.left != null) 
				nextLevel.add(temp.left);
			if(temp.right != null) 
				nextLevel.add(temp.right);
			
			if(curLevel.isEmpty()) {
				expectedSize = expectedSize * 2;
				if(finalRun)
					break;
				
				if(nextLevel.size() != expectedSize)
					finalRun = true;
				
				curLevel = nextLevel;
				nextLevel = new LinkedList<Node>();
			}
		}
		
		return nextLevel.isEmpty();
	}
	
	/* Convert an array into an pre-ordered tree*/
	public Node arrayToTree(int[] arr) {
		if(arr.length < 1) 
			return null;
		
		Node[] nodes = new Node[arr.length+1];
		Node traveler, root;
		
		for (int i = 0 ; i < arr.length ; i++ ) 
			nodes[i+1] = new Node(arr[i]);
		
		int i = 1;
		while(i * 2 < nodes.length) {
			traveler = nodes[i * 2];
			root = nodes[i];
			root.left = traveler;
			
			if(i*2 + 1 < nodes.length) {
				traveler = nodes[i*2 + 1];
				root.right = traveler;
			}
			i++;
		}
		
		return nodes[1];
	}
	
	/* Print Binary Tree level by level (recursive) */
	/* '?' represents null values 					*/
	public void recursiveLevelPrint(Node root) {
		int height = treeHeight(root);
		for(int i = 0 ; i < height ; i++) {
			recursiveLevelPrint(root, i);
			System.out.println();
		}
	}
	private void recursiveLevelPrint(Node root, int level) {
		if(root == null) 
			System.out.print("?" + ",");
		else if(level == 0) 
			System.out.print(root.data + ",");
		else {
			recursiveLevelPrint(root.left, level-1);
			recursiveLevelPrint(root.right, level-1);
		}
	}

	/* Return height of Binary Tree */
	public int treeHeight(Node root) {
		if(root == null) 
			return 0;
		return Math.max( treeHeight(root.left)+1, treeHeight(root.right)+1 );
	}
	
	/* Serialize Binary Tree into Level-Order String separated by commas */
	/* '?' represents null values 										 */
	public String recursiveSerialize(Node root) {
		int height = treeHeight(root);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < height ; i++) {
			sb.append(recursiveSerialize(root, i)).append(",");
		}
		return sb.toString();
	}
	public String recursiveSerialize(Node root, int level) {
		if(root == null)
			return "?";
		if(level == 0)
			return Integer.toString(root.data);
		return recursiveSerialize(root.left, level-1) + "," + recursiveSerialize(root.right, level-1);
	}
	
	/* Convert Serialized Tree (String) back into a Tree */
	public Node convert(String serialized) {
		String[] nodeStrings = serialized.split(",");
		Node[] nodes = new Node[nodeStrings.length + 1];
		
		for(int i = 0 ; i < nodeStrings.length ; i++) {
			if (!nodeStrings[i].equals("?")) 
				nodes[i+1] = new Node(Integer.valueOf(nodeStrings[i]));
			else 
				nodes[i+1] = null;
		}

		Node traveler, root;
		
		for(int i = 1 ; i * 2 < nodes.length ; i++) {
			root = nodes[i];
			if(root == null)
				continue;
			
			traveler = nodes[i * 2];
			root.left = traveler;
			
			if(i*2 + 1 < nodes.length) {
				traveler = nodes[i*2 + 1];
				root.right = traveler;
			}
		}
		
		return nodes[1];
	}
	
	
	/* BETTER SERIALIZE 
	 
		public String serializeTree(TreeNode root){
		    if(root == null) return "N,";
		    return String.valueOf(root.data) + "," + serializeTree(root.left) + serializeTree(root.right);
		}
		
		public TreeNode restoreTree(String str){
		    return helper(new LinkedList<String>(Arrays.asList(str.split(","))));
		}
		
		public TreeNode helper(List<String> list){
		    String s = list.poll();
		    if(s.equals("N")) return null;
		    return new TreeNode(Integer.valueOf(s), helper(list), helper(list));
		} 
		
	 */
}
  