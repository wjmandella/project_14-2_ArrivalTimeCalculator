import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import javax.sound.midi.MidiUnavailableException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ArrivalTimeGUIApplication extends Application {

	private static TextField departureDateField;
	private static TextField departureTimeField;
	private static TextField numberOfMilesField;
	private static TextField milesPerHourField;
	private static TextField estimatedTravelTimeField;
	private static TextField daysTraveledField;
	private static TextField hoursTraveledField;
	private static TextField minutesTraveledField;
	private static TextField estimatedDateOfArrivalField;
	private static TextField estimatedTimeOfArrivalField;
	private static TextField continueField;
	
	private static String message;
	private static GridPane grid;
	
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Arrival Time Calculator");
		
//		boolean cont = true;
		
		grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(20,25,25,25));
		grid.setHgap(10);
		grid.setVgap(10);
//		ColumnConstraints col1 = new ColumnConstraints();
//		col1.setPercentWidth(40);
//		ColumnConstraints col2 = new ColumnConstraints();
//		col2.setPercentWidth(60);
//		grid.getColumnConstraints().addAll(col1,col2);
		Scene scene = new Scene(grid);
		
		
//		while(cont == true) {
		
		departureDateField = new TextField();
		departureTimeField = new TextField();
		numberOfMilesField = new TextField();
		milesPerHourField = new TextField();
		daysTraveledField = new TextField();
		hoursTraveledField = new TextField();
		minutesTraveledField = new TextField();
		estimatedDateOfArrivalField = new TextField();
		estimatedTimeOfArrivalField = new TextField();
	
		grid.add(new Label ("Departure Date (YYYY-MM-DD):"), 0, 0);
		grid.add(departureDateField, 1, 0);
		grid.add(new Label ("Departure Time (HH:MM):"), 0, 1);
		grid.add(departureTimeField, 1, 1);
		grid.add(new Label ("Number of miles:"), 0, 2);
		grid.add(numberOfMilesField, 1, 2);
		grid.add(new Label ("Miles per hour:"), 0, 3);
		grid.add(milesPerHourField, 1, 3);
		
		grid.add(new Label ("ESTIMATED TRAVEL TIME"), 0, 5);

		grid.add(new Label ("Days:"), 0, 6);
		grid.add(daysTraveledField, 1, 6);
		grid.add(new Label ("Hours:"), 0, 7);
		grid.add(hoursTraveledField, 1, 7);
		grid.add(new Label ("Minutes:"), 0, 8);
		grid.add(minutesTraveledField, 1, 8);
		grid.add(new Label ("Estimated Arrival Date:"), 0, 9);
		grid.add(estimatedDateOfArrivalField, 1, 9);
		grid.add(new Label ("Estimated Arrival Time:"), 0, 10);
		grid.add(estimatedTimeOfArrivalField, 1, 10);
	
		Button submitButton = new Button("Submit");
		submitButton.setOnAction(event -> submitButtonClicked());
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(event -> exitButtonClicked());
		Button clearTextFieldsButton = new Button("Again?");
		clearTextFieldsButton.setOnAction(event -> clearTextFieldsButtonClicked());
		
		HBox buttonBox = new HBox();
		HBox buttonBox2 = new HBox();
		buttonBox.getChildren().add(submitButton);
		buttonBox.getChildren().add(exitButton);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		grid.add(buttonBox,  0,  4, 2, 1);
		
		buttonBox2.getChildren().add(clearTextFieldsButton);
		buttonBox2.setAlignment(Pos.BASELINE_CENTER);
		grid.add(clearTextFieldsButton, 2, 12);
			
		primaryStage.setScene(scene);
		primaryStage.show();
		
//		}
}
	
	public static void main(String[] args) {
		   launch(args);

	}

	private void submitButtonClicked() {	
		String departDateString = departureDateField.getText();
		String departTimeString = departureTimeField.getText();

		String departDateTimeString = departDateString + "T" + departTimeString;
		LocalDateTime departDateTime = LocalDateTime.parse(departDateTimeString);
		
		double miles = Double.parseDouble(numberOfMilesField.getText());
		double milesPerHours = Double.parseDouble(milesPerHourField.getText());

		double hours = (miles/milesPerHours);  
		double days = hours/24;
		double minutes = Math.round((miles%milesPerHours)*((double) 60/milesPerHours));
		long minutesRounded = (int) minutes;
		long hoursRounded = (int) hours;
		int daysRounded = (int) days;
		long hoursRounded2 = hoursRounded - (daysRounded*24);
		
		String hoursString = Long.toString(hoursRounded2);
		String daysString = Integer.toString(daysRounded);
		String minutesString = Long.toString(minutesRounded); 
		
		hoursTraveledField.setText(hoursString);
		daysTraveledField.setText(daysString);
		minutesTraveledField.setText(minutesString);
		
		DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		DateTimeFormatter tf = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		
		estimatedDateOfArrivalField.setText(df.format(departDateTime.plusHours(hoursRounded).plusMinutes(minutesRounded)));			
		estimatedTimeOfArrivalField.setText(tf.format(departDateTime.plusHours(hoursRounded).plusMinutes(minutesRounded)));	
		
			
	
	
//			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//			alert.setHeaderText("Arrival Time Estimator");
//			alert.setContentText("Calculate another trip?");
//			alert.showAndWait();
		
	}

	public void exitButtonClicked() {
		System.exit(0);
	}
	
	public void clearTextFieldsButtonClicked() {
		departureDateField.clear();
		departureTimeField.clear();
		numberOfMilesField.clear();
		milesPerHourField.clear();
		hoursTraveledField.clear();
		daysTraveledField.clear();
		minutesTraveledField.clear();
		estimatedDateOfArrivalField.clear();
		estimatedTimeOfArrivalField.clear();
	}

//	public void continueButtonClicked() {
//
//	}
	
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
