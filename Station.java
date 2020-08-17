
public class Station {
	private String name;
	private int hours,minutes;
	
	public Station(String name, String time) throws IllegalArgumentException {
		String[] splitTime = time.split(":");
		this.name = name;
		try {	
			hours = Integer.parseInt(splitTime[0]);
			minutes = Integer.parseInt(splitTime[1]);
			if(hours<0||hours>23||minutes<0||minutes>59)
				throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid time.");
		}
	}
	
	public int getHours() {
		return hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public String getTime() {
		String h,m;
		h=""+hours;
		m=""+minutes;
		if(hours<10)
			h="0"+hours;
		if(minutes<10)
			m="0"+minutes;
		
		return h + ":" + m;
	}
	public String getName() {
		return name;
	}
}