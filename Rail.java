import java.util.ArrayList;
import java.util.Scanner;

public class Rail {

	static ArrayList<Itinerary> allItineraries = new ArrayList<Itinerary>();

	public static void main(String[] args) {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("=================================================");
		System.out.println("Welcome Admin to Israel Railway Schedule Program.");
		System.out.println("=================================================");

		do {
			System.out.println("How would you like to proceed?\n" + "(1) Append itinerary.\n"
					+ "(2) Display all itineraries. \n" + "(9) Exit program. ");
			System.out.println("=================================================");
			
			choice = Integer.parseInt(input.nextLine());
			
			switch (choice) {
				case 1:
					Itinerary a = new Itinerary(input.nextLine(),input.nextLine(),input.nextLine(),input.nextLine());
					allItineraries.add(a);
					sortByDepartureTime();
					break;
	
				case 2:
					for (Itinerary i : allItineraries)
						System.out.println(i.toString());
					break;
			}
		} while (choice != 9);
		System.out.println("------------------------------------PROGRAM TERMINATED----------------------------------------------");
		input.close();
	}

	public static void sortByDepartureTime() {
		boolean repeat;
		Itinerary temp = null;
		String[] time1, time2;
		do {
			repeat = false;
			for (int i = 0; i < allItineraries.size() - 1; i++) {
				time1 = allItineraries.get(i).getDepartureTime().split(":");
				time2 = allItineraries.get(i + 1).getDepartureTime().split(":");

				if (Integer.parseInt(time1[0]) > Integer.parseInt(time2[0])) {
					temp = allItineraries.set(i, allItineraries.get(i + 1));
					allItineraries.set(i + 1, temp);
					repeat = true;
				}
				else if (Integer.parseInt(time1[0]) == Integer.parseInt(time2[0]))
					if (Integer.parseInt(time1[1]) > Integer.parseInt(time2[1])) {
						temp = allItineraries.set(i, allItineraries.get(i + 1));
						allItineraries.set(i + 1, temp);
						repeat = true;
					}
			}

		} while (repeat);
	}

}
