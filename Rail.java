<<<<<<< HEAD
=======
package Rail_IL;

>>>>>>> 5ace76e838a9d065d02b7ec0db6b39ad400a97e5
import java.util.ArrayList;
import java.util.Scanner;

public class Rail {

	static ArrayList<Itinerary> allItineraries = new ArrayList<Itinerary>();

	public static void main(String[] args) {
<<<<<<< HEAD
		int choice;
=======
		int choice, counter = 0;
>>>>>>> 5ace76e838a9d065d02b7ec0db6b39ad400a97e5
		Scanner input = new Scanner(System.in);
		
//		String text = "1\n" +
//				"Tel Aviv-HaHagana\n"+         
//				"16:12\n"+       
//				"Be'er Sheva-North/University\n"+ 
//				"18:05\n"+
//				"1\n"+                
//				"Tel Aviv-HaHagana\n"+            
//				"15:19\n"+          
//				"Jerusalem - Yitzhak Navon\n"+    
//				"15:55\n"+ 
//				"2\n"
//				+ "9";
<<<<<<< HEAD
		
=======

		String[] lines = input.next().split("\n");
>>>>>>> 5ace76e838a9d065d02b7ec0db6b39ad400a97e5
		System.out.println("Hello and welcome to Israel Railway");

		do {
			System.out.println("how would you like to proceed?\n" + "1. Append itinerary.\n"
					+ "2. Display all itineraries. \n" + "9. Exit program. ");
<<<<<<< HEAD
			choice = Integer.parseInt(input.nextLine());
=======
			choice = Integer.parseInt(lines[counter]);
			counter++;
>>>>>>> 5ace76e838a9d065d02b7ec0db6b39ad400a97e5

			switch (choice) {

			case 1:

				Itinerary a = new Itinerary();
<<<<<<< HEAD
				a.setOriginStation(input.nextLine());
				a.setDepartureTime(input.nextLine());
				a.setDestinationStation(input.nextLine());
				a.setETA(input.nextLine());
=======
				a.setOriginStation(lines[counter]);
				counter++;
				a.setDepartureTime(lines[counter]);
				counter++;
				a.setDestinationStation(lines[counter]);
				counter++;
				a.setETA(lines[counter]);
				counter++;
>>>>>>> 5ace76e838a9d065d02b7ec0db6b39ad400a97e5
				allItineraries.add(a);
				sort();

				break;

			case 2:

				for (Itinerary i : allItineraries) {
					System.out.println(i.toString());
				}
				break;

			}
		} while (choice != 9);
		input.close();

	}

	public static void sort() {
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
