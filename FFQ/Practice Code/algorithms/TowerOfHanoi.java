package algorithms;

import java.util.Scanner;
import java.util.Stack;

/*
 * Tower of Hanoi is a mathematical puzzle where we have three rods and n disks. 
 * The objective of the puzzle is to move the entire stack to another rod, obeying the following simple rules:
 *	1) Only one disk can be moved at a time.
 *	2) Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack 
 *		i.e. a disk can only be moved if it is the uppermost disk on a stack.
 *	3) No disk may be placed on top of a smaller disk. 
 */

public class TowerOfHanoi {
	static Stack<Integer> L = new Stack<>();
	static Stack<Integer> M = new Stack<>();
	static Stack<Integer> R = new Stack<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		sc.close();
		
		for(int i  = size ; i > 0 ; i--)
			L.push(i);
		
		print();
		move('L', 'R', 'M', size);
	}
	
	private static void move(char start, char end, char tempStack, int discNum)
    {
        if (discNum == 1) {
            moveStacks(start, end);
            return;
        }
        move(start, tempStack, end, discNum-1);
        moveStacks(start, end);
        move(tempStack, end, start, discNum-1);
    }
	
	private static void moveStacks(char start, char end) {
		switch(start) {
        case 'L':
        	if(end == 'M')
        		M.push(L.pop());
        	else
        		R.push(L.pop());
        	break;
        case 'M':
        	if(end == 'L')
        		L.push(M.pop());
        	else
        		R.push(M.pop());
        	break;
        case 'R':
        	if(end == 'M')
        		M.push(R.pop());
        	else
        		L.push(R.pop());
        	break;
		}
		print();
	}
	
	public static void print() {
		System.out.println("\n\n *********************");
		System.out.print("\n\nLeft");
		String space = "";
		
		for(Integer in : L) {
			System.out.print("\n" + space + "<");
			for(int i = 0 ; i < in ; i++) 
				System.out.print("==");
			
			System.out.print(">");
			space += " ";
		}
		space = "";

		System.out.print("\n\nMid");
		for(Integer in : M) {
			System.out.print("\n" + space + "<");
			for(int i = 0 ; i < in ; i++) 
				System.out.print("==");
			
			System.out.print(">");
			space += " ";
		}
		space = "";
		
		System.out.print("\n\nRight");
		for(Integer in : R) {
			System.out.print("\n" + space + "<");
			for(int i = 0 ; i < in ; i++) 
				System.out.print("==");
			
			System.out.print(">");
			space += " ";
		}
	}
}
