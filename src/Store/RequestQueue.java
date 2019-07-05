package Store;
import dataStructures.Queue;

public class RequestQueue {

	private Queue requests; 
	
	public RequestQueue () {
		requests = new Queue();
	}
	
	public Request getNextRequest() {
		return (Request)requests.top();
	}
	
	public void addNewRequest(int barcodeId, float quantity, int customerId) {
		Request newRequest = new Request(barcodeId, quantity, customerId);
		requests.push(newRequest);
		System.out.println("Request Added To Queue");
	}
	
	public void removeRequest(Request o) {
		requests.findAndRemoveElement((Comparable)o);
	}
	
	public void removeAllRequests() {
		requests = new Queue();
	}
	
	public int getSize() {
		return requests.size();
	}
	
	public Request getRequest(int i) {
		return (Request)requests.getElementData(i);
	}
	
	public String toString () {
		String s = "\nUnserved Requests: \n" + requests.toString() ;
		return s;
	}
}
