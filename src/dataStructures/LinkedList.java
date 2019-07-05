package dataStructures;
public class LinkedList {
	
	private class ListElement {
		private Object el1;
		private ListElement el2;

		public ListElement(Object el1, ListElement el2) {
			this.el1 = el1;
			this.el2 = el2;
		}

		public ListElement(Object el1) {
			this(el1, null);
		}

		public Object getFirst() {
			return el1;
		}

		public ListElement getNext() {
			return el2;
		}

		public void setFirst(Object value) {
			el1 = value;
		}

		public void setNext(ListElement value) {
			el2 = value;
		}
	}
	
	private ListElement head;

	public LinkedList() {
		head = null;
	}

	public void addFirst(Object o) {
		head = new ListElement(o, head);
	}

	public Object getFirst() {
		return head.getFirst();
	}

	public Object getElementData(int n) {
		ListElement d = head;
		n--;
		while (n > 0) {
			d = d.getNext();
			n--;
		}
		return d.getFirst();
	}
	
	public String toString() {
		String out = "";
		ListElement temp = head;
		while (temp != null) {
			out += temp.getFirst().toString();
			//out += " ";
			temp = temp.getNext();
		}
		return out;
	}
	
	public int getSize() {
		int count = 0;
		ListElement temp = head;
		while(temp!= null) {
			count++;
			temp = temp.getNext();
		}
		return count;
	}
	
	// INSERTS THE NEW NODE AS THE Nth NODE OF THE LINKED LIST
	// THIS METHOD ASSUMES THAT THE VALUE OF n IS NOT GREATER THAN COUNT
	public void insertNode(int n, Object o) {	
		
		//Boundary condition: If the node has to be inserted as the head
		if(n == 1) {
			addFirst(o);			
			return;
		}
		
		ListElement newNode = new ListElement(o);
		ListElement temp = head;
		int i=1;
		while(i<(n-1)) {
			temp = temp.getNext();
			i++;
		}
	
		newNode.setNext(temp.getNext());
		temp.setNext(newNode);
	}
	
	// INSERTS THE NEW NODE AFTER THE Nth NODE
	// THIS METHOD ASSUMES THAT THE VALUE OF n IS NOT GREATER THAN COUNT
	public void replaceNode(int n, Object o) {	 
		ListElement newNode = new ListElement(o);
		int i=1;
		ListElement temp = head;
		while(i<(n-1)) {
			temp = temp.getNext();
			i++;
		}
		if(n == 1) {
			newNode.setNext(temp.getNext());
			head = newNode;
		}
		else {
			newNode.el2 = (temp.el2).el2;
			temp.el2 = newNode;
		}
	}
	
	// REMOVES THE Nth NODE
	public void removeNode(int n) {	 
		int i=1;
		ListElement temp = head;
		while(i<(n-1)) {
			temp = temp.getNext();
			i++;
		}
		if(n == 1) {
			head = head.getNext();
		}
		else {
			int count = getSize();
			temp.el2 = (temp.el2).el2;
			if(count == 1)
				head = null;
		}
	}
	
	public Object getLast() {
		ListElement temp = head;
		while(temp.el2!= null)
			temp = temp.getNext();
		return temp.getFirst();
	}
	
	public void addLast(Object o) {
		if(head != null) {
			ListElement temp = head;
		
			while(temp.getNext()!= null)
				temp = temp.getNext();
			ListElement newNode = new ListElement(o);
			temp.setNext(newNode);
		}
		else
			head = new ListElement(o, head);
		}

	public void fropple() {
		ListElement first = new ListElement(null,null);
		ListElement second = new ListElement(null,null);
		int listSize = getSize();
		int it = 0;
		if(listSize%2 ==0)
			it = listSize/2;
		else
			it = (listSize-1)/2;
		
		for(int i=1;i<=it;i++) {
			if (i==1) {
				first = head;
				second = head.getNext();
			}
			
			else {
				first = second.getNext();
				second = first.getNext();
			}
			Object temp = first.getFirst();
			first.setFirst(second.getFirst());
			second.setFirst(temp);
		}
	}
	
	public void append(LinkedList ll2) {
		ListElement temp = head;
		while(temp.el2!= null)
			temp = temp.getNext();
		temp.setNext(ll2.head);
	}
	
	public void removeLast() {
		ListElement temp = head;
		while((temp.el2).el2!= null)
			temp = temp.el2;
		temp.el2 = null;
	} 

	public void removeFirst() {
		head = head.getNext();
	}
	
	public void addSorted (Comparable o) {
		// an empty list , add element in front
		if( head == null ) head = new ListElement (o , null );
		else if( ((Comparable)head.getFirst()).compareTo(o) < 0) {
			// we have to add the element in front
			head = new ListElement (o , head );
		}
		else {
			// we have to find the first element which is bigger
			ListElement temp = head ;
			while (( temp.getNext() != null ) && ( ((Comparable)temp.getNext().getFirst()).compareTo(o) > 0)) {
				temp = temp.getNext();
			}
			ListElement next = temp.getNext();
			temp.setNext(new ListElement(o , next));
		}
	}
	
	public Object findElement (Comparable o) {
		ListElement temp = head;
		while ( temp!= null ) {
			if (((Comparable)temp.getFirst()).compareTo(o) == 0) return temp.getFirst();
			temp = temp.getNext();
		}
		return null;
	}
	
	public void findAndReplaceElement (Comparable o, Object newValue) {
		ListElement temp = head;
		while ( temp!= null ) {
			if (((Comparable)temp.getFirst()).compareTo(o) == 0) {
				temp.setFirst(newValue); 
				return ;
			}
			temp = temp.getNext();
		}
	}
	
	public void findAndRemoveElement (Comparable o) {
		ListElement temp = head;
		ListElement temp1 = null;
		while ( temp!= null ) {
			if (((Comparable)temp.getFirst()).compareTo(o) == 0) {
				if(temp==head) {
					head = head.getNext();
					return;
				}
				else {
					temp1.setNext(temp.getNext()); 
					return ;
				}
			}
			temp1 = temp;
			temp = temp.getNext();
		}
	}
	
	public boolean isEmpty() {
		if (getSize() == 0) {
			return true;
		}
		
		else return false;
	}
}
