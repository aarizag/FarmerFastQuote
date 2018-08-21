package simple.ai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class MarkovChains<T> {
	HashMap<T, MarkovNode> chains;
	static MarkovNode activeNode = null;
	
	public MarkovChains() {
		this.chains = new HashMap<T, MarkovNode>();
	}
	
	public void readParagraphs(String[] locations) {
		String dir = "C:\\Users\\ADRYEL.ARIZAGA\\eclipse-workspace\\SimpleAI\\";
		for(String s: locations)
			readParagraphs(dir + s);
	}
	
	public static String[] getLines(String location) {
		try (FileReader f = new FileReader(location) ; BufferedReader br = new BufferedReader(f)) {
			String line = br.readLine(), lastWord = null;
			ArrayList<String> arr = new ArrayList<>();
			int i = 0;
			while (line != null) {
				arr.add(line);
				i++;
				line = br.readLine();
			}
			
			int size = arr.size();
			String[] lines = new String[size];
			for(i = 0 ; i < size ; i++)
				lines[i] = arr.get(i);
			
			return lines;
		}
		
		catch(IOException e) { 
			System.out.println("FILE NOT ABLE TO BE READ.");
		}
		return null;
	}
	
	public void readParagraphs(String location) {
		try (FileReader f = new FileReader(location) ; BufferedReader br = new BufferedReader(f)) {
			String line = br.readLine(), lastWord = null;
			String[] splitLine;
			
			while (line != null) {
				splitLine = line.split(" ");
				lastWord = addToMap(lastWord, splitLine);
				line = br.readLine();
			}
		}
		
		catch(IOException e) { 
			System.out.println("FILE NOT ABLE TO BE READ.");
		}
	}
	
	public void readDictionary(String location) {
		try (FileReader f = new FileReader(location) ; BufferedReader br = new BufferedReader(f)) {
			String line = br.readLine();
			
			while (line != null) {
				addToMap(line);
				line = br.readLine();
			}
		}
		catch(IOException e) { 
			System.out.println("FILE NOT ABLE TO BE READ.");
		}
	}
	
	// addToMap function for Characters in words
	private void addToMap(String line) {
		if(line == null || line.length() == 0)
			return;
		
		Character previous, current;
		
		for(int i = line.length()-1 ; i > 0 ; i--) {
			current = line.charAt(i);
			previous = line.charAt(i-1);
			if(!Character.isLetter(previous) || !Character.isLetter(current))
				continue;
			previous = Character.toUpperCase(previous);
			current = Character.toUpperCase(current);
			
			if(!chains.containsKey(current)) 
				chains.put((T) current, new MarkovNode(current));
			
			chains.get(current).addToChain(previous);
		}
	}
	
	// addToMap function for Strings
	// Return Last Word to add to chain
	private String addToMap (String firstWord, String[] splitLine) {
		if(splitLine == null || splitLine.length == 0)
			return firstWord;
		String current = firstWord;
		MarkovNode curNode = activeNode, prevNode = null;
		
		for(int i = 0 ; i < splitLine.length ; i++) {
				current = splitLine[i];
			if(current == null || current.trim().length() < 1) 
				continue;
			
			boolean hasPunctuation = current.contains(".") || current.contains("!") || current.contains("?");
			current = current.replaceAll("[^a-zA-Z ]", "").toLowerCase();
			
			if(curNode == null) {
				curNode = (chains.containsKey(current)) ? chains.get(current) : new MarkovNode(current);
				chains.put((T)current, curNode);
			}
			else {
				curNode.addToChain(current);
				if(hasPunctuation)
					curNode = (chains.containsKey(current)) ? chains.get(current) : new MarkovNode(current);
				else 
					curNode = curNode.stringChain.get(current);
			}
				
		}
		return current;
	}
	
	public void writeNewParagraph() {
		Random r = new Random();
		Set<T> keySet = chains.keySet();
		int stop = r.nextInt(keySet.size()), currentSize = 0;
		
		String start = randKey(keySet, stop);
		MarkovNode currentNode = chains.get(start);
		
		for (int i = 0 ; i < 500 ; i++) {
			if(currentNode == null) {
				if(currentSize > 8)
					System.out.println(".\n");
				start = randKey(keySet, r.nextInt(keySet.size()));
				currentNode = chains.get(start);
				i--;
				currentSize = 0;
			}
			else {
				currentSize ++;
				System.out.print(currentNode.data + " ");
				currentNode = currentNode.chooseOnWeight();
			}
		}
	}
	
	private String randKey(Set<T> s, int stop) {
		String start = null;
		int index = 0;
		for(T t : s) {
			if(index >= stop) {
				start = (String) t;
				break;
			}
			index ++;
		}
		return start;
	}
	
	public void print() {
		System.out.println("Letter || Succeded by : Probability %\n");
	
		for(T t: chains.keySet())
			System.out.println(chains.get(t));
	}
}
