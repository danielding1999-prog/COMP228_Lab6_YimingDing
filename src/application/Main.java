package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import com.yd.lab6.buttoncontrol.*;
import com.yd.lab6.patient.Patient;
import com.yd.lab6.table.*;

public class Main extends Application {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/lab6";
	  private static final String DB_USER = "root";
	  private static final String DB_PASSWORD = "001214";
	@Override
	public void start(Stage primaryStage) {

		
		BorderPane root = new BorderPane();
		TextField full_name_text = new TextField();
		TextField age_text = new TextField();
		ComboBox<String> gender_FMN = new ComboBox<String>();
		TextField Phone_text = new TextField();
		TextField Diagnosis_text = new TextField();
		Button add = new Button("add");
		Button update = new Button("update");
		Button delete = new Button("delete");
		Label status = new Label();
		

		final DatePicker Admission_date_picker = new DatePicker();
		VBox top = new VBox();
		HBox patient_detail = new HBox();
		HBox execute_button = new HBox();
		execute_button.getChildren().addAll(add, update, delete, status);

		Label title = new Label("Hospital Management System");
		Label subtitle = new Label("Management system that maintains registration records.");

		top.getChildren().addAll(title, subtitle);

		top.getChildren().add(patient_detail);

		gender_FMN.getItems().addAll("Male", "Female", "N/A");
		GridPane form = new GridPane();
		Label full_name = new Label("Full Name: ");
		Label age = new Label("Age: ");
		Label gender = new Label("Gender: ");
		Label Phone = new Label("Phone: ");
		Label Diagnosis = new Label("Diagnosis: ");
		Label admission_date = new Label("Admission Date: ");


		form.add(full_name, 0, 0);
		form.add(age, 0, 1);
		form.add(gender, 0, 2);
		form.add(Phone, 0, 3);
		form.add(Diagnosis, 0, 4);
		form.add(admission_date, 0, 5);
		form.add(full_name_text, 1, 0);
		form.add(age_text, 1, 1);
		form.add(gender_FMN, 1, 2);
		form.add(Phone_text, 1, 3);
		form.add(Diagnosis_text, 1, 4);
		form.add(Admission_date_picker, 1, 5);
		
		TableView<Patient> tableView = new TableView<>();

        TableColumn<Patient, String> idCol = new TableColumn<>("Patient ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("patient_id"));

        TableColumn<Patient, String> nameCol = new TableColumn<>("Full Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));

        TableColumn<Patient, String> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));

        TableColumn<Patient, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("Gender"));

        TableColumn<Patient, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Patient, String> diagnosisCol = new TableColumn<>("Diagnosis");
        diagnosisCol.setCellValueFactory(new PropertyValueFactory<>("Diagnosis"));

        TableColumn<Patient, String> admissionDateCol = new TableColumn<>("Admission Date");
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<>("Admission_date"));

        tableView.getColumns().addAll(
                idCol, nameCol, ageCol, genderCol, phoneCol, diagnosisCol, admissionDateCol
        );
        
        Table tableLoader = new Table();
        ObservableList<Patient> patientList = tableLoader.getTableData(DB_URL, DB_USER, DB_PASSWORD);
        tableView.setItems(patientList);
        
        patient_detail.getChildren().addAll(form, tableView);

		root.setTop(top);
		root.setBottom(execute_button);
		root.setCenter(patient_detail);

		try {

			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ButtonControl buttonControl = new ButtonControl();
		add.setOnAction((e -> {
				String status_message = buttonControl.registerPatient(full_name_text, age_text,
				Phone_text, Diagnosis_text, gender_FMN, Admission_date_picker);
				status.setText(status_message);
				patientList.setAll(tableLoader.getTableData(DB_URL, DB_USER, DB_PASSWORD));
		        tableView.refresh();
		}));
		
		update.setOnAction(e -> {
            Patient selected = tableView.getSelectionModel().getSelectedItem();
            String selectedId = (selected != null) ? selected.getPatient_id() : null;

            String status_message = buttonControl.updatePatient(
                    selectedId,
                    full_name_text,
                    age_text,
                    Phone_text,
                    Diagnosis_text,
                    gender_FMN,
                    Admission_date_picker
            );
            status.setText(status_message);
            patientList.setAll(tableLoader.getTableData(DB_URL, DB_USER, DB_PASSWORD));
            tableView.refresh();
        });
		
		delete.setOnAction(e -> {
		    Patient selected = tableView.getSelectionModel().getSelectedItem();
		    String selectedId = (selected != null) ? selected.getPatient_id() : null;

		    String status_message = buttonControl.deletePatient(
		            selectedId,
		            full_name_text,
		            age_text,
		            Phone_text,
		            Diagnosis_text,
		            gender_FMN,
		            Admission_date_picker
		    );
		    status.setText(status_message);
		    patientList.setAll(tableLoader.getTableData(DB_URL, DB_USER, DB_PASSWORD));
		    tableView.refresh();
		});
		}
	
	

	public static void main(String[] args) {
		launch(args);
	}
	
	
}
