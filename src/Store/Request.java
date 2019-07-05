package Store;
import java.util.Comparator;

public class Request implements Comparable{
	private int barcodeId;
	private float quantity;
	private int customerId;
	
	public Request (int barcodeId, float quantity, int customerId) {
		this.barcodeId = barcodeId;
		this.quantity = quantity;
		this.customerId = customerId;
	}
	
	public int getBarcodeId() {
		return barcodeId;
	}
	
	public int getClientId() {
		return customerId;
	}
	
	public float getQuantity() {
		return quantity;
	}
	
	public String toString() {
		String s = Integer.toString(barcodeId) + "  " + Float.toString(quantity) +  "  " + Integer.toString(customerId) +"\n";
		return s;
	}
	
	public int compareTo(Object o) {
		Request next = (Request) o;
		return ((Comparable)barcodeId).compareTo(next.getBarcodeId());
	}
}
