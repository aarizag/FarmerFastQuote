package linked.lists;

public class Driver {
	public static void main(String[] args) {
		LinkedListOperations llo = new LinkedListOperations();
		DoubleLinkedOperations dlo = new DoubleLinkedOperations();
		ListNode head = new ListNode(0);
		
		head.next = new ListNode(1);
		head.next.prev = head;
		
		head.next.next = new ListNode(2);
		head.next.next.prev = head.next;
		
		head.next.next.next = new ListNode(3);
		head.next.next.next.prev = head.next.next;
		
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.prev = head.next.next.next;
//		System.out.println(llo.isCircularList2(head));
		
		dlo.printDoubleList(head);
		head = dlo.removeAtPos(head, 0);
		dlo.printDoubleList(head);
	}
}