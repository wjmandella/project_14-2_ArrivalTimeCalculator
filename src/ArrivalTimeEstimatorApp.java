//import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class ArrivalTimeEstimatorApp {

	public static void main(String[] args) {
		System.out.println("Arrival Time Estimator");
		
		Scanner sc = new Scanner(System.in);
		String choice = "y";
		
		while (choice.equalsIgnoreCase("y") ) {
			
			System.out.print("\nDeparture date (YYYY-MM-DD): ");
			String departDateString = sc.nextLine();
//			LocalDate departDate = LocalDate.parse(departDateString);
			
			System.out.print("Departure time (HH:MM): ");
			String departTimeString = sc.nextLine();
//			LocalTime departTime = LocalTime.parse(departTimeString);
			
			// Concatenation of departTime and departDate so that some methods for LocalDateTime
			// can be used--methods that are unavailable to LocalDate and LocalTime. 
			// (For example: "plusHours" and "plusMinutes" at lines 50 & 52.)
			String departDateTimeString = departDateString + "T" + departTimeString;
			LocalDateTime departDateTime = LocalDateTime.parse(departDateTimeString);
			
			int miles = ConsoleArrivTimeEst.getInt("Number of miles: ");
			int milesPerHours = ConsoleArrivTimeEst.getInt("Miles per hour: ");
			
			// A double must be used for the following 2 calculations to insure a correct result 
			// for estimated "hours" and "minutes". 
			// NOTE:The book's solution for the calculation of minutes (using ONLY the modulus operator) is INCORRECT.
			double hours = (miles/milesPerHours);  
			double days = hours/24;
			double minutes = Math.round((miles%milesPerHours)*((double) 60/milesPerHours));
			long minutesRounded = (int) minutes;
			long hoursRounded = (int) hours;
			int daysRounded = (int) days;
//			long totalTripMinutes = hoursRounded*60 + minutesRounded;
			
			System.out.println("\nEstimated travel time");
			System.out.println("Days: " + daysRounded);
			System.out.println("Hours: " + (hoursRounded-(daysRounded*24)));
			System.out.println("Minutes: " + minutesRounded);
//			System.out.println("Total minutes  " + totalTripMinutes);
			
			DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
			DateTimeFormatter tf = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
			
			System.out.println("Estimated date of arrival: " + 
					df.format(departDateTime.plusHours(hoursRounded).plusMinutes(minutesRounded)));		
			System.out.println("Estimated time of arrival: " + 
					tf.format(departDateTime.plusHours(hoursRounded).plusMinutes(minutesRounded)));
		
		choice = ConsoleArrivTimeEst.getString("\nContinue? (y/n): ", "y", "n");
		}
		
		System.out.println("\nBye!");
		sc.close();
		
	}

}

//////SAMPLE OUTPUT:///////

//Arrival Time Estimator
//
//Departure date (YYYY-MM-DD): 2017-11-15
//Departure time (HH:MM): 10:30
//Number of miles: 35674
//Miles per hour: 50
//
//Estimated travel time
//Days: 29
//Hours: 17
//Minutes: 29
//Estimated date of arrival: Dec 15, 2017
//Estimated time of arrival: 3:59 AM