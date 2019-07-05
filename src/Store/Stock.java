package Store;
import dataStructures.DictionaryTree;

public class Stock {

	private DictionaryTree inventory;
	
	public Stock() {
		inventory = new DictionaryTree();
	}
	
	public void addProduct(String department, String name, float price, int barcodeId, int count) {
		GroceryItem temp = lookupItem(barcodeId);
		if(temp!= null) {
			// If product already present, then update it's quantity.
			updateItemQuantity(temp, count);
			return;
		}
		
		// Inserting new product entry into the store
		Product item = new Product(barcodeId, name, price); 
		GroceryItem node = new GroceryItem(item, count, false, department);
		inventory.add(barcodeId, node);
	}
	
	public void addFreshProduct(String name, float pricePerKg, int barcodeId, float amountInKg) {
		GroceryItem temp = lookupItem(barcodeId);
		if(temp!= null) {
			//If product already present, then update it's quantity.
			updateItemQuantity(temp, amountInKg);
			return;
		}
		
		// Inserting new product entry into the store
		Product item = new Product (barcodeId, name, pricePerKg); 
		GroceryItem node = new GroceryItem(item, amountInKg, true);
		inventory.add(barcodeId, node);
	}
	
	public String getDepartment(int barcodeId) {
		GroceryItem temp = lookupItem(barcodeId);
		String department = temp.getDepartment();
		return department;
	}
 
	//Checks if the product is present in the Store.
	//If product is present then if sufficient stock is there in the store.
	public GroceryItem checkStock(int barcodeId, float quantity) {
		
		GroceryItem temp = lookupItem(barcodeId);
		
		if(temp == null) 
			return null;
		
		if(temp.getQuantity()< quantity) 
			return null;
		
		return temp;	
	}
	
	// Searches and returns Grocery Item 
	public GroceryItem lookupItem(int barcodeId) {
		return (GroceryItem)inventory.findKey(barcodeId);
	}
	
	// Updates the present quantity of a product/freshProduct 
	public void updateItemQuantity (GroceryItem oldValue, float quantity) {
		GroceryItem newValue = oldValue;
		newValue.setQuantity(newValue.getQuantity()+quantity);
		inventory.updateNode(newValue.getId(), newValue);
	}
	
	
	public void print() {
		inventory.print();
	}
}
