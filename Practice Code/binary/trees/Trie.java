package binary.trees;

import java.util.*;

class TrieNode {
	Character c;
	Boolean isLeaf = false;
	HashMap<Character, TrieNode> children = new HashMap<>();

	public TrieNode() {
	}

	public TrieNode(Character c) {
		this.c = c;
	}
}

class Trie {
	private TrieNode root;

	public Trie() {
	}

	public Trie(Character c) {
		this.root = new TrieNode(c);
	}

	public void insertWord(String word) {
		if (word == null || word.length() == 0) {
			return;
		}
		if (root == null) {
			root = new TrieNode(word.charAt(0));
		}
		TrieNode current = root;
		HashMap<Character, TrieNode> chil = current.children;
		char character;

		for (int i = 1; i < word.length(); i++) {
			character = word.charAt(i);
			if (!chil.containsKey(character)) {
				chil.put(character, new TrieNode(character));
			}
			current = chil.get(character);
			chil = current.children;
		}

		current.isLeaf = true;
	}

	public Boolean searchWord(String word) {
		TrieNode node = search(word);
		return node != null && node.isLeaf;
	}

	public Boolean searchPrefix(String word) {
		TrieNode node = search(word);
		return node != null;
	}

	private TrieNode search(String word) {
		if (word == null || word.length() == 0)
			return null;
		if (root == null) 
			return null;
		
		TrieNode cur = root;
		HashMap<Character, TrieNode> chil = cur.children;
		char c;

		for (int i = 1; i < word.length(); i++) {
			c = word.charAt(i);
			
			if (!chil.containsKey(c))
				return null;
			
			cur = chil.get(c);
			chil = cur.children;
		}
		return cur;
	}
}