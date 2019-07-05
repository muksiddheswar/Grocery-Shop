package Store;
import java.util.Comparator;

import dataStructures.LinkedList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Client implements Comparable {
	private String name;
	private int clientId;
	private Basket basket;
	private LinkedList shoppingHistory;
	
	private class HistoryItem {
		Basket basket;
		String date;
		
		public HistoryItem(Basket basket, String date) {
			this.basket = basket;
			this.date = date;
		}
		
		public String toString () {
			String s = "\n"+ date+ "\n" +basket.toString()+ "\nTotal Price: EUR "+ basket.computePrice()+ "\n";
			return s;
		}
	}
	
	public Client(String name, int clientId) {
		this.name = name;
		this.clientId = clientId;
		basket = new Basket();
		shoppingHistory = new LinkedList();
	}
	
	public Client(int clientId) {
		this.name = null;
		this.clientId = clientId;
		basket = new Basket();
		shoppingHistory = new LinkedList();
	}
	
	public int getId() {
		return clientId;
	}
	
	public String getName() {
		return name;
	}
	
	public Basket getBasket() {
		return basket;
	}
	
	public void addToBasket(Product item, float quantity, boolean isFresh) {
		basket.addItem(item, quantity, isFresh);
	}
	
	public boolean removeFromBasket(int barcodeId, int count) {
		return basket.removeItem(barcodeId, count);	
	}
	
	public void printBasket() {
		String s = "";
		if(basket.getSize() ==0) {
			s = "\nNo items in Basket for " + toString();
		}
		else {
			s = "\nBasket Items for " + toString() + basket.toString();
		}
		System.out.print(s);
	}
	
	public String toString () {			
		String s = Integer.toString(clientId)+ "   "+ name+ "\n";
		return s;
	}
	
	public float computeBasketPrice() {
		float price = basket.computePrice();
		return price;
	}
	
	public void addToHistory() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = LocalDateTime.now().format(formatter);
		HistoryItem history = new HistoryItem(basket, formatDateTime);
		shoppingHistory.addLast(history);
		basket = new Basket();
	}
	
	public void printHistory() {
		System.out.println(shoppingHistory);
	}
	
	public int compareTo(Object o) 
	{
		Client customer = (Client) o;
		return ((Comparable)clientId).compareTo(customer.clientId);
	}
}