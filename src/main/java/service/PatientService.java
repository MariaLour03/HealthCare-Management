package service;

import model.Doctor;
import model.Patient;
import repository.PatientRepositoryImpl;

import java.util.List;

public class PatientService {

    private final PatientRepositoryImpl patientRepository;

    // Constructor
    public PatientService(PatientRepositoryImpl patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void createPatient(Patient patient) {
        patientRepository.createPatient(patient);
    }

    public Patient getPatientById(int id) {
        return patientRepository.getPatientById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    public void updatePatient(Patient patient) {
        patientRepository.updatePatient(patient);
    }

    public void deletePatient(int id) {
        patientRepository.deletePatient(id);
    }

    public void addDoctorToPatient(int patientId, Doctor doctor) {
        patientRepository.addDoctorToPatient(patientId, doctor);
    }

    public void removeDoctorFromPatient(int patientId, Doctor doctor) {
        patientRepository.removeDoctorFromPatient(patientId, doctor);
    }
}