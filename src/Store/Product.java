package Store;
import java.util.Comparator;

public class Product implements Comparable{
	private int barcodeId;
	private String name;
	private float price;
	
	public Product (int barcodeId, String name, float price) {
		this.barcodeId = barcodeId;
		this.name = name;
		this.price = price;
	}
	
	public Product (int barcodeId) {
		this.barcodeId = barcodeId;
		this.name = null;
		this.price = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return  barcodeId;
	}
	
	public float getPrice() {
		return price;
	}
	
	public String toString () {	
		String s = Integer.toString(barcodeId) + "  " + name + "  " + Float.toString(price);
		return s;
	}
	
	public int compareTo (Object o) {
		Product item = (Product) o;
		return ((Comparable)barcodeId).compareTo(item.barcodeId);
	}
}
