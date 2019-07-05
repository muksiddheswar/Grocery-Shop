package dataStructures;
import java.util.Comparator;

import javax.swing.text.AbstractDocument.LeafElement;

//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Tree {
	/*
	private class NaturalComparator implements Comparator
	{
		public int compare(Object a, Object b)
		{
			return ((Comparable)a).compareTo(b);
		}
	}
	*/
	// the class for implementing a node in the tree.
	// contains a value, a pointer to the left child and a pointer to the right child
	
	public class TreeNode implements Comparable {
		 
		protected Comparable value;
		protected TreeNode leftNode;
		protected TreeNode rightNode;
		 
		public TreeNode(Comparable v) {
			 value = v;
			 leftNode = null;
			 rightNode = null;
		}
	  
		 public TreeNode(Comparable v, TreeNode left, TreeNode right) { 
			  value = v;
			  leftNode = left;
			  rightNode = right;
		 }
		 
		 public TreeNode getLeftTree() {
			 return leftNode;
		 }
		 
		 public TreeNode getRightTree() {
		  return rightNode;
		 }
	 
		 public Comparable getValue() {
			 return value;
		 }
		 
		 public void setValue( Comparable value) {
			 this.value = value;
		 }
		 
		 public void setLeftTree(TreeNode leftNode) {
			 this.leftNode = leftNode;
		 }
		 
		 public void setRightTree(TreeNode rightNode) {
			 this.rightNode= rightNode;
		 }
		 
		 public void nodeSwap() {
			 TreeNode temp = leftNode;
			 leftNode = rightNode;
			 rightNode = temp;
		 }
		 
		@Override
		public int compareTo(Object arg0) {
			return value.compareTo(((TreeNode )arg0).value);
		}
	}
		
	// start of the actual tree class
	// the root of our tree
	protected TreeNode root;
	
	public Tree() {
		root = null;
	}
	
	public void traverse(TreeAction action) {
		Queue t = new Queue();
		//Stack t = new Stack();
		t.push(root);
		while(!t.empty()) {
			TreeNode n = (TreeNode)t.pop();
			action.run(n);
			 
			if(n.getLeftTree() != null) t.push(n.getLeftTree());
			if(n.getRightTree() != null) t.push(n.getRightTree());
		}
	}
	
	public void traverseNode(TreeNode n,TreeAction action) {
		if(n != null) {
			if(n.getLeftTree() != null) traverseNode(n.getLeftTree(),action); 
			action.run(n);
			if(n.getRightTree() != null) traverseNode(n.getRightTree(),action);
		}
	}
	
	public void traverseInOrder(TreeAction action) {
		traverseNode(root,action);
	} 
	
	public void print() {
		traverseInOrder(new TreeAction()
		{
			public void run(TreeNode n)
			{
				// put your code here
				System.out.print(n.getValue());
			}
		});
	}

	public void insert(Comparable element) {
		insertAtNode(element,root,null);
	}	
	
	// we traverse the tree.
	// Current holds the pointer to the TreeNode we are currently checking
	// Parent holds the pointer to the parent of the current TreeNode
	private void insertAtNode(Comparable element,TreeNode current,TreeNode parent) {
		// if the node we check is empty
		if(current == null) {
			TreeNode newNode = new TreeNode(element);
			// the current node is empty, but we have a parent
			if(parent != null) {
				// do we add it to the left?
				if(element.compareTo(parent.value) < 0)
					parent.leftNode = newNode;
				
				// or do we add it to the right?
				else
					parent.rightNode = newNode;

			}
			// the current node is empty and it has no parent, we actually have an empty tree
			else root = newNode;
		}
		
		// if the node we check is not empty
		else if(element.compareTo(current.value) == 0)
		{
			// if the element is already in the tree, what to do?
			System.out.println("Error: Value already exists");
		}
		
		// if the element is smaller than current, go left
		else if(element.compareTo(current.value) < 0) {
			insertAtNode(element,current.getLeftTree(),current);
		}
		
		// if the element is bigger than current, go right
		else insertAtNode(element,current.getRightTree(),current);
	}
	
	public int calculateDepth(TreeNode current) {
		// if the node we check is empty
		if(current == null)
			return 0;
		
		int leftHeight = calculateDepth(current.getLeftTree());
		int rightHeight = calculateDepth(current.getRightTree());
		
		if(leftHeight>rightHeight) return leftHeight+1;
		else return rightHeight+1;
	}
	
	public Object biggestElement() {
		return findBiggestElement(root.getRightTree(), root);
	}
	
	private Object findBiggestElement(TreeNode current, TreeNode parent) {
		if (current == null) {
			return parent.getValue();
		}
		
		else {
			return findBiggestElement(current.getRightTree(), current);
		}
	}
	
	public void treeSwap() {
		swap(root);
	}
	
	public void swap(TreeNode t) {
		if(t != null) {
			t.nodeSwap();
			swap(t.getLeftTree());
			swap(t.getRightTree());
		}
	}
	
	public void removeBiggestElement() {
		TreeNode current = root;
		TreeNode parent = null;
		while(current!= null) {
			parent = current;
			current = current.getRightTree(); 
		}
		if(parent!= null) {
			parent.setRightTree(null);
		}
	}
	
	public boolean find(Comparable element) {
		return findNode(element,root);
	}

	public boolean findNode(Comparable element, TreeNode current) {
		if (current == null) return false;
		
		else if (element.compareTo(current.value) == 0)
			return true;
		
		else if (element.compareTo(current.value) < 0) 
			return findNode(element, current.getLeftTree());
		
		else 
			return findNode(element, current.getRightTree());
	}
	
	public Object findElement (Comparable element) {
		TreeNode temp =returnNode(element , root);
		if(temp == null)
			return null;
					
		return  temp.getValue();
	}
	
	private TreeNode returnNode (Comparable element, TreeNode current) {
		if (current == null) return null;
		
		else if (element.compareTo(current.value) == 0)
			return current;
		
		else if (element.compareTo(current.value) < 0) 
			return returnNode(element, current.getLeftTree());
		
		else 
			return returnNode(element, current.getRightTree());
	}
	
	public void updateNode (Comparable element, Comparable value) {
		TreeNode current = returnNode(element , root);
		current.setValue(value);
	}
	
}

