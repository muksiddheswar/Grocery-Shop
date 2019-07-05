package Store;
import dataStructures.DictionaryTree;

public class ClientDirectory {
	private DictionaryTree clientTree;
	
	public ClientDirectory() {
		clientTree = new DictionaryTree();
	}
	

	// Takes in string and generates unique string.
	// This is a crude mimic of hash key generation
	private int generateId(String string) {
		int key = 7;
		for (int i = 0; i < string.length(); i++)
			key = key*7 + string.charAt(i);
		
		return key;
	}
	
	// Stores the customer information in a dictionary tree
	// Customer ID is used as a key and the client object is the value
	public int addClient(String name) {
		int customerId = generateId(name);
		Client customer = lookupClient(customerId);
		if(customer == null) {
			customer = new Client(name, customerId);
			clientTree.add(customerId, customer);
			return customerId;
		}
		
		System.out.println("Error: Client already exists");
		return -1;
	}
	
	public Client lookupClient(int customerId) {	
		return (Client)clientTree.findKey(customerId);
	}
	
	public boolean addToBasket(Product item, float count, int customerId, boolean isFresh) {
		Client c2 = lookupClient(customerId);
		if(c2 == null) {
			System.out.println("Error: Client Entry not found.");
			return false;
		}
		
		c2.addToBasket(item, count, isFresh);
		clientTree.updateNode(customerId, c2);
		return true;
	}
	
	public boolean removeFromBasket(int barcodeId, int count, int customerId) {
		Client c2 = lookupClient(customerId);
		if(c2 == null) {
			System.out.println("Error: Client Entry not found.");
			return false;
		}
		
		boolean response = c2.removeFromBasket(barcodeId, count);
		if(response)
			clientTree.updateNode(customerId, c2);
		return response;
	}
	
	public float computeBasketPrice(int customerId) {
		Client c2 = lookupClient(customerId);
		if(c2 == null) {
			System.out.println("Error: Client Entry not found.");
			return 0;
		}
		
		return c2.computeBasketPrice();
	}
	
	public void printBasket(int customerId) {
		Client c2 = lookupClient(customerId);
		if(c2 == null) {
			System.out.println("Error: Client Entry not found.");
			return;
		}
		c2.printBasket();
	}
	
	public void checkout(int customerId) {
		Client c2 = lookupClient(customerId);
		if(c2 == null) {
			System.out.println("Error: Client Entry not found.");
			return;
		}
		System.out.println("Checkout for Client");
		System.out.println(c2);
		System.out.println((c2.getBasket()));
		System.out.print("Total Price: EUR ");
		System.out.println(c2.computeBasketPrice());
		c2.addToHistory();
	}
	
	public void printShoppingHistory(int customerId) {
		Client c2 = lookupClient(customerId);
		if(c2 == null) {
			System.out.println("Error: Client Entry not found.");
			return;
		}
		c2.printHistory();
	}
	
	
	public String toString () {
		String s = "\nClient List: \n" + clientTree.toString() ;
		return s;
	}
}
