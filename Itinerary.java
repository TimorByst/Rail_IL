import java.util.ArrayList;

public class Itinerary {
	
	private String originStation,departureTime,ETA,destinationStation;
	private ArrayList<String> midStations;
	private ArrayList<String> schedule;
	

	public Itinerary(String originStation,String departureTime,String destinationStation,String ETA) {
		this.originStation=originStation;
		this.departureTime=departureTime;
		this.ETA=ETA;
		this.destinationStation=destinationStation;
		midStations = new ArrayList<String>();
		schedule = new ArrayList<String>();
	}

	
	public String getOriginStation() {
		return originStation;
	}

	public void setOriginStation(String originStation) {
		this.originStation = originStation;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getETA() {
		return ETA;
	}

	public void setETA(String ETA) {
		this.ETA = ETA;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	
	public void addMidStation(String name, String ETA) {
		midStations.add(name);
		schedule.add(ETA);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		 sb.append(originStation + " (" + departureTime + ") ---> " + destinationStation
				+ " (" + ETA +")\n");
		 for(int i = 0 ; i < midStations.size(); i++) 
			 sb.append("--- " + midStations.get(i) +" (" + schedule.get(i) +")\n");
		 sb.deleteCharAt(sb.length()-1);
		 return sb.toString();
	}
}
