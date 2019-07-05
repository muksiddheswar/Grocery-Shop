import Store.GroceryStore;

public class Main {

	public static void main(String[] args) {
		GroceryStore store = new GroceryStore();
		
		store.addDepartment("Electronics");
		store.addDepartment("Frozen");
		store.addDepartment("Snacks");
		store.addDepartment("Stationery");
		store.addDepartment("Apparel");
		store.addDepartment("Vegetables");
		store.addDepartment("Drinks");
		
		
		store.addProduct("Electronics","Ipad", 1000f, 900, 40);
		store.addProduct("Electronics","Cellphone", 2000f, 901,20 );
		store.addProduct("Frozen","Ice Cream", 5f, 774400, 200);
		store.addProduct("Frozen","Yogurt", 5f, 774411, 500);
		store.addProduct("Snacks","Cheetos", 5f, 775500, 200);
		store.addProduct("Snacks","Biscuit", 12f, 775501, 100);
		store.addProduct("Stationery","Pencil", 10f, 1100, 500);
		store.addProduct("Stationery", "Pen", 15f, 1101, 100);
		store.addProduct("Apparel","Shirt", 50f, 2200, 50);
		store.addProduct("Apparel","T Shirt", 20f, 2201, 50);
		store.addProduct("Vegetables", "Brinjal", 9.32f, 1800, 50);
		store.addProduct("Vegetables", "Beans", 23.89f, 1801, 100);
		store.addProduct("Drinks", "Ice Tea", 12f, 7700, 100);
		store.addProduct("Drinks", "Cola", 12f, 7701, 100);
		
		store.addFreshProduct("Fish", 15.4f, 228, 5.08f);
		store.addFreshProduct("Meat", 9.78f, 450, 24);
		
		System.out.println(store.addClient("Siddheswar"));
		System.out.println(store.addClient("Andrew"));
		System.out.println(store.addClient("Ryan"));
		System.out.println(store.addClient("Lica"));
		
		store.addToBasket(2201, 10, 1733168631);
		store.printBasket(1733168631);
		store.removeFromBasket(2201,5 ,1733168631);
		store.printBasket(1733168631);
		store.addToBasket(7701, 5, 1733168631);
		System.out.println(store.computeBasketPrice(1733168631));
		
		store.requestFreshProduct(450, 20, 1733168631);
		store.printBasket(1733168631);
		store.removeFromBasket(450, 10, 1733168631);
		store.printBasket(1733168631);
		
		store.requestFreshProduct(228, 6, 1733168631);
		store.requestFreshProduct(111, 10, 1733168631);
		
		store.serveNextRequest();
		store.addFreshProduct("Pork", 11.0f, 111, 18.05f);
		store.serveNextRequest();
		store.printBasket(1733168631);
		
		store.printUnservedRequests();
		store.checkout(1733168631);
		store.printShoppingHistory(1733168631);
		
		
//		store.connectDepartments("Electronics", "Stationery");
		store.connectDepartments("Electronics", "Frozen");
		store.connectDepartments("Electronics", "Drinks");
		store.connectDepartments("Electronics", "Vegetables");
		
		store.connectDepartments("Vegetables", "Snacks");
//		store.connectDepartments("Vegetables", "Electronics");
		store.connectDepartments("Vegetables", "Apparel");
		
//		store.connectDepartments("Snacks", "Vegetables");
//		store.connectDepartments("Snacks", "Electronics");
		
		store.connectDepartments("Apparel", "Stationery");
//		store.connectDepartments("Apparel", "Vegetables");
//		store.connectDepartments("Apparel", "Drinks");
		
//		store.connectDepartments("Drinks", "Apparel");
		store.connectDepartments("Drinks", "Frozen");
		store.connectDepartments("Drinks", "Stationery");
		
//		store.connectDepartments("Stationery", "Electronics");
//		store.connectDepartments("Stationery", "Drinks");
		
//		store.connectDepartments("Frozen", "Electronics");
		store.connectDepartments("Frozen", "Snacks");
//		store.connectDepartments("Frozen", "Apparel");
		
//		store.shortestPath("Apparel", "Snacks");
		
		store.addToShoppingList(2200, 10, 1733168631);		//Apparel - Shirt
		store.addToShoppingList(775501, 10, 1733168631); 	// Snacks - Biscuits
		store.addToShoppingList(7700, 10, 1733168631); 		// Drinks
		store.addToShoppingList(1800, 10, 1733168631);		// Vegetables
		
		store.printsOptimalPath(1733168631);

	}

}
