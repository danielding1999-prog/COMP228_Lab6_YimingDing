package application;
	
import java.awt.TextField;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane root = new BorderPane();
		TextField patient_id = new TextField();
		TextField full_name = new TextField();
		TextField age = new TextField();
		 ComboBox<String> gender = new ComboBox<String>();
		 TextField Phone = new TextField();
		 TextField Diagnosis = new TextField();
		 final DatePicker datePicker = new DatePicker();
		 gender.getItems().addAll("Male", "Female");
		try {

			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
