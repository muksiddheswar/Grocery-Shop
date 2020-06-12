# GroceryShop

The various entities constituting the store are as follows:

**Product**: A grocery store has products. The product is characterised by 3 properties:    
* name
* price 
* unique barcode id.     

<br>
A grocery store can have multiple instances of the a product.     
There are classes representing clients and shopping baskets.     
The basket can contain multiple products and is associated with a client. 
The client is allowed to:

1. Add an item (a piece of a product) into the basket.
2. Remove an item from the basket
3. Print all products that are in a basket


**Fresh Product**: A Fresh product cannot be taken by the customer directly, but is provided on demand in the store. A client can request a product and he is notified once it is available. The principle here is first comes is first served.


**Departments**: Every product is located inside some department. A department is characterised by a name that does not repeat in one store.

When a customer **checks out**, the content of the basket is printed in a bill and the items are added to the shopping history. After printing a bill, the content of the basket is removed simulating that the customer took the content home.

Grocery store supports adding multiple products of a category to the basket while shopping.



**Shopping List**: Customers can **add** and **remove** any type of product to the shopping list. The product in the shopping list is characterized by the bar code.
 

There is a support for searching the **shortest path** in order to buy all the items in the customer’s shopping list.     
To do a floor plan of the shop is created which will be represented as neighbouring departments. Two departments are neighbouring, if there is a direct aisle between them and no other department needs to be crossed. The shortest path between two departments/products in the shop can be searched as well.
