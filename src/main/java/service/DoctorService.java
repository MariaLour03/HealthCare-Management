package service;

import model.Doctor;
import model.Patient;
import repository.DoctorRepositoryImpl;

import java.util.List;

public class DoctorService {
    private final DoctorRepositoryImpl doctorRepository;

    // Constructor
    public DoctorService(DoctorRepositoryImpl doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void createDoctor(Doctor doctor) {
        doctorRepository.createDoctor(doctor);
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.getDoctorById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

    public void updateDoctor(Doctor doctor) {
        doctorRepository.updateDoctor(doctor);
    }

    public void deleteDoctor(int id) {
        doctorRepository.deleteDoctor(id);
    }

    public void addPatientToDoctor(int doctorId, Patient patient) {
        doctorRepository.addPatientToDoctor(doctorId, patient);
    }

    public void removePatientFromDoctor(int doctorId, Patient patient) {
        doctorRepository.removePatientFromDoctor(doctorId, patient);
    }
}
