package linked.lists;

public class DoubleLinkedOperations {

	// head node is indexed at 1
	public ListNode removeAtPos(ListNode head, int pos) {
		if (head == null || pos < 1)
			return head;
		else if (pos == 1)
			return head.next;
		
		else {
			head.next = removeAtPos(head.next, pos - 1);
			if(head.next != null)
				head.next.prev = head;
			return head;
		}
	}
	
	public void printDoubleList(ListNode head) {
		if (head == null) { System.out.println("Null"); }
		while(head != null) {
			if (head.next != null) 
				System.out.print(head.data + " <=> ");
			else
				System.out.print(head.data);
			head = head.next;
		}
		
		System.out.println();
	}
}
