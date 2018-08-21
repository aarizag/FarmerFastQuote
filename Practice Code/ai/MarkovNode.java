package simple.ai;

import java.util.HashMap;

public class MarkovNode {
	HashMap<MarkovNode, Integer> chain;
	HashMap<String, MarkovNode> stringChain;
	
	String data;
	long total;
	
	public MarkovNode(String s) {
		construct(s);
	}
	public MarkovNode(Character c) {
		construct(Character.toString(c));
	}	
	private void construct(String s) {
		this.data = s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
		this.chain = new HashMap<MarkovNode, Integer>();
		this.stringChain = new HashMap<String, MarkovNode>();
		this.total = 0;
	}
	
	public void addToChain(Character addition) {
		addToChain(Character.toString(addition));
	}
	
	public void addToChain(String addition) {
		addition = addition.replaceAll("[^a-zA-Z ]", "").toLowerCase();
		MarkovNode node;
		
		if(addition.length() == 1 && (!addition.equals("a") || !addition.equals("i")))
			return;
		if(!stringChain.containsKey(addition)) {
			node = new MarkovNode(addition);
			stringChain.put(addition, node);
			chain.put(node, 1);
		}
		else {
			node = stringChain.get(addition);
			chain.put(node, chain.get(node) + 1);
		}
		total+=1;
	}
	
	public String toString(boolean b) {
		StringBuilder sb = new StringBuilder();
		sb.append(data);
		int buffer = 20;
		if(sb.length() == 1)
			buffer = 5;
		
		for(int i = buffer - sb.toString().length() ; i >= 0 ; i--)
			sb.append(" ");
		System.out.print(" || ");
		for(MarkovNode n : chain.keySet()) {
			sb.append(n.data + ": ")
			  .append(chain.get(n)) 
			  .append(", ");
		}
		
		return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(data);
		int buffer = 20;
		if(sb.length() == 1)
			buffer = 5;
		
		for(int i = buffer - sb.toString().length() ; i >= 0 ; i--)
			sb.append(" ");
		
		sb.append(" || ");

		calcTotal();
		
		for(MarkovNode n : chain.keySet()) {
			double percent = chain.get(n) / (double) total;
			percent *= 100;
			sb.append(n.data + ": ")
			  .append(String.format("%2.2f", percent)) 
			  .append("% ,  ");
		}
		
		return sb.toString();
	}
	
	public void calcTotal() {
		total = 0;
		for(MarkovNode n : chain.keySet())
			total += chain.get(n);
	}
	
	public MarkovNode chooseOnWeight() {        
        double r = Math.random() * total;
        double countWeight = 0.0;
        
        for (MarkovNode n: chain.keySet()) {
            countWeight += chain.get(n);
            if (countWeight >= r)
                return n;
        }
        return null;
	}
	
	@Override
	public int hashCode() {
		return data.hashCode();
	}
}
