import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
	public static void main(String args[]) {
		ArrayList<WorkDay> workDays = Rail.workDays;
	 
		try {
			Scanner read = new Scanner(new FileReader("./Output_File.txt"));
			String line, origin, t1, des, t2, currentWD=null;
			String temp[];
			Itinerary currentIT = null;
			while (read.hasNext()) {
				line = read.nextLine();
				if (!line.isEmpty()) {
					if(line.contains("[WDstart]"))
						currentWD=line.replace("[WDstart]", "");
					else if(line.contains("--->")) {
					    	temp = line.split(" ---> ");
						origin = temp[0].substring(0, temp[0].indexOf("(") - 1);
						t1 = temp[0].substring(temp[0].indexOf("(") + 1, temp[0].indexOf(")"));
						des = temp[1].substring(0, temp[1].indexOf("(") - 1);
						t2 = temp[1].substring(temp[1].indexOf("(") + 1, temp[1].indexOf(")"));
						boolean found2 = false;
						WorkDay tempWD2 = null;
						for(WorkDay wd : workDays) {
							if(wd.getDate().equalsIgnoreCase(currentWD)) {
								currentIT = new Itinerary(origin, t1, des, t2);
								wd.addItinerary(currentIT);
								found2 = true;
								tempWD2 = wd;
								break;
							}
						}
						if(!found2) {
							tempWD2 = new WorkDay(currentWD);
							workDays.add(tempWD2);
							currentIT = new Itinerary(origin, t1, des, t2);
							workDays.get(workDays.size()-1).addItinerary(currentIT);
						}
					}
					else {
						origin = line.substring(4, line.indexOf("("));
						t1 = line.substring(line.indexOf("(")+1, line.indexOf(")"));
						currentIT.addIntermidiate(new Station(origin, t1));
					}
					
				}
			}
			read.close();
			System.out.println("LOADED SUCCESSFULLY!");
		} catch (FileNotFoundException e) {
			System.out.println("READ FILE NOT FOUND!");
			e.printStackTrace();
		}
		
		Rail.findTripByDeparture(args[2], args[3], args[1], args[4]);
		
		System.out.println("<table style=\"height: 195px; border-color: black; background-color: pink; margin-left: auto; margin-right: auto;\" border=\"black\" width=\"662\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 158px;\"><strong>" + args[1] + "</strong></td>\r\n" + 
				"<td style=\"width: 158px;\">" + args[2] + "</td>\r\n" + 
				"<td style=\"width: 159px;\">" + args[3] + ":" + args[4] + "</td>\r\n" + 
				"<td style=\"width: 159px;\">TEST</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 158px;\">Fuck you</td>\r\n" + 
				"<td style=\"width: 158px;\">AAAA</td>\r\n" + 
				"<td style=\"width: 159px;\">&nbsp;GO TO ----&gt;</td>\r\n" + 
				"<td style=\"width: 159px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 158px;\">&nbsp;</td>\r\n" + 
				"<td style=\"width: 158px;\">&nbsp;<img src=\"https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /><img src=\"https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /><img src=\"https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /><img src=\"https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /><img src=\"https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /><img src=\"https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /><img src=\"https://html-online.com/editor/tinymce4_6_5/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /></td>\r\n" + 
				"<td style=\"width: 159px;\">ZZZ</td>\r\n" + 
				"<td style=\"width: 159px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>");
	}
}
