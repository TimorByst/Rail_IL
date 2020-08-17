import java.util.ArrayList;

public class WorkDay {
	private int day,month,year;
	private final int currentYEAR = 2020;
	private ArrayList<Itinerary> allItineraries = new ArrayList<Itinerary>();
	
	public WorkDay(String date) throws IllegalArgumentException {
		try {
			String[] splitdate = date.split("/");
			day = Integer.parseInt(splitdate[0]);
			month = Integer.parseInt(splitdate[1]);
			year = Integer.parseInt(splitdate[2]);
			
			if(day<1||day>31||month<1||month>12||year<1970||year>currentYEAR)
				throw new IllegalArgumentException();
		} catch(IllegalArgumentException e) {
			System.err.println("Invalid Date.");
		}
	}
	
	public void addItinerary(Itinerary i) {
		allItineraries.add(i);
	}
	
	public ArrayList<Itinerary> getItineraries() {
		return allItineraries;
	}
	public String getDate() {
		return day+"/"+month+"/"+year;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
}
