package Store;
import dataStructures.LinkedList;

public class Basket {
	
	private LinkedList itemsInBasket;
	
	public Basket() {
		itemsInBasket = new LinkedList();
	}
	
	public int getSize() {
		return itemsInBasket.getSize();
	}
	
	public void addItem (Product item, float quantity, boolean isFresh) {
		// If the product already added to basket then the quantity is added to the present quantity in basket.
		// If not present, a new basket entry is made.
		int barcodeId = item.getId();
		GroceryItem temp = lookupProduct(barcodeId);
		if(temp == null) {
			temp = new GroceryItem(item, quantity, isFresh); //Lookup if the item is already present in the basket
			itemsInBasket.addLast(temp);
		}
		
		else {
			temp.setQuantity(quantity+ temp.getQuantity());
			itemsInBasket.findAndReplaceElement(barcodeId, temp);
		}
	}
	
	public boolean removeItem (int barcodeId, int count) {
		
		GroceryItem temp = lookupProduct(barcodeId);
		if(temp == null) {
			System.out.println("Error: Item not present in basket");
			return false;
		}
		
		if(temp.getQuantity() < count) {
			// If the basket has less quantity than count
			System.out.println("Error: Please review the quantity");
			return false;
		}
		
		if(temp.isFresh()) {
			System.out.println("Error: Requested product is of type fresh. Fresh Products cannot be removed from the basket");
			return false;
		}
		
		if(temp.getQuantity() == count) {
			//If the qty of product in basket is same as count
			// Removing basket entry
			itemsInBasket.findAndRemoveElement(temp);
			return true;
		}
		
		temp.setQuantity(temp.getQuantity() - count);
		GroceryItem temp2 = new GroceryItem(new Product(barcodeId));
		itemsInBasket.findAndReplaceElement(temp2, temp);
		return true;
	}
	
	public float computePrice() {
		int basketSize = itemsInBasket.getSize();
		float price = 0;
		for(int i=1 ; i<=basketSize; i++) {
			GroceryItem temp = (GroceryItem)itemsInBasket.getElementData(i);
			price = price + (temp.getCost());
		}
		return price;
	}
	
	public GroceryItem lookupProduct(int barcodeId) {
		GroceryItem g2 = (GroceryItem)itemsInBasket.findElement(new GroceryItem(new Product(barcodeId)));
		return g2;
	}
	
	public String toString () {	
		String s;
		s = itemsInBasket.toString();
		return s;
	}
}