import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Rail {

	static ArrayList<Itinerary> allItineraries = new ArrayList<Itinerary>();

	public static void main(String[] args) {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("=================================================");
		System.out.println("Welcome Admin to Israel Railway Schedule Program.");

		do {
			System.out.println("==================Main Menu====================");
			System.out.println(
					"How would you like to proceed?\n" + "(1) Append itinerary.\n" + "(2) Display all itineraries.\n"
							+ "(3) Find Trip. \n" + "(4) Save Existing Itineraries to file. (WILL OVERWRITE!)\n"
//                          + "(5) Load From Itineraries file\n"
							+ "(9) Exit program.");

			choice = Integer.parseInt(input.nextLine());

			switch (choice) {
			case 1:
				System.out.println("==================Appending Itinerary================");
				String a, b, c, d, midStation, ETA;
				System.out.println("Please enter origin station: ");
				a = input.nextLine();
				System.out.println("Please enter departure time: ");
				b = input.nextLine();
				System.out.println("Please enter destination station: ");
				c = input.nextLine();
				System.out.println("Please enter arrival time: ");
				d = input.nextLine();
				Itinerary thisItinerary = new Itinerary(a, b, c, d);
				allItineraries.add(thisItinerary);
				sortByDepartureTime();

				do {
					System.out.println("Add an intermidiate station? (Y/N)");
					if (input.nextLine().contains("N"))
						break;
					System.out.println("Name of the station: ");
					midStation = input.nextLine();
					System.out.println("Time of arrival: ");
					ETA = input.nextLine();
					thisItinerary.addMidStation(midStation, ETA);

				} while (true);
				break;

			case 2:
				System.out.println("==================All Itineraries==================");
				for (Itinerary i : allItineraries)
					System.out.println(i.toString());
				break;

			case 3:
				System.out.println("===================Finding Trips===================");
				String originStation, destinationStation;
				System.out.println("Please enter origin: ");
				originStation = input.nextLine();
				System.out.println("Please enter destination: ");
				destinationStation = input.nextLine();
				System.out.println("Find trip by:\n" + "1. Departure.\n" + "2. Arrival.");
				if (input.nextLine().contains("1")) {
					System.out.println("Please enter time of departure: ");
					System.out.println("==================Relevant Tips===================");
					findTripByDeparture(originStation, destinationStation, input.nextLine());
				}

				else {
					System.out.println("Please enter time of arrival: ");
					System.out.println("==================Relevant Tips===================");
					findTripByArrival(originStation, destinationStation, input.nextLine());
				}

				break;

			case 4:
				System.out.println("====================Saving File===================");
				if (allItineraries.size() > 0)
					try {
						PrintWriter write = new PrintWriter(new File("./output.txt"));
						for (Itinerary i : allItineraries) {
							write.println(i.toString());
						}
						write.flush();
						write.close();
						System.out.println("SAVED SUCCESSFULLY!");
					} catch (FileNotFoundException e) {
						System.out.println("OUTPUT FILE NOT FOUND!");
						e.printStackTrace();
					}
				else
					System.out.println("Fill in some itineraries first.");

				break;

//			case 5:
//				try {
//					Scanner read = new Scanner(new File("./output.txt"));
//					String line, origin, t1, des, t2;
//					String temp[];
//					while (read.hasNext()) {
//						line = read.nextLine();
//						if (!line.isEmpty()) {
//							temp = line.split(" ----> ");
//							for (String x : temp)
//								System.out.println(x);
//							origin = temp[0].substring(0, temp[0].indexOf("(") - 1);
//							t1 = temp[0].substring(temp[0].indexOf("(") + 1, temp[0].indexOf(")"));
//							des = temp[1].substring(0, temp[1].indexOf("(") - 1);
//							t2 = temp[1].substring(temp[1].indexOf("(") + 1, temp[1].indexOf(")"));
//							allItineraries.add(new Itinerary(origin, t1, des, t2));
//						}
//					}
//					read.close();
//					System.out.println("LOADED SUCCESSFULLY!");
//				} catch (FileNotFoundException e) {
//					System.out.println("READ FILE NOT FOUND!");
//					e.printStackTrace();
//				}
//				break;
			}
		} while (choice != 9);
		System.out.println(
				"------------------------------------PROGRAM TERMINATED----------------------------------------------");
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
				} else if (Integer.parseInt(time1[0]) == Integer.parseInt(time2[0]))
					if (Integer.parseInt(time1[1]) > Integer.parseInt(time2[1])) {
						temp = allItineraries.set(i, allItineraries.get(i + 1));
						allItineraries.set(i + 1, temp);
						repeat = true;
					}
			}

		} while (repeat);
	}

	public static void findTripByDeparture(String origin, String destenation, String time) {
		String[] time1, time2;
		time1 = time.split(":");
		ArrayList<Itinerary> sortedItineraries = new ArrayList<Itinerary>();
		for (Itinerary i : allItineraries) {
			if (i.getOriginStation().equalsIgnoreCase(origin)
					&& i.getDestinationStation().equalsIgnoreCase(destenation)) {
				sortedItineraries.add(i);
			}
		}
		for (Itinerary i : sortedItineraries) {
			time2 = i.getDepartureTime().split(":");
			if ((Integer.parseInt(time1[0]) >= Integer.parseInt(time2[0])
					&& (Integer.parseInt(time1[0]) - Integer.parseInt(time2[0])) <= 1)
					|| (Integer.parseInt(time1[0]) <= Integer.parseInt(time2[0])
							&& (Integer.parseInt(time2[0]) - Integer.parseInt(time1[0])) <= 3)) {
				System.out.println(i.toString());
				return;
			}
			System.out.println("No trips available for given time.");
		}
	}

	public static void findTripByArrival(String origin, String destenation, String time) {
		String[] time1, time2;
		time1 = time.split(":");
		ArrayList<Itinerary> sortedItineraries = new ArrayList<Itinerary>();
		for (Itinerary i : allItineraries) {
			if (i.getOriginStation().equalsIgnoreCase(origin)
					&& i.getDestinationStation().equalsIgnoreCase(destenation)) {
				sortedItineraries.add(i);
			}
		}
		for (Itinerary i : sortedItineraries) {
			time2 = i.getETA().split(":");
			if ((Integer.parseInt(time1[0]) >= Integer.parseInt(time2[0])
					&& (Integer.parseInt(time1[0]) - Integer.parseInt(time2[0])) <= 3)
					|| ((Integer.parseInt(time1[0]) <= Integer.parseInt(time2[0])
							&& (Integer.parseInt(time2[0]) - Integer.parseInt(time1[0])) <= 1))) {
				System.out.println(i.toString());
				return;
			}
			System.out.println("No trips available for given time.");

		}

	}

}
