package dataStructures;
public class Vector
{
	protected Object data[];
	protected int count;
	
	public Vector(int capacity) {
		data = new Object[capacity];
		count = 0;
	}
	
	public Vector() {
		data = new Object[10];
		count = 0;
	}

	public int size() {
		return count;
	}
 
	public boolean isEmpty() {
		return size() == 0;
	}

	public Object get(int index) {
		return data[index];
	}

	public void set(int index, Object obj) {
		data[index] = obj;
	}

	public boolean contains(Object obj) {
		for(int i=0;i<count;i++) {
			if(data[i] == obj) return true;
		}
		return false;
	}
	
	public void addFirst(Object item) {
		for(int i=count; i>0;i--)
			data[i]=data[i-1];
		data[0]=item;
		count++;
	}

	public void addLast(Object o) {
		if(count == data.length)
			extendCapacity();
		data[count] = o;
		count++;
	}
	
	public Object getFirst() {
		return data[0];
	}

	public Object getLast() {
		return data[count-1];
	}

	public String toString() {
		String s = "";//data[0];
		for(int i=0;i<count;i++)
			s = s+ data[i].toString()+ "\n";
		return s;
	}
		
	public void removeLast() {
		count--;
	} 

	public void removeFirst() {
		for(int i=1; i<count;i++)
			data[i-1]=data[i];
		count--;
	}
	
	public void reverse() {
		Object t;
		for(int i=0;i<(count/2);i++) {
			t=data[i];
			data[i]=data[(count-1)-i];
			data[(count-1)-i]=t;
		}
	}
	
	public Vector vector_double() {
		Vector vec2 = new Vector(count*2);
		for(int i=0,j=0; i<count;i++) {
			vec2.data[j]=data[i];
			j++;
			vec2.data[j]=data[i];
			j++;
		}
		vec2.count=count*2;
		return vec2;
	}
	
	public Vector vector_interleave(Vector v2) {
		Vector vec2 = new Vector(count+v2.count);
		for(int i=0,j=0,k=0;i<(count+v2.count);) {
			if(j<count) {
				vec2.data[i]=data[j];
				j++; 
				i++;	
			}
			if(k<v2.count) {
				vec2.data[i]=v2.data[k];
				k++;
				i++;
			}	
		}
		vec2.count=count+v2.count;
		return vec2;
	}
	
	public void extendCapacity() {
		Object data2 []= new Object[2*data.length];
		for(int i=0; i<count;i++) {
			data2[i]=data[i];
		}
		data = data2;
	}
	
	// Linear search for unsorted vectors
	public Object findElementUnsorted (Comparable o) {
		for(int i=0; i<count; i++)
			if (((Comparable)data[i]).compareTo(o) == 0) return data[i];
		
		return null;
	}
	
	// Assumes that the Vector is sorted; hence uses binary search 
	public Object findElementSorted (Comparable o) {
		int index = binarySearch(o);
		if( index<0 )
			return null;
		
		return data[index];
	}
	
	public int findElementIndexSorted (Comparable o) {
		int index = binarySearch(o);
		return index;
	}
	
	public void addSorted(Object o) {
		addLast(o);
		insertionSort();
	}
	
	private void insertionSort() {
		for(int i = 1; i<count; i++) {
			Comparable temp = (Comparable) data[i];
			int j = i-1;
			
			while(j>=0 && temp.compareTo(data[j]) < 0) {
				data[j+1] = data[j];
				j--;
			}
			
			data[j+1] = temp;
		}
	}
	
	public int binarySearch(Object key){
	int start = 0;
	int end = count - 1;
	while(start <= end)
	{
		int middle = (start + end + 1) / 2;

		int response = ((Comparable)key).compareTo((Comparable) data[middle]);
		
		if (response < 0) end = middle - 1;
		else if(response > 0) start = middle+1;
		else return middle;
	}
	return -1;
	}
	
	public void swapNodes(int i, int j) {
		Object temp = data[j];
		data[j] = data[i];
		data[i] = temp;
	}
}