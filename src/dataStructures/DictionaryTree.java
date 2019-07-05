package dataStructures;

public class DictionaryTree {
	private Tree data;
	
	public DictionaryTree() {
		data = new Tree();
	}
		
	public void add(Comparable key,Object value) {
		DictionaryPair pair = new DictionaryPair(key, value);
		data.insert(pair);
	}
	
	// Accepts the key and returns the value
	public Object findKey(Object key) {
		DictionaryPair pair = new DictionaryPair((Comparable)key);
		pair = (DictionaryPair)data.findElement(pair);
		if(pair == null)
			return null;
		return pair.getValue();
	}
	
	public void updateNode (Comparable key, Comparable value) {
		DictionaryPair pair = new DictionaryPair(key, value);
		data.updateNode(pair, pair);
	}
		
	public void print() {
		data.print();
	}
}
