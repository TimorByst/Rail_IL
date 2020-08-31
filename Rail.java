import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Rail {

	static ArrayList<WorkDay> workDays = new ArrayList<WorkDay>();
	
	public static void main(String[] args) {
		int choice;
		Scanner input = new Scanner(System.in);
		System.out.println("=================================================");
		System.out.println("Welcome Admin to Israel Railway Schedule Program.");

		do {
			System.out.println("====================Main Menu====================");
			System.out.println(
					"How would you like to proceed?\n" + "(1) Append itinerary.\n" + "(2) Display all itineraries.\n"
							+ "(3) Find Trip. \n" + "(4) Save Existing Itineraries to file. (WILL OVERWRITE!)\n"
//                          + "(5) Load From Itineraries file\n"
							+ "(9) Exit program.");

			choice = Integer.parseInt(input.nextLine());

			switch (choice) {
			case 1:
				System.out.println("==================Appending Itinerary================");
				String a, b, c, d, f, midStation, ETA;
				System.out.println("Please enter full date (DD/MM/YYYY): ");
				f = input.nextLine();
				System.out.println("Please enter origin station: ");
				a = input.nextLine();
				System.out.println("Please enter departure time: ");
				b = input.nextLine();
				System.out.println("Please enter destination station: ");
				c = input.nextLine();
				System.out.println("Please enter arrival time: ");
				d = input.nextLine();
				
				boolean found = false;
				WorkDay tempWD = null;
				for(WorkDay wd : workDays) {
					if(wd.getDate().equalsIgnoreCase(f)) {
						wd.addItinerary(new Itinerary(a, b, c, d));
						found = true;
						tempWD = wd;
						break;
					}
				}
				if(!found) {
					tempWD = new WorkDay(f);
					workDays.add(tempWD);
					workDays.get(workDays.size()-1).addItinerary(new Itinerary(a, b, c, d));
				}
				
				do {
					System.out.println("Add an intermidiate station? (Y/N)");
					if (input.nextLine().contains("N"))
						break;
					System.out.println("Name of the station: ");
					midStation = input.nextLine();
					System.out.println("Time of arrival: ");
					ETA = input.nextLine();
					tempWD.getItineraries().get(tempWD.getItineraries().size()-1).addIntermidiate(new Station(midStation, ETA));
				} while (true);
				
				sortAll();
				break;
				
			case 2:
				System.out.println("==================All Itineraries==================");
				for(WorkDay workday : workDays) {
					System.out.println("### " + workday.getDate() + " ###");
					for (Itinerary i : workday.getItineraries())
						System.out.println(i.toString());
				}
				break;

			case 3:
				System.out.println("===================Finding Trips===================");
				String originStation, destinationStation,date;
				System.out.println("Please enter date: ");
				date = input.nextLine();
				System.out.println("Please enter origin: ");
				originStation = input.nextLine();
				System.out.println("Please enter destination: ");
				destinationStation = input.nextLine();
				System.out.println("Find trip by:\n" + "1. Departure.\n" + "2. Arrival.");
				if (input.nextLine().contains("1")) {
					System.out.println("Please enter time of departure: ");
					System.out.println("==================Relevant Trips===================");
					findTripByDeparture(originStation, destinationStation, date, input.nextLine());
				}
				else {
					System.out.println("Please enter time of arrival: ");
					System.out.println("==================Relevant Trips===================");
					findTripByArrival(originStation, destinationStation, date, input.nextLine());
				}

				break;

			case 4:
				System.out.println("====================Saving File===================");
				try {
					PrintWriter write = new PrintWriter(new FileOutputStream("./output.txt",false));
					for(WorkDay wd : workDays)
						if (wd.getItineraries().size() > 0) {
							write = new PrintWriter(new FileOutputStream("./output.txt",true));
							for (Itinerary i : wd.getItineraries()) {
								write.println(i.toString());
							}
							write.flush();
							write.close();
						}
						else
							System.out.println("Fill in some itineraries first.");
				} catch (FileNotFoundException e) {
					System.out.println("OUTPUT FILE NOT FOUND!");
					e.printStackTrace();
					break;
				}
				System.out.println("SAVED SUCCESSFULLY!");
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

	public static void sortAll() {
		boolean repeat;
		int origin=0;
		Itinerary temp = null;
		do {
			repeat = false;
			for (int k = 0; k < workDays.size(); k++)
				for (int i = 0; i < workDays.get(k).getItineraries().size() - 1; i++) {
						if (workDays.get(k).getItineraries().get(i).getTrip().get(origin).getHours() > workDays.get(k).getItineraries().get(i+1).getTrip().get(origin).getHours()) {
							temp = workDays.get(k).getItineraries().set(i, workDays.get(k).getItineraries().get(i + 1));
							workDays.get(k).getItineraries().set(i + 1, temp);
							repeat = true;
						} else if (workDays.get(k).getItineraries().get(i).getTrip().get(origin).getHours() == workDays.get(k).getItineraries().get(i+1).getTrip().get(origin).getHours())
							if (workDays.get(k).getItineraries().get(i).getTrip().get(origin).getMinutes() > workDays.get(k).getItineraries().get(i+1).getTrip().get(origin).getMinutes()) {
								temp = workDays.get(k).getItineraries().set(i, workDays.get(k).getItineraries().get(i + 1));
								workDays.get(k).getItineraries().set(i + 1, temp);
								repeat = true;
							}
					}
		} while (repeat);
			
//			WorkDay temp2 = null;
//			for(int k=0;k<workDays.size()-1;k++) {
//				temp2 = workDays.get(k);
//				if(workDays.get(k).getYear()>workDays.get(k+1).getYear())
//					workDays.set(k,workDays.set(k+1, temp2));							//SORT BY DAY - TO ADD
//				else if(workDays.get(k).getYear()==workDays.get(k+1).getYear())
//					if()
//			}
//
	}

	public static void findTripByDeparture(String origin, String destination, String date, String time) {
		
		WorkDay tempWD = null;
		boolean found = false,found2=false,isOrigin=false,isDestination=false;
		String[] sTime = time.split(":");
		
		for(WorkDay wd : workDays)
			if(wd.getDate().contains(date)) {
				tempWD = wd;
				found = true;
			}
		if(!found) {
			System.out.println("No trains available that day.");
			return;
		}
		
		ArrayList<Itinerary> relevantStations = new ArrayList<Itinerary>();
		ArrayList<Itinerary> relevantItineraries = new ArrayList<Itinerary>();
		
		for (Itinerary i : tempWD.getItineraries()) {
			isOrigin=false;
			isDestination=false;
			for(Station station : i.getTrip()) {
				if (station.getName().contains(origin))
					isOrigin = true;
				if (station.getName().contains(destination) && isOrigin)
					isDestination = true;
			}
			if(isOrigin&&isDestination)
				relevantStations.add(i);
		}
		
		int relevantBefore=0,relevantAfter=0;
		boolean A=false,B=false;
		
		for (Itinerary i : relevantStations)
			for(Station station : i.getTrip()) {
				if((station.getName().contains(origin) && station.getHours()>=Integer.parseInt(sTime[0]) && (station.getHours()-Integer.parseInt(sTime[0]))<=1)) {
					A=true;
					relevantAfter++;
				}
				if((station.getName().contains(origin) && station.getHours()<=Integer.parseInt(sTime[0]) && (Integer.parseInt(sTime[0])-station.getHours())<=3)) {
					B=true;
					relevantBefore++;
				}
				
				if(A||B) {
					relevantItineraries.add(i);
					A=false;
					B=false;
					found2=true;
				}
			}
		
		int counter=0;
		int printed = 0;
		int until = 2;
		if(relevantAfter==0)
			until = 3;
		
		for (Itinerary i : relevantItineraries) {
			counter++;
			if(relevantBefore-counter<until && relevantBefore-counter>=0) {
				printed++;
				System.out.println(i.toPartialString(origin,destination));
			}
			else if(relevantBefore-counter<0 && printed<3) {
				printed++;
				System.out.println(i.toPartialString(origin,destination));
			}
		}
		if(!found2)
			System.out.println("No trips available for given time.");	
	}
	
public static void findTripByArrival(String origin, String destination, String date, String time) {
		
		WorkDay tempWD = null;
		boolean found = false,found2=false,isOrigin=false,isDestination=false;
		String[] sTime = time.split(":");
		
		for(WorkDay wd : workDays)
			if(wd.getDate().contains(date)) {
				tempWD = wd;
				found = true;
			}
		if(!found) {
			System.out.println("No trains available that day.");
			return;
		}
		
		ArrayList<Itinerary> relevantStations = new ArrayList<Itinerary>();
		ArrayList<Itinerary> relevantItineraries = new ArrayList<Itinerary>();
		
		for (Itinerary i : tempWD.getItineraries()) {
			isOrigin=false;
			isDestination=false;
			for(Station station : i.getTrip()) {
				if (station.getName().contains(origin))
					isOrigin = true;
				if (station.getName().contains(destination) && isOrigin)
					isDestination = true;
			}
			if(isOrigin&&isDestination)
				relevantStations.add(i);
		}
		
		int relevantBefore=0,relevantAfter=0;
		boolean A=false,B=false;
		
		for (Itinerary i : relevantStations)
			for(Station station : i.getTrip()) {
				if((station.getName().contains(destination) && station.getHours()>=Integer.parseInt(sTime[0]) && (station.getHours()-Integer.parseInt(sTime[0]))<=1)) {
					A=true;
					relevantBefore++;
				}
				if((station.getName().contains(destination) && station.getHours()<=Integer.parseInt(sTime[0]) && (Integer.parseInt(sTime[0])-station.getHours())<=3)) {
					B=true;
					relevantAfter++;
				}
				
				if(A||B) {
					relevantItineraries.add(i);
					A=false;
					B=false;
					found2=true;
				}
			}
		
		int counter=0;
		int printed = 0;
		int until = 2;
		if(relevantAfter==0)
			until = 3;
		
		for (Itinerary i : relevantItineraries) {
			counter++;
			if(relevantBefore-counter<until && relevantBefore-counter>=0) {
				printed++;
				System.out.println(i.toPartialString(origin,destination));
			}
			else if(relevantBefore-counter<0 && printed<3) {
				printed++;
				System.out.println(i.toPartialString(origin,destination));
			}
		}
		if(!found2)
			System.out.println("No trips available for given time.");	
	}
}
