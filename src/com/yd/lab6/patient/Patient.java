package com.yd.lab6.patient;

import java.util.Objects;

public class Patient {
    private String patient_id;
    private String Full_Name;
    private String Age;
    private String Gender;
    private String phone;
    private String Diagnosis;
    private String Admission_date;

    public Patient() {
    }

    public Patient(
            String patient_id,
            String Full_Name,
            String Age,
            String Gender,
            String phone,
            String Diagnosis,
            String Admission_date
    ) {
        this.patient_id = patient_id;
        this.Full_Name = Full_Name;
        this.Age = Age;
        this.Gender = Gender;
        this.phone = phone;
        this.Diagnosis = Diagnosis;
        this.Admission_date = Admission_date;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String Diagnosis) {
        this.Diagnosis = Diagnosis;
    }

    public String getAdmission_date() {
        return Admission_date;
    }

    public void setAdmission_date(String Admission_date) {
        this.Admission_date = Admission_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient patient)) return false;
        return Objects.equals(patient_id, patient.patient_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(patient_id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_id='" + patient_id + '\'' +
                ", Full_Name='" + Full_Name + '\'' +
                ", Age='" + Age + '\'' +
                ", Gender='" + Gender + '\'' +
                ", phone='" + phone + '\'' +
                ", Diagnosis='" + Diagnosis + '\'' +
                ", Admission_date='" + Admission_date + '\'' +
                '}';
    }
}