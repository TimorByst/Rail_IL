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
		System.out.println("=================================================");

		do {
			System.out.println("How would you like to proceed?\n" + "(1) Append itinerary.\n"
					+ "(2) Display all itineraries.\n"
					+ "(3) Save Existing Itineraries to file. (WILL OVERWRITE!)\n"
					+ "(4) Load From Itineraries file\n" + "(9) Exit program.");
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
					
				case 3:
					if(allItineraries.size()>0)
						try {
							PrintWriter write = new PrintWriter(new File("./output.txt"));
							for(Itinerary i : allItineraries) {
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
					
				case 4:
				try {
					Scanner read = new Scanner(new File("./output.txt"));
					String line,origin,t1,des,t2;
					String temp[];
					while(read.hasNext()) {
						line = read.nextLine();
						if(!line.isEmpty()) {
							temp = line.split(" ----> ");
							for(String x : temp)
								System.out.println(x);
							origin = temp[0].substring(0,temp[0].indexOf("(")-1);
							t1 = temp[0].substring(temp[0].indexOf("(")+1,temp[0].indexOf(")"));
							des = temp[1].substring(0,temp[1].indexOf("(")-1);
							t2 = temp[1].substring(temp[1].indexOf("(")+1,temp[1].indexOf(")"));
							allItineraries.add(new Itinerary(origin,t1,des,t2));
						}
					}
					read.close();
					System.out.println("LOADED SUCCESSFULLY!");
				} catch (FileNotFoundException e) {
					System.out.println("READ FILE NOT FOUND!");
					e.printStackTrace();
				}
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
