package com.yd.lab6.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yd.lab6.patient.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Table {

    public ObservableList<Patient> getTableData(String DB_URL, String DB_USER, String DB_PASSWORD) {
        ObservableList<Patient> list1 = FXCollections.observableArrayList();
        String sql = "SELECT `patient_id`, `Full Name`, `Age`, `Gender`, `phone`, `Diagnosis`, `Admission date` FROM patients;";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getString("patient_id"),
                        rs.getString("Full Name"),
                        rs.getString("Age"),
                        rs.getString("Gender"),
                        rs.getString("phone"),
                        rs.getString("Diagnosis"),
                        rs.getString("Admission date")
                );
                list1.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list1;
    }
}