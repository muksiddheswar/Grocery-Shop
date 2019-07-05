package dataStructures;
// MATRIX REPRESENTATION USING VECTORS

public class Matrix
{	
	// some appropriate private members .
	private Vector data;
	
	public Matrix ( int nrNodes ) {
	// allocate an N-by -N matrix where N = nrNodes
	// all elements are initially 0
		data = new Vector();
		
		for(int i =0; i<nrNodes; i++) {
			Vector colData = new Vector(nrNodes);
			for(int j = 0; j<nrNodes; j++)
				colData.addLast((float)0);

			data.addLast(colData);
		}
	}
	
	public void set( int row , int col , Comparable weight ) {
	// store the weight at the given row and column .
		((Vector)data.get(row)).set(col, weight);
	}
	
	public Comparable get( int row , int col) {
	// return the weight at the given row and column .
		return (Comparable)((Vector)data.get(row)).get(col);
	}
	
	public String toString() {
		String s ="";
		for(int i =0; i<data.size(); i++) 
			s = s+ data.get(i).toString()+ "\n";

		return s;
	}
}