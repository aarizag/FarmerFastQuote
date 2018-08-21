package linked.lists;

import java.util.Stack;

public class MyQueue<T> {
//	Implement a MyQueue class which implements a queue using two stacks. 
	Stack<T> origin;
	Stack<T> alternate;
	
	public MyQueue() {
		this.origin = new Stack<>();
		this.alternate = new Stack<>();
	}
	
	public void add(T type) {
		while (!origin.empty()) 
			alternate.push(origin.pop());
		
		alternate.push(type);
		
		while(!alternate.empty()) 
			origin.push(alternate.pop());
	}
	
	public T poll() {
		if(origin.empty()) 
			return null;
		
		return origin.pop();
	}
}
