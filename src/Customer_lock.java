import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Customer_lock implements Callable<String> {
	String name;
	int seatsRequired;
	Movie_lock watch;

	public Customer_lock(String name, int seats, Movie_lock watch) {
		this.name = name;
		seatsRequired = seats;
		this.watch = watch;
	}

	public String call() {
	   Main_lock.lock.lock();
		try {
			if (watch.seats < seatsRequired) {
				if (watch.seats == 0) {
					System.out.println("Sorry " + name + ", Housefull!.(" + (seatsRequired) + ")");
					System.out.println();
					return "no";
				}
				if (watch.seats == 1) {

					System.out.println("Sorry " + name + ", only 1 seat available.(" + (seatsRequired) + ")");
					System.out.println();
					return "no";
				}

				System.out.println(
						"Sorry " + name + " only " + watch.seats + ", seats available.(" + (seatsRequired) + ")");
				System.out.println();
				return "no";
			}

			ArrayList<String> seats_assingned = assign();
			watch.seats = watch.seats - seatsRequired;
			System.out.println("Hey " + name + "!" + seatsRequired + ", seats booked successfully!");
			System.out.println(watch.name+" playing at screen : "+watch.screen+" at "+watch.time+" Hrs.");
			System.out.print("Your seats are :");
			for (String string : seats_assingned) {
				System.out.print(string + " ");
			}
			System.out.println();
			System.out.println("Amount to be paid = " + (watch.cost * seatsRequired) + " Rs.");
			System.out.println();
			System.out.println();
			return "yes";
		} finally
		{
			Main_lock.lock.unlock();
		}
	}

	public  ArrayList<String> assign() {
		ArrayList<String> finalised_seats = new ArrayList<String>();
		int size = 0, i = 0;
		while (size != seatsRequired) {
			finalised_seats.add(watch.seatsinfo.get(i));
			watch.seatsinfo.remove(i);
			size++;
		}
		return finalised_seats;
	}

}
