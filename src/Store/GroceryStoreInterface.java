package Store;

public interface GroceryStoreInterface {

	
	/**
	 * Adds a product to the store 
	 * 
	 * @param department - represents a department to which is the product added
	 * @param name - represents the name of the new product
	 * @param price - represents the price of the new product
	 * @param barcodeId - is the bar-code number of the new product 
	 * @param count - is the number of items of the new product in the store
	 */
	void addProduct(String department, String name, float price, int barcodeId, int count);
	
	/**
	 * Adds a fresh product to the store
	 * 
	 * @param name - represents the name of the new fresh product
	 * @param pricePerKg - represents the price of the new fresh product
	 * @param barcodeId - is the bar-code number of the new fresh product (identifying only the type)
	 * @param amountInKg - is the amount in kilograms the new fresh product in the store
	 */
	void addFreshProduct(String name, float pricePerKg, int barcodeId, float amountInKg);
	
	/**
	 * Registers a new client in the store
	 * 
	 * @param name - name of the client
	 * @return a unique id number identifying the client
	 */
	int addClient(String name);
	
	/**
	 * Adds a product to the basket for a specific client. Make sure you adjust
	 * the amount of the product that is available at the store for others. 
	 * 
	 * @param barcodeId - the bar code of the product that is added to the basket 
	 * @param count - number of the packages of the product that is added to the basket 
	 * @param customerId - the client id that is adding the product to the basket
	 */
	void addToBasket(int barcodeId, int count, int customerId);
	
	/**
	 * Removes a product to the basket for a specific client. Make sure you adjust
	 * the amount of the product that is available at the store for others.
	 * 
	 * @param barcodeId - the bar code of the product that is removed added from the basket
	 * @param count - number of the packages of the product that is removed from the basket
	 * @param customerId - the client id that is removing the product from the basket
	 */
	void removeFromBasket(int barcodeId, int count, int customerId);
	
	/**
	 * Prints the content of a basket for a specific client
	 * 
	 * @param customerId - the client id whose basket will be printed out
	 */
	void printBasket(int customerId);
	
	/**
	 * Computes the total price of the basket for a specific client
	 * 
	 * @param customerId - the client id whose basket will be printed out
	 * @return total price of products in the basket
	 */
	float computeBasketPrice(int customerId);
	
	/**
	 * Requests an amount in kg of the fresh product. If the request is
	 * successful, it is automatically added to the basket. 
	 * 
	 * @param barcodeId - the bar code of the fresh product that is requested by the client
	 * @param amount - amount in kilograms that is requested by the client
	 * @param customerId - the client id of the client making the request
	 */
	void requestFreshProduct(int barcodeId, float amount, int customerId);
	
	/**
	 * Processes the next request in the queue. If the request can be produces
	 * then it adds the product to the basket. 
	 * 
	 * Note: Fresh product cannot be removed from the basket. Once it is
	 * requested it has to be paid by the client
	 * 
	 * @return true if the next request was successfully processed, false otherwise
	 */
	boolean serveNextRequest();
	
	/**
	 * This method will print currently unserved requests that are waiting to be processed 
	 */
	void printUnservedRequests();
	
	/**
	 * This method adds a new department to the store
	 * 
	 * @param departmentName - is the name of the department that is added to the store
	 */
	void addDepartment(String departmentName);
	
	/**
	 * Checkouts specified client and prints a bill.
	 * 
	 * @param customerId - the client id of the client that checkouts the items from the
	 *     basket and finalizes the purchase 
	 */
	void checkout(int customerId);
	
	
	/**
	 * Connecting 2 departments. Two departments are connected when there is a direct aisle
	 * between them. 
	 * 
	 * @param department1 - name of the first department that will be connected.
	 * @param department2 - name of the second department that will be connected.
	 */
	void connectDepartments(String department1, String department2);
	
	/**
	 * Prints the shortest path between 2 departments. 
	 * 
	 * @param department1 - name of the source department.
	 * @param department2 - name of the destination department.
	 */
	void shortestPath(String department1, String department2);
	
	/**
	 * Prints a summary of previous purchases
	 * 
	 * @param customerId - the client id for which the shopping history will be printed
	 */
	void printShoppingHistory(int customerId);
	
	/**
	 * Adds a product to the shopping list of a specific client. 
	 * 
	 * @param barcodeId - the bar code of the product that is added to the shopping list 
	 * @param count - number of the packages of the product that is added to the shopping list
	 * @param customerId - the client id that is adding the product to the shopping list
	 */
	void addToShoppingList(int barcodeId, int count, int customerId);
	
	/**
	 * Removes all items from the shopping list. 
	 * 
	 * @param customerId - the client id for which the shopping list will be cleared.
	 */
	void clearShoppingList(int customerId);
	
	/**
	 * Prints optimal path to buy all products from the shopping list 
	 * 
	 * @param customerId - the client id for which the shopping list will be cleared.
	 */
	void printsOptimalPath(int customerId);
		
}
