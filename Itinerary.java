<<<<<<< HEAD
=======
package Rail_IL;

>>>>>>> 5ace76e838a9d065d02b7ec0db6b39ad400a97e5
public class Itinerary {
	
	private String originStation,departureTime,ETA,destinationStation;

	public Itinerary() {
		// Empty on purpose - default constructor.
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

	public void setETA(String eTA) {
		ETA = eTA;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	@Override
	public String toString() {
		return  originStation + " (" + departureTime + ") ----> " + destinationStation
				+ " (" + ETA +").";
	}
	
	
	
	
	
	

}
