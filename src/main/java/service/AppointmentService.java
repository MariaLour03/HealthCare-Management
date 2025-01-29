package service;

import model.Appointment;
import repository.AppointmentRepositoryImpl;

import java.util.List;

public class AppointmentService {
    private final AppointmentRepositoryImpl appointmentRepository;

    // Constructor
    public AppointmentService(AppointmentRepositoryImpl AppointmentRepository) {
        this.appointmentRepository = AppointmentRepository;
    }

    public void createAppointment(Appointment appointment) {
        appointmentRepository.createAppointment(appointment);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.updateAppointment(appointment);
    }

    public void deleteDoctor(int id) {
        appointmentRepository.deleteAppointment(id);
    }
}

