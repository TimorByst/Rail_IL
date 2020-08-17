import java.util.ArrayList;

public class Itinerary {
	protected ArrayList<Station> trip = new ArrayList<Station>();
	
	public Itinerary(String origin, String time1, String destination, String time2) {
		trip.add(new Station(origin, time1));
		trip.add(new Station(destination,time2));
	}
	
	public void addIntermidiate(Station station) {
		trip.add(station);
		trip.set(trip.size()-1, trip.set(trip.size()-2, station));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		 sb.append(trip.get(0).getName() + " (" + trip.get(0).getTime() + ") ---> " + trip.get(trip.size()-1).getName()
				+ " (" + trip.get(trip.size()-1).getTime() +")\n");
		 for(int i = 1 ; i < trip.size()-1; i++) 
			 sb.append("--- " + trip.get(i).getName() +" (" + trip.get(i).getTime() +")\n");
		 sb.deleteCharAt(sb.length()-1);
		 return sb.toString();
	}
	
	public ArrayList<Station> getTrip() {
		return trip;
	}
	
	public String toPartialString(String a, String b) {
		StringBuilder sb = new StringBuilder();
		boolean flag=false;
		for(Station station : trip) {
			if(station.getName().contains(a))
				sb.append(station.getName() + " (" + station.getTime() + ") ---> ");
			else if(station.getName().contains(b))
				sb.append(station.getName() + " (" + station.getTime() + ")\n");
		}
		for(Station station : trip) {
			if(station.getName().contains(b))
				flag = false;
			if(flag)
				sb.append("--- " + station.getName() + " (" + station.getTime() + ")\n");
			if(station.getName().contains(a))
				flag = true;
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
		}
}