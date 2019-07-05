package dataStructures;

//REPRESENTATION OF GRAPH USING ADJACENCY LIST

public class Graph
{
    public class Node implements Comparable
    {
        private Comparable info;
        private LinkedList edges;
        private boolean visited;
        float distance;
        Node parent;
        
        public Node( Comparable label ) {
            info = label;
            edges = new LinkedList();
            visited = true;
            distance = Float.POSITIVE_INFINITY;
            parent = null;
        }
        
        public Node( ) {
            info = null;
            edges = new LinkedList();
            visited = true;
            distance = Float.POSITIVE_INFINITY;
            parent = null;
        }
        
        public void addEdge(Edge e) {
            edges.addLast(e);
        }
        
        public int compareTo( Object o ) {
            // two nodes are equal if they have the same label
            Node n = (Node)o;
            return info.compareTo( n.info );
        }
        
        public Comparable getLabel() {
            return info;
        }
        
        public void setVisited(boolean value) {
        	visited = value;
        }
        
        public boolean getVisited( ) {
        	 return visited;
        } 

        public void setDistance(float distance) {
        	this.distance = distance;
        }
        
        public String toString() {
        	String s = "" + info;
//        	for( int i = 0 ; i < edges.size() ; i++ ) {
//        		s = s + info + " " + ((Edge)edges.get(i)).toString() + "\n";
//        	}
        	return s;
        }
    }
    
    private class Edge implements Comparable {
        private Node toNode;
        private float weight;
        
        public Edge(Node to, int weight) {
            toNode = to;
            this.weight = weight;
        }
        
        public int compareTo(Object o) {
            // two edges are equal if they point to the same node.
            // This assumes that the edges are starting from the same node !!!
            Edge n = (Edge)o;
            return n.toNode.compareTo(toNode);
        }
        
        public String toString() {
        	String s = toNode.getLabel()+ "  Wt: "+ Float.toString(weight);		
        	return s;
        }
    }
    
//    private LinkedList nodes;
    private Vector nodes;
    private Vector path;
    
    public Graph() {
//        nodes = new LinkedList();
    	nodes = new Vector();
    	path = new Vector();
    }
    


    public void addNode(Comparable label) {
    	Node temp = new Node(label); 
    	nodes.addLast(temp);
    }

    // ALL NODES ARE ADDED TO THE NODES VECTOR IN A SORTED MANNER
    public void addNodeSorted(Comparable label) {
  	Node temp = new Node(label); 
  	nodes.addSorted(temp);
  }
    
    // USES BINARY SEARCH (defined in vector class) BECAUSE ALL NODES ARE BEING STORED IN SORTED MANER
    private Node findNodeSorted(Comparable nodeLabel) {
		Node temp = new Node(nodeLabel); 
		return (Node)nodes.findElementSorted(temp) ;
    }
    
    private Node findNode(Comparable nodeLabel) {
		Node temp = new Node(nodeLabel); 
		return (Node)nodes.findElementUnsorted(temp) ;
    }
    
    public void addEdge(Comparable nodeLabel1, Comparable nodeLabel2, int weight) {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        n1.addEdge(new Edge(n2, weight));
    }
    
            
    public LinkedList findPath (Comparable nodeLabel1, Comparable nodeLabel2) {
    	boolean result = singleSourceShortestPaths(nodeLabel1);
    	
    	if(!result) {
    		System.out.println( "Error: Graph has negative weight cycle");
    		return null;
    	}
    	
    	Node temp = findNode(nodeLabel2);
    	Node startState = findNode(nodeLabel1);
    	LinkedList path = new LinkedList();
   
    	while(temp.compareTo(startState) != 0) {
    		path.addFirst(temp.info);
    		temp = temp.parent;
    	}
    	path.addFirst(temp.info);
    	
    	return path;
    }
    
    // Application of Bellman Ford algorithm
    private boolean singleSourceShortestPaths(Comparable nodeLabel1) {
    	
		Node startState = findNode(nodeLabel1);
		initialiseSingleSource(startState);
		
		// This loop ensures the |V| - 1 iterations 
		for( int i = 1; i<nodes.size(); i++) {
			
			// Next two loops iterate through all edges once
			for( int j = 0; j<nodes.size(); j++ ) {
//				Node tempNode = (Node)nodes.getElementData(j);
				Node tempNode = (Node)nodes.get(j);
				for( int k = 1; k<=tempNode.edges.getSize(); k++ ) {
					Edge tempEdge = (Edge)tempNode.edges.getElementData(k);
					relax(tempNode, tempEdge);
				}
			}
		}
		
		for( int j = 0; j<nodes.size(); j++ ) {
//			Node tempNode = (Node)nodes.getElementData(j);
			Node tempNode = (Node)nodes.get(j);
			for( int k = 1; k<=tempNode.edges.getSize(); k++ ) {
				Edge tempEdge = (Edge)tempNode.edges.getElementData(k);
				Node v = tempEdge.toNode;
				if (v.distance > tempNode.distance + tempEdge.weight) 
					return false;
			}
		}
		return true;
    }
    
