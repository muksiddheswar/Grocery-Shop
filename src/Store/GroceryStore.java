package Store;
import dataStructures.Vector;

public class GroceryStore implements GroceryStoreInterface{
	
	private DepartmentDirectory departments;
	private Stock productStock;
	private Stock freshProductStock;
	private ClientDirectory clients;
	private RequestQueue freshProductRequests;
	private RequestQueue shoppingList;
	
	
	public GroceryStore() {
		departments = new DepartmentDirectory();
		productStock = new Stock();
		freshProductStock = new Stock();
		clients = new ClientDirectory();
		freshProductRequests = new RequestQueue();
		shoppingList = new RequestQueue();
	}
	
	
	// It is assumed that the Department Name will not be more than 9 letters
	@Override
	public void addProduct(String department, String name, float price, int barcodeId, int count) {
		
		// Check if the department already exists
		if(departments.lookup(department) == null) {
			System.out.println("Error: Department does not exist. Product not added");
			return;
		}
		
		productStock.addProduct(department, name, price, barcodeId, count);
	}

	@Override
	public void addFreshProduct(String name, float pricePerKg, int barcodeId, float amountInKg) {
		freshProductStock.addFreshProduct(name, pricePerKg, barcodeId, amountInKg);
	}

	@Override
	public int addClient(String name) {
		return clients.addClient(name);
	}

	@Override
	public void addToBasket(int barcodeId, int count, int customerId) {
		GroceryItem temp = productStock.checkStock(barcodeId, count);
		if(temp!= null) {
			if(clients.addToBasket(temp.getProduct(), count, customerId, temp.isFresh())) {
				// Reduce quantity in stock if product added to basket.
				// Product will not be added if the client entry is not found in addToBasket()
				productStock.updateItemQuantity(temp, -count);
			}
		}
		else
			System.out.println("Error: Product does not exist or Not Enough Quantity.");
	}

	@Override
	public void removeFromBasket(int barcodeId, int count, int customerId) {
		boolean response = clients.removeFromBasket(barcodeId, count, customerId);
		if(response) {
			GroceryItem temp = productStock.lookupItem(barcodeId);
			productStock.updateItemQuantity(temp, count);
		}
	}

	@Override
	public void printBasket(int customerId) {
		clients.printBasket(customerId);
	}

	@Override
	public float computeBasketPrice(int customerId) {
		return clients.computeBasketPrice(customerId);
	}

	@Override
	public void requestFreshProduct(int barcodeId, float amount, int customerId) {
		GroceryItem temp = freshProductStock.lookupItem(barcodeId);
		if(temp == null ) {
			// Add new request to queue if fresh product is not present in stock
			freshProductRequests.addNewRequest(barcodeId, amount, customerId);
			return;
		}
		
		if(temp.getQuantity() < amount) {
			// Add new request if not enough quantity is present in stock
			freshProductRequests.addNewRequest(barcodeId, amount, customerId);
			return;
		}
		
		// Add Fresh Product to the client's basket if sufficient quantity present in stock
		boolean response = clients.addToBasket(temp.getProduct(), amount, customerId, temp.isFresh());
		if(response) {
			// Reduce quantity in stock if fresh product added to basket.
			// Fresh Product will not be added to basket if the client entry is not found in the addToBasket() function
			freshProductStock.updateItemQuantity(temp, -amount);
		}	
	}

	@Override
	public boolean serveNextRequest() {
		Request nextRequest = getRequest();
		if(nextRequest == null) return false;
		
		GroceryItem temp = freshProductStock.lookupItem(nextRequest.getBarcodeId());		
		boolean response = clients.addToBasket(temp.getProduct(), nextRequest.getQuantity(), nextRequest.getClientId(), temp.isFresh());
		if(response) {
			// Reduce quantity in stock if fresh product added to basket.
			// Fresh Product will not be added to basket if the client entry is not found in addToBasket()
			freshProductStock.updateItemQuantity(temp, -nextRequest.getQuantity());
			freshProductRequests.removeRequest(nextRequest);
			return true;
		}
		return false;
	}

	@Override
	public void printUnservedRequests() {
		System.out.println(freshProductRequests);
	}

	@Override
	public void addDepartment(String departmentName) {
		departments.addDepartment(departmentName);
	}

	@Override
	public void checkout(int customerId) {
		clients.checkout(customerId);
	}

	@Override
	public void connectDepartments(String department1, String department2) {
		departments.connectDepartments(department1, department2, 1);
	}

	@Override
	public void shortestPath(String department1, String department2) {
		departments.shortestPath(department1, department2);		
	}

	@Override
	public void printShoppingHistory(int customerId) {
		clients.printShoppingHistory(customerId);
	}

	@Override
	public void addToShoppingList(int barcodeId, int count, int customerId) {
		shoppingList.addNewRequest(barcodeId, count, customerId);
	}

	@Override
	public void clearShoppingList(int customerId) {
		shoppingList.removeAllRequests();
	}

	@Override
	public void printsOptimalPath(int customerId) {
		Vector departmentNameList = new Vector();
		
		// The following loop creates a list of departments that need to be visited
		// The departmetns are obtained by scanning the shopping list.
		for(int i = 1; i<= shoppingList.getSize(); i++) {
			Request temp = shoppingList.getRequest(i);
			String departmentName = productStock.getDepartment(temp.getBarcodeId());
			departmentNameList.addLast(departmentName);
		}
		System.out.println(departments.optimalPath(departmentNameList));		
	}
	
	
// -------------------------------------------------------------------------------
// HELPER METHODS
// -------------------------------------------------------------------------------	

	
	// Gets the first fresh product request that can be served.
	// Returns null if none of the requests can be served.
	public Request getRequest() {
		int size = freshProductRequests.getSize();
		for(int i =1; i<=size; i++) {
			Request nextRequest = freshProductRequests.getRequest(i);
			GroceryItem temp = freshProductStock.checkStock(nextRequest.getBarcodeId(), nextRequest.getQuantity());
			if(temp != null) return nextRequest;
		}
		return null;
	}
	
	
	public void printStock() {
		System.out.println("\nPRODUCTS:");
		productStock.print();
		System.out.println("\nFRESH PRODUCTS:");
		freshProductStock.print();
	}

}
