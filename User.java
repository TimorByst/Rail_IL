import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	public static void main(String args[]) {

		ArrayList<WorkDay> workDays = Rail.workDays;
		try {
			Scanner read = new Scanner(new FileReader("./Rail_IL/Output_File.txt"));
			String line, origin, t1, des, t2, currentWD = null;
			String temp[];
			Itinerary currentIT = null;
			while (read.hasNext()) {
				line = read.nextLine();
				if (!line.isEmpty()) {
					if (line.contains("[WDstart]"))
						currentWD = line.replace("[WDstart]", "");
					else if (line.contains("--->")) {
						temp = line.split(" ---> ");
						origin = temp[0].substring(0, temp[0].indexOf("(") - 1);
						t1 = temp[0].substring(temp[0].indexOf("(") + 1, temp[0].indexOf(")"));
						des = temp[1].substring(0, temp[1].indexOf("(") - 1);
						t2 = temp[1].substring(temp[1].indexOf("(") + 1, temp[1].indexOf(")"));
						boolean found2 = false;
						WorkDay tempWD2 = null;
						for (WorkDay wd : workDays) {
							if (wd.getDate().equalsIgnoreCase(currentWD)) {
								currentIT = new Itinerary(origin, t1, des, t2);
								wd.addItinerary(currentIT);
								found2 = true;
								tempWD2 = wd;
								break;
							}
						}
						if (!found2) {
							tempWD2 = new WorkDay(currentWD);
							workDays.add(tempWD2);
							currentIT = new Itinerary(origin, t1, des, t2);
							workDays.get(workDays.size() - 1).addItinerary(currentIT);
						}
					} else {
						origin = line.substring(4, line.indexOf("("));
						t1 = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
						currentIT.addIntermidiate(new Station(origin, t1));
					}

				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("READ FILE NOT FOUND!");
			e.printStackTrace();
		}
		
		if(args[0].equalsIgnoreCase("html")) {
			ArrayList<String> text = null;
			if(args.length>0)
				if(args[5].equalsIgnoreCase("departure"))
					text = Rail.findTripByDeparture(args[2], args[3], args[1], args[4], true);
				else
					text = Rail.findTripByArrival(args[2], args[3], args[1], args[4], true);
			
			StringBuilder sb = new StringBuilder();
			sb.append("<h1 style=\"text-align: center;\"><strong>ISRA-RAIL 2021</strong></h1>\r\n" + 
					"<p style=\"text-align: center;\">\".קשה עכשיו, הקלה אחר-כך\"</p>\r\n" + 
					"<h1 style=\"text-align: center;\"><img src=\"https://media1.tenor.com/images/ccd17b9c51655f059745ea8ab0b205eb/tenor.gif?itemid=6164113\" alt=\"\" width=\"399\" height=\"224\" /></h1>\r\n" + 
					"<p>&nbsp;</p>\r\n" + 
					"<h2 style=\"text-align: center;\">Plan a trip:</h2>\r\n" + 
					"<center><form action=\"/rail\"><label for=\"date\">Departure Date:</label> <input id=\"date\" name=\"date\" type=\"text\" /><br /><br /> <label for=\"origin\">Origin Station:</label> <input id=\"origin\" name=\"origin\" type=\"text\" /><br /><br /> <label for=\"destination\">Destination Station:</label> <input id=\"destination\" name=\"destination\" type=\"text\" /><br /><br /> <label for=\"timetype\">Search By: </label><select id=\"timetype\" name=\"timetype\">\r\n" + 
					"<option value=\"departure\">Departure</option>\r\n" + 
					"<option value=\"arrival\">Arrival</option>\r\n" + 
					"</select><label for=\"time\"> <br />Time:</label> <input id=\"time\" name=\"time\" type=\"text\" /><br /><br /> <input style=\"text-align: center;\" type=\"submit\" value=\"Search For Relevant Trips\" /></form><center>\r\n" + 
					"<p style=\"text-align: center;\">&nbsp;</p>"
					+ "<p style=\"text-align: center;\"><strong>RELEVANT ITINERARIES</strong></p>\r\n"
					+ "<p style=\"text-align: center;\">[ Origin (ETD) ---&gt; Destination (ETA) ]</p>\r\n"
					+ "<table style=\"height: 287px; border-color: black; background-color: pink; margin-left: auto; margin-right: auto;\" border=\"Black\" width=\"661\">\r\n"
					+ "<tbody>\r\n");
			if(text.size()==0 || text.isEmpty())
				sb.append(("<tr>\r\n" + "<td style=\"text-align: center;\"width: 322px;\">" + "NO RELEVANT TRIPS!" + "</td>\r\n" + "</tr>\r\n"));
			for (int i = 0; i < text.size(); i++)
				sb.append(("<tr>\r\n" + "<td style=\"text-align: center;\"width: 322px;\">" + text.get(i) + "</td>\r\n" + "</tr>\r\n"));
			sb.append("</tbody>\r\n" + "</table>");
	
			System.out.println(sb.toString());
		}
		else
			if(args.length>0)
				if(args[5].equalsIgnoreCase("departure"))
					Rail.findTripByDeparture(args[2], args[3], args[1], args[4], false);
				else
					Rail.findTripByArrival(args[2], args[3], args[1], args[4], false);
	}
}
