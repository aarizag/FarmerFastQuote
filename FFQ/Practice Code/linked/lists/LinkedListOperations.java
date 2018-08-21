package linked.lists;

import java.util.HashSet;
import java.util.Random;

public class LinkedListOperations {
	
	// Operations involving Singly Linked Lists
	public ListNode deleteAtTail(ListNode head) {
		if (head == null || head.next == null) { return null; }
		else {
			head.next = deleteAtTail(head.next);
			return head;
		}
	}
	
	public ListNode reverse(ListNode head) {
		if(head == null) { return null; }
		ListNode previous = null, temp;
		
		while (head.next != null) {
			temp = head.next;
			head.next = previous;
			previous = head;
			head = temp;
		}
		head.next = previous;
		return head;
	}

	
	public ListNode removeAtPos(ListNode head, int pos) {
		if (head == null) { return null; }
		else if (pos == 0) {
			return head.next;
		}
		else {
			head.next = removeAtPos(head.next, pos-1);
			return head;
		}
	}
	
	public ListNode generateSinglyLinkedList(int size) {
		Random r = new Random();
		ListNode head = new ListNode(r.nextInt(100)), cur = head;
		
		for(int i = 1 ; i < size ; i++) {
			cur.next = new ListNode(r.nextInt(100));
			cur = cur.next;
		}
		
		return head;
	}
	
	public ListNode generateSequentialList(int size) {
		ListNode head = new ListNode(0), cur = head;
		
		for(int i = 1 ; i < size ; i++) {
			cur.next = new ListNode(i);
			cur = cur.next;
		}
		
		return head;
	}
	
	public void printLinkedList(ListNode head) {
		if (head == null) { System.out.println("Null"); }
		while(head != null) {
			if (head.next != null) 
				System.out.print(head.data + " -> ");
			else
				System.out.print(head.data);
			head = head.next;
		}
		
		System.out.println();
	}
	
	public ListNode generateCircularList(int size) {
		ListNode head = new ListNode(0), temp = head;
		for (int i = 1 ; i < size ; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;
		}
		temp.next = head;
		return head;
	}
	
	public boolean isCircularList(ListNode head) {
		if (head == null) { return false; }
		ListNode fast = head.next;
		ListNode slow = head;
		
		while(fast != null && fast.next != null && slow != null) {
			if(slow.data == fast.data)
				return true;
			fast = fast.next.next;
			slow = slow.next;
		}
		
		return false;
	}
	
	public boolean isCircularList2(ListNode head) {
		HashSet<ListNode> hs = new HashSet<>();
		while(head != null) {
			if(hs.contains(head))
				return true;
			hs.add(head);
			head = head.next;
		}
		return false;
	}
	
	public boolean sizeIsEven (ListNode head) {
		if(head == null) 
			return true;
		return !sizeIsEven(head.next);
	}
}
