package Store;
import java.util.Comparator;

public class GroceryItem implements Comparable{
		private Product item;
		private float quantity;
		private boolean isFresh;
		private String department;
		
		public GroceryItem (Product item, float quantity, boolean isFresh) {	// Constructor for GroceryItem Node
			this.item = item;
			this.quantity = quantity;
			this.isFresh = isFresh;
			this.department = "";
		}
		
		public GroceryItem (Product item, float quantity, boolean isFresh, String department ) {	// Constructor for GroceryItem Node
			this.item = item;
			this.quantity = quantity;
			this.isFresh = isFresh;
			this.department = department;
		}
		
		public GroceryItem(Product item) {
			this.item = item;
			this.quantity = 0;
			this.isFresh = false;
			this.department = "";
		}
		
		public String getName() {
			return item.getName();
		}
		
		public int getId() {
			return item.getId();
		}
		
		public float getQuantity() {
			return quantity;
		}
		
		public boolean isFresh() {
			return isFresh;
		}
		
		public float getCost() {
			return quantity*item.getPrice();
		}
		
		public Product getProduct() {
			return item;
		}
		
		public void setQuantity (float quantity) {
			this.quantity = quantity;
		}
		
		public String getDepartment() {
			return department;
		}
		
//		public String toString () {			
//			String s = Integer.toString(getId()) + " " + " " + getName() + " " + Float.toString(getQuantity()) + "\n";
//			return s;
//		}
		
		public String toString () {	
			String s = item.toString()+ "  " + department+ "  " + Float.toString(quantity)+ " ";
			if(isFresh) {
				//s = s + "kg FRESH  EUR "+ quantity*item.getPrice()+"\n";
				s = s + "kg FRESH   "+ "\n";
			}
			else {
//				s = s +"EUR "+ quantity*item.getPrice() + "\n";
				s = s +  "\n";
			}
			return s;
		}
		
		
		public int compareTo(Object o) {
			GroceryItem temp = (GroceryItem) o;
			return ((Comparable)item.getId()).compareTo(temp.item.getId());
		}
	}