    // Assumes that all the nodes are at present there included in the graph
    public Vector findNNodePath(Vector nodeList) {
    	int len = nodes.size();
    	Matrix adjacency = adjacencyListToMatrix(len);
    	Matrix distance = allPairShortestPath(adjacency);
    	
    	Vector nodeIndexList = new Vector();
    	int nodeListLength = nodeList.size();
    	
    	// Translates the node labels to node index numbers
    	// This is done so as to use the distance matrix returned by the allPairShortestPath method.
    	for(int i = 0; i<nodeListLength; i++) {
    		Comparable temp = (Comparable)nodeList.get(i);
    		Node tempNode = new Node(temp);
    		int index = nodes.findElementIndexSorted(tempNode);
    		nodeIndexList.addFirst(index);
    	}
    	
    	float pathCost = findShortestArrangement(distance, nodeIndexList, 0, Float.POSITIVE_INFINITY);
    	
    	Vector finalPath = new Vector();
    	for(int i = 0; i<path.size(); i++) {
    		int index = (int) path.get(i);
    		Node temp = (Node) nodes.get(index);
    		finalPath.addLast(temp.getLabel());
    	}
    	
    	return finalPath;
    }
    
    
    private float findShortestArrangement (Matrix distance, Vector nodeList, int start, float pathCost) 
    { 
    	int length = nodeList.size(); 
    	if (start == (length-1)) {
    		float sum = findPathCost(distance, nodeList);
    		if(sum < pathCost) {
    			pathCost = sum;
    			path = nodeList;
    			
    			path = new Vector();
    			for (int i = 0; i < length; i++) { 
    				path.addLast(nodeList.get(i));
    			}
    		}
    		return pathCost;
    	}
    	
       else { 
           for (int i = start; i < length; i++) { 
        	   nodeList.swapNodes(start, i); 
        	   pathCost = findShortestArrangement(distance, nodeList, start+1, pathCost); 
        	   nodeList.swapNodes(start, i); 
           } 
    	   return pathCost;
       } 
    }


    private float findPathCost(Matrix distance, Vector nodeList) {
    	float cost = 0;
    	for(int i=0; i<(nodeList.size())-1; i++) {
    			int row = (int)nodeList.get(i);
    			int col = (int)nodeList.get(i+1);
    			cost = cost + (float)distance.get(row, col);
    	}
    	return cost;
    }
    
    
    // Application of the Floyd Warshall algorithm
    // Assumes that there are no negative weight edges
    private Matrix allPairShortestPath(Matrix adjacency) {
    	int len = nodes.size();
    	Matrix distance = new Matrix(len);
    	
    	// Initially no indermediate nodes are being considered.
    	// If there is no direct edge between two nodes, their weight is being set as Positive Infinity
    	for (int i = 0; i < len; i++) 
			for (int j = 0; j < len; j++) {
//				int value = (int) adjacency.get(i, j)
				float weight = (float)adjacency.get(i, j) ;
				if(weight>0)
					distance.set(i, j, weight);
				else if(i==j)
					distance.set(i, j, (float)0);
				else 
					distance.set(i, j, Float.POSITIVE_INFINITY);
			}
				
    	// The loop k tracks the intermediate nodes.
    	// Find the shortest path between nodes i and j starting with no nodes in between
    	// Then keep adding intermediate nodes by iterating the value of k in the outer loop. 
    	for (int k = 0; k < len; k++) 
		{ 
			// Pick all vertices as source one by one 
			for (int i = 0; i < len; i++) 
			{ 
				// Pick all vertices as destination 
				for (int j = 0; j < len; j++) 
				{ 
					// If vertex k is on the shortest path from i to j, 
					// then update the value of distance between i and j nodes
					float distIK = (float) distance.get(i, k);
					float distKJ = (float) distance.get(k, j);
					float distIJ = (float) distance.get(i, j);
					
					if(distIK + distKJ < distIJ) 
						distance.set(i, j, distIK + distKJ );
				} 
			} 
		} 
    	return distance;
    }
    
    
    private void relax(Node u, Edge e) {
    	Node v = e.toNode;
    	if(v.distance > u.distance + e.weight) {
    		v.distance = u.distance + e.weight;
    		v.parent = u;
    	}
    }
    
    private void initialiseSingleSource(Node startState) {
    	for(int i = 0; i < nodes.size(); i++) {
//    		Node temp = (Node)nodes.getElementData(i);
    		Node temp = (Node)nodes.get(i);
    		if(temp.compareTo(startState) == 0)
    			temp.setDistance(0);

    		else 
    			temp.setDistance(Float.POSITIVE_INFINITY);
    		
    		temp.setVisited(false);
    		temp.parent = null;
//    		nodes.replaceNode(i, temp);
    		nodes.set(i, temp);
    	}
    }
    
    private void initialise () {
    	for(int i = 0; i < nodes.size(); i++) {
//    		Node temp = (Node)nodes.getElementData(i);
    		Node temp = (Node)nodes.get(i);
    		temp.setVisited(false);
    		temp.setDistance(Float.POSITIVE_INFINITY);
    		temp.parent = null;
    		nodes.set(i, temp);
    		
    	}
    }
    
    public Object findElement (Comparable label) {
    	Node temp = findNode(label);
    	if(temp == null)
    		return null;
    	
    	return temp.getLabel(); 
    }
    
    // Creates an adjacency matrix representation
    private Matrix adjacencyListToMatrix(int len) {
    	Matrix adjacency = new Matrix(len);
    	for(int i = 0; i< len; i++) {
    		Node temp =(Node)nodes.get(i);
    		int row = nodes.findElementIndexSorted(temp);
    		int edgeLength = temp.edges.getSize();
    		for (int j=1; j<=edgeLength; j++) {
    			Edge tempEdge = (Edge) temp.edges.getElementData(j);
    			Node toNode = tempEdge.toNode;
    			int col = nodes.findElementIndexSorted(toNode);
    			adjacency.set(row, col, tempEdge.weight);
    		}
    	}
    	
    	return adjacency;
    }
    
    public String toString() {
    	String s = nodes.toString();
//    	String s = path.toString();
    	return s;
    }
}