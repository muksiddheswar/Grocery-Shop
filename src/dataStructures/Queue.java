package dataStructures;
public class Queue 
{
	private LinkedList data;
	
	public Queue () {
		data = new LinkedList();
	}
	
	
	public void push(Object o) { // Time Complexity = O(n) 
		data.addLast(o);
	}
	
	public Object pop() {		// Time Complexity = O(1)
		Object o = data.getFirst();
		data.removeFirst();
		return o;
	}
	
	public Object top() {
		return data.getFirst();
	}
	
	public int size() {
		return data.getSize();
	}
	
	public boolean empty() {
		int sz = data.getSize();
		if(sz!= 0)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	public LinkedList getList() {
		return data;
	}
	
	public Object getElementData(int n) {
		return data.getElementData(n);
	}
	
	public void findAndRemoveElement (Comparable o) {
		data.findAndRemoveElement(o);
	}
	
	public String toString() {
		return data.toString();
	}

	
}
