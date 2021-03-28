import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main_lock {
	public static ReentrantLock lock = new ReentrantLock(true);
	
	public static void main(String[] args) throws InterruptedException {
	
		Movie_lock Lucy = new Movie_lock("Lucy", 10, 150, 1, "15:30");
		Movie_lock Godzilla = new Movie_lock("Godzilla", 10, 120, 2, "18:00");
		Movie_lock Extraction = new Movie_lock("Extraction", 10, 200, 5, "17:00");
		Lucy.initialize();
		Godzilla.initialize();
		Extraction.initialize();
		System.out.println("Welcome to the ticket booking counter..");
		System.out.println();
		
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		Callable<String> customer1 = new Customer_lock("John doe", 6, Lucy);
		Callable<String> customer2 = new Customer_lock("Lily owens", 2, Lucy);
		Callable<String> customer3 = new Customer_lock("Percy Jackson", 3, Lucy);
		Callable<String> customer4 = new Customer_lock("Amy Winehouse", 2, Lucy);
		Callable<String> customer5 = new Customer_lock("Bertha Stewart", 5, Lucy);
		Callable<String> customer6 = new Customer_lock("Emma Watson", 3, Godzilla);
		Callable<String> customer7 = new Customer_lock("Jack Nicholson", 6, Extraction);
		Callable<String> customer8 = new Customer_lock("Christopher Nolan", 2, Extraction);
		Callable<String> customer9 = new Customer_lock("Timothée Chalamet", 2, Extraction);
		Callable<String> customer10 = new Customer_lock("Siorse Ronan", 3, Extraction);
		
		service.invokeAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5, customer6, customer7, customer8, customer9, customer10));
		service.shutdown();
		while (!service.isTerminated())
			;

	}
}
