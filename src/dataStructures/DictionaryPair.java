package dataStructures;

import java.util.Comparator;

public class DictionaryPair implements Comparable{
	
	private Comparable key;
	private Object value;
	
	public DictionaryPair (Comparable  someKey ,Object  someValue){
		key = someKey;
		value = someValue;		
	}
	
	public DictionaryPair (Comparable  someKey){
		key = someKey;
		value = null;		
	}
	
	public Comparable getKey(){
		return key;
	}
	
	public Object getValue(){
		return value;
	}
	
	public void setKey (Comparable newKey){
		key = newKey;
	}
	
	public void setValue(Object newValue){
		value = newValue;
	}

	@Override
	public int compareTo(Object o) {
		//Comparable temp = (Comparable) o;
		return key.compareTo(((DictionaryPair)o).key);
	}
	
	@Override
	public String toString() {
		String s = value.toString();
		return s;
	}
}

