package com.yd.lab6.buttoncontrol;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ButtonControl {
	  private static final String DB_URL = "jdbc:mysql://localhost:3306/lab6";
	  private static final String DB_USER = "root";
	  private static final String DB_PASSWORD = "001214";

	public String registerPatient(TextField full_name_text, TextField age_text,
			TextField Phone_text, TextField Diagnosis_text, ComboBox<String> gender_FMN, DatePicker Admission_date) {

		String name = full_name_text.getText().trim();

		String age_value = age_text.getText().trim();

		String gender = gender_FMN.getValue();

		String phone = Phone_text.getText().trim();

		String diagnosis = Diagnosis_text.getText().trim();
		
		LocalDate date = Admission_date.getValue();

		

		if (name.isEmpty() || age_value.isEmpty() || gender == null

				|| phone.isEmpty() || diagnosis.isEmpty() || date == null) {


			return "Please complete all fields.";

		}

		int age;

		try {

			age = Integer.parseInt(age_value);

		} catch (NumberFormatException ex) {


			return "Age must be a whole number.";

		}

		if (age < 16 || age > 100) {


			return "Age must be between 16 and 100.";

		}

		if (!phone.matches("^[0-9]{3}+-[0-9]{3}+-[0-9]{4}$")) {


			return "Enter a valid phone number.";

		}
		
		java.sql.Date sql_date = java.sql.Date.valueOf(date);
	    String sql = "INSERT INTO patients "

        + "(`Full Name`, `Age`, `Gender`, `phone`, `Diagnosis`, `Admission date`) "

        + "VALUES (?, ?, ?, ?, ?, ?)";
		
		 try (Connection connection =

		           DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		       PreparedStatement statement = connection.prepareStatement(sql)) {



		      statement.setString(1, name);

		      statement.setInt(2, age);

		      statement.setString(3, gender);

		      statement.setString(4, phone);

		      statement.setString(5, diagnosis);
		      
		      statement.setDate(6, sql_date);

		 int count = statement.executeUpdate();

	      if (count == 1) {
	    	  
		        clearFields(full_name_text, age_text,
						Phone_text, Diagnosis_text, gender_FMN, Admission_date);

	    	  return "Patient registered successfully!";



	      } else {

	    	  return "No record was inserted.";
	      }

	    } catch (SQLException ex) {

		      ex.printStackTrace();
	    	return "Database error: " + ex.getMessage();



	    }
		
	  }
	
	 public void clearFields(TextField full_name_text, TextField age_text,
				TextField Phone_text, TextField Diagnosis_text, ComboBox<String> gender_FMN, DatePicker Admission_date) {

		 Diagnosis_text.clear();

		 full_name_text.clear();
		 age_text.clear();

		 gender_FMN.getSelectionModel().clearSelection();

		    Phone_text.clear();

		    Admission_date.setValue(null);

		  }
	}
